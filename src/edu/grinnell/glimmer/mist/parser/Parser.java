
package edu.grinnell.glimmer.mist.parser;
import java.io.PrintWriter;
import java.util.*;


/**
 * Main Parser
 * 
 * @author Albert Owusu-Asare
 * 
 */
public class Parser {

    public static TreeNode parse(String code) throws Exception {
	if (code.isEmpty())
	    throw new Exception("Code is an empty string");
	Tokenizer t = new Tokenizer(code);
	return parseHelper(t);
    }

    static TreeNode parseHelper(Tokenizer t) throws Exception {
	TreeNode temp = new TreeNode(t.next());
	String peeked = t.peek();
	if (peeked.equals("(")) {
	    t.next(); // consume the open paren
	    temp.addChild(parseHelper(t));
	    while (t.peek().equals(",")) {
		t.next(); // consume the comma
		temp.addChild(parseHelper(t));
	    }// while comma
	    t.next(); // consume close paren
	    return temp;
	}// if open paren
	else if (peeked.equals(")") || peeked.equals(",") || peeked.isEmpty()) {
	    return temp;
	}// if close paren or comma or empty
	else
	    throw new Exception("Unexpected token " + peeked + " at index "
		    + t.index);
    }// parseHelper

    class HashNode{
	String val;
	ArrayList<Integer> args;
	public HashNode(String v)
	{
	    args = new ArrayList<>();
	    val = v;
	}
	
    }
    
    static String makeDAG(TreeNode root, HashMap<TreeNode, String> hash, int i)
	    throws Exception {

	// what if it is already in the table?
	if (hash.containsKey(root)) {
	    return hash.get(root);
	}
	// if we have a leaf that's not in the hash
	if (!root.hasChildren()) {
	    i++;
	    hash.put(root, i + "");
	    return i + "";
	}
	// we have a non-leaf that is not in the table. we recurse:
	// we don't add children, we try to put it in the hash
	TreeNode temp = new TreeNode(root.val);
	for (TreeNode kid : root.getChildren())
	// root.addChild(makeDAG(kid, hash, i));
	{	    
	    temp.addChild(makeDAG(kid, hash, i));
	    i++; // works for one level
	}
	i++;
	hash.put(temp, i + "");
	return null;

    }

    public static void main(String[] args) throws Exception {
	TreeNode t = new TreeNode("sum");
	t.addChild("x");
	t.addChild("y");
	HashMap<TreeNode, String> hash = new HashMap<>();
	makeDAG(t, hash, 1);
	System.out.println(hash.toString());
    }

}
