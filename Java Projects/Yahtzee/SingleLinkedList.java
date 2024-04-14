package secondAlgoHomework;

import java.io.FileWriter;
import java.io.IOException;

public class SingleLinkedList {
	Node head;
	public void add(Object data)
	{
		if(head==null)
		{
			Node newNode = new Node(data);
			head = newNode;
		}
		else
		{
			Node temp = head;
			while(temp.getLink() != null)
			{
				temp = temp.getLink();
			}
			Node newNode = new Node(data);
			temp.setLink(newNode);
		}
	}
	
	public void display()
	{
		if(head == null)
		{
			System.out.println("Linked List is Empty.");
		}
		else
		{
			Node temp = head;
			while(temp != null)
			{
				System.out.print(temp.getData() + " ");
				temp = temp.getLink();	
			}
		}
	}
	
	public int size()
	{
		if(head == null)
		{
			return 0;
		}
		else
		{
			int count = 0;
			Node temp = head;
			while(temp != null)
			{
				temp = temp.getLink();
				count++;
			}
			return count;
		}
	}
	//REMOVE ALL OF THE GIVEN DATA FROM SLL.
	public void removeAll(Object data)
	{
		if(head == null)
		{
			System.out.println("Linked List is Empty.");
		}
		else
		{
			while(data.equals(head.getData()))
			{
				head = head.getLink();
				if (head == null)
					break;
			}
			Node temp = head;
			Node previous = null;
			while(temp != null)
			{
				if(data.equals(temp.getData()))
				{
					previous.setLink(temp.getLink());
					temp = previous;
				}
				previous = temp;
				temp=temp.getLink();
			}
		}
	}
	//REMOVE ONE OF THE GIVEN DATA FROM SLL.
	public void removeJustOne(Object data)
	{
		if(head == null)
		{
			System.out.println("Linked List is Empty.");
		}
		else
		{
			boolean flag = true;
			if(data.equals(head.getData()))
			{
				head = head.getLink();
				flag = false;
			}
			Node temp = head;
			Node previous = null;
			while(temp != null && flag)
			{
				if(data.equals(temp.getData()))
				{
					previous.setLink(temp.getLink());
					temp = previous;
					break;
				}
				previous = temp;
				temp=temp.getLink();
			}
		}
	}

	public boolean search(Object data)
	{
		if(head == null)
		{
			System.out.println("Linked List is Empty");
			return false;
		}
		else
		{
			Node temp = head;
			while(temp != null)
			{
				if(temp.getData().equals(data))
				{
					return true;
				}
				temp = temp.getLink();
			}
			return false;
		}
	}
	
	public void displayHighScoreTable()
	{
		if(head == null)
		{
			System.out.println("Linked List is Empty.");
		}
		else
		{
			Node temp = head;
			while(temp != null)
			{
				System.out.println(temp.getData() + " " + (temp.getLink()).getData());
				for(int i = 0; i<2; i++)
					temp = temp.getLink();	
			}
		}
	}

	public int scoreCalculator()
	{
		int score = 0;
		if(head==null)
		{
			System.out.println("There is no element.");
		}
		else
		{
			Node temp = head;
			Node tempNext;
			int elementCounter = 1;
			//COUNTING SAME ELEMENTS.
			while(temp != null)
			{
				tempNext = temp.getLink();
				while(tempNext != null)
				{
					if(tempNext.getData().equals(temp.getData()))
					{
						elementCounter++;
					}
					tempNext = tempNext.getLink();
				}
				//IF THERE IS A DATA WHICH REPEATS AT LEAST 4 TIME, DELETE 4 OF THEM AND UPDATE SCORE.
				if(elementCounter >= 4)
				{
					for(int i = 0; i<4; i++)
						removeJustOne(temp.getData());
					score = score + 10;
				}
				elementCounter = 1;
				temp = temp.getLink();
			}
			
			//IF THERE ARE 6 CONSECUTIVE NUMBERS DELETE THEM AND UPDATE SCORE.
			while(search(1) && search(2) && search(3) && search(4) && search(5) && search(6))
			{
				removeJustOne(1);
				removeJustOne(2);
				removeJustOne(3);
				removeJustOne(4);
				removeJustOne(5);
				removeJustOne(6);
				score = score + 30;
			}
			
		}
		return score;
	}	

	//THE SORT METHOD WORKS AS FOLLOWS: THE METHOD FIRST FINDS MAX SCORE AMONG ALL SCORES AND THEN...
	//...ADDS MAX SCORE TO ANOTHER SLL AND REMOVES FROM CURRENT SLL. WHILE CURRENT SSL IS NOT EMPTY...
	//... THE METHOD REPEATS THIS ALGORITHM. AT LAST THE METHOD RETURNS SORTED SSL (HIGHSCORE).
	public SingleLinkedList sort()
	{
		Node temp = head;
		SingleLinkedList sll = new SingleLinkedList();
		
		while(size() != 0)
		{
			
			Node score = temp.getLink();
			Node name = temp;
			Node score2 = head.getLink();
			Node name2 = head;
			int counter = 0;
			
			while(score2 != null)
			{
				if(Integer.parseInt((String)score2.getData()) <= Integer.parseInt((String)score.getData()))
				{
					counter++;
					
				}
				
				for(int i = 0; i<2; i++)
				{
					if(score2 != null)
					{
						score2 = score2.getLink(); 
						name2 = name2.getLink();
					}
				}
			}
			
			for(int i = 0; i<2; i++)
				temp = temp.getLink();
			
			//IF CHOSEN SCORE IS BIGGER THAN OR EQUALS TO ALL SCORES ADD IT TO ANOTHER SINGLE LINKED LIST.
			if(counter == (size()/2))
			{
				sll.add(name.getData());
				sll.add(score.getData());
				removeJustOne(name.getData());
				removeJustOne(score.getData());
				temp = head;
			}
		}

	return sll;
	}

	//RETURNS LAST SCORE AS INTEGER.
	public int lastItem()
	{
		Node temp = head;
		while(temp.getLink() != null)
		{
			temp = temp.getLink();
		}
		return Integer.parseInt((String)temp.getData());
	}
	
	//REMOVES LAST SCORE AND NAME.
	public void removeLastItem()
	{
		Node temp = head;
		while(temp.getLink().getLink().getLink() != null)
		{
			temp = temp.getLink();
		}
		
		temp.setLink(null);
	}
	
	//WRITES HIGHSCORE TABLE INTO A TXT FILE.
	public void fileWriter()
	{
		Node temp = head;
		try {
		      FileWriter file1 = new FileWriter("D:\\HighScoreTable.txt");
		      while(temp != null)
		      {
		    	  if(temp.getLink() == null)
		    		  file1.write((String)temp.getData());
		    	  else
		    		  file1.write((String)temp.getData() + '\n');
		    	  
		    	  temp = temp.getLink();	
		    	  
		      }
		      file1.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	

}
			