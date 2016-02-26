// CS 0445 Spring 2016
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder2(String s)
	{
		if (s != null && s.length() > 0)
            makeBuilderString(s, 0);
		else  // no String so initialize empty MyStringBuilder2
		{
			length = 0;
            firstC = null;
            lastC = null;
		}
	}
	
	private void makeBuilderString(String s, int pos)
	{
		// Recursive case – we have not finished going through the String
		if (pos < s.length()-1)
		{
            // Note how this is done – we make the recursive call FIRST, then
            // add the node before it.  In this way the LAST node we add is
            // the front node, and it enables us to avoid having to make a
            // special test for the front node.  However, many of your
            // methods will proceed in the normal front to back way.
            makeBuilderString(s, pos+1);
            firstC = new CNode(s.charAt(pos), firstC);
            length++;
		}
		else if (pos == s.length()-1) // Special case for last char in String
		{                             // This is needed since lastC must be
                                    // set to point to this node
            firstC = new CNode(s.charAt(pos));
            lastC = firstC;
            length = 1;
		}
				// This case should never be reached, due to the way the
		else	// constructor is set up.  However, I included it as a
		{		// safeguard (in case some other method calls this one)
            length = 0;
            firstC = null;
            lastC = null;
		}
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
		if (s != null && s.length > 0)
            makeBuilderCharArray(s, 0);
		else  // no String so initialize empty MyStringBuilder2
		{
			length = 0;
            firstC = null;
            lastC = null;
		}
	}
	
	private void makeBuilderCharArray(char [] s, int pos)
	{
		// Recursive case – we have not finished going through the String
		if (pos < s.length-1)
		{
            // Note how this is done – we make the recursive call FIRST, then
            // add the node before it.  In this way the LAST node we add is
            // the front node, and it enables us to avoid having to make a
            // special test for the front node.  However, many of your
            // methods will proceed in the normal front to back way.
            makeBuilderCharArray(s, pos+1);
            firstC = new CNode(s[pos], firstC);
            length++;
		}
		else if (pos == s.length-1) // Special case for last char in String
		{                             // This is needed since lastC must be
                                    // set to point to this node
            firstC = new CNode(s[pos]);
            lastC = firstC;
            length = 1;
		}
				// This case should never be reached, due to the way the
		else	// constructor is set up.  However, I included it as a
		{		// safeguard (in case some other method calls this one)
            length = 0;
            firstC = null;
            lastC = null;
		}
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder2()
	{
		firstC = null;
		lastC = null;
		length = 0;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
		if (b == null || b.length() == 0) // Special case for empty String
		{					 			  // or null reference
			return this;
		}
		else
		{			
			appendMSB(b.firstC, lastC);
		}
		length += b.length();
		return this;
	}
	
	private void appendMSB(CNode front, CNode end){
		// Recursive case – we have not finished going through the String
		if (front != null)
		{
			CNode temp = new CNode(front.data);
			end.next = temp;
            appendMSB(front.next, temp);
		}
		else
		{
            lastC = end;
		}
	}


	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			return this;
		}
		else if (firstC == null)
		{
			makeBuilderString(s,0);
		}
		else
		{			
			appendString(s, 0, lastC);
		}
		length += s.length();
		return this;
	}
	
	private void appendString(String s, int pos, CNode end){
		// Recursive case – we have not finished going through the String
		if (pos < s.length()-1)
		{
			CNode temp = new CNode(s.charAt(pos));
			end.next = temp;
            appendString(s, pos+1, temp);

		}
		else
		{
            CNode temp = new CNode(s.charAt(pos));
			end.next = temp;
            lastC = temp;
		}
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(char [] c)
	{
		if (c == null || c.length == 0) // Special case for empty String
		{					 			  // or null reference
			return this;
		}
		else
		{			
			appendCharArray(c, 0, lastC);
		}
		length += c.length;
		return this;
	}
	
	private void appendCharArray(char [] s, int pos, CNode end){
		// Recursive case – we have not finished going through the String
		if (pos < s.length-1)
		{
			CNode temp = new CNode(s[pos]);
			end.next = temp;
            appendCharArray(s, pos+1, temp);

		}
		else
		{
            CNode temp = new CNode(s[pos]);
			end.next = temp;
            lastC = temp;
		}
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
	{
		return this;
	}
	
	private void appendChar(CNode front, CNode end){
		// Recursive case – we have not finished going through the String
		if (front != null)
		{
			CNode temp = new CNode(front.data);
			end.next = temp;
            appendMSB(front.next, temp);
		}
		else
		{
            lastC = end;
		}
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		return 'c';
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end)
	{
		return this;
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
	{
		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		return -1;
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
	{
		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
	{
		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder2 insert(int offset, char [] c)
	{
		return this;
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}


	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.
	public MyStringBuilder2 replace(int start, int end, String str)
	{
		return this;
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		return "this";
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		char [] c = new char[length];
		getString(c, 0, firstC);
		return (new String(c));
	}
	
	private void getString(char [] c, int pos, CNode curr)
	{
		if (curr != null)
		{
			c[pos] = curr.data;
            getString(c, pos+1, curr.next);
		}
	}

	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}
