// Camilo Gomez
// COP 3502 - Computer Science 2
import java.util.*;

public class calc {
int add, mul, div;

    // create object for each new value
    public calc(int add, int mul, int div){
        this.add = add;
        this.mul = mul;
        this.div = div;
    }
    // perform Breadth First Search 
    static int bfs(int add, int mul, int div, int target) { 
        int[] output = new int[3];
        int i = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        int[] d = new int[1000000];
        calc newNode = new calc(add, mul, div);
        // this will be used to check if we've been here before
        Arrays.fill(d, 1, d.length, -1);
        // enqueue queue with 0 first
        queue.offer(0);
        while (!queue.isEmpty()) 
        {
            // dequeues the top element of queue
            int v = queue.poll();

            if(d[target] != -1)
            {
                return d[target];
            }
            // updates the value and mods by 1000000 to keep first six digits
            output[0] = (v + newNode.add) % 1000000;
            output[1] = (v * newNode.mul) % 1000000;
            output[2] = (v / newNode.div);    

            for (i = 0; i < output.length; i++) 
            {
                if(d[output[i]] == -1)
                {
                    d[output[i]] = d[v] + 1;
                    queue.offer(output[i]);
                } 
            }
        }
        return d[i];
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int add, mul, div, target, presses = 0;
        for (int i = 0; i < n; i++) {
       
            add = scanner.nextInt();
            mul = scanner.nextInt();
            div = scanner.nextInt();
            target = scanner.nextInt();

            presses = bfs(add, mul, div, target);
            System.out.println(presses);
        }
    }
}
