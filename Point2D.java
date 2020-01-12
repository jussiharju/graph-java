/*
 * Jussi Harju
 * 424450
 * jussi.harju@tuni.fi
 * 
 */

import java.lang.Math;

public class Point2D {

  private float x; //Pisteen x-koordinaatti.
  private float y; //Pisteen y-koordinaatti.
  private Point2D neighbors[] = new Point2D[400]; //Linkit pisteen naapureihin
  private int last = 0; // viimeisimmän naapurin indeksi
  private boolean visited; // onko solmussa käyty


  public Point2D(float x, float y) {
    x(x);
    y(y);
    last(0);
    visited(false);
  }

  // setteri ja getteri x:lle
  public float x() {
    return this.x;
  }
  public void x(float f) {
    this.x = f;
  }

  // setteri ja getteri y:lle
  public float y() {
    return this.y;
  }
  public void y(float f) {
    this.y = f;
  }

  // setteri ja getteri naapureille
  public Point2D[] neighbors() {
    return this.neighbors;
  }
  public void neighbors(Point2D[] n) {
    this.neighbors = n;
  }

  // setteri ja getteri viimeisimmän naapurin indeksille
  public int last() {
    return this.last;
  }
  public void last(int i) {
    this.last = i;
  }

  // setteri ja getteri sille onko solmussa käyty
  public boolean visited() {
    return this.visited;
  }
  public void visited(boolean b) {
    this.visited = b;
  }


  // etäisyys toiseen solmuun
  public double distance(Point2D p) {
    return Math.sqrt(Math.pow((this.x - p.x()),2) + Math.pow((this.y - p.y()),2));
  }

  // lisää naapuri
  public void addNeighbor(Point2D p) {
    this.neighbors[last] = p;
    last++;
  }

  @Override
  public String toString() {
    return "(" + this.x + "; " + this.y + ")";
  }

  public boolean equals(Point2D p) {
    if(p == null)
      return false;
    else {
      if(this.x == p.x() && this.y == p.y())
        return true;
    }
    return false;
  }
}