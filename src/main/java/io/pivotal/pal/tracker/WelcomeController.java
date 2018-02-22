package io.pivotal.pal.tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private Service service;

    private String message;

    @RequestMapping("/")
    public String sayHello(){
        return message;
    }
    @RequestMapping("/Bye")
    public String sayBye(){
      return service.sayBye();

    }

    public WelcomeController(@Value("${WELCOME_MESSAGE}") String message){
        this.message=message;

    }




}
