package hw4;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class HW4Test {
	
	//@Rule
    //public Timeout globalTimeout = Timeout.seconds(10);
	Price p101 = new Price(1,1);
	Price p202 = new Price(2,2);
	Price p303 = new Price(3,3);
	Price p404 = new Price(4,4);
	Price p505 = new Price(5,5);
	
	@Test
	public void test05NewPriceQueue() {
		PriceQueue pq = new PriceQueue();
		assertTrue(pq.isEmpty());
		assertEquals(0, pq.size());
	}
	
	@Test
	public void test05Exception() {
		PriceQueue pq = new PriceQueue();
		try {
			pq.dequeue();
			fail();
		} catch (NoSuchElementException e) {
		}
		pq.enqueue(p101);
		assertEquals(p101, pq.dequeue());
		try {
			pq.dequeue();
			fail();
		} catch (NoSuchElementException e) {
		}
		pq = new PriceQueue();
		pq.enqueue(p101);
		assertTrue(pq.delete(p101));
		try {
			pq.dequeue();
			fail();
		} catch (NoSuchElementException e) {
		}
	}
	
	@Test
	public void test05EnqDeqDel() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		assertFalse(pq.isEmpty());
		assertEquals(1, pq.size());
		assertEquals(p101, pq.dequeue());
		assertTrue(pq.isEmpty());
		assertEquals(0, pq.size());
		assertFalse(pq.delete(p101));
	}
	
	@Test
	public void test05EnqDelDeq() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		assertFalse(pq.isEmpty());
		assertEquals(1, pq.size());
		assertFalse(pq.delete(p202));
		assertTrue(pq.delete(p101));
		assertTrue(pq.isEmpty());
		assertEquals(0, pq.size());
		assertFalse(pq.delete(p101));
	}
	
	@Test
	public void test05Enq1Enq2Del2Deq() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		assertFalse(pq.isEmpty());
		assertEquals(2, pq.size());
		assertTrue(pq.delete(p202));
		assertFalse(pq.isEmpty());
		assertEquals(1, pq.size());
		assertEquals(p101, pq.dequeue());
		assertEquals(0, pq.size());
		assertFalse(pq.delete(p101));
		assertFalse(pq.delete(p202));
	}
	
	@Test
	public void test05Enq1Enq2Del1Deq() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		assertFalse(pq.isEmpty());
		assertEquals(2, pq.size());
		assertTrue(pq.delete(p101));
		assertFalse(pq.isEmpty());
		assertEquals(1, pq.size());
		assertEquals(p202, pq.dequeue());
		assertEquals(0, pq.size());
		assertFalse(pq.delete(p101));
		assertFalse(pq.delete(p202));
	}
	
	@Test
	public void test05EnqDeqEnqDeq() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		assertEquals(p101, pq.dequeue());
		pq.enqueue(p202);
		assertEquals(p202, pq.dequeue());
	}
	
	@Test
	public void test05EnqDeqEnqDel() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		assertEquals(p101, pq.dequeue());
		pq.enqueue(p202);
		assertTrue(pq.delete(p202));
	}
	
	@Test
	public void test05EnqDelEnqDel() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		assertTrue(pq.delete(p101));
		pq.enqueue(p202);
		assertTrue(pq.delete(p202));
	}
	
	@Test
	public void test05EnqDelEnqDeq() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		assertTrue(pq.delete(p101));
		pq.enqueue(p202);
		assertEquals(p202, pq.dequeue());
	}
	
	@Test
	public void test05DeqThenDeleteInOrder() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertEquals(5, pq.size());
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(0, pq.size());
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertEquals(5, pq.size());
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p505));
		assertEquals(0, pq.size());
	}
	
	@Test
	public void test05DeqThenDeleteReverseOrder() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertEquals(5, pq.size());
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(0, pq.size());
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertEquals(5, pq.size());
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p101));
		assertEquals(0, pq.size());
	}
	
	@Test
	public void test05DelInOrderThenDeq() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertEquals(5, pq.size());
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p505));
		assertEquals(0, pq.size());
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertEquals(5, pq.size());
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(0, pq.size());
	}
	
	@Test
	public void test05DelReverseOrderThenDeq() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertEquals(5, pq.size());
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p101));
		assertEquals(0, pq.size());
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertEquals(5, pq.size());
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(0, pq.size());
	}
	
	@Test
	public void test05DeleteMiddleInOrder() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p404));
		assertEquals(p101, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		
		pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p404));
		pq.enqueue(p202);
		pq.enqueue(p303);
		assertEquals(p101, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
	}
	
	@Test
	public void test05DeleteMiddleRevOrder() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p202));
		assertEquals(p101, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		
		pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p202));
		pq.enqueue(p202);
		pq.enqueue(p303);
		assertEquals(p101, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
	}
	
	@Test
	public void test05DeleteFrontInOrder() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p303));
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		
		pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p101));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p303));
		pq.enqueue(p101);
		pq.enqueue(p202);
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
	}
		
	@Test
	public void test05DeleteFrontRevOrder() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p101));
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		
		pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p202));
		assertTrue(pq.delete(p101));
		pq.enqueue(p101);
		pq.enqueue(p202);
		assertEquals(p404, pq.dequeue());
		assertEquals(p505, pq.dequeue());
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
	}
	
	@Test
	public void test05DeleteBackInOrder() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p505));
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		
		pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p303));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p505));
		pq.enqueue(p303);
		pq.enqueue(p404);
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p404, pq.dequeue());
	}
	
	@Test
	public void test05DeleteBackRevOrder() {
		PriceQueue pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p303));
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		
		pq = new PriceQueue();
		pq.enqueue(p101);
		pq.enqueue(p202);
		pq.enqueue(p303);
		pq.enqueue(p404);
		pq.enqueue(p505);
		assertTrue(pq.delete(p505));
		assertTrue(pq.delete(p404));
		assertTrue(pq.delete(p303));
		pq.enqueue(p303);
		pq.enqueue(p404);
		assertEquals(p101, pq.dequeue());
		assertEquals(p202, pq.dequeue());
		assertEquals(p303, pq.dequeue());
		assertEquals(p404, pq.dequeue());
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
	public void test05ToyTest() {
		PriceQueue pq = new PriceQueue();
		for(int i = 0; i < 10; i++)
			pq.enqueue(new Price(i,i));
		for(int i = 0; i < 10; i+=2)
			pq.delete(new Price(i,i));
		assertEquals("$1.01 $3.03 $5.05 $7.07 $9.09 ", pq.toString());
		for(int i = 1; i < 10; i+=2)
			assertEquals(new Price(i,i), pq.dequeue());
	}
}
