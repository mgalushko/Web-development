package com.store.webstore;
import com.store.webstore.domain.Automobile;
import com.store.webstore.domain.Cart;
import com.store.webstore.repos.AutomobileRepo;
import com.store.webstore.repos.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private AutomobileRepo automobileRepo;
    @Autowired
    private CartRepo cartRepo;
    @GetMapping
    public String greaating (@RequestParam(name = "name" , required = false,defaultValue = "world")String name, Model model){

        Iterable<Automobile> automobiles = automobileRepo.findAll();
        model.addAttribute("automobiles", automobiles);
        return "main";
    }
    @GetMapping("/cart")
    public String cart(Map<String, Object> model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<String> carts = (ArrayList<String>) session.getAttribute("automobileList");
        model.put("carts", carts);
        ArrayList<Integer> costs = (ArrayList<Integer>) session.getAttribute("costList");
        model.put("costs", costs);
        return "cart";
    }
    @PostMapping("/")
    public @ResponseBody
    String addNewItem(@RequestParam String name, @RequestParam Integer cost, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ArrayList<String> automobileList = (ArrayList<String>) session.getAttribute("automobileList");
        ArrayList<Integer> costList = (ArrayList<Integer>) session.getAttribute("costList");
        if (automobileList == null && costList == null) {
            automobileList = new ArrayList<>();
            costList = new ArrayList<>();
        }
        assert automobileList != null;
        automobileList.add(name);
        costList.add(cost);
        session.setAttribute("automobileList", automobileList);
        session.setAttribute("costList", costList);
        return "welcome";
    }
    @PostMapping("/cart")
    public @ResponseBody
    String deleteItem(@RequestParam int id, Map<String, Object> model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<String> automobileList = (ArrayList<String>) session.getAttribute("automobileList");
        ArrayList<Integer> costList = (ArrayList<Integer>) session.getAttribute("costList");
        automobileList.remove(id);
        costList.remove(id);
        session.setAttribute("automobilesList", automobileList);
        session.setAttribute("costList", costList);

        return "cart";
    }
    @PostMapping("/addbase")
    public @ResponseBody
    String addbase(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<String> automobileList = (ArrayList<String>) session.getAttribute("automobileList");
        ArrayList<Integer> costList= (ArrayList<Integer>) session.getAttribute("costList");

        for(int i = 0; i < automobileList.size(); i++)
        {
            Cart cart = new Cart();
            cart.setName(automobileList.get(i));
            cart.setPrice(costList.get(i));
            cartRepo.save(cart);
        }
        clearbase(request);
        return "base";
    }


    public void clearbase(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<String> automobileList = (ArrayList<String>) session.getAttribute("automobileList");
        ArrayList<Integer> costList = (ArrayList<Integer>) session.getAttribute("costList");
        automobileList.clear();
        costList.clear();
        session.setAttribute("automobileList", automobileList);
        session.setAttribute("costList", costList);
    }

}
