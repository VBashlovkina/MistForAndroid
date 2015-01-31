
/**
 * A data type that encapsulates a node and a string array
 * @author albertowusu-asare
 *
 */
public class ParseData {

  private TreeNode node;
  private String [] remaining ;
  
  //Constructor
  ParseData(TreeNode node, String [] remaining){
    this.node = node;
    this.remaining = remaining;
      
  }
  
  /**
   * Returns the Node from this structure
   * @return
   */
  TreeNode getNode(){
    return this.node;
  }
  
  String [] getStringArray(){
    return this.remaining;
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}

