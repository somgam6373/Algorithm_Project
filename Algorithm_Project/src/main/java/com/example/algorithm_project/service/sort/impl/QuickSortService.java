package com.example.algorithm_project.service.sort.impl;

import com.example.algorithm_project.dto.request.SortRequestDto;
import com.example.algorithm_project.dto.response.SortResponseDto;
import org.springframework.stereotype.Service;

@Service

public class QuickSortService implements SortInterface{

    @Override
    public String getType(){
        return "QuickSort";
    }

    @Override
    public SortResponseDto run(SortRequestDto request){
        return null;
    }
}
