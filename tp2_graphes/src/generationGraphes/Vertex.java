package generationGraphes;

public class Vertex {
	private int number;
	private Vertex pere;
	private int part;
	
	public Vertex(int number){
		this.number = number;
		this.part = -1;
	}
	
	public void setPart(int part){
		this.part = part;
	}
	
	public int getPart(){
		return this.part;
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
