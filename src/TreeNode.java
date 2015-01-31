import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;


public class TreeNode {

    String val;
    //TreeNode[] children;
    ArrayList<TreeNode> children;
    
    public TreeNode(String str)
    {
	val = str;
	children = new ArrayList<>();
    }//TreeNode
    
    public void addChild(String child)
    {
	this.children.add(new TreeNode(child));
    }// addChild
    
    public int numChildren()
    {
	return children.size();
    }//numChildren
    
    public boolean hasChildren()
    {
	if (children.size() > 0)
	    return true;
	return false;
    }//hasChildren
    
    //iterator?
 // Print using a preorder traversal
    /*
    void printhelp(GTNode<E>* root) {
    if (root->isLeaf()) cout << "Leaf: ";
    else cout << "Internal: ";
    cout << root->value() << "\n";
    // Now process the children of "root"
    for (GTNode<E>* temp = root->leftmostChild();
    temp != NULL; temp = temp->rightSibling())
    printhelp(temp);
    }
    */
    public static void print(TreeNode root)
    {
	if (!root.hasChildren())
	    System.out.println("Leaf "+root.val);
	else
	    System.out.println("Internal " + root.val);
	for (TreeNode t : root.children)
	{
	    print(t);
	}
    }
   
    public String toString()
    {
	String result = "";
	return toStringHelper(this, result, "");
    }//toString
    
    String toStringHelper(TreeNode root, String result, String indent)
    {   
	result += indent + root.val + "\n";
	if (root.hasChildren()) //not a leaf
	{
	    indent += " ";
	    for (TreeNode t : root.children)
	    {
		toStringHelper(t, result, indent);
	    }
	}
	return result;
    }
    //remove?
    public void print(PrintWriter pen)
    {
      // A collection of the remaining things to print
      Stack<Object> remaining = new Stack<Object>();
      
      remaining.push(this);
      remaining.push(this.val);
      // Invariants:
      // remaining only contains Strings or BSTNodes
      // All key/value pairs in the tree are either
      // (a) already printed
      // (b) in remaining
      // (c) in or below a node in remaining
      while (!remaining.isEmpty())
        {
          Object next = remaining.pop();
          if (next instanceof String)
            {
              pen.print(next);
              pen.print(" ");
            } // if it's a string
          else
            {
              // next must be a BSTNode
              @SuppressWarnings("unchecked")
              TreeNode node = (TreeNode) next;
              if (node.hasChildren()){
              for (TreeNode t : node.children)
              {
        	  remaining.push(t);
        	  remaining.push(t.val);
              }
            } // if it's a node
            }
        } // while
      pen.println();
    } // print(PrintWriter)

    public boolean equals(TreeNode other)
    {
	//deal with null cases, maybe redundant
	/*if (this == null || other == null)
	{
	    if (this == other)
		return true;
	     return false;
	}*/
	
	if (this.val == other.val && numChildren() == other.numChildren())
	{
	    for (int i = 0; i < numChildren(); i++)
		return this.children.get(i).equals(other.children.get(i));
	}
	return false;

    }// equlas
    
    public static void main(String[] args) {
	TreeNode result = new TreeNode("sum");
	result.addChild("x");
	result.addChild("y");
	PrintWriter pen = new PrintWriter(System.out, true);
	result.print(pen);

    }

}
