package com.spark.demo;

import org.apache.spark.Accumulator;
import org.apache.spark.InternalAccumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;
import scala.tools.cmd.Spec;

import java.util.Arrays;
import java.util.List;

/**
 * focus Create in 10:38 2018/9/20
 */
public class Spart_test_rdd {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("app name").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        List<Integer> data = Arrays.asList(1,2,3,4,5);
        JavaRDD<Integer> distData = sparkContext.parallelize(data);

        JavaRDD<String> distFile = sparkContext.textFile("./src/main/java/focus/focusstepone/spark/resources/people.txt");

        //-- Passing Functions to Spark
        // lamada expression
        JavaRDD<Integer> lineLengths = distFile.map(line -> line.length());
        int totalLength = lineLengths.reduce((a, b) -> a + b);
        // anonymous inner class
        JavaRDD<Integer> lengths = distFile.map(new Function<String, Integer>() {
            @Override
            public Integer call(String s) throws Exception {
                return s.length();
            }
        });
        int total = lengths.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer a, Integer b) throws Exception {
                return a + b;
            }
        });

        //-- Working with the key-value pairs
        JavaRDD<String> originRDD = sparkContext.textFile("./src/main/java/focus/focusstepone/spark/resources/people.txt");
        JavaPairRDD<String, Integer> javaPairRDD = originRDD.mapToPair(line -> new Tuple2<>(line, 1));
        JavaPairRDD<String, Integer> counterPairRDD = javaPairRDD.reduceByKey((a, b) -> a + b);
        System.out.println(counterPairRDD.sortByKey().collect());

        //-- Broadcast variable
        Broadcast<int[]> broadcast = sparkContext.broadcast(new int[]{1,2,3});
        System.out.println(broadcast.value());

        //-- Accumulators
        Accumulator<Integer> integerAccumulator = sparkContext.intAccumulator(0);
        sparkContext.parallelize(Arrays.asList(1,2,3)).foreach(x -> integerAccumulator.add(x));
    }
}
