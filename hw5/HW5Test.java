package hw5;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;

public class HW5Test {

	@Test
	public void toy10Test() {
		LinearProbingHashST<MyString, Integer> st = new LinearProbingHashST<MyString, Integer>();
		st.put(new MyString("apple"), 4);
		st.put(new MyString("box"), -9);
		st.put(new MyString("car"), 0);
		st.put(new MyString("bear"), 5);
		assertEquals(new Integer(4), st.get(new MyString("apple")));
		assertEquals(new Integer(-9), st.get(new MyString("box")));
		assertEquals(new Integer(0), st.get(new MyString("car")));
		assertEquals(new Integer(5), st.get(new MyString("bear")));
		st.delete(new MyString("box"));
		assertFalse(st.contains(new MyString("box")));
		st.put(new MyString("bear"), -3);
		assertEquals(new Integer(4), st.get(new MyString("apple")));
		assertNull(st.get(new MyString("box")));
		assertEquals(new Integer(0), st.get(new MyString("car")));
		assertEquals(new Integer(-3), st.get(new MyString("bear")));
		
		// Scan all slots looking for keys.
		// Verify corresponding value is correct.
		int appleCount = 0;
		int boxCount = 0;
		int carCount = 0;
		int bearCount = 0;
		for(int i = 0; i < st.tableSize(); i++) {
			MyString k = st.getKeyAt(i);
			if (k != null) {
				if (k.equals(new MyString("apple"))) {
					assertEquals(new Integer(4), st.getValueAt(i));
					appleCount++;
				} else if (k.equals(new MyString("box"))) {
					assertNull(st.getValueAt(i));
					boxCount++;
				} else if (k.equals(new MyString("car"))) {
					assertEquals(new Integer(0), st.getValueAt(i));
					carCount++;
				} else if (k.equals(new MyString("bear"))) {
					Integer val = st.getValueAt(i);
					if (val != null)
						assertEquals(new Integer(-3), st.getValueAt(i));
					bearCount++;
				} else {
					fail();
				}
			}
		}
		// The number of times each key should appear in the array of keys.
		assertEquals(1, appleCount);
		assertEquals(0, boxCount);
		assertEquals(1, carCount);
		assertEquals(2, bearCount);
	}
	
	@Test
	public void test10SingleKey() {
		LinearProbingHashST<MyString, Integer> st = new LinearProbingHashST<MyString, Integer>(4);
		MyString enter = new MyString("enter");
		Integer four = new Integer(4);
		st.put(enter, four);
		assertEquals(1, st.size());
		assertEquals(4, st.tableSize());
		assertEquals(enter, st.getKeyAt(0));
		assertEquals(four, st.getValueAt(0));
	}
	
	@Test
	public void test10PutsA() {
		String[] keys1 = {"box", "bear", "be", "band", "bar", "bat", "bed", "bet", "bend", "bond"};
		MyString[] K1 = new MyString[10];
		Integer[] I = new Integer[10];
		for(int i = 0; i < 10; i++) {
			K1[i] = new MyString(keys1[i]);
			I[i] = new Integer(i);
		}
		LinearProbingHashST<MyString, Integer> st = new LinearProbingHashST<MyString, Integer>(4);
		for(int i = 0; i < 10; i++) {
			st.put(K1[i],  I[i]);
		}
		// Keys should now be in slots 1 through 10.
		
		assertEquals(10, st.size());
		for(int i = 0; i < 10; i++)
			assertEquals(I[i], st.get(K1[i]));
		assertEquals(32, st.tableSize());
		for(int i = 0; i < 10; i++) {
			assertEquals(K1[i], st.getKeyAt(i+1));
			assertEquals(I[i], st.getValueAt(i+1));
		}
	}

	@Test
	public void test10PutsB() {
		// A sequence of keys to be inserted in order.
		String[] keys2 = {"band", "car", "eat", "ant", "box", "fox", "dear", "gate", "hat", "ice"};
		// The slots where those keys should end up.
		int[] slot2 = {1, 2, 4, 0, 3, 5, 6, 7, 8, 9};
		MyString[] K2 = new MyString[10];
		Integer[] I = new Integer[10];
		for(int i = 0; i < 10; i++) {
			K2[i] = new MyString(keys2[i]);
			I[i] = new Integer(i);
		}
		
		LinearProbingHashST<MyString, Integer> st = new LinearProbingHashST<MyString, Integer>(4);
		for(int i = 0; i < 10; i++) {
			st.put(K2[i],  I[i]);
		}
		assertEquals(10, st.size());
		for(int i = 0; i < 10; i++)
			assertEquals(I[i], st.get(K2[i]));
		assertEquals(32, st.tableSize());
		for(int i = 0; i < 10; i++) {
			assertEquals(K2[i], st.getKeyAt(slot2[i]));
			assertEquals(I[i], st.getValueAt(slot2[i]));
		}
	}
	
