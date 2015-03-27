package VertexExceptions;

public class VertexAlreadyExistException extends Exception {
	public VertexAlreadyExistException(){
		System.err.println("Vertex already exists");
	}
}
