package matrixes;


public interface IMatrix {
    void setElem(int row, int column, double value);
    double getElem(int row, int column);
    
    double getDeterminant();
}
