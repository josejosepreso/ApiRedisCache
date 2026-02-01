#!/bin/sh

curl -X POST \
	-H "Content-Type: application/json" \
	-d '{
		"email": "admin@gmail.com",
		"password": "abc456",
		"firstName": "Jose",
		"lastName": "Admin",
		"active": "true"
	}' \
	localhost:8080/api/auth/register \
	| jq
