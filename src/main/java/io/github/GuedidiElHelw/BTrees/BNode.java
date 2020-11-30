package io.github.GuedidiElHelw.BTrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BNode {
	private int m;
	private int n;	// Number of keys
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
    
    public BNode(int m, int n, ArrayList<Integer> keys, ArrayList<BNode> bNodes, )
 
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
 
    
    class IsFoundBNode{
		public boolean isFound;
		public BNode bn;
		
		public IsFoundBNode(BNode bn, boolean isFound){
			this.isFound = isFound ;
			this.bn = bn;
		}
	}
    
    public IsFoundBNode search(int k) {
 
        int i = 0;
        while (i < n && k > keys.get(i)) {
        	i++;
        }
 
        if (keys.get(i) == k)
            return new IsFoundBNode(this, true);
 
        if (isLeaf == true)
            return new IsFoundBNode(this, false);
 
        return bNodes.get(i).search(k);
 
    }
    
    public void split(int k) {
    	
    	keys.add(k);
    	Collections.sort(keys);
    	
    	// Second half :
    	List<Integer> temp = keys.subList(m/2+1, keys.size());
    	
    	// Insertion in parent :
    	int medianK = keys.remove(m/2);
    	parent.keys.add(medianK);
    	Collections.sort(parent.keys);
    	parent.bNodes.add(parent.keys.indexOf(medianK)+1, new BNode(temp, m);
    	
    	// First half :
    	keys.removeAll(temp);
    	
    }
    
    public ArrayList<BNode> getbNodes() {
		return bNodes;
	}
    
    public ArrayList<Integer> getKeys() {
		return keys;
	}
    
    public int getM() {
		return m;
	}
    
    public int getN() {
		return n;
	}
    
    public BNode getParent() {
		return parent;
	}
    
    public void setbNodes(ArrayList<BNode> bNodes) {
		this.bNodes = bNodes;
	}
    
    public void setKeys(ArrayList<Integer> keys) {
		this.keys = keys;
	}
    
    public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
    
    public void setM(int m) {
		this.m = m;
	}
    
    public void setN(int n) {
		this.n = n;
	}
    
    public void setParent(BNode parent) {
		this.parent = parent;
	}
}
