package alternative;

import java.util.ArrayList;
import java.util.Arrays;

public class AltParser {

	public static AltTreeNode parse(String code)
	{
		String[] splits = magicSplit(code);
		AltTreeNode root = new AltTreeNode(splits[0]);
		return parse(splits, root);
	}
	
	
	/**
	 * Magic split splits the string at commas and parens, throwing away commas
	 * but keeping parens as members of the resulting splits array
	 * @param code
	 * @return
	 */
	static String[] magicSplit(String code)
	{
		String[] commaSplit = code.split(",");
		
		char[] withParens;
		String withoutParens;
		ArrayList<String> result = new ArrayList<>();
		char c;
		for (String str : commaSplit)
		{
			withParens = str.toCharArray();
			withoutParens = "";
			for (int i = 0; i < withParens.length; i++)
			{
				if ((c = withParens[i]) == '(' ||
					c == ')')
				{
					// put the withoutParens so far into the ArrayList
					if (!withoutParens.equals(""))
						result.add(new String(withoutParens));
					// clear without parens
					withoutParens = "";
					// put the paren into the arraylist, too
					result.add(c + "");
				}
				else
					withoutParens += c;
			}// for each char
			
			// Deal with remaining withoutParens
			if (!withoutParens.equals(""))
				result.add(new String(withoutParens));
		}// for each str in the commaSplit
		
		// put the resulting ArrayList into a regular array (DO WE NEED THIS?)
		String[] toReturn = new String[result.size()];
		for (int i = 0; i < toReturn.length; i++)
		{
			toReturn[i] = result.get(i);
		}
		return toReturn;	
	}// magicSplit
	
	static AltTreeNode parse(String[] splits, AltTreeNode root)
	{
		AltTreeNode currentRoot = root;

		for (int i = 2; i < splits.length; i++)
		{
			if (splits[i].equals("(")) //open paren means previously added node has children
				currentRoot = currentRoot.children.get(
						currentRoot.numChildren() - 1); //will be adding to the last child
			else if (splits[i].equals(")"))
				currentRoot = currentRoot.parent; // closed paren means we need to return to the parent
			else
				currentRoot.addChild(splits[i]);
		}
		return root;
	}// parse
		
	
	public static void main(String[] args) {
		String test = "rgb(sq(sum(x,y)),x,y)";
		String okay = "rgb(wsum(x, y, y, t.s), sum(sum(wsum(x, x, y, neg(t.s), x), wsum(y, y, x, neg(t.s), y)), sum(wsum(y, x, x, t.s), wsum(x, y, y, t.s))), sum(wsum(x, x, y, neg(t.s), x), wsum(y, y, x, neg(t.s), y)))";		
		System.out.println("Printing out test tree:\n" + test + "\n" + parse(test));
		System.out.println("Printing out okay tree:\n" + okay + "\n" + parse(okay));

	}

}
