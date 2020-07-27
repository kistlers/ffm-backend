cd ../fronis-proxy
./mvnw spring-boot:build-image

cd ../fronis-app
./mvnw spring-boot:build-image

cd ../fronis-admin
./mvnw spring-boot:build-image
