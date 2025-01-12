# Before running this, have everything compiled -> mvn clean package
# Make sure you are logged in to the GitHub Container Registry -> docker login ghcr.io ...

docker build -t recommendations recommendations/
docker tag recommendations:latest ghcr.io/spehic/recommendations:latest
docker push ghcr.io/spehic/recommendations:latest