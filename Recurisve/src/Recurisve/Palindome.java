/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recurisve;

/**
 *
 * @author TEMP
 */
public class Palindome {
    
    public static boolean palindome(String a){
        return palindome(a, 0, a.length() -1);
    }
    public static boolean palindome(String a, int low, int high){
        if(low >= high){
            return true;
        }
        else if(a.charAt(high) != a.charAt(low)){
            return false;
        }
        else{
            return palindome(a,low+1,high-1);
        }
    }
    
}
