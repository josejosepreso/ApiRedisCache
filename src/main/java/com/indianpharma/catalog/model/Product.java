package com.indianpharma.catalog.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {
	private Integer productId;

	private String brandName;

	private String manufacturer;

	private BigDecimal priceInr;

	private boolean isDiscontinued;

	private String dosageForm;

	private BigDecimal packSize;

	private String packUnit;

	private Integer numActiveIngredients;

	private String primaryIngredient;

	private String primaryStrength;

	private String activeIngredients;

	private String therapeuticClass;

	private String packagingRaw;

	private String manufacturerRaw;
}
