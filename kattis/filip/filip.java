import java.util.*;

public class filip{

    public static void compare(int first, int second){
        int[] firback = new int[3];
        int[] secback = new int[3];
        int idcanymore = 0;
        for(int i = 2; i >= 0; i--)
        {
            firback[i] = first%10;
            first = first / 10;
            
            secback[i] = second%10;
            second = second / 10;

            if(firback[i] > secback[i])
            {
                idcanymore = 1;
            }
        }
        if(idcanymore == 1)
        {
            for(int i = 2; i >= 0; i--)
            System.out.print(firback[i]);

        }
        if(idcanymore == 0)
        for(int i = 2; i >= 0; i--)
        System.out.print(secback[i]);
    }

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        int first = scanner.nextInt();
        int second = scanner.nextInt();

        compare(first, second);
    }
}