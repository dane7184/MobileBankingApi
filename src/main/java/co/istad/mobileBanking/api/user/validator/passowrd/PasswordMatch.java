package co.istad.mobileBanking.api.user.validator.passowrd;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Constraint(validatedBy = PasswordMatchConstraintValidator.class)
@Retention(RUNTIME)
@Target({TYPE})
public @interface PasswordMatch {

    String message() default "Password is not match";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String password();

    String confirmPassword();

    @Target({ElementType.TYPE})
    @Retention(RUNTIME)
    @interface List {
        PasswordMatch[] value();
    }


}
