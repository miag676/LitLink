# Before running this, have everything compiled -> mvn clean package
# Make sure you are logged in to the GitHub Container Registry -> docker login ghcr.io ...

docker build -t books books/
docker tag books:latest ghcr.io/spehic/books:latest
docker push ghcr.io/spehic/books:latest