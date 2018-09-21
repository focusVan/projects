from pyspark.sql import SparkSession

## Starting point: SparkSession
spark = SparkSession.builder.appName("Python Spark SQL basic example").config("spark.some.config.option", "some-value").getOrCreate()
## Creating DataFrames
df = spark.read.json("resources/people.json")
df.show()
## Untyped Dataset Operations
df.printSchema() # Print the schema in a tree format
df.select("name").show() # Select only thte "name" column
df.select(df['name'], df['age'] + 1).show()
df.filter(df['age'] > 20).show()
df.groupBy("age").count().show()
## Running SQL Queries Programmatically
df.createOrReplaceTempView("people") # register the dataframe as a temporary view
sqlDF = spark.sql("SELECT * FROM people WHERE age < 20")
sqlDF.show()
## Global Temporay View
df.createGlobalTempView("global_people")
spark.sql("SELECT * FROM global_temp.global_people WHERE age = '30'").show() # Global temporary view is tied to a system preseved database "global_temp"
spark.newSession().sql("SELECT age FROM global_temp.global_people").show() # Global temporary view is cross-session
## Inferring the Schema Using Reflection
from pyspark.sql import Row
sparkContext = spark.sparkContext;

# Load a text file and convert each line to Row
lines = sparkContext.textFile("resources/people.txt")
parts = lines.map(lambda line : line.split(","))
people = parts.map(lambda part : Row(name = part[0], age = int(part[1])))

# Infer the schema, and register the Dataframe as a table
schemaPeople = spark.createDataFrame(people)
schemaPeople.show()
schemaPeople.createOrReplaceTempView("people")

# SQL can be run over Dataframe that have been registered as a teble
teenagers = spark.sql("Select * from people where age < 20 and age > 13")
teenagers.show()

# The results of SQL queries are Dataframe objects
# rdd returns the content as an : class:'pyspark.RDD' of :class:'Row'
teenNames = teenagers.rdd.map(lambda p : "Name : " + p.name).collect()
for name in teenNames:
    print(name)

## Programmatically Specifying the Schema

from pyspark.sql.types import *
# each line is converted to a tuple
peopleTuple = parts.map(lambda part : (part[0], part[1].strip()))

# the schema is encoded in a string
schemaString = "name age"
fields = [StructField(fieldName, StringType(), True) for fieldName in schemaString.split()]
schema = StructType(fields)

# Apply the schema2 to the RDD
dataFrame = spark.createDataFrame(peopleTuple, schema)

# Create a temporary view using the DataFrame
dataFrame.createOrReplaceTempView("people")

# SQL can be run over DataFrames that have been registered as a table
results = spark.sql("SELECT * FROM people")
results.show()



