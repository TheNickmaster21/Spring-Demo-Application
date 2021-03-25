package in.nickma.DemoRest;

import in.nickma.DemoRest.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = HelloService.class)
public class DemoRestConfig {

    @Bean
    public HelloService getHelloService() {
        return new HelloService();
    }
}
