package com.imadp.service.commerce.product;

import com.imadp.service.PersistableService;


/**
 * IProductService
 * 
 * Provides common retrieval operations for Product objects.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public interface ProductService extends PersistableService<Product> {
	
	/**
	 * Finds a Product by code, or null if none was found.
	 * 
	 * @param code
	 * @return Product
	 */
	public Product findByCode(String code);
	
}