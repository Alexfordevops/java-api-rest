package Api.Core.service;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Para testes: admin / 123456
        if (username.equals("admin")) {
            return User.withUsername("admin")
                    .password("{bcrypt}$2a$10$UOpBQut8UOAEKjPV1RSY0O49YIb2HcxLSweUZzGcFfSBJBAH7rRB6") // senha: 123456
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
