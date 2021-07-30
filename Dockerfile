FROM openjdk:11
COPY ./src/main/java/com/codecool/company_car/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","CompanyCarApplication"]