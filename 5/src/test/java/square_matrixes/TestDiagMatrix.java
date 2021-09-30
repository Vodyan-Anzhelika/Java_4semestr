package square_matrixes;


import matrixes.IMatrix;
import matrixes.MatrixErrorCode;
import matrixes.square_matrixes.DiagMatrix;
import matrixes.square_matrixes.SquareMatrix;
import matrixes.square_matrixes.UpTriangleMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestDiagMatrix {
    @Test
    void testConstr() {
        DiagMatrix matrix = new DiagMatrix(2);
        
        assertAll(
                () -> assertEquals(2, matrix.getDimension()),
                () -> assertEquals(0, matrix.getElem(0, 0), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(0, 1), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(1, 0), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(1, 1), DiagMatrix.EPS)
        );
    }
    
    
    @Test
    void testConstrExceptions() {
        try {
            IMatrix matrix = new DiagMatrix(-5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Matrix dimension cannot be negative, but was -5", e.getMessage());
        }
    }
    
    
    @Test
    void testElem() {
        IMatrix matrix = new DiagMatrix(3);
        
        matrix.setElem(0, 0, 10);
        matrix.setElem(0, 1, 0);
        matrix.setElem(1, 1, -2.1);
        matrix.setElem(2, 0, 0);
        matrix.setElem(2, 1, 0);
        matrix.setElem(2, 2, 2341);
        
        assertAll(
                () -> assertEquals(10, matrix.getElem(0, 0), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(0, 1), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(0, 2), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(1, 0), DiagMatrix.EPS),
                () -> assertEquals(-2.1, matrix.getElem(1, 1), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(1, 2), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(2, 0), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(2, 1), DiagMatrix.EPS),
                () -> assertEquals(2341, matrix.getElem(2, 2), DiagMatrix.EPS)
        );
    }
    
    
    @Test
    void testSetElemExceptions() {
        IMatrix matrix1 = new DiagMatrix(5);
        IMatrix matrix2 = new DiagMatrix(11);
        
        try {
            matrix1.setElem(-2, 1, 2);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 5, row = -2, column = 1", e.getMessage());
        }
        
        try {
            matrix2.setElem(1, -1, 1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 11, row = 1, column = -1", e.getMessage());
        }
        
        try {
            matrix2.setElem(11, 11, 8);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 11, row = 11, column = 11", e.getMessage());
        }
        
        try {
            matrix1.setElem(3, 6, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 5, row = 3, column = 6", e.getMessage());
        }
        
        try {
            matrix2.setElem(1, 2, 1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 11, row = 1, column = 2", e.getMessage());
        }
    }
    
    
    @Test
    void testGetElemExceptions() {
        IMatrix matrix1 = new DiagMatrix(3);
        IMatrix matrix2 = new DiagMatrix(16);
        
        try {
            double a = matrix1.getElem(-2, 1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 3, row = -2, column = 1", e.getMessage());
        }
        
        try {
            double b = matrix2.getElem(1, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 16, row = 1, column = -1", e.getMessage());
        }
        
        try {
            double c = matrix2.getElem(16, 2);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 16, row = 16, column = 2", e.getMessage());
        }
        
        try {
            double d = matrix1.getElem(2, 100);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 3, row = 2, column = 100", e.getMessage());
        }
    }
    
    
    @Test
    void testGetDeterminant0() {
        try {
            double determinant = new DiagMatrix(0).getDeterminant();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(MatrixErrorCode.ZERO_DIMENSION, e.getMessage());
        }
    }
    
    
    @Test
    void testGetDeterminant1() {
        IMatrix matrix = new DiagMatrix(1);
        
        assertEquals(0, matrix.getDeterminant(), DiagMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant2() {
        IMatrix matrix = new DiagMatrix(1);
        
        matrix.setElem(0, 0, 2);
        
        assertEquals(2, matrix.getDeterminant(), DiagMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant3() {
        IMatrix matrix = new DiagMatrix(2);
        
        matrix.setElem(1, 1, 5);
        
        assertEquals(0, matrix.getDeterminant(), DiagMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant4() {
        IMatrix matrix = new DiagMatrix(2);
        
        matrix.setElem(0, 0, -2);
        matrix.setElem(1, 1, 5);
        
        assertEquals(-10, matrix.getDeterminant(), DiagMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant5() {
        IMatrix matrix = new DiagMatrix(3);
        
        matrix.setElem(0, 0, -1);
        matrix.setElem(1, 1, 8);
        matrix.setElem(2, 2, 2.5);
        
        assertEquals(-20, matrix.getDeterminant(), DiagMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant6() {
        IMatrix matrix = new DiagMatrix(5);
        
        matrix.setElem(0, 0, -10);
        matrix.setElem(1, 1, 1.25);
        matrix.setElem(2, 2, 1.5);
        matrix.setElem(3, 3, 20);
        matrix.setElem(4, 4, -0.01);
        
        assertEquals(3.75, matrix.getDeterminant(), DiagMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant7() {
        IMatrix matrix = new DiagMatrix(19);
        
        for (int i = 0; i < 19; i++)
            matrix.setElem(i, i, Math.pow(-1, i) * (i % 3 + 1));
        
        assertAll(
                () -> assertEquals(-46656, matrix.getDeterminant(), DiagMatrix.EPS),
                () -> assertEquals(-46656, matrix.getDeterminant(), DiagMatrix.EPS)
        );
    }
    
    
    @Test
    void testSwapStrings() {
        DiagMatrix actual = new DiagMatrix(3);
        DiagMatrix expected = new DiagMatrix(3);
        
        actual.setElem(0, 0, 2);  actual.setElem(1, 1, 0);  actual.setElem(2, 2, 0);
        expected.setElem(0, 0, 2);  expected.setElem(1, 1, 0);  expected.setElem(2, 2, 0);
        
        actual.swapStrings(1, 2);
        
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertEquals(0, actual.getDeterminant(), UpTriangleMatrix.EPS)
        );
    }
    
    
    @Test
    void testSwapStringsExceptions() {
        DiagMatrix matrix0 = new DiagMatrix(0);
        DiagMatrix matrix1 = new DiagMatrix(2);
        DiagMatrix matrix2 = new DiagMatrix(2);
        
        matrix2.setElem(0, 0, 1);
        
        try {
            matrix0.swapStrings(0, 0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 0, row = 0, column = 0", e.getMessage());
        }
        
        try {
            matrix1.swapStrings(-1, 0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 2, row = -1, column = 0", e.getMessage());
        }
        
        try {
            matrix1.swapStrings(0, 5);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 2, row = 5, column = 0", e.getMessage());
        }
    
        try {
            matrix2.swapStrings(0, 1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(MatrixErrorCode.CANNOT_SWAP_STRINGS, e.getMessage());
        }
    }
    
    
    @Test
    void testEquals() {
        IMatrix matrix1 = new DiagMatrix(1);
        IMatrix matrix2 = new DiagMatrix(2);
        IMatrix matrix3 = new DiagMatrix(2);
        IMatrix matrix4 = new SquareMatrix(2);
        IMatrix matrix5 = matrix3;
        IMatrix matrix6 = new DiagMatrix(3);
        IMatrix matrix7 = new DiagMatrix(3);
        IMatrix matrix8 = new SquareMatrix(3);
        IMatrix matrix9 = new DiagMatrix(10);
        
        matrix3.setElem(0, 0, 1.2);
        matrix3.setElem(1, 1, -19);
        
        matrix4.setElem(0, 0, 1.2);
        
        matrix7.setElem(1, 1, 1);
        matrix7.setElem(2, 2, -0.0001);
        
        matrix8.setElem(1, 1, 1);
        matrix8.setElem(2, 2, -0.0001);
        
        assertAll(
                () -> assertNotEquals(matrix1, "0"),
                () -> assertEquals(matrix1, matrix1),
                () -> assertNotEquals(matrix1, matrix2),
                () -> assertNotEquals(matrix2, matrix3),
                () -> assertNotEquals(matrix3, matrix4),
                () -> assertEquals(matrix3, matrix5),
                () -> assertNotEquals(matrix6, matrix7),
                () -> assertNotEquals(matrix6, matrix8),
                () -> assertEquals(matrix7, matrix8),
                () -> assertNotEquals(matrix6, matrix9)
        );
    }
}
