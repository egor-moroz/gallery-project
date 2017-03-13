package com.gallery.validator;

import com.gallery.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidUniqueEmailValidator implements ConstraintValidator<ValidUniqueEmail, String> {

    @Autowired
    GalleryService galleryService;


    @Override
    public void initialize(ValidUniqueEmail validUniqueEmail) {

    }

    @Override
    public boolean isValid(String userEmail, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;
        valid = galleryService.isUserEmailUnique(userEmail);
        return valid;
    }
}
