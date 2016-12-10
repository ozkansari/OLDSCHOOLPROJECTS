package com.provus.util;

/*******************************************************************************
 * $Author: Korayg $
 * $Date: 8/04/06 1:17p $
 * $Modtime: 8/04/06 1:04p $
 * $Revision: 2 $
 * $History: RegExpFilenameFilter.java $
 *
 * *****************  Version 2  *****************
 * User: Korayg       Date: 8/04/06    Time: 1:17p
 * Updated in $/JMS/src/tr/com/provus/common/util
 *
 * *****************  Version 1  *****************
 * User: Korayg       Date: 8/04/06    Time: 11:24a
 * Created in $/JMS/src/tr/com/provus/common/util
 *
 * *****************  Version 1  *****************
 * User: Korayg       Date: 26.07.06   Time: 16:48
 * Created in $/JMS/src/tr/com/provus/common/util
 *
 * *****************  Version 1  *****************
 * User: Korayg       Date: 5/22/06    Time: 2:43p
 * Created in $/JMS/src/tr/com/provus/util
 *
 * *****************  Version 1  *****************
 * User: Korayg       Date: 8/29/05    Time: 1:30p
 * Created in $/Provus/ViewController/src/tr/com/provus/util
 *
 * *****************  Version 1  *****************
 * User: Korayg       Date: 8/29/05    Time: 9:43a
 * Created in $/Provus/ViewController/src/it/tr/com/provus/util
 *
 * *****************  Version 1  *****************
 * User: Erkanc       Date: 22.10.04   Time: 14:24
 tr.com.provus.jms.utilrovus/util
 * Initial release of utility classes
 *
 ******************************************************************************/
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regular Expression filter mainly used for filenames
 * 
 * @author korayg
 * 
 */
public class RegExpFilenameFilter implements FilenameFilter {
	private Pattern pattern;

	private Matcher matcher;

	/** Creates a new instance of RegExpFilenameFilter */
	public RegExpFilenameFilter() {
	}

	/**
	 * Sets the pattern
	 * 
	 * @param value
	 */
	public void setPattern(String value) {
		pattern = Pattern.compile(value);
	}

	public boolean accept(File dir, String name) {
		matcher = pattern.matcher(name);
		return (matcher.find());
	}
}
