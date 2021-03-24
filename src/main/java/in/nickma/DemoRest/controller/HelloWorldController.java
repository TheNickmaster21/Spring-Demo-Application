package in.nickma.DemoRest.controller;

import in.nickma.DemoRest.model.HelloHistory;
import in.nickma.DemoRest.repository.HelloHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    HelloHistoryRepository helloHistoryRepository;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name) {
        this.helloHistoryRepository.save(new HelloHistory(name));
        return String.format("Hello %s!", name);
    }

    @GetMapping("/hello-history")
    public ResponseEntity<List<HelloHistory>> getHelloHistory(@RequestParam(required = false) String name) {
        if (name == null) {
            return new ResponseEntity<List<HelloHistory>>(this.helloHistoryRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<HelloHistory>>(this.helloHistoryRepository.findByName(name), HttpStatus.OK);

        }
    }
}
