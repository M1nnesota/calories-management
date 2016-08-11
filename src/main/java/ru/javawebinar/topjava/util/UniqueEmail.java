package ru.javawebinar.topjava.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "User with this email already present in application";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
