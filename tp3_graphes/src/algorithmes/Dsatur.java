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
	
	public LinkedList<Integer> getMax(){
		LinkedList<Integer> indMax = new LinkedList<Integer>();
		int max = dsatur[0];
		indMax.add(0);
		for(int i=1;i<dsatur.length;i++){
			if (dsatur[i] > max){
				indMax.clear();
				max = dsatur[i];
				indMax.add(i);
			} else if(dsatur[i] == max){
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
			if(vertex.getColor() !=1 && !alreadyUsedColor.contains(vertex.getColor())){
				alreadyUsedColor.add(vertex.getColor());
			}
		}
		int min = 0;
		while(alreadyUsedColor.contains(min)){
			System.out.println(min);
			min++;
		}
		return min;
	}
	
	public void dsaturToString(){
		System.out.println("début dsatur");
		for(int i=0;i<this.dsatur.length;i++){
			System.out.println(i + " " + this.dsatur[i]);
		}
		System.out.println("fin dsatur");
	}
	

	public void algoDSATUR(){
		System.out.println("Etape 1");
		this.dsaturToString();
		this.graphe.getVertex().get(this.getMaxDegree()).setColor(0);
		System.out.println(this.degrees[0]);
		System.out.println(this.graphe.getVertex().get(this.degrees[0]).getNumber() + " a la couleur 0");
		LinkedList<Vertex> neighboors = this.graphe.getVertexNeighboors(this.graphe.getVertex().get(this.degrees[0]));
		this.dsatur[this.degrees[0]] = -1;
		this.dsaturToString();
		for (Vertex vertex : neighboors) {
			this.dsatur[vertex.getNumber()-1] = 1;
		}
		System.out.println("Etape 2");
		for(int i=1;i<this.degrees.length;i++){
			System.out.println("Etape 3");
			LinkedList<Integer> maxDsatur = this.getMax();
			System.out.println("Etape 4");
			int num;
			if(maxDsatur.size() == 1){
				System.out.println("here");
				num = maxDsatur.get(0);
			} else {
				num = getMaxDegree(maxDsatur);
			}
				this.graphe.getVertex().get(num).setColor(this.getMinColor(num));
				System.out.println(this.graphe.getVertex().get(num).getNumber() + " a la couleur : "+this.getMinColor(num));
				this.dsatur[num] = -1;
				this.dsaturToString();
				System.out.println(num);
				neighboors = this.graphe.getVertexNeighboors(this.graphe.getVertex().get(num));
				for (Vertex vertex : neighboors) {
					if((vertex.getColor() == -1) && (this.dsatur[vertex.getNumber()-1] < this.graphe.getVertexDegree(vertex))){
						this.dsatur[vertex.getNumber()-1]++;
					}
				}
			}
		}
	
	private int getMaxDsatur(LinkedList<Integer> maxDsatur) {
		int max = maxDsatur.get(0);
		int degreeMax = this.graphe.getVertexDegree(this.graphe.getVertex(maxDsatur.get(0)));
		for (Integer number : maxDsatur) {
			if(this.graphe.getVertexDegree(this.graphe.getVertex(number))>degreeMax){
				max = number;
				degreeMax = this.graphe.getVertexDegree(this.graphe.getVertex(number));
			}
		}
		return max;
	}
	
	private int getMaxDegree(LinkedList<Integer> maxDsatur){
		int max = 0;
		int degreeMax = this.degrees[maxDsatur.get(0)];
		for (Integer i : maxDsatur) {
			if(this.degrees[i] > degreeMax){
				degreeMax = this.degrees[i];
				max = i;
			}
		}
		return max;
	}
	
	private int getMaxDegree(){
		LinkedList<Integer> vertex = new LinkedList<Integer>();
		for(int i=0;i<this.graphe.getVertex().size();i++){
			vertex.add(i);
		}
		return getMaxDegree(vertex);
	}
	
	public static void main(String[] args) throws VertexNotFoundException, VertexAlreadyExistException {
		Generation g = new Generation();
		GraphImpl graphe = g.generateValueGraph(5, 0.5);
		Dsatur algo = new Dsatur(graphe);
		algo.algoDSATUR();
		algo.getGraphe().affiche();
		System.out.println("fin");
	}
}
