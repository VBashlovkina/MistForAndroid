package edu.grinnell.glimmer.mist.parserAlt;

import java.io.PrintWriter;
import java.util.HashMap;

import edu.grinnell.glimmer.mist.parser.Parser;
import edu.grinnell.glimmer.mist.parser.TreeNode;

/**
 * Creates a directed acyclic graph from a non directed version
 * @author Albert Owusu-Asare<br>
 *         Vasilisa Bashlovkina<br>
 *         Jason Liu<br>
 * @since 1.0
 *
 */
public class DAG
{

  /**
   * Creates a new DAG.
   * @param root the root of the tree
   * @return the root of a DAG
   */
  public static TreeNode makeDAG(TreeNode root)
  {
    HashMap<HashNode, Integer> map = new HashMap<>();
    HashMap<Integer, HashNode> reverseMap = new HashMap<>();
    Table table = new Table(map);
    HashNode rootNode = makeTable(root, table, reverseMap);
    return makeDAGHelper(rootNode, reverseMap);
  }// makeDAG(TreeNode)

  public static HashNode makeTable(TreeNode root, Table table,
                                   HashMap<Integer,HashNode> reverseMap)
  {
    HashNode key = new HashNode(root);
    //if root is not a leaf : process all its children
    if (!root.isLeaf())
      {
        for (TreeNode child : root.getChildren())
          {
            HashNode childNode = makeTable(child, table, reverseMap);
            //update parent key with child Number
            key.addChildNumber(childNode.getNodeNumber());
          }//for
      }//if
    
    
    //for a leaf, check if it exists in the table
    if (!table.map.containsKey(key))
      {
        // set they key's number 
        key.setNodeNumber(table.availableNumber);
        // put it in the map and the reverse map
        table.map.put(key, key.getNodeNumber());
        reverseMap.put(key.getNodeNumber(), key);
        table.incrementAvailableNum();
      }//if
    else 
        key.setNodeNumber(table.map.get(key));
    return key;
  }//makeDAG(Treenode, Table)


  private static TreeNode makeDAGHelper(HashNode hashRoot,
                                        HashMap<Integer, HashNode> reverseMap)
  {

    TreeNode dagRoot = new TreeNode(hashRoot.getNodeVal());
    // if it's a leaf
    if (hashRoot.getChildrenNumbers().isEmpty())
      {
        return dagRoot;
      }

    // otherwise, we need to recurse on its children
    for (Integer kid : hashRoot.getChildrenNumbers())
      {
        HashNode temp = reverseMap.get(kid);

        //TreeNode newChild = new TreeNode(temp.node.val);
        dagRoot.addChild(makeDAGHelper(temp, reverseMap));
      }
    return dagRoot;
  }

  public static void main(String[] args)
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);

    String code = "sum(neg(x), neg(x), x, y)";
    
    TreeNode root = Parser.parse(code);
    
    pen.println("After parsing, the tree is:\n " + root);
    
    TreeNode dagRoot  = makeDAG(root);
    
    pen.println("After making a DAG, the tree is:\n " + dagRoot);
    
    // Testing makeDAG for tree 1 level tree with 2 leafs
//    pen.println("Test makeDAG() 1 level tree with 2 leafs");
//    pen.println("====================================");
//    TreeNode test1Root = new TreeNode("square");
//    test1Root.addChild("x");
//    test1Root.addChild("y");
//    pen.println(test1Root);
//    Table test1Table = makeTable(test1Root);
//    pen.println(test1Table.map);
//    pen.println();
//    pen.println("Test makeDAG() 2 levels tree with 3 leafs");
//    pen.println("====================================");
//    TreeNode test2Root = new TreeNode("square");
//    test2Root.addChild("x");
//    test2Root.addChild(test1Root);
//    pen.println(test2Root);
//    Table test2Table = makeTable(test2Root);
//    pen.println(test2Table.map);
//    pen.println();
//    String code = "sum(neg(x), neg(x), x, y)";
//    pen.println("Test makeDAG() of " + code);
//    pen.println("====================================");
//    TreeNode sumNegNeg = Parser.parse(code);
//    pen.println(sumNegNeg);
//    Table sNNmap = makeTable(sumNegNeg);
//    pen.println(sNNmap.map);
//    System.out.println("Resulting DAG/tree thing: \n" + makeDAG(sNNmap));

  }// main

}//DAG
