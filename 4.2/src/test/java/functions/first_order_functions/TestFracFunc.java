package functions.first_order_functions;


import functions.FunctionsErrorCode;
import functions.FunctionsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestFracFunc {
    private static final double EPS = 1E-9;
    
    
    @Test
    void testFracFunc() {
        FracFunc f1 = new FracFunc(1, 2, 3, 4, 5, 6);
        FracFunc f2 = new FracFunc(1, 2, 3, 4);
        
        assertAll(
                () -> assertEquals(1, f1.getA(), EPS),
                () -> assertEquals(2, f1.getB(), EPS),
                () -> assertEquals(3, f1.getC(), EPS),
                () -> assertEquals(4, f1.getD(), EPS),
                () -> assertEquals(5, f1.getLeft(), EPS),
                () -> assertEquals(6, f1.getRight(), EPS),
                () -> assertEquals(-Double.MAX_VALUE, f2.getLeft(), EPS),
                () -> assertEquals(Double.MAX_VALUE, f2.getRight(), EPS)
        );
        
        try {
            FracFunc f3 = new FracFunc(1, 2, 0, 0);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.NULL_DENOMINATOR, e.getErrorCode());
        }
        
        try {
            FracFunc f4 = new FracFunc(1, 1, 1, 1, 1, 0);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.LEFT_GT_RIGHT, e.getErrorCode());
        }
    }
    
    
    @Test
    void testFracFuncGetValue() {
        FracFunc f1 = new FracFunc(1.5, -4.5, -1, 3, -20, 10);
        FracFunc f2 = new FracFunc(-1, 0.5, 1, -2);
        FracFunc f3 = new FracFunc(0, 0, 1, 1);
    
        assertAll(
                () -> assertEquals(-1.5, f1.getValue(0), EPS),
                () -> assertEquals(-1.5, f1.getValue(9), EPS),
                () -> assertEquals(0, f2.getValue(0.5), EPS),
                () -> assertTrue(Double.isInfinite(f2.getValue(2)))
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
    void testFracFuncEquals() {
        FracFunc f1 = new FracFunc(1, 1, -1, 1);
        FracFunc f2 = new FracFunc(1, 1, -1, 1);
        FracFunc f3 = new FracFunc(-1, 1, -1, 1);
        FracFunc f4 = new FracFunc(1, -1, -1, 1);
        FracFunc f5 = new FracFunc(1, 1, 1, 1);
        FracFunc f6 = new FracFunc(1, 1, -1, -1);
        FracFunc f7 = new FracFunc(1, 1, -1, 1, -100, 100);
    
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
