package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return equals(tree1.getRoot(), tree2.getRoot());
	}

	private boolean equals(BTNode<T> node1, BTNode<T> node2) {
		boolean result = false;

		if (node1.isEmpty() && node2.isEmpty()) {
			result = true;

		} else if (!node1.isEmpty() && !node2.isEmpty()) {
			if (node1.getData().compareTo(node2.getData()) == 0) {
				boolean left = equals(node1.getLeft(), node2.getLeft());
				boolean right = equals(node1.getRight(), node2.getRight());
				result = left && right;
			}
		}

		return result;	
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return isSimilar(tree1.getRoot(), tree2.getRoot());
	}

	private boolean isSimilar(BTNode<T> node1, BTNode<T> node2) {
		boolean result = false;

		if (node1.isEmpty() && node2.isEmpty()) {
			result = true;

		} else if (!node1.isEmpty() && !node2.isEmpty()) {
			boolean left = isSimilar(node1.getLeft(), node2.getLeft());
			boolean right = isSimilar(node1.getRight(), node2.getRight());
			result = left && right;
		}

		return result;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T result = null;

		if (k >= 1 && k <= size(tree.getRoot())) {
			result = orderStatistic(tree.getRoot(), k);
		}

		return result;
	}

    private T orderStatistic(BTNode<T> node, int k) {
		T result = null;

		int rank = 1 + size(node.getLeft());

		if (k == rank) {
			result = node.getData();

		} else if (k < rank) {
			result = orderStatistic(node.getLeft(), k);

		} else {
			result = orderStatistic(node.getRight(), k - rank);
		}

		return result;
	}

	private int size(BTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = 1 + size(node.getLeft())
					+ size(node.getRight());
		}
		return result;
	}

}
