
public class SingleLinkedList {
	Node head;
	private static final int COUNT = 1; 
	
	
	public void add(String data)
	{
		add(data, COUNT);
	}
	
	public void add(String data, int count1)
	{
		if(head==null)
		{
			Node newNode = new Node(data, count1);
			head = newNode;
		}
		else
		{
			Node temp = head;
			boolean flag = true;
			
			while(temp != null)
			{
				if (temp.getTxt() == data) {
					temp.setCount(temp.getCount() + 1);
					flag = false;
				}
				temp = temp.getLink();
			}
			
			if (flag) {
				temp = head;
				while(temp.getLink() != null)
				{
					temp = temp.getLink();
				}
				Node newNode = new Node(data, count1);
				temp.setLink(newNode);
			}
			
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
				System.out.print(temp.getTxt() + "  ----->  " + temp.getCount() + "      ");
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

	public boolean search(String data)
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
				if(temp.getTxt().equals(data))
				{
					return true;
				}
				temp = temp.getLink();
			}
			return false;
		}
	}

	public void removeJustOne(String txt)
	{
		if(head == null)
		{
			System.out.println("Linked List is Empty.");
		}
		else
		{
			boolean flag = true;
			if(txt.equals(head.getTxt()))
			{
				head = head.getLink();
				flag = false;
			}
			Node temp = head;
			Node previous = null;
			while(temp != null && flag)
			{
				if(txt.equals(temp.getTxt()))
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
	
	public SingleLinkedList sort()
	{
		Node temp = head;
		SingleLinkedList sll = new SingleLinkedList();
		
		while(size() != 0)
		{
			Node score = temp;
			Node score2 = head;
			int counter = 0;
			while(score2 != null)
			{
				if(score2.getCount() <= score.getCount())
				{
					counter++;
				}
				if(score2 != null)
				{
					score2 = score2.getLink(); 
				}
			}
			
			temp = temp.getLink();
			
			if(counter == (size()))
			{
				removeJustOne(score.getTxt());
				sll.add(score.getTxt(), score.getCount());
				temp = head;
			}
		}

	return sll;
	}
	
	public int totalCount()
	{
		Node temp = head;
		int totalCount = 0;
		while(temp != null)
		{
			totalCount = totalCount + temp.getCount();
			temp = temp.getLink();
		}
		return totalCount;
		
	}
	
	public int findCount(String data)
	{
		if(head == null)
		{
			System.out.println("Linked List is Empty");
			return 0;
		}
		else
		{
			Node temp = head;
			while(temp != null)
			{
				if(temp.getTxt().equals(data))
				{
					return temp.getCount();
				}
				temp = temp.getLink();
			}
			return 0;
		}
	}
	
	public void removeLastItem()
	{
		Node temp = head;
		while(temp.getLink().getLink().getLink() != null)
		{
			temp = temp.getLink();
		}
		
		temp.setLink(null);
	}
	

}
			