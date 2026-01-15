package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.DtoPageableResponse;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCategory;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoProduct;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoUpdateProduct;
import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.InventoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.inventory.ExceptionInventoryNotFound;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductNameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductNotFound;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductSerialNumberAlreadyInUse;
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
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final  ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public DtoPageableResponse<DtoProduct> getAllProducts(Integer page, Integer size){
        Page<ProductEntity> allProducts = productRepository.findAll(PageRequest.of(page,size));
        List<DtoProduct> dtoProducts = allProducts.getContent().stream().map(productMapper::toDto).collect(Collectors.toList());
        return new DtoPageableResponse<DtoProduct>(
                allProducts.getTotalElements(),
                allProducts.getTotalPages(),
                dtoProducts
        );
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

    public boolean existsByNameAndIdNot(String name,Long idProduct){
        return productRepository.existsByNameAndIdNot(name,idProduct);
    }

    public boolean existsBySerialNumberAndIdNot(String serialNumber,Long idProduct){
        return productRepository.existsBySerialNumberAndIdNot(serialNumber,idProduct);
    }

    @Transactional
    public void deleteProduct(Long idProduct, Long idInventory){
        productRepository.deleteByIdAndInventoryId(idProduct, idInventory);
    }

}
