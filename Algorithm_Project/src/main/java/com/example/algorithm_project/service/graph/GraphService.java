package com.example.algorithm_project.service.graph;

import com.example.algorithm_project.dto.request.GraphRequestDto;
import com.example.algorithm_project.dto.response.GraphResponseDto;
import com.example.algorithm_project.service.graph.impl.BfsService;
import com.example.algorithm_project.service.graph.impl.DfsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraphService {

    private final DfsService dfsService;
    private final BfsService bfsService;

    public GraphResponseDto getData(GraphRequestDto request) {
        String type = request.getAlgorithmType();
        if(type.equals("DFS")) {
            return dfsService.run(request);
        }
        else if(type.equals("BFS")) {
            return bfsService.run(request);
        }
        else {
            throw new IllegalArgumentException("지원하지 않는 알고리즘: " + type);
        }
    }
}