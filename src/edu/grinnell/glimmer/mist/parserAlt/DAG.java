package edu.grinnell.glimmer.mist.parserAlt;

import java.util.HashMap;
import edu.grinnell.glimmer.mist.parser.TreeNode;

/**
 * Creates a directed acyclic graph from a non directed version
 * @author Albert Owusu-Asare<br>
 *         Vasilisa Bashlovkina<br>
 *         Jason Liu<br>
 * @since 1.0
 *
 *
 */
public class DAG
{

  /**
   * Creates a new  table.
   * @param root the root of the non direct graph
   * @return the <code>table</code> structure containing the HashMap
   *  and next integer value.
   */
  public static Table makeDAG(TreeNode root)
  {
    HashMap<HashNode, Integer> map = new HashMap<HashNode, Integer>();
    Table table = new Table(map);
    HashNode rootNode = makeDAG(root, table);
    return table;
  }// makeDAG(TreeNode)

  public static HashNode makeDAG(TreeNode root, Table table)
  {
    HashNode key = new HashNode(root);
    //if root is not a leaf : process all its children
    if (!root.isLeaf())
      {
        for (TreeNode child : root.getChildren())
          {
              HashNode childNode =makeDAG(child,table);
              //update parent key with child Number
              key.addChildNumber(childNode.getNodeNumber());
          }//for
      }//if
    //child is a leaf
    key.setNodeNumber(table.availableNumber);
    //check if key exist in Map
    if(!table.map.containsKey(key)){
      table.map.put(key, table.getAvailableNumber());
      table.incrementAvailableNum();
    }//if
    return key;
  }//makeDAG(Treenode, Table)

}//DAG
