package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCategory;
import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.category.ExceptionCategoryNotFound;

import com.SwSoftware.OptiRouteTracker.repositories.CategoryRepository;

import com.SwSoftware.OptiRouteTracker.utils.mapper.CategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository,CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;

    }

    public List<DtoCategory> getAllCategories(Integer page, Integer size){
        Page<CategoryEntity> categories = categoryRepository.findAll(PageRequest.of(page,size));
        return categories.getContent().stream().map(categoryMapper::toDto).collect(Collectors.toList());
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

    public DtoCategory getCategory(Long idCategory){
        return categoryMapper.toDto(getCategoryById(idCategory));
    }

    public CategoryEntity getCategoryById(Long idCategory){
        return categoryRepository.findById(idCategory).orElseThrow(ExceptionCategoryNotFound::new);
    }



    public DtoCategory updateCategory(DtoCategory request){
       CategoryEntity categoryEntity = getCategoryById(request.getId());

       categoryEntity.setActive(request.isActive());
       categoryEntity.setName(request.getName());
       categoryEntity.setQuantityProducts(request.getQuantityProducts());

       categoryRepository.save(categoryEntity);
       return categoryMapper.toDto(categoryEntity);
    }

    public void disableCategory(Long idCategory){
        CategoryEntity category = getCategoryById(idCategory);
        category.setActive(false);
        categoryRepository.save(category);
    }
}