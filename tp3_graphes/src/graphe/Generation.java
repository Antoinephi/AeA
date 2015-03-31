package graphe;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class Generation implements RandomGraphGenerator {

	
	public GraphImpl generateErdosRenyiGraph(int n, double p) throws VertexAlreadyExistException, VertexNotFoundException{
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
			for(int j=i;j<=n;j++){
				alea = Math.random();
				if(alea < p){
					graphe.addEdge(i, j);
				}
			}
		}
		return graphe;
	}
	
	public GraphImpl generateValueGraph(int n, double p) throws VertexNotFoundException, VertexAlreadyExistException{
		GraphImpl graphe = generateErdosRenyiGraph(n,p);
		for(int i=0;i<graphe.getEdges().size();i++){
			int aleaValue = (int) (Math.random()*(Math.pow(n, 4)-1));
			graphe.getEdges().get(i).setValue(aleaValue);
		}
		return graphe;
	}
	
	public static void main(String[] args) throws VertexNotFoundException, VertexAlreadyExistException {
		Generation generate = new Generation();
		GraphImpl graphe = generate.generateValueGraph(5,0.5);
		//graphe.affiche();
		System.out.println(graphe.graphToTxt());
		String txt = graphe.graphToTxt();
		GraphImpl graph = new GraphImpl();
		graph.txtToGraph(txt);
		graph.affiche();
	}
}
