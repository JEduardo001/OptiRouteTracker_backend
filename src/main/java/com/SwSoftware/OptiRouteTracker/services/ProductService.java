package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoProduct;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoUpdateProduct;
import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductNotFound;
import com.SwSoftware.OptiRouteTracker.repositories.ProductRepository;
import com.SwSoftware.OptiRouteTracker.utils.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final  ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,CategoryService categoryService,
                          ProductMapper productMapper,UserService userService){
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    public List<DtoProduct> getAllProducts(Integer page, Integer size){
        Pageable s = PageRequest.of(page,size);
        return productRepository.findAll(s).map(productMapper::toDto).getContent();
    }

    public DtoProduct getProduct(Long idProduct){
        return productMapper.toDto(getProductById(idProduct));
    }

    public ProductEntity saveProduct(ProductEntity product){
        return productRepository.save(product);
    }

    public Page<ProductEntity> getProductsByIdInventory(Long id, Pageable page){
        return productRepository.findByInventoryId(id,page);
    }

    public ProductEntity getProductById(Long idProduct){
        return productRepository.findById(idProduct).orElseThrow(ExceptionProductNotFound::new);
    }

    public void existProduct(Long idProduct){
        if (!productRepository.existsById(idProduct)) {
            throw new ExceptionProductNotFound();
        }
    }

    @Transactional
    public void deleteProduct(Long idProduct, Long idInventory){
        productRepository.deleteByIdAndInventoryId(idProduct, idInventory);
    }

    public DtoProduct updateProduct(DtoUpdateProduct product){
        ProductEntity productEntity = getProductById(product.getId());

        List<CategoryEntity> categories = new ArrayList<>();

        if(product.getNewIdCategories() != null){
            categories = categoryService.getCategoriesByIdsOrThrow(product.getNewIdCategories());
        }

        productEntity.setBatch(product.getBatch());
        productEntity.setActive(product.isActive());
        productEntity.setDescription(product.getDescription());
        productEntity.setName(product.getName());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setSerialNumber(product.getSerialNumber());
        productEntity.setCategory(categories);

        productRepository.save(productEntity);

        return productMapper.toDto(productEntity);
    }

}
