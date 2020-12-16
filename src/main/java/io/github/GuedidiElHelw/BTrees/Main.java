package io.github.GuedidiElHelw.BTrees;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static BTree buildBTree() {
		int m = 5;
		BTree bTree = new BTree(m);
		
		BNode bNode1 = new BNode(m, false);
		BNode bNode2 = new BNode(m, false);
		BNode bNode3 = new BNode(m, false);
		BNode bNode4 = new BNode(m, true);
		BNode bNode5 = new BNode(m, true);
		BNode bNode6 = new BNode(m, true);
		BNode bNode7 = new BNode(m, true);
		BNode bNode8 = new BNode(m, true);
		BNode bNode9 = new BNode(m, true);
		
		bNode1.setKeys(new ArrayList<>(Arrays.asList(46)));
		bNode1.setN(1);
		bNode1.setbNodes(new ArrayList<>(Arrays.asList(bNode2, bNode3)));
		
		bNode2.setKeys(new ArrayList<>(Arrays.asList(27, 37)));
		bNode2.setN(2);
		bNode2.setbNodes(new ArrayList<>(Arrays.asList(bNode4, bNode5, bNode6)));
		bNode2.setParent(bNode1);
		
		bNode3.setKeys(new ArrayList<>(Arrays.asList(66, 79)));
		bNode3.setN(2);
		bNode3.setbNodes(new ArrayList<>(Arrays.asList(bNode7, bNode8, bNode9)));
		bNode3.setParent(bNode1);
		
		bNode4.setKeys(new ArrayList<>(Arrays.asList(10, 15, 20, 25)));
		bNode4.setN(4);
		bNode4.setParent(bNode2);
		
		bNode5.setKeys(new ArrayList<>(Arrays.asList(30, 35)));
		bNode5.setN(2);
		bNode5.setParent(bNode2);
		
		bNode6.setKeys(new ArrayList<>(Arrays.asList(40, 45)));
		bNode6.setN(2);
		bNode6.setParent(bNode2);
		
		bNode7.setKeys(new ArrayList<>(Arrays.asList(50, 55, 60, 65)));
		bNode7.setN(4);
		bNode7.setParent(bNode3);
		
		bNode8.setKeys(new ArrayList<>(Arrays.asList(68, 71, 74, 78)));
		bNode8.setN(4);
		bNode8.setParent(bNode3);
		
		bNode9.setKeys(new ArrayList<>(Arrays.asList(81, 85, 90, 95)));
		bNode9.setN(4);
		bNode9.setParent(bNode3);
		
		bTree.setRoot(bNode1);
		
		return bTree;
	}
	
	public static void main(String[] args) {
		BTree bTree = buildBTree();
		bTree.printBTree();
		
		// Searching for key 55 :
		int k = 55;
		BNode bNode1 = bTree.search(k).bn;
		System.out.println(bNode1.getKeys());
		
		// Searching for key 35 :
		k = 35;
		BNode bNode2 = bTree.search(k).bn;
		System.out.println(bNode2.getKeys());

	}

}