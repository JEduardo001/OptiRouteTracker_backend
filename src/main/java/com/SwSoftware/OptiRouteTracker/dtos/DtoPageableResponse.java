package com.SwSoftware.OptiRouteTracker.dtos;

import java.util.List;


public record DtoPageableResponse<T>(
        Long totalElements,
        Integer totalPages,
        List<T> data
) {
}
