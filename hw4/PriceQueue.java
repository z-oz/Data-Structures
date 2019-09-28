/***************************************************************
 * Below is the Queue code from the textbook, but modified to be
 * a queue of Price objects rather than a generic queue.
 ****************************************************************/

package hw4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class PriceQueue implements Iterable<Price> {
    private Node first;    // beginning of queue
    private Node last;     // end of queue
    private int n;         // number of elements on queue
    private TreeMap<Price, Node> map;

    // helper linked list class
    private static class Node {
        private Price price;
        private Node next;
    }

    /**
     * Initializes an empty queue.
     */
    public PriceQueue() {
        first = null;
        last  = null;
        n = 0;
        map = new TreeMap<Price, Node>();
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of Prices in this queue.
     *
     * @return the number of Prices in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the Price least recently added to this queue.
     *
     * @return the Price least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Price peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.price;
    }

    /**
     * Adds the Price to this queue.
     *
     * @param  Price the Price to add
     */
    public void enqueue(Price price) {
    	if (!map.containsKey(price)) {
	        Node oldlast = last;
	        map.put(price, oldlast);
	        last = new Node();
	        last.price = price;
	        last.next = null;
	        if (isEmpty()) first = last;
	        else           oldlast.next = last;
	        n++;
    	}
    }

    /**
     * Removes and returns the Price on this queue that was least recently added.
     *
     * @return the Price on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Price dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Price price = first.price;
        first = first.next;
        if (first != null) {
        	map.put(first.price, null);
        } 
        map.remove(price);
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return price;
    }
    
    
    /**
     * Deletes a Price from the queue if it was present.
     * @param price the Price to be deleted.
     * @return {@code true} if the Price was deleted and {@code false} otherwise
     */
    public boolean delete(Price price) {
    	if (isEmpty()) return false;
    	if (map.containsKey(price)) {
    		Node prevNode = map.get(price);
    		if (prevNode == null) {
    			map.remove(price);
    			first = first.next;
    			if (first != null) map.put(first.price, null);
    		} 
    		else {
    			map.remove(prevNode.next.price);
    			Node next = prevNode.next.next;
    			if (next == null) {
    				prevNode.next = prevNode.next.next;
    				map.remove(price);
    				last = prevNode;
    			}
    			else {
    				prevNode.next = prevNode.next.next;
    				map.remove(price);
    				map.put(next.price, prevNode);
    			}
    		}
    		n--;
    		if (isEmpty()) last = null;
    		return true;
    	}
    	
    	// TODO implelment me!!!
    	// Make sure the running time is no worse than logrithmic!!!
    	// You will want to use Java's TreeMap class to map Prices to the node
    	// that precedes the Price in the queue  	
    	
    	return false;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of Prices in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Price price : this) {
            s.append(price);
            s.append(' ');
        }
        return s.toString();
    } 

    /**
     * Returns an iterator that iterates over the Prices in this queue in FIFO order.
     *
     * @return an iterator that iterates over the Prices in this queue in FIFO order
     */
    public Iterator<Price> iterator()  {
        return new PriceListIterator(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class PriceListIterator implements Iterator<Price> {
        private Node current;

        public PriceListIterator(Node first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Price next() {
            if (!hasNext()) throw new NoSuchElementException();
            Price price = current.price;
            current = current.next; 
            return price;
        }
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
