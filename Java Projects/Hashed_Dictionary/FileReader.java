import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class FileReader 
{  
	
	public FileReader()
	{
		
	}
	public HashedDictionary<String, Integer> fileReader(int fileCount)
	{				
		HashedDictionary<String, Integer> hashedDic = new HashedDictionary<String, Integer>();
		String fileName;
		long totalIndexTime = 0;
		for (int j = 1; j < fileCount + 1 ; j++) {
			if (j < 10)
				fileName = "00" + j + ".txt";
			else if (10 <= j && j < 100)
				fileName = "0" + j + ".txt";
			else
				fileName = "" + j + ".txt";
			try 
			{
				String[] dataSplitted;
			    File myObj = new File("C:\\Users\\Hasan\\Desktop\\sport\\" + fileName);
			    Scanner myReader = new Scanner(myObj);  
			    String data = "";
			    while (myReader.hasNextLine()) 
			    {
			      data = data + myReader.nextLine().replace('\n', ' ') + " ";
			    }
			    myReader.close();
			    data = data.toLowerCase(Locale.ENGLISH);
			    //DELETING DELIMITERS
			    for (int i = 0; i < data.length(); i++) 
			    {
			    	if (data.charAt(i)<97 || data.charAt(i) > 122)
			    	{
			    		data = data.replace(data.charAt(i), ' ');
			    	}
			    }
			    for (int i = 0; i < data.length(); i++) 
			    {
			    	data = data.replaceAll("  ", " ");
			    }
			    data = data.trim();
			    dataSplitted = data.split(" ");
			    long startTime = System.nanoTime();
			    for (int i = 0; i < dataSplitted.length; i++) {
					hashedDic.add(dataSplitted[i], hashedDic.getPAFCode(dataSplitted[i]), fileName);
				}
			    long indexTime = System.nanoTime() - startTime;
			    totalIndexTime = totalIndexTime + indexTime;
			    
			} 
			catch (FileNotFoundException e) 
			{
			    System.out.println("An error occurred.");
			    e.printStackTrace();
			} 
		}
		System.out.println("Index Time: " + totalIndexTime);
		return hashedDic;
	}
	
	//A METHOD TO GET LENGTH OF GIVEN TXT.
	public int getTxtLength(String fileName)
	{
		String[] dataSplitted = new String[0];
		try 
		{
		    File myObj = new File("C:\\Users\\Hasan\\Desktop\\sport\\" + fileName);
		    Scanner myReader = new Scanner(myObj);  
		    String data = "";
		    while (myReader.hasNextLine()) 
		    {
		      data = data + myReader.nextLine().replace('\n', ' ') + " ";
		    }
		    myReader.close();
		    data = data.toLowerCase(Locale.ENGLISH);
		    for (int i = 0; i < data.length(); i++) 
		    {
		    	if (data.charAt(i)<97 || data.charAt(i) > 122)
		    	{
		    		data = data.replace(data.charAt(i), ' ');
		    	}
		    }
		    for (int i = 0; i < data.length(); i++) 
		    {
		    	data = data.replaceAll("  ", " ");
		    }
		    data = data.trim();
		    dataSplitted = data.split(" ");
		} 
		catch (FileNotFoundException e) 
		{
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		} 
		return dataSplitted.length;
	}
    
   
}