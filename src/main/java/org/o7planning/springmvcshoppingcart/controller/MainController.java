package org.o7planning.springmvcshoppingcart.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.o7planning.springmvcshoppingcart.dao.DetalleCompraDAO;
import org.o7planning.springmvcshoppingcart.dao.ProductoDAO;
import org.o7planning.springmvcshoppingcart.entity.Producto;
import org.o7planning.springmvcshoppingcart.model.CartInfo;
import org.o7planning.springmvcshoppingcart.model.ClienteInfo;
import org.o7planning.springmvcshoppingcart.model.PaginationResult;
import org.o7planning.springmvcshoppingcart.model.ProductoInfo;
import org.o7planning.springmvcshoppingcart.util.Utils;
import org.o7planning.springmvcshoppingcart.validator.ClienteInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class MainController {

    @Autowired
    private DetalleCompraDAO orderDAO;

    @Autowired
    private ProductoDAO productDAO;

    @Autowired
    private ClienteInfoValidator customerInfoValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        // For Cart Form.
        // (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
        if (target.getClass() == CartInfo.class) {

        }
        // For Customer Form.
        // (@ModelAttribute("customerForm") @Validated CustomerInfo
        // customerForm)
        else if (target.getClass() == ClienteInfo.class) {
            dataBinder.setValidator(customerInfoValidator);
        }

    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    // Product List page.
    // Danh sách sản phẩm.
    @RequestMapping({ "/productList" })
    public String listProductHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;

        PaginationResult<ProductoInfo> result = productDAO.queryProductos(page, //
                maxResult, maxNavigationPage, likeName);

        model.addAttribute("paginationProducts", result);
        return "productList";
    }

    @RequestMapping({ "/buyProduct" })
    public String listProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {

        Producto product = null;
        if (code != null && code.length() > 0) {
            product = productDAO.findProducto(code);
        }
        if (product != null) {

            // Cart info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            ProductoInfo productInfo = new ProductoInfo(product);

            cartInfo.addProduct(productInfo, 1);
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }

    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
        Producto product = null;
        if (code != null && code.length() > 0) {
            product = productDAO.findProducto(code);
        }
        if (product != null) {

            // Cart Info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            ProductoInfo productInfo = new ProductoInfo(product);

            cartInfo.removeProduct(productInfo);

        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }

    // POST: Update quantity of products in cart.
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("cartForm") CartInfo cartForm) {

        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updatecantidad(cartForm);

        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }

    // GET: Show Cart
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);

        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }

    // GET: Enter customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

        CartInfo cartInfo = Utils.getCartInSession(request);
     
        // Cart is empty.
        if (cartInfo.isEmpty()) {
            
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        }

        ClienteInfo customerInfo = cartInfo.getClienteInfo();
        if (customerInfo == null) {
            customerInfo = new ClienteInfo();
        }

        model.addAttribute("customerForm", customerInfo);

        return "shoppingCartCustomer";
    }

    // POST: Save customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("customerForm") @Validated ClienteInfo customerForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
 
        // If has Errors.
        if (result.hasErrors()) {
            // Forward to reenter customer info.
            return "shoppingCartCustomer";
        }

        CartInfo cartInfo = Utils.getCartInSession(request);


        // Redirect to Confirmation page.
        return "redirect:/shoppingCartConfirmation";
    }

    // GET: Review Cart to confirm.
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);

        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
    }

    // POST: Send Cart (Save).
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);

        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
    }

    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {

        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);

        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }

        return "shoppingCartFinalize";
    }

    @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("code") String code) throws IOException {
        Producto product = null;
        if (code != null) {
            product = this.productDAO.findProducto(code);
        }
        if (product != null && product.getProductoimagen() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        }
        response.getOutputStream().close();
    }
    
}