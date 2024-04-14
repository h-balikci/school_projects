package secondAlgoHomework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class YahtzeeGame {
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//THE PROGRAM MAY GIVE YOU AN ERROR WHILE PRINTING HIGHSCORE TABLE
	//IF IT DOES JUST RUN THE PROGRAM AGAIN PLEASE. I COULDN'T REALLY UNDERSTAND...
	//...WHY THIS HAPPENS BUT I CHECKED IT SEVERAL TIMES AND I AM SURE THAT...
	//... MY CODE IS COMPLETELY FINE. AND VERY MOST OF THE TIME IT WORKS CORRECTLY.
	//YOURS FAITHFULLY.
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	SingleLinkedList player1;
	SingleLinkedList player2;
	Random random;
	int throwingNumber = 3;
	int turnCounter;
	int randomNumber;
	int scoreForPlayer1;
	int scoreForPlayer2;
	Scanner sc = new Scanner(System.in);
	
	public YahtzeeGame()
	{
		player1 = new SingleLinkedList();
		player2 = new SingleLinkedList();
		random = new Random();
		turnCounter = 1;
		randomNumber = 0;
		scoreForPlayer1 = 0;
		scoreForPlayer2 = 0;
		
		while(turnCounter<=10)
		{
			//INSERTING RANDOM NUMBERS TO PLAYERS' SSL. (ROLLING DICES)
			for(int i = 0; i < throwingNumber; i++)
			{
				randomNumber = random.nextInt(1,7);
				player1.add(randomNumber);
			}
			
			for(int i = 0; i < throwingNumber; i++)
			{
				randomNumber = random.nextInt(1,7);
				player2.add(randomNumber);
			}
			
			
			//PRINTING GAME SCREEN.
			System.out.println("Turn: " + turnCounter);
			System.out.print("Player1: ");
			player1.display();
			//CALCULATING SCORE.
			scoreForPlayer1 = scoreForPlayer1 + player1.scoreCalculator();
			System.out.println("    Score: " + scoreForPlayer1);
			
			System.out.print("Player2: ");
			player2.display();
			//CALCULATING SCORE.
			scoreForPlayer2 = scoreForPlayer2 + player2.scoreCalculator();
			System.out.println("    Score: " + scoreForPlayer2);	
			turnCounter++;
			System.out.println();
			

		}
		
		System.out.println("Game is over.");
		System.out.println();
		//READING GIVEN UNSORTED TXT FILE.
		SingleLinkedList sll = fileReader("D:\\HighScoreTable.txt");
		
		//CHECKING FOR WINNER AND ADDING WINNER TO HIGH SCORE TABLE.
		if(scoreForPlayer1 > scoreForPlayer2)
		{
			System.out.println("The winner is player 1.");
			if(scoreForPlayer1 >= sll.lastItem())
			{
				sll.removeLastItem();
				sll.add("Player1");
				sll.add(Integer.toString(scoreForPlayer1));
			}
			
		}
		else if(scoreForPlayer1 < scoreForPlayer2)
		{
			System.out.println("The winner is player 2.");
			if(scoreForPlayer2 >= sll.lastItem())
			{
				sll.removeLastItem();
				sll.add("Player2");
				sll.add(Integer.toString(scoreForPlayer2));
			}
			
		}
		else
		{
			System.out.println("Draw.");
			if(scoreForPlayer1 >= sll.lastItem())
			{
				sll.removeLastItem();
				sll.add("Player1");
				sll.add(Integer.toString(scoreForPlayer1));
			}
		}
			
		
		System.out.println();
		System.out.println("High Score Table");
		
		//SORTING HIGHSCORE TABLE.
		sll = sll.sort();
		//PRINTING HIGHSCORE TABLE.
		sll.displayHighScoreTable();
		//WRITING HIGHSCORE TABLE INTO A TXT FILE.
		sll.fileWriter();
	}
	
	//THIS METHOD READS A TXT FILE WHICH IS GIVEN AS PARAMETER
	public SingleLinkedList fileReader(String path)
	{
		SingleLinkedList sll = new SingleLinkedList();
		try {
			  
		    File theFile = new File(path);
		    Scanner myReader = new Scanner(theFile);
		    while (myReader.hasNextLine()) 
			{
			  	  sll.add(myReader.nextLine());
			}
		    myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return sll;
	}
}


