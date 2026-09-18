package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
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

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			int balance = calculateBalance(node);
			BSTNode<T> subTreeRoot = node;
			BSTNode<T> child;

			// Caso que o node tende para a esquerda
			if (balance > 1) {
				// Se o filho também tender para a esquerda então cai no caso LL
				if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
					subTreeRoot = Util.rightRotation(node);

					// Se o filho tender para a direita, então cai no caso LR
				} else {
					child = Util.leftRotation((BSTNode<T>) node.getLeft());
					node.setLeft(child);
					subTreeRoot = Util.rightRotation(node);
				}

				// Caso que o node tende para a direita
			} else if (balance < -1) {
				// Se o filho tender para a direita cai no caso RR
				if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
					subTreeRoot = Util.leftRotation(node);
					
					// Se o filho tender para a esquerda cai no caso do RL
				} else {
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

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		while (parent!= null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
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
			
			} else if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), element);
			}
			rebalance(node);
		}
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
			rebalanceUp(node);
		
		} else if ((!node.getLeft().isEmpty() && node.getRight().isEmpty()) || (node.getLeft().isEmpty() && node.getRight().isEmpty())) {
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

			rebalanceUp(node);

		} else {
			BSTNode<T> sucessor = sucessor(node.getData());
			node.setData(sucessor.getData());
			remove(sucessor);
		}
	}
}