package sorters;

import java.util.Comparator;

import structures.SwapList;

public class QuickSorter<T> extends AbstractSorter<T> {

	public QuickSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}
	
	public int partition(int low, int high) {
		int storeIndex = low;
		int pivot = (low+high)/2;
		list.swap(pivot, high);
		pivot = high;
		for(int j = low; j <=high-1; j++){
			if(list.compare(j, pivot, comparator) <= 0){
				list.swap(j, storeIndex);
				storeIndex++;
			}
		}
		list.swap(storeIndex, high);
		return storeIndex;
	}
	public void recQuickSort(int low, int high){
		if(low < high){
			int p = partition(low, high); // p is split point
			 recQuickSort(low, p-1);
			 recQuickSort(p+1, high);
		}
	}
	@Override
	public SwapList<T> sort() {
                // TODO
		recQuickSort(0, list.size()-1);
		return list;
	}

}