	@Test
	public void test10SimpleReuse() {
		String[] keys1 = {"box", "bear", "be", "band", "bar", "bat", "bed", "bet", "bend", "bond"};
		MyString[] K1 = new MyString[10];
		Integer[] I = new Integer[10];
		for(int i = 0; i < 10; i++) {
			K1[i] = new MyString(keys1[i]);
			I[i] = new Integer(i);
		}
		LinearProbingHashST<MyString, Integer> st = new LinearProbingHashST<MyString, Integer>(4);
		// Insert and delete 10 keys that hash to the same slot.
		// Because of slot reuse, the table size should remain at 4.
		for(int i = 0; i < 10; i++) {
			st.put(K1[i], I[i]);
			assertEquals(I[i], st.get(K1[i]));
			assertTrue(st.contains(K1[i]));
			assertEquals(1, st.size());
			assertEquals(4, st.tableSize());
			assertEquals(K1[i], st.getKeyAt(1));
			
			st.delete(K1[i]);
			assertFalse(st.contains(K1[i]));
			assertEquals(0, st.size());
			assertEquals(4, st.tableSize());
			assertNotNull(st.getKeyAt(1));
			assertNull(st.getValueAt(1));
		}
	}
	
	@Test
	public void test10ComplexReuse() {
		String[] keys1 = {"box", "bear", "be", "band", "bar", "bat", "bed", "bet", "bend", "bond"};
		MyString[] K1 = new MyString[10];
		Integer[] I = new Integer[10];
		for(int i = 0; i < 10; i++) {
			K1[i] = new MyString(keys1[i]);
			I[i] = new Integer(i);
		}
		LinearProbingHashST<MyString, Integer> st = new LinearProbingHashST<MyString, Integer>(6);
		st.put(K1[0], I[0]);
		st.put(K1[1],  I[1]);
		
		for(int i = 0; i < 8; i++) {
			// Delete the older key
			st.delete(K1[i]);
			assertEquals(1, st.size());
			assertEquals(6, st.tableSize());
			// Replace value for the newer key
			st.put(K1[i+1],  (i+1)*11);
			assertEquals(1, st.size());
			assertEquals(6, st.tableSize());
			// Add a new key
			st.put(K1[i+2], I[i+2]);
			assertEquals(2, st.size());
			assertEquals(6, st.tableSize());
			assertEquals(new Integer((i+1)*11), st.get(K1[i+1]));
			assertEquals(I[i+2], st.get(K1[i+2]));
		}
	}
	
	@Test
	public void test10ResizingA() {
		String[] keys1 = {"apple", "box", "car", "dog", "ear", "fox", "gate", "hot", "ice", "jail"};
		MyString[] K1 = new MyString[10];
		Integer[] I = new Integer[10];
		for(int i = 0; i < 10; i++) {
			K1[i] = new MyString(keys1[i]);
			I[i] = new Integer(i);
		}
		
		LinearProbingHashST<MyString, Integer> st = new LinearProbingHashST<MyString, Integer>(4);
		for(int i = 0; i < 10; i++)
			st.put(K1[i], I[i]);
		for(int i = 0; i < 7; i++)
			st.delete(K1[i]);
		for(int i = 7; i < 10; i++)
			assertEquals(I[i], st.get(K1[i]));
		assertEquals(16, st.tableSize());
		assertEquals(3, st.size());
	}
	
	@Test
	public void test10ResizingB() {
		String[] keys1 = {"apple", "box", "car", "dog", "ear", "fox", "gate", "hot", "ice", "jail"};
		MyString[] K1 = new MyString[10];
		Integer[] I = new Integer[10];
		for(int i = 0; i < 10; i++) {
			K1[i] = new MyString(keys1[i]);
			I[i] = new Integer(i);
		}
		
		LinearProbingHashST<MyString, Integer> st = new LinearProbingHashST<MyString, Integer>(4);
		for(int i = 9; i >= 0; i--)
			st.put(K1[i], I[i]);
		for(int i = 9; i > 2; i--)
			st.delete(K1[i]);
		for(int i = 0; i < 3; i++)
			assertEquals(I[i], st.get(K1[i]));
		assertEquals(16, st.tableSize());
		assertEquals(3, st.size());
	}
	
