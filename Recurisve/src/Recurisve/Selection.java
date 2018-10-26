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
//排序
public class Selection {
    public static void sort(double[] list){
        sort(list, 0, list.length -1);
    }
    
    public static void sort(double[] list, int low, int high){
        if(low < high){
            int indexofmin = low;
            double min = list[low];
            for(int i = low + 1; i <= high; i++){
                if(list[i]<min){
                    indexofmin = i;
                    min = list[i];
                    
                }
            }
            list[indexofmin] = list[low];
            list[low] = min;
            sort(list, low+1, high);
        }
        
        
    }  
    
}
