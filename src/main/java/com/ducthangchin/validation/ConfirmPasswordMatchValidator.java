package com.ducthangchin.validation;

import com.ducthangchin.model.WebUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordMatchValidator implements ConstraintValidator<ConfirmPasswordMatch, WebUser> {


    @Override
    public void initialize(ConfirmPasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(WebUser user, ConstraintValidatorContext context) {

        String plainPassword = user.getPlainPassword();
        String confirmPassword = user.getConfirmPassword();

        if (plainPassword == null || plainPassword.length() == 0){
            return true;
        }

        if (!plainPassword.equals(confirmPassword)) {
            return false;
        }

        return true;

    }
}
