package socialnetwork.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
    private final int V; // No. of vertices in graph.

    private final LinkedList<Integer>[] adj; // Adjacency List
    // representation

    ArrayList<ArrayList<Integer>> components = new ArrayList<>();

    @SuppressWarnings("unchecked") public Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList();
    }

    public void addEdge(int u, int w)
    {
        adj[u].add(w);
        adj[w].add(u); // Undirected Graph.
    }

    void DFSUtil(int v, boolean[] visited,
                 ArrayList<Integer> al)
    {
        visited[v] = true;
        al.add(v);

        for (int n : adj[v]) {
            if (!visited[n])
                DFSUtil(n, visited, al);
        }
    }

    public void DFS()
    {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            ArrayList<Integer> al = new ArrayList<>();
            if (!visited[i]) {
                DFSUtil(i, visited, al);
                components.add(al);
            }
        }
    }

    public int ConnecetedComponents() { return components.size(); }
}
