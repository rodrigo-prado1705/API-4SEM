package fatec.grupodois.endurance.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import fatec.grupodois.endurance.entity.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
<<<<<<< HEAD
=======
import org.springframework.context.annotation.Bean;
>>>>>>> 2bea5a457ac43bd4613ca51b12f002630fb5629f
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Component;
>>>>>>> 2bea5a457ac43bd4613ca51b12f002630fb5629f

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static fatec.grupodois.endurance.constant.SecurityConstant.*;
import static java.util.Arrays.stream;

<<<<<<< HEAD
=======
@Component
>>>>>>> 2bea5a457ac43bd4613ca51b12f002630fb5629f
public class JWTTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    public String generateJwtToken(UserPrincipal userPrincipal) {

        String[] claims = getClaimsFromUser(userPrincipal);

        return JWT.create().withIssuer(ENDURANCE)
                .withAudience(ENDURANCE_ADMINISTRATION)
                .withIssuedAt(new Date())
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(this.secret.getBytes()));
    }

    public List<GrantedAuthority> getAuthorities(String token) {

        String[] claims = getClaimsFromToken(token);

        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities,
                                            HttpServletRequest request) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);

        usernamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource()
                                                        .buildDetails(request));

        return usernamePasswordAuthToken;
    }

    public boolean isTokenValid(String username, String token) {

        JWTVerifier verifier = getJWTVerifier();

        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
    }

    public String getSubject(String token) {

        JWTVerifier verifier = getJWTVerifier();

        return verifier.verify(token).getSubject();
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {

        Date expiration = verifier.verify(token).getExpiresAt();

        return expiration.before(new Date());
    }

    private String[] getClaimsFromToken(String token) {

        JWTVerifier verifier = getJWTVerifier();

        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJWTVerifier() {

        JWTVerifier verifier;

        try{
            Algorithm algorithm = Algorithm.HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(ENDURANCE).build();
        }catch(JWTVerificationException exception) {
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }

        return verifier;
    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {

        List<String> authorities = new ArrayList<>();

        for(GrantedAuthority grantedAuthority: userPrincipal.getAuthorities()) {

            authorities.add(grantedAuthority.getAuthority());
        }

        return authorities.toArray(new String[0]);
    }
}
