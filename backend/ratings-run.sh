# Before running this, have everything compiled -> mvn clean package
# Make sure you are logged in to the GitHub Container Registry -> docker login ghcr.io ...

docker build -t ratings ratings/
docker tag ratings:latest ghcr.io/spehic/ratings:latest
docker push ghcr.io/spehic/ratings:latest