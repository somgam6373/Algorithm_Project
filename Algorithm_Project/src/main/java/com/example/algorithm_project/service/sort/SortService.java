package com.example.algorithm_project.service.sort;

import com.example.algorithm_project.dto.request.SortRequestDto;
import com.example.algorithm_project.dto.response.SortResponseDto;
import com.example.algorithm_project.service.sort.impl.BubbleSortService;
import com.example.algorithm_project.service.sort.impl.QuickSortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SortService {

    private final BubbleSortService bubbleSortService;
    private final QuickSortService quickSortService;

    public SortResponseDto getData(SortRequestDto request) {
        String algorithmType = request.getAlgorithmType();
        if (algorithmType.equals("BubbleSort")) {
            return bubbleSortService.run(request);
        }
        else if(algorithmType.equals("QuickSort")) {
            return quickSortService.run(request);
        }
        else {
            throw new IllegalArgumentException("지원하지 않는 알고리즘: " + algorithmType);
        }
    }
}
