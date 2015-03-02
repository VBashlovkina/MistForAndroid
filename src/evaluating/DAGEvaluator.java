package evaluating;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import dagmaking.DAG;

import parsing.Parser;
import parsing.TreeNode;

public class DAGEvaluator
{
  static HashMap<String, Function> functions = new HashMap<>();

  public static double evaluate(TreeNode root)
  {
    if (root.isSet())
      return root.getEvaluation();
    if (root.isLeaf())
      {
        //STUB
        root.set();
        double val = getContext(root.getNodeVal());
        root.evaluate(val);
        return val; //all leaves have value 1 for now
      }// if leaf

    double[] args = new double[root.numChildren()];
    int i = 0;
    for (TreeNode kid : root.getChildren())
      {
        //STUB
        args[i++] = evaluate(kid);
      }
    root.set();
    root.evaluate(functions.get(root.getNodeVal()).apply(args));
    return root.getEvaluation();
  }//

  private static double getContext(String nodeVal)
  {
    //STUB - assume all context values are 1.0
    return 1.0;
  }

  static void populateFunctionMap()
  {
    // Sum
    functions.put("sum", new Function()
                    {
                      @Override
                      public double apply(double[] args)
                      {
                        double sum = 0;
                        for (double arg : args)
                          sum += arg;
                        return range(sum);
                      }
                    });
    // Mult
    functions.put("mult", new Function()
                    {
                      @Override
                      public double apply(double[] args)
                      {
                        double prod = 0;
                        for (double arg : args)
                          prod *= arg;
                        return range(prod);
                      }
                    });
    functions.put("avg", new Function()
                    {
                      @Override
                      public double apply(double[] args)
                      {
                        double prod = 0;
                        for (double arg : args)
                          prod *= arg;
                        return range(prod);
                      }
                    });
    

  }

  public static void main(String[] args)
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    String code = "sum(neg(x), neg(x), x, y)";
    TreeNode root = Parser.parse(code);
    pen.println("After parsing, the tree is:\n " + root);
    TreeNode dagRoot = DAG.makeDAG(root);
    pen.println("After making a DAG, the tree is:\n " + dagRoot);
    System.out.println("Result is " + evaluate(dagRoot));//should be 4

  }

}
