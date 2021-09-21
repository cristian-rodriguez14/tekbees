package org.o7planning.springmvcshoppingcart.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.o7planning.springmvcshoppingcart.model.ClienteInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ClienteInfoValidator implements Validator{

	private EmailValidator emailValidator = EmailValidator.getInstance();

    // This Validator support CustomerInfo class.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ClienteInfo.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
    	ClienteInfo custInfo = (ClienteInfo) target;

        // Check the fields of CustomerInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customerForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.customerForm.phone");

        if (!emailValidator.isValid(custInfo.getClienteEmail())) {
            errors.rejectValue("email", "Pattern.customerForm.email");
        }
    }
}
