package functions.first_order_functions;


import functions.FunctionsErrorCode;
import functions.FunctionsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestExpFunc {
    private static final double EPS = 1E-9;
    
    
    @Test
    void testSinFunc() {
        ExpFunc f1 = new ExpFunc(5, -2, -10, 10);
        ExpFunc f2 = new ExpFunc(1, 2);
        
        assertAll(
                () -> assertEquals(5, f1.getA()),
                () -> assertEquals(-2, f1.getB()),
                () -> assertEquals(-10, f1.getLeft()),
                () -> assertEquals(10, f1.getRight()),
                () -> assertEquals(1, f2.getA()),
                () -> assertEquals(2, f2.getB()),
                () -> assertEquals(-Double.MAX_VALUE, f2.getLeft()),
                () -> assertEquals(Double.MAX_VALUE, f2.getRight())
        );
        
        try {
            ExpFunc f3 = new ExpFunc(1, 1, 1, -1);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.LEFT_GT_RIGHT, e.getErrorCode());
        }
    }
    
    
    @Test
    void testSinFuncGetValue() {
        ExpFunc f1 = new ExpFunc(1.5, -4.5, -20, 10);
        ExpFunc f2 = new ExpFunc(-1, 0.5);
        
        assertAll(
                () -> assertEquals(-3, f1.getValue(0), EPS),
                () -> assertEquals(33035.1986922101, f1.getValue(10), EPS),
                () -> assertEquals(-0.5, f2.getValue(0), EPS),
                () -> assertEquals(0.5, f2.getValue(-333), EPS)
        );
        
        try {
            double y1 = f1.getValue(10.1);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.ARGUMENT_OUT_OF_DOMAIN, e.getErrorCode());
        }
        
        try {
            double y2 = f1.getValue(-20.001);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.ARGUMENT_OUT_OF_DOMAIN, e.getErrorCode());
        }
    }
    
    
    @Test
    void testExpFuncEquals() {
        ExpFunc f1 = new ExpFunc(1, 2, -2, 2);
        ExpFunc f2 = new ExpFunc(1, 2, -2, 2);
        ExpFunc f3 = new ExpFunc(0, 2, -2, 2);
        ExpFunc f4 = new ExpFunc(1, 1, -2, 2);
        ExpFunc f5 = new ExpFunc(1, 2, -1, 2);
        ExpFunc f6 = new ExpFunc(1, 2, -2, 1);
        ExpFunc f7 = new ExpFunc(1, 2);
        
        assertAll(
                () -> assertEquals(f1, f1),
                () -> assertEquals(f1, f2),
                () -> assertNotEquals(f1, f3),
                () -> assertNotEquals(f1, f4),
                () -> assertNotEquals(f1, f5),
                () -> assertNotEquals(f1, f6),
                () -> assertNotEquals(f1, f7),
                () -> assertNotEquals(f1, "")
        );
    }
}
