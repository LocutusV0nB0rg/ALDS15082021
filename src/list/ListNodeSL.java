package list;

public class ListNodeSL 
{
    private int key;                                    // integer as a key
    private ListNodeSL next;                            // reference to the next node

    public ListNodeSL(int key, ListNodeSL next)
    {
        this.key = key;
        this.next = next;
    }
    
    public ListNodeSL()
    {
        this(0, null);
    }

    /**
     * @return the key
     */
    public int getKey() 
    {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(int key) 
    {
        this.key = key;
    }

    /**
     * @return the next
     */
    public ListNodeSL getNext() 
    {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(ListNodeSL next) 
    {
        this.next = next;
    }
    
    /**
     * toString-method 
     * @return the key-value of list node
     */
    public String toString()
    {
    	return Integer.toString(key);
    }
    
}
