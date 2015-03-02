package evaluating;

public abstract class Function
{
  public abstract double apply(double[] args);
 
  public double range(double result)
  {
    if (result > 1.0)
      return 1.0;
    else if (result < -1.0)
      return -1.0;
    else
      return result;
  }

  
}// abstract class Function
