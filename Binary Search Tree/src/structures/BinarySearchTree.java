package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		// TODO
		BSTNode<T> node = root;
		while(node != null){
			if(node.getData().compareTo(t) > 0)
				node = node.getLeft();
			else if(node.getData().compareTo(t) < 0)
				node = node.getRight();
			else
				return true;
		}
		return false;
	}

	public boolean remove(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		// TODO
		if (t == null) {
			throw new NullPointerException();
		}
		if(isEmpty()){
			return null;
		}
		BSTNode<T> node = root;
		while(node != null){
			if(node.getData().compareTo(t) > 0)
				node = node.getLeft();
			else if(node.getData().compareTo(t) < 0)
				node = node.getRight();
			else
				return node.getData();
		}
		return null;
	}


	public void add(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		root = addToSubtree(root, new BSTNode<T>(t, null, null));
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
		if (node == null) {
			return toAdd;
		}
		int result = toAdd.getData().compareTo(node.getData());
		if (result <= 0) {
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
		} else {
			node.setRight(addToSubtree(node.getRight(), toAdd));
		}
		return node;
	}

	@Override
	public T getMinimum() {
		// TODO
		if(isEmpty())
			return null;
		BSTNode<T> node = root;
		while(node.getLeft()!=null){
			node = node.getLeft();
		}
		return node.getData();
	}


	@Override
	public T getMaximum() {
		// TODO
		if(isEmpty())
			return null;
		BSTNode<T> node = root;
		while(node.getRight()!=null){
			node = node.getRight();
		}
		return node.getData();

	}

	public int getHeight(BSTNode<T> node) {
		if(node.getLeft() == null && node.getRight() == null){
			return 0;
		}
		if(node.getRight() == null){
			int leftHeight = 1 + getHeight(node.getLeft());
			return leftHeight;
		}
		if(node.getLeft() == null){
			int rightHeight = 1 + getHeight(node.getRight());
			return rightHeight;
		}
		int leftHeight = 1 + getHeight(node.getLeft());
		int rightHeight = 1 + getHeight(node.getRight());
		if(leftHeight > rightHeight){
			return leftHeight;
		}
		else {
			return rightHeight;
		}
	}
	@Override
	public int height() {
		// TODO
		if(isEmpty()){
			return -1;
		}
		return getHeight(root);
	}

	private void preOrderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preOrderTraverse(queue, node.getLeft());
			preOrderTraverse(queue, node.getRight());
		}
	}
	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		preOrderTraverse(queue, root);
		return queue.iterator();
	}


	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}


	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}
	private void postOrderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			postOrderTraverse(queue, node.getLeft());
			postOrderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}
	public Iterator<T> postorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		postOrderTraverse(queue, root);
		return queue.iterator();
	}


	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODO
		Queue<T> q1 = new LinkedList<T>();
		Queue<T> q2 = new LinkedList<T>();
		Queue<T> q3 = new LinkedList<T>();
		Queue<T> q4 = new LinkedList<T>();
		preOrderTraverse(q1, root);
		preOrderTraverse(q2, other.getRoot());
		postOrderTraverse(q3, root);
		postOrderTraverse(q4, other.getRoot());
		if(q1.size() != q2.size()){
			return false;
		}
		for(int i = 0; i<q2.size();){
			if(q1.remove().compareTo(q2.remove())!= 0){
				return false;
			}
		}
		for(int i = 0; i<q3.size();){
			if(q3.remove().compareTo(q4.remove())!= 0){
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODO
		Queue<T> q1 = new LinkedList<T>();
		Queue<T> q2 = new LinkedList<T>();
		inorderTraverse(q1, root);
		inorderTraverse(q2, other.getRoot());
		if(q1.size() != q2.size()){
			return false;
		}
		for(int i = 0; i<q2.size();){
			if(q1.remove().compareTo(q2.remove())!= 0){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isBalanced() {
		// TODO
		int size = size();
		int height = height();
		if(Math.pow(2, height) <= size){
			if(Math.pow(2, height+1) > size){
				return true;
			}
		}
		return false;
	}

	@Override
    @SuppressWarnings("unchecked")
	
	public void balance() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		T[] array = (T[])new Comparable[size()];
		inorderTraverse(queue, root);
		for(int i = 0; i< size(); i++){
			array[i] = queue.remove();
		}
		root = sortedArray2BST(0, size()-1, array);
	}
	public BSTNode<T> sortedArray2BST(int lower, int upper, T[] array) {
		if (lower > upper)
		return null;
		int mid = (lower + upper) / 2;
		BSTNode<T> node = new BSTNode<T>(array[mid], null, null);
		node.setLeft (sortedArray2BST(lower, mid - 1, array));
		node.setRight(sortedArray2BST(mid + 1, upper, array));
		return node;
	}

	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.add(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.remove(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.add(r);
		}
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
		tree.balance();
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
	}
}