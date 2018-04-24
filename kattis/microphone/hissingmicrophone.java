import java.util.*;
public class hissingmicrophone{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        String word = scanner.next();
        int temp = 0;
        for(int i = 0; i < word.length() - 1; i++)
        {
            if(word.charAt(i+1) == 's' && word.charAt(i) == 's')
            {
                System.out.print("hiss");
                temp = 1;
                break;
            }
        }
        if(temp == 0)
        {
            System.out.print("no hiss");
        }
    }
}