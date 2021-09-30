import java.util.Objects;


public class SquareTrinomial {
    private static final double EPS = 1E-6;
    private final double a, b, c;
    
    
    public SquareTrinomial(double a, double b, double c) {
        if (Math.abs(a) < EPS)
            throw new IllegalArgumentException("a must not be 0");
        
        this.a = a;
        this.b = b;
        this.c = c;
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
    
    
    public double[] getCoefficients() {
        return new double[]{getA(), getB(), getC()};
    }
    
    
    public double[] getRealRoots() {
        double d = b * b - 4 * a * c;
        
        if (d < 0)
            return new double[0];
        
        else {
            d = Math.sqrt(d);
            
            if (d ==0)
                return new double[]{(-d - b) / (2 * a)};
            
            else
                return new double[]{(-d - b) / (2 * a), (d - b) / (2 * a)};
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquareTrinomial that = (SquareTrinomial) o;
        return Double.compare(that.a, a) == 0 &&
                Double.compare(that.b, b) == 0 &&
                Double.compare(that.c, c) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
