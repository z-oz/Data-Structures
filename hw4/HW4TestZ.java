package hw4;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.TreeMap;
//import java.

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.TreeMap;
public class HW4TestZ {
		
	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);
	
	@Test
	public void testToyTest() {		
		PriceQueue pq = new PriceQueue();
		for(int i = 0; i < 10; i++)
			pq.enqueue(new Price(i,i));
		for(int i = 0; i < 10; i+=2)
			pq.delete(new Price(i,i));
		assertEquals("$1.01 $3.03 $5.05 $7.07 $9.09 ", pq.toString());
		for(int i = 1; i < 10; i+=2)
			assertEquals(new Price(i,i), pq.dequeue());
	}
	
	@Test
	public void testTiming() {
		final int SIZE = 1000000;
		Price[] prices = new Price[SIZE*2];
		for (int i = 0; i < SIZE*2; i++)
			prices[i] = new Price(i/100, i%100);
		PriceQueue pq = new PriceQueue();
		for(int i = 0; i < SIZE; i++)
			pq.enqueue(prices[i]);
		long start1 = System.currentTimeMillis();
		for(int i = SIZE/2; i < SIZE; i++) {
			pq.delete(prices[i]);
			pq.enqueue(prices[i]);
		}
		long finish1 = System.currentTimeMillis();
		long time1 = finish1 - start1;
		System.out.println("time1 = " + time1);
		
		pq = new PriceQueue();
		for(int i = 0; i < 2*SIZE; i++)
			pq.enqueue(prices[i]);
		long start2 = System.currentTimeMillis();
		for(int i = (3*SIZE)/2; i < 2*SIZE; i++) {
			pq.delete(prices[i]);
			pq.enqueue(prices[i]);
		}
		long finish2 = System.currentTimeMillis();
		long time2 = finish2 - start2;
		assertFalse(time2 > time1 * 1.5);
		System.out.println("time2 = " + time2);
	}
	
	@Test
	public void testCompareTo() {
		Price z = new Price(10, 7);
		Price anotherOne = new Price(10, 6);
		System.out.println(z.compareTo(anotherOne));
	}
	
	@Test
	public void deleteOne() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(new Price(10,01));
		assertEquals(1, pq.size());
		pq.dequeue();
		pq.delete(new Price(10,01));
		assertEquals(0, pq.size());
	}
	
	@Test
	public void deleteTwo() {
		PriceQueue pq = new PriceQueue();
		assertEquals(0, pq.size());
		pq.enqueue(new Price(10,01));
		pq.enqueue(new Price(10,01));
		pq.enqueue(new Price(10,02));
		assertEquals(2, pq.size());
		pq.dequeue();
		pq.delete(new Price(10,01));
		pq.dequeue();
		pq.delete(new Price(10,02));
		assertEquals(0, pq.size());
	}
	
	@Test
	public void deleteThree() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(new Price(10,01));
		pq.enqueue(new Price(10,02));
		pq.enqueue(new Price(10,03));
		assertEquals(3, pq.size());
		pq.dequeue();
		pq.delete(new Price(10,01));
		pq.dequeue();
		pq.delete(new Price(10,02));
		pq.dequeue();
		pq.delete(new Price(10,03));
		assertEquals(0, pq.size());
	}
	
	@Test
	public void mixedSquence() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(new Price(10,01));
		pq.enqueue(new Price(10,02));
		pq.enqueue(new Price(10,03));
		pq.dequeue();
		pq.delete(new Price(10,01));
		pq.enqueue(new Price(10,04));
		pq.dequeue();
		pq.delete(new Price(10,02));
		pq.dequeue();
		pq.delete(new Price(10,03));
		assertEquals(1, pq.size());
		pq.dequeue();
		pq.delete(new Price(10,04));
		assertEquals(0, pq.size());
		pq.enqueue(new Price(10,05));
		pq.enqueue(new Price(10,06));
		pq.enqueue(new Price(10,07));
		pq.dequeue();
		pq.delete(new Price(10,05));
		pq.dequeue();
		pq.delete(new Price(10,06));
		pq.dequeue();
		pq.delete(new Price(10,07));
		assertEquals(0, pq.size());
	}
	
	@Test
	public void deleteEmpty() {
		PriceQueue pq = new PriceQueue();
		assertFalse(pq.delete(new Price(0,0)));
	}
}
