package matrixes.square_matrixes;


import matrixes.IMatrix;
import matrixes.MatrixErrorCode;

import java.util.Arrays;

import static java.lang.Math.abs;


public class SquareMatrix implements IMatrix {
    public static final double EPS = 1E-9;
    protected double[] data;
    protected int dimension;
    protected double determinant;
    protected boolean determinantCalculated;

    
    public SquareMatrix(int dimension) {
        if (dimension < 0)
            throw new IllegalArgumentException(
                    String.format(MatrixErrorCode.NEGATIVE_DIMENSION, dimension));
        
        this.dimension = dimension;
        data = new double[dimension * dimension];
    }
    
    
    private SquareMatrix(SquareMatrix matrix) {
        dimension = matrix.dimension;
        int squaredDimension = dimension * dimension;
        
        data = new double[squaredDimension];
        System.arraycopy(matrix.data, 0, data, 0, squaredDimension);
        determinant = matrix.determinant;
        determinantCalculated = matrix.determinantCalculated;
    }
    
    
    protected SquareMatrix() {
    }
    
    
    protected void checkIndexes(int row, int column) {
        if (row < 0 || column < 0 || row >= dimension || column >= dimension)
            throw new ArrayIndexOutOfBoundsException(
                    String.format(MatrixErrorCode.INCORRECT_INDEXES, dimension, row, column));
    }
    
    
    @Override
    public void setElem(int row, int column, double value) {
        checkIndexes(row, column);
        
        data[row * dimension + column] = value;
        determinantCalculated = false;
    }
    
    
    @Override
    public double getElem(int row, int column) {
        checkIndexes(row, column);
        
        return data[row * dimension + column];
    }
    
    
    public int getDimension() {
        return dimension;
    }
    
    
    @Override
    public double getDeterminant() {
        if (dimension == 0)
            throw new IllegalArgumentException(MatrixErrorCode.ZERO_DIMENSION);
        
        if (determinantCalculated)
            return determinant;

        SquareMatrix copy = new SquareMatrix(this);
        
        determinant = 1;
        
        for (int i = 0; i < dimension - 1; i++) {
            if (abs(copy.getElem(i, i)) < EPS)
                for (int k = i + 1; k < dimension; k++)
                    if (abs(copy.getElem(k, i)) > EPS) {
                        copy.swapStrings(i, k);
                        determinant *= -1;
                        break;
                    }
            
            if (abs(copy.getElem(i, i)) < EPS) {
                determinant = 0;
                determinantCalculated = true;
                return determinant;
            }
            
            determinant *= copy.getElem(i, i);
            
            for (int k = i + 1; k < dimension; k++) {
                double coef = copy.getElem(k, i) / copy.getElem(i, i);
                copy.setElem(k, i, 0);
                
                if (abs(coef) > EPS)
                    for (int j = i + 1; j < dimension; j++)
                        copy.setElem(k, j, copy.getElem(k, j) - coef * copy.getElem(i, j));
            }
        }
        
        determinant *= copy.getElem(dimension - 1, dimension - 1);
        
        determinantCalculated = true;
        return determinant;
    }
    
    
    public void swapStrings(int i, int j) {
        checkIndexes(i, 0);
        checkIndexes(j, 0);
        
        if (i != j) {
            for (int k = 0; k < dimension; k++) {
                double t = data[i * dimension + k];
                data[i * dimension + k] = data[j * dimension + k];
                data[j * dimension + k] = t;
            }
            
            determinant *= -1;
        }
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SquareMatrix)) return false;
        SquareMatrix that = (SquareMatrix) o;
        
        if (dimension != that.dimension)
            return false;
        
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                if (abs(getElem(i, j) - that.getElem(i, j)) > EPS)
                    return false;
        
        return true;
    }
    
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
