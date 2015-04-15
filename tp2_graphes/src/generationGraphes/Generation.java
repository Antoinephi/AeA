package generationGraphes;

import java.util.Random;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class Generation implements RandomGraphGenerator {

	
	public GraphImpl generateErdosRenyiGraph(int n, double p) throws VertexAlreadyExistException, VertexNotFoundException{
		long startTime = System.currentTimeMillis();
		
		if(p<0 || p>1)
			throw new IllegalArgumentException();
		GraphImpl graphe = new GraphImpl();
		double alea;
		//On génère n sommets numérotés de 1 à n
		for(int i=1;i<=n;i++){
			graphe.addVertexNumber(i);
		}
		//Puis on ajoute les arètes selon la probabilité p
		for(int i=1;i<=n;i++){
			for(int j=i+1;j<=n;j++){
				//System.out.println(i*j + "/" + n*n);
				alea = Math.random();
				if(alea < p){
					graphe.addEdge(i, j);
				}
			}
		}
		System.out.println("total time : " + ((float)(System.currentTimeMillis() - startTime)) / 1000 + "sec");
		return graphe;
	}
	
	public GraphImpl generateValueGraph(int n, double p) throws VertexNotFoundException, VertexAlreadyExistException{
		GraphImpl graphe = generateErdosRenyiGraph(n,p);
		for(int i=0;i<graphe.getEdges().size();i++){
			int aleaValue = (int) (new Random().nextDouble()*(Math.pow(n, 2)-1)*Math.pow(10, 2)); //Changed Random calcul
			graphe.getEdges().get(i).setValue(aleaValue);
		}
		return graphe;
	}
	
	public static void main(String[] args) throws VertexNotFoundException, VertexAlreadyExistException {
		Generation generate = new Generation();
		GraphImpl graphe = generate.generateValueGraph(1000,1);
		graphe.affiche();
		System.out.println(graphe.graphToTxt());
		String txt = graphe.graphToTxt();
		GraphImpl graph = new GraphImpl();
		graph.txtToGraph(txt);
		graph.affiche();
	}
}
