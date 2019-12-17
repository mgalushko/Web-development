package com.store.webstore;
import com.store.webstore.domain.Automobile;
import com.store.webstore.repos.AutomobileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MainController {
    @Autowired
    private AutomobileRepo automobileRepo;
    @GetMapping
    public String greaating (@RequestParam(name = "name" , required = false,defaultValue = "world")String name, Model model){

        Automobile automobile = new Automobile();
        automobile.setName("хук");

        automobileRepo.save(automobile);
        Iterable<Automobile> automobiles = automobileRepo.findAll();
        model.addAttribute("automobiles", automobiles);

        return "greeting";
    }

}
