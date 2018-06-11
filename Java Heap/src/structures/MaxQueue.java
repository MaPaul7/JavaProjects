package structures;

import comparators.IntegerComparator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {
	
	Comparator<Integer> comparator = new IntegerComparator(); 
	StudentArrayHeap<Integer, V> heap = new StudentArrayHeap<>(comparator);
	
	public int size(){
		return heap.size();
	}
	public V dequeue(){
		if(heap.isEmpty()){
			throw new IllegalStateException();
		}
		return heap.remove();
	}
	public V peek(){
		if(heap.isEmpty()){
			throw new IllegalStateException();
		}
		return heap.peek();
	}
	public Comparator<Integer> getComparator(){
		return comparator;
	}
	public boolean isEmpty(){
		return heap.isEmpty();
	}
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value){
		heap.add(priority, value);
		return this;
	}
	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		return heap.asList().iterator();
	}
}
