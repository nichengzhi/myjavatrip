package graphtheory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class UndirectGraph {
    int vsize;
    int esize;//number of vertex and edge
    Vertex[] vertexlist;
    int[] id; // for sparate vertex into clusters
    int count;//number of groups
    //vertex class
    private static class Vertex{
        int vertex;
        EdgeNode firstedge;
        boolean isvisted;
    }
    //edge node
    private class EdgeNode{
        int adjvex;//set to link with vertex
        EdgeNode next;
    }
    /*
     * initialize a graph
     * @param vexs
     * @oaram edges
     */
    public UndirectGraph(int[] vexs, int[][] edges) {
        vsize = vexs.length;
        esize = edges.length;

        vertexlist = new Vertex[vsize];//a list of vertex
        for(int i = 0; i < vsize; i++) {
            vertexlist[i] = new Vertex();
            vertexlist[i].vertex = vexs[i];
            vertexlist[i].firstedge = null;
        }
        for(int i = 0; i < esize; i ++) {//every record like 1-2 2-1
            EdgeNode edgenode1 = new EdgeNode();
            EdgeNode edgenode2 = new EdgeNode();
            int pos1 = searchvertex(edges[i][0]);
            int pos2 = searchvertex(edges[i][1]);
            //use head insert, every time insert edge at the top

            edgenode1.adjvex = edges[i][1];
            edgenode1.next = vertexlist[pos1].firstedge;
            vertexlist[pos1].firstedge = edgenode1;

            edgenode2.adjvex = edges[i][0];
            edgenode2.next = vertexlist[pos2].firstedge;
            vertexlist[pos2].firstedge = edgenode2;
        }


    }


    private int searchvertex(int ch) {
        for(int i = 0; i < vsize; i ++) {
            if(vertexlist[i].vertex == ch) {
                //char can use ==
                return i;
            }
        }
        return -1;
    }
    //depth first search
    //generate a hashmap for value -> index
    public void dfs(int c) {
        boolean[] marked = new boolean[vsize];
        Vertex v = vertexlist[c];
        dfs(v, marked);
    }
    public void dfs(Vertex v, boolean[] marked) {
        marked[v.vertex] = true;
        System.out.println(v.vertex);
        EdgeNode cursor = v.firstedge;
        while(cursor != null) {
            int tempc = cursor.adjvex;
            Vertex tempv = vertexlist[tempc];
            if(!marked[tempc]) {
                dfs(tempv, marked);
            }
            cursor = cursor.next;
        }
    }
    //use dfs find a path
    //use a edge to record the path point
    public void dfs_findpath(int c) {//c is the start point
        boolean[] marked = new boolean[vsize];
        int[] edgeTo = new int[vsize];
        Vertex v = vertexlist[c];
        dfs_findpath(v, marked,edgeTo);
        for(Vertex end: vertexlist){
            int end_int = end.vertex;
            if(end_int != c){
                if(marked[end_int] == true){
                    Stack<Integer> path = new Stack<>();
                    StringBuilder sb = new StringBuilder();
                    sb.append(c).append(" to ").append(end_int).append("path: ").append(c);
                    for(int i = end_int; i != c; i = edgeTo[i]){
                        path.push(i);
                    }
                    while(!path.empty()) {
                        sb.append("->").append(path.pop());

                    }
                    System.out.println(sb.toString());

                }
            }
        }
    }
    public void dfs_findpath(Vertex v, boolean[] marked, int[] edgeTo) {
        marked[v.vertex] = true;

        EdgeNode cursor = v.firstedge;
        while(cursor != null) {
            int tempc = cursor.adjvex;
            Vertex tempv = vertexlist[tempc];
            if(!marked[tempc]) {
                edgeTo[tempc] = v.vertex;
                dfs_findpath(tempv, marked, edgeTo);
            }
            cursor = cursor.next;
        }
    }
    // find all the connected vertex by using dfs
    // use a int[] id to record the cluster id
    public void connected_vertex(){
        boolean[] marked = new boolean[vsize];
        id = new int[vsize];
        count = 0;//use dfs find connected vertex, record their cluster id by count
        for(Vertex point: vertexlist){
            if(marked[point.vertex] == false){
                cv_dfs(point, marked, count);
                count ++;
            }
        }

    }
    public void cv_dfs(Vertex point, boolean[] marked, int count){//same dfs only need record id
        marked[point.vertex] = true;
        id[point.vertex] = count;
        EdgeNode cursor = point.firstedge;
        while(cursor != null){
            int cursor_name = cursor.adjvex;
            if(!marked[cursor_name]){
                cv_dfs(vertexlist[cursor_name], marked, count);
            }
            cursor = cursor.next;
        }
    }
    //breadth first search
    public void bfs(int c){
        LinkedList<Vertex> visitedlist = new LinkedList<>();

        Vertex v = vertexlist[c];
        visitedlist.add(v);

        v.isvisted = true;

        while(!visitedlist.isEmpty()){
            Vertex topv = visitedlist.pop();
            System.out.println(topv.vertex);
            EdgeNode cursor = topv.firstedge;
            while(cursor != null){
                Integer tempc = cursor.adjvex;
                Vertex tempv = vertexlist[tempc];
                if(tempv.isvisted == false){
                    tempv.isvisted = true;
                    visitedlist.add(tempv);
                }
                cursor = cursor.next;
            }

        }

    }
    // to find two points is connected, just need check their id if same
    public boolean is_connected(int i, int j){
        return id[i] == id[j];
    }
    public boolean graph_connected(){
        return count == 0;
    }
    //find shortest path by BFS
    //add a edgeTo to record points relationship

    public void bfs_findpath(int start){
        LinkedList<Vertex> visitedlist = new LinkedList<>();
        int[] edgeTo = new int[vsize];
        Vertex v = vertexlist[start];
        visitedlist.add(v);

        v.isvisted = true;

        while(!visitedlist.isEmpty()){
            Vertex topv = visitedlist.pop();

            EdgeNode cursor = topv.firstedge;
            while(cursor != null){
                Integer tempc = cursor.adjvex;
                Vertex tempv = vertexlist[tempc];
                if(tempv.isvisted == false){
                    tempv.isvisted = true;
                    edgeTo[tempc] = topv.vertex;
                    visitedlist.add(tempv);
                }
                cursor = cursor.next;
            }

        }
        //System.out.println(Arrays.toString(edgeTo));
        Stack<Integer> path = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(Vertex end: vertexlist){


            int end_point = end.vertex;
            if(end_point != start && is_connected(start, end_point)) {
                sb.append(start).append(" to ").append(end_point).append(": ").append(start);

                for (int i = end_point; i != start; i = edgeTo[i]) {
                    path.push(i);
                }

                while (!path.isEmpty()) {
                    sb.append("->").append(path.pop());
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < vsize; i ++) {

            sb.append(vertexlist[i].vertex);
            sb.append("-->");
            EdgeNode cursor = vertexlist[i].firstedge;
            while(cursor != null) {
                sb.append(cursor.adjvex);
                cursor = cursor.next;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] vexs = {
                0, 1, 2, 3, 4, 5,6,7
        };
        int[][] edges = new int[][] {
                {
                        0, 5
                }, {
                2, 4
        }, {
                2, 3
        }, {
                1, 2
        }, {
                0, 1
        }, {
                    3, 4
        }, {
                    3, 5
        }, {
                    0, 2
        }, {
                    6, 7
        }
        };
        UndirectGraph udg = new UndirectGraph(vexs, edges);

        System.out.println(udg);
        //udg.dfs(0);
        udg.dfs_findpath(0);
        //udg.bfs(1);
        udg.connected_vertex();
        udg.bfs_findpath(0);

        System.out.println(Arrays.toString(udg.id));
        
    }

}
