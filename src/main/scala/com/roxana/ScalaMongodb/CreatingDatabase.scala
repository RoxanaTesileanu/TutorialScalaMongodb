/* This object helps you create a MongoDB database called "MyStuff" on your localhost. Within this database you will create a collection called "FruitsAndVegetables".
The functions presented here will help you do CRUD processing. If you need additional guidance check the official reference of the mongodb-scala-driver. If you need more complex connections you should use a connection string or the settings builder. In the next scala script I will elaborate a connection strig example (which you should be able to adapt to your personal requirements). 

PREREQUISITES:
- you should install mongodb on your machine (go to the official page and follow the steps for your distribution)
- you have to allow the port 27017 used by the localhost for mongo ($ sudo ufw allow 27017)
- you have to start the mongod service in a terminal ($ sudo service mogod start). At the end of the session you should close the service ($sudo service mongod stop). 
- in another terminal, navigate to the project's directory and start sbt and the scala console and test the codes within the bellow object (Copy-paste it and wait to see the how the REPL evaluates it. This will help you undestand how mongodb behaves.)

*/

package com.roxana.ScalaMongodb

object CreatingDatabase {

import org.mongodb.scala._
import concurrent.ExecutionContext.Implicits.global

val mongoClient : MongoClient = MongoClient("mongodb://localhost:27017") // this should open the connection and you can inspect all the connection settings and details.
Thread.sleep(7000)
val mydb : MongoDatabase =  mongoClient.getDatabase("MyStuff") // this creates the database; until we populate it with records it isn't really instantiated by mongodb.
Thread.sleep(4000)
val mycol = mydb.getCollection("FruitsAndVegetables") // this creates the collection; until we populate it, it isn't really instantiated by mongodb.
Thread.sleep(4000)
val mydoc1 : org.mongodb.scala.Document = Document(
	"_id"-> 0,
	"fruits" -> "apple",
	"color"-> "red",
	"observation"-> "An apple a day keeps the doctor away") // this creates a record. 

val mydoc2 : org.mongodb.scala.Document = Document(
     "_id"->1,
     "fruits" -> "banane",
     "color" -> "yellow",
     "observation" -> "Everyone loves them")

val mydoc3 : org.mongodb.scala.Document = Document(
     "_id"->2,
     "fruits" -> "citruses",
     "color" -> "yellow",
     "observation" -> "They are healthy!")

 val mydoc4:  org.mongodb.scala.Document = Document(
     "_id" -> 3,
     "vegetables" -> "tomato",
     "color" -> "red",
     "observation" -> "Are the best!")

 val mydoc5:  org.mongodb.scala.Document = Document(
     "_id" -> 4,
     "vegetables" -> "potatoes",
     "color" -> "braun",
     "observation" -> "Delicios!")

val myDocsArray : Array[Document] = Array(mydoc1, mydoc2, mydoc3, mydoc4, mydoc5)

def completedInsertion (doc: Document, col: MongoCollection[Document]): scala.concurrent.Future[org.mongodb.scala.Completed] = {
     val myFutureRecord = col.insertOne(doc).toFuture
     myFutureRecord 
}  //this method will insert a document into the database.

def completedInsertionMany(docArray: Array[Document], col: MongoCollection[Document]) : Array[scala.concurrent.Future[org.mongodb.scala.Completed]]= {
  docArray.map(doc => completedInsertion(doc, col))
}

def logFindAllResults(col: MongoCollection[Document]) : Unit = {
concurrent.Future{col.find().foreach(println)}
Thread.sleep(4000)
} //this method uses the callback "foreach" to get the raw log of the existing documents within a collection. In the next script I will explain how to transit from an Observable, to Future and so on, in order to get an array of the key-value pairs within a document. At this point you should take a look at scala's futures and promises: https://docs.scala-lang.org/overviews/core/futures.html. For more details on Futures and Options in Scala consult the book of Jason Swartz "Learning Scala".


def main (args: Array[String]) {
//completedInsertion(mydoc1, mycol) this line uses the function to insert a document into the database. Now, if the collection and the database don't exist they are instantiated and will get listed when you check the list of database names and collection names.
//completedInsertionMany(myDocsArray, mycol) this line inserts the whole array of docs into the collection.
logFindAllResults(mycol)

mongoClient.close() // this will close the connection.
Thread.sleep(4000)
}

/* 
You can run this object in sbt with the command "run" and need to wait until it has finished. The first time you run the application, you can uncomment the line "completedInsertion(mydoc1, mycol)". Then, after you rerun the application you would like to comment it back in order to not ask for the same insertion again (it won't make it because it has a unique id, but you will get warnings in your output).
*/
}
