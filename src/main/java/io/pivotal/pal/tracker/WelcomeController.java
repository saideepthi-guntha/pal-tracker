package io.pivotal.pal.tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private Service service;


    @RequestMapping("/")
    public String sayHello(){
        return "hello";
    }
    @RequestMapping("/Bye")
    public String sayBye(){
      return service.sayBye();


    }


}
