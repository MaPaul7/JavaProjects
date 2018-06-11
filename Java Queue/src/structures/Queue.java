package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	 public Node<T> head = null;
     public Node<T> tail = null;
     int size;
     
	public Queue() {		
           head = null;
           tail = null;
           size = 0;
    }
	
	public Queue(Queue<T> other) {
            // TODO 2
			Node<T> n = other.head;
			Queue<T> newQueue = new Queue<T>();
			while(n!=null){
				newQueue.enqueue(n.data);
				n = n.next;
				size++;
			}
			head = newQueue.head;
			tail = newQueue.tail;
	}
	
	@Override
	public boolean isEmpty() {
            // TODO 3
            return head == null;
	}

	@Override
	public int size() {
            // TODO 4
            return size;
	}

	@Override
	public void enqueue(T element) {
            // TODO 5
			if(head == null){
				head = new Node<T>(element);
				tail = head;
			}
			else{
				Node<T> n = new Node<T>(element);
				tail.next = n;
				tail = n;
			}
				size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
			if(head == null){
				throw new NoSuchElementException();
			}
			Node<T> node = head;
            head = head.next;
            size--;
            return node.data;
	}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
			if(this.isEmpty()){
				throw new NoSuchElementException();
			}
            return head.data;
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
            // TODO 8
			Queue<T> newQueue = new Queue<T>(this);
			LinkedStack<T> newStack = new LinkedStack<T>();
			while(!newQueue.isEmpty()){
				newStack.push(newQueue.dequeue());
			}
			while(!newStack.isEmpty()){
				newQueue.enqueue(newStack.pop());
			}
			return newQueue;
	}
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}

