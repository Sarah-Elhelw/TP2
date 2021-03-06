package io.github.GuedidiElHelw.BTrees;

import java.util.ArrayList;
import java.util.Collections;

public class BNode {
	private int m;
	private int n; // Number of keys
	private ArrayList<Integer> keys;
	private ArrayList<BNode> bNodes;
	private BNode parent;
	private boolean isLeaf;

	public BNode(int m, boolean leaf) {
		this.m = m;
		this.n = 0;
		this.keys = new ArrayList<>();
		this.bNodes = new ArrayList<>();
		this.parent = null;
		this.isLeaf = leaf;
	}

	public void printBTree() {
		int i = 0;
		for (i = 0; i < this.n; i++) {
			if (this.isLeaf == false) {
				bNodes.get(i).printBTree();
			}
			System.out.print(keys.get(i) + " ");
		}
		if (isLeaf == false)
			bNodes.get(i).printBTree();
	}

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
		while (i < n && k > keys.get(i)) {
			i++;
		}

		if (i!=n && keys.get(i) == k)
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
		int n = bn.getN();
		ArrayList<Integer> keys = bn.getKeys();

		// Case 2 : it is possible to insert the key directly
		if (n < m - 1) {
			keys.add(k);
			Collections.sort(keys);
			bn.n++;
			return true;
		}


		// Case 3 : A split is needed
		bn.split(k);
		return true;
	}

	public void split(int k) {

		keys.add(k);
		Collections.sort(keys);
		
		
		// Second half :
		ArrayList<Integer> temp = new ArrayList<>(keys.subList(m / 2 + 1, keys.size()));
		

		// New node
		BNode newBNode = new BNode(m, isLeaf);
		newBNode.keys = temp;
		newBNode.n = temp.size();
		newBNode.parent=this.parent;
		if(!isLeaf) {
		newBNode.bNodes = new ArrayList<>(bNodes.subList(m / 2 + 1, bNodes.size()));}

		// Insertion in parent :
		int medianK = keys.remove(m / 2);
		parent.keys.add(medianK);
		Collections.sort(parent.keys);
		int bNodeIndexOfInseretion=parent.keys.indexOf(medianK)+1;
		parent.bNodes.add(bNodeIndexOfInseretion,newBNode);
		parent.n=parent.keys.size();


		// First half :
		keys.removeAll(temp);
		n=keys.size();
		if (parent.n>m-1) {
		medianK = parent.keys.remove(m / 2);
		parent.split(medianK);
		}

	}
	


	public ArrayList<Integer> getKeys() {
		return keys;
	}

	public int getN() {
		return n;
	}
	
	public void setbNodes(ArrayList<BNode> bNodes) {
		this.bNodes = bNodes;
	}
	
	public void setKeys(ArrayList<Integer> keys) {
		this.keys = keys;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public void setParent(BNode parent) {
		this.parent = parent;
	}
}
