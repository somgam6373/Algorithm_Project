package com.example.algorithm_project.service.array.impl;

import com.example.algorithm_project.dto.request.ArrayRequestDto;
import com.example.algorithm_project.dto.response.ArrayResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinarySearchService implements ArrayInterface{
    @Override
    public String getType(){
        return "BinarySearch";
    }

    @Override
    public ArrayResponseDto run(ArrayRequestDto request) {
       List<Integer> list = request.getList();
       List<Integer> explore = new ArrayList<>();

       int input = request.getInput();
       int s = 0;
       int e = list.size() - 1;
       int result = binarySearch(list, s, e, input, explore);

       for(int i: explore) {
           System.out.println(i);
       }

       return new ArrayResponseDto(request.getAlgorithmType(), list,result,"Success",explore);
    }
    private int binarySearch(List<Integer> list, int s, int e, int input, List<Integer> explore) {

        if (s > e) return -1;

        int mid = s + (e - s) / 2;
        explore.add(mid);

        if (list.get(mid) == input) {
            return mid;
        } else if (list.get(mid) > input) {
            return binarySearch(list, s, mid - 1, input, explore);
        } else {
            return binarySearch(list, mid + 1, e, input, explore);
        }
    }
}
