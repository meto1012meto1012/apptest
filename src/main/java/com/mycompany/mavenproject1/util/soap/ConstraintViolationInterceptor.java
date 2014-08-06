

package com.mycompany.mavenproject1.util.soap;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;

import static javax.validation.ElementKind.RETURN_VALUE;


public class ConstraintViolationInterceptor implements Serializable {
	private static final long serialVersionUID = -5185419395102871261L;

	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
		Object result = null;
		try {
			result = ctx.proceed();
		}
		catch (ConstraintViolationException e) {
			final Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			
			// Rueckgabewert null oder leere Liste? d.h. NOT_FOUND?
			if (violations.size() > 1) {
				throw new RuntimeException(e);
			}

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
					return invalidValue;
				}
				throw new RuntimeException(e);
			}
		}
		return result;
	}
}
