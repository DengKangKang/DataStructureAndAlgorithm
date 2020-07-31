package graph;

public class GraphByAdjacencyMatrix {

    private int[][] adjacencyMatrix;
    private int v;


    GraphByAdjacencyMatrix(int v) {
        this.v = v;
        adjacencyMatrix = new int[v][v];
    }


    public void addEdge(int s, int t) {
        if (s >= v || t >= v) return;
        adjacencyMatrix[s][t]= 1;
        adjacencyMatrix[t][s]= 1;

    }

    public void addEdgeByDirection(int s, int t) {
        if (s >= v || t >= v) return;
        adjacencyMatrix[s][t]= 1;
    }

    public void addEdgeByWidget(int s, int t,int widget) {
        if (s >= v || t >= v) return;
        adjacencyMatrix[s][t]= widget;
        adjacencyMatrix[t][s]= widget;
    }

    public void addEdgeByWidgetAndDirection(int s, int t,int widget) {
        if (s >= v || t >= v) return;
        adjacencyMatrix[s][t]= widget;
    }

}
