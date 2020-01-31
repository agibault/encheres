package org.eni.encheres.buisiness.validator;

import org.eni.encheres.buisiness.Factory;
import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

abstract class Validator {

    public Validator(Object bean) {
        validate(bean);
    }

    private Map<String, String> errors = new HashMap<>();

    protected void addError(String errorName, String error) {
        errors.put(errorName, error);
    }

    private void addBeanViolationsToErrors(Object bean) {
        Set<ConstraintViolation<Object>> violations = getViolationBean(bean);

        for (ConstraintViolation<Object> violation : violations) {
            addError(violation.getPropertyPath().toString(), violation.getMessage());
        }
    }

    private Set<ConstraintViolation<Object>> getViolationBean(Object object) {
        javax.validation.Validator validator = Factory.getValidator();
        return validator.validate(object);
    }

    protected void validate(Object bean) {
        addBeanViolationsToErrors(bean);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
