package io.github.GuedidiElHelw.BTrees;

import java.util.Arrays;

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
    
    public boolean insert(int k) {	// return false is k is in the tree
    	
    	// Case 1 : there is no root
    	if (root == null) {
    		root = new BNode(m, true);
    	}
    	
    	IsFoundBNode ifbn = root.search(k);
    	
    	// Case 2 : k is found in the tree
    	if (ifbn.isFound == true) {
    		return false;
    	}
    	
    	BNode bn = ifbn.bn;	// node where k should be inserted if not found
    	int n = bn.getN();
    	int[] keys = bn.getKeys();
    	
    	
    	// Case 3 : it is possible to insert the key directly
    	if (n < m-1) {
    		keys[n] = k;
    		n++;
    		Arrays.sort(keys);
    		return true;
    	}
    	
    	// Case 4 : A split is needed
    	bn.split(k);
    	
    	
    }
    
    public int getM() {
		return m;
	}
    
    public BNode getRoot() {
		return root;
	}
    
}
