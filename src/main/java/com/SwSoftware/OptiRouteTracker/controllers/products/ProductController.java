package com.SwSoftware.OptiRouteTracker.controllers.products;

import com.SwSoftware.OptiRouteTracker.constants.ApiPaths;
import com.SwSoftware.OptiRouteTracker.dtos.dtosCreate.DtoCreateProduct;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoUpdateProduct;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.services.ProductFacade;
import com.SwSoftware.OptiRouteTracker.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.baseApi + "/product")
public class ProductController {

    private final ProductFacade productFacade;
    private final ProductService productService;

    public ProductController(ProductFacade productFacade,ProductService productService){
        this.productFacade = productFacade;
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<DtoResponseApi<Object>> getProduct(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Products obtained")
                .data(productService.getAllProducts(page,size))
                .build()
        );
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<DtoResponseApi<Object>> getProduct(@PathVariable Long idProduct){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Product obtained")
                .data(productService.getProduct(idProduct))
                .build()
        );
    }

    @PostMapping()
    public ResponseEntity<DtoResponseApi<Object>> createProduct(@Valid @RequestBody DtoCreateProduct data){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("Product created")
                .data(productFacade.createProduct(data))
                .build()
        );
    }

    @PutMapping()
    public ResponseEntity<DtoResponseApi<Object>> updateProduct(@Valid @RequestBody DtoUpdateProduct data){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Product updated")
                .data(productService.updateProduct(data))
                .build()
        );
    }

    @DeleteMapping("/{idProduct}/{idInventory}")
    public ResponseEntity<DtoResponseApi<Object>> deleteProduct(@PathVariable Long idProduct,@PathVariable Long idInventory){
        productService.deleteProduct(idProduct,idInventory);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Product elimined")
                .build()
        );
    }

}
