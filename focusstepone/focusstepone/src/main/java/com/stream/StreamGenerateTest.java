package com.stream;

import java.util.Arrays;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * focus Create in 16:06 2019/4/11
 */
public class StreamGenerateTest {
    public static void main(String[] args){
        //genarated from array
        int[] array = {1,2,3,4,5};
        IntStream intStream = Arrays.stream(array);
        intStream.filter(x -> x > 3).forEach(System.out::println);
        System.out.println(array.length);
        //genarated from conllection
        List<String> list = Arrays.asList("hello","word");
        Stream<String> listStream = list.stream();
        listStream.forEach(System.out::println);
        //generated empty stream
        Stream emptyStream = Stream.empty();
        //generate infinite stream
        Stream.iterate(0, x -> x + 1).limit(10).forEach(System.out::println);
    }
}
