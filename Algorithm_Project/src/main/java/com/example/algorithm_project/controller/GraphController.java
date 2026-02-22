package com.example.algorithm_project.controller;

import com.example.algorithm_project.dto.request.GraphRequestDto;
import com.example.algorithm_project.dto.response.GraphResponseDto;
import com.example.algorithm_project.service.graph.GraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/graph")
public class GraphController {
    private final GraphService algorithmService;

    @PostMapping("/{algorithmType}")
    public GraphResponseDto executeData(
            @RequestBody GraphRequestDto request,
            @PathVariable  String algorithmType) {
        request.setAlgorithmType(algorithmType);
        return algorithmService.getData(request);
    }
}
