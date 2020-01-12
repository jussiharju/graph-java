/*
 * Jussi Harju
 * 424450
 * jussi.harju@tuni.fi
 * 
 */

import java.io.*;
import java.util.Scanner;
import java.lang.Float;

public class T2018 {

  public static void main(String[] args) {
    T2018 ht = new T2018();		      
    PointGraph pg = new PointGraph(400);
    // luetaan tiedosto
    ht.readInput();
    // luodaan pisteet
    ht.createPoints(pg);
    // lähin naapuri
    ht.joinNearest(pg);
    // toiseksi lähin naapuri
    ht.joinSecondNearest(pg);
    
    // Kysytään mistä indeksistä aloitetaan haut,
    // koska graafi ei ole yhtenäinen.
    Scanner scanner = new Scanner(System.in);
    ht.writeBFS(pg, scanner);
    ht.writeDFS(pg, scanner);
    ht.writeDegrees(pg);
    scanner.close();
  }

  private Point2D[] points = new Point2D[400];

  private void joinNearest(PointGraph pg) {
    double dist, min;
    int minIndex = 0;
    Point2D minTemp = null;
    for(int i = 0; i < 400; i++) {
      if(i < 398) {
        min = points[i].distance(points[i+1]);
      }
      else
        min = points[i].distance(points[i-1]);
      for(int j = 0; j < 400; j++) {
        if(i != j) {
          dist = points[i].distance(points[j]);
          if(dist < min) {
            min = dist;
            minTemp = points[j];
            minIndex = j;
          }
        }
      }
      points[i].addNeighbor(minTemp);
      pg.addEdge(i, minIndex, min);
    }
  }

  private void joinSecondNearest(PointGraph pg) {
    double dist, min;
    int minIndex = 0;
    Point2D minTemp = null;
    for(int i = 0; i < 400; i++) {
      if(i < 398) {
        min = points[i].distance(points[i+1]);
      }
      else
        min = points[i].distance(points[i-1]);
      for(int j = 0; j < 400; j++) {
        if(i != j) {
          dist = points[i].distance(points[j]);
          if(dist < min && points[j] != points[i].neighbors()[0]) {
            min = dist;
            minTemp = points[j];
            minIndex = j;
          }
        }
      }
      points[i].addNeighbor(minTemp);
      pg.addEdge(i, minIndex, min);
    }
  }

  private void createPoints(PointGraph pg) {
    for(int i = 0; i < 400; i++) {
      Point2D hifk = new Point2D(x[i], y[i]); 
      points[i] = hifk;
    }
    pg.points(points);
  }
  
  private	String line;
  private float x[];
  private float y[];

  private void readInput() {
    x=new float[400];
    y=new float[400];
    try {
      BufferedReader br = new BufferedReader( new FileReader("Tdata.txt"));
      for(int i=0; i<400; i++) {
        line=br.readLine();
        String[] values=line.split(",");	
        x[i]=Float.parseFloat(values[0]);	
        y[i]=Float.parseFloat(values[1]);				
        
      }
      br.close();
    } 
    catch(IOException e) {
      System.out.println("File not found.");
    }
  }

  public void writeBFS(PointGraph pg, Scanner scanner) {
    try {
      PrintStream originalOut = System.out;
      PrintStream fileOut = new PrintStream(new File("BFS.txt"));
      System.setOut(fileOut);
      originalOut.println("Give an index where to start performing the BFS [0...399]:");
      int index = scanner.nextInt();
      pg.breadthFirstSearch(index);
      originalOut.println("BFS written to BFS.txt");
      System.setOut(originalOut);
    } 
    catch(FileNotFoundException ex) {
      ex.printStackTrace();
    }
  }

  public void writeDFS(PointGraph pg, Scanner scanner) {
    try {
      PrintStream originalOut = System.out;
      PrintStream fileOut = new PrintStream(new File("DFS.txt"));
      System.setOut(fileOut);
      originalOut.println("Give an index where to start performing the DFS [0...399]:");
      int index = scanner.nextInt();
      pg.depthFirstSearch(index);
      originalOut.println("DFS written to DFS.txt");
      System.setOut(originalOut);
    } 
    catch(FileNotFoundException ex) {
      ex.printStackTrace();
    }
  }

  public void writeDegrees(PointGraph pg) {
    try {
      PrintStream originalOut = System.out;
      PrintStream fileOut = new PrintStream(new File("Degrees.txt"));
      System.setOut(fileOut);
      for(int i = 0; i < pg.points().length; i++) {
        System.out.println(pg.points()[i] + 
        " in: " + pg.getInDeg(i) + "; out: " +
        pg.getOutDeg(i));
      } 
      System.setOut(originalOut);
    } 
    catch(FileNotFoundException ex) {
      ex.printStackTrace();
    }
  }

}