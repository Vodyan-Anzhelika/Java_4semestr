package square_matrixes;


import matrixes.IMatrix;
import matrixes.MatrixErrorCode;
import matrixes.square_matrixes.DiagMatrix;
import matrixes.square_matrixes.SquareMatrix;
import matrixes.square_matrixes.UpTriangleMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestUpTriangleMatrix {
    @Test
    void testConstr() {
        UpTriangleMatrix matrix = new UpTriangleMatrix(2);
        
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
            IMatrix matrix = new UpTriangleMatrix(-5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Matrix dimension cannot be negative, but was -5", e.getMessage());
        }
    }
    
    
    @Test
    void testElem() {
        IMatrix matrix = new UpTriangleMatrix(3);
        
        matrix.setElem(0, 0, 3);
        matrix.setElem(0, 1, 0);
        matrix.setElem(0, 2, -1);
        matrix.setElem(1, 1, -2.91);
        matrix.setElem(1, 2, 223);
        matrix.setElem(2, 0, 0);
        matrix.setElem(2, 2, 234011);
        
        assertAll(
                () -> assertEquals(3, matrix.getElem(0, 0), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(0, 1), DiagMatrix.EPS),
                () -> assertEquals(-1, matrix.getElem(0, 2), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(1, 0), DiagMatrix.EPS),
                () -> assertEquals(-2.91, matrix.getElem(1, 1), DiagMatrix.EPS),
                () -> assertEquals(223, matrix.getElem(1, 2), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(2, 0), DiagMatrix.EPS),
                () -> assertEquals(0, matrix.getElem(2, 1), DiagMatrix.EPS),
                () -> assertEquals(234011, matrix.getElem(2, 2), DiagMatrix.EPS)
        );
    }
    
    
    @Test
    void testSetElemExceptions() {
        IMatrix matrix1 = new UpTriangleMatrix(4);
        IMatrix matrix2 = new UpTriangleMatrix(13);
        
        try {
            matrix1.setElem(-2, 1, 2);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 4, row = -2, column = 1", e.getMessage());
        }
        
        try {
            matrix2.setElem(1, -1, 4);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 13, row = 1, column = -1", e.getMessage());
        }
        
        try {
            matrix2.setElem(13, 13, 8);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 13, row = 13, column = 13", e.getMessage());
        }
        
        try {
            matrix1.setElem(1, 6, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 4, row = 1, column = 6", e.getMessage());
        }
        
        try {
            matrix2.setElem(2, 1, 1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Incorrect indexes: dimension = 13, row = 2, column = 1", e.getMessage());
        }
    }
    
    
    @Test
    void testGetElemExceptions() {
        IMatrix matrix1 = new UpTriangleMatrix(3);
        IMatrix matrix2 = new UpTriangleMatrix(16);
        
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
            double determinant = new UpTriangleMatrix(0).getDeterminant();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(MatrixErrorCode.ZERO_DIMENSION, e.getMessage());
        }
    }
    
    
    @Test
    void testGetDeterminant1() {
        IMatrix matrix = new UpTriangleMatrix(1);
        
        assertEquals(0, matrix.getDeterminant(), UpTriangleMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant2() {
        IMatrix matrix = new UpTriangleMatrix(1);
        
        matrix.setElem(0, 0, -8);
        
        assertEquals(-8, matrix.getDeterminant(), UpTriangleMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant3() {
        IMatrix matrix = new UpTriangleMatrix(2);
        
        matrix.setElem(0, 1, 5);
        
        assertEquals(0, matrix.getDeterminant(), UpTriangleMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant4() {
        IMatrix matrix = new UpTriangleMatrix(2);
        
        matrix.setElem(0, 0, -2); matrix.setElem(0, 1, -1.9);
        matrix.setElem(1, 0, 0);  matrix.setElem(1, 1, 5);
        
        assertEquals(-10, matrix.getDeterminant(), UpTriangleMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant5() {
        IMatrix matrix = new UpTriangleMatrix(3);
        
        matrix.setElem(0, 0, -1.5); matrix.setElem(0, 1, 1234); matrix.setElem(0, 2, -0.28);
                                    matrix.setElem(1, 1, 6);    matrix.setElem(1, 2, 6);
                                                                matrix.setElem(2, 2, 1);
        
        assertEquals(-9, matrix.getDeterminant(), UpTriangleMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant6() {
        IMatrix matrix = new UpTriangleMatrix(5);
        
        matrix.setElem(0, 0, -60);
        matrix.setElem(1, 1, 0.125);
        matrix.setElem(2, 2, 3);
        matrix.setElem(3, 3, 700);
        matrix.setElem(4, 4, -0.0002);
        
        assertEquals(3.15, matrix.getDeterminant(), UpTriangleMatrix.EPS);
    }
    
    
    @Test
    void testGetDeterminant7() {
        IMatrix matrix = new UpTriangleMatrix(26);
        
        for (int i = 0; i < 26; i++)
            for (int j = i; j < 26; j++)
                matrix.setElem(i, j, Math.pow(-1, i) * (i % 2 + 1));
        
        assertAll(
                () -> assertEquals(-8192, matrix.getDeterminant(), UpTriangleMatrix.EPS),
                () -> assertEquals(-8192, matrix.getDeterminant(), UpTriangleMatrix.EPS)
        );
    }
    
    
    @Test
    void testSwapStrings() {
        UpTriangleMatrix actual = new UpTriangleMatrix(3);
        UpTriangleMatrix expected = new UpTriangleMatrix(3);
        
        actual.setElem(0, 0, 2);  actual.setElem(0, 1, 0);  actual.setElem(0, 2, 1);
                                                            actual.setElem(1, 2, 8);
                                                            actual.setElem(2, 2, 1);
        
        expected.setElem(0, 0, 2);  expected.setElem(0, 1, 0);  expected.setElem(0, 2, 1);
                                                                expected.setElem(1, 2, 1);
                                                                expected.setElem(2, 2, 8);
        
        actual.swapStrings(1, 2);
        
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertEquals(0, actual.getDeterminant(), UpTriangleMatrix.EPS)
        );
    }
    
    
    @Test
    void testSwapStringsExceptions() {
        UpTriangleMatrix matrix0 = new UpTriangleMatrix(0);
        UpTriangleMatrix matrix1 = new UpTriangleMatrix(2);
        UpTriangleMatrix matrix2 = new UpTriangleMatrix(2);
        
        matrix2.setElem(0, 0, 1);
        matrix2.setElem(1, 1, 1);
        
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
        IMatrix matrix1 = new UpTriangleMatrix(1);
        IMatrix matrix2 = new UpTriangleMatrix(2);
        IMatrix matrix3 = new DiagMatrix(2);
        IMatrix matrix4 = new SquareMatrix(2);
        IMatrix matrix5 = matrix2;
        IMatrix matrix6 = new UpTriangleMatrix(3);
        IMatrix matrix7 = new UpTriangleMatrix(3);
        IMatrix matrix8 = new SquareMatrix(3);
        IMatrix matrix9 = new SquareMatrix(3);
        
        matrix2.setElem(0, 0, 335);
        matrix2.setElem(1, 1, -0.1);
        
        matrix3.setElem(0, 0, 335);
        matrix3.setElem(1, 1, -0.1);
    
        matrix4.setElem(0, 0, 335);
        matrix4.setElem(1, 1, -0.1);
        
        matrix6.setElem(1, 1, 1);
        matrix6.setElem(1, 2, -5);
        matrix6.setElem(2, 2, -1);
    
        matrix7.setElem(1, 1, 1);
        matrix7.setElem(2, 2, -1);
    
        matrix8.setElem(1, 1, 1);
        matrix8.setElem(1, 2, -5);
        matrix8.setElem(2, 2, -1);
    
        matrix9.setElem(1, 1, 1);
        matrix9.setElem(1, 2, -5);
        matrix9.setElem(2, 2, 11);
        
        assertAll(
                () -> assertNotEquals(matrix1, "0"),
                () -> assertEquals(matrix1, matrix1),
                () -> assertNotEquals(matrix1, matrix2),
                () -> assertEquals(matrix2, matrix3),
                () -> assertEquals(matrix2, matrix4),
                () -> assertEquals(matrix2, matrix5),
                () -> assertNotEquals(matrix6, matrix7),
                () -> assertEquals(matrix6, matrix8),
                () -> assertNotEquals(matrix6, matrix9)
        );
    }
}
