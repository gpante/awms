package com.cs506;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestWorkshopRequestPage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void RendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(WorkshopRequestPage.class);

		//assert rendered page class
		tester.assertRenderedPage(WorkshopRequestPage.class);
	}
}
