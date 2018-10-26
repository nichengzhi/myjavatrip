/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recurisve;
import java.io.File;
import java.util.Scanner;
/**
 *
 * @author TEMP
 */
public class Getfilesize {
    public static void main(String args[]){
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();
        File file = new File(directory);
        System.out.println(file.length());
    }
}
