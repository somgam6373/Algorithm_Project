package com.example.algorithm_project.service.array;

import com.example.algorithm_project.dto.request.ArrayRequestDto;
import com.example.algorithm_project.dto.response.ArrayResponseDto;
import com.example.algorithm_project.service.array.impl.ArrayInterface;
import com.example.algorithm_project.service.array.impl.BinarySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArrayService {

    private final List<ArrayInterface> arrayService;

    public ArrayResponseDto getData(ArrayRequestDto request) {
        return arrayService.stream()
                .filter(service->service.getType().equals(request.getAlgorithmType()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("지원하지 않는 알고리즘"))
                .run(request);
    }
}
