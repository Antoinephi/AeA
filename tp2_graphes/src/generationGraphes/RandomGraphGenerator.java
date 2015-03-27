package generationGraphes;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public interface RandomGraphGenerator {
	GraphImpl generateErdosRenyiGraph(int n, double p)throws VertexAlreadyExistException, VertexNotFoundException;
}
