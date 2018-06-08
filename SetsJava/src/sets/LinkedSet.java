package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

  /********************************************************
   * NOTE: Before you start, check the Set interface in
   * Set.java for detailed description of each method.
   *******************************************************/
  
  /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any Collection-based class 
   * such as ArrayList, Vector etc. You will receive a 0
   * if you use any of them.
   *******************************************************/ 

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but do NOT add new files (as they will be ignored).
   *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
   // TODO (1)
	  int count = 0;
	  Iterator<E> iter = iterator();
	  while (iter.hasNext()) {
		  iter.next();
		  count++;
	  }
	return count;
  }

  @Override
  public boolean isEmpty() {
    // TODO (2)
    return (head == null);
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    // TODO (3)
	LinkedNode<E> next = head;
	while(next!=null) {
		if(next.getData().equals(o)) {
			return true;
		}
		next = next.getNext();
	}
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    LinkedNodeIterator<E> iter = new LinkedNodeIterator<E>(head);
    while(iter.hasNext()) {
    	if(!that.contains(iter.next())) {
    		return false;
    	}
    }
    return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    // TODO (5)
    return that.isSubset(this);
  }

  @Override
  public Set<E> adjoin(E e) {
    // TODO (6)
	if(this.contains(e)) {
		return this;
	}
	else {
		LinkedNode<E> n = new LinkedNode<E>(e, head);
		head = n;
		Set<E> LSet = new LinkedSet<E>(n);
		return LSet;
	}
  }

  @Override
  public Set<E> union(Set<E> that) {
    // TODO (7)
	Iterator<E> iter = that.iterator();
	 while(iter.hasNext()) {
	    	adjoin(iter.next());
	    }
    return this;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    // TODO (8)
	if(this.equals(that)) {
		return that;
	}
	Set<E> LSet = new LinkedSet<E>();
	Iterator<E> iter = iterator();
	while(iter.hasNext()) {
		if(that.contains(iter.next())) {
			if(LSet.size() == 0) {
				LSet = new LinkedSet<E>(iter.next());
			}
			else {
				LSet.adjoin(iter.next());
			}
		}
	}
    return LSet;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    // TODO (9)
	Set<E> LSet = new LinkedSet<E>();
	if(that.size() > this.size()) {
		Iterator<E> iter = that.iterator();
		while(iter.hasNext()) {
			if(!that.contains(iter.next())) {
				if(LSet.size() == 0) {
					LSet = new LinkedSet<E>(iter.next());
				}
				else {
					LSet.adjoin(iter.next());
				}
			}
			
		}
	}
		else {
			Iterator<E> iter2 = this.iterator();
			while(iter2.hasNext()) {
				if(!this.contains(iter2.next())) {
					if(LSet.size() == 0) {
						LSet = new LinkedSet<E>(iter2.next());
					}
					else {
						LSet.adjoin(iter2.next());
					}
				}
				
		}
	}
    return LSet;
  }

  @Override
  public Set<E> remove(E e) {
    // TODO (10)
	if(!this.contains(e)) {
		return this;
	}
	Set<E> LSet = new LinkedSet<E>();
	Iterator<E> iter = this.iterator();
	while(iter.hasNext()) {
		if(iter.next()!= e) {
			if(LSet.size() == 0) {
				LSet = new LinkedSet<E>(iter.next());
			}
			else {
				LSet.adjoin(iter.next());
			}
		}
	}
    return LSet;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
    public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
