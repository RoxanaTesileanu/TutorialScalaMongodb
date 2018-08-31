/* This object helps you create a MongoDB database called "MyStuff" on your localhost. Within this database you will create a collection called "FruitsAndVegetables".
The functions presented here will help you do CRUD processing. If you need additional guidance check the official reference of the mongodb-scala-driver. If you need more complex connections you should use a connection string or the settings builder. In the next scala script I will elaborate a connection strig example (which you should be able to adapt to your personal requirements). 

PREREQUISITES:
- you should install mongodb on your machine (go to the official page and follow the steps for your distribution)
- you have to allow the port 27017 used by the localhost for mongo ($ sudo ufw allow 27017)
- you have to start the mongod service in a terminal ($ sudo service mogod start). At the end of the session you should close the service ($sudo service mongod stop). 
- in another terminal, navigate to the project's directory and start sbt and the scala console and test the codes within the bellow object (Copy-paste it and wait to see the how the REPL evaluates it. This will help you undestand how mongodb behaves.)

*/

import org.mongodb.scala._

object CreatingDatabase {

val mongoClient : MongoClient = MongoClient() // this should open the connection and you can inspect all the connection settings and details.

val mydb : MongoDatabase = mongoClient.getDatabase("MyStuff") // this creates the database; until we populate it with records it isn't really instantiated by mongodb.
val mycol = mydb.getCollection("FruitsAndVegetables") // this creates the collection; until we populate it, it isn't really instantiated by mongodb.

val mydoc : org.mongodb.scala.Document = Document(
	"_id"-> 0,
	"fruits" -> "apple",
	"color"-> "red",
	"observation"-> "Eva took a bite and was wrong about it") // this creates a record. 

def completedInsertion (doc: Document, col: MongoCollection[Document]): Unit = {
     col.insertOne(doc).subscribe(new Observer[Completed]{
     override def onNext(result: Completed) : Unit = println("inserted")
     override def onError(e: Throwable) : Unit = println("failed")
     override def onComplete() : Unit = println("completed")})
}  //this method will insert a document into the database.

completedInsertion(mydoc, mycol) // this line uses the funtion to insert a document into the database. Now the collection and the database are instantiated and will get listed when you check the list of database names and collection names.

mongoClient.close() // this will close the connection.

def main (args: Array[String]) { //main method that will perform all tasks.

}

}
