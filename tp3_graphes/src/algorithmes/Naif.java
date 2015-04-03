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
		GraphImpl graphe = g.generateValueGraph(5, 0.5);
		Naif algo = new Naif(graphe);
		algo.algoNaif();
		algo.getGraphe().affiche();
	}
}
