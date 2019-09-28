package hw1;

/**
 *  The {@code SequentialSearchST} class represents an (unordered)
 *  symbol table of generic key-value pairs.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 *  It also provides a <em>keys</em> method for iterating over all of the keys.
 *  A symbol table implements the <em>associative array</em> abstraction:
 *  when associating a value with a key that is already in the symbol table,
 *  the convention is to replace the old value with the new value.
 *  The class also uses the convention that values cannot be {@code null}. Setting the
 *  value associated with a key to {@code null} is equivalent to deleting the key
 *  from the symbol table.
 *  <p>
 *  This implementation uses a singly-linked list and sequential search.
 *  It relies on the {@code equals()} method to test whether two keys
 *  are equal. It does not call either the {@code compareTo()} or
 *  {@code hashCode()} method. 
 *  The <em>put</em> and <em>delete</em> operations take linear time; the
 *  <em>get</em> and <em>contains</em> operations takes linear time in the worst case.
 *  The <em>size</em>, and <em>is-empty</em> operations take constant time.
 *  Construction takes constant time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/31elementary">Section 3.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
import java.util.LinkedList;

public class SequentialSearchST<Key extends Comparable<Key>, Value> {
    private int n;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public SequentialSearchST() {
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * 
     * NOTE:  Java's LinkedList was use in place of the book's Queue class.
     *
     * @return all keys in the symbol table
     */
    public Iterable<Key> keys()  {
        LinkedList<Key> queue = new LinkedList<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.add(x.key);
        return queue;
    }


    /**
     * Returns the value associated with the given key in this symbol table.
     * Takes advantage of the fact that the keys appear in increasing order to terminate
     * early when possible.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
    	// TODO
    	// Change this code to make use of the fact the list is sorted to terminate early
    	// when possible.
        if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
            if(key.compareTo(x.key) > 0 && key.compareTo(x.next.key) < 0)
            	return null;
        }
        return null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table while maintaining the
     * ordering of keys.  Overwrites the old value with the new value if the symbol table
     * already contains the specified key.  Deletes the specified key (and its associated
     * value) from this symbol table if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */// 
    // Conditional: Greater than one number but less than the next
    public void put(Key key, Value val) {
    	// TODO
    	// Change this code to make sure the list remains sorted!
//    	for (Node x = first; x != null; x = x.next) {System.out.println(x.key);}
        if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 
        if (val == null) {
            delete(key);
            return;
        }
        if(first == null) {
        	first = new Node(key, val, null);
        	n++;
        	return;
        	}
        if (key.compareTo(first.key) < 0) {
        	Node x = new Node(key, val, first);
        	first = x;
        	n++;
        	return;
        }
        if (key.equals(first.key)) {
    		first.val = val;
    		return;
    	}
        if (first.next == null) {
        	if (key.equals(first.key)) {
        		first.val = val;
        		return;
        	}
        	if (key.compareTo(first.key) > 0) {
    			Node q = new Node(key, val, null);
    			first.next = q;
    			n++;
        		return;
        	}
        }
        Node a = first;
        for (Node x = first, y = first.next; y != null; y = y.next, x = x.next) {
        	if(key.equals(y.key)) {
        		y.val = val;
        		return;
        	}
        	if (key.compareTo(x.key) > 0 && key.compareTo(y.key) < 0) {
        		Node w = new Node(key, val, y);
        		x.next = w;
        		n++;
//        		for (Node t = first; t != null; t = t.next) {System.out.println(t.key);}
        		return;
        	}
        	a = y;
        }
        if (key.compareTo(a.key) > 0) {
        	Node s = new Node(key, val, null);
        	a.next = s;
    		n++;
//    		for (Node x = first; x != null; x = x.next) {System.out.println(x.key);}
    		return;
        }
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).  Takes advantage of the fact that the
     * keys appear in increasing order to terminate` early when possible. 
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
    	// TODO
    	// Change this code to make use of the fact that the list is sorted to
    	//     terminate early when possible.
    	// Also use a loop instead of recursion!  So remove the recursive helper
    	//     function below.
    	if (key == null) throw new IllegalArgumentException("argument to delete() is null");
    	if (key.compareTo(first.key) < 0) {return;}
    	if(first.key.equals(key) && size() == 1) {
    		first = null;
    		n--;
    		return;
    	}
    	if (key.equals(first.key)) {
    		first = first.next;
    		n--;
    		return;
    	}
    	Node x = first;
    	Node y = first.next;
    	while(!(key.equals(y.key))) {
    		if (key.compareTo(x.key) > 0 && key.compareTo(y.key) < 0) {return;}
    		x = x.next;
    		y = y.next;
    		if (y.next == null && !key.equals(y.key)) {return;}
    	}
    	if(y.next == null) {
    		x.next = null;
    		n--;
    		return;
    	}
        x.next = y.next;
        n--;
        return;       
    }
 
    public boolean checkInvariant() {
    	if (first == null)
    		return true;
    	Node current = first;
    	Node next = first.next;
    	while (next != null) {
    		if (current.key.compareTo(next.key) > 0)
    			return false;
    		current = next;
    		next = current.next;
    	}
    	return true;
    }

}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
