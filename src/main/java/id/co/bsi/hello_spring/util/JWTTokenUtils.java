package id.co.bsi.hello_spring.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTokenUtils {

    @Value("${jwt.key}")
    private String jwtkey;

    public String generateToken(String username){
        Map<String, Object> additionalPayload = new HashMap<>();
        additionalPayload.put("data 1", "data 1");
        additionalPayload.put("data 2", "data 2");
        additionalPayload.put("data 3", "data 3");

        return Jwts.builder()
                .addClaims(additionalPayload)
                .setSubject(username)
                .setAudience("bsi")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, jwtkey); //Blom selesai
    }


}
