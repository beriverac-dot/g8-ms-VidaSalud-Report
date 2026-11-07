#Compilación del proyecto con Maven
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copiar la configuración de Maven y el código fuente
COPY pom.xml .
COPY src ./src

# Compilar y empaquetar la aplicación omitiendo tests
RUN mvn clean package -DskipTests

# Etapa 2: Imagen liviana de ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el ejecutable .jar generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]