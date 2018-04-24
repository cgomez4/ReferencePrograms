import java.util.*;
public class tarifa{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int income = scanner.nextInt();
        int temp = income;
        int N = scanner.nextInt();
        int megabytes;
        for(int i = 0; i < N; i++)
        {
            megabytes = scanner.nextInt();
            income = income - megabytes;
            income += temp;
        }
        System.out.print(income);
    }
}