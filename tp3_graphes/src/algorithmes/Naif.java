package algorithmes;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;
import graphe.Generation;
import graphe.GraphImpl;
import graphe.Vertex;

public class Naif {
	
	private GraphImpl graphe;
	
	public Naif(GraphImpl graphe){
		this.graphe = graphe;
	}
	
	public int getMaxColor(){
		int max = 0;
		for (Vertex v : graphe.getVertex()) {
			if (v.getColor() > max)
				max = v.getColor();
		}
		return max;
	}
	
	public void algoNaif(){
		int min = -1;
		for(int i=0;i<this.graphe.getVertex().size();i++){
			int j = 0;
			LinkedList<Vertex> neighboors = this.graphe.getVertexNeighboors(this.graphe.getVertex(i));
			LinkedList<Integer> alreadyUsedColor = new LinkedList<Integer>();
			for (Vertex vertex : neighboors) {
				if((vertex.getColor() != -1) && (!alreadyUsedColor.contains(vertex.getColor()))){
					alreadyUsedColor.add(vertex.getColor());
				}
			}
			while(alreadyUsedColor.contains(j)){
				j++;
			}
			this.graphe.getVertex(i).setColor(j);
		}
		
	}
	
	public GraphImpl getGraphe(){
		return this.graphe; 
	}
	
	public static void main(String[] args) throws VertexNotFoundException, VertexAlreadyExistException {
		Generation g = new Generation();
		GraphImpl graphe = g.generateValueGraph(1000, 0.5);
		//graphe.affiche();
		System.out.println("graphe généré");
		long tempsDebut = System.currentTimeMillis();
		Naif algo = new Naif(graphe);
		long tempsFin = System.currentTimeMillis();
		algo.algoNaif();
		float seconds = (tempsFin - tempsDebut) / 1000F;
		System.out.println("Dsatur effectué en :"
				+ Float.toString(seconds) + " secondes.");
		System.out.println(algo.getMaxColor());
	}
}
