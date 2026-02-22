package com.example.algorithm_project.service.graph.impl;

import com.example.algorithm_project.dto.request.GraphRequestDto;
import com.example.algorithm_project.dto.response.GraphResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DfsService implements GraphInterface {

    @Override
    public String getType(){
        return "DFS";
    }

    @Override
    public GraphResponseDto run(GraphRequestDto request) {

        int nodeCount = request.getNodeCount();
        int startNode = request.getStartNode();
        List<String> edges = request.getEdges();
        boolean directed = request.isDirected();

        List<Integer>[] graph = createGraph(nodeCount,edges, directed);
        boolean[] visited = new boolean[nodeCount+1];
        List<Integer> result = new ArrayList<>();

        dfs(startNode, graph, visited, result);

        return new GraphResponseDto(request.getAlgorithmType(),true,"success",result);
    }
    private List<Integer>[] createGraph(int nodeCount,
                                        List<String> edges,
                                        boolean directed) {

        List<Integer>[] graph = new ArrayList[nodeCount + 1];
        for (int i = 1; i <= nodeCount; i++) {
            graph[i] = new ArrayList<>();
        }

        for (String edge : edges) {

            if (edge == null || edge.trim().isEmpty()) continue;

            String[] split = edge.trim().split("\\s+");
            if (split.length < 2) continue;

            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);

            graph[from].add(to);

            if (!directed) {
                graph[to].add(from);
            }
        }

        return graph;
    }



    private void dfs(int node, List<Integer>[] graph, boolean[] visited, List<Integer> result) {

        visited[node] = true;
        result.add(node);
        for(int next : graph[node]){
            if(!visited[next]){
                dfs(next, graph, visited, result);
            }
        }
    }
}
