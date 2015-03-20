package algorithmes;

import java.util.LinkedList;

import generationGraphes.*;

public class Prim {
	private Graphe graphe;
	private LinkedList<Vertex> Q;
	private Graphe MST;
	private int[] key;

	public Prim(Graphe graphe) {
		this.graphe = graphe;
		this.Q = graphe.getVertex();
		init_key();
	}

	public void init_key() {
		int[] key = new int[graphe.getVertex().size()];
		for (int i = 0; i < key.length; i++) {
			key[i] = Integer.MAX_VALUE;
		}
		int alea = (int) (Math.random() * (this.key.length));
		key[alea] = 0;
	}
	
	public int extract_min(){
		return 0;
	}
	
	
	public void algo(){
		while(this.Q.size() > 0){
			int u = extract_min();
		}
	}
}
