package com.example.algorithm_project.service.graph.impl;

import com.example.algorithm_project.dto.request.GraphRequestDto;
import com.example.algorithm_project.dto.response.GraphResponseDto;

public interface GraphInterface {

    String getType();
    GraphResponseDto run(GraphRequestDto graphRequestDto);
}
