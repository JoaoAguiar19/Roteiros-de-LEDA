package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BSTNode<T> node) {
		int height = -1;

		if (node != null && !node.isEmpty()) {
			int heightLeft = height((BSTNode<T>) node.getLeft());
			int heightRight = height((BSTNode<T>) node.getRight());

			height = 1 + Math.max(heightLeft, heightRight);
		}

		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(this.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
        BSTNode<T> result = new BSTNode<>();

		if (element != null && node != null && !node.isEmpty()) {
			if (element.compareTo(node.getData()) == 0) {
				result = node;

			} else if (element.compareTo(node.getData()) < 0) {
				result = search((BSTNode<T>) node.getLeft(), element);

			} else {
				result = search((BSTNode<T>) node.getRight(), element);
			}
		}
		return result;
    }

	@Override
	public void insert(T element) {
		insert(this.root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		if (element != null) {
			if (node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BSTNode<>());
				node.setRight(new BSTNode<>());
				node.getLeft().setParent(node);
    			node.getRight().setParent(node);

			} else if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);

			} else if (element.compareTo(node.getData()) > 0){
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	
	}
	
	public BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> max = node;

		if (!max.isEmpty()) {
			while (!max.getRight().isEmpty()) {
				max = (BSTNode<T>) max.getRight();
			}
		}

		if (max.isEmpty()) {
			max = null;
		}

		return max;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}

	public BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> min = node;

		if (!min.isEmpty()) {
			while (!min.getLeft().isEmpty()) {
				min = (BSTNode<T>) min.getLeft();
			}
		}

		if (min.isEmpty()) {
			min = null;
		}

		return min;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> node = search(element);

		if (node != null && !node.isEmpty() && !isEmpty()) {

			if (!node.getRight().isEmpty()) {
				result = minimum((BSTNode<T>) node.getRight());

			} else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && !parent.isEmpty() && parent.getData().compareTo(node.getData()) <= 0) {
					parent = (BSTNode<T>) parent.getParent();
				}

				result = parent;
			}
		}

		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> node = search(element);

		if (node != null && !node.isEmpty() && !isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				result = maximum((BSTNode<T>) node.getLeft());

			} else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && !parent.isEmpty() && parent.getData().compareTo(node.getData()) >= 0) {
					parent = (BSTNode<T>) parent.getParent();
				}

				result = parent;
			}
		}

		return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.isLeaf()) {
			node.setData(null);

		} else if ((!node.getLeft().isEmpty() && node.getRight().isEmpty()) || (node.getLeft().isEmpty() && !node.getRight().isEmpty())) {
			BSTNode<T> child;

			if (!node.getLeft().isEmpty()) {
				child = (BSTNode<T>) node.getLeft();

			} else {
				child = (BSTNode<T>) node.getRight();
			}

			if (node != this.root) {
				if (node.getData().compareTo(node.getParent().getData()) < 0) {
					node.getParent().setLeft(child);

				} else {
					node.getParent().setRight(child);
				}

				child.setParent(node.getParent());

			} else {
				this.root = child;
				child.setParent(null);
			
			}

		} else {
			BSTNode<T> sucessor = sucessor(node.getData());
			node.setData(sucessor.getData());
			remove(sucessor);
		}
	}

	@Override
	public T[] preOrder() {
		ArrayList<T> result = new ArrayList<>();
		preOrder(this.root, result);

		return result.toArray((T[]) new Comparable[result.size()]);
	}

	private void preOrder(BSTNode<T> node, ArrayList<T> result) {
		if (node != null && !node.isEmpty()) {
			result.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), result);
			preOrder((BSTNode<T>) node.getRight(), result);
		}
	}

	@Override
	public T[] order() {
		ArrayList<T> result = new ArrayList<>();
		
		if (!this.isEmpty()) {
			order(this.root, result);
		}

		return result.toArray((T[]) new Comparable[result.size()]);
	}

	private void order(BSTNode<T> node, ArrayList<T> result) {
		if (node != null && !node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), result);
			result.add(node.getData());
			order((BSTNode<T>) node.getRight(), result);
		}
	}

	@Override
	public T[] postOrder() {
		ArrayList<T> result = new ArrayList<>();

		if (!this.isEmpty()) {
			postOrder(this.root, result);
		}

		return result.toArray((T[]) new Comparable[result.size()]);
	}

	private void postOrder(BSTNode<T> node, ArrayList<T> result) {
		if (node != null && !node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), result);
			postOrder((BSTNode<T>) node.getRight(), result);
			result.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
