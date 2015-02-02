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
    }//AltTreeNode(String)
    
    public AltTreeNode(String str, AltTreeNode par) 
    {
	val = str;
	parent = par;
	children = new ArrayList<>();
    }// AltTreeNode(String, AltTreeNode)
    
    public void addChild(String child)
    {
    	this.children.add(new AltTreeNode(child, this));
	
    }//addChild
    
    public int numChildren()
    {
	return children.size();
    }//numChildren
    
    public boolean hasChildren()
    {
	return children.size() > 0;
    }//hasChildren
        
    public String toString()
    {
	return toStringHelper(this, "");
    }//toString
    
    String toStringHelper(AltTreeNode root, String indent)
    {   
    	String result = indent + root.val + "\n";
    	if (root.hasChildren()) //not a leaf
    	{
    		indent += "\t";
    		for (AltTreeNode t : root.children)
    			result += toStringHelper(t, indent);
    	}
    	return result;
    }// toStringHelper
    
    
    
}// class AltTreeNode
