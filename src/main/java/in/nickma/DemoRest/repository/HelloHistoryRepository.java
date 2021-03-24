package in.nickma.DemoRest.repository;

import in.nickma.DemoRest.model.HelloHistory;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface HelloHistoryRepository extends Repository<HelloHistory, Long> {
    void save(HelloHistory helloHistory);

    List<HelloHistory> findByName(String Name);
    List<HelloHistory> findAll();

}
