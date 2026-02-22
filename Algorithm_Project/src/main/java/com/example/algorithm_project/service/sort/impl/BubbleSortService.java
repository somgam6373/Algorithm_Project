package com.example.algorithm_project.service.sort.impl;

import com.example.algorithm_project.dto.request.SortRequestDto;
import com.example.algorithm_project.dto.response.SortResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BubbleSortService {

    public SortResponseDto run(SortRequestDto request) {
        List<Integer> list = new ArrayList<>(request.getList());
        List<Integer> explore = new ArrayList<>();

        int n = list.size();

        // 버블 정렬 알고리즘
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                explore.add(j);
                explore.add(j + 1);

                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }

        return new SortResponseDto(request.getAlgorithmType(), list, explore, "Bubble Sort Completed");
    }
}