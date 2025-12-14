package com.indianpharma.catalog.service;

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
		final ResultSet rs = this.database.executeQuery("select * from catalog.products");

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
}
