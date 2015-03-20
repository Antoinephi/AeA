package generationGraphes;

public class Vertex {
	private int number;
	
	public Vertex(int number){
		this.number = number;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public boolean equals(Object o){
		return this.getNumber() == ((Vertex) o).getNumber();
	}
}
