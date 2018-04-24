import java.util.*;
public class quadrant{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        if(x > 0 && y > 0)
        System.out.print(1);
        if(x < 0 && y > 0)
        System.out.print(2);
        if(x < 0 && y < 0)
        System.out.print(3);
        if(x > 0 && y < 0)
        System.out.print(4);
    }
}