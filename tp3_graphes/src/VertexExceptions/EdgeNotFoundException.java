package VertexExceptions;

public class EdgeNotFoundException extends Exception{
	public EdgeNotFoundException(){
		System.err.println("Edge not found");
	}
}
