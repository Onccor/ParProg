import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
 
 
public class Count {
public static void main(String[] args) throws FileNotFoundException {
    String s = "";
    Scanner in = new Scanner(new File("C:\\Users\\ryzhov\\eclipse-workspace\\Lab1\\src\\testfile.txt"));
    while(in.hasNext())
    s += in.nextLine() + "\n";
    // ------------------------------------
                String[] a = s.split("\\s"); //!!!!
                Arrays.sort(a);
                for (int i = 0; i < a.length - 1; i++) {
                    for (int j = 0; j < a.length - 1; j++) {
                        if (a[j].length() > a[j + 1].length()) {
                            String b = a[j];
                            a[j] = a[j + 1];
                            a[j + 1] = b;
                        }
                    }
                }
                // ------------------------------------
                int[] num = new int[a.length];
                for (int i = 0; i < num.length; i++) {
                    num[i] = 0;
                }
                for (int i = 0; i < a.length; i++) {
                    if (a[i] != "") {
                        num[i] = 1;
                        for (int j = 0; j < a.length; j++) {
                            if ((a[i].equals(a[j])) & (i != j)) {
                                a[j] = "";
                                num[i]++;
                            }
                        }
                    }
                }
                for (int i = 0; i < a.length; i++) {
                    if (a[i] != "") {
                        System.out.println(a[i] + " = " + num[i]);
                    }
                }   
    in.close();
    }
}