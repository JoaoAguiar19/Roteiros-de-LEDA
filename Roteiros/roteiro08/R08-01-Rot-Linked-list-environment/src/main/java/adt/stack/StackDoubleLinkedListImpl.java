package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.DoubleLinkedListNode;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;
	protected int count;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
		this.count = 0;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}

		if (element != null) {
			this.top.insert(element);
			count++;
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}

		T result = top();
		top.removeLast();
		count--;
		return result;
	}

	@Override
	public T top() {
		T result = null;

		if (!isEmpty()) {
			DoubleLinkedListNode<T> lastNode = ((DoubleLinkedListImpl<T>) top).getLast();
			result = lastNode.getData();
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public boolean isFull() {
		return count == size;
	}

}
