# Estágio 1: Build
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copia apenas os arquivos necessários para baixar as dependências (otimiza cache)
COPY reservas-system/.mvn/ .mvn/
COPY reservas-system/mvnw reservas-system/pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copia o código fonte e compila o projeto
COPY reservas-system/src ./src
RUN ./mvnw clean package -DskipTests

# Estágio 2: Runtime
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# O caminho do JAR muda porque ele foi gerado dentro da subpasta target
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
