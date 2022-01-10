import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class NotNullIfValidator implements ConstraintValidator<FieldsValueMatch, UpdateContract> {
    private String[] fields;
    private Status ifStatus;
    private String message;

    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
        this.ifStatus = constraintAnnotation.ifStatus();
    }

    @Override
    public boolean isValid(UpdateContract o, ConstraintValidatorContext constraintValidatorContext) {
        Status status = o.getStatus();
        boolean isValid = true;
        if (status == ifStatus) {
            for (String f : fields) {

                Object fieldValue = new BeanWrapperImpl(o)
                        .getPropertyValue(f);

                if (Objects.isNull(fieldValue)) {
                    isValid = false;
                    constraintValidatorContext.disableDefaultConstraintViolation();
                    constraintValidatorContext.buildConstraintViolationWithTemplate(this.message)
                            .addPropertyNode(f)
                            .addConstraintViolation();
                }

            }
        }
        return isValid;
    }
}

