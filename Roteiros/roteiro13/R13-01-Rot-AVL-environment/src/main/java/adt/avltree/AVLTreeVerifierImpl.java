package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		return isBST() && isAVLTree(getAVLTree().getRoot());
	}

	private boolean isAVLTree(BSTNode<T> node) {
		boolean result = false;

		if (node == null || node.isEmpty()) {
			result = true;

		} else {
			int balance = this.avlTree.calculateBalance(node);

			if (balance >= -1 && balance <= 1) {
				boolean balanceLeft = isAVLTree((BSTNode<T>) node.getLeft());
				boolean balanceRight = isAVLTree((BSTNode<T>) node.getRight());

				result = balanceLeft && balanceRight;
			}
		}

		return result;
	}

}
