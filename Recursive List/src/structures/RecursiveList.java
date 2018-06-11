package structures;
import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T>{
	int size;
	LLNode<T> head;		
		
	public RecursiveList(){
	size = 0;
	head = null;
	}
	
	public Iterator<T> iterator(){
		return new LinkedNodeIteartor<T>(this.head);
	}
		
	public int size(){
		return size;
	}
	public ListInterface<T> insertFirst(T elem) throws NullPointerException{ 
		if(elem == null) {
			throw new NullPointerException();
		}
		LLNode<T> node = new LLNode<T>(elem, head);
		head = node;
		size++;
		return this;
	}
	public ListInterface<T> insertLast(T elem) throws NullPointerException{
		if(elem == null) {
			throw new NullPointerException();
		}
		if(isEmpty()){
			head = new LLNode<T>(elem);
			size++;
		}
		else {
			LLNode<T> prev = head;
			insertHelp(size, elem, prev);
			size++;
		}
			return this;
	}
	public void insertHelp(int index, T elem, LLNode<T> prev){
		if(index == 1){
			LLNode<T> node = new LLNode<T>(elem, prev.getNext());
			prev.setNext(node);
		}
		else{
			prev = prev.getNext();
			insertHelp(index-1, elem, prev);
		}
	}
	public ListInterface<T> insertAt(int index, T elem) throws NullPointerException, IndexOutOfBoundsException{
		if(elem == null) {
			throw new NullPointerException();
		}
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<T> prev = head;
		if(index == 0){
			insertFirst(elem);
		}
		else if(index == size){
			insertLast(elem);
		}
		else {
			size++;
			insertHelp(index, elem, prev);
		}
		return this;
	}
	public T removeFirst() throws IllegalStateException{
		if(isEmpty()){
			throw new IllegalStateException();
		}
		T val = head.getValue();
		head = head.getNext();
		size--;
		return val;
	}
	public T removeHelp(int i, LLNode<T> prev, LLNode<T> curr){
		if(i == 0){
			T elem = curr.getValue();
			prev.setNext(curr.getNext());
			return elem;
		}
		else{
			prev = curr;
			curr = curr.getNext();
			return removeHelp(i-1, prev, curr);
		}
	}
	public T removeLast() throws IllegalStateException{ 
		if(isEmpty()){
			throw new IllegalStateException();
		}
		if(size == 1){
			T val = head.getValue();
			head = null;
			size--;
			return val;
		}
		size--;
		LLNode<T> prev = head;
		LLNode<T> curr = head;
		return removeHelp(size, prev, curr);
	}
	public T removeAt(int i) throws IndexOutOfBoundsException{
		if(i >= size || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		size--;
		LLNode<T> prev = head;
		LLNode<T> curr = head;
		return removeHelp(i, prev, curr);
	}
	public T getFirst() throws IllegalStateException{
		if(isEmpty()) {
			throw new IllegalStateException();
		}
		return head.getValue();
	}
	public T getLast() throws IllegalStateException{
		if(isEmpty()) {
			throw new IllegalStateException();
		}
		return getHelp(size-1, head);
	}
	public T getHelp(int i, LLNode<T> current){
		if(i == 0){
			return current.getValue();
		}
		else{
			current = current.getNext();
			return getHelp(i-1, current);
		}
	}
	public T get(int i) throws IndexOutOfBoundsException{
		if(i < 0 ||i >= size){
			throw new IndexOutOfBoundsException();
		}
		return getHelp(i, head);
	}
	public LLNode<T> findVal(T elem){
		LLNode<T> current = head;
		if(current.getValue() == elem){
			return current;
		}
		else {
			current = current.getNext();
			return findVal(elem);
		}
	}
	public boolean remove(T elem) throws NullPointerException{
		if(elem == null){
			throw new NullPointerException();
		}
		if(findVal(elem) == null) {
			return false;
		}
		if(isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	public int findInt(T elem, int i, LLNode<T> curr){
		LLNode<T> current = curr;
		if(current == null){
			return -1;
		}
		else if(current.getValue() == elem) {
			return i;
		}
		else {
			current = current.getNext();
			return findInt(elem, i+1, current);
		}
	}
	public int indexOf(T elem) throws NullPointerException{
		if(elem == null){
			throw new NullPointerException();
		}
		LLNode<T> node = head;
		return findInt(elem, 0, node);
	}
	public boolean isEmpty(){
		return head == null;
	}
}
