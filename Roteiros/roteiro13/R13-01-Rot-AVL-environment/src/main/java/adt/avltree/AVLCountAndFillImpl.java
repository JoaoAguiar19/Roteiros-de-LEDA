package adt.avltree;


import java.util.Arrays;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	private boolean rebalanceEnabled = true;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null && array.length > 0) {
			this.rebalanceEnabled = false;
			Arrays.sort(array);

			fillWithoutRebalance(array, 0, array.length-1);
			this.rebalanceEnabled = true;
		}
	}

	private void fillWithoutRebalance(T[] array, int left, int right) {
		if (left <= right) {
			int mid = (left + right) / 2;
			insert(array[mid]);
			fillWithoutRebalance(array, left, mid - 1);
			fillWithoutRebalance(array, mid + 1, right);
		}
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		if (node != null && this.rebalanceEnabled) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			int balance = calculateBalance(node);
			BSTNode<T> subTreeRoot = node;
			BSTNode<T> child;

			// Caso que o node tende para a esquerda
			if (balance > 1) {
				// Se o filho também tender para a esquerda então cai no caso LL
				if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
					this.LLcounter++;
					subTreeRoot = Util.rightRotation(node);

					// Se o filho tender para a direita, então cai no caso LR
				} else {
					this.LRcounter++;
					child = Util.leftRotation((BSTNode<T>) node.getLeft());
					node.setLeft(child);
					subTreeRoot = Util.rightRotation(node);
				}

				// Caso que o node tende para a direita
			} else if (balance < -1) {
				// Se o filho tender para a direita cai no caso RR
				if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
					this.RRcounter++;
					subTreeRoot = Util.leftRotation(node);
					
					// Se o filho tender para a esquerda cai no caso do RL
				} else {
					this.RLcounter++;
					child = Util.rightRotation((BSTNode<T>) node.getRight());
					node.setRight(child);
					subTreeRoot = Util.leftRotation(node);
				}
			}

			// Se mudou a raiz da sub-árvore
			if (subTreeRoot != node) {
				// Se o pai do node for null, então ele era a raiz da ávore
				if (parent == null) {
					this.root = subTreeRoot;
				
					// Se ele tinha pai e node era o filho a esquerda
				} else if (parent.getLeft() == node) {
					parent.setLeft(subTreeRoot);

					// Se node era filho a direita 
				} else {
					parent.setRight(subTreeRoot);
				}
			}

			// Atualiza o pai da sub-raiz
			if (subTreeRoot != null) {
				subTreeRoot.setParent(parent);
			}
		}
	}

}
