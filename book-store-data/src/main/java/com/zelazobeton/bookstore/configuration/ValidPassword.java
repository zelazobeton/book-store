package com.zelazobeton.bookstore.configuration;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Password must contain between 6 and 14 characters with at least one digit, " +
                             "one lower case character and one upper case character";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
