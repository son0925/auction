package org.son.webapplicationserver.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.son.webapplicationserver.common.annotation.UserId;

import java.util.regex.Pattern;

public class UserIdValidator implements ConstraintValidator<UserId,String> {

    private String regexp;

    @Override
    public void initialize(UserId constraintAnnotation) {
        regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.matches(regexp, value);
    }
}
