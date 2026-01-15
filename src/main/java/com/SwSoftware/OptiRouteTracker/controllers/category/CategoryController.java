package com.SwSoftware.OptiRouteTracker.controllers.category;

import com.SwSoftware.OptiRouteTracker.constants.ApiPaths;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCategory;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCreateCategory;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.baseApi + "/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @PostMapping()
    public ResponseEntity<DtoResponseApi> createCategory(@Valid @RequestBody DtoCreateCategory request){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("Category created")
                .data(categoryService.createCategory(request))
                .build()
        );
    }

    @GetMapping()
    public ResponseEntity<DtoResponseApi> getAllCategories(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Categories obtained")
                .data(categoryService.getAllCategories(page,size))
                .build()
        );
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<DtoResponseApi> getCategory(@PathVariable Long idCategory){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Category obtained")
                .data(categoryService.getCategory(idCategory))
                .build()
        );
    }

    @PutMapping
    public ResponseEntity<DtoResponseApi> updateCategory(@Valid @RequestBody DtoCategory request){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Updated category")
                .data(categoryService.updateCategory(request))
                .build()
        );
    }
}
