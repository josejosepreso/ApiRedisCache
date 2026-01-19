#!/bin/sh

curl -X POST \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJmaXJzdE5hbWUiOiJBZG1pbiIsImxhc3ROYW1lIjoiVXNlciIsImFjdGl2ZSI6dHJ1ZSwiYWRtaW4iOnRydWUsImlhdCI6MTc2ODc4ODQ4MiwiZXhwIjoxNzY4Nzg5MzgyfQ.W63EpeRv2yhHLsCg6RVdHz6YE-KlRu3XtdDrFBHEzYg" \
  -d '{
		"brandName": "Amoxicillin",
		"manufacturer": "HealthCorp",
		"priceInr": 120.00,
		"isDiscontinued": false,
		"dosageForm": "Tablet",
		"packSize": 14,
		"packUnit": "Tablets",
		"numActiveIngredients": 1,
		"primaryIngredient": "Amoxicillin",
		"primaryStrength": "500mg",
		"activeIngredients": "Amoxicillin 500mg",
		"therapeuticClass": "Antibiotic",
		"packagingRaw": "Box of 14 tablets",
		"manufacturerRaw": "HealthCorp Pharmaceuticals"
  }' \
  http://localhost:8080/api/catalog \
	| jq
