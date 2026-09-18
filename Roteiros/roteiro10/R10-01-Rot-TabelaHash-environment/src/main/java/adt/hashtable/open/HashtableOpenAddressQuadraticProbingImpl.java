package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
		int probing = 0;
		int hash;
		boolean flag = false;

		while (probing < this.table.length && !flag) {
			hash = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probing);

			if (this.table[hash] == null || this.table[hash] == this.deletedElement) {
				this.table[hash] = element;
				this.elements++;
				flag = true;

			} else if (element.equals(this.table[hash])) {
				flag = true;

			} else {
				this.COLLISIONS++;
				probing++;
			}
		}

		if (!flag) {
			throw new HashtableOverflowException();
		}
	}
		}

	@Override
	public void remove(T element) {
		if (element != null) {
			int probing = 0;
			int hash;
			boolean flag = false;

			while (probing < this.table.length && !flag) {
				hash = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probing);

				// Se for null, então o elemento nunca foi adicionado
				if (this.table[hash] == null) {
					flag = true;

				} else if (this.table[hash].equals(element)) {
					this.table[hash] = this.deletedElement;
					this.elements--;
					flag = true;
				
				} else {
					probing++;
				}
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;

		if (element != null) {
			int probing = 0;
			int hash;
			boolean flag = false;

			while (probing < this.table.length && !flag) {
				hash = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probing);

				// Se for null, então o elemento nunca foi adicionado
				if (this.table[hash] == null) {
					flag = true;

				} else if (this.table[hash].equals(element)) {
					result = (T) this.table[hash];
					flag = true;
				
				} else {
					probing++;
				}
			}
		}

		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;

		if (element != null) {
			int probing = 0;
			int hash;
			boolean flag = false;

			while (probing < this.table.length && !flag) {
				hash = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probing);

				// Se for null, então o elemento nunca foi adicionado
				if (this.table[hash] == null) {
					flag = true;

				} else if (this.table[hash].equals(element)) {
					result = hash;
					flag = true;
				
				} else {
					probing++;
				}
			}
		}

		return result;
	}
}
