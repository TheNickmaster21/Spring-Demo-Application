package in.nickma.DemoRest.service;


import in.nickma.DemoRest.model.HelloHistory;
import in.nickma.DemoRest.repository.HelloHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class HelloService {
    @Autowired
    private HelloHistoryRepository helloHistoryRepository;

    public void makeHistory(String name) {
        this.helloHistoryRepository.save(new HelloHistory(name));
    }

    public ResponseEntity<List<HelloHistory>> getHistory(String name) {
        if (name == null) {
            return new ResponseEntity<>(this.helloHistoryRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(this.helloHistoryRepository.findByName(name), HttpStatus.OK);
        }
    }
}
