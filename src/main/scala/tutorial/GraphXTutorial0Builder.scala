package tutorial

import org.apache.spark.{SparkContext}
import org.apache.spark.graphx.{Edge, Graph, _}
import org.apache.spark.rdd.RDD
/**
  * Created by cxa123230 on 10/26/2016.
  */
class GraphXTutorial0Builder {

  def createToyGraph(sc:SparkContext): Graph[(String, String), String] ={


    // Create an RDD for the vertices
    val users: RDD[(VertexId, (String, String))] =
    sc.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")),
      (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))
    // Create an RDD for edges
    val relationships: RDD[Edge[String]] =
    sc.parallelize(Array(Edge(3L, 7L, "collab"),    Edge(5L, 3L, "advisor"),
      Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))
    // Define a default user in case there are relationship with missing user
    val defaultUser = ("John Doe", "Missing")
    // Build the initial Graph
    val graph = Graph(users, relationships,defaultUser)
    graph
  }
  def createNumericToyGraph(sc:SparkContext): Graph[VertexId, Double] ={


    // Create an RDD for the vertices
    val users: RDD[(Long,Long )] =
    sc.parallelize(Array((3L, (1L)), (7L, (2L)),
      (5L, (3L)), (2L, (4L))))
    // Create an RDD for edges
    val relationships: RDD[Edge[Double]] =
    sc.parallelize(Array(Edge(3L, 7L, 1.0),    Edge(5L, 3L, 2.0),
      Edge(2L, 5L, 3.0), Edge(5L, 7L, 4.0)))
    // Build the initial Graph
    val graph = Graph(users, relationships)
    graph
  }
}
