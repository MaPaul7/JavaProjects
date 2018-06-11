package structures;

	public class LinkedStack<T> {
		Node<T> head = null;
		public T pop() {
			Node<T> node = head;
			if(head != null) {
				head = head.next;
			}
		    return node.data;
			}
		public T top() {
			return head.data;
		}
		public boolean isEmpty() {
			return head == null;
		}
		public int size() {
			int count = 0;
			Node<T> node = head;
			while(node!=null) {
				count++;
				node = node.next;
			}
		    return count;
		}
		public void push(T elem) {
			Node<T> node = new Node<T>(elem);
			node.next = head;
			head = node;
		}
		class Node<T> {
			public T data;
			public Node<T> next;
			public Node(T data) { this.data=data;}
			public Node(T data, Node<T> next) {
				this.data = data; this.next=next;
			}
		}
	}
