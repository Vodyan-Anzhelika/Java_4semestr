package model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import error.ErrorMessage;
import service.json_deserializer.FlatJsonDeserializer;
import service.json_serializer.FlatJsonSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@JsonSerialize(using = FlatJsonSerializer.class)
@JsonDeserialize(using = FlatJsonDeserializer.class)
public class Flat implements Serializable {
    private static final long serialVersionUID = 2L;
    public static final double EPS = 1E-6;
    private int number;
    private double area;
    private List<Person> owners;

    
    
    public Flat(int number, double area, List<Person> owners) {
        setNumber(number);
        setArea(area);
        setOwners(owners);
    }
    
    
    public void setNumber(int number) {
        if (number <= 0)
            throw new IllegalArgumentException(ErrorMessage.NON_POSITIVE_FLAT_NUMBER);
        
        this.number = number;
    }
    
    
    public void setArea(double area) {
        if (Math.abs(area) < EPS)
            throw new IllegalArgumentException(ErrorMessage.NON_POSITIVE_FLAT_AREA);
        
        this.area = area;
    }
    
    
    public void setOwners(List<Person> owners) {
        if (owners == null)
            this.owners = new ArrayList<>();
        
        else
            this.owners = owners;
    }
    
    
    public int getNumber() {
        return number;
    }
    
    
    public double getArea() {
        return area;
    }
    
    
    public List<Person> getOwners() {
        return owners;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return number == flat.number &&
                Double.compare(flat.area, area) == 0 &&
                Objects.equals(owners, flat.owners);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(number, area, owners);
    }
}
