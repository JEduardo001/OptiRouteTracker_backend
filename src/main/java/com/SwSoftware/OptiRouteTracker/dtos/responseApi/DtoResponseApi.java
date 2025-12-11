package com.SwSoftware.OptiRouteTracker.dtos.responseApi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoResponseApi<T> {
    private Integer status;
    private String message;
    private T data;
}
