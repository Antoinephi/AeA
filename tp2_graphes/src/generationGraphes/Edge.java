package generationGraphes;

public class Edge {
	private Vertex start;
	private Vertex end;
	private int value;

	public Edge(Vertex start, Vertex end, int value) {
		this.start = start;
		this.end = end;
		this.value = value;
	}

	public Edge(Vertex start, Vertex end) {
		this.start = start;
		this.end = end;
		this.value = 0;
	}
	
	public Vertex getStart() {
		return this.start;
	}

	public Vertex getEnd() {
		return this.end;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
	
	public boolean equals(Object o){
		return (this.start.equals(((Edge) o).getStart()) && this.end
				.equals(((Edge) o).getEnd()) && this.value == ((Edge) o).getValue())
				|| (this.start.equals(((Edge) o).getEnd()) && this.end
						.equals(((Edge) o).getStart()) && this.value == ((Edge) o).getValue()); 
	}
	
	public String toString(){
		return this.start.toString() + " " + this.end.toString() + " : " + this.value;
	}

}
