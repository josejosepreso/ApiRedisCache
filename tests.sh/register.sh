#!/bin/sh

curl -X POST \
	-H "Content-Type: application/json" \
	-d '{
		"email": "jose@gmail.com",
		"password": "abc123",
		"firstName": "Jose",
		"lastName": "Bautista",
		"active": "true"
	}' \
	localhost:8080/api/auth/register \
	| jq
