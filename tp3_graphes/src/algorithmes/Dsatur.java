package algorithmes;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;
import graphe.Generation;
import graphe.GraphImpl;
import graphe.Vertex;

public class Dsatur {
	private GraphImpl graphe;
	private int[] degrees; 
	private int[] dsatur;

	public Dsatur(GraphImpl graphe){
		this.graphe = graphe;
		this.degrees = initDegrees();
		this.dsatur = initDsatur();
	}
	
	public int getMaxColor(){
		int max = 0;
		for (Vertex v : graphe.getVertex()) {
			if (v.getColor() > max)
				max = v.getColor();
		}
		return max;
	}
	
	public LinkedList<Integer> getMax(int[] tab){
		LinkedList<Integer> indMax = new LinkedList<Integer>();
		int max = tab[0];
		indMax.add(0);
		for(int i=1;i<tab.length;i++){
			if (tab[i] > max){
				indMax.clear();
				max = tab[i];
				indMax.add(i);
			} else if(tab[i] == max){
				indMax.add(i);
			}
		}
		return indMax;
	}
	
	public int[] initDsatur(){
		int[] dsatur = new int[this.graphe.getVertex().size()];
		for(int i=0;i<dsatur.length;i++){
			dsatur[i] = this.graphe.getVertexDegree(this.graphe.getVertex().get(i));
		}
		return dsatur;
	}
	
	public int[] initDegrees(){
		int[] degrees = new int[this.graphe.getVertex().size()];
		for (int i=0;i<this.graphe.getVertex().size();i++) {
			degrees[i] = this.graphe.getVertexDegree(this.graphe.getVertex(i));
		}
		return degrees;
	}
	
	public int[] getDegrees(){
		return this.degrees;
	}
	
	public GraphImpl getGraphe(){
		return this.graphe;
	}

	public int getMinColor(int number){
		LinkedList<Integer> alreadyUsedColor = new LinkedList<Integer>();
		LinkedList<Vertex> neighboors = this.graphe.getVertexNeighboors(this.graphe.getVertex().get(number));
		for (Vertex vertex : neighboors) {
			if((vertex.getColor() !=-1) && (!alreadyUsedColor.contains(vertex.getColor()))){
				alreadyUsedColor.add(vertex.getColor());
			}
		}
		int min = 0;
		while(alreadyUsedColor.contains(min)){
			min++;
		}
		return min;
	}
	
	public void dsaturToString(){
		for(int i=0;i<this.dsatur.length;i++){
			System.out.println(i + " " + this.dsatur[i]);
		}
	}
	

	public void algoDSATUR(){
		int max = this.getMax(this.degrees).get(0);
		this.graphe.getVertex().get(max).setColor(0);
		LinkedList<Vertex> neighboors = this.graphe.getVertexNeighboors(this.graphe.getVertex().get(max));
		this.dsatur[max] = -1;
		for (Vertex vertex : neighboors) {
			this.dsatur[vertex.getNumber()-1] = 1;
		}
		for(int i=1;i<this.degrees.length;i++){
			LinkedList<Integer> maxDsatur = this.getMax(dsatur);
			int num;
			if(maxDsatur.size() == 1){
				num = maxDsatur.get(0);
			} else {
				num = getMax(maxDsatur);
			}
				this.graphe.getVertex().get(num).setColor(this.getMinColor(num));
				this.dsatur[num] = -1;
				//this.dsaturToString();
				neighboors = this.graphe.getVertexNeighboors(this.graphe.getVertex().get(num));
				for (Vertex vertex : neighboors) {
					if((vertex.getColor() == -1) && (this.dsatur[vertex.getNumber()-1] < this.graphe.getVertexDegree(vertex))){
						this.dsatur[vertex.getNumber()-1]++;
					}
				}
			}
		}
	
	private int getMax(LinkedList<Integer> list) {
		int max = list.get(0);
		int degreeMax = this.graphe.getVertexDegree(this.graphe.getVertex(list.get(0)));
		for (Integer number : list) {
			if(this.graphe.getVertexDegree(this.graphe.getVertex(number))>degreeMax){
				max = number;
				degreeMax = this.graphe.getVertexDegree(this.graphe.getVertex(number));
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws VertexNotFoundException, VertexAlreadyExistException {
		Generation g = new Generation();
		GraphImpl graphe = g.generateValueGraph(1000, 0.5);
		//graphe.affiche();
		System.out.println("graphe généré");
		long tempsDebut = System.currentTimeMillis();
		Dsatur algo = new Dsatur(graphe);
		long tempsFin = System.currentTimeMillis();
		float seconds = (tempsFin - tempsDebut) / 1000F;
		System.out.println("Dsatur effectué en :"
				+ Float.toString(seconds) + " secondes.");
		algo.algoDSATUR();
		//algo.getGraphe().affiche();
		System.out.println(algo.getMaxColor());
	}
}
