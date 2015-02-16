package edu.grinnell.glimmer.mist.parser;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/** 
 * 
 * @author Albert Owusu-Asare
 * @author Vasilisa Bashlovkina
 * @author Jason Liu
 * @see TreeNode
 * 
 */
 /* TODO: re-check HashCode implementation. */

public class HashNode {
	
	TreeNode node;
	ArrayList<Integer> childrenValues;
	Integer nodeVal;
	
	/**
	 * Constructs a HashNode with the specified <code>TreeNode</code>
	 * @param node	
	 */
	public HashNode(TreeNode node){
		this.node = node;
		this.nodeVal = 0;
		this.childrenValues = new ArrayList<Integer>();
	}//HashNode(TreeNode)
	
	@Override
	public String toString(){
		String result ="[ ("+this.nodeVal+") "+this.node.getRootVal()+" (";
		for(Integer num : this.childrenValues){
			result += num +",";
		}//for
		result += ") ]";
		return result;
	}//toString()
	
	// TODO http://eclipsesource.com/blogs/2012/09/04/the-3-things-you-should-know-about-hashcode/
	@Override 
	public int hashCode(){
	  int hash = 17;
      // hash = hash * 23 + node.hashCode();
	  for(Integer i : childrenValues){
		  if(!(i ==null)){
			  hash = hash * 23 + i.hashCode();
		  }//if
	  }//for
    // hash = hash * 23 + nodeVal.hashCode();
	  return hash;
	}//hashCode
	
	@Override
	public boolean equals(Object other){
		//check for self
		if(this == other) return true;
		//check if other is null
		if(other == null) return false;
		if(this.getClass() != other.getClass()) return false;
		HashNode that = (HashNode) other;
		// compare properties
		if(!this.node.val.equals(that.node.val)) return false;
		if(!this.childrenValues.equals(that.childrenValues)) return false;
		return true;
	}//equals(Object)
    
	public static void main(String [] args){
		PrintWriter pen = new PrintWriter(System.out,true);
		//Testing to String
		pen.println("Testing toString()");
		pen.println("===========================");
		HashNode test = new HashNode(new TreeNode("square"));
		test.childrenValues.add(1);
		test.childrenValues.add(2);
		test.nodeVal = 2;
		pen.println(test);
		//Testing equals
		pen.println();
		pen.println("Testing equals()");
		pen.println("===========================");
		HashNode test2 = new HashNode(new TreeNode("square"));
		test2.childrenValues.add(1);
		test2.childrenValues.add(2);
		test2.nodeVal = 2;
		pen.println("test.equals(test2) Expecting true Actual: " + 
		test.equals(test2));
		HashNode test3 = new HashNode(new TreeNode("square"));
		test3.childrenValues.add(1);
		test3.childrenValues.add(3);
		test3.nodeVal = 2;
		pen.println("test.equals(test3) Expecting false Actual: " + 
				test.equals(test3));
		HashNode test4 = new HashNode(new TreeNode("x"));
		HashNode test5 = new HashNode(new TreeNode("x"));
		pen.println("test4.equals(test5) Expecting true Actual: " + 
				test4.equals(test5));
		TreeNode test1Root = new TreeNode("square");
		test1Root.addChild("x");
		test1Root.addChild("y");
		HashNode test6 = new HashNode(test1Root);
		pen.println(test6);
		pen.println();
		pen.println("Testing Mapping with equals");
		pen.println("===========================");
		HashMap<HashNode,Integer> map = new HashMap<HashNode,Integer>();
		map.put(test4, 1);
		map.put(test5, 2);
		pen.println("Expected: one entry for x Actual:" +map);
		
		
	}
//	String val;
//	Integer indexInTheHash;
//	ArrayList<Integer> args;
//
//	public HashNode(String v, Integer index) {
//	    args = new ArrayList<>();
//	    val = v;
//	    indexInTheHash = index;
//	}
//
//	public void addArg(Integer arg) {
//	    args.add(arg);
//	}
//	
//	
//	public boolean equals(HashNode other) {
//	    return val.equals(other.val) && args.equals(other.args);
//	}

}// HashNode class