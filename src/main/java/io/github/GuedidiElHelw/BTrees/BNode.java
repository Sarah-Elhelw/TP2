package io.github.GuedidiElHelw.BTrees;

import java.util.Arrays;

public class BNode {
	private int m;
	private int n;	// Number of keys
	private int[] keys;
    private BNode[] bNodes;
    private BNode parent;
    private boolean isLeaf;
 
    public BNode(int m, boolean leaf) {
        this.m = m;
        this.n = 0;
        this.keys = new int[m- 1];
        this.bNodes = new BNode[m];
        this.parent = null;
        this.isLeaf = leaf;
    }
 
    public void printBTree() {
        int i = 0;
        for (i = 0; i < this.n; i++) {
            if (this.isLeaf == false) {
                bNodes[i].printBTree();
            }
            System.out.print(keys[i] + " ");
        }
        if (isLeaf == false)
            bNodes[i].printBTree();
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
        while (i < n && k > keys[i])
            i++;
 
        if (keys[i] == k)
            return new IsFoundBNode(this, true);
 
        if (isLeaf == true)
            return new IsFoundBNode(this, false);
 
        return bNodes[i].search(k);
 
    }
    
    public void split(int k) {
    	
    	// Temporary keys :
    	int[] temp = new int[m];
    	for (int i=0; i<n; i++) {
    		temp[i] = keys[i];
    	}
    	temp[n] = k;
    	Arrays.sort(temp);
    	
    	// Inserting median in parent node :
    	int medianK = temp[m/2];
    	int n2 = parent.getN();
    	int[] keys2 = parent.getKeys();
    	if (n2 < m-1) {
    		keys[n2] = medianK;
    		n2++;
    		Arrays.sort(keys2);
    	}
    	
    	// Updating list of keys :
    	int j = m/2 ;
    	while (j<m-1) {
    		temp[j] = temp[j+1];
    	}
    	keys = temp;
    	
    }
    
    public BNode[] getbNodes() {
		return bNodes;
	}
    
    public int[] getKeys() {
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
    
    public void setbNodes(BNode[] bNodes) {
		this.bNodes = bNodes;
	}
    
    public void setKeys(int[] keys) {
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
