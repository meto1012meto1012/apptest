

package com.mycompany.mavenproject1.util.rest;

import com.mycompany.mavenproject1.util.interceptor.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ViolationReport;

import static javax.validation.ElementKind.RETURN_VALUE;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static org.jboss.resteasy.api.validation.ConstraintType.Type.PARAMETER;


@Provider
@ApplicationScoped
@Log
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {	@Override
	public Response toResponse(ConstraintViolationException exception) {
		final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		// Rueckgabewert null oder leere Liste? d.h. NOT_FOUND?
		if (violations.size() == 1) {
			final ConstraintViolation<?> violation = violations.iterator().next();
			
			// Ende des Validation-Pfades suchen
			final Iterator<Path.Node> pathIterator = violation.getPropertyPath().iterator();
			Node node = null;
			while (pathIterator.hasNext()) {
				node = pathIterator.next();
			}
			
			// Verletzung des Rueckgabewertes?
			if (node != null && node.getKind() == RETURN_VALUE) {
				final Object invalidValue = violation.getInvalidValue();
				// null oder leere Liste?
				if (invalidValue == null || (invalidValue instanceof List && ((List<?>) invalidValue).isEmpty())) {
					return Response.status(NOT_FOUND)
							       .type(TEXT_PLAIN)
						           .entity(violation.getMessage())
		                           .build();
				}
			}
		}
		
		return Response.status(BAD_REQUEST)
				       .entity(toViolationReport(violations))
                       .build();
	}
	
	private static ViolationReport toViolationReport(Set<ConstraintViolation<?>> violations) {
		final ArrayList<ResteasyConstraintViolation> parameterViolations = new ArrayList<>();
		violations.forEach(v -> {
			final String path = v.getPropertyPath().toString();
			final String message = v.getMessage();
			final Object invalidValue = v.getInvalidValue();
			final String inalidValueStr = Objects.toString(invalidValue);
			final ResteasyConstraintViolation resteasyConstraintViolation =
					                          new ResteasyConstraintViolation(PARAMETER, path, message, inalidValueStr);
			parameterViolations.add(resteasyConstraintViolation);
		});
		
		final ViolationReport violationReport = new ViolationReport();
		violationReport.setParameterViolations(parameterViolations);
		return violationReport;
	}
}
