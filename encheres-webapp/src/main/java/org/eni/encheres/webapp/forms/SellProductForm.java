package org.eni.encheres.webapp.forms;

import org.eni.encheres.buisiness.validator.ProductValidator;
import org.eni.encheres.model.Category;
import org.eni.encheres.model.Product;
import org.eni.encheres.model.Withdrawal;
import javax.servlet.http.HttpServletRequest;


public class SellProductForm extends Form {
    private Product product;

    public SellProductForm(HttpServletRequest request, Product product) {
        super(request);
        this.product = product;
    }

    public void hydrate(Product product) throws Exception {
        product.setName(getParam("product_name"));
        product.setDescription(getParam("product_description"));
        product.setCategory(new Category(getParam("product_category")));
        product.setStartingPrice(getIntParam("product_initPrice"));
        product.setDateStartBid(getDateParam("product_startBid"));
        product.setDateEndBid(getDateParam("product_endBid"));
        product.setWithdrawal(getWithdrawal());
    }



    public Withdrawal getWithdrawal() {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setStreet(getParam("withdrawal_street"));
        withdrawal.setPostalCode(getParam("withdrawal_postalCode"));
        withdrawal.setCity(getParam("withdrawal_city"));
        return withdrawal;
    }

    @Override
    public boolean isValid() {
        return getErrors().isEmpty();
    }

//    private Category retrieveCategory(String categoryNane) {
//        Category category;
//        switch (categoryNane) {
//            case "ameublement":
//                category = Category.FURNISHING;
//                break;
//            case "vetement":
//                category = Category.CLOTHING;
//                break;
//            case "sport&loisirs":
//                category = Category.SPORTS_AND_HOBBIES;
//                break;
//            default:
//                category = Category.COMPUTING;
//        }
//        return category;
//    }

    public void validate() {
        ProductValidator productValidator = new ProductValidator(product);
        if (!productValidator.getErrors().isEmpty()) {
            addError("f", "e");
        }
    }
}
