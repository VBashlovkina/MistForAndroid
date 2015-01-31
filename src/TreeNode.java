import java.util.ArrayList;


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
    //remove?
    
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
	print(result);

    }

}
