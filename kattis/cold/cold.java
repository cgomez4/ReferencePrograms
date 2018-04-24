import java.util.*;
public class cold{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int days = scanner.nextInt();
        int count = 0;
        for(int i = 0; i < days; i++)
        {
            int temp = scanner.nextInt();
            if(temp < 0)
            {
                count++;
            }
        }
        System.out.print(count);
    }
}