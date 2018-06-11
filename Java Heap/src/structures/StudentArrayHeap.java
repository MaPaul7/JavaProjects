package structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import comparators.StringLengthComparator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {


	
	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
	}
	@Override
	public int getLeftChildOf(int index){
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		return (index*2 + 1);
	}
	@Override
	public int getRightChildOf(int index){
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		return (index*2 + 2);
	}
	@Override
	public int getParentOf(int index){
		if(index < 1){
			throw new IndexOutOfBoundsException();
		}
		return (index-1)/2;
	}
	@Override
	public void bubbleUp(int index){
		int hole = index;
		if(hole == 0){
			return;
		}
		int parent = getParentOf(hole);
		while(comparator.compare(heap.get(hole).getPriority(), heap.get(parent).getPriority()) > 0){
			swap(hole, parent);
			hole = parent;
			if (hole==0){
				return;
			}
			parent = getParentOf(hole);
		}	
	}
	@Override
	protected void bubbleDown(int index) {
		int largerChild, hole = index, left = getLeftChildOf(hole), right = getRightChildOf(hole);
		if(hole >= heap.size()){
			return;
		}
		while (left < heap.size()){
			if(right < heap.size() && comparator.compare(heap.get(left).getPriority(), heap.get(right).getPriority()) < 0){
				largerChild = right;
			} 
			else {
				largerChild = left;
			}
			if(comparator.compare(heap.get(hole).getPriority(), (heap.get(largerChild)).getPriority()) >= 0)
				 break;
			swap(hole, largerChild);
			hole = largerChild;
			left = getLeftChildOf(hole);
			right = getRightChildOf(hole);
		}
		return;
	}
}

