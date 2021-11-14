package list;

@SuppressWarnings("unused")
public class LinkedList 
{	
	////////////// -Attribute- //////////////
	private ListNodeSL anchor;

	
	////////////// -Konstruktor- //////////////
	public LinkedList()
	{
		anchor = null;
	}
        
	
	////////////// -getFirst- //////////////
	public ListNodeSL getFirst() throws ListEmptyException
	{
		if(isEmpty())
			throw new ListEmptyException("Liste ist leer!");
    	
		return anchor;
	}    
    
	
	////////////// -getLast- //////////////
	public ListNodeSL getLast() throws ListEmptyException
	{
		if(isEmpty())
			throw new ListEmptyException("Liste ist leer!");
    	
		ListNodeSL current = anchor;

		while (current.getNext() != null)
		{            
			current = current.getNext();
		}
        		return current;
	}
    
    
	////////////// -getNext- //////////////
	public ListNodeSL getNext(ListNodeSL position) throws ListEmptyException
	{
		if(isEmpty())
			throw new ListEmptyException("Liste ist leer!");
    	
		return position.getNext();
	}
    
    
	////////////// -getPrevious- //////////////
	public ListNodeSL getPrevious(ListNodeSL position) throws ListEmptyException
	{
		if(isEmpty())
			throw new ListEmptyException("Liste ist leer!");
    	
		ListNodeSL current = anchor;

		while (current.getNext() != position)
		{            
			current = current.getNext();
		}
        
		return current;
	}
    
    
	////////////// -isEmpty- //////////////
	public boolean isEmpty()
	{
		return (anchor == null);
	}
    
    
	// iterative traversal from begin to end of list
	public void show1()
	{
		ListNodeSL current = anchor;

		while (current != null)
		{
			System.out.print(current.getKey() + " ");
			current = current.getNext();
		}
	}
    
    
  // recursive traversal from begin to end of list
  public void show2()
  {
		// start recursion
		show2_rec(anchor);
  }

	// class internal helper method
	private void show2_rec(ListNodeSL current)
	{
		if (current != null)
		{
			System.out.print(current.getKey() + " ");

			// recursive method invocation
			show2_rec(current.getNext());
		}
	}
        
    
	// simplest dispose: 
	// pass responsibility for entire list deletion 
	// to garbage collector
	public void dispose1()
	{
		anchor = null;
	}   
     
     
	// mistrustful dispose:
	// explicitly delete references on all list nodes
	public void dispose2()
	{
		ListNodeSL current = anchor;
    	 
		while (current != null)
		{
			current = current.getNext();
			anchor.setNext(null);
			anchor = current;             
		}
	}
     
     
	// position oriented insertion of list element
	// insert new list node with content key at front of the list 
	public void insertFirst(int key)
	{
		anchor = new ListNodeSL(key, anchor);
	}
    
    
	// position oriented insertion of list element
	// insert new list node with content key at end of the list 
	public void insertLast(int key)
	{
		ListNodeSL current = anchor;
    
		// empty list
		if (anchor == null)
			anchor = new ListNodeSL(key, anchor);
		else
		{   
			while (current.getNext() != null)
				current = current.getNext();
    
			current.setNext(new ListNodeSL(key, null));
		}
	}
      
      
	// insertion of list element in sort order 
	public void insert(int key)
	{
		ListNodeSL current = anchor;

		// empty list or first position fit
		if ((anchor == null) || (key < anchor.getKey())) 
			anchor = new ListNodeSL(key, anchor);
		else
		{   
			// advance to next element if
			// next element exists and its key is smaller
			// than or equal to key
			while ((current.getNext() != null) && (key >= current.getNext().getKey()))
				current = current.getNext();

			current.setNext(new ListNodeSL(key, current.getNext()));
		}
	}
      
      
	// iterative deletion of first element having the given key
	public void delete4(int key)
	{
		ListNodeSL previous, current = anchor;

		// list empty?
		if ( current == null )
			return;

		if ( current.getKey() == key ) 
		{
			anchor = anchor.getNext();
			return;
		}

		do 
		{
			previous = current;
			current = current.getNext();
		} while (( current != null ) && ( current.getKey() != key ));

		if ( current != null )
			previous.setNext(current.getNext());
	}
      
      
    // recursive deletion of first element having the given key
	public void delete5(int key)
	{
		if ( anchor == null ) // list empty?
			return;
		if ( anchor.getKey() == key )	 // special case: first element 
		{
			anchor = anchor.getNext();
			return;
		}
		delete5_rec(anchor, key); // start recursion
	}

