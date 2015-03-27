package Tests;

import java.util.LinkedList;

import VertexExceptions.VertexNotFoundException;

import generationGraphes.GraphImpl;
import generationGraphes.Vertex;

public class Ex1 {
	
	
	public static void main(String[] args) throws VertexNotFoundException {
		GraphImpl graphe = new GraphImpl();
		for(int i=0;i<23;i++){
			graphe.addVertex();
		}
		graphe.addEdge(graphe.getVertex(0), graphe.getVertex(1), 1);		
		
	}

}
