package com.imadp.service.commerce.product;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.PersistableServiceImpl;

/**
 * ProductServiceImpl
 * 
 * The standard implementation of the ProductService.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class ProductServiceImpl extends PersistableServiceImpl<Product> implements ProductService {

	@Override
	public Product findByCode(String code) {
		FindCriteria<Product> findCriteria = findCriteriaBuilder(CriteriaParams.<Product>of(Results.ONE))
			.whereEqualTo(Product.CODE, code).build();
	
		return findFirstBy(findCriteria);
	}
	
}