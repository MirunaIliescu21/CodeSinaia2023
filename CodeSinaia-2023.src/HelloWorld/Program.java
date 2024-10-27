package HelloWorld;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World to Code Sinaia 2023!");

        // int[][] arr = new int[7][4];
        // int value = 1;
        // for (int r = 0; r < arr.length; r++) {
        //     for (int c = 0; c < arr[r].length; c++) {
        //         arr[r][c] = value++;
        //     }
        // }

        // System.out.println(arr);

        // File f = new File("xyz");
        // //System.out.println(f.getCanonicalPath());
        // System.out.println(f.getCanonicalPath());

        Scanner console = new Scanner(System.in);
        System.out.print("Give me a number> ");
        int x = console.nextInt();
        System.out.println(x);
        
    }
}