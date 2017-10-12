package unrepeatedchardetector;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnrepeatedCharDetectorTest {

	UnrepeatedCharDetector detector;
	@Before
	public void setUp() throws Exception {
		detector = new UnrepeatedCharDetector();
	}

	@Test
	public void testInit() {
		assertTrue(this.detector.init());
	}

	@Test
	public void testDetect() {
		assertNotNull(this.detector.init());
		assertEquals("bc", this.detector.detect("aabcdaaadfaiweu"));
		assertEquals("a", this.detector.detect("abcdefghiklmnb"));
		assertEquals( "", this.detector.detect(""));
		assertEquals("", this.detector.detect("aabbccddee"));
		assertEquals("abcdef", this.detector.detect("abcdef"));
	}

}
