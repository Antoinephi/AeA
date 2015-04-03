package generationGraphes;

public class Vertex {
	private int number;
	private Vertex pere;
	
	public Vertex(int number){
		this.number = number;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public boolean equals(Object o){
		return this.getNumber() == ((Vertex) o).getNumber();
	}

	public Vertex getPere() {
		return pere;
	}

	public void setPere(Vertex pere) {
		this.pere = pere;
	}
	
	public String toString(){
		return this.number +"";
	}
}
