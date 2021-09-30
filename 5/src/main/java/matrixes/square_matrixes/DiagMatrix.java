package matrixes.square_matrixes;


import matrixes.MatrixErrorCode;

import static java.lang.Math.abs;


public class DiagMatrix extends TriangleMatrix {
    public DiagMatrix(int dimension) {
        super(dimension);
        
        data = new double[dimension];
    }
    
    
    @Override
    public void setElem(int row, int column, double value) {
        checkIndexes(row, column);
        
        if (abs(value) > EPS) {
            if (row != column)
                throw new ArrayIndexOutOfBoundsException(
                        String.format(MatrixErrorCode.INCORRECT_INDEXES, dimension, row, column));
            
            data[row] = value;
            determinantCalculated = false;
        }
    }
    
    
    @Override
    public double getElem(int row, int column) {
        checkIndexes(row, column);
        
        if (row != column)
            return 0;
        
        return data[row];
    }
    
    
    @Override
    public void swapStrings(int i, int j) {
        checkIndexes(i, 0);
        checkIndexes(j, 0);
        
        if (i != j && (getElem(i, i) != 0 || getElem(j, j) != 0))
            throw new IllegalArgumentException(MatrixErrorCode.CANNOT_SWAP_STRINGS);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        
        if (o instanceof DiagMatrix) {
            DiagMatrix that = (DiagMatrix) o;
            
            if (dimension != that.dimension)
                return false;
            
            for (int i = 0; i < data.length; i++)
                if (abs(data[i] - that.data[i]) > EPS)
                    return false;
        }
        
        return super.equals(o);
    }
}
