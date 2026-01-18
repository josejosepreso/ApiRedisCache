#!/bin/sh

curl -X POST \
	-H "Content-Type: application/json" \
	-d '{
		"email": "jose@gmail.com",
		"password": "abc123"
	}' \
	localhost:8080/api/auth/login \
	| jq
