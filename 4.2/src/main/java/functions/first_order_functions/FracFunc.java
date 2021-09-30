package functions.first_order_functions;


import functions.FunctionsErrorCode;
import functions.FunctionsException;

import java.util.Objects;

import static java.lang.Math.abs;


public class FracFunc implements Function {
    private static final double EPS = 1E-9;
    private double a;
    private double b;
    private double c;
    private double d;
    private double left;
    private double right;
    
    
    public FracFunc(double a, double b, double c, double d, double left, double right) {
        if (left - right >= EPS)
            throw new FunctionsException(FunctionsErrorCode.LEFT_GT_RIGHT);
        
        if ((abs(c) < EPS) && (abs(d) < EPS))
            throw new FunctionsException(FunctionsErrorCode.NULL_DENOMINATOR);
        
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.left = left;
        this.right = right;
    }
    
    
    public FracFunc(double a, double b, double c, double d) {
        this(a, b, c, d, -Double.MAX_VALUE, Double.MAX_VALUE);
    }
    
    
    public double getA() {
        return a;
    }
    
    
    public double getB() {
        return b;
    }
    
    
    public double getC() {
        return c;
    }
    
    
    public double getD() {
        return d;
    }
    
    
    @Override
    public double getLeft() {
        return left;
    }
    
    
    @Override
    public double getRight() {
        return right;
    }
    
    
    @Override
    public double getValue(double x) {
        if ((x - left <= -EPS) || (x - right >= EPS))
            throw new FunctionsException(FunctionsErrorCode.ARGUMENT_OUT_OF_DOMAIN);
        
        return (a * x + b) / (c * x + d);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FracFunc)) return false;
        FracFunc that = (FracFunc) o;
        return abs(that.left - left) < EPS &&
                abs(that.right - right) < EPS &&
                abs(c) >= EPS && abs(that.c) >= EPS && abs(a / c - that.a / that.c) < EPS &&
                abs(d) >= EPS && abs(that.d) >= EPS && abs(b / d - that.b / that.d) < EPS;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d, left, right);
    }

}
