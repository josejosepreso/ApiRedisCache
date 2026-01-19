#!/bin/sh

curl -X POST \
	-H "Content-Type: application/json" \
	-d '{
		"email": "admin@gmail.com",
		"password": "def456"
	}' \
	localhost:8080/api/auth/login \
	| jq
