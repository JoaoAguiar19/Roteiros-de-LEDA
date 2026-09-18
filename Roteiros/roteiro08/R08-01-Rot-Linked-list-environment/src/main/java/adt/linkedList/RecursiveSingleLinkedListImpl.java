package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		int result = 0;

		if (!isEmpty()) {
			result += 1 + next.size();
		}

		return result;
	}

	@Override
	public T search(T element) {
		T result = null;

		if (element != null) {
			if (!isEmpty()) {
				if (this.data.equals(element)) {
					result = this.data;
	
				} else {
					result = this.next.search(element);
				}
			}
		}


		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.next = new RecursiveSingleLinkedListImpl<>();
	
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!isEmpty()) {
				if (this.data.equals(element)) {
					this.data = next.data;
					this.next = next.next;
	
				} else {
					this.next.remove(element);
				}
			}
		}

	}

	@Override
	public T[] toArray() {
		List<T> array = new ArrayList<>();
		fillArray(array, this);
		return (T[]) array.toArray((T[]) new Object[array.size()]);
	}
	


	private void fillArray(List<T> array, RecursiveSingleLinkedListImpl<T> node) {
		if (!node.isEmpty()) {
			array.add(node.data);
			fillArray(array, node.next);
		}
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
}
