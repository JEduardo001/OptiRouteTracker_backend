package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.DtoPageableResponse;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoProduct;
import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductNotFound;
import com.SwSoftware.OptiRouteTracker.interfaces.IProductService;
import com.SwSoftware.OptiRouteTracker.repositories.ProductRepository;
import com.SwSoftware.OptiRouteTracker.utils.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final  ProductMapper productMapper;

    @Override
    public DtoPageableResponse<DtoProduct> getAllProducts(Integer page, Integer size){
        Page<ProductEntity> allProducts = productRepository.findAll(PageRequest.of(page,size));
        List<DtoProduct> dtoProducts = allProducts.getContent().stream().map(productMapper::toDto).collect(Collectors.toList());
        return new DtoPageableResponse<>(
                allProducts.getTotalElements(),
                allProducts.getTotalPages(),
                dtoProducts
        );
    }

    @Override
    public DtoProduct getProduct(Long idProduct){
        return productMapper.toDto(getProductById(idProduct));
    }

    @Override
    public ProductEntity saveProduct(ProductEntity product){
        return productRepository.save(product);
    }

    @Override
    public Page<ProductEntity> getProductsByIdInventory(Long id, Pageable page){
        return productRepository.findByInventoryId(id,page);
    }

    @Override
    public ProductEntity getProductById(Long idProduct){
        return productRepository.findById(idProduct).orElseThrow(ExceptionProductNotFound::new);
    }

    @Override
    public void existProduct(Long idProduct){
        if (!productRepository.existsById(idProduct)) {
            throw new ExceptionProductNotFound();
        }
    }

    @Override
    public boolean existsByNameAndIdNot(String name,Long idProduct){
        return productRepository.existsByNameAndIdNot(name,idProduct);
    }

    @Override
    public boolean existsBySerialNumberAndIdNot(String serialNumber,Long idProduct){
        return productRepository.existsBySerialNumberAndIdNot(serialNumber,idProduct);
    }

    @Override
    public void deleteProduct(Long idProduct, Long idInventory){
        productRepository.deleteByIdAndInventoryId(idProduct, idInventory);
    }

}
