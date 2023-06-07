package adt.heap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import adt.heap.extended.FloorCeilHeapImpl;

public class StudentMaxHeapTest {

	Heap<Integer> heap;
	FloorCeilHeapImpl floorCeil;

	@Before
	public void setUp() {
		// TODO Instancie seu comparator para fazer sua estrutura funcionar como
		// uma max heap aqui. Use instanciacao anonima da interface
		// Comparator!!!!
		Comparator<Integer> comparator = new ComparatorMaxHeap<Integer>();
		heap = new HeapImpl<Integer>(comparator);
		floorCeil = new FloorCeilHeapImpl(comparator);
	}

	@Test
	public void testBuild() {
		heap.buildHeap(new Integer[] { 82, 6, 99, 12, 34, 64, 58, 1 });

		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());

		//verifyHeap(new Integer[] { 99, 12, 82, 6, 34, 64, 58, 1 });
		verifyHeap(new Integer[] { 99, 34, 82, 6, 12, 64, 58, 1 });
	}

	@Test
	public void testInsert() {
		heap.insert(8);
		heap.insert(12);
		heap.insert(-2);
		heap.insert(7);
		heap.insert(8);
		heap.insert(null);
		heap.insert(-5);
		heap.insert(14);
		heap.insert(3);
		heap.insert(-10);
		heap.insert(0);

		assertEquals(10, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 14, 8, 12, 7, 8, -5, -2, 3, -10, 0 });
	}
	
	@Test
	public void testInsertWithResize() {
		heap.insert(8);
		heap.insert(12);
		heap.insert(-2);
		heap.insert(7);
		heap.insert(8);
		heap.insert(-5);
		heap.insert(14);
		heap.insert(3);
		heap.insert(-10);
		heap.insert(0);
		heap.insert(20);
		heap.insert(14);
		heap.insert(16);
		heap.insert(-4);
		heap.insert(17);
		heap.insert(21);
		heap.insert(23);
		heap.insert(-15);
		heap.insert(-18);
		heap.insert(-9);
		heap.insert(30);
		
		assertEquals(21, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 30, 23, 17, 21, 20, 16, 14, 8, -10, 8, 12,
				14, -5, -4, -2, 7, 3, -15, -18, -9, 0 });
	}
	
	@Test
	public void testRemove() {
		heap.insert(22);
		heap.insert(45);
		heap.insert(38);
		heap.insert(17);
		heap.insert(40);
		heap.insert(15);
		heap.insert(26);
		heap.insert(79);
		heap.insert(53);
		heap.insert(30);

		assertEquals(new Integer(79), heap.rootElement());
		assertEquals(new Integer(79), heap.extractRootElement());
		
		assertEquals(new Integer(53), heap.rootElement());
		assertEquals(new Integer(53), heap.extractRootElement());
		
		assertEquals(new Integer(45), heap.rootElement());
		assertEquals(new Integer(45), heap.extractRootElement());
		
		assertEquals(new Integer(40), heap.rootElement());
		assertEquals(new Integer(40), heap.extractRootElement());
		
		assertEquals(new Integer(38), heap.rootElement());
		assertEquals(new Integer(38), heap.extractRootElement());

		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 22, 17, 15, 26, 30 });
	}
	
	@Test
	public void testRemoveEmptyHeap() {
		assertNull(heap.extractRootElement());

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		verifyHeap(new Integer[0]);
	}
	
	@Test
	public void testRootElement() {
		Integer[] array = {22,45,38,17,40,15,26,79,53,30};
		for (Integer number: array) {
			this.heap.insert(number);
		}
		
		assertEquals(new Integer(79), this.heap.rootElement());
	}
	
	@Test
	public void testRootElementinEmptyHeap() {
		assertNull(this.heap.rootElement());
	}

	@Test
	public void testSort() {
		assertArrayEquals(new Integer[] { 5, 6, 12, 20, 34, 43, 49, 92 },
				heap.heapsort(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 }));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertArrayEquals(new Integer[] {}, heap.toArray());
	}
	
	@Test
	public void testSortEmptyHeap() {
		assertArrayEquals(new Integer[0],
				heap.heapsort(new Integer[0]));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertArrayEquals(new Integer[] {}, heap.toArray());
	}
	
	@Test
	public void testSortOneElementHeap() {
		assertArrayEquals(new Integer[] { 5 },
				heap.heapsort(new Integer[] { 5 }));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertArrayEquals(new Integer[] {}, heap.toArray());
	}
	
	
	@Test
	public void testBuildHeap() {
		Integer[] array = {22,45,38,17,40,15,26,79,53,30};
		
		this.heap.insert(30);
		this.heap.insert(40);
		
		this.heap.buildHeap(array);
		
		assertArrayEquals(new Integer[] { 79, 53, 38, 45, 40, 15, 26, 17, 22, 30 }, this.heap.toArray());
		
	}
	
	@Test
	public void testBuildEmptyHeap() {
		Integer[] array = new Integer[0];
		
		this.heap.insert(30);
		this.heap.insert(40);
		
		this.heap.buildHeap(array);
		
		assertArrayEquals(new Integer[0], this.heap.toArray());
		
	}
	
	@Test
	public void testFloor80() {
		Integer[] array = {22,45,38,17,40,15,26,79,53,30};
		
		assertEquals(new Integer(79), this.floorCeil.floor(array, 80));
	}
	
	@Test
	public void testFloor15() {
		Integer[] array = {22,45,38,17,40,15,26,79,53,30};
		
		assertEquals(new Integer(15), this.floorCeil.floor(array, 15));
	}
	
	@Test
	public void testFloor14() {
		Integer[] array = {22,45,38,17,40,15,26,79,53,30};
		
		assertNull(this.floorCeil.floor(array, 14));
	}
	
	@Test
	public void testFloor465() {
		Integer[] array = {22,45,38,17,40,15,26,79,53,30};
		
		assertEquals(new Integer(45), this.floorCeil.floor(array, 46.5));
	}
	
	
	@Test
	public void testCeil50() {
		Integer[] array = {22,45,38,17,40,15,26,79,53,30};
		
		assertEquals(new Integer(53), this.floorCeil.ceil(array, 50));
	}
	
	@Test
	public void testCeil79() {
		Integer[] array = {22,45,38,17,40,15,26,79,53,30};
		
		assertEquals(new Integer(79), this.floorCeil.ceil(array, 79));
	}
	
	@Test
	public void testCeil80() {
		Integer[] array = {22,45,38,17,40,15,26,79,53,30};
		
		assertNull(this.floorCeil.ceil(array, 80));
	}

	@Test
	public void testCeil465() {
		Integer[] array = {22,45,38,17,40,15,26,79,53,30};
		
		assertEquals(new Integer(53), this.floorCeil.ceil(array, 46.5));
	}
	
	private void verifyHeap(Integer[] expected) {
		boolean isHeap = true;

		Comparable<Integer>[] original = heap.toArray();

		Arrays.sort(expected);
		Arrays.sort(original);

		if (Arrays.equals(expected, original) == false)
			isHeap = false;

		original = heap.toArray();

		for (int i = 0; i < original.length; i++) {
			if (2 * i + 1 < original.length && original[i].compareTo((Integer) original[2 * i + 1]) < 0)
				isHeap = false;
			if (2 * i + 2 < original.length && original[i].compareTo((Integer) original[2 * i + 2]) < 0)
				isHeap = false;
		}

		assertTrue(isHeap);
	}
}
