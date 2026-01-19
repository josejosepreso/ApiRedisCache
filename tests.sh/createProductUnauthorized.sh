#!/bin/sh

curl -X POST \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer token" \
  -d '{
    "brandName": "Paracetamol",
    "manufacturer": "ABC Pharma",
    "priceInr": 25.50,
    "isDiscontinued": false,
    "dosageForm": "Tablet",
    "packSize": 10,
    "packUnit": "Tablets",
    "numActiveIngredients": 1,
    "primaryIngredient": "Paracetamol",
    "primaryStrength": "500mg",
    "activeIngredients": "Paracetamol 500mg",
    "therapeuticClass": "Analgesic",
    "packagingRaw": "Blister pack of 10 tablets",
    "manufacturerRaw": "ABC Pharmaceuticals Ltd."
  }' \
  http://localhost:8080/api/catalog \
	| jq
