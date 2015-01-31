import java.io.PrintWriter;
import java.util.Arrays;


/**
 * Main Parser
 * @author Albert Owusu-Asare
 *
 */
public class Parser {

   /**
    *  Parses the contents of the input string to a more usable tree-like
    *  structure.
    *  
    * @param input, the string with the input code
    * @return
    */
  public static TreeNode parser(String input){
    String [] splitArray = input.split("\\(");
    System.out.println("Array in parser :" + Arrays.toString(splitArray));
    int index = 0;
    TreeNode root = new TreeNode(splitArray[index++]);
    return parser(splitArray,root,index).getNode();
  }
  
  /*
   * Helper function for parser.
   * @param splitArray the array produced by spliting input string with regex "("
   * @param root, the node representing the root command
   * @param index, integer to keep track of where we are during parsing
   * @return an encapsulated data type, contining a tree Node and the rest of 
   * a string to be parsed
   */
  
  private static ParseData parser(String [] splitArray, TreeNode root, int index){
    String tempHolder  = splitArray[index++];
    String [] children = tempHolder.split("[,\\)]");
    TreeNode childNode = null;
    //add each element to root as a node
       for( int i = 0; i<children.length;i++){
         if((children[i] == null || children[i].trim().equals(""))){
           String [] subArray =  Arrays.copyOfRange(children, i + 1, children.length);
           return new ParseData(root, subArray);
         }
         else {
           childNode = new TreeNode(children[i]);
           root.addChild(childNode);
         }//add child notdes to root
       }//for 
    //populate remaining of child trees
    ParseData fromChild = parser(splitArray, childNode,index);
    String [] dataLeft = fromChild.getStringArray();
    if( dataLeft !=null){
       for( int i = 0; i<dataLeft.length;i++){
         if((dataLeft[i] == null || dataLeft[i].trim().equals(""))){
           String [] subArray =  Arrays.copyOfRange(dataLeft, i + 1, dataLeft.length);
           return new ParseData(root, subArray);
         }
         else {
           childNode = new TreeNode(dataLeft[i]);
           root.addChild(childNode);
         }//add child notdes to root
       }//for
    }
    return new ParseData(root,null);
  }//parser(String [], TreeNode,int)
  
  

   public static TreeNode parse(String code)
   {
       TreeNode result = null;
       if (code.length() == 0)
     return result;
       
       // Put code into char array
       char[] buffer = code.toCharArray();
       
       // Special case: the first string 
       /** Options
         -- Split code at '('?
         -- Go char by char and recurse at ( ?
         -- Have a stack of parens
   -- Count the level of each node, represent the tree as a matrix?
       */
       return result;
   }
   
   /** 
    * Subroutine for processString
    * @param input
    * @return
    */
   public static String [] processString(String []input){
     for( int i = 0; i<input.length;i++){
       if((input[i] == null || input[i].trim().equals(""))){
         String [] subArray =  Arrays.copyOfRange(input, i + 1, input.length);
         return subArray;
       }
     }
     return null;
   }
    public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out,true);
  //String input = "rgb(sq(sum(x,y,t.s)),t.s,y)";
   // String input = "rgb(mult(sign(wsum(sin(neg(mult(y, x))), wsum(t.s, square(cos(y))))), wsum(t.s, square(cos(y)))), sign(wsum(sin(neg(mult(y, x))), wsum(t.s, square(cos(y))))), sin(neg(mult(y, x))))";
    String input = "rgb(t.s,y,sq(sum(x,y,t.s)))";
  String [] splitArray = input.split("\\(");
  pen.println(Arrays.toString(splitArray));
  String test  = splitArray[3];
  String [] testArray = test.split("[,\\)]");
  pen.println(test + test.contains(")"));
  pen.println(Arrays.toString(testArray) + testArray.length);
  String [] copyOfRangeTest = processString(testArray);
  pen.println(Arrays.toString(copyOfRangeTest));
  
  TreeNode root = parser(input);
  TreeNode.print(root);
  pen.println(root);   
  }

}

