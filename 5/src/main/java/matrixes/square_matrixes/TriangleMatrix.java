package matrixes.square_matrixes;


import matrixes.MatrixErrorCode;


public abstract class TriangleMatrix extends SquareMatrix {
    public TriangleMatrix(int dimension) {
        super();
        
        if (dimension < 0)
            throw new IllegalArgumentException(
                    String.format(MatrixErrorCode.NEGATIVE_DIMENSION, dimension));
        
        this.dimension = dimension;
    }
    
    
    @Override
    public abstract void setElem(int row, int column, double value);
    
    
    @Override
    public abstract double getElem(int row, int column);
    
    
    @Override
    public double getDeterminant() {
        if (dimension == 0)
            throw new IllegalArgumentException(MatrixErrorCode.ZERO_DIMENSION);
        
        if (determinantCalculated)
            return determinant;
        
        determinant = 1;
        
        for (int i = 0; i < dimension; i++)
            determinant *= getElem(i, i);
        
        determinantCalculated = true;
        return determinant;
    }
    
    
    @Override
    public abstract void swapStrings(int i, int j);
}
