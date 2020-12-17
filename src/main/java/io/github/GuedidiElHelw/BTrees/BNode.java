package io.github.GuedidiElHelw.BTrees;

import java.util.ArrayList;
import java.util.Collections;

public class BNode {
	private int m;
	private ArrayList<Integer> keys;
	private ArrayList<BNode> bNodes;
	private BNode parent;
	private boolean isLeaf;
	private BTree bTree;

	public BNode(int m, boolean leaf, BTree bTree) {
		this.m = m;
		this.keys = new ArrayList<>();
		this.bNodes = new ArrayList<>();
		this.parent = null;
		this.isLeaf = leaf;
		this.bTree = bTree;
	}

	public void printBTree() {
		int i = 0;
		for (i = 0; i < this.keys.size(); i++) {
			if (this.isLeaf == false) {
				bNodes.get(i).printBTree();
			}
			System.out.print(keys.get(i) + " ");
		}
		if (isLeaf == false)
			bNodes.get(i).printBTree();
	}

	/**
	 * Internal class that represents a couple (boolean, BNode) indicating either
	 * where a key that is not found in the BTree (boolean is false) should be
	 * inserted or where the key was found (boolean is true).
	 *
	 */
	class IsFoundBNode {
		public boolean isFound;
		public BNode bn;

		public IsFoundBNode(BNode bn, boolean isFound) {
			this.isFound = isFound;
			this.bn = bn;
		}
	}

	public IsFoundBNode search(int k) {

		int i = 0;
		while (i < keys.size() && k > keys.get(i)) {
			i++;
		}

		if (i != keys.size() && keys.get(i) == k)
			return new IsFoundBNode(this, true);

		if (isLeaf == true)
			return new IsFoundBNode(this, false);

		return bNodes.get(i).search(k);

	}

	public boolean insert(int k) {
		IsFoundBNode ifbn = search(k);

		// Case 1 : k is found in the tree
		if (ifbn.isFound == true) {
			return false;
		}

		BNode bn = ifbn.bn; // leaf where k should be inserted if not found
		int nLeaf = bn.keys.size();
		ArrayList<Integer> keysLeaf = bn.getKeys();

		// Case 2 : it is possible to insert the key directly
		if (nLeaf < m - 1) {
			keysLeaf.add(k);
			Collections.sort(keysLeaf);
			return true;
		}

		// Case 3 : A split is needed
		bn.split(k);
		return true;
	}

	public void split(int k) {

		if (parent == null) {	// "this" is a root
			parent = new BNode(m, false, bTree);
			parent.bNodes.add(this);
			bTree.setRoot(parent);
		}
		
		keys.add(k);
		Collections.sort(keys);

		// Second half of the list of keys :
		ArrayList<Integer> temp = new ArrayList<>(keys.subList(m / 2 + 1, keys.size()));

		// New node
		BNode newBNode = new BNode(m, isLeaf, bTree);
		newBNode.keys = temp;
		newBNode.parent = this.parent;
		if (!isLeaf) {
			newBNode.bNodes = new ArrayList<>(bNodes.subList(m / 2 + 1, bNodes.size()));
		}

		// Insertion in parent :
		int medianK = keys.remove(m / 2);
		parent.keys.add(medianK);
		Collections.sort(parent.keys);
		int bNodeIndexOfInseretion = parent.keys.indexOf(medianK) + 1;
		parent.bNodes.add(bNodeIndexOfInseretion, newBNode);

		// First half of the list of keys :
		keys.removeAll(temp);
		
		// A split is also needed in the parent :
		if (parent.keys.size() > m-1) {
			medianK = parent.keys.remove(m / 2);
			parent.split(medianK);
		}

	}

	public ArrayList<Integer> getKeys() {
		return keys;
	}

	public void setbNodes(ArrayList<BNode> bNodes) {
		this.bNodes = bNodes;
	}

	public void setKeys(ArrayList<Integer> keys) {
		this.keys = keys;
	}

	public void setParent(BNode parent) {
		this.parent = parent;
	}
}
