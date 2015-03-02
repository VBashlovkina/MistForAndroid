package evaluating;

import java.io.PrintWriter;
import java.util.HashMap;

import dagmaking.DAG;

import parsing.Parser;
import parsing.TreeNode;

public class DAGEvaluator
{
  HashMap<String, Function> functions;

  public DAGEvaluator()
  {
    functions = new HashMap<>();
    populateFunctionMap();
  }

  public RGBValue evaluate(TreeNode root)
    throws Exception
  {
    if (root.isSet())
      return root.getEvaluation();
    if (root.isLeaf())
      {
        //STUB
        root.set();
        RGBValue val = getContext(root.getNodeVal());
        root.evaluate(val);
        return val; //all leaves have value 1 for now
      }// if leaf

    RGBValue[] args = new RGBValue[root.numChildren()];
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

  private static RGBValue getContext(String nodeVal)
  {
    //STUB - assume all context values are 1.0
    return new RGBValue(1.0, 1.0, 1.0);
  }

  void populateFunctionMap()
  {
    // Sum
    functions.put("sum", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                      {
                        RGBValue sum = new RGBValue(0, 0, 0);
                        for (RGBValue arg : args)
                          {
                            sum.add(arg);
                          }// for each argument
                        sum.range();
                        return sum;
                      }
                    });
    // Mult
    functions.put("mult", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                      {
                        RGBValue prod = new RGBValue(0, 0, 0);
                        for (RGBValue arg : args)
                          {
                            prod.multiplyBy(arg);
                          }// for each argument
                        prod.range();
                        return prod;
                      }
                    });
    // Average
    functions.put("avg", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                      {
                        RGBValue sum = new RGBValue(0, 0, 0);
                        for (RGBValue arg : args)
                          {
                            sum.add(arg);
                          }// for each argument
                        sum.range();
                        sum.multiplyBy(new RGBValue(1 / ((double) args.length)));
                        return sum;
                      }
                    });
    functions.put("wsum", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                      {
                        RGBValue sum = new RGBValue(0, 0, 0);
                        for (RGBValue arg : args)
                          {
                            sum.add(arg);
                          }// for each argument
                        sum.wrap();
                        return sum;
                      }
                    });

    functions.put("neg", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                        throws Exception
                      {
                        if (args.length != 1)
                          throw new Exception();
                        RGBValue result = new RGBValue();
                        for (int i = 0; i < 3; i++)
                          {
                            result.components[i] = -1.0 * args[0].components[i];
                          }// for each component
                        result.range();
                        return result;
                      }
                    });
    functions.put("sin", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                        throws Exception
                      {
                        if (args.length != 1)
                          throw new Exception();
                        RGBValue result = new RGBValue();
                        for (int i = 0; i < 3; i++)
                          {
                            result.components[i] =
                                Math.sin(args[0].components[i]);
                          }// for each component
                        result.range();
                        return result;
                      }
                    });
    functions.put("cos", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                        throws Exception
                      {
                        if (args.length != 1)
                          throw new Exception();
                        RGBValue result = new RGBValue();
                        for (int i = 0; i < 3; i++)
                          {
                            result.components[i] =
                                Math.cos(args[0].components[i]);
                          }// for each component
                        result.range();
                        return result;
                      }
                    });

    functions.put("sqr", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                        throws Exception
                      {
                        if (args.length != 1)
                          throw new Exception();
                        RGBValue result = new RGBValue();
                        for (int i = 0; i < 3; i++)
                          { // Notice that we allow negative inputs 
                            result.components[i] =
                                Math.sqrt(Math.abs(args[0].components[i]));
                          }// for each component
                        result.range();
                        return result;
                      }
                    });
    functions.put("sqr", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                        throws Exception
                      {
                        if (args.length != 1)
                          throw new Exception();
                        RGBValue result = new RGBValue();
                        for (int i = 0; i < 3; i++)
                          { // Notice that we allow negative inputs 
                            result.components[i] =
                                Math.abs(args[0].components[i]);
                          }// for each component
                        result.range();
                        return result;
                      }
                    });

    // Non-component-wise sign
    functions.put("sign", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                        throws Exception
                      {
                        if (args.length != 1)
                          throw new Exception();

                        if (args[0].components[0] > 0
                            && args[0].components[1] > 0
                            && args[0].components[2] > 0)
                          return new RGBValue(1.0);
                        else
                          return new RGBValue(-1.0);
                      }
                    });
    // Non-component-wise if
    functions.put("if", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                        throws Exception
                      {
                        if (args.length != 3)
                          throw new Exception();
                        // If each component of the test is positive
                        if (args[0].components[0] > 0
                            && args[0].components[1] > 0
                            && args[0].components[2] > 0)
                          // return the first option
                          return args[1];
                        else
                          // otherwise, the second option
                          return args[2];
                      }
                    });
    functions.put("rgb", new Function()
                    {
                      public RGBValue apply(RGBValue[] args)
                        throws Exception
                      {
                        if (args.length != 3)
                          throw new Exception();
                        // Take the first component of the first child, 
                        // second of the second child, etc
                        RGBValue result =
                            new RGBValue(args[0].components[0],
                                         args[1].components[1],
                                         args[2].components[2]);
                        result.range();
                        return result;
                      }
                    });

  }// populateFunctionMap

  public static void main(String[] args)
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    String code = "sum(neg(x), avg(x,y), x, y)";
    TreeNode root = Parser.parse(code);
    pen.println("After parsing, the tree is:\n " + root);
    TreeNode dagRoot = DAG.makeDAG(root);
    pen.println("After making a DAG, the tree is:\n " + dagRoot);
    DAGEvaluator e = new DAGEvaluator();
    System.out.println("Result is " + e.evaluate(dagRoot));//should be 4

  }

}
