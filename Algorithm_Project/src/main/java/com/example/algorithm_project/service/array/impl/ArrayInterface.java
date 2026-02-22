package com.example.algorithm_project.service.array.impl;

import com.example.algorithm_project.dto.request.ArrayRequestDto;
import com.example.algorithm_project.dto.response.ArrayResponseDto;

public interface ArrayInterface {

    String getType();
    ArrayResponseDto run(ArrayRequestDto request);
}
