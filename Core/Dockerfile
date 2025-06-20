# Etapa 1: build da aplicação
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia arquivos de build e resolve dependências com cache
COPY pom.xml .
COPY src ./src

# Compila a aplicação e gera o JAR
RUN mvn clean package -DskipTests

# Etapa 2: imagem final otimizada
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o JAR da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Copia os arquivos de segredos (se forem montados via docker secrets)
# Docker Secrets devem ser montados externamente com `docker-compose`

# Expõe a porta
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
