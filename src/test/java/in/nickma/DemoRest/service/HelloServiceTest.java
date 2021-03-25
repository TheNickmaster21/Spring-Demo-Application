package in.nickma.DemoRest.service;

import in.nickma.DemoRest.model.HelloHistory;
import in.nickma.DemoRest.repository.HelloHistoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HelloServiceTest {
    private final String[] testNames = {"Edward", "Frank"};

    @Autowired
    private HelloService helloService;

    @MockBean
    private HelloHistoryRepository helloHistoryRepository;


    @Test
    void testMakeHistory() {
        helloService.makeHistory(testNames[0]);

        verify(helloHistoryRepository).save(Mockito.argThat(history -> history.getName().equals(testNames[0])));
    }

    void mockHistory() {

        HelloHistory[] fullHistory = {new HelloHistory(testNames[0]), new HelloHistory(testNames[1])};
        when(helloHistoryRepository.findAll()).thenReturn(Arrays.asList(fullHistory));

        when(helloHistoryRepository.findByName(any())).thenReturn(Collections.emptyList());

        HelloHistory[] history0 = {new HelloHistory(testNames[0])};
        when(helloHistoryRepository.findByName(matches(testNames[0]))).thenReturn(Arrays.asList(history0));

        HelloHistory[] history1 = {new HelloHistory(testNames[1])};
        when(helloHistoryRepository.findByName(matches(testNames[1]))).thenReturn(Arrays.asList(history1));

    }

    @Test
    void testGetHistoryWithoutName() {
        ResponseEntity<List<HelloHistory>> result1 = helloService.getHistory(null);

        assert result1.getBody() != null;
        assert result1.getBody().isEmpty();

        this.mockHistory();

        ResponseEntity<List<HelloHistory>> result2 = helloService.getHistory(null);

        assert result2.getBody() != null;
        assert result2.getBody().size() == 2;
    }

    @Test
    void testGetHistoryWithName() {
        this.mockHistory();

        ResponseEntity<List<HelloHistory>> result1 = helloService.getHistory("FakeName");

        assert result1.getBody() != null;
        assert result1.getBody().isEmpty();

        ResponseEntity<List<HelloHistory>> result2 = helloService.getHistory(testNames[0]);

        assert result2.getBody() != null;
        assert result2.getBody().size() == 1;
        assert result2.getBody().get(0).getName().equals(testNames[0]);
    }
}
