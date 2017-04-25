package sets;

import java.util.Iterator;

public class LinkedSet<E> implements Set<E> {
	private LinkedNode<E> head = null;

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
		int counter = 0;
		for (E e : this) {
			counter++;
		}
		return counter;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedNodeIterator<E>(this.head);
	}

	@Override
	public boolean contains(Object o) {
		for (E e : this) {
			if (o.equals(e)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isSubset(Set<E> that) {
		for(E e: this) {
			if(!that.contains(e)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isSuperset(Set<E> that) {
		return that.isSubset(this);
	}

	@Override
	public Set<E> adjoin(E e) {
		if(e == null) {
			return this;
		}
		else {
			LinkedNode<E> node = new LinkedNode<E>(e, head);
			return new LinkedSet<E>(node);
		}
	}
	
	@Override
	public Set<E> union(Set<E> that) {
		if(that.isEmpty()) {
			return this;
		}
		else {
			Set<E> result = this;
			for(E e: that) {
				if(!result.contains(e)) {
					result = result.adjoin(e);					
				}
			}
			return result;
		}
	}

	@Override
	public Set<E> intersect(Set<E> that) {
		if(that.isEmpty()) {
			return new LinkedSet<E>();
		}
		else {
			Set<E> result = new LinkedSet<E>();
			for(E e: that) {
				if(this.contains(e)) {
					result = result.adjoin(e);
				}
			}
			return result;
		}
	}

	@Override
	public Set<E> subtract(Set<E> that) {
		if(that.isEmpty()) {
			return this;
		}
		else {
			Set<E> result = new LinkedSet<E>();
			for(E e: this) {
				if(!that.contains(e)) {
					result = result.adjoin(e);
				}
			}
			return result;
		}
	}

	@Override
	public Set<E> remove(E e) {
		if(!this.contains(e)) {
			return this;
		}
		else {
			Set<E> result = new LinkedSet<E>();
			for(E f: this) {
				if(!f.equals(e)) {
					result = result.adjoin(f);
				}
			}
			return result;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (!(o instanceof Set)) {
			return false;
		}
		Set<E> that = (Set<E>) o;
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
