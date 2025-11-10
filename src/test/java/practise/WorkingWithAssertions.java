package practise;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkingWithAssertions {

	@Test
	public void m1() {
		System.out.println("Hard Assert");

		System.out.println("Start of the Method");

		// HardAssert //assertEquals
		Assert.assertEquals("hdfc", "hgdc");

		System.out.println("End of the Method");

	}

	@Test
	public void m2() {
		System.out.println("Hard Assert");

		System.out.println("Start of the Method");

		// HardAssert //assertNotEquals
		Assert.assertNotEquals("hdfc", "hgdc");

		System.out.println("End of the Method");

	}

	@Test
	public void m3() {
		System.out.println("Soft Assert");

		System.out.println("Start of the Method");

		// Soft Assert //assertEquals
		SoftAssert soft = new SoftAssert();
		soft.assertEquals("Amol Shardul", "Shardul Amol"); // False -- Fail

		System.out.println("End of the Method");

		soft.assertAll();
	}

	@Test
	public void m4() {
		System.out.println("Soft Assert");

		System.out.println("Start of the Method");

		// Soft Assert //assertNotEquals
		SoftAssert soft = new SoftAssert();
		soft.assertNotEquals("Amol Shardul", "Shardul Amol"); // True -- Pass

		System.out.println("End of the Method");

		soft.assertAll();
	}

	@Test
	public void m5() {
		System.out.println("Hard Assert");

		System.out.println("Start of the Method");

		// HardAssert //asertTrue
		Assert.assertTrue("hdfc".equals("hfdg")); // False -- Fail

		System.out.println("End of the Method");

	}

	@Test
	public void m6() {
		System.out.println("Soft Assert");

		System.out.println("Start of the Method");

		// HardAssert //asertFalse
		Assert.assertFalse("hdfc".equals("hfdg")); // True -- Pass

		System.out.println("End of the Method");
	}

	@Test
	public void m7() {
		System.out.println("Hard Assert");

		System.out.println("Start of the Method");

		SoftAssert soft = new SoftAssert();

		soft.assertTrue("hdfc".equals("hdgc")); // False -- Fail

		System.out.println("End of the Method");

		soft.assertAll();

	}

	@Test
	public void m8() {
		System.out.println("Soft Assert");

		System.out.println("Start of the Method");

		SoftAssert soft = new SoftAssert();

		soft.assertFalse("hdfc".equals("hdgc")); // True -- Pass

		System.out.println("End of the Method");

		soft.assertAll();
	}

	@Test
	public void m9() {
		System.out.println("Soft Assert");

		System.out.println("Start of the Method");

		String s = "Amol";

		// HardAssert //asertFalse
		Assert.assertNull(s); // False -- Fail -- s should be Null

		System.out.println("End of the Method");
	}

	@Test
	public void m10() {
		System.out.println("Soft Assert");

		System.out.println("Start of the Method");

		String s = "Amol";

		// HardAssert //asertFalse
		Assert.assertNotNull(s); // True -- Pass -- s should be Not Null and is Not Null

		System.out.println("End of the Method");
	}

	@Test
	public void m11() {
		System.out.println("Soft Assert");

		System.out.println("Start of the Method");

		SoftAssert soft = new SoftAssert();

		String s = "Amol";

		soft.assertNull(s); // False -- Fail

		System.out.println("End of the Method");

		soft.assertAll();
	}

	@Test
	public void m12() {
		System.out.println("Soft Assert");

		System.out.println("Start of the Method");

		SoftAssert soft = new SoftAssert();

		String s = "Amol";

		soft.assertNotNull(s); // True -- Pass

		System.out.println("End of the Method");

		soft.assertAll();
	}
}
