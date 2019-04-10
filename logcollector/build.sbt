
name := "logcollector"

version := "0.0.1"

scalaVersion := "2.11.11"

libraryDependencies += "org.apache.spark"   % "spark-streaming_2.11"  % "2.2.0"   % "provided"
libraryDependencies += "org.apache.spark"   % "spark-core_2.11"        % "2.2.0"   % "provided"
libraryDependencies += "org.apache.spark"   % "spark-sql_2.11"         % "2.2.0"   % "provided"
libraryDependencies += "org.apache.spark"   % "spark-streaming-kafka-0-8_2.11" % "2.2.0"
libraryDependencies += "com.typesafe"        % "config"                 % "1.3.1"
libraryDependencies += "commons-cli"         % "commons-cli"            % "1.2"
libraryDependencies += "org.apache.avro"     % "avro"                    % "1.8.2"
