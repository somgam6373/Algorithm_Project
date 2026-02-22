package com.example.algorithm_project.controller;

import com.example.algorithm_project.dto.request.SortRequestDto;
import com.example.algorithm_project.dto.response.SortResponseDto;
import com.example.algorithm_project.service.sort.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sort")
public class SortController {

    private final SortService sortService;

    @PostMapping("/{AlgorithmType}")
    public SortResponseDto sort( @PathVariable String AlgorithmType, @RequestBody SortRequestDto request) {
        request.setAlgorithmType(AlgorithmType);
        return sortService.getData(request);
    }
}
