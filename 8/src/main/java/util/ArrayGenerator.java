package util;


import iface.Executable;


public class ArrayGenerator implements Executable {
    private int[] array;
    private int size;
    
    
    public ArrayGenerator(int size) {
        setSize(size);
    }
    
    
    public void setSize(int size) {
        this.size = Math.max(0, size);
    }
    
    
    public int[] getArray() {
        return array;
    }
    
    
    public int getSize() {
        return size;
    }
    
    
    @Override
    public void execute() {
        array = new int[size];
    }
}
