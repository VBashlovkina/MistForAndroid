package edu.grinnell.glimmer.mist.parser;

import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author Albert Owusu-Asare 
 * @since 1.0
 */
/* TODO : How to use resultant Map structure  to  actually create Graph */
public class NewDAG {

	/**
	 * Creates a DAG from a Tree
	 * @param root -  of the tree
	 * @return the <code>table</code> structure containing the HashMap
	 *  and next integer value.
	 */
	public static Table makeDAG(TreeNode root) {
		HashNode rootNode = new HashNode(root);
		HashMap<HashNode, Integer> map = new HashMap<HashNode, Integer>();
		Table table = new Table(map);
		rootNode = makeDAG(rootNode, table);
		return table;
	}// makeDAG(TreeNode)

	/**
	 * Creates a DAG from a Tree
	 * @param root -root of the tree
	 * @param table - structure containing HashMap and next Integer value
	 * @return a HashNode
	 */
	public static HashNode makeDAG(HashNode root, Table table) {
		// Iterate through children of the root.
		for (TreeNode child : root.node.getChildren()) {
			HashNode temp = new HashNode(child);
			// If child is a leaf
			if (child.isLeaf()) {
			    	// If it isn't already in the map, add it
				if (!table.map.containsKey(temp)) {
					temp.nodeVal = table.availableNumber;
					table.map.put(temp, table.availableNumber);
					table.increment();
				}// child does not exist in map
			}// if child is a leaf
			else {
				// recurse the child and gather necessary data
				temp = makeDAG(temp, table);
			}// else child is not a leaf
			
			// update root: retrieve the new value of temp from the map
			Integer val = table.map.get(temp);
			// add it to root's children-arguments
			root.childrenValues.add(val);
		}// process children

		// process root: put root into map and update number
		if (!table.map.containsKey(root)) {
		    	root.nodeVal = table.availableNumber; 
			table.map.put(root, root.nodeVal);
			table.increment();
		}// if root not in map
		return root;
	}// makeDAG(TreeNode)

	public static void main(String[] args) throws Exception {
		PrintWriter pen = new PrintWriter(System.out, true);

		// Testing makeDAG for tree 1 level tree with 2 leafs
		pen.println("Test makeDAG() 1 level tree with 2 leafs");
		pen.println("====================================");
		TreeNode test1Root = new TreeNode("square");
		test1Root.addChild("x");
		test1Root.addChild("y");
		pen.println(test1Root);
		Table test1Table= makeDAG(test1Root);
		pen.println(test1Table.map);
		pen.println();
		pen.println("Test makeDAG() 2 levels tree with 3 leafs");
		pen.println("====================================");
		TreeNode test2Root = new TreeNode("square");
		test2Root.addChild("x");
		test2Root.addChild(test1Root);
		pen.println(test2Root);
		Table test2Table = makeDAG(test2Root);
		pen.println(test2Table.map);
		pen.println();
		String code = "sum(neg(x), neg(x), x, y)";
		pen.println("Test makeDAG() of " + code);
		pen.println("====================================");
		TreeNode sumNegNeg = Parser.parse(code);
		pen.println(sumNegNeg);
		Table sNNmap = makeDAG(sumNegNeg);
		pen.println(sNNmap.map);
	}// main

}
