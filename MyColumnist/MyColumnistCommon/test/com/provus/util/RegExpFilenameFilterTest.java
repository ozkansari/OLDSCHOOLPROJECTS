package com.provus.util;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Assert;

import com.provus.util.RegExpFilenameFilter;

/**
 * Test class for com.provus.util.RegExpFilenameFilter
 * 
 * @author dilaras
 * 
 */
public class RegExpFilenameFilterTest extends TestCase {

	/**
	 * Tests com.provus.util.RegExpFilenameFilter.accept(File, String)
	 */
	public void testAccept() {
		RegExpFilenameFilter filter = new RegExpFilenameFilter();
		filter.setPattern("\\.[eE][xX][eE]$");

		File winDir = new File("c:/WINDOWS");
		boolean funcResult = filter.accept(winDir, "notepad.exe");

		Assert.assertEquals(funcResult, true);
	}
}
