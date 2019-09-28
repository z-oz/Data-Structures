package hw3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

public class HW3Test {
	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);

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
	
	@Test
	public void test10HiLo() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(-4);
		s.put(-222);
		for(int i = -1000; i < 1000; i++) {
			if (i == -4 || i == -222)
				assertTrue(s.contains(i));
			else
				assertFalse(s.contains(i));
		}
		assertEquals("(-222|-4)", s.levelOrder());
	}
	
	@Test
	public void test10ThreeToHi() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(1);
		s.put(2);
		s.put(3);
		for(int i = -1000; i < 1000; i++) {
			if (i >= 1 && i <= 3)
				assertTrue(s.contains(i));
			else
				assertFalse(s.contains(i));
		}
		assertEquals("(2),(1),(3)", s.levelOrder());
	}
	
	@Test
	public void test10ThreeToMid() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(3);
		s.put(1);
		s.put(2);
//		System.out.println(s.levelOrder());
		for(int i = -1000; i < 1000; i++) {
			if (i >= 1 && i <= 3)
				assertTrue(s.contains(i));
			else
				assertFalse(s.contains(i));
		}
		assertEquals("(2),(1),(3)", s.levelOrder());
	}
	
	@Test
	public void test10ThreeToLo() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(2);
		s.put(3);
		s.put(1);
		for(int i = -1000; i < 1000; i++) {
			if (i >= 1 && i <= 3)
				assertTrue(s.contains(i));
			else
				assertFalse(s.contains(i));
		}
		assertEquals("(2),(1),(3)", s.levelOrder());
	}
	
	@Test
	public void test10MaxDepthOne() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(100);
		s.put(150);
		s.put(200);
		s.put(250);
		s.put(300);
		s.put(88);
		s.put(201);
		s.put(251);
		
		assertEquals("(150|250),(88|100),(200|201),(251|300)", s.levelOrder());
	}
	
	
	
	@Test
	public void test10UpTo10() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		for(int i = 1; i <= 10; i++)
			s.put(i);
		for(int i = -1000; i < 1000; i++) {
			if (i >= 1 && i <= 10)
				assertTrue(s.contains(i));
			else
				assertFalse(s.contains(i));
		}
		assertEquals("(4),(2),(6|8),(1),(3),(5),(7),(9|10)", s.levelOrder());
	}
	
	@Test
	public void test10DownFrom10() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		for(int i = 10; i >= 1; i--)
			s.put(i);
		for(int i = -1000; i < 1000; i++) {
			if (i >= 1 && i <= 10)
				assertTrue(s.contains(i));
			else
				assertFalse(s.contains(i));
		}
		assertEquals("(7),(3|5),(9),(1|2),(4),(6),(8),(10)", s.levelOrder());
	}
	
	@Test
	public void test10RandomInserts() {
		ArrayList<Integer> in = new ArrayList<Integer>(120);
		ArrayList<Integer> out = new ArrayList<Integer>(120);
		for(int i = 0; i < 120; i++) {
			in.add(i*7+3);  // contains numbers like 3, 10, 17, 24, 31
			out.add(i*7+i%3);  // does NOT contain any numbers from in
		}
		for(int run = 0; run < 120; run++) {
			Collections.shuffle(in);
			TwoThreeIntSet s = new TwoThreeIntSet();
			for(int pos = 0; pos < 120; pos++)
				s.put(in.get(pos));
			assertTrue(s.getHeight() < 7);
			Collections.shuffle(in);
			Collections.shuffle(out);
			for(int pos = 0; pos < 120; pos++) {
				assertTrue(s.contains(in.get(pos)));
				assertFalse(s.contains(out.get(pos)));
			}
		}
	}
}
