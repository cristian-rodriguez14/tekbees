package org.o7planning.springmvcshoppingcart.validator;

import org.o7planning.springmvcshoppingcart.dao.ProductoDAO;
import org.o7planning.springmvcshoppingcart.entity.Producto;
import org.o7planning.springmvcshoppingcart.model.ProductoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductoInfoValidator implements Validator{

	@Autowired
    private ProductoDAO ProductoDAO;

    // This Validator support ProductoInfo class.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ProductoInfo.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductoInfo ProductoInfo = (ProductoInfo) target;

        // Check the fields of ProductoInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");

        String code = ProductoInfo.getProductoCodbarras();
        if (code != null && code.length() > 0) {
            if (code.matches("\\s+")) {
                errors.rejectValue("code", "Pattern.productForm.code");
            } else {
                Producto Producto = ProductoDAO.findProducto(code);
                if (Producto != null) {
                    errors.rejectValue("code", "Duplicate.productForm.code");
                }
            }
        }
    }
}
