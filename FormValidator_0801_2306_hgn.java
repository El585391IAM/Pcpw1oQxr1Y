// 代码生成时间: 2025-08-01 23:06:43
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * FormValidator is a utility class for validating form data using Bean Validation API with Hibernate Validator.
 * It provides a simple way to validate objects and report any validation errors.
 */
public class FormValidator {

    // Validator instance for validating objects
    private static final Validator VALIDATOR = initValidator();

    // Initialize the validator instance
    private static Validator initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    /**
     * Validates an object and returns a Set of ConstraintViolations if any.
     *
     * @param object The object to be validated.
     * @return A Set of ConstraintViolations if the object is invalid, otherwise an empty Set.
     */
    public static Set<ConstraintViolation<?>> validate(Object object) {
        Set<ConstraintViolation<?>> violations = VALIDATOR.validate(object);
        if (!violations.isEmpty()) {
            System.err.println("Validation failed: " + violations);
        }
        return violations;
    }

    // Main method for demonstration purposes
    public static void main(String[] args) {
        // Example usage with a dummy class
        class DummyClass {
            private String name;

            public DummyClass(String name) {
                this.name = name;
            }
        }

        DummyClass dummy = new DummyClass("John Doe");
        Set<ConstraintViolation<?>> violations = validate(dummy);
        if (!violations.isEmpty()) {
            System.out.println("Validation failed for DummyClass: " + violations);
        } else {
            System.out.println("Validation passed for DummyClass");
        }
    }
}
