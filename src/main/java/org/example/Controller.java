package org.example;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

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

    @GetMapping(value="/pokemon/{pokemonName}")
    public ResponseEntity<String> callingPokemonApi(@PathVariable ("pokemonName") String pokemonName){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonName;
            RestTemplate restTemplate = new RestTemplate();
            /*ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);*/
            HttpEntity<String> entity=new HttpEntity<String>(headers);
            return restTemplate.exchange(url,HttpMethod.GET,entity,String.class);
            //return result;
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such Pokemon exists");
        }

    }
}
