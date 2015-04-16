package graphe;

import VertexExceptions.VertexNotFoundException;

public interface GraphItf {
	public void addVertex();
	public void addEdge(Vertex v1, Vertex v2) throws VertexNotFoundException;
	public void addEdge(int i, int j) throws VertexNotFoundException;
	public Vertex getVertex(int i) throws VertexNotFoundException;
	
}
