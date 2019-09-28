package hw1;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class RHW1Test {
	private static final int SIZE = 10000;
	
	private String[] words = {
			"ant", "bee", "cat", "dog", "emu", "fox", "gem", "hat", "ice", "jar"
	};
	
	private static RSequentialSearchST<String, Integer> numbers;
	
	@BeforeClass
	public static void setupNumbers() {
		numbers = new RSequentialSearchST<String, Integer>();
		for(int i = 0; i < SIZE; i++)
			numbers.put(""+i, i);
	}
	
	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	
	// 5 points
	// @Test (expected = IllegalArgumentException.class)
	@Test
	public void test05GetNull() {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		st.put("a",  1);
		
		// Null is not a valid key.  This should throw an IllegalArgumentException
		try {
			st.get(null);
			// If we don't break out here this is an error.
			fail();
		} catch(IllegalArgumentException e) {
		assertEquals(new Integer(1), st.get("a"));
		}
	}
	
	// 5 points
	//@Test (expected = IllegalArgumentException.class)
	@Test
	public void test05PutNull() {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		st.put("a",  1);
		
		// Null is not a valid key.  This should throw an IllegalArgumentException
		try {
			st.put(null, 3);
			// If we don't break out here this is an error.
			fail();
		} catch(IllegalArgumentException e) {
		assertEquals(new Integer(1), st.get("a"));
		}
	}
	
	// 5 points
	// @Test (expected = IllegalArgumentException.class)
	@Test
	public void test05DeleteNull() {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		st.put("a",  1);
		
		// Null is not a valid key.  This should throw an IllegalArgumentException
		try {
			st.delete(null);
			// If we don't break out here this is an error.
			fail();
		} catch(IllegalArgumentException e) {
		assertEquals(new Integer(1), st.get("a"));
		}
	}
	
	// 15 points
	@Test
	public void test15EmptySize() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		
		// Check that a newly created RSequentialSearchST has size 0.
		assertEquals(0, st.size());
	}
	
	//10 points
	@Test
	public void test10Size() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		
		// Insert 9 items and after each one make sure the size is correct.
		for (int i = 1; i < 9; i++) {
			st.put(words[i], i);
			assertEquals(i, st.size());
		}
		
		// Delete a key in the symbol table and check new size.
		st.delete(words[5]);
		assertEquals(7, st.size());
		
		// Delete a key in the symbol table and check new size.
		st.delete(words[1]);
		assertEquals(6, st.size());
		
		// Delete a key in the symbol table and check new size.
		st.delete(words[8]);
		assertEquals(5, st.size());
		
		// Delete an already deleted key ane make sure size doesn't change.
		st.delete(words[5]);
		assertEquals(5, st.size());
	}
	
	// 10 points
	@Test
	public void test10PutGetNoDuplicates() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		
		// Put 9 words in order into the ST.
		for (int i = 1; i < 9; i++) {
			st.put(words[i], i);
			
			// After each insertion, verify all words inserted so far are in the ST.
			for (int j = 1; j <= i; j++)
				assertEquals(new Integer(j), st.get(words[j]));
		}
	}
	
	// 10 points
	@Test
	public void test10PutDuplicates() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		
		// Put 5 words in order into th ST.
		for(int i = 1; i < 5; i++) {
			st.put(words[i], i);
			assertEquals(i, st.size());
		}
		
		// Check the values for those keys are correct.
		for(int i = 1; i < 5; i++)
			assertEquals(new Integer(i), st.get(words[i]));
		
		
		// Put new values for each of the 5 words in the ST.
		for(int i = 1; i < 5; i++) {
			st.put(words[i], i*10);
			assertEquals(4, st.size());
		}
		
		// Check the values for those keys are correct.
		for(int i = 1; i < 5; i++)
			assertEquals(new Integer(i*10), st.get(words[i]));
		
		// Put new values for each of the 5 words in the ST.
		for(int i = 4; i >= 1; i--) {
			st.put(words[i], i*100);
			assertEquals(4, st.size());
		}
		
		// Check the values for those keys are correct.
		for(int i = 4; i >= 1; i--)
			assertEquals(new Integer(i*100), st.get(words[i]));
	}
	
	// 10 points
	@Test
	public void test10MissingDeletes() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		
		// Put all the words from the array into the ST.
		for(int i = 0; i < words.length; i++)
			st.put(words[i],  i);
		assertEquals(words.length, st.size());
		
		// Delete a key that would be 1st in the ST, but isn't there.
		st.delete("a");
		assertEquals(words.length, st.size());
		
		// Delete a key that would be 2nd in the ST, but isn't there.
		st.delete("b");
		assertEquals(words.length, st.size());
		
		// Delete a key that would be 5th in the ST, but isn't there.
		st.delete("e");
		assertEquals(words.length, st.size());
		
		// Delete a key that would be last in the ST, but isn't there.
		st.delete("x");
		assertEquals(words.length, st.size());
		
		// Delete a key that would be last in the ST, but isn't there.
		st.delete("z");
		for(int i = 0; i < words.length; i++)
			assertEquals(new Integer(i), st.get(words[i]));
	}

	// 5 points
	@Test
	public void test05PutIncDelInc() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		// Put all words into the ST in order
		for(int i = 0; i < words.length; i++)
			st.put(words[i],  i);
		
		// Get the words in order
		for(int i = 0; i < words.length; i++)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Get the words in reverse order
		for(int i = words.length-1; i >= 0; i--)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Verify the words are returned in order by the iterator.
		int counter = 0;
		for(String s : st.keys()) {
			assertEquals(words[counter], s);
			counter++;
		}
		assertEquals(words.length, st.size());
		
		// Delete each of the words in order
		for(int i = 0; i < words.length; i++) {
			st.delete(words[i]);
			counter = i+1;
			assertEquals(words.length-counter, st.size());
			
			// After each delete, verify the remaining words are all there in order.
			for(String s : st.keys()) {
				assertEquals(words[counter], s);
				counter++;
			}
		}
	}

	// 5 points
	@Test
	public void test05PutIncDelDec() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		
		// Put all words into the ST in order
		for(int i = 0; i < words.length; i++)
			st.put(words[i],  i);
		
		// Get the words in order
		for(int i = 0; i < words.length; i++)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Get the words in reverse order
		for(int i = words.length-1; i >= 0; i--)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Verify the words are returned in order by the iterator.
		int counter = 0;
		for(String s : st.keys()) {
			assertEquals(words[counter], s);
			counter++;
		}
		assertEquals(words.length, st.size());
		
		// Delete each of the words in reverse order
		for(int i = words.length-1; i >= 0; i--) {
			st.delete(words[i]);
			counter = 0;
			assertEquals(i, st.size());
			
			// After each delete, verify the remaining words are all there in order.
			for(String s : st.keys()) {
				assertEquals(words[counter], s);
				counter++;
			}
		}
	}
	
	// 5 points
	@Test
	public void test05PutDecDelInc() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		
		// Put all words into the ST in reverse order
		for(int i = words.length-1; i >= 0; i--)
			st.put(words[i],  i);
		
		// Get the words in order
		for(int i = 0; i < words.length; i++)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Get the words in reverse order
		for(int i = words.length-1; i >= 0; i--)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Verify the words are returned in order by the iterator.
		int counter = 0;
		for(String s : st.keys()) {
			assertEquals(words[counter], s);
			counter++;
		}
		assertEquals(words.length, st.size());
		
		// Delete each of the words in order
		for(int i = 0; i < words.length; i++) {
			st.delete(words[i]);
			counter = i+1;
			assertEquals(words.length - counter, st.size());
			
			// After each delete, verify the remaining words are all there in order.
			for(String s : st.keys()) {
				assertEquals(words[counter], s);
				counter++;
			}
		}
	}

	// 5 points
	@Test
	public void test05PutDecDelDec() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		
		// Put all words into the ST in reverse order
		for(int i = words.length-1; i >= 0; i--)
			st.put(words[i],  i);
		
		// Get the words in order
		for(int i = 0; i < words.length; i++)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Get the words in reverse order
		for(int i = words.length-1; i >= 0; i--)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Verify the words are returned in order by the iterator.
		int counter = 0;
		for(String s : st.keys()) {
			assertEquals(words[counter], s);
			counter++;
		}
		assertEquals(words.length, st.size());
		
		// Delete each of the words in reverse order
		for(int i = words.length-1; i >= 0; i--) {
			st.delete(words[i]);
			counter = 0;
			assertEquals(i, st.size());
			
			// After each delete, verify the remaining words are all there in order.
			for(String s : st.keys()) {
				assertEquals(words[counter], s);
				counter++;
			}
		}
	}
	
	// 5 points
	@Test
	public void test05UpEvensDownOdds() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		
		// Insert words in even slots in order.
		for(int i = 0; i < words.length; i+=2)
			st.put(words[i], i);
		
		// Insert words in odd slots in reverse order.
		for(int i = words.length % 2 == 0 ? words.length-1 : words.length-2; i >= 0; i-=2)
			st.put(words[i], i);
		
		// Get all the words in order.
		for(int i = 0; i < words.length; i++)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Get all the words in reverse order.
		for(int i = words.length-1; i >= 0; i--)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Verify the words are returned in order by the iterator.
		int counter = 0;
		for(String s : st.keys()) {
			assertEquals(words[counter], s);
			counter++;
		}
		
		// Delete the words in reverse order
		for(int i = words.length-1; i >= 0; i--) {
			st.delete(words[i]);
			
			// After each delete, verify the remaining words are in order
			counter = 0;
			for(String s : st.keys()) {
				assertEquals(words[counter], s);
				counter++;
			}
		}
	}
	
	// 5 points
	@Test
	public void test05DownEvensUpOdds() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		
		// Insert words in even slots in reverse order.
		for(int i = words.length % 2 == 1 ? words.length-1 : words.length-2; i >= 0; i-=2)
			st.put(words[i], i);
		
		// Insert words in odd slots in order.
		for(int i = 1; i < words.length; i+=2)
			st.put(words[i], i);
		
		// Get all the words in order.
		for(int i = 0; i < words.length; i++)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Get all the words in reverse order.
		for(int i = words.length-1; i >= 0; i--)
			assertEquals(new Integer(i), st.get(words[i]));
		
		// Verify the words are returned in order by the iterator.
		int counter = 0;
		for(String s : st.keys()) {
			assertEquals(words[counter], s);
			counter++;
		}
		
		// Delete the words in reverse order
		for(int i = words.length-1; i >= 0; i--) {
			st.delete(words[i]);
			
			// After each delete, verify the remaining words are in order
			counter = 0;
			for(String s : st.keys()) {
				assertEquals(words[counter], s);
				counter++;
			}
		}
	}

}
