package algorithmes;

import java.util.LinkedList;

import graphe.GraphImpl;
import graphe.Vertex;

public class Naif {

	public void algoNaif(GraphImpl graphe){
		int min;
		for(int i=0;i<graphe.getVertex().size();i++){
			LinkedList<Vertex> neighboors = graphe.getVertexNeighboors(graphe.getVertex(i));
			LinkedList<Integer> alreadyUsedColor = new LinkedList<Integer>();
			for (Vertex vertex : neighboors) {
				if(vertex.getColor() != -1 && !neighboors.contains(vertex.getColor()))
					alreadyUsedColor.add(vertex.getColor());
			}
		}
		
	}
}
