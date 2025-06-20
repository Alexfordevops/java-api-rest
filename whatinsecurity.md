1. Uso de Docker Secrets para segredos (senhas, JWT secret, etc.)
Mantém senhas e chaves fora do código-fonte e do ambiente (variáveis de ambiente comuns são menos seguras).

Os arquivos secretos são gerenciados pelo Docker, que garante permissões restritas e isolamento.

Facilita a rotação de segredos sem modificar código.

2. Leitura explícita dos arquivos secretos no Java
Spring Boot não lê automaticamente o conteúdo de arquivos secretos, então ler manualmente previne que as senhas sejam acidentalmente expostas como texto simples.

Garante que a aplicação sempre use os valores reais de segredos atuais, conforme o arquivo Docker Secret.

3. Senhas de usuários armazenadas no banco com hash BCrypt
BCrypt é um algoritmo de hash resistente a ataques de força bruta e salt.

Nunca armazena senhas em texto simples.

Facilita validação segura no login.

4. JWT com segredo forte e externo à aplicação
O segredo do JWT fica fora do código, no Docker Secret.

Isso previne que o segredo seja exposto no código-fonte ou logs.

Torna mais seguro invalidar/rotacionar a chave JWT.

5. Spring Security configurado para proteger rotas
Rotas públicas (login, registro) são explicitamente liberadas.

Demais rotas exigem autenticação JWT.

Filtro JwtAuthFilter valida o token para cada requisição protegida.

6. Desabilitação de CSRF para APIs REST
API REST normalmente não usa cookies de sessão, por isso CSRF é desabilitado corretamente.
Evita bloqueios indevidos.

Dockerfile da API Spring Boot

docker-compose com PostgreSQL e Docker Secrets para DB e JWT

Configuração da API para ler secrets do Docker (DB e JWT)

Código completo da configuração DataSource customizado

Código JwtUtil que lê JWT secret do arquivo

Exemplo mínimo de uso JWT

Configuração SecurityConfig + AuthController para login e register funcionando com JWT