	@Test
	public void test10Iterator() {
		String[] keys1 = {"box", "bear", "be", "band", "bar", "bat", "bed", "bet", "bend", "bond"};
		MyString[] K1 = new MyString[10];
		Integer[] I = new Integer[10];
		TreeSet<MyString> set1 = new TreeSet<MyString>();
		for(int i = 0; i < 10; i++) {
			K1[i] = new MyString(keys1[i]);
			I[i] = new Integer(i);
		}
		LinearProbingHashST<MyString, Integer> st = new LinearProbingHashST<MyString, Integer>(4);
		TreeSet<MyString> set2 = new TreeSet<MyString>();
		// Add all the keys one at a time to the symbol table and to set1.
		for(int i = 0; i < 10; i++) {
			st.put(K1[i], I[i]);
			set1.add(K1[i]);
			// Now iterate through symbol tables keys, adding to set2
			Iterable<MyString> it = st.keys();
			set2.clear();
			for(MyString myS : it) {
				set2.add(myS);
			}
			// Verify set1 and set2
			assertEquals(set1, set2);
		}
		// Remove some keys one at at time from the symbol table and from set1
		for(int i = 9; i > 0; i-= 2) {
			st.delete(K1[i]);
			set1.remove(K1[i]);
			// Now iterate through symbol tables keys, adding to set2
			Iterable<MyString> it = st.keys();
			set2.clear();
			for(MyString myS : it) {
				set2.add(myS);
			}
			// Verify set1 and set2
			assertEquals(set1, set2);
		}
	}
	
	@Test
	public void test10Sequence() {
		String[] keys1 = {"apple", "box", "car", "dog", "ear", "fox", "gate", "hot", "ice", "jail"};
		String[] keys2 = {"day", "ham", "and", "angle", "bear", "zoo", "yawn", "water", "vase", "up"};
		MyString[] K1 = new MyString[10];
		MyString[] K2 = new MyString[10];
		Integer[] I = new Integer[10];
		for(int i = 0; i < 10; i++) {
			K1[i] = new MyString(keys1[i]);
			K2[i] = new MyString(keys2[i]);
			I[i] = new Integer(10-i);
		}
		
		LinearProbingHashST<MyString, Integer> st = new LinearProbingHashST<MyString, Integer>(4);
		// Insert keys1 in order
		for(int i = 9; i >= 0; i--)
			st.put(K1[i], I[i]);
		/*
		0		1		2		3		4		5		6		7		8		9		10		11
		apple	box		car		dog		ear		fox		gate	hot		ice		jail
		10		9		8		7		6		5		4		3		2		1
		*/
		assertEquals(K1[1], st.getKeyAt(1));	// verify box is in slot 1
		
		
		st.delete(K1[1]);						// delete box
		/*
		0		1		2		3		4		5		6		7		8		9		10		11
		apple	box		car		dog		ear		fox		gate	hot		ice		jail
		10		null	8		7		6		5		4		3		2		1
		*/
		assertNull(st.get(K1[1]));				// verify box is deleted
		
		st.put(K2[0], I[0]);					// add day (slot 10)
		/*
		0		1		2		3		4		5		6		7		8		9		10		11
		apple	box		car		dog		ear		fox		gate	hot		ice		jail	day
		10		null	8		7		6		5		4		3		2		1		10
		*/
		assertEquals(K2[0], st.getKeyAt(10));	// verify day is in slot 10
		assertEquals(I[0], st.get(K2[0]));		// verify you can find day
		
		st.delete(K1[6]);						// delete gate
		/*
		0		1		2		3		4		5		6		7		8		9		10		11
		apple	box		car		dog		ear		fox		gate	hot		ice		jail	day
		10		null	8		7		6		5		null	3		2		1		10
		*/
		assertNull(st.get(K1[6]));				// verify gate is deleted
		assertEquals(K1[7], st.getKeyAt(7));	// verify hot is still in slot 7
		assertEquals(I[0], st.get(K2[0]));		// verify you can still find day
		
		st.put(K2[1], I[1]);					// add ham (slot 11)
		/*
		0		1		2		3		4		5		6		7		8		9		10		11
		apple	box		car		dog		ear		fox		gate	hot		ice		jail	day		ham
		10		null	8		7		6		5		null	3		2		1		10		9
		*/
		assertEquals(K2[1], st.getKeyAt(11));	// verify ham is in slot 11.
		
		st.put(K2[2], I[2]);					// add and (slot 1)
		/*
		0		1		2		3		4		5		6		7		8		9		10		11
		apple	and		car		dog		ear		fox		gate	hot		ice		jail	day		ham
		10		8		8		7		6		5		null	3		2		1		10		9
		*/
		assertEquals(K2[2], st.getKeyAt(1));	// verify and is in slot 1
		assertEquals(I[2], st.get(K2[2]));		// verify you can find and
		
		st.put(K2[3], I[3]);					// add angle (slot 6)
		/*
		0		1		2		3		4		5		6		7		8		9		10		11
		apple	and		car		dog		ear		fox		angle	hot		ice		jail	day		ham
		10		8		8		7		6		5		7		3		2		1		10		9
		*/
		assertEquals(K2[3], st.getKeyAt(6));	// verify angle is in slot 6
		assertEquals(I[3], st.get(K2[3]));		// verify you can find angle
		
		st.put(K2[4], I[4]);					// add bear (slot 12)
		assertEquals(K2[4], st.getKeyAt(12));	// verify bear is in slot 12
		assertEquals(I[4], st.get(K2[4]));		// verify you can find bear
	}
}
