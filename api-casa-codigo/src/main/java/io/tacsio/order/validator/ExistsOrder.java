package io.tacsio.order.validator;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ExistsOrderValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface ExistsOrder {
	String message() default "{Order.notFound}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}