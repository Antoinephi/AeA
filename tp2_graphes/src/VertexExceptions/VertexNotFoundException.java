package VertexExceptions;


public class VertexNotFoundException extends Exception{
	
	public VertexNotFoundException(){
		System.err.println("Vertex not found in the graph");
	}

}
