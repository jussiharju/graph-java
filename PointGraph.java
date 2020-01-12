/*
 * Jussi Harju
 * 424450
 * jussi.harju@tuni.fi
 * 
 */


public class PointGraph {
  
  /*
    fromTo is the adjacency matrix
    if there is an edge from v to w then fromTo[v][w] is non-null
    and contains the edge object. 
  */
  private Edge[][] fromTo;

  /* 
    points is the array of Point2D classes 
    representing the graph 
  */
  private Point2D[] points;


  public PointGraph(int n) {
    points(new Point2D[n]);
    fromTo(new Edge[n][n]);
  }

  public Edge[][] fromTo() {
    return this.fromTo;
  }
  public void fromTo(Edge[][] ft) {
    this.fromTo = ft;
  }

  public Point2D[] points() {
    return this.points;
  } 
  public void points(Point2D[] p) {
    this.points = p;
  }

  public void addEdge(int from, int to, double weight) {
    fromTo[from][to] = new Edge(from, to, weight);
  }

  /* return true is there exist an edge (from, to) */
  public boolean hasEdgeFromTo (int from, int to) {
    return fromTo[from][to] != null;
  }


  /* set markers in all vertices to val */
  private void clearPointMarkers() {
    for (int i = 0; i < points.length; ++i)
      points[i].visited(false);
  }

  /* perform depthFirstSearch starting at point v */
  public void breadthFirstSearch(int n) {
    clearPointMarkers();  /* clear markers from points */
    
    // Create a queue for BFS 
    Queue queue = new Queue();

    // Mark the current point as visited and enqueue it 
    points[n].visited(true); 
    queue.enqueue(n);
  
    while(!queue.isEmpty()) {
      int v = (Integer)queue.dequeue();
      System.out.println(points[v]);
      for(int w=0; w < points.length; w++) {
        if (hasEdgeFromTo(v, w)) {
          /* see if (v,w) leads to a new vertex */
          if (!points[w].visited()) {
            queue.enqueue(w);
            points[w].visited(true);
          }
        }
      }
    }
  }

  /* perform depthFirstSearch starting at point n */
  public void depthFirstSearch(int n) {
    clearPointMarkers();  /* clear markers from points */
    depthFirstSearchRec(n);              /* start recursive routine     */
  }

  /* recursive part */
  private void depthFirstSearchRec (int v) {
    System.out.println(points[v]);
    /* mark point found so we don't loop forever in case of a cycle */
    points[v].visited(true);
    for (int w = 0; w < points.length; ++w) {
      if (hasEdgeFromTo(v, w)) {
        /* see if (v,w) leads to a new vertex */
        if (!points[w].visited()) {
          depthFirstSearchRec(w);
        }
      }
    }
  }

  /* test if the graph is connected using a depth first search */
  public boolean isConnected () {
    boolean connected = true;
    /* perform search starting at vertex 0 */
    depthFirstSearch(0);
    /* see if we found all nodes in the search */
    for (int i = 0; connected && i < points.length; ++i)
      if (!points[i].visited())
        /* nope => this graph is not connected */
        connected = false;
    return connected;
  }

  // palauttaa solmun lähtöasteen
  public int getOutDeg(int v) {
    return points[v].last();
  }

  // palauttaa solmun tuloasteen
  public int getInDeg(int v) {
    int count = 0;
    for(int i=0; i<400; i++) {
      if(hasEdgeFromTo(i, v)) {
        count++;
      }
    }
    return count;
  }
}