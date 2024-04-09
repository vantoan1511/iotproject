package com.iotproject.data.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private List<?> contents;
    private int page;
    private int size;
    private int totalPages;
    private int totalElements;
    private int numberOfElements;
    private String sortBy;
    private String sortOrder;
}