	private void delete5_rec(ListNodeSL previous, int key) 
	{
		ListNodeSL current = previous.getNext();

		if (( current != null ) && ( current.getKey() != key ))
			// not found: advance recursive
			delete5_rec(current, key);

		else if ( current != null )
			// element found
			previous.setNext(current.getNext());
	}
	
	
	////////////// -getMinIter- //////////////
	public int getMinIter() throws ListEmptyException
	{
		int minhelp;									// Hilfsvariable
		ListNodeSL current;								// der aktuelle Knoten	
		
		if(anchor == null)								// �berpr�fen, ob Liste leer ist
			throw new ListEmptyException("Liste ist Leer!");
			
		current = anchor;								// Anfangsannahme: erstes
		minhelp = current.getKey();						// Element ist Minimum
		
		while((current=current.getNext()) != null)		// solange es ein n�chstes
		{																							// Element gibt
			if(minhelp > current.getKey())				// kleinstes Element in
				minhelp = current.getKey();				// minhelp schreiben
		}
		
		return minhelp;									// Hilfsvariable zur�ckgeb.
	}
	
	
	////////////// -getMinRec- //////////////
	// Hauptmethode
	public int getMinRec() throws ListEmptyException	
	{
		if(anchor == null)								// �berpr�fen, ob Liste leer ist
			throw new ListEmptyException("Liste ist Leer!");	
			
		return getMinRecHelp(anchor);					// Aufruf der Hilfsfunktion
	}

	// Hilfsmethode
	private int getMinRecHelp(ListNodeSL current)
	{
		int minhelp;			   						// Hilfsvariable
		
		if(current.getNext() == null)					// wenn dies das letzte 
														// Element ist
			return current.getKey();					// gib aktuellen Wert zur�ck
			
		minhelp = getMinRecHelp(current.getNext());		// Minimum der Restliste
		
		if(minhelp < current.getKey())					// wenn in minhelp der 
														// kleinere Wert steht
			return minhelp;								// gib minhelp zur�ck
		else											// sonst
			return current.getKey();					// gib aktuellen Wert zur�ck
	}
	

	////////////// -printReversedRec- //////////////	
	// Hauptmethode
	public void printReversedRec()
	{
		printReversedRecHelp(anchor);

		System.out.println();
	}

	// Hilfsmethode
	private void printReversedRecHelp(ListNodeSL current)
	{
		// wenn es keinen Wert gibt, tue nichts und breche ab
		if(current==null)
			return;
		
		// Restliste r�ckw�rts ausgeben
		printReversedRecHelp(current.getNext());
		
		// zum Schlu�: aktuellen Wert ausgeben
		System.out.print(current.getKey() + " ");
	}
	
	////////////// -reverseIter, Variante 1- //////////////
	public void reverseIter()	
	{
		ListNodeSL previous, current, next;
		
		if(anchor == null)
			return;
		
		current = anchor;
		next = current.getNext(); 	// k�nnte Null sein
		
		current.setNext(null);		// tail of the reversed list
		
		while(next != null)
		{
			previous = current;
			current = next;
			next = current.getNext();
			
			current.setNext(previous);
		}
		
		anchor = current;		
	}
	
	////////////// -reverseIter, Variante 2- //////////////	
	public void reverseIter2()
	{
		LinkedList listTemp = new LinkedList();

   		try 
   		{
   			while(!this.isEmpty())
   	   		{
   	   			int x = this.getFirst().getKey();
   	   			listTemp.insertFirst(x);
   	   			this.delete4(x);
   	   		}
   			
   			this.anchor = listTemp.getFirst();
		} 
   		catch (Exception e) 
   		{   			
		}    		
	}
	

