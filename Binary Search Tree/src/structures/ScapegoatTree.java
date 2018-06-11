package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {
	private int upperBound;


	@Override
	public void add(T t) {
		// TODO
		upperBound++;
		super.add(t);
		if(Math.pow(3/2, getHeight(getRoot())) > upperBound){
			
		}
	}

	@Override
	public boolean remove(T element) {
		// TODO
		if(this.contains(element)){
			super.remove(element);
			if(upperBound > 2*size()){
				balance();
				upperBound = size();
			}
			return true;
		}
		else{
			return false;
		}
	}
}
