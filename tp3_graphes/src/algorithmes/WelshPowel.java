package algorithmes;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;
import graphe.Generation;
import graphe.GraphImpl;
import graphe.Vertex;

public class WelshPowel {
	private GraphImpl graphe;
	private int[] degreesDec; 

	public WelshPowel(GraphImpl graphe){
		this.graphe = graphe;
		this.degreesDec = initDegrees();
	}
	
	public int getMaxColor(){
		int max = 0;
		for (Vertex v : graphe.getVertex()) {
			if (v.getColor() > max)
				max = v.getColor();
		}
		return max;
	}
	
	public int[] initDegrees(){
		int[] degrees = new int[this.graphe.getVertex().size()];
		int[] degreesDec = new int[this.graphe.getVertex().size()];
		for (int i=0;i<this.graphe.getVertex().size();i++) {
			degrees[i] = this.graphe.getVertexDegree(this.graphe.getVertex(i));
		}
		for(int j=0;j<degreesDec.length;j++){
			int max = degrees[0];
			int maxInd = 0;
			for(int k=1;k<degrees.length;k++){
				if(max < degrees[k]){
					max = degrees[k];
					maxInd = k;
				}
			}
			degrees[maxInd] = -1;
			degreesDec[j] = maxInd;
		
		}
		return degreesDec;
	}
	
	public int[] getDegreesDec(){
		return this.degreesDec;
	}
	
	public GraphImpl getGraphe(){
		return this.graphe;
	}


	public void algoWelshPowel(){
		int colorMax = 0;
		for(int i=0;i<this.degreesDec.length;i++){
			if(this.graphe.getVertex(this.degreesDec[i]).getColor() == -1){
				this.graphe.getVertex(this.degreesDec[i]).setColor(colorMax);
				LinkedList<Vertex> neighboors = this.graphe.getVertexNeighboors(this.graphe.getVertex(this.degreesDec[i]));
				for (Vertex vertex : this.graphe.getVertex()) {
					if((!neighboors.contains(vertex)) && (vertex.getColor() ==-1))
						vertex.setColor(colorMax);
					neighboors.addAll(this.graphe.getVertexNeighboors(vertex));
				}
				colorMax++;
			}
		}
	}
	
	public static void main(String[] args) throws VertexNotFoundException, VertexAlreadyExistException {
		Generation g = new Generation();
		GraphImpl graphe = g.generateValueGraph(50, 0.5);
		WelshPowel algo = new WelshPowel(graphe);
		for(int i=0;i<algo.getDegreesDec().length;i++){
			System.out.println(graphe.getVertex(algo.getDegreesDec()[i]).getNumber() + " " + graphe.getVertexDegree(graphe.getVertex(algo.getDegreesDec()[i])));
		}
		algo.algoWelshPowel();
		algo.getGraphe().affiche();
		System.out.println(algo.getMaxColor());
	}
}
