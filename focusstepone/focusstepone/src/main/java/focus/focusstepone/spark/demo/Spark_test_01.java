package focus.focusstepone.spark.demo;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.expressions.MutableAggregationBuffer;
import org.apache.spark.sql.expressions.UserDefinedAggregateFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import static org.apache.spark.sql.functions.col;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * focus Create in 14:35 2018/9/18
 */
public class Spark_test_01 {
    public static class Person implements Serializable {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) throws AnalysisException {
        //-- Starting Point:SparkSession
        SparkSession sparkSession = SparkSession.builder().appName("Java Spark SQL Basic Demo").config("spark.some.config.option", "some-value").getOrCreate();
        //-- Creating DataFrame
        Dataset<Row> dataFrame = sparkSession.read().json("./src/main/java/focus/focusstepone/spark/resources/people.json");
        dataFrame.show();
        // --Untyped Dataset Operaitons
        dataFrame.printSchema();
        dataFrame.select("name");
        // col("...") is preferable to dataFrame.col("...")
        dataFrame.select(col("age"), col("age").plus(1)).show();
        dataFrame.filter(col("age").gt(21)).show();
        dataFrame.groupBy(col("age")).count().show();
        //-- Running SQL Queries Programatically
        dataFrame.createOrReplaceTempView("people"); // register the DataFrame as a SQL temporary view
        Dataset<Row> sqlDF = sparkSession.sql("SELECT * FROM people");
        sqlDF.show();
        //-- Global Temporary View
        dataFrame.createGlobalTempView("global_people");
        sparkSession.sql("SELECT age FROM global_temp.global_people").show();
        //-- Creating Datasets

        Person person = new Person();
        person.setName("Andy");
        person.setAge(32);
        Person person1 = new Person();
        person1.setAge(12);
        person1.setName("Focus");

        // Encoders are created for Java beans
        Encoder<Person> personEncoder = Encoders.bean(Person.class);
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person1);
        Dataset<Person> javaBeanDS = sparkSession.createDataset(personList, personEncoder);
        javaBeanDS.show();

        // Encoders for most common types are provided in class Encoders
        Encoder<Integer> integerEncoder = Encoders.INT();
        Dataset<Integer> primitiveDS = sparkSession.createDataset(Arrays.asList(1,2,3), integerEncoder);
        Dataset<Integer> transformedDS = primitiveDS.map((MapFunction<Integer, Integer>) value -> value + 1, integerEncoder);
        transformedDS.show();
        transformedDS.collect();

        // DataFrames can be converted to Dataset by Providing a class, Mapping based on name
        String path = "./src/main/java/focus/focusstepone/spark/resources/people.json";
        Dataset<Person> peopleDS = sparkSession.read().json(path).as(personEncoder);
        peopleDS.show();

        //-- Interring the Schema Using Reflection
        // create an RDD of Person objects from text file
        JavaRDD<Person> personJavaRDD = sparkSession.read().textFile("./src/main/java/focus/focusstepone/spark/resources/people.txt").javaRDD().map(line -> {
            String[] parts = line.split(",");
            Person personTemp = new Person();
            personTemp.setName(parts[0]);
            personTemp.setAge(Integer.parseInt(parts[1].trim()));
            return personTemp;
        });

        // Apply a schema to RDD of JavaBeans to get a DataFrame
        Dataset<Row> peopleDF = sparkSession.createDataFrame(personJavaRDD, Person.class);
        // Register a DataFrame as a temporary view
        peopleDF.createOrReplaceTempView("people");

        // SQL statements can be run by using the sql methods provided by spark
        Dataset<Row> teengerDF = sparkSession.sql("Select * from people");
        teengerDF.show();

        // The columns of a row in the result can be accessed by field index
        Encoder<String> stringEncoder = Encoders.STRING();
        Dataset<String> teenNamesDF = teengerDF.map((MapFunction<Row, String>) row -> "Name: " + row.getString(1), stringEncoder);
        teenNamesDF.show();

        //-- Programmatically Specifying the Schema
        // Create an RDD
        JavaRDD<String> peopleRDD = sparkSession.sparkContext().textFile("./src/main/java/focus/focusstepone/spark/resources/people.txt", 1).toJavaRDD();
        // The Schema is encoded in a string
        String schemaString = "name age";
        // Generated schema based on the string of schema
        List<StructField> fields = new ArrayList<>();
        for (String fieldName : schemaString.split(" ")) {
            StructField structField = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
            fields.add(structField);
        }
        StructType schema = DataTypes.createStructType(fields);

        // Convert records of the RDD(people) to Rows
        JavaRDD<Row> rowJavaRDD = peopleRDD.map((Function<String, Row>) record -> {
            String[] attributes = record.split(",");
            return RowFactory.create(attributes[0], attributes[1].trim());
        });

        // Apply the schema to RDD
        Dataset<Row> peopleDataFrame = sparkSession.createDataFrame(rowJavaRDD, schema);
        // Create a temporary view using DataFrame
        peopleDataFrame.createOrReplaceTempView("people");
        Dataset<Row> results = sparkSession.sql("select name from people");
        results.show();

        // The results of SQL queries are DataFrames and suport all the normal RDD operations
        // The columns of a row in the results can be accessed by field index or by field name
        Dataset<String> namesDS = results.map((MapFunction<Row, String>) row -> "Name: " + row.get(0), Encoders.STRING());
        namesDS.show();


        //-- Aggregations Untyped User-Defined Aggregated Functions
    }

    public static class MyAverage extends UserDefinedAggregateFunction {
        private StructType inputSchema;
        private StructType bufferSchema;

        public MyAverage() {
            List<StructField> inputFields = new ArrayList<>();
            inputFields.add(DataTypes.createStructField("inputColumn", DataTypes.LongType, true));
            inputSchema = DataTypes.createStructType(inputFields);

            List<StructField> bufferFields = new ArrayList<>();
            bufferFields.add(DataTypes.createStructField("sum", DataTypes.LongType, true));
            bufferFields.add(DataTypes.createStructField("count", DataTypes.LongType, true));
            bufferSchema = DataTypes.createStructType(bufferFields);
        }

        // Data types of input arguments of this aggregate function
        @Override
        public StructType inputSchema() {
            return inputSchema;
        }
        // Data types of values in the aggregation buffer
        @Override
        public StructType bufferSchema() {
            return bufferSchema;
        }
        // The data type of return value
        @Override
        public DataType dataType() {
            return DataTypes.DoubleType;
        }
        // Whether this function always returns the same output on the identical input
        @Override
        public boolean deterministic() {
            return true;
        }

        @Override
        public void initialize(MutableAggregationBuffer buffer) {

        }

        @Override
        public void update(MutableAggregationBuffer buffer, Row input) {

        }

        @Override
        public void merge(MutableAggregationBuffer buffer1, Row buffer2) {

        }

        @Override
        public Object evaluate(Row buffer) {
            return null;
        }
    }
}
