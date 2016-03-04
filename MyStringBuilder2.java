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
		if (c == '\u0000'){//checks if char is 	empty				 			  
			return this;
		}
		else		
		{
			if(this.firstC == null){
				this.firstC = new CNode(c);
				this.lastC = this.firstC;
			}
			
			CNode currNode = lastC;
			CNode newNode = new CNode(c);
			currNode.next = newNode;
			currNode = newNode;
			lastC = currNode;
		}	
		length++;
		return this;
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if(index<0||index>length){
			throw new IndexOutOfBoundsException();//incase index isn't valid
		}
		else{
			return charAtR(0,index,firstC);
		}
	}
	
	private char charAtR(int s, int index, CNode node){
		if (s == index)
		{
			return node.data;
		}
		else
		{
			return charAtR(s + 1,index,node.next);
		}
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end)
	{
		if(start < 0||start > length){//Special case 
			return this;
		}
		else if(start == 0 && end == 1){//just delete the first char
			firstC = firstC.next;
			length--;
			return this;
		}
		else if(start >= end){ // special case
			return this;
		}
		else if(start == 0 && end >= length){//delete the whole thing
			firstC=null;
			lastC = null;
			length = 0;

		}
		else if(start > 0 && end >= length){
			CNode temp = getNodeAt(0, start - 1, firstC);
			temp.next = null;
			length = start;
		}
		else if(start == 0 && end < length){
			firstC = getNodeAt(0, end, firstC);
			length -= end;
		}
		else{
			CNode front = firstC;
			deleteR(0, start, end, front, firstC);
			length = length-end+start;
		}
		return this;
	}
	
	private void deleteR(int s, int start, int end, CNode front, CNode node){
		if(s != start - 1 && s != end)
		{
			deleteR(s + 1, start, end, front, node.next);
		}
		else if (s == start - 1)
		{
			front = node;
			deleteR(s + 1, start, end, front, node.next);
		}
		else
		{
			front.next = node;
		}
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
	{
		if(index<0||index>length){//special case
			return this;
		}
		else if(index == 0){//front char
			firstC = firstC.next;
		}
		else if(index == length-1){//just delete end
			lastC = getNodeAt(0, index-1, firstC);
			lastC.next=null;
		}
		else{//normal case
			int i = 0;
			CNode currNode = getNodeAt(0, index-1, firstC);
			currNode.next = currNode.next.next;
		}
		length--;
		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		return indexOfR(0, 0, firstC, str, false);
	}
	
	private int indexOfR(int count, int s, CNode node, String str, boolean found){
		if(node == null && found == false){
			return -1;
		}
		else if(node == null || found == true){
			return count;
		}
		else if(s<=str.length()-1 && str.charAt(s) == node.data)
		{
			if(s==str.length()-1){
				count-=str.length();
				found = true;
			}
			return indexOfR(count + 1, s + 1, node.next, str, found);
		}
		else
		{
			return indexOfR(count + 1, 0, node.next, str, found);
		}
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
	{
		if(offset<0||offset>length){
			return this;
		}
		
		else if(offset == length){//just the append case
			appendString(str, 0, lastC);
		}
		
		else if(offset == 0){//put at front
			int lHolder = length;
			CNode tempNode = firstC;
			CNode tempNode2 = lastC;
			makeBuilderString(str, 0);
			lastC.next = tempNode;
			lastC = tempNode2;
			length = lHolder;
		}
		
		else{//normal case
			CNode lastHolder = lastC;
			CNode tempNode = getNodeAt(0, offset - 1, firstC);
			CNode contNode = tempNode.next;
			appendString(str, 0, tempNode);
			lastC.next = contNode;
			lastC = lastHolder;
		}
		length+=str.length();
		return this;
	}
	
	private void insertString(String s, int pos, CNode end){
		// Recursive case – we have not finished going through the String
		if (pos < s.length()-1)
		{
			CNode temp = new CNode(s.charAt(pos));
			end.next = temp;
            insertString(s, pos+1, temp);

		}
		else
		{
            CNode temp = new CNode(s.charAt(pos));
			end.next = temp;
            lastC = temp;
		}
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
	{
		if(offset<0||offset>length){
			return this;
		}
		
		else if(offset == length){//just the append case
			CNode temp = new CNode(c);
			lastC.next = temp;
			lastC = temp;
		}
		
		else if(offset == 0){//put at front
			CNode temp = new CNode(c,firstC);
			firstC = temp;
		}
		
		else{//normal case
			CNode tempNode = getNodeAt(0, offset - 1, firstC);
			CNode contNode = tempNode.next;
			CNode temp = new CNode(c,contNode);
			tempNode.next = temp;
		}
		length++;
		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder2 insert(int offset, char [] c)
	{
		if(offset<0||offset>length){
			return this;
		}
		
		else if(offset == length){//just the append case
			appendCharArray(c, 0, lastC);
		}
		
		else if(offset == 0){//put at front
			int lHolder = length;
			CNode tempNode = firstC;
			CNode tempNode2 = lastC;
			makeBuilderCharArray(c, 0);
			lastC.next = tempNode;
			lastC = tempNode2;
			length = lHolder;
		}
		
		else{//normal case
			CNode lastHolder = lastC;
			CNode tempNode = getNodeAt(0, offset - 1, firstC);
			CNode contNode = tempNode.next;
			appendCharArray(c, 0, tempNode);
			lastC.next = contNode;
			lastC = lastHolder;
		}
		length+=c.length;
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
		if(start<0||start>length){//special case
			return this;
		}
		else if(start>=end){//special case
			return this;
		}
		else if(start == 0 && end>= length){
			makeBuilderString(str,0);
		}
		else if(start > 0 && end>= length){
			CNode temp = getNodeAt(0, start-1, firstC);
			appendString(str, 0, temp);
			length = start + str.length();
		}
		else{//normal case
			CNode front = firstC;
			replaceR(0, start, end, front, firstC, lastC, str);
			length = length-end+start+str.length();
		}
		return this;
	}
	
	private void replaceR(int s, int start, int end, CNode front, CNode node, CNode nodeL, String str){
		if(s != start - 1 && s != end)
		{
			replaceR(s + 1, start, end, front, node.next, nodeL, str);
		}
		else if (s == start - 1)
		{
			front = node;
			replaceR(s + 1, start, end, front, node.next, nodeL, str);
		}
		else
		{
			appendString(str, 0, front);
			lastC.next = node;
			lastC = nodeL;
		}
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)	
	{
		if (firstC == null) {//special case					 			  
			return ("");
		}
		else{// normal case
			char[] build = new char[end-start];
			CNode temp = getNodeAt(0, start, firstC);
			substringR(0,end-start,temp, build);
			
			
			return new String(build);
		}
	}
	
	private void substringR(int s, int end, CNode node, char [] b){
		if(s!=end)
		{
			b[s] = node.data;
			substringR(s+1, end, node.next, b);
		}
	}
	
	
	//EXTRA CREDIT
	public MyStringBuilder2 reverse(MyStringBuilder2 b){
		b.lastC = reverseR(b.firstC, b);
		b.lastC.next = null;
		return(b);
	}
	
	private CNode reverseR(CNode node, MyStringBuilder2 b){
		CNode temp = null;
		if(node.next != null)
		{
			temp = reverseR(node.next, b);
			temp.next = node;
			return node;
		}
		else{
			b.firstC = node;
			return node;
		}
	}
	
	/*
	THIS IS AN EXAMPLE OF CODE TO TEST THE REVERSE METHOD.
	
	b1.reverse(b1);
	System.out.println(b1);
		
	b1.reverse(b1);
	System.out.println(b1);
	*/
	
	//END EXTRA CREDIT
	
	
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
	
	private CNode getNodeAt(int s, int index, CNode node){
		if (s == index)
		{
			return node;
		}
		else
		{
			return getNodeAt(s + 1,index,node.next);
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
