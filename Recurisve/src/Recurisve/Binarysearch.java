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
import java.util.*;
public class Binarysearch {
    
    public static int binsearch(int[] list, int key){
        Arrays.sort(list);
        return binsearch(list,key,0,list.length -1);
    }
    public static int binsearch(int[] list, int key, int low, int high){
        if(low>high){
            return -low-1;
        }
        int mid = (low+high)/2;
        if(list[mid] > key){
            return binsearch(list,key,low,mid);
        }
        else if(list[mid] < key){
            return binsearch(list,key,mid,high);
        }
        else{
            return mid;
        }
    }
    
}
