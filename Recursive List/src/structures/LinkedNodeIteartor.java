package structures;
import java.util.Iterator;

public class LinkedNodeIteartor<T> implements Iterator<T> {
	private LLNode<T> head;
	public LinkedNodeIteartor(LLNode<T> current){
		head = current;
	}
	public boolean hasNext(){
		return true;
	}
	public T next(){
		return null;
	}
	
	
}
