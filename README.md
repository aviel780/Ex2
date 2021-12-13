## Ex2

# MyNode Class: Represents the vertices
  **Attributes:**
  * ID- The node's number
  * Location- Represented by 3 perameters
  * Weight- Represents the node's weight
  * Info- The node's information
  * Tag- Conatains the node's tag
 
 **The class contains the following methods:**
 * Costructor method which gets a Json file
 * Default constructor
 * Costructor which gets different perameters
 * Getters and setters methos for each attribut
 
# MyEdge Class: Represents the graph's edges
  **Attributes:**
  * Src- Represents the edge's sorce vertex
  * Dest- Represents the edge's destination vertex
  * Weight- Represents the edge's weight
  * Info- Contains information about the edge
  * Tag- The edge's tag
 
 **The class contains the following methods:**
 * Costructor method which gets a Json file
 * Default constructor
 * Costructor which gets different perameters
 * Getters and Setters for each attribute
    
# MyGeo Class: Represents the location
  **Attributes:**
  * x- width
  * y- length
  * z- hight, set as 0

  **The class contains the following methods:**
  * Costructor method which gets a Json file
  * Default constructor
  * Costructor which gets different perameters
  * Distance- Returns the distance between two 3D points
  * Getters and Setters for each attribute

# MyDirectedWeightedGraph Class: Represents the graph
  **Attributes:**
      * MC- The number of activities that were made on the graph
      * Nodes- A hashmap that contains all the vertices's info, the key's type is an integer and the value is NodeData
      * Edges- A hashmap which containd all the edges info, the key's type is a hashmap that contains an integer key that leads to another integer, the final value is EdgeData
      * SizeOfNodes- Represents the number of node the graph contains
      * SizeOfEdges- Represents the number of edges the graph contains
      * NodeIter- An iterator object for the Nodes list
      * EdgeIter- An iterator object for the Edges list
      * EdgeList- An arrayList that contains info about the edges
 
 **The class contains the following methods:**
 * Costructor method which gets a Json file
 * Default constructor
 * Costructor which gets different perameters
 * Getters and Setters for each attribute
 * AddNode- Adds a to the graph
 * AddEdge- Adds an edge to the graph
 * Connect- Checks if the edge is connected to the node, if not add an edge
 * RemoveNode- removes the vertex (node) from the list and does adaptaions if needed on the graph
 * RemoveEdge- removes the edge from the list and does adaptaions if needed on the graph
 * NodeIter- Returns the node's iterator accordong to the ID
 * EdgeIter- Returns the edge's iterator
 * NodeSize- Returns the number of nodes
 * EdgeSize- Returns the number of edges
# MyDirectedWeightedGraphAlgorithems: Represents the algo class
  **Attributes:**
  * Object of MyDirectedWeightedGraph type
  
  **The class contains the following methods:**
  * Costructor method which gets a Json file
  * Default constructor
  * Costructor which gets different perameters
  * Copy – Coping (deep copy) the graph
  * Init- Initializes the graph
  * IsConnected- Checks if the graph is connected by the BFS algorithm.
                 Which means, checks on the graph and on the transposed graph if there is a rout from a specific node on the graph to another node.
                 If so the graph is connected, returns true, otherwise, returns false
  * BFS- Scanning the graph by the BFS algorithem, scanning the connected edges only
  * ResetTag- Resets the tags in order to use in other methods
  * ResetInfo- Resets the information in order to use in other methods
  * ResetWeight- Resets the graph's weight in order to use in other methods
  * ShortestPathDist- Returns the weight of the shortest path from a given sorce to a given destination, using the Dijkstra algorithem
                      For more information check in: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
  * ShortestPath- Findes the NodeData which minimizes the max distance to all the other nodes
  * Center- Returns the center (node) of the graph (using the dijekstra algorithm).
  * TSP- Gets a nodes list.
         Returns the shortest path which contains the list's nodes
  * Save- Saves the graph into a json file , using the Gson library of google
  * Load- Loads from json file, using the Gson library of google
  
      
