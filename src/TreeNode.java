
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

   
    public String toString()
    {
	String result = "";
	return toStringHelper(this, "");
    }//toString
    
    String toStringHelper(TreeNode root, String indent)
    {   
	String result = indent + root.val + "\n";
	//result += indent + root.val + "\n";
	if (root.hasChildren()) //not a leaf
	{
	    indent += "  ";
	    for (TreeNode t : root.children)
	    {
		result += toStringHelper(t, indent);
	    }
	}
	return result;
    }
    
    public ArrayList<TreeNode> getChildren(){
    	return this.children;
    }
    public void addChild(TreeNode child){
    	this.children.add(child);
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
    
  /*  public String toString(){
	String temp = "[" + this.val;
	if(! this.children.isEmpty()){
	for(TreeNode node : this.children)
	{
		temp += node.toString();
	}
	}
	
	temp += "]";
	return temp;
}
*/

    public static void main(String[] args) {
	TreeNode result = new TreeNode("sum");
	result.addChild("x");
	result.addChild("y");
	PrintWriter pen = new PrintWriter(System.out, true);
	pen.println(result);

    }

}
