

package com.mycompany.mavenproject1.util.persistence;

import com.mycompany.mavenproject1.util.interceptor.Log;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.WARNING;



@ApplicationScoped
@Named
public class FileHelper implements Serializable {
	private static final long serialVersionUID = 12904207356717310L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
	
	// Zulaessige Extensionen fuer Upload mit einer Webseite
	private String extensionen;
	
	// Verzeichnis fuer hochgeladene Dateien
	private transient Path path;

	@PostConstruct
	private void postConstruct() {
		// Bei .flv wird der Mime-Type weder bei RichFaces noch bei RESTEasy erkannt
		extensionen = "jpg, jpeg, png, mp4, wav";
		LOGGER.info("Extensionen fuer Datei-Upload: " + extensionen);
		
		String appName = null;
		Context ctx = null;
		try {
			ctx = new InitialContext();  // InitialContext implementiert nicht das Interface Autoclosable
			appName = String.class.cast(ctx.lookup("java:app/AppName"));				
		}
		catch (NamingException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (ctx != null) {
					try {
						ctx.close();
					}
					catch (NamingException e) {
						LOGGER.log(WARNING, e.getMessage(), e);
					}
			}
		}
		
		// Verzeichnis im Dateisystem des Betriebssystems
		path = Paths.get(System.getenv("JBOSS_HOME"), "standalone", "deployments", "filesDb.war", appName);
				
		if (Files.exists(path)) {
			LOGGER.info("Verzeichnis fuer hochgeladene Dateien: " + path);
		}
		else {
			LOGGER.severe("Kein Verzeichnis " + path + " fuer hochgeladene Dateien vorhanden");
		}
	}

	/**
	 * MIME-Type zu einer Datei als byte[] ermitteln
	 * @param bytes Byte-Array, zu dem der MIME-Type ermitelt wird
	 * @return Der zugehoerige MIME-Type
	 */
	public MimeType getMimeType(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		try (final InputStream inputStream = new ByteArrayInputStream(bytes)) {
			final String mimeTypeStr = URLConnection.guessContentTypeFromStream(inputStream);
			
			if (LOGGER.isLoggable(FINEST)) {
				LOGGER.finest("MIME-Type: " + mimeTypeStr);
			}
			return MimeType.build(mimeTypeStr);
		}
		catch (IOException e) {
			LOGGER.warning("Fehler beim Ermitteln des MIME-Types");
			return null;
		}
	}
	
	public String getFilename(Class<? extends Serializable> clazz, Object id, MimeType mimeType) {
		final String filename = clazz.getSimpleName() + "_" + id + "." + mimeType.getExtension();
		if (LOGGER.isLoggable(FINEST)) {
			LOGGER.finest("Dateiname: " + filename);
		}
		return filename;
	}
	
	public String getExtensionen() {
		return extensionen;
	}
	
	@Log
	public void store(File file) {
		if (file == null) {
			return;
		}
		
		final String filename = file.getFilename();
		final Path absoluteFilename = path.resolve(filename);
		if (LOGGER.isLoggable(FINEST)) {
			LOGGER.finest("Absoluter Dateiname: " + absoluteFilename);
		}
		
		// aktuelle Datei nicht ueberschreiben?
		if (Files.exists(absoluteFilename)) {
			long creationTime = 0L;
			try {
				creationTime = Files.getFileAttributeView(absoluteFilename, BasicFileAttributeView.class)
				                    .readAttributes()
									.creationTime()
									.toMillis();
			}
			catch (IOException e) {
				LOGGER.log(WARNING, "Fehler beim Lesen des Erzeugungsdatums der Datei " + absoluteFilename, e);
			}
			
			// Die Datei wurde beim Hochladen evtl. in einem parallelen Thread angelegt,
			// der evtl. vor dem Abspeichern der Verwaltungsdaten in der DB fertig war.
			// Als Zeitunterschied bzw. Toleranz sollten 1000 Millisekunden ausreichend sein.
			if (creationTime + 1000 > file.getAktualisiert().getTime()) {
				if (LOGGER.isLoggable(FINEST)) {
					LOGGER.finest("Die Datei " + filename + " existiert bereits");
				}
				return;
			}
		}
		
		// byte[] als Datei abspeichern
		try (final InputStream inputStream = new ByteArrayInputStream(file.getBytes())) {
			Files.copy(inputStream, absoluteFilename, REPLACE_EXISTING);
		}
		catch (IOException e) {
			LOGGER.log(WARNING, "Fehler beim Speichern der Datei " + absoluteFilename, e);
		}
	}
}
