package graphtheory;

public class UndirectGraph {
	int vsize;
	int esize;//number of vertex and edge
	Vertex[] vertexlist;
	//vertex class
	private class Vertex{
		char vertex;
		EdgeNode firstedge;
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
	}

}
