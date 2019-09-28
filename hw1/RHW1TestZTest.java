package hw1;
import static org.junit.Assert.*;

import org.junit.Test;

public class RHW1TestZTest {
	
        
	/**
	 * A toy test function.  Tests that symbol table behaves as
	 * expected when a single key/value pair is inserted.
	 */
	@Test
	public void testSinglePutGet() {
		RSequentialSearchST<String, Integer> st = new RSequentialSearchST<String, Integer>();
		assertEquals(0,  st.size());
		assertNull(st.get("apple"));
		st.put("apple", 42);
		assertEquals(1, st.size());
		assertEquals(new Integer(42), st.get("apple"));
//		assertNull(st.get("a"));
//		assertNull(st.get("b"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetNull() {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		st.put("a", 1);

		// Null is not a valid key. This should throw an IllegalArgumentException
		st.get(null);
	}
	
	@Test
	public void testPPGG() {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		st.put("a", 1);
		st.put("b", 2);
		assertTrue(st.checkInvariant());
//		assertEquals(new Integer(1), st.get("a"));
//		assertEquals(new Integer(2), st.get("b"));
		// Null is not a valid key. This should throw an IllegalArgumentException
	}
	
	// TODO
		// Write some more test functions below. Make sure to test:
		// 1. Putting at the beginning, end, and middle of the list.
		// 2. Putting something that is already in the list
		@Test
		public void testDuplicate() {
			SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
			st.put("a", 1);
			st.put("a", 1);
		}
		// 3. Size works correctly
		@Test
		public void testSize() {
			SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
			st.put("a", 1);
			st.put("b", 2);
			st.put("c", 3);
			st.put("d", 4);
			assertEquals(4, st.size());
			st.delete("c");
			assertEquals(3, st.size());
		}
		// 4. Get works correctly for keys in beginning, middle and end.
		@Test
		public void testGet() {
			SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
			st.put("a", 1);
			st.put("f", 2);
			st.put("l", 3);
			st.put("o", 4);
			st.put("r", 42);
			st.put("u", 53);
			st.put("z", 64);
			assertEquals(new Integer(1), st.get("a"));
			assertEquals(new Integer(4), st.get("o"));
			assertEquals(new Integer(64), st.get("z"));
		}
		// 5. Get works correctly for keys that are not in the list but
		//    would be located at beginning, middle, end of list.
		@Test
		public void testGetAdvanced() {
			SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
			st.put("b", 1);
			st.put("g", 2);
			st.put("m", 3);
			st.put("p", 4);
			st.put("s", 42);
			st.put("u", 53);
			st.put("y", 64);
			assertNull(st.get("a"));
			assertNull(st.get("o"));
			assertNull(st.get("z"));
		}
		// 6. Delete works correctly for keys in beginning, middle, and end.
		@Test
		public void testDelete() {
			SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
			st.put("a", 1);
			st.put("f", 2);
			st.put("l", 3);
			st.put("o", 4);
			st.put("r", 42);
			st.put("u", 53);
			st.put("z", 64);
			st.delete("a");
			st.delete("r");
			st.delete("z");
			assertEquals(6, st.size());
		}
		// 7. Delete works correctly for keys that are not in the symbol table.
		@Test
		public void testDeleteAdvanced() {
			SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
			st.put("b", 1);
			st.put("g", 2);
			st.put("m", 3);
			st.put("p", 4);
			st.put("s", 42);
			st.put("u", 53);
			st.put("y", 64);
			st.delete("a");;
			st.delete("o");
			st.delete("z");
			assertEquals(7, st.size());
		}
		// 8. You can put a few items, delete them all, and then put more items
		//    and all the functions (put, get, delete, iterate) continue to work.
		@Test
		public void testMixedSequence() {
			SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
			st.put("taught", 71);
			st.put("thought", 98);
			st.put("though", 13);
			st.put("through", 41);
			st.put("tough", 55);
			st.delete("threw");
			assertEquals(5, st.size());
			st.delete("though");
			assertEquals(4, st.size());
			st.delete("through");
			st.delete("tough");
			st.delete("thought");
			st.delete("taught");
			assertEquals(0, st.size());
			st.put("fade", 34);
			st.put("paid", 66);
			st.put("said", 82);
			assertEquals(new Integer(34), st.get("fade"));
			assertNull(st.get("aid"));
			assertEquals(3, st.size());
			
		
		}
		// 9. The iterator returned by keys() provides the keys in increasing order.
		//10.  Null is not a valid key.  Your code should behave as before your
		//     changes and throw an IllegalArgumentException when null is given as a
		//     key to any method. 
		@Test
		public void testPutNull() {
			SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
			try {
				st.put(null, 1);
				fail();
			} catch(IllegalArgumentException e) {
			}
			assertEquals(0, st.size());
		}
		@Test
		public void testDeleteNull() {
			SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
			try {
				st.delete(null);
				fail();
			} catch(IllegalArgumentException e) {
			}
			assertEquals(0, st.size());
		}

	}
