import java.util.*;
public class planina{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int iterations = scanner.nextInt();
        int dot = 3;
        for(int i = 1; i< iterations; i++)
            dot += (dot-1);
        System.out.println(dot*dot);
    }
}