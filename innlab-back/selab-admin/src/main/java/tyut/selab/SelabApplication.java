package tyut.selab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @ClassName: SelabApplication
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 13:05
 * @Version: 1.0
 **/

@EnableAsync
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SelabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelabApplication.class, args);
    }

}
