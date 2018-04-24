import java.util.*;
public class batterup{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int atbats = scanner.nextInt();
        double temp = atbats;
        double total = 0;
        for(int i = 0; i < atbats; i++)
        {
            double n = scanner.nextInt();
            if(n != -1)
                total += n;
            if(n == -1)
                temp = temp - 1;
        }
        total = total / temp;
        System.out.print(total);
    }
}