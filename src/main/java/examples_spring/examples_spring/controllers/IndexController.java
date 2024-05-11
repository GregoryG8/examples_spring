package examples_spring.examples_spring.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;






@Controller

public class IndexController {

    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("titulo", "Soy un titulo");
        model.addAttribute("saludo", "Hola a todos mis estimados");
        model.addAttribute("show", true);
        // List<String> series = List.of("Dexter", "Game of Thrones", "Vikings");
        // model.addAttribute("series", series);
        return "index";
    }

    @GetMapping(value = "/index-mv")
    public ModelAndView indexMV(ModelAndView mv){
        mv.addObject("titulo", "Soy un titulo");
        mv.addObject("saludo", "Hola a todos mis estimados");
        mv.addObject("show", true);
        //List<String> series = List.of("Dexter", "Game of Thrones", "Vikings");
        //mv.addObject("series", series);
        mv.setViewName("index");
        return mv;
    }

    @ModelAttribute("series")
    public List<String> getSeries(){
        return List.of("Dexter", "Game of Thrones", "Vikings", "XMEN 97");
    }

    @PostMapping(value = "/index-post") 
    public String indexPostMapping(){
        return "index";
    }

    // Si no se especifica el metodo se puede usar todos

    @RequestMapping(value = "/index-reg-mapping", method = RequestMethod.GET) 
    public String indexRequestMapping(){
        return "index";
    }

    
    
    

}
