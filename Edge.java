/*
 * Jussi Harju
 * 424450
 * jussi.harju@tuni.fi
 * 
 */

public class Edge {           

  public double  weight;
  public int     vertex1;    /* nodes attached to            */
  public int     vertex2;
  public int     marker;     /* marker for use in algorithms */

  public Edge (int vertex1, int vertex2, double weight) {
    this.vertex1  = vertex1;
    this.vertex2  = vertex2;
    this.weight   = weight;
  }

  /* for printing and debugging; lower number vertex first */
  public String toString() {
    return "{" + vertex1 + "," + vertex2 + "} w = " + weight;
  }
  
  /*
    int otherEnd (int thisEnd)

    returns the other end of the edge given the other,
    useful for undirected graphs - note: no error checking,
    so thisEnd _must_ be one of the ends of the edge.
  */
  public int otherEnd (int thisEnd) {
    return vertex1 == thisEnd ? vertex2 : vertex1;
  }
}
