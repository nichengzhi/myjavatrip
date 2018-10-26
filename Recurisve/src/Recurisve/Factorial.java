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
public class Factorial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.print(3/2);
        System.out.print(3.0/2);
        
    }
    public static long factorial(int n){
        if(n == 0 | n == 1){
            return 1;
        }
        else{
            return n*factorial(n-1);
        }
    }
    
}
