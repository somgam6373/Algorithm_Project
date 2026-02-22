package com.example.algorithm_project.service.graph.impl;

import com.example.algorithm_project.dto.request.GraphRequestDto;
import com.example.algorithm_project.dto.response.GraphResponseDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class BfsService implements GraphInterface {

    @Override
    public String getType(){
        return "BFS";
    }

    @Override
    public GraphResponseDto run(GraphRequestDto request){
        int nodeCount = request.getNodeCount();
        int startNode = request.getStartNode();
        List<String> edges = request.getEdges();
        boolean directed = request.isDirected();

        List<Integer>[] graph = createGraph(nodeCount, edges, directed);
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[nodeCount+1];

        bfs(startNode, graph, visited, result);

        return new GraphResponseDto(request.getAlgorithmType(),true,"success",result);

    }


    private List<Integer>[] createGraph(int nodeCount,
                                        List<String> edges,
                                        boolean directed) {

        List<Integer>[] graph = new ArrayList[nodeCount + 1];

        for (int i = 0; i <= nodeCount; i++) {
            graph[i] = new ArrayList<>();
        }

        for (String edge : edges) {

            if (edge == null || edge.trim().isEmpty()) continue;

            String[] split = edge.trim().split("\\s+");
            if (split.length < 2) continue;

            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);

            if (from < 1 || from > nodeCount ||
                    to < 1 || to > nodeCount) continue;

            graph[from].add(to);

            if (!directed) {
                graph[to].add(from);
            }
        }

        for (int i = 1; i <= nodeCount; i++) {
            Collections.sort(graph[i]);
        }

        return graph;
    }


    private void bfs(int node, List<Integer>[] graph, boolean[] visited, List<Integer> result) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while(!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            for (int next : graph[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
        }
    }
}
}
