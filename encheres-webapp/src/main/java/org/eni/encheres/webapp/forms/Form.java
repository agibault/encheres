package org.eni.encheres.webapp.forms;

import org.eni.encheres.buisiness.Factory;
import org.eni.encheres.webapp.forms.exceptions.FormExeption;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Form {

    private Map<String, String> errors = new HashMap<>();
    protected HttpServletRequest request;

    public Form(HttpServletRequest request) {
        this.request = request;
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

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
        Validator validator = Factory.getValidator();
        return validator.validate(object);
    }

    protected void validateBean(Object bean)  {
        addBeanViolationsToErrors(bean);
    }
    abstract protected void validate() throws FormExeption;

    protected String formatString(String string) {
        if (string == null) {
            return "";
        }
        return string.trim().toLowerCase();
    }

    protected String getParam(String param) {
        return formatString(request.getParameter(param));
    }

    protected int getIntParam(String param) {
        int value = 0;
        if (param != null) {
            try {
                value = Integer.parseInt(getParam(param));
            } catch (NumberFormatException e) {
                addError(param, "not a valid number");
            }
        }
        return value;
    }

    public LocalDate getDateParam(String param) {
        LocalDate date = null;
        if (param != null) {
            try {
                date = LocalDate.parse(getParam(param));
            } catch (DateTimeException e) {
                addError(param, "not a valid date");
            }
        }
        return date;
    }
    protected boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }


}