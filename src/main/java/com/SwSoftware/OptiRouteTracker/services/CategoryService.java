package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.DtoPageableResponse;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCategory;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCreateCategory;
import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.category.ExceptionCategoryNameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.category.ExceptionCategoryNotFound;

import com.SwSoftware.OptiRouteTracker.interfaces.ICategoryService;
import com.SwSoftware.OptiRouteTracker.repositories.CategoryRepository;

import com.SwSoftware.OptiRouteTracker.utils.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public DtoCategory createCategory(DtoCreateCategory request){
        if(categoryRepository.existsByName(request.getName())){
            throw new ExceptionCategoryNameAlreadyInUse();
        }
        return categoryMapper.toDto(categoryRepository.save(CategoryEntity.builder()
                .active(request.isActive())
                .name(request.getName())
                .quantityProducts(0)
                .build()
        ));
    }

    @Override
    public DtoPageableResponse<DtoCategory> getAllCategories(Integer page, Integer size){
        Page<CategoryEntity> categories = categoryRepository.findAll(PageRequest.of(page,size));
        List<DtoCategory> dtoCategories = categories.getContent().stream().map(categoryMapper::toDto).collect(Collectors.toList());
        return new DtoPageableResponse<DtoCategory>(
                categories.getTotalElements(),
                categories.getTotalPages(),
                dtoCategories
        );
    }
    @Override
    public List<CategoryEntity> getCategoriesByIdsOrThrow(List<DtoCategory> categories) {
        if(categories != null){
            List<Long> idsCategories = categories.stream().map(DtoCategory::getId).toList();
            List<CategoryEntity> result = categoryRepository.findByIdIn(idsCategories);
            if (result.size() != categories.size()) {
                throw new ExceptionCategoryNotFound();
            }
            return result;
        }

        return new ArrayList<>();
    }

    @Override
    public DtoCategory getCategory(Long idCategory){
        return categoryMapper.toDto(getCategoryById(idCategory));
    }

    private CategoryEntity getCategoryById(Long idCategory){
        return categoryRepository.findById(idCategory).orElseThrow(ExceptionCategoryNotFound::new);
    }


    @Override
    public DtoCategory updateCategory(DtoCategory request){
        CategoryEntity categoryEntity = getCategoryById(request.getId());

        if(categoryRepository.existsByNameAndIdNot(request.getName(), request.getId())){
            throw new ExceptionCategoryNameAlreadyInUse();
        }

        categoryEntity.setActive(request.isActive());
        categoryEntity.setName(request.getName());
        categoryEntity.setQuantityProducts(request.getQuantityProducts());

        categoryRepository.save(categoryEntity);
        return categoryMapper.toDto(categoryEntity);
    }

}