package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <fT, sT> {
    // TODO напишите реализацию
    private fT fValue;
    private sT sValue;
    private Pair(fT fValue, sT sValue){
        this.fValue = fValue;
        this.sValue = sValue;
    }
    public fT getFirst(){
        return fValue;
    }
    public sT getSecond(){
        return sValue;
    }
    public void setfValue(fT fValue){
        this.fValue = fValue;
    }
    public void setsValue(sT sValue){
        this.sValue = sValue;
    }
    public boolean equals(Object value){
        if (this == value){
            return true;
        }
        Pair<fT, sT> pair = (Pair<fT, sT>) value;
        if(this.fValue == pair.fValue && this.sValue == pair.sValue){
            return true;
        }else{
            return false;
        }
    }
    public int hashCode(){
        return Objects.hash(fValue, sValue);
    }
    public static <fT, sT> Pair<fT, sT> of(fT fV, sT sV){
         return new Pair<>(fV, sV);
    }
     public void ifPresent(BiConsumer biConsumer){
           if(this.fValue != null && this.sValue != null){
               biConsumer.accept(fValue, sValue);
           }
     }
}
