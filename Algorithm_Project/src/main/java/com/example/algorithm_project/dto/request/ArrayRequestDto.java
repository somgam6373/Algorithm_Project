package com.example.algorithm_project.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArrayRequestDto {
    private String algorithmType;
    private List<Integer> list;
    private int input;
}
