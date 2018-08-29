name := "TutorialScalaMongodb"
scalaVersion := "2.12.6"
fork in run := true

libraryDependencies ++= Seq(
 "org.mongodb.scala" %% "mongo-scala-driver" % "2.4.0"

)


