# MooVUpTest

# Mobile Developer Programming Test



## Question 1

Use the following undirected graph ­- nodes can be visited only once:

![List](https://github.com/moovup/programming-test/blob/master/assets/web/graph.png)

- a. Write a function that returns all the possible paths between A­-H.
- b. Write a function that returns the least number of hops (shortest path) between A­-H.



```
import java.util.ArrayList;
import java.util.List;
public class MoovUpTest {
    public static void main(String[] args) {

        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(0, 7);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        graph.addEdge(2, 3);
        graph.addEdge(2, 5);

        graph.addEdge(3, 4);

        graph.addEdge(4, 5);
        graph.addEdge(4, 7);

        graph.addEdge(5, 6);

        graph.addEdge(6, 7);

        // arbitrary source
        int start = 0;

        // arbitrary destination
        int destination = 7;


        System.out.println("Following are all possible paths from " + graph.getCharForNumber(start) + " to " + graph.getCharForNumber(destination));
        graph.printAllPaths(start, destination);

    }

}

class Graph {

    // No. of vertices in graph
    private final int vert;

    String getCharForNumber(int i) {
        return String.valueOf((char) (i + 65));
    }

    // adjacency list
    private ArrayList<Integer>[] adjList;

    int smallestPath = Integer.MAX_VALUE;
    List<Integer> shortestPathList = new ArrayList<Integer>();

    // Constructor
    public Graph(int vertices) {

        // initialise vertex count
        this.vert = vertices;

        // initialise adjacency list
        initAdjList();
    }

    /*
     * utility method to initialise adjacency list
     */
    private void initAdjList() {
        adjList = new ArrayList[vert];

        for (int i = 0; i < vert; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // add edge from u to vert
    public void addEdge(int u, int v) {
        // Add vert to u's list.
        adjList[u].add(v);
        adjList[v].add(u);
    }

    // Prints all paths from
    // 's' to 'd'
    public void printAllPaths(int s, int d) {
        boolean[] isVisited = new boolean[vert];
        ArrayList<String> pathList = new ArrayList<>();

        // add source to path[]
        pathList.add(getCharForNumber(s));

        // Call recursive utility
        printAllPathsUtil(s, d, isVisited, pathList);
        System.out.println("Shortest path:");
        System.out.println(shortestPathList);
    }

    /*
     * A recursive function to print all paths from 'u' to 'd'. isVisited[] keeps
     * track of vertices in current path. localPathList<> stores actual vertices in
     * the current path
     */
    private void printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, ArrayList<String> localPathList) {

        if (u.equals(d)) {

            System.out.println(localPathList);
            if (localPathList.size() < smallestPath) {
                smallestPath = localPathList.size();
                shortestPathList = (ArrayList) localPathList.clone();
            }
            return;
        }

        // Mark the current node
        isVisited[u] = true;

        // Recur for all the vertices adjacent to current vertex
        for (Integer i : adjList[u]) {
            if (!isVisited[i]) {
                // store current node in path[]
                localPathList.add(getCharForNumber(i));
                printAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node in path[]
                localPathList.remove(getCharForNumber(i));
            }
        }

        // Mark the current node
        isVisited[u] = false;
    }
}

```

```
Output:
[A, B, C, D, E, F, G, H]
[A, B, C, D, E, H]
[A, B, C, F, E, H]
[A, B, C, F, G, H]
[A, B, D, C, F, E, H]
[A, B, D, C, F, G, H]
[A, B, D, E, F, G, H]
[A, B, D, E, H]
[A, D, B, C, F, E, H]
[A, D, B, C, F, G, H]
[A, D, C, F, E, H]
[A, D, C, F, G, H]
[A, D, E, F, G, H]
[A, D, E, H]
[A, H]
Shortest path:
[A, H]
```

## Question 2
### API Specification

**Get list of people**
  * Method
    `GET`
  * Endpoint
    `https://api.json-generator.com/templates/Xp8zvwDP14dJ/data`
  * API Key (Bearer Token): `v3srs6i1veetv3b2dolta9shrmttl72vnfzm220z` (Don't worry. It is a public key. If it doesn't works, do let us know)

Android App Screenshots:

![alt text](https://user-images.githubusercontent.com/32219192/143590327-a8883eef-c1aa-48f9-9347-7452ab3131a9.jpeg?raw=true)
![alt text](https://user-images.githubusercontent.com/32219192/143590324-5622c9ae-6a96-4229-b09f-0fc9f1c3dcfa.jpeg?raw=true)
![alt text](https://user-images.githubusercontent.com/32219192/143590315-f2c18c97-b26b-4db9-8367-f468eaad3744.jpeg?raw=true)

https://user-images.githubusercontent.com/32219192/143590329-12469cdb-f933-4ae1-b829-0311b3056776.mp4
