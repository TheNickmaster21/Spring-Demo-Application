package in.nickma.DemoRest.controller;

import in.nickma.DemoRest.model.HelloHistory;
import in.nickma.DemoRest.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name) {
        this.helloService.makeHistory(name);
        return String.format("Hello %s!", name);
    }

    @GetMapping("/hello-history")
    public ResponseEntity<List<HelloHistory>> getHelloHistory(@RequestParam(required = false) String name) {
        return this.helloService.getHistory(name);
    }
}
