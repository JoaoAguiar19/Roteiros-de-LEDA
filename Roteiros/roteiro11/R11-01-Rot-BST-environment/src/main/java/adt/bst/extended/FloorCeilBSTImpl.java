package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		treeFill(array, tree);
		return floor(numero, tree.getRoot());
	}

	private Integer floor(double numero, BSTNode<Integer> node) {
		Integer result = null;

		if (node != null && !node.isEmpty()) {
			if (node.getData() == numero) {
				result = node.getData();
	
			} else if (numero < node.getData()) {
				result = floor(numero, (BSTNode<Integer>) node.getLeft());

			} else {
				result = node.getData();
				Integer resultRight = floor(numero, (BSTNode<Integer>) node.getRight());

				if (resultRight != null && resultRight > result) {
					result = resultRight;
				}
			}
		}

		return result;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		treeFill(array, tree);
		return ceil(numero, (BSTNode<Integer>) tree.getRoot());
	
	}
	private Integer ceil(double numero, BSTNode<Integer> node) {
		Integer result = null;

		if (node != null && !node.isEmpty()) {
			if (numero == node.getData()) {
				result = node.getData();

			} else if (numero < node.getData()) {
				result = node.getData();

				Integer resulLeft = ceil(numero, (BSTNode<Integer>) node.getLeft());

				if (resulLeft != null && resulLeft < result) {
					result = resulLeft;
				}

			} else {
				result = ceil(numero, (BSTNode<Integer>) node.getRight());
			}
		}
		
		return result;
	}
	
	private void treeFill(Integer[] array, BSTImpl<Integer> tree) {
		for (Integer num : array) {
			tree.insert(num);
		}
	}
}
