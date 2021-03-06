package br.com.deveficiente.bolaoapi.shared.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsValidation.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Exists {
    String message() default "not found";

    Class<?> entityClass();

    String entityField() default "id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
