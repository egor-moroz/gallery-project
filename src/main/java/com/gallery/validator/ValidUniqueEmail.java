package com.gallery.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidUniqueEmailValidator.class })
@Documented
public @interface ValidUniqueEmail {

    String message() default "User with this email already exist";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
