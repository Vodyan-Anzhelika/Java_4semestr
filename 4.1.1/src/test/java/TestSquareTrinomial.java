import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestSquareTrinomial {
    private static final double EPS = 1E-6;
    
    
    @Test
    void testSquareTrinomial() {
        SquareTrinomial trinomial = new SquareTrinomial(8.5, 1, -3);
        
        assertAll(
                () -> assertEquals(8.5, trinomial.getA(), EPS),
                () -> assertEquals(1, trinomial.getB()),
                () -> assertEquals(-3, trinomial.getC()),
                () -> assertArrayEquals(new double[]{8.5, 1, -3}, trinomial.getCoefficients(), EPS),
                () -> assertThrows(IllegalArgumentException.class, () -> new SquareTrinomial(0, 1, 1)),
                () -> assertThrows(IllegalArgumentException.class, () -> new SquareTrinomial(0.0000009, 1, 1))
        );
    }
    
    
    @Test
    void testSquareTrinomialGetSquareRoots() {
        SquareTrinomial trinomial1 = new SquareTrinomial(1, 2, -8);
        SquareTrinomial trinomial2 = new SquareTrinomial(.25, -3.5, 12.25);
        SquareTrinomial trinomial3 = new SquareTrinomial(1, 1, 1);
        SquareTrinomial trinomial4 = new SquareTrinomial(16, 0, -64);
        SquareTrinomial trinomial5 = new SquareTrinomial(-0.2349, 0, 0);
        
        assertAll(
                () -> assertArrayEquals(new double[]{-4, 2}, trinomial1.getRealRoots(), EPS),
                () -> assertArrayEquals(new double[]{7}, trinomial2.getRealRoots(), EPS),
                () -> assertArrayEquals(new double[]{}, trinomial3.getRealRoots(), EPS),
                () -> assertArrayEquals(new double[]{-2, 2}, trinomial4.getRealRoots(), EPS),
                () -> assertArrayEquals(new double[]{0}, trinomial5.getRealRoots(), EPS)
        );
    }
    
    
    @Test
    void testSquareTrinomialEquals() {
        SquareTrinomial trinomial1 = new SquareTrinomial(1, 2, -8);
        SquareTrinomial trinomial2 = new SquareTrinomial(1, 2, -8);
        SquareTrinomial trinomial3 = new SquareTrinomial(2, 2, -8);
        SquareTrinomial trinomial4 = new SquareTrinomial(1, 3, -8);
        SquareTrinomial trinomial5 = new SquareTrinomial(1, 2, -2);
        
        assertAll(
                () -> assertEquals(trinomial1, trinomial2),
                () -> assertNotEquals(trinomial1, trinomial3),
                () -> assertNotEquals(trinomial1, trinomial4),
                () -> assertNotEquals(trinomial1, trinomial5),
                () -> assertNotEquals(trinomial1, "")
        );
    }
}
