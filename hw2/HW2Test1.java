package hw2;
import static org.junit.Assert.*;

import org.junit.Test;

public class HW2Test1 {

	
	// create a tree from a string
	public static IntTree fromString (String ints) {
		IntTree tree = new IntTree ();
		for (String s : ints.split (" "))
			tree.put (Integer.parseInt (s));
		return tree;
	}
        
	/**
	 * A toy test function.  Tests that size behaves correctly on the empty
	 * tree and on a tree with only one node.  Use this only as a template
	 * for your more thorough tests.
	 */
	@Test
	public void testSingleKey() {
		IntTree tree = new IntTree();
		assertEquals(0,  tree.size());
		tree.put(42);
		assertEquals(1, tree.size());
	}
	
	@Test
	public void testLargerTree() {
		IntTree tree = fromString("5 3 7 4");
		assertEquals(4,  tree.size());
		tree.put(42);
		assertEquals(5, tree.size());
	}
	
	// Write some more test functions below.
	// Make sure to test all of the functions your wrote on various
	// trees of different shapes and sizes.
	
	@Test
	public void testHeight() {
		IntTree tree = new IntTree();
		assertEquals(-1, tree.height());
		tree.put(1);
		assertEquals(1, tree.size());
		assertEquals(0, tree.height());
	}
	
	@Test
	public void testHeightAdvanced() {
		IntTree treeOne = fromString("50 25 100 12 37 150 127");
		IntTree treeTwo = fromString("50 17 76 9 23 54 14 19 72 12 67 11 10");
		assertEquals(3, treeOne.height());
		assertEquals(6, treeTwo.height());
	}
	
	@Test
	public void testSizeOdd() {
		IntTree treeOne = fromString("50 25 100 12 37 150 127");
		IntTree treeTwo = fromString("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14");
		IntTree treeThree = new IntTree();
		assertEquals(3, treeOne.sizeOdd());
		assertEquals(7, treeTwo.sizeOdd());
		assertEquals(0, treeThree.sizeOdd());
		treeThree.put(2);
		assertEquals(0, treeThree.sizeOdd());
	}
	
	@Test
	public void testEquals() {
		IntTree treeOne = fromString("50 25 100 12 37 150 127");
		IntTree treeTwo = fromString("50 25 100 12 37 150 127");
		assertTrue(treeOne.treeEquals(treeTwo));
		treeOne.put(128);
		assertFalse(treeOne.treeEquals(treeTwo));
	}
	
	@Test
	public void testAtDepth() {
		IntTree treeOne = fromString("50 25 100 12 37 150 127");
		IntTree treeTwo = fromString("40 25 78 10 32 50 93 3 17 30 38 2 39");
		assertEquals(1, treeOne.sizeAtDepth(0));
		assertEquals(2, treeOne.sizeAtDepth(1));
		assertEquals(3, treeOne.sizeAtDepth(2)); 
		assertEquals(1, treeOne.sizeAtDepth(3)); 
		assertEquals(0, treeOne.sizeAtDepth(4)); 
		assertEquals(0, treeOne.sizeAtDepth(6));
		assertEquals(2, treeTwo.sizeAtDepth(4));
	}
	
	@Test
	public void testAboveDepth() {
		IntTree treeOne = fromString("50 25 100 12 37 150 127");
		IntTree treeTwo = fromString("60 20 80 10 30 70 90 5 15 25 35 65 75 85 95 96");
		assertEquals(0, treeOne.sizeAboveDepth(0));
		assertEquals(1, treeOne.sizeAboveDepth(1));
		assertEquals(3, treeOne.sizeAboveDepth(2));
		assertEquals(6, treeOne.sizeAboveDepth(3));
		assertEquals(7, treeOne.sizeAboveDepth(4));
		assertEquals(7, treeOne.sizeAboveDepth(6));
		assertEquals(7, treeTwo.sizeAboveDepth(3));
		assertEquals(15, treeTwo.sizeAboveDepth(4));
	}
	
	@Test
	public void testBalance() {
		IntTree treeOne = fromString("50 25 100 12 37 99 127");
		IntTree treeTwo = fromString("60 20 80 10 30 70 90 5 15 25 35 65 75 85 95");
		IntTree treeThree = fromString("50 17 76 9 23 54 14 19 72 12 67");
		IntTree treeFour = fromString("40 20 60 10 30 50 70");
		assertTrue(treeOne.isPerfectlyBalancedS());
		assertTrue(treeTwo.isPerfectlyBalancedS());
		assertFalse(treeThree.isPerfectlyBalancedS());
		treeFour.put(71);
		treeFour.removeOddSubtrees();
		assertTrue(treeFour.isPerfectlyBalancedS());
	}
	
	@Test
	public void testRemoveOddSubtree() {
		IntTree treeOne = fromString("50 25 100 12 37 150 127");
		IntTree treeTwo = fromString("40 20 60 11 29 51 69");
		IntTree treeThree = fromString("50 17 76 9 23 54 14 19 73 12 67");
		treeOne.removeOddSubtrees();
		treeTwo.removeOddSubtrees();
		treeThree.removeOddSubtrees();
		assertEquals(3, treeOne.size());
		assertEquals(3, treeTwo.size());
		treeThree.put(55);
		treeThree.removeOddSubtrees();
		assertEquals(3, treeTwo.size());
//		System.out.println(treeOne); // 50 100 150
//		System.out.println(treeTwo); // 40 20 60
//		System.out.println(treeThree); // 50 76 54
	
	}
}
