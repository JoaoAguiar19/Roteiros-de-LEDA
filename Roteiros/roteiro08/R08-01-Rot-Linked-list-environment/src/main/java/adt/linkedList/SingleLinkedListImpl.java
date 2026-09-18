package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		SingleLinkedListNode<T> aux = this.head;
		int size = 0;

		while (!aux.isNIL()) { 
			size++;
			aux = aux.next;
		}

		return size;
	}

	@Override
	public T search(T element) {
		T result = null;

		if (element != null) {
			SingleLinkedListNode<T> aux = this.head;
	
			while (!aux.isNIL() && !aux.data.equals(element)) {
				aux = aux.next;
			}
	
			if (!aux.isNIL()) {
				result = aux.data;
			}
		}

		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.head = new SingleLinkedListNode<>(element, new SingleLinkedListNode<>());
			
			} else {
				SingleLinkedListNode<T> aux = this.head;

				while (!aux.next.isNIL()) {
					aux = aux.next;
				}

				aux.next = new SingleLinkedListNode<>(element, new SingleLinkedListNode<>());
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			
			if (this.head.data.equals(element)) {
				this.head = this.head.next;

			} else {
				SingleLinkedListNode<T> aux = this.head;

				while (!aux.next.isNIL() && !aux.next.data.equals(element)) {
					aux = aux.next;
				}

				if (!aux.next.isNIL()) {
					aux.next = aux.next.next;
				}
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = this.head;
		int i = 0;

		while (!aux.isNIL()) {
			array[i++] = aux.data;
			aux = aux.next;
		}

		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return this.head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
