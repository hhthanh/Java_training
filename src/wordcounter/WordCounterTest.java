package wordcounter;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordCounterTest {
	WordCounter counter;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() throws Exception {
		counter = new WordCounter();
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@Test
	public void testInit() {
		assertTrue(this.counter.init());
	}

	//the print words method use stream() method of the map, which is not guarantee the order of the 
	//word in the map, so we can't test the multiple different words case
	@Test
	public void testPrintWords() {
		this.counter.countWord("ha");
		assertEquals("ha: 1\r\n",outContent.toString());
		outContent.reset();
		
		this.counter.countWord("ha ha ha ha");
		assertEquals("ha: 4\r\n",outContent.toString());
		
		outContent.reset();
		this.counter.countWord("");
		assertEquals("String is empty!\r\n",outContent.toString());
	}
	
	@After
	public void cleanStream() {
		System.setOut(null);
	    System.setErr(null);
	}
	
}
