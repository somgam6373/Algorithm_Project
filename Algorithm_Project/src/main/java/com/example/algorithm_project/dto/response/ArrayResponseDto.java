package com.example.algorithm_project.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArrayResponseDto {
    private String algorithmType;
    private List<Integer> list;
    private int result;
    private String message;
    private List<Integer> explore;

    public ArrayResponseDto(String algorithmType, List<Integer> list, int result, String message, List<Integer> explore) {
        this.algorithmType = algorithmType;
        this.list = list;
        this.result = result;
        this.message = message;
        this.explore = explore;
    }
}
