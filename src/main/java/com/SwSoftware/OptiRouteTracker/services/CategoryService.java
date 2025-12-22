package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.category.ExceptionCategoryNotFound;
import com.SwSoftware.OptiRouteTracker.repositories.CategoryRepository;
import com.SwSoftware.OptiRouteTracker.repositories.InventoryRepository;
import com.SwSoftware.OptiRouteTracker.utils.mapper.InventoryMapper;
import com.SwSoftware.OptiRouteTracker.utils.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;

    }

    public List<CategoryEntity> getCategoriesByIdsOrThrow(List<Long> ids) {
        if(ids != null){
            List<CategoryEntity> result = categoryRepository.findByIdIn(ids);
            if (result.size() != ids.size()) {
                throw new ExceptionCategoryNotFound();
            }
            return result;
        }

        return new ArrayList<>();
    }




}
