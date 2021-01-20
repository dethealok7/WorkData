
package com.poc.restapi;

import java.math.BigDecimal;


/** @author alokdethe
 */
public class NumberUtilM {
    
    //private N n;
    
    enum e { BigDecimal, Double, Integer, String, Long }
   //BigDecimal, Double, Integer, String, Long
    
    public String getString(Number d){
       
        return d.toString();
    }
    
    public boolean isGreaterThan(Number n1, Number n2){
        
         Class<? extends Number> num1 = (Class) n1.getClass();
         Class<? extends Number> num2 = (Class) n2.getClass();
        
        try{
            if( num1.getSimpleName().matches(".*"+e.BigDecimal.toString()) && 
                    num2.getSimpleName().matches(".*"+e.BigDecimal.toString()) ){
               
                if(((BigDecimal) n1).compareTo( (BigDecimal) n2 ) == 1){
                    return true;
                }
                
           }
            else if( num1.getSimpleName().matches(".*"+e.Double.toString()) && 
                    num2.getSimpleName().matches(".*"+e.Double.toString()) ){
                
                if(((Double) n1).compareTo( (Double) n2 ) == 1){
                    return true;
                }
                
            }
            else if( num1.getSimpleName().matches(".*"+e.Integer.toString()) && 
                    num2.getSimpleName().matches(".*"+e.Integer.toString()) ){
                
                if(((Integer) n1).compareTo( (Integer) n2 ) == 1){
                    return true;
                }
            }
           
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return false;
        
    }
    
    public boolean isLowerThan(Number n1, Number n2){
        
        Class<? extends Number> num1 = (Class) n1.getClass();
         Class<? extends Number> num2 = (Class) n2.getClass();
        
        try{
            if( num1.getSimpleName().matches(".*"+e.BigDecimal.toString()) && 
                    num2.getSimpleName().matches(".*"+e.BigDecimal.toString()) ){
               
                if(((BigDecimal) n1).compareTo( (BigDecimal) n2 ) == -1){
                    return true;
                }
                
           }
            else if( num1.getSimpleName().matches(".*"+e.Double.toString()) && 
                    num2.getSimpleName().matches(".*"+e.Double.toString()) ){
                
                if(((Double) n1).compareTo( (Double) n2 ) == -1){
                    return true;
                }
                
            }
            else if( num1.getSimpleName().matches(".*"+e.Integer.toString()) && 
                    num2.getSimpleName().matches(".*"+e.Integer.toString()) ){
                
                if(((Integer) n1).compareTo( (Integer) n2 ) == -1){
                    return true;
                }
            }
           
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public boolean isEqualTo(Number n1, Number n2){
        
         Class<? extends Number> num1 = (Class) n1.getClass();
         Class<? extends Number> num2 = (Class) n2.getClass();
        
        try{
            if( num1.getSimpleName().matches(".*"+e.BigDecimal.toString()) && 
                    num2.getSimpleName().matches(".*"+e.BigDecimal.toString()) ){
               
                if(((BigDecimal) n1).compareTo( (BigDecimal) n2 ) == 0){
                    return true;
                }
                
           }
            else if( num1.getSimpleName().matches(".*"+e.Double.toString()) && 
                    num2.getSimpleName().matches(".*"+e.Double.toString()) ){
                
                if(((Double) n1).compareTo( (Double) n2 ) == 0){
                    return true;
                }
                
            }
            else if( num1.getSimpleName().matches(".*"+e.Integer.toString()) && 
                    num2.getSimpleName().matches(".*"+e.Integer.toString()) ){
                
                if(((Integer) n1).compareTo( (Integer) n2 ) == 0){
                    return true;
                }
            }
           
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public BigDecimal getBigDecimal(Number d){
        
        //n = (N)d;
        
        Class<? extends Number> n1 = (Class) d.getClass();
        
        try{
            if( n1.getSimpleName().matches(".*"+e.BigDecimal.toString())){
               
               return  (BigDecimal) d;
           }
           else if( n1.getSimpleName().matches(".*"+e.Double.toString())){
               
               return BigDecimal.valueOf(d.doubleValue());
           }
           else if( n1.getSimpleName().matches(".*"+e.Integer.toString()) ){
               
               return BigDecimal.valueOf(d.intValue());
           }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null; 
    }
    
    public Double getDouble(Number d){

       //n = (N)d;     
       Class<? extends Number> n1 = (Class) d.getClass();
       
       try{
           
           if( n1.getSimpleName().matches(".*"+e.BigDecimal.toString())){
               
               return  ((BigDecimal) d).doubleValue();
           }
           else if( n1.getSimpleName().matches(".*"+e.Double.toString())){
               
               return ((Double) d).doubleValue();
           }
           else if( n1.getSimpleName().matches(".*"+e.Integer.toString()) ){
               
               return ((Integer)d).doubleValue();
           }
           
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
       return null;
    }
   
 
    
}

