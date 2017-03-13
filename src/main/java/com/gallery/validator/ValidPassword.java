package com.gallery.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;


@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidPasswordValidator.class })
@Documented
public @interface ValidPassword {

    String message() default "password and confirm password not equal" ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}