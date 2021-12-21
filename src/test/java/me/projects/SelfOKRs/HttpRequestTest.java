package me.projects.SelfOKRs;

import me.projects.SelfOKRs.entities.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
public class HttpRequestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void userShouldNotBeNull() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:8080/api/users/1",
                User.class)).isNotNull();
    }
}
