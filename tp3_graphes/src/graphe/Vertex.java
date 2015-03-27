package graphe;

public class Vertex {
	private int number;
	private int color;
	
	public Vertex(int number){
		this.number = number;
		this.color = -1;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public boolean equals(Object o){
		return this.getNumber() == ((Vertex) o).getNumber();
	}
	
	public int getColor(){
		return this.color;
	}
	
	public void setColor(int color){
		this.color = color;
	}
}
