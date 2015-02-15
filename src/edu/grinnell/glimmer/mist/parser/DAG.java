package edu.grinnell.glimmer.mist.parser;

import java.util.*;

public class DAG {



    static HashNode makeDAG(TreeNode root, HashMap<HashNode, Integer> hash, int i)
	    throws Exception {

	// what if it is already in the table?
	if (hash.containsKey(root)) {
	    return hash.get(root); // what do we doooooo here
	}
	// if we have a leaf that's not in the hash
	if (!root.hasChildren()) {
	    HashNode h = new HashNode(root.val, i);
	    hash.put(h, i);
	    return h;
	}
	// we have a non-leaf that is not in the table. we recurse:
	// we don't add children, we try to put it in the hash
	HashNode temp = new HashNode(root.val, i);
	for (TreeNode kid : root.getChildren())
	// root.addChild(makeDAG(kid, hash, i));
	{
	    temp.addArg(makeDAG(kid, hash, i).indexInTheHash);
	    i++; // works for one level??
	}
	
	hash.put(temp, i);
	return temp;

    }

    public static void main(String[] args) throws Exception {
	TreeNode t = new TreeNode("sum");
	t.addChild("x");
	t.addChild("y");
	HashMap<TreeNode, String> hash = new HashMap<>();
	//makeDAG(t, hash, 1);
	System.out.println(hash.toString());
    }

}
