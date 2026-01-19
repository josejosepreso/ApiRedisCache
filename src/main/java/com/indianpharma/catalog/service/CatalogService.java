package com.indianpharma.catalog.service;

import com.indianpharma.catalog.dto.ProductCreateRequestDto;
import com.indianpharma.catalog.model.Product;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indianpharma.catalog.util.Database;

@Service
public class CatalogService {

	@Autowired
	private Database database;

	public List<Product> getAll() throws SQLException {
		final ResultSet rs = this.database.executeQuery("select * from sch_catalog.products");

		final List<Product> products = new ArrayList<>();

		while (rs.next()) {
			Product product = new Product();

			product.setProductId(rs.getInt("product_id"));
			product.setBrandName(rs.getString("brand_name"));
			product.setManufacturer(rs.getString("manufacturer"));
			product.setPriceInr(rs.getBigDecimal("price_inr"));
			product.setDiscontinued(rs.getBoolean("is_discontinued"));
			product.setDosageForm(rs.getString("dosage_form"));
			product.setPackSize(rs.getBigDecimal("pack_size"));
			product.setPackUnit(rs.getString("pack_unit"));
			product.setNumActiveIngredients(rs.getInt("num_active_ingredients"));
			product.setPrimaryIngredient(rs.getString("primary_ingredient"));
			product.setPrimaryStrength(rs.getString("primary_strength"));
			product.setActiveIngredients(rs.getString("active_ingredients"));
			product.setTherapeuticClass(rs.getString("therapeutic_class"));
			product.setPackagingRaw(rs.getString("packaging_raw"));
			product.setManufacturerRaw(rs.getString("manufacturer_raw"));

			products.add(product);
		}

		return products;
	}

	public Product create(ProductCreateRequestDto dto) throws SQLException, Exception {
		final Product product = Product.builder()
				.brandName(dto.getBrandName())
				.manufacturer(dto.getManufacturer())
				.priceInr(dto.getPriceInr())
				.isDiscontinued(dto.isDiscontinued())
				.dosageForm(dto.getDosageForm())
				.packSize(dto.getPackSize())
				.packUnit(dto.getPackUnit())
				.numActiveIngredients(dto.getNumActiveIngredients())
				.primaryIngredient(dto.getPrimaryIngredient())
				.primaryStrength(dto.getPrimaryStrength())
				.activeIngredients(dto.getActiveIngredients())
				.therapeuticClass(dto.getTherapeuticClass())
				.packagingRaw(dto.getPackagingRaw())
				.manufacturerRaw(dto.getManufacturerRaw())
				.build();

		final String sql = String.format("""
				INSERT INTO sch_catalog.products (
				     		brand_name,
				     		manufacturer,
				     		price_inr,
				     		is_discontinued,
				     		dosage_form,
				     		pack_size,
				     		pack_unit,
				     		num_active_ingredients,
				     		primary_ingredient,
				     		primary_strength,
				     		active_ingredients,
				     		therapeutic_class,
				     		packaging_raw,
				     		manufacturer_raw
				) VALUES (
					'%s', '%s', %s, %s, '%s', %s, '%s', %s, '%s', '%s', '%s', '%s', '%s', '%s'
				);
				""",
				product.getBrandName(),
				product.getManufacturer(),
				product.getPriceInr(),
				product.isDiscontinued() ? 1 : 0,
				product.getDosageForm(),
				product.getPackSize(),
				product.getPackUnit(),
				product.getNumActiveIngredients(),
				product.getPrimaryIngredient(),
				product.getPrimaryStrength(),
				product.getActiveIngredients(),
				product.getTherapeuticClass(),
				product.getPackagingRaw(),
				product.getManufacturerRaw());

		if (this.database.executeInsert(sql) < 1) {
			throw new Exception("Failed to create product.");
		}

		final ResultSet rs = this.database.executeQuery("select max(product_id) as id from sch_catalog.products");

		rs.next();
		assert rs.isLast();

		product.setProductId(rs.getInt("id"));

		return product;
	}
}
