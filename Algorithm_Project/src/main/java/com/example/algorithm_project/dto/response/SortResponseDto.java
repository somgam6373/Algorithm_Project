package com.example.algorithm_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SortResponseDto {

    private String algorithmType;
    private List<Integer> list;
    private List<Integer> explore;
    private String message;

    public SortResponseDto(String algorithmType, List<Integer> list, List<Integer> explore, String message) {
        this.algorithmType = algorithmType;
        this.list = list;
        this.explore = explore;
        this.message = message;
    }
}
