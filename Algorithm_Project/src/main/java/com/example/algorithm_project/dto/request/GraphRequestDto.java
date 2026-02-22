package com.example.algorithm_project.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GraphRequestDto {

    private String algorithmType;
    private int nodeCount;
    private int edgeCount;
    private int startNode;
    private List<String> edges;
    private boolean directed;
}
