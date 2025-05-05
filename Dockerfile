# Usa a imagem oficial do OpenJDK 21
FROM eclipse-temurin:21-jdk

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto (incluindo pom.xml/build.gradle)
COPY . .

# Compila o projeto (Maven ou Gradle)
# Para Maven:
RUN ./mvnw clean package -DskipTests

# Para Gradle:
# RUN ./gradlew build -x test

# Comando para executar o JAR gerado
CMD ["java", "-jar", "target/furiabot-0.0.1-SNAPSHOT.jar"]  

