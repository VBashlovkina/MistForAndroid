package edu.grinnell.glimmer.mist.parser;

import java.util.ArrayList;



public class HashNode {
	String val;
	Integer indexInTheHash;
	ArrayList<Integer> args;

	public HashNode(String v, Integer index) {
	    args = new ArrayList<>();
	    val = v;
	    indexInTheHash = index;
	}

	public void addArg(Integer arg) {
	    args.add(arg);
	}
	
	
	public boolean equals(HashNode other) {
	    return val.equals(other.val) && args.equals(other.args);
	}

}// HashNode class