package pokerface.pokerface.config.jwt.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void createAccessToken() {
        System.out.println("accessToken : " + jwtService.createAccessToken("test@test", "seojio"));

    }
}