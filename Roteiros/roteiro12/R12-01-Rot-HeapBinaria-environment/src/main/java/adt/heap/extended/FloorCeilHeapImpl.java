package adt.heap.extended;

import java.util.Comparator;

import adt.heap.ComparatorMaxHeap;
import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer result = null;

		if (array != null && array.length > 0) {
			HeapImpl<Integer> heap = new HeapImpl<Integer>(new ComparatorMaxHeap<>());
			heap.buildHeap(array);

			while (heap.rootElement() >= numero) {
			}
		}

		return result;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
