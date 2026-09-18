package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		DoubleLinkedListNode<T> nilNode = new DoubleLinkedListNode<>();
		this.head = nilNode;
		this.last = nilNode;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());
				this.head = newNode;
				this.last = newNode;
			
			} else {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), this.last);
				this.last.next = newNode;
				this.last = newNode;
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.insert(element);
			} else {
				DoubleLinkedListNode<T> currentHead = (DoubleLinkedListNode<T>) this.head;
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, currentHead, new DoubleLinkedListNode<>());

				currentHead.previous = newNode;
				this.head = newNode;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (this.head.data.equals(element)) {
				this.removeFirst();

			} else if (this.last.data.equals(element)) {
				this.removeLast();

			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;

				while (!aux.isNIL() && !aux.data.equals(element)) {
					aux = (DoubleLinkedListNode<T>) aux.next;
				}

				if (!aux.isNIL()) {
					aux.previous.next = aux.next;
					((DoubleLinkedListNode<T>) aux.next).previous = aux.previous;
				}
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.head.isNIL()) {
			this.head = this.head.next;

			if (this.head.isNIL()) {
				this.last = (DoubleLinkedListNode<T>) this.head;
			
			} else {
				((DoubleLinkedListNode<T>) this.head).previous = new DoubleLinkedListNode<>();
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.last.isNIL()) {
			this.last = this.last.getPrevious();

			if (this.last.isNIL()) {
				this.head = this.last;
			
			} else {
				this.last.setNext(new DoubleLinkedListNode<>());
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;

		if (element != null && !isEmpty()) {
			DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.head;
			DoubleLinkedListNode<T> auxLast = this.last;

			while (!auxHead.isNIL() && !auxLast.isNIL() && auxHead != auxLast && auxHead.next != auxLast 
			&& !auxHead.data.equals(element) && !auxLast.data.equals(element)) {
				
				auxHead = (DoubleLinkedListNode<T>) auxHead.next;
				auxLast = auxLast.previous;
			}

			if (!auxHead.isNIL() && auxHead.data.equals(element)) {
				result = auxHead.data;
			}

			if (!auxLast.isNIL() && auxLast.data.equals(element)) {
				result = auxLast.data;
			}
		}

		return result;
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
