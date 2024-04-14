import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;


public class Test {
	public static void main(String[] args) 
	{
		HashedDictionary<String, Integer> hashedDic;
		FileReader a = new FileReader();
		hashedDic = a.fileReader(511);
		//RECEIVING INPUTS
		Scanner scan = new Scanner(System.in);
		System.out.print("Please Enter the Words: ");
		String inputWords = scan.nextLine();
		scan.close();
		inputWords = inputWords.toLowerCase(Locale.ENGLISH);
		String[] inputArray = inputWords.split(" ");
		
		hashedDic.mostRelevant(inputArray[0], inputArray[1], inputArray[2]);
		
		// FOR COUNTING COLLUSIONS
		/*
		hashedDic.sortCounters();
		System.out.println("Size: " + hashedDic.getSize());
		hashedDic.displayTable();
		
		int collisionNumber = 0;
		try {
		      File myObj = new File("search.txt");
		      Scanner myReader = new Scanner(myObj);
		      long totalIndexTime = 0;
		      long minIndexTime = 1111111111;
		      long maxIndexTime = 0;
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        long startTime = System.nanoTime();
		        collisionNumber = collisionNumber + hashedDic.searchForCollisionsDH(data);
		        //System.out.println(hashedDic.searchForCollisionsDH(data) + "   " + data);
		        long indexTime = System.nanoTime() - startTime;
		        totalIndexTime = totalIndexTime + indexTime;
		        if (indexTime > maxIndexTime) {
					maxIndexTime = indexTime;
				}
		        if (indexTime < minIndexTime)
		        {
		        	minIndexTime = indexTime;
		        }
		      }
		      long averageIndexTime = totalIndexTime / 1000;
		      System.out.println("Average Search Time: " + averageIndexTime);
		      System.out.println("Minimum Search Time: " + minIndexTime);
		      System.out.println("Maximum Search Time: " + maxIndexTime);
		      myReader.close();
		      System.out.println("Collision: " + collisionNumber);
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		*/
	}
} 
