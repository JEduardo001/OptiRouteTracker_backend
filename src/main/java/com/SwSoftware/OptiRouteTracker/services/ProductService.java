package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductNotFound;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserUsernameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Page<ProductEntity> getProductsByIdInventory(Long id, Pageable s){
        return productRepository.findByInventoryId(id,s);
    }

    public ProductEntity getProductById(Long idProduct){
        return productRepository.findById(idProduct).orElseThrow(ExceptionProductNotFound::new);
    }

    public void existProduct(Long idProduct){
        if (!productRepository.existsById(idProduct)) {
            throw new ExceptionProductNotFound();
        }
    }

    public void deleteProduct(Long idProduct, Long idInventory){
        productRepository.deleteByIdAndInventoryId(idProduct, idInventory);
    }


}
