package com.SwSoftware.OptiRouteTracker.interfaces;

import com.SwSoftware.OptiRouteTracker.dtos.DtoPageableResponse;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCategory;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCreateCategory;
import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICategoryService {
    @Transactional
    DtoCategory createCategory(DtoCreateCategory request);
    @Transactional
    DtoPageableResponse<DtoCategory> getAllCategories(Integer page, Integer size);
    List<CategoryEntity> getCategoriesByIdsOrThrow(List<DtoCategory> categories);
    @Transactional
    DtoCategory getCategory(Long idCategory);
    @Transactional
    DtoCategory updateCategory(DtoCategory request);

}
