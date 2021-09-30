package functions.first_order_functions;


import functions.FunctionsErrorCode;
import functions.FunctionsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestLinearFunc {
    private static final double EPS = 1E-9;
    
    
    @Test
    void testLinearFunc() {
        LinearFunc f1 = new LinearFunc(5, -2, -10, 10);
        LinearFunc f2 = new LinearFunc(1, 2);
        
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
            LinearFunc f3 = new LinearFunc(1, 1, 1, -1);
            fail();
        } catch (FunctionsException e) {
            assertEquals(FunctionsErrorCode.LEFT_GT_RIGHT, e.getErrorCode());
        }
    }
    
    
    @Test
    void testLinearFuncGetValue() {
        LinearFunc f1 = new LinearFunc(1.5, -4.5, -20, 10);
        LinearFunc f2 = new LinearFunc(-1, 3);
        
        assertAll(
                () -> assertEquals(0, f1.getValue(3), EPS),
                () -> assertEquals(10.5, f1.getValue(10), EPS),
                () -> assertEquals(3, f2.getValue(0), EPS),
                () -> assertEquals(-997, f2.getValue(1000), EPS)
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
    void testLinearFuncEquals() {
        LinearFunc f1 = new LinearFunc(1, 1, -1, 1);
        LinearFunc f2 = new LinearFunc(1, 1, -1, 1);
        LinearFunc f3 = new LinearFunc(1, 1, -2, 1);
        LinearFunc f4 = new LinearFunc(1, 1, -1, 2);
        LinearFunc f5 = new LinearFunc(1, 1);
        LinearFunc f6 = new LinearFunc(3, 1, -1, 1);
        LinearFunc f7 = new LinearFunc(1, 3, -1, 1);
        
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
