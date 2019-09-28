package hw3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class HW3TestZ {

	@Test
	public void test5Empty() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		for(int i = -1000; i < 1000; i++)
			assertFalse(s.contains(i));
		assertEquals("", s.levelOrder());
	}
	
	@Test
	public void test5Single() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(-4);
		for(int i = -1000; i < 1000; i++) {
			if (i == -4)
				assertTrue(s.contains(i));
			else
				assertFalse(s.contains(i));
		}
		for(int i = 0; i < 100; i++)
			s.put(-4);
		assertEquals(0, s.getHeight());
		assertEquals("(-4)", s.levelOrder());
	}
	
	@Test
	public void test10LoHi() {
		TwoThreeIntSet s = new TwoThreeIntSet();       
		s.put(4);
		s.put(222);
		for(int i = -1000; i < 1000; i++) {
			if (i == 4 || i == 222)
				assertTrue(s.contains(i));
			else
				assertFalse(s.contains(i));
		}
		assertEquals("(4|222)", s.levelOrder());
	}
	
//	Our tests
	
	@Test
	public void testContains() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		for(int i = -123456; i < 123456; i+=2) {
			s.put(i);
			assertTrue(s.contains(i));
		}
		for(int i = -123455; i < 123456; i+=2) {
			assertFalse(s.contains(i));
		}
	}
	
	@Test
	public void testOne() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(1);
		assertEquals("(1)", s.levelOrder());
		s.put(3);
		assertEquals("(1|3)", s.levelOrder());
		s.put(0);
		assertEquals("(1),(0),(3)", s.levelOrder());
		s.put(2);
		assertEquals("(1),(0),(2|3)", s.levelOrder());
		assertTrue(s.contains(1));
		assertTrue(s.contains(3));
		assertFalse(s.contains(4));
	}
	
	@Test
	public void testTwo() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(50);
		assertTrue(s.contains(50));
		s.put(60);
		assertTrue(s.contains(60));;
		s.put(70);
		assertTrue(s.contains(70));
		s.put(40);
		assertTrue(s.contains(40));
		s.put(30);
		assertTrue(s.contains(30));
		assertEquals("(40|60),(30),(50),(70)", s.levelOrder());
		s.put(20);
		assertEquals("(40|60),(20|30),(50),(70)", s.levelOrder());
		s.put(10); // fail
		assertEquals("(40),(20),(60),(10),(30),(50),(70)", s.levelOrder());
		s.put(80);
		assertEquals("(40),(20),(60),(10),(30),(50),(70|80)", s.levelOrder());
		s.put(90);
		assertEquals("(40),(20),(60|80),(10),(30),(50),(70),(90)", s.levelOrder());
		s.put(100);
		assertEquals("(40),(20),(60|80),(10),(30),(50),(70),(90|100)", s.levelOrder());
	}
	
	@Test
	public void testThree() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(50);
		assertEquals("(50)", s.levelOrder());
		s.put(35);
		assertEquals("(35|50)", s.levelOrder());
		s.put(75);
		assertEquals("(50),(35),(75)", s.levelOrder());
		s.put(25);
		assertEquals("(50),(25|35),(75)", s.levelOrder());
		s.put(20);
		assertEquals("(25|50),(20),(35),(75)", s.levelOrder());
		s.put(10);
		assertEquals("(25|50),(10|20),(35),(75)", s.levelOrder());
		s.put(15); // fail
		assertEquals("(25),(15),(50),(10),(20),(35),(75)", s.levelOrder());
	}
	
	@Test
	public void testFour() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(100);
		s.put(200);
		s.put(230);
		s.put(30);
		s.put(60);
		s.put(10);
		s.put(45);
		s.put(72);
		s.put(70);
		s.put(240);
		assertEquals("(60),(30),(72|200),(10),(45),(70),(100),(230|240)", s.levelOrder());
	}
}
