package evaluating;

import java.util.Iterator;

public class RGBValue
    extends Value
{

  double[] components;
  
  public RGBValue()
  {
    components = new double[3];
  }
  public RGBValue(double red, double green, double blue)
  {
    components = new double[3];
    components[0] = red;
    components[1] = green;
    components[2] = blue;
  }
  
  public RGBValue(double gray)
  {
    components = new double[3];
    components[0] = components[1] = components[2] = gray;
  }
//  
//  public RGBValue(GrayValue gray)
//  {
//    r = g = b = gray.getVal();
//  }
//  
  
  public void range()
  {
    for (int i = 0; i < 3; i++)
      {
        double c = components[i];
        if (c > 1.0)
          components[i] = 1.0;
        else if (c < -1.0)
          components[i] = -1.0;
      }// for each component
  }// range
  
  public void wrap()
  {
    for (int i = 0; i < 3; i++)
      {
        double c = components[i];
        if (c > 1.0)
          components[i] = c - ((int)c/2 + 1)*2;
        else if (c < -1.0)
          components[i] = c - ((int)c/2 - 1)*2;
      }
  }
  public void add(RGBValue toAdd)
  {
    for (int i = 0; i < 3; i++)
      {
        components[i] += toAdd.components[i];
      }// for each componenet
  }// add
  
  public void multiplyBy(RGBValue toMultiply)
  {
    for (int i = 0; i < 3; i++)
      {
        components[i] *= toMultiply.components[i];
      }// for each componenet
  }// multiplyBy
  
  public String toString()
  {
    return "(" + components[0] + ", " + components[1] + ", " + components[2] + ")";
  }

  @Override
  public double getVal()
  {
    // TODO Auto-generated method stub
    return 0;
  }

//  @Override
//  public Iterator<Double> iterator()
//  {
//    return new Iterator<Double>(){
//
//      int state = 0;
//      @Override
//      public boolean hasNext()
//      {
//        return state > 2;
//      }
//
//      @Override
//      public Double next()
//      {
//        // TODO Auto-generated method stub
//        return null;
//      }
//
//      @Override
//      public void remove()
//      {
//        // Unimplemented
//      }
//      
//    };
//  }

  public static void main(String[] args)
  {
    RGBValue seventeen = new RGBValue(17.2, -17.2, -.5);
    seventeen.wrap();
    System.out.println(seventeen);
    System.out.println(((int)17.2 / 2 + 1));
  }
}
