package sorters;

import java.util.Comparator;

import structures.SwapList;

public class HeapSorter<T> extends AbstractSorter<T> {

	public HeapSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
        // TODO
		for(int i=list.size()/2-1; i>=0; i--) { 
			bubbleDown(i, list.size()-1);
		}
		for(int i=list.size()-1;  i>=1; i--) {
			list.swap(0, i);
			bubbleDown(0, i-1);
		}
        return list;
	}
	public void bubbleDown(int index, int end) {
		int biggerChild; int hole = index, left = index*2+1, right = index*2+2;
		while(left <= end) {
			if(right <= end&&list.compare(left, right, comparator) < 0) {
				biggerChild = right;
			}
			else {
				biggerChild = left;
			}
			if(list.compare(hole, biggerChild, comparator) >=0) {
				break;
			}
			list.swap(hole, biggerChild);
			hole = biggerChild;
			left = hole*2 + 1; right = hole*2 + 2;
		}
	}
}
