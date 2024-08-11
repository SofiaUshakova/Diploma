package com.example.diploma;

import com.example.diploma.Service.TestcontainersConfiguration;
import com.example.diploma.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DiplomaApplicationTests {

    @Test
    void contextLoads() {
    }

}
