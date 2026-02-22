package com.example.algorithm_project.service.array;

import com.example.algorithm_project.dto.request.ArrayRequestDto;
import com.example.algorithm_project.dto.response.ArrayResponseDto;
import com.example.algorithm_project.service.array.impl.BinarySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArrayService {

    private final BinarySearchService binarySearchService;

    public ArrayResponseDto getData(ArrayRequestDto request) {
        String type = request.getAlgorithmType();
        if(type.equals("BinarySearch")){
            return binarySearchService.run(request);
        }
        else {
            throw new IllegalArgumentException("지원하지 않는 알고리즘: " + type);
        }
    }
}
