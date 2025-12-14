APP_NAME = indian-pharma-catalog
VERSION = latest
IMAGE = $(APP_NAME):$(VERSION)
CONTAINER = $(APP_NAME)-container

MVN = mvn

run:
	mvn spring-boot:run

package:
	$(MVN) clean package -DskipTests

build: package docker-build

docker-build:
	sudo docker buildx build --platform linux/amd64 -t $(IMAGE) .

docker-run:
	sudo docker run -d \
		--name $(CONTAINER) \
		-p 8080:8080 \
		$(IMAGE)

docker-stop:
	sudo docker stop $(CONTAINER) || true
	sudo docker rm $(CONTAINER) || true

docker-logs:
	sudo docker logs -f $(CONTAINER)

clean:
	$(MVN) clean
	sudo docker rmi $(IMAGE) || true
