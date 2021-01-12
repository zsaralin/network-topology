# network-topology
- Reads in an undirected graph with n nodes
- Prints out whether the graph is a Ring, Star, or Fully-connected Mesh (or none)

Structure of input text file:
- The first line contains a positive integer n, the number of nodes.
- The following n lines give the adjacency matrix for the graph. Each of these n
lines contains a binary string of length n consisting entirely of 0 and 1 characters,
where a 1 in row i, column j indicates an edge from node i to node j. There are no
self-loops in the graph, and since the graph is undirected, the adjacency matrix is
symmetric.

![Network Topology](networkTopo.png?raw=true "Network Topologies")


