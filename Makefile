APP_NAME = indian-pharma-catalog
VERSION = latest
IMAGE = $(APP_NAME):$(VERSION)
CONTAINER = $(APP_NAME)-container

MVN = mvn

package:
	$(MVN) clean package -DskipTests

build: package
	make docker-build

docker-build:
	docker buildx build --platform linux/amd64 -t $(IMAGE) .

docker-run:
	docker run -d \
		--name $(CONTAINER) \
		-p 8080:8080 \
		$(IMAGE)

docker-stop:
	docker stop $(CONTAINER) || true
	docker rm $(CONTAINER) || true

docker-logs:
	docker logs -f $(CONTAINER)

clean:
	$(MVN) clean
	docker rmi $(IMAGE) || true
