name := "TutorialScalaMongodb"
scalaVersion := "2.12.6"
fork in run := true

libraryDependencies ++= Seq(
 "org.mongodb.scala" %% "mongo-scala-driver" % "2.4.0",
"com.cra.figaro" %% "figaro" % "5.0.0.0",
"org.neo4j.driver" % "neo4j-java-driver" % "1.5.2"

)


