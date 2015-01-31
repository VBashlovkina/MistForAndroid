
public class Parser {

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
    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
