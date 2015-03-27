package graphe;

public class Edge {
	private Vertex start;
	private Vertex end;
	private int value;

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

}
