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

	public boolean insert(int k) { // return false if k is in the tree
		if (root == null) {
			root = new BNode(m, true);
		}

		return root.insert(k);
	}

	public int getM() {
		return m;
	}

	public BNode getRoot() {
		return root;
	}

}
