package structures;


public class LLNode<T> {
	private T data;
	private LLNode<T> link;
		
	public LLNode(T elem){
		data = elem;
	}
	public LLNode(T elem, LLNode<T> link){
		data = elem;
		this.link = link;
	}
	
	public void setValue(T elem){
		data = elem;
	}
	
	public T getValue(){
		return data;
	}
	
	public void setNext(LLNode<T> next){
		link = next;
	}
	
	public LLNode<T> getNext(){
		return link;
	}
}
