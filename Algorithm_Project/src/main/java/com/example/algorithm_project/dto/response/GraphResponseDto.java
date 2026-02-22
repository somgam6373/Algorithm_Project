package com.example.algorithm_project.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GraphResponseDto {

    private String algorithmType;
    private boolean success;
    private String message;
    private List<Integer> result;

    public GraphResponseDto(String algorithmType, boolean b, String success, List<Integer> result) {
        this.algorithmType = algorithmType;
        this.success = b;
        this.message = success;
        this.result = result;
    }
}
