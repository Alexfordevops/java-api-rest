package Api.Core.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Caminho do arquivo com a chave secreta
    @Value("${jwt.secret.file}")
    private String jwtSecretFilePath;

    private Key SECRET_KEY;

    // Token expira em 24 horas
    private static final long EXPIRATION_TIME_MS = 1000 * 60 * 60 * 24;

    /**
     * Lê o conteúdo da chave secreta do arquivo configurado.
     * Executado automaticamente após a injeção de dependência do Spring.
     */
    @PostConstruct
    public void loadSecretKey() {
        try {
            String secret = Files.readString(Path.of(jwtSecretFilePath)).trim();
            if (secret.length() < 32) {
                throw new IllegalArgumentException("A chave JWT precisa ter pelo menos 32 caracteres para HS256.");
            }
            this.SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo com a chave secreta JWT", e);
        }
    }

    /**
     * Gera um token JWT com base no nome do usuário.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida se o token é assinado corretamente e não expirou.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Extrai o nome do usuário (subject) do token JWT.
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
