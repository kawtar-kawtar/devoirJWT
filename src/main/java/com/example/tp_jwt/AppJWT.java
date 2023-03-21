package com.example.tp_jwt;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class AppJWT {
    public static void main(String[] args) {
        //------------------Create JWT tocken : -------------------------
        //1-String secret="12365478995647821235214785632514";//32 caractéres
        String secret="MTIzNjU0Nzg5OTU2NDc4MjEyMzUyMTQ3ODU2MzI1MTQ=";
        SecretKey secretKey=new SecretKeySpec(secret.getBytes(),0,secret.length(), SignatureAlgorithm.HS256.getJcaName());
       //1- System.out.println(Base64.getUrlEncoder().encodeToString(secretKey.getEncoded()));

        Map <String,String> claims=new HashMap<>();
        claims.put("name","kawtar");
        claims.put("email","k.benyahya@gmail.com");
        JwtBuilder jwtBuilder= Jwts.builder().setIssuedAt(new Date()).setId("123321")
                .setExpiration(new Date(System.currentTimeMillis()+100000))
                .setClaims(claims)
                .setSubject("jwt authentification")
                .signWith(secretKey);
        String jwtTocken=jwtBuilder.compact();
        System.out.println(jwtTocken);


        //-------------------Validate and parse the Jwt Tocken : ----------------------
        Jws<Claims> claimsJws= Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwtTocken);
        System.out.println("-------------------Header --------------------");
        System.out.println(claimsJws.getHeader());

        System.out.println("-------------------Body --------------------");
        System.out.println(claimsJws.getBody());

        System.out.println("-------------------Signature --------------------");
        System.out.println(claimsJws.getSignature());
    }
}
