package hw5;

import static org.junit.Assert.*;

import org.junit.Test;

public class HW5TestZ {

	@Test
	public void toyTest() {
		LinearProbingHashST<Integer, String> st = new LinearProbingHashST<Integer, String>(4);
		/* Arrays should look like:
		 *        0       1       2       3
		 * keys  null    null    null    null
		 * vals  null    null    null    null
		 */
		st.put(7, "seven");
		st.put(2, "two");
		/* Arrays should look like:
		 *        0       1       2       3
		 * keys  null    null     2       7
		 * vals  null    null    two    seven
		 */
		st.put(6, "six");
		/* Arrays should look like:
		 *        0       1       2       3       4       5       6       7
		 * keys  null    null     2      null    null    null     6       7
		 * vals  null    null    two     null    null    null    six    seven
		 */
		// verify you can find 7, 2, and 6
		assertEquals("seven", st.get(7));
		assertEquals("two", st.get(2));
		assertEquals("six", st.get(6));
		st.delete(2);
		/* Arrays should look like:
		 *        0       1       2       3       4       5       6       7
		 * keys  null    null     2      null    null    null     6       7
		 * vals  null    null    null    null    null    null    six    seven
		 */
		// verify box was deleted
		assertFalse(st.contains(2));
		int sevenCount = 0;
		int twoCount = 0;
		int sixCount = 0;
		// loop through the table looking for keys
		// and verifying corresponding values
		for(int i = 0; i < st.tableSize(); i++) {
			Integer k = st.getKeyAt(i);
			if (k != null) {
				if (k.equals(7)) {
					assertEquals("seven", st.getValueAt(i));
					sevenCount++;
				} else if (k.equals(2)) {
					assertNull(st.getValueAt(i));
					twoCount++;
				} else if (k.equals(6)) {
					assertEquals("six", st.getValueAt(i));
					sixCount++;
				} else {
					fail();
				}
			}
		}
		// verify each key appears exactly once.
		assertEquals(1, sevenCount);
		assertEquals(1, twoCount);
		assertEquals(1, sixCount);
	}
}
