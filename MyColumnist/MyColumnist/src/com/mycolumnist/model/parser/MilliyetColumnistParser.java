package com.mycolumnist.model.parser;

import java.util.Date;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.util.DateUtil;

public class MilliyetColumnistParser extends HtmlPageParserBase {

	public MilliyetColumnistParser() {
		super(NewspaperEnum.MILLIYET, 
				"http://www.milliyet.com.tr",
				"http://www.milliyet.com.tr/Yazar.aspx?aType=Yazarlar", 
				"http://www.milliyet.com.tr/D/rss/rss/RssY.xml",
				"UTF-8", 
				true);
	}

	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */
	
	@Override
	protected boolean parseUsingElementAttributes(TagNode node) throws XPatherException {
		
		try {
			TagNode[] tagsForColumnists = node.getElementsByAttValue("class",
					"yazarlar", true, true);
	
			// Loop for each columnist part of the html
			for (TagNode tagNode : tagsForColumnists) {
	
				TagNode[] linkNodes = tagNode.getElementsHavingAttribute("href",
						true);
	
				TagNode linkTag = linkNodes[1];
	
				int sequence = 1;
	
				String summary = "";
	
				String hyperlink = linkTag.getAttributeByName("href");
	
				String title = linkTag.getChildren().get(2).toString();
	
				// Parse date
				String dateStr = ((TagNode) linkTag.getChildren().get(1)).getChildren().get(0).toString();
				Date date = parseDateFromString(dateStr);
	
				// Parse columnist name
				String columnistName = ((TagNode) linkTag.getChildren().get(0)).getChildren().get(1).toString();
				
				// TODO
				String columnistCategory = "";
				
				// TODO
				String columnistImageSrc = "";
				
				createResultedInstances(sequence, summary, hyperlink, title, date,
						columnistName,columnistCategory,columnistImageSrc);
		
			}
			
			return true;
		}
		catch(Exception allException){
			return false;
		}
	}
	
	@Override
	protected boolean parseInnerPage(String innerPageUrl){
		// TODO: Implement
		return false;
	}

	/** **************************************************** */
	/** HELPER METHODS */
	/** **************************************************** */

	private Date parseDateFromString(String dateStr) {
		Date date = DateUtil.parseRegularTurkishStyleDate(dateStr,"\\.");
		return date;
	}

}
