package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.dtosCreate.DtoCreateInventory;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.DtoProduct;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoInventoryWithProducts;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoInventoryWithoutProducts;
import com.SwSoftware.OptiRouteTracker.entities.InventoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.inventory.ExceptionInventoryNameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.inventory.ExceptionInventoryNotFound;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductNotFound;
import com.SwSoftware.OptiRouteTracker.repositories.InventoryRepository;
import com.SwSoftware.OptiRouteTracker.utils.mapper.InventoryMapper;
import com.SwSoftware.OptiRouteTracker.utils.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductService productService;
    private final InventoryMapper inventoryMapper;
    private final ProductMapper productMapper;

    public InventoryService(InventoryRepository inventoryRepository,ProductService productService,
                            InventoryMapper inventoryMapper, ProductMapper productMapper){
        this.inventoryRepository = inventoryRepository;
        this.productService = productService;
        this.inventoryMapper = inventoryMapper;
        this.productMapper = productMapper;
    }

    public DtoInventoryWithoutProducts createInventory(DtoCreateInventory data){
        if(inventoryRepository.existsByName(data.getName())){
           throw new ExceptionInventoryNameAlreadyInUse();
        }

        InventoryEntity inventory = inventoryRepository.save(InventoryEntity.builder()
                .name(data.getName())
                .description(data.getDescription())
                .location(data.getLocation())
                .createDate(LocalDateTime.now())
                .quantity(0)
                .products(new LinkedHashSet<>())
                .build()
        );
        System.out.println(inventory.getName());
        return inventoryMapper.toDtoByEntity(inventory);
    }

    public DtoInventoryWithProducts getAllDataInventory(Long idInventory){

        DtoInventoryWithoutProducts inventory = inventoryRepository.findInventoryById(idInventory)
                .orElseThrow(ExceptionInventoryNotFound::new);

        Page<ProductEntity> products = getProductsInventory(idInventory,0,25);

        DtoInventoryWithProducts inventoryWithProducts = inventoryMapper.dtoToDtoWithProducts(inventory);
        List<DtoProduct> productsDto = products.getContent().stream().map(productMapper::toDto).toList();
        inventoryWithProducts.setProducts(productsDto);

        return inventoryWithProducts;
    }

    public Page<ProductEntity> getProductsInventory(Long idInventory,Integer page, Integer size){
        return productService.getProductsByIdInventory(idInventory,PageRequest.of(page,size));
    }

    public List<DtoInventoryWithoutProducts> getAllInventories(Integer page, Integer size){
        Page<InventoryEntity> inventory = inventoryRepository.findAll(PageRequest.of(page, size));
        return inventory.getContent().stream().map(inventoryMapper::toDtoWithoutProducts).collect(Collectors.toList());
    }

    @Transactional
    public void removeInventoryProduct(Long idProduct, Long idInventory){
        if (!inventoryRepository.existsById(idInventory)) {
            throw new ExceptionInventoryNotFound();
        }
        productService.existProduct(idProduct);
        productService.deleteProduct(idProduct,idInventory);
    }

    public DtoInventoryWithoutProducts updateInventory(DtoInventoryWithoutProducts data){
        InventoryEntity inventory = inventoryRepository.findById(data.getId()).orElseThrow(ExceptionInventoryNotFound::new);
        inventory.setName(data.getName());
        inventory.setDescription(data.getDescription());
        inventory.setLocation(data.getLocation());
        inventory.setQuantity(data.getQuantity());

        InventoryEntity inventoryEntity = inventoryRepository.save(inventory);

        return inventoryMapper.toDtoWithoutProducts(inventoryEntity);

    }
    @Transactional
    public void removeInventory(Long idInventory){
        inventoryRepository.deleteByInventoryId(idInventory);
        inventoryRepository.deleteById(idInventory);
    }
}


