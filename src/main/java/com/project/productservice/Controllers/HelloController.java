package com.project.productservice.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// class will have multiple methods that will be serving http request at /hello

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/")
    public String sayHello(){
        return "Hello there!";
    }

    @GetMapping("/sayName/{name}/{times}")
    public String sayHelloWithName(@PathVariable("name") String name,@PathVariable("times") int times){
        String result = "Hello there! </br>";
        for(int i=1;i<=times;i++){
            result += name;
            result += "</br>";
        }
        return result;
    }

}
