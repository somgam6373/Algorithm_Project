package com.example.algorithm_project.controller;

import com.example.algorithm_project.dto.request.ArrayRequestDto;
import com.example.algorithm_project.dto.response.ArrayResponseDto;
import com.example.algorithm_project.service.array.ArrayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/array")
public class ArrayController {

    private final ArrayService arrayService;

    @PostMapping("/{AlgorithmType}")
    public ArrayResponseDto addAlgorithm(
            @PathVariable String AlgorithmType,
            @RequestBody ArrayRequestDto request) {
        request.setAlgorithmType(AlgorithmType);
        return arrayService.getData(request);
    }
}
