package co.istad.mobileBanking.api.user.validator.passowrd;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;


public class PasswordMatchConstraintValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String password;
    private String conformPassword;
    private String message;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        this.password= constraintAnnotation.password();
        this.conformPassword= constraintAnnotation.confirmPassword();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object conformPasswordValue = new BeanWrapperImpl(value).getPropertyValue(conformPassword);

        boolean isValid= false;

        if (passwordValue != null){
            isValid = passwordValue.equals(conformPasswordValue);
        }

        if (!isValid){
            context.disableDefaultConstraintViolation();
        }

        // Sending one message each time failed validation.
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(password)
                .addConstraintViolation();

        // Sending one message each time failed validation.
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(conformPassword)
                .addConstraintViolation();
        return isValid;
    }
}
