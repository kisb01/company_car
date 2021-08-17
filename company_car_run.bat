docker build . -t companycar
docker run --name companycar-container -p 8080:8080 companycar