package com.zelazobeton.bookstore.configuration;

import com.zelazobeton.bookstore.commands.UserCommand;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<MatchingPassword, Object> {

    @Override
    public void initialize(MatchingPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserCommand user = (UserCommand) obj;
        boolean isValid = user.getPassword().equals(user.getMatchingPassword());
        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode( "matchingPassword" ).addConstraintViolation();
        }
        return isValid;
    }

}