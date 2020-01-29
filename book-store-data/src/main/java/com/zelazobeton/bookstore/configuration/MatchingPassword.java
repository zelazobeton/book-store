package com.zelazobeton.bookstore.configuration;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchingPassword {
    String message() default "Passwords do not match";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