	////////////// -reverseRec- //////////////	
	// Hauptmethode
	public void reverseRec()
	{
		anchor = reverseRecHelp(anchor, null);		
	}
	
	// Hilfsmethode
	private ListNodeSL reverseRecHelp(ListNodeSL current, ListNodeSL previous)
	{
		if(current == null)
			return previous;
		
		ListNodeSL next = current.getNext();
		current.setNext(previous);
		return reverseRecHelp(next, current);
	}	
	
	
	////////////// -searchKeyIter- //////////////
	public boolean searchKeyIter(int skey)
	{
		ListNodeSL current = anchor;
		
		if(anchor == null)								// �berpr�fen, ob Liste leer ist
			return false;	
		
		do
		{
			if (current.getKey() == skey)
				return true;			
		}while((current = current.getNext()) != null);
		
		return false;
	}
	
	
	////////////// -searchKeyRec- //////////////	
	// Hauptmethode
	public boolean searchKeyRec(int skey)
	{
		if(anchor == null)								// �berpr�fen, ob Liste leer ist
			return false;	
				
		return searchKeyRecHelp(anchor, skey);			// Aufruf der Hilfsfunktion
	}
	
	// Hilfsmethode
	private boolean searchKeyRecHelp(ListNodeSL current, int skey)
	{
		if(current.getKey() == skey)
			return true;
		
		if(current.getNext() == null)
			return false;
		
		return searchKeyRecHelp(current.getNext(), skey);			
	}
	

	////////////// -checkSortOrderIter- //////////////
	public boolean checkSortOrderIter()
	{
		ListNodeSL current = anchor;
		
		while(current != null)
		{
			if((current.getNext() != null) && (current.getKey() > current.getNext().getKey()))
				return false;
			
			current = current.getNext();
		}
		
		return true;
	}
	

	////////////// -checkSortOrderRec- //////////////	
	// Hauptmethode
	public boolean checkSortOrderRec()
	{
		if(anchor == null)	
			return true;
		
		return checkSortOrderRecHelp(anchor);
	}
	
	// Hilfsmethode
	private boolean checkSortOrderRecHelp(ListNodeSL current)
	{
		if(current.getNext() == null)
			return true;
		
		if(current.getKey() > current.getNext().getKey())
			return false;
		
		return checkSortOrderRecHelp(current.getNext());		
	}
	
	
	////////////// -countElementsIter- //////////////
	public int countElementsIter()
	{
		int count = 0;
		
		ListNodeSL current = anchor;
		
		while(current != null)
		{
			count++;
			current = current.getNext();
		}
		
		return count;
	}
	
	
	////////////// -countElementsRec- //////////////
	// Hauptmethode
	public int countElementsRec()
	{
		return countElementsRecHelp(anchor);
	}
	
	// Hilfsmethode
	private int countElementsRecHelp(ListNodeSL current)
	{
		if(current == null)
			return 0;
		
		return (1 + countElementsRecHelp(current.getNext()));		
	}
	
	
	////////////// -countKeyIter- //////////////	
	public int countKeyIter(int skey)
	{
		int count = 0;
		ListNodeSL current = anchor;
		
		while(current != null)
		{
			if(current.getKey() == skey)
				count++;
			current = current.getNext();
		}
		
		return count;		
	}

	////////////// -countKeyRec- //////////////	
	// Hauptmethode
	public int countKeyRec(int skey)
	{
		return  countKeyRecHelp(anchor, skey);
	}
	
	// Hilfsmethode
	private int countKeyRecHelp(ListNodeSL current, int skey)
	{
		if(current == null)
			return 0;
		
		if(current.getKey() == skey)
			return 1 + countKeyRecHelp(current.getNext(), skey);
		else
			return countKeyRecHelp(current.getNext(), skey);	
	}








}