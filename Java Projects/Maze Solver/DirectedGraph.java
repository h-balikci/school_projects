package GraphPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import ADTPackage.*; // Classes that implement various ADTs
/**
 A class that implements the ADT directed graph.
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.1
 */
public class DirectedGraph<T> implements GraphInterface<T>
{
   private DictionaryInterface<T, VertexInterface<T>> vertices;
   private int edgeCount;
   
   public DirectedGraph()
   {
      vertices = new UnsortedLinkedDictionary<>();
      edgeCount = 0;
   } // end default constructor

   public boolean addVertex(T vertexLabel)
   {
      VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
      return addOutcome == null; // Was addition to dictionary successful?
   } // end addVertex
   
   public boolean addEdge(T begin, T end, double edgeWeight)
   {
      boolean result = false;
      VertexInterface<T> beginVertex = vertices.getValue(begin);
      VertexInterface<T> endVertex = vertices.getValue(end);
      if ( (beginVertex != null) && (endVertex != null) )
         result = beginVertex.connect(endVertex, edgeWeight);
      if (result)
         edgeCount++;
      return result;
   } // end addEdge
   
   public boolean addEdge(T begin, T end)
   {
      return addEdge(begin, end, 0);
   } // end addEdge

   public boolean hasEdge(T begin, T end)
   {
      boolean found = false;
      VertexInterface<T> beginVertex = vertices.getValue(begin);
      VertexInterface<T> endVertex = vertices.getValue(end);
      if ( (beginVertex != null) && (endVertex != null) )
      {
         Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
         while (!found && neighbors.hasNext())
         {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (endVertex.equals(nextNeighbor))
               found = true;
         } // end while
      } // end if
      
      return found;
   } // end hasEdge

	public boolean isEmpty()
	{
	  return vertices.isEmpty();
	} // end isEmpty

	public void clear()
	{
	  vertices.clear();
	  edgeCount = 0;
	} // end clear

	public int getNumberOfVertices()
	{
	  return vertices.getSize();
	} // end getNumberOfVertices

	public int getNumberOfEdges()
	{
	  return edgeCount;
	} // end getNumberOfEdges

	protected void resetVertices()
	{
	   Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
	   while (vertexIterator.hasNext())
	   {
	      VertexInterface<T> nextVertex = vertexIterator.next();
	      nextVertex.unvisit();
	      nextVertex.setCost(0);
	      nextVertex.setPredecessor(null);
	   } // end while
	} // end resetVertices
	
	public StackInterface<T> getTopologicalOrder() 
	{
		resetVertices();

		StackInterface<T> vertexStack = new LinkedStack<>();
		int numberOfVertices = getNumberOfVertices();
		for (int counter = 1; counter <= numberOfVertices; counter++)
		{
			VertexInterface<T> nextVertex = findTerminal();
			nextVertex.visit();
			vertexStack.push(nextVertex.getLabel());
		} // end for
		
		return vertexStack;	
	} // end getTopologicalOrder
	
	
   public QueueInterface<T> getBreadthFirstTraversal(T origin, T end)
   {
	   boolean found = false;
	   int theNumberOfVisitedVerticiesBFS = 0;
	   QueueInterface<T> traversalOrder = new LinkedQueue<T>();
	   QueueInterface<T> vertexQueue = new LinkedQueue<T>();
	   VertexInterface<T> originVertex = vertices.getValue(origin);
	   originVertex.visit();
	   theNumberOfVisitedVerticiesBFS++;
	   traversalOrder.enqueue(origin);
	   vertexQueue.enqueue(origin);
	   
	   while (!vertexQueue.isEmpty() && !found)
	   {
		   VertexInterface<T> frontVertex = vertices.getValue(vertexQueue.dequeue());
		   
		   Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
		   while(neighbors.hasNext() && !found)
		   {
			   VertexInterface<T> nextNeighbor = neighbors.next();
			   
			   if(!nextNeighbor.isVisited())
			   {
				   nextNeighbor.visit();	
				   theNumberOfVisitedVerticiesBFS++;
				   traversalOrder.enqueue(nextNeighbor.getLabel());
				   vertexQueue.enqueue(nextNeighbor.getLabel());
			   }
			   
			   if(nextNeighbor.getLabel().equals(end))
			   {
				   found = true;
				   break;
			   }
			   
		   }
	   }
	   System.out.println("The number of visited verticies for BFS: " + theNumberOfVisitedVerticiesBFS);
	   return traversalOrder;
   }
   
