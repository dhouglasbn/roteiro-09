package adt.heap.extended;

import java.util.Comparator;

import adt.heap.ComparatorMaxHeap;
import adt.heap.ComparatorMinHeap;
import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		for (Integer number: array) {
			this.insert(number);
		}
		Integer floor = null;
		
		while(this.size() > 0) {
			if (this.rootElement() == numero) {
				floor = this.rootElement();
				break;
			} else if (this.rootElement() < numero) {
				floor = this.rootElement();
				this.extractRootElement();
				if (this.comparator instanceof ComparatorMaxHeap) {
					break;
				}
			} else {
				this.extractRootElement();
			}
		}
		
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		for (Integer number: array) {
			this.insert(number);
		}
		Integer ceil = null;
		
		while(this.size() > 0) {
			if (this.rootElement() == numero) {
				ceil = this.rootElement();
				break;
			} else if (this.rootElement() > numero) {
				ceil = this.rootElement();
				this.extractRootElement();
				if (this.comparator instanceof ComparatorMinHeap) {
					break;
				}
			} else {
				this.extractRootElement();
			}
		}
		
		return ceil;
	}

}
