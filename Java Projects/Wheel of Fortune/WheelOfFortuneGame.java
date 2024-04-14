package firstAlgoHomework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class WheelOfFortuneGame {
	//ATTRIBUTES
	Stack S1;
	Stack S2;
	Stack S3;
	Stack S4;
	Stack wheel;
	Stack wheelTemp;
	Stack tempS;
	Stack tempS1;
	Stack tempS2;
	Stack tempS3;
	Stack tempS4;
	Stack unsortedHS;
	Stack sortedHS;
	Queue Q1;
	Queue Q2;
	Queue tempQ1;
	Queue tempQ2;
	Random random1;
	int random2;
	int score;
	int stepCounter;
	int hyfenCounter;
	int trueLetterCounter;
	int tempHighScoreSub;
	String temp;
	String tempCountries;
	String randomWheel;
	String tempHighScore;
	char tempQ1Char;
	char tempQ2Char;
	char tempLetter;
	char randomLetter;
	boolean flag;
	boolean flag2;
	boolean trueOrFalse;
	
	//CONSTRUCTOR
	WheelOfFortuneGame()
	{
		//CREATING WHEEL
		wheel = new Stack(8);
		wheel.push("10");
		wheel.push("50");
		wheel.push("100");
		wheel.push("250");
		wheel.push("500");
		wheel.push("1000");
		wheel.push("Double Money");
		wheel.push("Bankrupt");
		
		//READING COUNTRY TXT AND FILLING S1 (Country Stack)
		S1 = (Stack) fileReader("D:\\countries.txt", "stack");
		
		//SORTING S1 USING A TEMPORARY STACK (tempS)
		tempS = new Stack(S1.size());
		while(!(S1.isEmpty()))
		{
			temp = (String) S1.pop();
			while((!(tempS.isEmpty())) && (((String)tempS.peek()).compareTo(temp) > 0))
			{
				S1.push(tempS.pop());
			}
			tempS.push(temp);
		}
		while(!(tempS.isEmpty()))
		{
			S1.push(tempS.pop());
		}
		
		// READING HIGHSCORE TABLE
		unsortedHS = (Stack) fileReader("D:\\HighScoreTable.txt", "stack");
		
		// SORTING HIGHSCORES
		sortedHS = new Stack(unsortedHS.size());
		while(!(unsortedHS.isEmpty()))
		{
			tempHighScore = (String) unsortedHS.pop();
			tempHighScoreSub = Integer.parseInt(tempHighScore.substring(tempHighScore.indexOf(' ')+1));
			while((!(sortedHS.isEmpty())) && Integer.parseInt(((String)sortedHS.peek()).substring(((String)sortedHS.peek()).indexOf(" ")+1)) > tempHighScoreSub)
			{
				unsortedHS.push(sortedHS.pop());
			}
			sortedHS.push(tempHighScore);
		}
		
		//ARRANGING INCREASING OR DECREASING
		while(!sortedHS.isEmpty())
		{
			unsortedHS.push(sortedHS.pop());
		}
		
		//CREATING AND FILLING REAL HIGHSCORE STACKS
		S3 = new Stack(unsortedHS.size());
		S4 = new Stack(unsortedHS.size());
		while(!unsortedHS.isEmpty())
		{
			tempHighScore = (String) unsortedHS.pop();
			S3.push(tempHighScore.substring(0,tempHighScore.indexOf(' ')));
			S4.push(tempHighScore.substring(tempHighScore.indexOf(' ')+1));
		}
		
		//FILLING S2 (ALPHABET STACK) WITH LETTERS
		S2 = new Stack(26);
		for(int i = 122; i>96; i--)
		{
			S2.push((char)i);
		}

		//RANDOMLY SELECTING A COUNTRY
		random1 = new Random();
		random2 = random1.nextInt(1,S1.size()+1);
		System.out.println("Randomly generated number: " + random2);
		tempS1 = new Stack(random2);
		for(int i=1; i<=random2; i++)
		{
			if(i==random2)
			{
				tempCountries = (String)S1.pop();
				tempS1.push(tempCountries);
			}
			else
				tempS1.push(S1.pop());
		}
		tempCountries = tempCountries.toLowerCase();
		
		//RE-ASSEMBLY COUNTRY STACK
		while(!(tempS1.isEmpty()))
		{
			S1.push(tempS1.pop());
		}
		
		//ADDING RANDOMLY SELECTED COUNTRY IN Q1
		Q1 = new Queue(tempCountries.length());
		for(int i=0; i<tempCountries.length(); i++)
		{
			Q1.enqueue(tempCountries.charAt(i));
		}

		
		Q2 = new Queue(tempCountries.length());
		score = 0;
		stepCounter = 0;
		flag = true;
		//GAME STARTS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		do
		{
			stepCounter++;
			
			// SPINNING THE WHEEL
			random2 = random1.nextInt(1,9);
			wheelTemp = new Stack(random2);
			for(int i=1; i<=random2; i++)
			{
				if(i==random2)
				{
					randomWheel = (String) wheel.pop();
					wheelTemp.push(randomWheel);
				}
				else
					wheelTemp.push(wheel.pop());
			}
			if(randomWheel.equals("Bankrupt"))
				score = 0;
			
			//CHOOSING THE LETTER
			random2 = random1.nextInt(1,(28-stepCounter));
			tempS2 = new Stack(random2);
			
			for(int i=1; i<=random2; i++)
			{
				if(i==random2)
				{
					randomLetter = (char) S2.pop();
				}
				else
					tempS2.push(S2.pop());
			}
			
			while(!(tempS2.isEmpty()))
			{
				S2.push(tempS2.pop());
			}
			
			//FILLING Q2
			while(!Q2.isFull())
			{
				Q2.enqueue('-');
			}
			
			trueLetterCounter = 0;
			trueOrFalse = false;
			tempQ1 = new Queue(Q1.size());
			tempQ2 = new Queue(Q2.size());
			while(!(Q1.isEmpty()))
			{
				tempQ1Char = (char)Q1.dequeue();
				tempQ2Char = (char)Q2.dequeue();
				tempQ1.enqueue(tempQ1Char);
				tempQ2.enqueue(tempQ2Char);
				//CHECKING IF THE CHOSEN LETTER EXISTS IN THE RANDOM WORD
				if(tempQ1Char == randomLetter)
				{
					Q2.enqueue(tempQ1Char);
					trueOrFalse = true;
					trueLetterCounter++;
				}
				//WHEN A LETTER IS ALREADY FOUND 
				else if(tempQ2Char != '-')
				{
					Q2.enqueue(tempQ1Char);
				}
				//IF COUNTRY WORD CONTAINS COMMA, DOT ETC. REPLACING THEM WITH SPACE IN ORDER TO AVOID PROBLEMS.
				else if(tempQ1Char == ' ' || tempQ1Char == ',' || tempQ1Char == '.' || tempQ1Char == '(' || tempQ1Char == ')' || tempQ1Char == '&')
				{
					Q2.enqueue(' ');
				}
				//IF LETTER DID'T FOUND
				else
				{
					Q2.enqueue('-');
				}
					
			}
			
			while(!tempQ1.isEmpty())
			{
				Q1.enqueue(tempQ1.dequeue());
			}
			
			//CHANGING SCORE IF ANSWER IS TRUE
			if(trueOrFalse)
			{
				trueOrFalse = false;
				
				if(randomWheel.equalsIgnoreCase("Double Money"))
					score = score*2;
					
				for(int i = 0; i<trueLetterCounter; i++)
				{
					switch (randomWheel)
					{
					case "10":
						score+=10;
						break;
					case "50":
						score+=50;
						break;
					case "100":
						score+=100;
						break;
					case "250":
						score+=250;
						break;
					case "500":
						score+=500;
						break;
					case "1000":
						score+=1000;
						break;
					}
				}
				
			}
			
			
			while(!(wheelTemp.isEmpty()))
			{
				wheel.push(wheelTemp.pop());
			}
			
			//PRINTING THE SCREEN
			System.out.print("Word:  ");
			
			//PRINTING Q2
			tempQ2 = new Queue(Q2.size());
			while(!Q2.isEmpty())
			{
				tempQ2Char = (char)Q2.dequeue();
				tempQ2.enqueue(tempQ2Char);
				System.out.print(tempQ2Char);
				
			}
			
			Q2 = new Queue(tempCountries.length());
			while(!tempQ2.isEmpty())
			{
				Q2.enqueue(tempQ2.dequeue());
			}
			
			System.out.print("		Step: " + stepCounter);
			System.out.print("		Score: " + score + "		");
			
			//PRINTING REMAINING LETTERS
			tempS2 = new Stack(S2.size());
			while(!S2.isEmpty())
			{
				tempLetter = (char)S2.pop();
				tempS2.push(tempLetter);
				System.out.print(tempLetter);
			}
			while(!tempS2.isEmpty())
			{
				S2.push(tempS2.pop());
			}
			System.out.println();
			
			System.out.println("Wheel: " + randomWheel);
			System.out.println("Guess: " + randomLetter);
			
			//IF THERE IS NOT ANY HYFEN THAT MEANS ALL LETTERS HAVE BEEN FOUND. WE NEED TO FINISH THE GAME.
			hyfenCounter = 0;
			tempQ2 = new Queue(Q2.size());
			while(!Q2.isEmpty())
			{
				tempQ2Char = (char)Q2.dequeue();
				tempQ2.enqueue(tempQ2Char);
				if(tempQ2Char == '-')
				{
					hyfenCounter++;
				}
			}
			while(!tempQ2.isEmpty())
			{
				Q2.enqueue(tempQ2.dequeue());
			}
			if(hyfenCounter == 0)
			{
				System.out.println("You win €" + score + " !!!");
				flag = false;
			}
			else
				flag = true;
				
			
		}while(flag);
		
		
		//EDITING HIGHSCORE TABLE DUE TO NEW SCORE
		tempS4 = new Stack(S4.size());
		tempS3 = new Stack(S3.size());
		flag2 = false;
		while(!(S4.isEmpty()))
		{
			tempHighScore = (String) S3.pop();
			tempHighScoreSub = Integer.parseInt((String) S4.pop());
			if(S3.isEmpty())
			{
				if(score<tempHighScoreSub)
				{
					tempS4.push(tempHighScoreSub);
					tempS3.push(tempHighScore);
				}
			}
			else if((score>=tempHighScoreSub) && flag2==false)
			{
				tempS4.push(score);
				tempS3.push("You");
				tempS4.push(tempHighScoreSub);
				tempS3.push(tempHighScore);
				flag2=true;
			}
			
			else
			{
				tempS4.push(tempHighScoreSub);
				tempS3.push(tempHighScore);
			}
			
		}
		
		while(!(tempS4.isEmpty()))
		{
			S3.push(tempS3.pop());
			S4.push(tempS4.pop());
		}
		
		System.out.println();
		System.out.println("Highscore Table");
		
		while(!(S4.isEmpty()))
		{
			tempHighScore = (String)S3.pop();
	    	tempHighScoreSub = (int) S4.pop();
	    	tempS3.push(tempHighScore);
	    	tempS4.push(tempHighScoreSub);
	    	System.out.println(tempHighScore + " " + tempHighScoreSub);
		}
		
		
		while(!(tempS4.isEmpty()))
		{
			S3.push(tempS3.pop());
			S4.push(tempS4.pop());
		}
		
		// WRITING HIGHSCORES TO TXT.
			try {
			      FileWriter file1 = new FileWriter("D:\\HighScoreTable.txt");
			      while(!S3.isEmpty())
			      {
			    	  tempHighScore = (String)S3.pop();
			    	  tempHighScoreSub = (int) S4.pop();
			    	  if(S4.isEmpty())
			    		  file1.write(tempHighScore + " " + tempHighScoreSub);
			    	  else
			    		  file1.write(tempHighScore + " " + tempHighScoreSub + '\n');
			      }
			      file1.close();
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		
	}
	
	
	//I WROTE A METHOD FOR READING FILES. THIS METHOD READS THE FILE AND INTEGRATES THAT FILE TO A QUEUE...
	//...OR A STACK AND RETURNS THAT QUEUE/STACK. ALTHOUGH I DIDN'T USE QUEUE PART IN THIS METHOD I BELIEVE IT WORKS.
	public Object fileReader(String txt, String type)
	{
		Object object1 = new Object();
		Stack stack1 = new Stack(0);
		Queue queue1 = new Queue(0);
		try {
			  
			  int lineCounter = 0;  
		      File theFile = new File(txt);
		      Scanner myReaderLineCounter = new Scanner(theFile);
		      Scanner myReader = new Scanner(theFile);
		      
		      //Counting the Lines.
		      while (myReaderLineCounter.hasNextLine()) 
		      {
		    	myReaderLineCounter.nextLine();
		        lineCounter++;
		      }
		      
		    //Implementing the Data to the Queue/Stack
		      if(type.equalsIgnoreCase("stack"))
		      {
		    	  stack1 = new Stack(lineCounter);
		    	  while (myReader.hasNextLine()) 
			      {
			    	  stack1.push(myReader.nextLine());
				  }
		    	  object1 = stack1;
		      }
		    	  
		      else if(type.equalsIgnoreCase("queue"))
		      {
		    	  queue1 = new Queue(lineCounter);
		    	  while (myReader.hasNextLine()) 
			      {
			    	  queue1.enqueue(myReader.nextLine());
				  }
		    	  object1 = queue1;
		      }
		    	  
		      myReaderLineCounter.close();
		      myReader.close();
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return object1;
	}
	
}
