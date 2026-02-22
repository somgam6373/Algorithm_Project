package com.example.algorithm_project.service.graph;

import com.example.algorithm_project.dto.request.GraphRequestDto;
import com.example.algorithm_project.dto.response.GraphResponseDto;
import com.example.algorithm_project.service.graph.impl.BfsService;
import com.example.algorithm_project.service.graph.impl.DfsService;
import com.example.algorithm_project.service.graph.impl.GraphInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GraphService {

    private final List<GraphInterface> graphService;

    public GraphResponseDto getData(GraphRequestDto request) {
        return graphService.stream()
                .filter(service->service.getType().equals(request.getAlgorithmType()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("지원하지 않는 알고리즘"))
                .run(request);
    }
}