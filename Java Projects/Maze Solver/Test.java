import GraphPackage.*;
import ADTPackage.*;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner; // Import the Scanner class to read text files

public class Test {

	public static void main(String[] args) 
	{
		String txtPath = "C:\\Users\\Hasan\\Desktop\\maze\\maze1.txt";
		char[][] mazeArray = fileReader(txtPath);
		char[][] mazeArray2 = fileReader(txtPath);
		char[][] mazeArray3 = fileReader(txtPath);
		char[][] mazeArray4 = fileReader(txtPath);
		UndirectedGraph<String> mazeGraph = arrayToGraph(mazeArray);
		
		
		//PRINTING ADJACENCY MATRIX
		System.out.println("Adjacency Matrix: ");
		String[][] adjacencyMatrix = mazeGraph.initializeAdjacencyMatrix();
		mazeGraph.displayAdjacencyMatrix(adjacencyMatrix);
		System.out.println();
		
		//PRINTING ADJACENCY LIST
		System.out.println("Adjacency List: ");
		mazeGraph.displayAdjacencyList();
		System.out.println();
		
		//PRINTING NUMBER OF EDGES FOUND
		System.out.println("The number of edges found: " + mazeGraph.getNumberOfEdges());
		System.out.println();
		
		//BREADTH FIRST TRAVERSAL
		QueueInterface<String> breadthFirstQueue = mazeGraph.getBreadthFirstTraversal("0-1", (mazeArray.length - 2) + "-" + (mazeArray[0].length - 1));
		while(!breadthFirstQueue.isEmpty())
		{
			String[] coordinates = breadthFirstQueue.dequeue().split("-");
			int x = Integer.parseInt(coordinates[0]);
			int y = Integer.parseInt(coordinates[1]);
			mazeArray[x][y] = '.';
		}
		displayMazeArray(mazeArray);
		System.out.println();
		
		//DEPTH FIRST TRAVERSAL
		UndirectedGraph<String> mazeGraph1 = arrayToGraph(mazeArray2);
		QueueInterface<String> depthFirstQueue = mazeGraph1.getDepthFirstTraversal("0-1", (mazeArray.length - 2) + "-" + (mazeArray[0].length - 1));
		while(!depthFirstQueue.isEmpty())
		{
			String[] coordinates = depthFirstQueue.dequeue().split("-");
			int x = Integer.parseInt(coordinates[0]);
			int y = Integer.parseInt(coordinates[1]);
			mazeArray2[x][y] = '.';
		}
		displayMazeArray(mazeArray2);
		System.out.println();
		
		//SHORTEST PATH
		UndirectedGraph<String> mazeGraph2 = arrayToGraph(mazeArray3);
		StackInterface<String> path = new LinkedStack<>(); 
		mazeGraph2.getShortestPath("0-1",(mazeArray.length - 2) + "-" + (mazeArray[0].length - 1) , path);
		while(!path.isEmpty())
		{
			String[] coordinates = ((String)path.pop()).split("-");
			int x = Integer.parseInt(coordinates[0]);
			int y = Integer.parseInt(coordinates[1]);
			mazeArray3[x][y] = '.';
		}
		displayMazeArray(mazeArray3);
		System.out.println();
		
		//CHEAPEST PATH
		UndirectedGraph<String> mazeGraph3 = arrayToGraph(mazeArray4);
		StackInterface<String> path2 = new LinkedStack<>(); 
		mazeGraph3.getCheapestPath("0-1",(mazeArray.length - 2) + "-" + (mazeArray[0].length - 1) , path2);
		while(!path2.isEmpty())
		{
			String[] coordinates = ((String)path2.pop()).split("-");
			int x = Integer.parseInt(coordinates[0]);
			int y = Integer.parseInt(coordinates[1]);
			mazeArray4[x][y] = '.';
		}
		displayMazeArray(mazeArray4);
		System.out.println();
	}
			
		
		
	//TAKES MAZE AS ARRAY AND RETURNS MAZE AS GRAPH
	public static UndirectedGraph<String> arrayToGraph(char[][] mazeArray)
	{
		UndirectedGraph<String> mazeGraph = new UndirectedGraph<String>();
		Random random = new Random();
		for (int i = 0; i < mazeArray.length; i++) 
		{
			for (int j = 0; j < mazeArray[0].length; j++) 
			{
				if (mazeArray[i][j] == ' ') 
				{
					mazeGraph.addVertex(i + "-" + j);
				}
			}
		}
		for (int i = 0; i < mazeArray.length; i++) 
		{
			for (int j = 0; j < mazeArray[0].length; j++) 
			{
				if (mazeArray[i][j] == ' ') 
				{
					int randomWeight = 0;
					if(i == mazeArray.length - 1 && j == mazeArray[0].length - 1)
					{
						
					}
					else if (j == mazeArray[0].length - 1)
					{
						if(mazeArray[i+1][j] == ' ')
						{
							randomWeight = random.nextInt(1,5);
							mazeGraph.addEdge(i + "-" + j , (i+1) + "-" + j , randomWeight);
						}
					}
					else if(i == mazeArray.length - 1)
					{
						if(mazeArray[i][j+1] == ' ')
						{
							randomWeight = random.nextInt(1,5);
							mazeGraph.addEdge(i + "-" + j , i + "-" + (j+1) , randomWeight);
						}
					}
					else
					{
						if(mazeArray[i][j+1] == ' ')
						{
							randomWeight = random.nextInt(1,5);
							mazeGraph.addEdge(i + "-" + j , i + "-" + (j+1) , randomWeight);
						}
						if(mazeArray[i+1][j] == ' ')
						{
							randomWeight = random.nextInt(1,5);
							mazeGraph.addEdge(i + "-" + j , (i+1) + "-" + j , randomWeight);
						}
					}
				}
				
			}
		}
		return mazeGraph;
	}
	
	//RETURNS MAZE'S LENGTH
	public static int[] mazeLength(String mazeTxt)
	{
		int[] mazeLength = new int[2];
		try {
		      File file = new File(mazeTxt);
		      Scanner fileReader = new Scanner(file);
		      
		      while (fileReader.hasNextLine()) {
		        String data = fileReader.nextLine();
		        mazeLength[0]++;
		        mazeLength[1] = data.length();
		      }
		      
		      fileReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return mazeLength;
	}
	
	//READS MAZE TXT AND RETURNS IT AS ARRAY
	public static char[][] fileReader(String mazeTxt)
	{
		int rowCount = mazeLength(mazeTxt)[0];
	    int columnCount = mazeLength(mazeTxt)[1];
	    char[][] graphArray = new char[rowCount][columnCount];
		try {
		      File file = new File(mazeTxt);
		      Scanner fileReader = new Scanner(file);
		      
		      int i = 0;
		      while (fileReader.hasNextLine()) {
		        String data = fileReader.nextLine();
		        //System.out.println(data);
		        for (int j = 0; j < data.length(); j++) 
		        {
					graphArray[i][j] = data.charAt(j);
				}
		        i++;
		      }
		      
		      fileReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return graphArray;
	}

	//DISPLAYS MAZE
	public static void displayMazeArray(char[][] mazeArr)
	{
		for (int i = 0; i < mazeArr.length; i++) {
			for (int j = 0; j < mazeArr[0].length; j++) {
				System.out.print(mazeArr[i][j]);
			}
			System.out.println();
		}
	}
}

