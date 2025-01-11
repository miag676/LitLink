# Before running this, have everything compiled -> mvn clean package
# Make sure you are logged in to the GitHub Container Registry -> docker login ghcr.io ...

docker build -t lists lists/
docker tag lists:latest ghcr.io/spehic/lists:latest
docker push ghcr.io/spehic/lists:latest