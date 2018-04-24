// Camilo Gomez
// ca506812
import java.util.*;

public class bigtruck {
    static ArrayList<Edge>[] items;

    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        int numlocation = scanner.nextInt();
        items = new ArrayList[numlocation];
        int[] numItems = new int[numlocation];
        for (int i = 0; i < numlocation; i++) 
        {
            numItems[i] = scanner.nextInt();
            items[i] = new ArrayList<Edge>();
        }
        int numRoads = scanner.nextInt();
        for (int i = 0; i < numRoads; i++) 
        {
            int a = scanner.nextInt() - 1;
            int b = scanner.nextInt() - 1;
            long d = scanner.nextInt();
            items[a].add(new Edge(b, d * 1000000 - numItems[b]));
            items[b].add(new Edge(a, d * 1000000 - numItems[a]));
        }
        // Use dijstra's algorithm to find solution
        State path = dijkstras(0, numlocation - 1, numItems[0]);
        if (path == null) 
        {
            System.out.println("impossible");
        } 
        else 
        {
            System.out.println(((path.distance.dist - 1) / 1000000 + 1) + " " + (1000000 - path.distance.dist % 1000000));
        }
    }

    public static class Edge {
        int end;
        long dist;

        public Edge(int _end, long _dist) {
            end = _end;
            dist = _dist;
        }
    }

    // Use modified dijkstra's algorithm to find shortest path
    static class State implements Comparable<State> {
        final DistanceType distance; // cost of path + tiebreakers
        final int id;
        // any other information about it
        // e.g. location, other information

        public State(DistanceType dist, int _id) {
            this.distance = dist;
            this.id = _id;
        }

        @Override
        public int compareTo(State path) {
            // delegate to distance
            return distance.compareTo(path.distance);
        }

        @Override
        public int hashCode() {
            // use as much information as possible
            // do not include dist. must obey contract
            // that a == b implies a.hashCode() = b.hashCode()
            return id;
        }

        @Override
        public boolean equals(Object o) {
            State s = (State) o;
            // compare additional information.
            // do not include distance: two states are
            // the same regardless of dist.
            return id == id;
        }

        
         // Produce this state's successors.
         
        List<State> adj() {
            List<State> adj = new ArrayList<State>();
            // compute successor states based on this state's
            // information. The successor states may depend
            // on internal information that compress information
            // about the route taken from the start vertex.
            // each successor must be supplied with an
            // appropriate distance
            for(Edge e: items[id])
            {
                State temp = new State(new DistanceType(e.dist+distance.dist), e.end);
                adj.add(temp);
            }
            return adj;
        }
    }

    
    //   A type to represent the distance from start. In this simplest case, an
    //   int. If the problem asks for a given way of breaking ties, use a class
    //   and implement an approriate tie breaker
    static class DistanceType implements Comparable<DistanceType> {
        final long dist;

        DistanceType(long dist) {
            this.dist = dist;
        }

        public int compareTo(DistanceType a) {
            // first order comparator is distance
            return Long.compare(dist, a.dist);
        }
    }

    static State dijkstras(int st, int e, int initialHelp) {
        // uses compare() for sorting by distance
        PriorityQueue<State> pq = new PriorityQueue<State>();

        // uses hashCode() + equals() to find if equivalent state was already
        // found
        HashMap<State, DistanceType> distance = new HashMap<State, DistanceType>();

        State start = new State(new DistanceType(initialHelp * -1), st);
        pq.offer(start);
        distance.put(start, new DistanceType(initialHelp * -1));
        while (!pq.isEmpty()) 
        {
            State current = pq.poll();

            if (current.id == e) 
            { // check if target is reached
                return current;
            }

            for (State adj : current.adj()) 
            {
                DistanceType bestSoFar = distance.get(adj);
                if (bestSoFar == null || adj.distance.compareTo(bestSoFar) < 0) 
                {
                    pq.offer(adj);
                    distance.put(adj, adj.distance);
                }
            }
        }
        // no path to target
        return null;
    }
}
