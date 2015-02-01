package alternative;





import java.util.ArrayList;

public class AltTreeNode {

	String val;
	ArrayList<AltTreeNode> children;
	AltTreeNode parent;
	
	public AltTreeNode(String str)
    {
	val = str;
	children = new ArrayList<>();
    }//TreeNode
    
	public AltTreeNode(String str, AltTreeNode par)
	{
		val = str;
		parent= par;
		children = new ArrayList<>();
	}
    public void addChild(String child)
    {
    	this.children.add(new AltTreeNode(child, this));
	
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
    
    
    public String toString()
    {
	String result = "";
	return toStringHelper(this, result, "");
    }//toString
    
    String toStringHelper(AltTreeNode root, String result, String indent)
    {   
    	String res = indent + root.val + "\n";
	//result += indent + root.val + "\n";
    	if (root.hasChildren()) //not a leaf
    	{
    		indent += "  ";
    		for (AltTreeNode t : root.children)
	    
    			res += toStringHelper(t, result, indent);
    	}
    	return res;
	}
    
}// class
