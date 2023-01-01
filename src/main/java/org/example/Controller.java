package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping(value="/helloWorld/{string}")
    public String index(@PathVariable ("string") String string){
      if(string.equalsIgnoreCase("hello")){
          return (string+ " World !!");
      }
      else{
          return (string);
      }
    }
}
