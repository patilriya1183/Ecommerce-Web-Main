package com.ecommerce.webmain;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.webmain.orders.CustomerOrder;
import com.ecommerce.webmain.orders.OrderDTO;
import com.ecommerce.webmain.orders.OrderService;
import com.ecommerce.webmain.orders.ProdDTO;
import com.ecommerce.webmain.products.Product;
import com.ecommerce.webmain.products.ProductDTO;
import com.ecommerce.webmain.products.ProductServices;

@Controller
public class MainController {
	@Autowired
	private ProductServices ps;
	
	@Autowired
	private OrderService os;

	@GetMapping("/")
	public String home(Model model) {
		List<Product> products = ps.getAllProducts();
		List<ProductDTO> productDTOs = products.stream().map(product -> {
            String base64Image = Base64.getEncoder().encodeToString(product.getImage());
            return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), base64Image);
        }).collect(Collectors.toList());
		model.addAttribute("products", productDTOs);
		return "index";
	}

	@GetMapping("/history")
	public String history() {
		return "history";
	}
	
	@GetMapping("/seller")
	public String seller(Model model) {
		model.addAttribute("product", new Product());
		return "seller";
	}
	
	@GetMapping("/login_or_signup")
	public String login_or_signup() {
		return "login_or_signup";
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		return "cart";
	}
	
	@GetMapping("/buy")
	public String buy() {
		return "buy_now";
	}
	
	@GetMapping("/order_success/{orderid}")
	public String order(@PathVariable Long orderid, Model model) {
		CustomerOrder c = os.getOrderById(orderid);
		List<ProdDTO> prod = new ArrayList<ProdDTO>();
		for(int i=0; i<c.getQuantity().size(); i++) {
			prod.add(new ProdDTO(c.getName(), c.getPrice().get(i), c.getQuantity().get(i)));
		}
		model.addAttribute("order", new OrderDTO(orderid, c.getName(), c.getEmail(), c.getMobile(), c.getAddress(), c.getPayment(), c.getTotalprice(), prod));
		return "order_success";
	}

	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
}
