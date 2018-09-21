from pyspark import SparkConf,SparkContext

conf = SparkConf().setAppName("Spark RDD").setMaster("local")
sparkContext = SparkContext(conf=conf)

data = [1,2,3,4,5]
distData = sparkContext.parallelize(data)

lines = sparkContext.textFile("resources/people.json")
lineLengths = lines.map(lambda s : len(s))
totalLengths = lineLengths.reduce(lambda a, b : a + b)
print(totalLengths)

## Passing Functions to Spark
def myFunc(s):
    words = s.split(",")
    return len(words)

sparkContext.textFile("resources/people.txt").map(myFunc)

## Working with the key-value pairs
lines = sparkContext.textFile("resources/people.txt")
pairs = lines.map(lambda line : (line, 1))
counts = pairs.reduceByKey(lambda a,b : a + b)
print(counts.sortByKey().collect())

## Transformations

