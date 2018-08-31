/* This object helps you create a MongoDB database called "MyStuff". Within this database you will create a collection called "FruitsAndVegetables".
The functions presented here will help you do CRUD processing. If you need additional guidance check the official reference of the mongodb-scala-driver.

PREREQUISITES:
- you should install mongodb on your machine (go to the official page and follow the steps for your distribution)
- you have to allow the port 27017 used by the localhost for mongo ($ sudo ufw allow 27017)
- you have to start the mongod service in a terminal ($ sudo service mogod start). At the end of the session you should close the service ($sudo service mongod stop). 
- in another terminal, navigate to the project's directory and start sbt and the scala console and test the codes within the bellow object (Copy-paste it and wait to see the how the REPL evaluates it. This will help you undestand how mongodb behaves.)

*/

import org.mongodb.scala._

object CreatingDatabase{

val mongoClient : MongoClient = MongoClient() // this should open the connection and you can inspect all the connection settings and details.





def main (args: Array[String]) { //main method that will perform all tasks.

}

}
