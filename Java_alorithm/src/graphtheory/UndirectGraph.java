package graphtheory;
import java.util.*;

public class UndirectGraph {
	int vsize;
	int esize;//number of vertex and edge
	Vertex[] vertexlist;
	HashMap<Character, Vertex> vertexmap;
	HashMap<Vertex, Integer> vertexindexmap;
	//vertex class
	private static class Vertex{
		char vertex;
		EdgeNode firstedge;
		boolean isvisted;
	}
	//edge node
	private class EdgeNode{
		char adjvex;//set to link with vertex
		EdgeNode next;
	}
	/*
	 * initialize a graph
	 * @param vexs
	 * @oaram edges
	 */
	public UndirectGraph(char[] vexs, char[][] edges) {
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
		//System.out.println(vsize);
		//System.out.println(vertexlist.length);
		vertexmap = new HashMap<>();
		vertexindexmap = new HashMap<>();
		int i = 0;
		for(Vertex ve: vertexlist) {
			vertexindexmap.put(ve, i++);
			vertexmap.put(ve.vertex, ve);
		}
		
	}
	
	
	private int searchvertex(char ch) {
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
	public void dfs(char c) {
		boolean[] marked = new boolean[vsize];
		Vertex v = vertexmap.get(c);
		dfs(v, marked);
	}
	public void dfs(Vertex v, boolean[] marked) {
		marked[vertexindexmap.get(v)] = true;
		System.out.println(v.vertex);
		EdgeNode cursor = v.firstedge;
		while(cursor != null) {
			char tempc = cursor.adjvex;
			Vertex tempv = vertexmap.get(tempc);
			if(!marked[vertexindexmap.get(tempv)]) {
				dfs(tempv, marked);
			}
			cursor = cursor.next;
		}
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
		char[] vexs = {
	            'A', 'B', 'C', 'D'
	        };
		char[][] edges = new char[][] {
            {
                'A', 'B'
            }, {
                'A', 'C'
            }, {
                'A', 'D'
            }, {
                'B', 'D'
            }, {
                'C', 'D'
            }
        };
        UndirectGraph udg = new UndirectGraph(vexs, edges);
        
        System.out.println(udg);
        udg.dfs('A');
	}

}
