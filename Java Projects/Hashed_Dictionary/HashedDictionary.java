import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashedDictionary<K, V> implements DictionaryInterface<K, V> {
	private TableEntry<K, V>[] hashTable; 
	private int numberOfEntries;
	private int locationsUsed; 
	private static final int DEFAULT_SIZE = 2477; 
	private static final double MAX_LOAD_FACTOR = 0.5;

	public HashedDictionary() {
		this(DEFAULT_SIZE); 
	} 

	@SuppressWarnings("unchecked")
	public HashedDictionary(int tableSize) {
		int primeSize = getNextPrime(tableSize);
		hashTable = new TableEntry[primeSize];
		numberOfEntries = 0;
		locationsUsed = 0;
	}

	public boolean isPrime(int num) {
		boolean prime = true;
		for (int i = 2; i <= num / 2; i++) {
			if ((num % i) == 0) {
				prime = false;
				break;
			}
		}
		return prime;
	}

	public int getNextPrime(int num) {
		if (num <= 1)
            return 2;
		else if(isPrime(num))
			return num;
        boolean found = false;   
        while (!found)
        {
            num++;     
            if (isPrime(num))
                found = true;
        }     
        return num;
	}

	public V add(K key, V value) {
		V oldValue = null; 
		if (isHashTableTooFull())
			rehash();
		int index = getHashIndex(key);
		index = doubleHashing(index, key); 
		if ((hashTable[index] == null) || hashTable[index].isRemoved()) { 
			hashTable[index] = new TableEntry<K, V>(key, value);
			numberOfEntries++;
			locationsUsed++;
			oldValue = null;
		} else if (key.equals(hashTable[index].getKey()) && value.equals(hashTable[index].getValue())){ 
			oldValue = hashTable[index].getValue();
			hashTable[index].setValue(value); 
		}
		return oldValue;
	}
	
	
	public V add(K key, V value, String txt) {
		V oldValue = null; 
		if (isHashTableTooFull())
			rehash();
		int index = getHashIndex(key);
		index = doubleHashing(index, key); 
		if ((hashTable[index] == null) || hashTable[index].isRemoved()) { 
			hashTable[index] = new TableEntry<K, V>(key, value);
			hashTable[index].addTxt(txt);
			numberOfEntries++;
			locationsUsed++;
			oldValue = null;
		} else if (key.equals(hashTable[index].getKey())){ 
			oldValue = hashTable[index].getValue();
			hashTable[index].addTxt(txt);
		}
		return oldValue;
	}
	
	
	
	
	public V add(K key, V value, SingleLinkedList txt) {
		V oldValue = null; 
		if (isHashTableTooFull())
			rehash();
		int index = getHashIndex(key);
		index = doubleHashing(index, key); 
		if ((hashTable[index] == null) || hashTable[index].isRemoved()) { 
			hashTable[index] = new TableEntry<K, V>(key, value);
			
			hashTable[index].setSLL(txt);
			numberOfEntries++;
			locationsUsed++;
			oldValue = null;
		}
		return oldValue;
	}
	
	
	
	public void sortCounters()
	{
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				hashTable[i].sortTxt();
			}
		}
	}
	
	public int getSSFCode(String givenString)
	{
		int sum = 0;
		for (int i = 0; i < givenString.length(); i++) {
			sum = sum + (int)givenString.charAt(i) - 96;
		}
		return sum;
	}
	
	
	public int getPAFCode(String givenString)
	{
		long sum = 0;
		for (int i = givenString.length()-1; i >= 0; i--) {
			sum = (long) (sum + ((long)Math.pow(31, i) * ((long)givenString.charAt(givenString.length()-1 - i) - 96)));
		}
		int sum1 = (int)sum;
		return sum1;
	}
	
	
	public void mostRelevant(String word1, String word2, String word3)
	{
		FileReader a = new FileReader();
		TableEntry<K, V> word1Entry = null; TableEntry<K, V> word2Entry = null; TableEntry<K, V> word3Entry = null;
		int count1 = 0; int count2 = 0; int count3 = 0; int countTotal = 0;
		double ratio = 0; double maxRatio = 0;
		String txt = "";
		for (int i = 0; i < hashTable.length; i++) {
			if(hashTable[i] != null && hashTable[i].getKey().equals(word1)){
				word1Entry = hashTable[i];
			}
			else if (hashTable[i] != null && hashTable[i].getKey().equals(word2)) {
				word2Entry = hashTable[i];
			} 
			else if (hashTable[i] != null && hashTable[i].getKey().equals(word3)){
				word3Entry = hashTable[i];
			}
		}
		String fileName;
		for (int j = 1; j < 512 ; j++) {
			if (j < 10)
				fileName = "00" + j + ".txt";
			else if (10 <= j && j < 100)
				fileName = "0" + j + ".txt";
			else
				fileName = "" + j + ".txt";
			
			if (word1Entry != null && word1Entry.getSLL().search(fileName)) {
				count1 = word1Entry.wordCountOfGivenTxt(fileName);
			}
			else
				count1 = 0;
			if (word2Entry != null && word2Entry.getSLL().search(fileName)) {
				count2 = word2Entry.wordCountOfGivenTxt(fileName);
			}
			else
				count2 = 0;
			if (word3Entry != null && word3Entry.getSLL().search(fileName)) {
				count3 = word3Entry.wordCountOfGivenTxt(fileName);
			}
			else
				count3 = 0;
			
			countTotal = count1 + count2 + count3;
			ratio = (double)countTotal / a.getTxtLength(fileName);
			System.out.println("Ratio in " + fileName + " is equal to: " + ratio);
			if (ratio > maxRatio)
			{
				maxRatio = ratio;
				txt = fileName;
			}
		}
		System.out.println();
		System.out.println("According to Ratio, Most Relevant Txt is " + txt + " with Maximum Ratio of " + maxRatio);
	}

	private int getHashIndex(K key) {
		int hashIndex = getPAFCode((String)key) % hashTable.length;
		if (hashIndex < 0)
			hashIndex = hashIndex + hashTable.length;
		return hashIndex;
	}
	
	public boolean isHashTableTooFull() {
		double load_factor = (double)locationsUsed / hashTable.length;
		if (load_factor >= MAX_LOAD_FACTOR)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public void rehash() {
		TableEntry<K, V>[] oldTable = hashTable;
		int oldSize = hashTable.length;
		int newSize = getNextPrime(2 * oldSize);
		hashTable = new TableEntry[newSize];
		numberOfEntries = 0; 
		locationsUsed = 0;

		for (int index = 0; index < oldSize; index++) {
			if(oldTable[index] != null && oldTable[index].isIn())
				add(oldTable[index].getKey(), oldTable[index].getValue(), oldTable[index].getSLL());
		}
	}

	private int probe(int index, K key) {
		boolean found = false;
		int removedStateIndex = -1; 
		while (!found && (hashTable[index] != null)) {
			if (hashTable[index].isIn()) {
				if (key.equals(hashTable[index].getKey()))
					found = true; 
				else 
					index = (index + 1) % hashTable.length; 
			} 
			else 
			{
				if (removedStateIndex == -1)
					removedStateIndex = index;
				index = (index + 1) % hashTable.length; 
			} 
		} 
		if (found || (removedStateIndex == -1))
			return index; 
		else
			return removedStateIndex; 
	}
	
	private int doubleHashing(int index, K key) {
		boolean found = false;
		int removedStateIndex = -1; 
		int i = 1;
		int originIndex = index;
		while (!found && (hashTable[index] != null)) {
			if (hashTable[index].isIn()) {
				if (key.equals(hashTable[index].getKey()))
					found = true; 
				else 
				{
					index = originIndex;
					index = ((getPAFCode((String)key) % hashTable.length) + i * (31 - (getPAFCode((String)key) % 31))) % hashTable.length;
					if (index < 0)
						index = index + hashTable.length;
				}
				
			} 
			else 
			{
				if (removedStateIndex == -1)
					removedStateIndex = index;
				index = originIndex;
				index = ((getPAFCode((String)key) % hashTable.length) + i * (31 - (getPAFCode((String)key) % 31))) % hashTable.length;
				if (index < 0)
					index = index + hashTable.length;
			} 
			i++;
		} 
		if (found || (removedStateIndex == -1))
			return index; 
		else
			return removedStateIndex; 
	}
	
	private int[] locateDoubleHashing(int index, K key) {
		boolean found = false;
		int[] results = new int[2];
		results[1] = 0;
		int i = 1;
		int originIndex = index;
		while (!found && (hashTable[index] != null)) {
			if (hashTable[index].isIn() && key.equals(hashTable[index].getKey()))
				found = true; 
			else 
			{
				index = originIndex;
				index = ((getPAFCode((String)key) % hashTable.length) + i * (31 - (getPAFCode((String)key) % 31))) % hashTable.length;
				
				if (index < 0)
					index = index + hashTable.length;
				results[1]++;
				i++;
			}
			
				
		} 
		results[0] = -1;
		if (found)
			results[0] = index;
		return results;
	}
	
	public int searchForCollisionsDH(K key) {
		int index = getHashIndex(key);
		int [] locateArray = locateDoubleHashing(index, key);
		int collisionCount = locateArray[1];
		index = locateArray[0];
		return collisionCount;
	}
	
	public void displayTable()
	{
		for (int i = 0; i < hashTable.length; i++) 
		{
			if(hashTable[i] != null)
			{
				
				hashTable[i].displayTxt();
				System.out.println(hashTable[i].getKey() + "      " + i);
			}
			
		}
	}
	
	public void findWord(String word)
	{
		for (int i = 0; i < hashTable.length; i++) 
		{
			if(hashTable[i] != null && hashTable[i].getKey().equals(word))
			{
				
				hashTable[i].displayTxt();
				System.out.println(hashTable[i].getKey() + "      Total Count is :  " + hashTable[i].getTotalCount() + "     ");
			}
			
		}
	}
	
	public V remove(K key) {
		V removedValue = null;
		int hashIndex = getHashIndex(key);
		int index = locate(hashIndex, key)[0];
		if (index != -1) { 
			removedValue = hashTable[index].getValue();
			hashTable[index].setToRemoved();
			numberOfEntries--;
		} 
		return removedValue;
	}

	private int[] locate(int index, K key) {
		boolean found = false;
		int[] results = new int[2];
		results[1] = 0;
		while (!found && (hashTable[index] != null)) {
			if (hashTable[index].isIn() && key.equals(hashTable[index].getKey()))
				found = true; 
			else 
			{
				index = (index + 1) % hashTable.length; 
				results[1]++;
			}
				
		} 
		results[0] = -1;
		if (found)
			results[0] = index;
		return results;
	}

	public V getValue(K key) {
		V result = null;
		int index = getHashIndex(key);
		index = locate(index, key)[0];
		if (index != -1)
			result = hashTable[index].getValue(); 
		return result;
	}

	public boolean contains(K key) {
		int index = getHashIndex(key);
		index = locate(index, key)[0];
		if (index != -1)
			return true;
		return false;
	}
	
	public int searchForCollisions(K key) {
		int index = getHashIndex(key);
		int [] locateArray = locate(index, key);
		int collisionCount = locateArray[1];
		index = locateArray[0];
		return collisionCount;
	}

	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	public int getSize() {
		return numberOfEntries;
	}

	public void clear() {
		while(getKeyIterator().hasNext()) {
			remove(getKeyIterator().next());		
		}
	}
	
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	private class TableEntry<S, T> {
		private S key;
		private T value;
		private boolean inTable;
		
		SingleLinkedList sll;//

		private TableEntry(S key, T value) {//
			this.key = key;
			this.value = value;
			inTable = true;
			sll = new SingleLinkedList();//
		}

		private S getKey() {
			return key;
		}

		private T getValue() {
			return value;
		}

		private void setValue(T value) {
			this.value = value;
		}

		private boolean isRemoved() {
			return inTable == false;
		}

		private void setToRemoved() {
			inTable = false;
		}

		private void setToIn() {
			inTable = true;
		}

		private boolean isIn() {
			return inTable == true;
		}
		
		private void addTxt(String txt)
		{
			sll.add(txt);//
		}
		
		private void displayTxt()
		{
			sll.display();
		}
		
		private void sortTxt()
		{
			sll = sll.sort();
		}
		
		private void setSLL(SingleLinkedList newSLL)
		{
			sll = newSLL;
		}
		
		private SingleLinkedList getSLL()
		{
			return sll;
		}
		private int getTotalCount()
		{
			return sll.totalCount();
		}
		
		private int wordCountOfGivenTxt(String givenTxt)
		{
			return sll.findCount(givenTxt);
		}
		
		
	}

	private class KeyIterator implements Iterator<K> {
		private int currentIndex; 
		private int numberLeft; 

		private KeyIterator() {
			currentIndex = 0;
			numberLeft = numberOfEntries;
		} 

		public boolean hasNext() {
			return numberLeft > 0;
		} 

		public K next() {
			K result = null;
			if (hasNext()) {
				while ((hashTable[currentIndex] == null) || hashTable[currentIndex].isRemoved()) {
					currentIndex++;
				} 
				result = hashTable[currentIndex].getKey();
				numberLeft--;
				currentIndex++;
			} else
				throw new NoSuchElementException();
			return result;
		} 

		public void remove() {
			throw new UnsupportedOperationException();
		} 
	}
	
	private class ValueIterator implements Iterator<V> {
		private int currentIndex; 
		private int numberLeft; 

		private ValueIterator() {
			currentIndex = 0;
			numberLeft = numberOfEntries;
		} 

		public boolean hasNext() {
			return numberLeft > 0;
		} 

		public V next() {
			V result = null;
			if (hasNext()) {
				while ((hashTable[currentIndex] == null) || hashTable[currentIndex].isRemoved()) {
					currentIndex++;
				} 
				result = hashTable[currentIndex].getValue();
				numberLeft--;
				currentIndex++;
			} else
				throw new NoSuchElementException();
			return result;
		} 

		public void remove() {
			throw new UnsupportedOperationException();
		} 
	}
}
