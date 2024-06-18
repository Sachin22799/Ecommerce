package com.ecommerce.seller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.seller.entity.CategoryEntity;
import com.ecommerce.seller.entity.ProductDetailsEntity;
import com.ecommerce.seller.entity.RecipesDetailsEntity;
import com.ecommerce.seller.repository.CategoryRepository;
import com.ecommerce.seller.repository.ProductDetailsRepository;
import com.ecommerce.seller.repository.RecipesRepository;
import com.ecommerce.seller.service.ISellerService;

@Service
public class SellerServiceImpl implements ISellerService {

	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	RecipesRepository recipesRepo;

	@Autowired
	ProductDetailsRepository productRepo;

	@Override
	public List<CategoryEntity> getAllCategories() {
		return categoryRepo.findByIsApproved('Y');
	}

	@Override
	public ProductDetailsEntity addNewProduct(ProductDetailsEntity productDetails) {

		return productRepo.save(productDetails);
	}

//	@Override
//	public CategoryEntity addNewCategory(CategoryEntity categoryDetails) {
//		return categoryRepo.save(categoryDetails);
//	}

	@Override
	public Optional<CategoryEntity> findByCategoryId(String categoryId) {

		return categoryRepo.findById(Integer.parseInt(categoryId));
	}

	@Override
	public List<ProductDetailsEntity> findProductBySeller(Integer sellerId) {
		return productRepo.findBySellerId(sellerId);

	}

	@Override
	public ProductDetailsEntity editProduct(ProductDetailsEntity productDetails) {
		return productRepo.findAllById(productDetails);
	}

	@Override
	public Optional<ProductDetailsEntity> findByProductId(Integer productId) {
		return productRepo.findById(productId);
	}

	@Override
	public ProductDetailsEntity updateProduct(ProductDetailsEntity productDetails) {

		ProductDetailsEntity product = productRepo.getById(productDetails.getId());
		if (product != null) {

			product.setAvailableQty(productDetails.getAvailableQty() != null ? productDetails.getAvailableQty()
					: product.getAvailableQty());
			product.setProductDesc(productDetails.getProductDesc() != null ? productDetails.getProductDesc()
					: product.getProductDesc());
			product.setProductImage(productDetails.getProductImage() != null ? productDetails.getProductImage()
					: product.getProductImage());
			product.setProductName(productDetails.getProductName() != null ? productDetails.getProductName()
					: product.getProductName());
			product.setPurchasingPrice(productDetails.getPurchasingPrice() != null ? productDetails.getPurchasingPrice()
					: product.getPurchasingPrice());
			product.setSellingPrice(productDetails.getSellingPrice() != null ? productDetails.getSellingPrice()
					: product.getSellingPrice());
			product.setAvailableQty(productDetails.getAvailableQty() != null ? productDetails.getAvailableQty()
					: product.getAvailableQty());
			product.setMinQty(productDetails.getMinQty() != null ? productDetails.getMinQty() : product.getMinQty());
			product.setProductInfo(productDetails.getProductInfo() != null ? productDetails.getProductInfo()
					: product.getProductInfo());

		}
		return productRepo.saveAndFlush(product);
	}

	@Override
	public Integer deleteProduct(Integer productId) {
		return productRepo.removeById(productId);
	}

	@Override
	public boolean isDuplicateProduct(String productName, Integer sellerId ) {
		List<ProductDetailsEntity> productList = productRepo.findBySellerId(sellerId);
		Optional<ProductDetailsEntity> product = productList.stream().filter(x->x.getProductName().equalsIgnoreCase(productName)).findAny();
		return product.isPresent();
	}

	@Override
	public RecipesDetailsEntity addNewRecipe(RecipesDetailsEntity recipeDetails) {
		return recipesRepo.save(recipeDetails);

	}

	@Override
	public List<RecipesDetailsEntity> findRecipesBySeller(Integer sellerId) {
		return recipesRepo.findBySellerId(sellerId);

	}



}
