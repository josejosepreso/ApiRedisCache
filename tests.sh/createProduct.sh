#!/bin/sh

curl -X POST \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3NlQGdtYWlsLmNvbSIsImZpcnN0TmFtZSI6Ikpvc2UiLCJsYXN0TmFtZSI6IkJhdXRpc3RhIiwiYWN0aXZlIjp0cnVlLCJhZG1pbiI6ZmFsc2UsImlhdCI6MTc2OTM3NTkzNCwiZXhwIjoxNzY5Mzc2ODM0fQ.lDll__JdMMjKjDRNLulzM6YdCygT7laGtfTNfu3gerY" \
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
