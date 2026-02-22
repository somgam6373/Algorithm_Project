package com.example.algorithm_project.service.sort;

import com.example.algorithm_project.dto.request.SortRequestDto;
import com.example.algorithm_project.dto.response.SortResponseDto;
import com.example.algorithm_project.service.sort.impl.BubbleSortService;
import com.example.algorithm_project.service.sort.impl.QuickSortService;
import com.example.algorithm_project.service.sort.impl.SortInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SortService {

    private final List<SortInterface> sortService;

    public SortResponseDto getData(SortRequestDto request) {
        return sortService.stream()
                .filter(service->service.getType().equals(request.getAlgorithmType()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("지원하지 않는 알고리즘"))
                .run(request);
    }
}
