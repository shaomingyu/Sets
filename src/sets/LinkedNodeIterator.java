package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    private LinkedNode<E> head;
    private LinkedNode<E> curr;
  
  // Constructors
	public LinkedNodeIterator(LinkedNode<E> head) {
		this.head = head;
	}

	@Override
	public boolean hasNext() {
 		if(head == null) {
			return false;
		}
		else if(curr == null) {
			return true;
		}
		else if(curr.getNext() == null) {
			return false;
		}
		else return true;
	}

	@Override
	public E next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		else {
			if(curr == null) {
				curr = head;
			}
			else {
				curr = curr.getNext();
			}
			return curr.getData();
		}
    }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
