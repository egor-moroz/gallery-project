package com.gallery.Util;

import com.gallery.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, User> {
    @Override
    public void initialize(ValidPassword validPassword) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext cvc) {

        boolean valid = user.getPassword().equals(user.getConfirmPassword());

        if(!valid) {
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate("{validation.user.ValidPassword.message}").addNode("password").addConstraintViolation();
        }
            return valid;
    }
}
