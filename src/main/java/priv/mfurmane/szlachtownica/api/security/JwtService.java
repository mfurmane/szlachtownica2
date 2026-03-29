//package priv.mfurmane.szlachtownica.api.security;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.List;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//
//    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Możesz też użyć klucza z pliku .env lub application.yml
//
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .claim("roles", userDetails.getAuthorities().stream()
//                        .map(GrantedAuthority::getAuthority) // np. ROLE_ADMIN
//                        .toList())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
//                .signWith(key)
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public List<String> extractRoles(String token) {
//        Claims claims = extractAllClaims(token);
//        return claims.get("roles", List.class);
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractClaim(token, Claims::getExpiration).before(new Date());
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        return claimsResolver.apply(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody());
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//
//}