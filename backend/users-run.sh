# Before running this, have everything compiled -> mvn clean package
# Make sure you are logged in to the GitHub Container Registry -> docker login ghcr.io ...

docker build -t users users/
docker tag users:latest ghcr.io/spehic/users:latest
docker push ghcr.io/spehic/users:latest