package com.melihcan.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice
public class JwtTokenManager {

    private final Long exTime = 1000L*60*15;
    private final String sifreAnahtari = "GvCT$2*;Yn**U#')IGL2|4YZ9QiX~<g/g~=s'tHC4!H%C>nbn57+*pdE#A/,>NA";
    public Optional<String> createToken(Long id){
        String token="";
        // token olustururken jwt hata verebılır.
        try {
            /**
             * withClaim --> içinde Key-Value şeklinde bilgiler saklanır.
             * Bu bilgiler Payload olarak tutulur ve herkes tarafından görülür. Bu nedenle buraya özel bilgiler koyulmaz.
             * Bu JWT yi olusturan kısının kımlıgını tutmak ıcın kullanılır.
             * withIssuerAt --> Jwt yi olusturma zamanı
             * withExpiresAt --> JWT nin geçerlilik süresi. 30sn de olabilir sonsuz da olabilir.
             * sign --> hazırlanan ıcerıgın ımzalanması yapılır. Bunun ıcın bır sıfre belırlenır ve bununla krıptolama yapılır.
             */
            token = JWT.create().withAudience()
                    .withClaim("id",id)
                    .withClaim("howtopage","AUTHPAGE")
                    .withClaim("yetki","ADMIN")
                    .withIssuer("java5")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+exTime))
                    .sign(Algorithm.HMAC512(sifreAnahtari));
            return Optional.of(token);

        }catch (Exception exception){
            return Optional.empty();
        }
    }

    public Boolean validateToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC512(sifreAnahtari);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("java5").build();
            DecodedJWT decodedJWT =  verifier.verify(token);
            if (decodedJWT==null) return false;
        }catch (Exception exception){
            return false;
        }
        return true;
    }


    public Optional<Long> getIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(sifreAnahtari);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("java5").build();
            DecodedJWT decodedJWT =  verifier.verify(token);
            if (decodedJWT==null) return Optional.empty();
         return Optional.of(decodedJWT.getClaim("id").asLong());
        }catch (Exception exception){
            return Optional.empty();
        }

    }
}
