
package com.mycompany.mavenproject1.util.persistence;


public enum MimeType {
	JPEG("image/jpeg"),
	PJPEG("image/pjpeg"),
	PNG("image/png"),
	MP4("video/mp4"),
	WAV("audio/wav");
	
	private final String value;

	private MimeType(String value) {
		this.value = value;
	}
	
	public static MimeType build(String value) {
		if (value == null) {
			return null;
		}
		
		switch (value.toUpperCase()) {
			case "image/jpeg":
			case "IMAGE/JPEG":
				return JPEG;
			case "image/pjpeg":
			case "IMAGE/PJPEG":
				return PJPEG;
			case "image/png":
			case "IMAGE/PNG":
				return PNG;
			case "video/mp4":
			case "VIDEO/MP4":
				return MP4;
			case "audio/wav":
			case "AUDIO/WAV":
				return WAV;
			
			default:			return null;
		}
	}
	
	public String getExtension() {
		switch (this) {
			case JPEG:	return "jpeg";
			case PJPEG:	return "jpeg";
			case PNG:	return "png";
			case MP4:	return "mp4";
			default:	throw new RuntimeException("Der MIME-Type " + this + " wird nicht unterstuetzt");
		}
	}
	
	public static MimeType buildFromExtension(String extension) {
		switch (extension) {
			case "jpeg":	return JPEG;
			case "png":		return PNG;
			case "mp4":		return MP4;
			default:	 	throw new RuntimeException("Die Extension " + extension + " wird nicht unterstuetzt");
		}
	}
	
	public MultimediaType getMultimediaType() {
		if (value.startsWith("image/")) {
			return MultimediaType.IMAGE;
		}
		if (value.startsWith("video/")) {
			return MultimediaType.VIDEO;
		}
		if (value.startsWith("audio/")) {
			return MultimediaType.AUDIO;
		}
		
		throw new RuntimeException("Der MultimediaType " + this + " wird nicht unterstuetzt");
	}
	
	@Override
	public String toString() {
		return value;
	}
}
