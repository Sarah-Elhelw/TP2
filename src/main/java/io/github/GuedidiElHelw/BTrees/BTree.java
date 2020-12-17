package io.github.GuedidiElHelw.BTrees;

import io.github.GuedidiElHelw.BTrees.BNode.IsFoundBNode;

public class BTree {
	private int m; // the order of the tree
	private BNode root;

	public BTree(int m) {
		this.m = m;
		root = null;
	}

	public void printBTree() {
		if (root != null)
			root.printBTree();
		System.out.println();
	}

	public IsFoundBNode search(int k) {
		if (root == null)
			return null;
		else
			return root.search(k);
	}

	/**
	 * Inserts the key k in the BTree.
	 * 
	 * @param k
	 * @return true if k was inserted and false if k already exists.
	 */
	public boolean insert(int k) {
		if (root == null) {
			root = new BNode(m, true, this);
		}

		return root.insert(k);
	}

	public int getM() {
		return m;
	}

	public BNode getRoot() {
		return root;
	}

	public void setRoot(BNode root) {
		this.root = root;
	}

}
