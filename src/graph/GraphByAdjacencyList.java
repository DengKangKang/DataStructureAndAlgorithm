package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("all")
public class GraphByAdjacencyList {


    public static void main(String[] args) {
        GraphByAdjacencyList graph = new GraphByAdjacencyList(8);
        graph.addEdge(0, 3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 6);
        graph.dfs(0, 7);

    }

    private LinkedList<Vertex>[] adjacencyList;
    private int v;


    GraphByAdjacencyList(int v) {
        this.v = v;
        adjacencyList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjacencyList[i] = new LinkedList<Vertex>();
        }
    }


    public void addEdge(int s, int t) {
        if (s >= v || t >= v) return;
        adjacencyList[s].push(new Vertex(t));
        adjacencyList[t].push(new Vertex(s));
    }

    public void addEdgeByDirection(int s, int t) {
        if (s >= v || t >= v) return;
        adjacencyList[s].push(new Vertex(t));

    }

    public void addEdgeByWidget(int s, int t, int widget) {
        if (s >= v || t >= v) return;
        adjacencyList[s].push(new Vertex(t, widget));
        adjacencyList[t].push(new Vertex(s, widget));
    }

    public void addEdgeByWidgetAndDirection(int s, int t, int widget) {
        if (s >= v || t >= v) return;
        adjacencyList[s].push(new Vertex(t, widget));
    }


    class Vertex {
        int v;
        int widget;


        public Vertex(int v) {
            this.v = v;
        }

        public Vertex(int v, int widget) {
            this.v = v;
            this.widget = widget;
        }
    }


    public void bfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        while (!queue.isEmpty()) {
            int w = queue.poll();
            LinkedList<Vertex> vertices = adjacencyList[w];
            for (int i = 0; i < vertices.size(); i++) {
                int next = vertices.get(i).v;
                if (!visited[next]) {
                    prev[next] = w;
                    if (next == t) {
                        print(prev, s, t);
                    }
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }

    public void print(int[] prev, int s, int t) {
        if (t != s && prev[t] != -1) {
            print(prev, s, prev[t]);
        }
        System.out.println(t);
    }

    public void dfs(int s, int t) {
        boolean[] visited = new boolean[v];
        visited[s] = true;
        int[] prev = new int[v];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        recur(prev, visited, s, t);
        print(prev, s, t);

    }

    boolean found = false;

    public void recur(int[] pre, boolean[] visited, int w, int t) {
        if (found) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        LinkedList<Vertex> vertices = adjacencyList[w];
        for (Vertex vertex : vertices) {
            if (!visited[vertex.v]) {
                pre[vertex.v] = w;
                recur(pre, visited, vertex.v, t);
            }
        }

    }


    public void kahn() {
        int[] vDegrees = new int[v];
        for (int i = 0; i < v; i++) {
            LinkedList<Vertex> vertices = adjacencyList[v];
            vertices.forEach(v -> {
                vDegrees[v.v]++;
            });
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vDegrees.length; i++) {
            if (vDegrees[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int w = queue.remove();
            System.out.println("->" + w);
            LinkedList<Vertex> vertices = adjacencyList[w];
            vertices.forEach(v -> {
                vDegrees[v.v]--;
                if (vDegrees[v.v] == 0) queue.add(v.v);

            });
        }
    }


    public void topoSortByDfs() {
        LinkedList<Vertex>[] inversAdj = new LinkedList[v];
        for (int i = 0; i < adjacencyList.length; i++) {
            inversAdj[i] = new LinkedList();
        }
        for (int i = 0; i < adjacencyList.length; i++) {
            for (int j = 0; j < adjacencyList[i].size(); j++) {
                Vertex vertex = adjacencyList[i].get(j);
                inversAdj[vertex.v].push(new Vertex(i));
            }
        }
        boolean[] visited = new boolean[v];
        for (int i = 0; i < adjacencyList.length; i++) {
            if(visited[i] == false){
                visited[i] = true;
                dfs(i, inversAdj, visited);
            }
        }
    }

    public void dfs(int vertex, LinkedList<Vertex>[] inversAdj, boolean[] visited) {
        LinkedList<Vertex> vertexs = inversAdj[vertex];
        for (int i = 0; i < vertexs.size(); i++) {
            if (visited[vertexs.get(i).v] == true) continue;
            visited[vertexs.get(i).v] = true;
            dfs(vertexs.get(i).v, inversAdj, visited);
        }
        System.out.println("->" + v);

    }

}
