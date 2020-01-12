/*
 * Jussi Harju
 * 424450
 * jussi.harju@tuni.fi
 * 
 */

class Queue {
  private static class Node {
		public Object datum;
		public Node   next;

		public Node (Object datum) {
      this.datum = datum;
      this.next = null;
		}
  }
    
  public static class QueueEmptyException extends RuntimeException {}

  Node head, tail;

  public Queue () {
		this.head = null;
		this.tail = null;
  }

  public void clear () {
		head = tail = null;
  }

  public Object front () {
		Object result = null;
		if (head == null)
	    throw new QueueEmptyException();
		return head.datum;
  }
  
  public boolean isEmpty () {
		return head == null;
  }

  public void enqueue (Object o) {
		if (head == null)
	    head = tail = new Node (o);
		else {
			tail.next = new Node (o);
			tail = tail.next;
		}
  }

  public Object dequeue () {
		Object result = null;
		if (head == null)
	    throw new QueueEmptyException();
		else {
			result = head.datum;
			head = head.next;
			if (head == null)
			  tail = null;
		}
		return result;
  }
}
