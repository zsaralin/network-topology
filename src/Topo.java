package topo;
/**
 * Topo - Determines whether an undirected graph is a Ring, Star, or Fully-connected Mesh (or none)
 * @author: Saralin Zassman
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Topo {

	//Adjacency Matrix for Graph
	private int[][] matrix;
	//Number of nodes in Graph
	private int n;

	/**
	 * Constructor for Topo
	 *
	 * @param filePath File path of the input
	 * @throws FileNotFoundException if the file path isn't valid
	 */
	public Topo(String filePath) throws FileNotFoundException {
		Scanner scFile = new Scanner(new File(filePath));

		// Read the number of nodes
		n = scFile.nextInt();

		//Set size of matrix
		matrix = new int[n][n];

		//Fill in matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = scFile.nextInt();
			}
		}
		scFile.close();
	}

	/*prints the Topology (if any) of the Graph*/
	public void getTopology() {
		if (testRing())
			System.out.println("Graph has Ring Topology");
		else if (testMesh())
			System.out.println("Graph has Fully-connected Mesh Topology");
		else if (testStar())
			System.out.println("Graph has Star Topology");
		else {
			//Graph doesn't match Ring, Fully-connected Mesh, or Star Topology
			System.out.println("Graph does not have Ring, Fully-connected Mesh, or Star Topology");
		}
	}

	/*Determine if Graph is Ring
    Every node is connected to exactly 2 other nodes so the sum of each row must equal 2*/
	public boolean testRing() {
		for (int i = 0; i < n; i++) {
			//Sum of row entries
			int sum = 0;
			//Scan through each row in Matrix
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 1)
					sum++;
			}
			if (sum != 2)
				return false;
		}
		//if sum == 2 for each row, then Graph is a Ring
		return true;
	}

	/*Determine if Graph is fully-connected mesh
    Matrix has 1's everywhere except for the diagonal (since a node doesn't connect to itself)*/
	public boolean testMesh() {
		//Only need to look at Upper or Lower Triangular part of Matrix (matrix is symmetrical)
		//Scan through each element in Upper Triangular part of Matrix
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (matrix[i][j] == 0 && i != j) //0 value that's not on the diagonal
					return false;
			}
		}
		return true;
	}

	/*Determine if Graph is Star
    Star matrix has: 1 row with (n-1) 1's (since middleNode doesn't connect to itself)
    and all other rows must contain a single 1 (sum of row entries = 1)*/
	public boolean testStar() {
		int middleNode = 0;
		int otherNode = 0;
		//Scan through each element in Matrix
		for (int i = 0; i < n; i++) {
			//Sum of row entries
			int sum = 0;
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 1)
					sum++;
			}
			if (sum == 1)
				otherNode++;
			else if (sum == n - 1) {
				middleNode++;
			}
		}
		//Star has 1 middle Node (middle of Star), and (n-1) other Nodes (nodes connected to Middle)
		if (middleNode == 1 && otherNode == n - 1) {
			return true;
		}
		return false;
	}

	/*Main method*/
	public static void main(String[] args) throws FileNotFoundException {
		Topo t = new Topo("input1.txt");
		t.getTopology();
	}

}