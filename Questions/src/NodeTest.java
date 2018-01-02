import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class NodeTest {

	private Node a;
	
	private Node b;
	
	private Node c;
	
	private Node d;
	
	private Node e;
	
	@Before
	public void setUp() {
		a = new Node("a");
		b = new Node("b");
		c = new Node("c");
		d = new Node("d");
		e = new Node("e");
		a.setLeft(b);
		b.setLeft(c);
		b.setRight(d);
		a.setRight(e);
	}

	@Test
	public void getsChildren() {
		assertSame(b, a.getLeft());
		assertSame(e, a.getRight());
	}
	
	@Test
	public void detectsLeaf() {
		assertFalse(a.isLeaf());
		assertFalse(b.isLeaf());
		assertTrue(c.isLeaf());
		assertTrue(d.isLeaf());
		assertTrue(e.isLeaf());
	}
	
	@Test
	public void detectsHeight() {
		assertEquals(2, a.height());
		assertEquals(1, b.height());
		assertEquals(0, c.height());
		assertEquals(0, d.height());
		assertEquals(0, e.height());
	}
	
	@Test
	public void detectsHeightForTrickierTree() {
		Node p = new Node("p");
		Node q = new Node("q");
		Node r = new Node("r");
		Node s = new Node("s");
		Node t = new Node("t");
		Node u = new Node("u");
		Node v = new Node("v");
		p.setLeft(q);
		p.setRight(r);
		q.setRight(s);
		r.setLeft(t);
		s.setLeft(u);
		t.setRight(v);
		assertEquals(3, p.height());
		assertEquals(2, q.height());
		assertEquals(2, r.height());
		assertEquals(1, s.height());
		assertEquals(1, t.height());
		assertEquals(0, u.height());
		assertEquals(0, v.height());
	}
	
	@Test
	public void givesProperlyIndentedMultilineString() {
		assertEquals("a\n  b\n    c\n    d\n  e\n", a.toString());
	}

	@Test
	public void hasAlternateConstructor() {
		Node root = new Node("Does it have a motor?",
				new Node("Does it store information?",
						new Node("a hard drive"),
						new Node("a car")),
				new Node("a giraffe"));
		assertEquals("Does it have a motor?\n  Does it store information?\n    a hard drive\n    a car\n  a giraffe\n", root.toString());
	}
	
	@Test
	public void learns() {
		Node root = new Node("Does it have a motor?",
				new Node("Does it store information?",
						new Node("a hard drive"),
						new Node("a car")),
				new Node("a giraffe"));
		root.getRight().learn("a whale", "Does it live in the ocean?");
		assertEquals("Does it have a motor?\n  Does it store information?\n    a hard drive\n    a car\n  Does it live in the ocean?\n    a whale\n    a giraffe\n", root.toString());				
	}

}