   public QueueInterface<T> getDepthFirstTraversal(T origin, T end) 
   {
	   boolean found = false;
	   int theNumberOfVisitedVerticiesDFS = 0;
	   QueueInterface<T> traversalOrder = new LinkedQueue<T>();
	   StackInterface<T> vertexStack = new LinkedStack<T>();
	   VertexInterface<T> originVertex = vertices.getValue(origin);
	   originVertex.visit();
	   theNumberOfVisitedVerticiesDFS++;
	   traversalOrder.enqueue(origin);
	   vertexStack.push(origin);
	   while (!vertexStack.isEmpty())
	   {
		   VertexInterface<T> topVertex = vertices.getValue(vertexStack.peek());
		   Iterator<VertexInterface<T>> neighbors = topVertex.getNeighborIterator();
		   if (topVertex.getUnvisitedNeighbor() != null) 
		   {
			   boolean flag = false;
			   VertexInterface<T> nextNeighbor = neighbors.next();
			   while (!flag) 
			   {
				   if(!nextNeighbor.isVisited())
				   {
					   flag = true;
				   }
				   else
					   nextNeighbor = neighbors.next();
					   
			   }
			   
			   if (!nextNeighbor.isVisited()) 
			   {
				   nextNeighbor.visit();
				   theNumberOfVisitedVerticiesDFS++;
				   traversalOrder.enqueue(nextNeighbor.getLabel());
				   vertexStack.push(nextNeighbor.getLabel());
			   }
			   if(nextNeighbor.getLabel().equals(end))
			   {
				   found = true;
				   break;
			   }
			   
			   
		   }
		   else
			   vertexStack.pop();
	   }
	   
	   System.out.println("The number of visited verticies for DFS: " + theNumberOfVisitedVerticiesDFS);
	   return traversalOrder;
   }
   
	public int getShortestPath(T begin, T end, StackInterface<T> path)
	{
		int theNumberOfVisitedVerticies = 0;
		resetVertices();
		boolean done = false;
		QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<VertexInterface<T>>();
		VertexInterface<T> originVertex = vertices.getValue(begin); 
		VertexInterface<T> endVertex = vertices.getValue(end);
		originVertex.visit();
		theNumberOfVisitedVerticies++;

		vertexQueue.enqueue (originVertex);
		while (!done && !vertexQueue.isEmpty())
		{
			VertexInterface<T> frontVertex = vertexQueue.dequeue();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			while (!done && neighbors.hasNext())
			{
				VertexInterface<T> nextNeighbor = neighbors.next();
				if (!nextNeighbor.isVisited())
				{
					nextNeighbor.visit();
					theNumberOfVisitedVerticies++;
					nextNeighbor.setCost (1 + frontVertex.getCost()); 
					nextNeighbor.setPredecessor (frontVertex); 
					vertexQueue.enqueue(nextNeighbor);
				} // end if
				if (nextNeighbor.equals(endVertex))
					done = true;
			} // end while
		}
			// Traversal ends; construct shortest path 
			int pathLength = (int)endVertex.getCost(); 
			path.push(endVertex.getLabel());
			VertexInterface<T> vertex = endVertex;
			while (vertex.hasPredecessor())
			{
				vertex = vertex.getPredecessor();
				path.push(vertex.getLabel());
			}
			
			System.out.println("The number of visited vertices for shortest path: " + theNumberOfVisitedVerticies);
			return pathLength;
		
	}
  
      public double getCheapestPath(T begin, T end, StackInterface<T> path)
      {
    	  int theNumberOfVisitedVerticiesWeighted = 0;
    	  boolean done = false;
    	  PriorityQueueInterface<EntryPQ> priorityQueue = new HeapPriorityQueue<EntryPQ>();
    	  VertexInterface<T> originVertex = vertices.getValue(begin); 
    	  VertexInterface<T> endVertex = vertices.getValue(end); 
    	  priorityQueue.add(new EntryPQ(originVertex, 0, null));
    	  while (!done && !priorityQueue.isEmpty()) 
    	  {
			EntryPQ frontEntry = priorityQueue.remove();
			VertexInterface<T> frontVertex = frontEntry.getVertex();
			if(!frontVertex.isVisited())
			{
				frontVertex.visit();
				theNumberOfVisitedVerticiesWeighted++;
				frontVertex.setCost (frontEntry.getCost()); 
				frontVertex.setPredecessor(frontEntry.getPredecessor());
				if(frontVertex.equals(endVertex))
				{
					done = true;
				}
				else
				{
					Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
					Iterator<Double> weight = frontVertex.getWeightIterator();
					while(neighbors.hasNext())
					{
						VertexInterface<T> nextNeighbor = neighbors.next();
						double weightOfEdgeToNeighbor = weight.next();
						
						if(!nextNeighbor.isVisited())
						{
							double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
							priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
						}
					}
					
				}
				
			}
    	  }
    	  double pathCost = endVertex.getCost();
    	  path.push(end);
    	  VertexInterface<T> vertex = endVertex;
    	  while(vertex.hasPredecessor())
    	  {
    		  vertex = vertex.getPredecessor();
    		  path.push(vertex.getLabel());
    	  }
    	  
    	  System.out.println("The number of visited vertices for cheapest path: " + theNumberOfVisitedVerticiesWeighted);
    	  System.out.println();
    	  System.out.println("The cost of the cheapest path: " + pathCost);
    	  return pathCost;
      }

