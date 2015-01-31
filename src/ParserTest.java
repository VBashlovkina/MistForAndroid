import static org.junit.Assert.*;

import org.junit.Test;


public class ParserTest {

    @Test
    public void simpleTest() {
	//TreeNode result = Parser.parse("sum(x,y);");
	TreeNode correct = new TreeNode("sum");
	correct.addChild("x");
	correct.addChild("y");
	TreeNode result = new TreeNode("sum");
	result.addChild("x");
	result.addChild("y");
	assertTrue(correct.equals(result));
    }

}
