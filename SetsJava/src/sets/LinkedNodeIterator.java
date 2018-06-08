package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    LinkedNode<E> head;
  
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
      // TODO (2) choose appropriate parameters and do the initialization
	  this.head = head;
  }

  @Override
  public boolean hasNext() {
    // TODO (3)
	return head!=null;
	}

  @Override
  public E next() {
    // TODO (4)
	if(!hasNext()) {
		throw new NoSuchElementException();
	}
	else {
		E e = head.getData();
		head = head.getNext();
		return e;
	}
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}
