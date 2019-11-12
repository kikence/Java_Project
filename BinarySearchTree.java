//package progettoasd;

import java.util.ArrayList;


public class BinarySearchTree {


	/* a class that represents a node */

	class Node {

		char key; 
		Node left; 
		Node right; 
		int depth; 
		int occurrences; 
		int id; 

		
		public Node(char key) {

			this.key = key;
			left = null;
			right = null;
			this.occurrences = 1;

		}

		public String toString() {

			return "" + key;

		}

	} //class


	/* a class that represents a pair of nodes */

	class PairOfNodes {

		Node node1; 
		Node node2; 

		
		public PairOfNodes(Node n1,Node n2) {

			this.node1 = n1;
			this.node2 = n2;

		}

	} // class


	private Node root; 
	private static int idNode;  
	private ArrayList<Node> leaves; 
	private ArrayList<PairOfNodes> Edges; 
	private int minOccurs; 
	private int maxOccurs; 
	

	/* constructor */

	public BinarySearchTree(ArrayList<Character> toAdd) {
		
		this.root = null;
		this.idNode = 0;
		this.minOccurs = Integer.MAX_VALUE;
		this.maxOccurs = 1;
		this.leaves = new ArrayList<Node>();
		this.Edges = new ArrayList<PairOfNodes>();

		for (int i=0; i < toAdd.size(); i++)
			this.insert(toAdd.get(i),0);

	}

	public void insert(char id, int depth) {

		Node newNode = new Node(id);
		int depthToInsert = depth;
		int occurencesToInsert = 1;

		if (root == null) {

			root = newNode;
			newNode.depth = depthToInsert;
			newNode.occurrences = occurencesToInsert;
			return;

		}

		Node current = root;
		Node parent = null;

		while (true) {

			parent = current;

			if (id < current.key) {

				current = current.left;
				depthToInsert++;

				if (current == null) {

					parent.left = newNode;
					newNode.depth = depthToInsert;
					newNode.occurrences = occurencesToInsert;
					return;

				}

			} else if (id > current.key) {

				current = current.right;
				depthToInsert++;

				if (current == null) {

					parent.right = newNode;
					newNode.depth = depthToInsert;
					newNode.occurrences = occurencesToInsert;
					return;

				}

			} else {

				current.occurrences++;
				occurencesToInsert++;
				current = current.left;
				depthToInsert++;

				if (current == null) {

					parent.left = newNode;
					newNode.depth = depthToInsert;
					newNode.occurrences = occurencesToInsert;
					return;

				}
			}
		}
	}


	/* method that prints the new graph */

	public String toString() {

		return "Graph G {" + '\n' + vertices(root) + Edges() + "}";

	}


	/* method that prints the nodes */

	private String vertices(Node start) {

		String result = "";

		if ( isLeaf(start) )
			result = nextNodeName(start) + " [label=\"" + start.key + "\"];" + '\n';

		if ( start.left != null )
			result += vertices(start.left);

		if ( start.right != null )
			result += vertices(start.right);

		return result;

	}


	/* method that prints the edges */
		
	private String Edges() {

		String result = "";

		for (PairOfNodes element : this.Edges )
			result += "n" + element.node1.id + " -- " + "n" + element.node2.id + ";" + '\n';

		return result;

	}


	/* method that assigns a unique name to all the nodes */

	private String nextNodeName(Node n) {

		n.id = this.idNode;
		this.idNode++;
		return "n" + n.id;

	}


	/* method that checks if a node is a leaf */

	private boolean isLeaf(Node node) {

		return (node.left == null && node.right == null);

	}


	
	/* method lowest common ancestor */

	private Node lca(Node node, Node n1, Node n2) {  
		
	  	if (node == null) 
		  return null;
		  
	  	if (node.key >= n1.key && node.key >= n2.key) {

		  return lca(node.left, n1, n2);

	  	}
	  
	  	if (node.key < n1.key && node.key < n2.key) {

		  return lca(node.right, n1, n2);

	  	}
	  
		return node;
	  
	}
		
	
	

	/* method that saves the vertices in the array
 	*  AND calculates the minimum and maximum number of occurrences  */

	 private void readLeaves(Node start) {

		 if ( start.occurrences < this.minOccurs )
		 	this.minOccurs = start.occurrences;

		 else if ( start.occurrences > this.maxOccurs )
			this.maxOccurs = start.occurrences;

		 if ( isLeaf(start) )
		 	this.leaves.add(start);

		 if ( start.left!=null )
		 	readLeaves(start.left);

		 if ( start.right!=null )
 		 	readLeaves(start.right);
	 }


	/* method that calculates the number of the nodes and edges of the new graph 
 	* AND saves the connected nodes */

	 public int[] newGraph() {   

		 if (this.root == null) {

			 System.out.println('\n' + "|V|=" + "0" + ", |E|=" + "0");
			 System.out.println("Graph G {" + '\n' + "}");
			 System.exit(0);

		 }

		 readLeaves(this.root);                                          
		 int Edges = 0;

		 for (int i=0; i<this.leaves.size(); i++) {
                                  
			 for (int j=i+1; j<this.leaves.size(); j++) {                            

				 int sumdepth = this.leaves.get(i).depth + this.leaves.get(j).depth;
				 int nroArchi = sumdepth - 2 * lca(this.root,this.leaves.get(i),this.leaves.get(j)).depth;    

				 if ( nroArchi >= this.minOccurs && nroArchi <= this.maxOccurs )   
					this.Edges.add( new PairOfNodes(this.leaves.get(i),this.leaves.get(j)) );
				 
				}

		 }

		 int[] result;
		 result = new int[] {this.leaves.size(),this.Edges.size()};
		 return result;
	 }
	 
	 

} // class

