package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
 
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				RecursiveDoubleLinkedListImpl<T> nil = new RecursiveDoubleLinkedListImpl<>();
				this.next = nil;
				nil.setPrevious(this);
	
				if (previous == null) {
					this.previous = new RecursiveDoubleLinkedListImpl<>();
				}
	
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.insert(element);

			} else {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>();
				newNode.data = this.data;
				newNode.next = this.next;
				newNode.previous = this;

				if (this.next != null && !this.next.isEmpty()) {
					((RecursiveDoubleLinkedListImpl<T>) this.next).previous = newNode;
				}			

				this.data = element;
				this.next = newNode;

				if (this.previous == null) {
					this.previous = new RecursiveDoubleLinkedListImpl<>();
					this.previous.next = this;

				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (this.data.equals(element)) {
				if (this.previous.isEmpty() && this.next.isEmpty()) {
					this.data = null;
					this.next = null;
					this.previous = null;
				
				} else {
					this.data = this.next.data;
					this.next = this.next.next;

					if (this.next != null) {
						((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;
					}
				}

			} else {
				this.next.remove(element);
			}
		}
	}

	
	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (this.next == null || this.next.isEmpty()) {
				this.data = null;
				this.next = null;
				this.previous = null;

			} else {
				this.data = this.next.data;
				this.next = this.next.next;
	
				if (this.next != null) {
					((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;
				}
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.next == null || this.next.isEmpty()) {
				if (this.previous == null || this.previous.isEmpty()) {
					this.data = null;
					this.next = null;
					this.previous = null;

				} else {
					this.previous.next = this.next;
					
					if (this.next != null  && !this.next.isEmpty()) {
						((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this.previous;
					}
				}

			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
