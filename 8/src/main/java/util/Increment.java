package util;


import iface.Executable;


public class Increment implements Executable {
    private int value;
    
    
    public void setValue(int value) {
        this.value = value;
    }
    
    
    public int getValue() {
        return value;
    }
    
    
    @Override
    public void execute() {
        value++;
    }
}
