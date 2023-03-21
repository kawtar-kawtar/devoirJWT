package com.example.tp_jwt;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class App2JWT {
    private static String createJwtToken(String secret){
        SecretKey secretKey=new SecretKeySpec(secret.getBytes(),0,secret.length(), SignatureAlgorithm.HS256.getJcaName());

        Map<String,String> claims=new HashMap<>();
        claims.put("name","kawtar");
        claims.put("email","k.benyahya@gmail.com");
        JwtBuilder jwtBuilder= Jwts.builder().setIssuedAt(new Date()).setId("123321")
                .setExpiration(new Date(System.currentTimeMillis()+100000))
                .setClaims(claims)
                .setSubject("jwt authentification")
                .signWith(secretKey);
        String jwtTocken=jwtBuilder.compact();
        return jwtTocken;

    }
    private static void parseValideToken(String secret,String token){
        SecretKey secretKey=new SecretKeySpec(secret.getBytes(),0,secret.length(), SignatureAlgorithm.HS256.getJcaName());

        //-------------------Validate and parse the Jwt Tocken : ----------------------
        Jws<Claims> claimsJws= Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(createJwtToken(secret));
        System.out.println("-------------------Header --------------------");
        System.out.println(claimsJws.getHeader());

        System.out.println("-------------------Body --------------------");
        System.out.println(claimsJws.getBody());

        System.out.println("-------------------Signature --------------------");
        System.out.println(claimsJws.getSignature());
    }
    public static void main(String[] args) {
        String secret="12365478995647821235214785632514";
        String jwtToken=createJwtToken(secret);
        parseValideToken(secret,jwtToken);

    }
}
