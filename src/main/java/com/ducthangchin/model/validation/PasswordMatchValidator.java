package com.ducthangchin.model.validation;

import com.ducthangchin.model.WebUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, WebUser> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(WebUser value, ConstraintValidatorContext context) {

        String plainPassword = value.getPlainPassword();
        String repeatPassword = value.getRepeatPassword();

        if(plainPassword == null || plainPassword.length() == 0) {
            return true;
        }

        if(!plainPassword.equals(repeatPassword)){
            return false;
        }

        return true;
    }
}
