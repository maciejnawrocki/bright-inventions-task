mvn clean install -DskipTests
docker build --tag demo:0.1 .
docker-compose up