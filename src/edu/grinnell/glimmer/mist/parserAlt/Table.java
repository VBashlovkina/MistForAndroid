package edu.grinnell.glimmer.mist.parserAlt;
import java.io.PrintWriter;
import java.util.HashMap;

public class Table
{
   /**
   * Encapsulates a HashMap and an Integer counter
   * @author Albert Owusu-Asare
   * @since 1.0
   */
 
    HashMap<HashNode, Integer> map;
    
    Integer availableNumber;

    private HashNode rootNode;
    /**
     * Constructs a <code>table</code>
     * @param map a HashMap of key <code>HashNode</code> value:
     *            <code>Integer</code>
     */
    public Table(HashMap<HashNode, Integer> map)
    {
      this.map = map;
      this.availableNumber = 1;
      this.rootNode = null;
    }// Table

    /**
     * Increments the available Number
     */

    public void incrementAvailableNum()
    {
      this.availableNumber++;
    }//

    /**
     * Gets the rootNode
     * @return
     */
    public HashNode getRootNode(){
      return this.rootNode;
    }
    
    /**
     * Sets the rootNode
     * @param rootNode
     */
    public void setRootNode(HashNode rootNode){
      this.rootNode= rootNode;
    }
    /**
     * Returns the available number
     */
    public Integer getAvailableNumber()
    {
      return availableNumber;
    }// getAvailableNumber()

    public static void main(String[] args)
    {
      PrintWriter pen = new PrintWriter(System.out, true);
      HashMap<HashNode, Integer> map = new HashMap<HashNode, Integer>();
      Table table = new Table(map);

      // Testing increment
      pen.println("Before increment():" + table.getAvailableNumber());
      table.incrementAvailableNum();
      pen.println("After increment():" + table.getAvailableNumber());

      
      //Equality of HashMaps
      HashMap<String,Integer> map2  = new HashMap<String,Integer>();
      map2.put("a", 1);
      map2.put("b", 2);
      
      HashMap<String,Integer> map4  = new HashMap<String,Integer>();
      map4.put("a", 1);
      map4.put("b", 2);
      
      pen.println(map2.equals(map4)); 
    }
  }// Table