      //DISPLAYS ADJACENCY LIST
      public void displayAdjacencyList()
  	{
  		displayEdges();
  	}
      
      //THIS METHOD FIRSTLY FILLS MATRIX WITH 0'S THEN ADDS HEADERS (VERTEX LABELS) AND THEN CONTROLS EDGES
      public String[][] initializeAdjacencyMatrix()
  	{
    	String[][] adjacencyMatrix = new String[getNumberOfVertices() + 1][getNumberOfVertices() + 1];
    	for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[0].length; j++) {
				adjacencyMatrix[i][j] = "0";
			}
		}
    	adjacencyMatrix[0][0] = " ";
    	
    	
    	
    	int i = 1;
  		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
  		while (vertexIterator.hasNext())
  		{
  			T vertexName = ((Vertex<T>)(vertexIterator.next())).getLabel();
  			adjacencyMatrix[0][i] = (String)vertexName;
			adjacencyMatrix[i][0] = (String)vertexName;
  			i++;
  			
  		} // end while
  		for (int k = 1; k < adjacencyMatrix.length; k++) {
  			for (int j = 2; j < adjacencyMatrix.length; j++) {
  				if(hasEdge((T)adjacencyMatrix[0][k], (T)adjacencyMatrix[0][j]))
				{
					adjacencyMatrix[k][j] = "1";
					adjacencyMatrix[j][k] = "1";
				}
			}
				
		}
  		return adjacencyMatrix;
  	}
      
      //PRINTS ADJACENCY MATRIX
      public void displayAdjacencyMatrix(String[][] adjacency)
  	{
  		for (int i = 0; i < adjacency.length; i++) {
  			for (int j = 0; j < adjacency[0].length; j++) {
  				
  				
  				if(j == 0 && i == 0)
  					System.out.print("  ");
  				
  				if(i != 0 && j == 0)
  					System.out.print(" ");
  				
  				
  				if (j != 0 && i != 0) {
  					System.out.print(adjacency[i][j] + "   ");
				}
  				else
  					System.out.print(adjacency[i][j]  + " ");
  				
  			}
  			System.out.println();
  		}
  	}
    //###########################################################################
      
      
      
      

	
	protected VertexInterface<T> findTerminal()
	{
		boolean found = false;
		VertexInterface<T> result = null;

		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();

		while (!found && vertexIterator.hasNext())
		{
			VertexInterface<T> nextVertex = vertexIterator.next();
			
			// If nextVertex is unvisited AND has only visited neighbors)
			if (!nextVertex.isVisited())
			{ 
				if (nextVertex.getUnvisitedNeighbor() == null )
				{ 
					found = true;
					result = nextVertex;
				} // end if
			} // end if
		} // end while

		return result;
	} // end findTerminal

	// Used for testing
	public void displayEdges()
	{
		System.out.println("\nEdges exist from the first vertex in each line to the other vertices in the line.");
		System.out.println("(Edge weights are given; weights are zero for unweighted graphs):\n");
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		while (vertexIterator.hasNext())
		{
			((Vertex<T>)(vertexIterator.next())).display();
		} // end while
	} // end displayEdges 
	
	private class EntryPQ implements Comparable<EntryPQ>
	{
		private VertexInterface<T> vertex; 	
		private VertexInterface<T> previousVertex; 
		private double cost; // cost to nextVertex
		
		private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex)
		{
			this.vertex = vertex;
			this.previousVertex = previousVertex;
			this.cost = cost;
		} // end constructor
		
		public VertexInterface<T> getVertex()
		{
			return vertex;
		} // end getVertex
		
		public VertexInterface<T> getPredecessor()
		{
			return previousVertex;
		} // end getPredecessor

		public double getCost()
		{
			return cost;
		} // end getCost
		
		public int compareTo(EntryPQ otherEntry)
		{
			// Using opposite of reality since our priority queue uses a maxHeap;
			// could revise using a minheap
			return (int)Math.signum(otherEntry.cost - cost);
		} // end compareTo
		
		public String toString()
		{
			return vertex.toString() + " " + cost;
		} // end toString 
	} // end EntryPQ
} // end DirectedGraph
