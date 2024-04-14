public class Node {
	String txt;
	int count;
	Node link; 
	private static final int COUNT = 1; 

	public Node(String txtToAdd) {
	   this(txtToAdd, COUNT);
	}
	public Node(String txtToAdd, int counterToAdd) {
		txt = txtToAdd;
		count = counterToAdd;
		link = null;
	}
	
	public String getTxt() { return txt; }
	public void setTxt(String data) { txt = data;  }
	public int getCount() { return count; }
	public void setCount(int data) { count = data;  }
	public Node getLink() { return link;  }
	public void setLink(Node link) { this.link = link;   }  
}
