package com.example.algorithm_project.service.sort.impl;

import com.example.algorithm_project.dto.request.SortRequestDto;
import com.example.algorithm_project.dto.response.SortResponseDto;

public interface SortInterface {

    String getType();

    SortResponseDto run(SortRequestDto request);
}
