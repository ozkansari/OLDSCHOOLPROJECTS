package com.mycolumnist.model.parser;

import java.util.Date;

import org.apache.log4j.Logger;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.util.DateUtil;
import com.mycolumnist.util.ParseUtils;

public class SabahColumnistParser extends HtmlPageParserBase {

	private static Logger logger = Logger.getLogger(SabahColumnistParser.class);
	
	public SabahColumnistParser(){
		super(NewspaperEnum.SABAH, 
				"http://sabah.com.tr",
				"http://www.sabah.com.tr/Yazarlar/", 
				"http://www.sabah.com.tr/rss/Yazarlar.xml",
				"ISO-8859-9", false);
	}

	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */
	
	@Override
	protected boolean parseUsingXPath(TagNode node)
			throws XPatherException {
		
		try {
			TagNode rootDivTag = (TagNode) node.evaluateXPath("//div[@id='center_column']")[0];
			
			Object[] tagsForColumnists = rootDivTag.evaluateXPath("/div/div/table[@width='630']/tbody/tr/td/div[@style]");
			
			// Parse date
			Date date = parseDate(node);
			
			for(Object currentObject : tagsForColumnists ){
				
				TagNode tagNode = (TagNode) currentObject;
				
				int sequence = 1;
	
				TagNode linkTag = tagNode.getElementsByAttValue("class", "yazar_adi", true, true)[0];
				String hyperlink = getRootDomainAddress() + linkTag.getAttributeByName("href");
				String columnistName =  ParseUtils.cleanStringFromWhitespaces( linkTag.getChildren().get(0).toString() );
				
				TagNode yazarSpotTag = ((TagNode)tagNode.getElementsByAttValue("class", "yazar_spot", true, true)[0]);
				String title = ParseUtils.cleanStringFromWhitespaces( ((TagNode)yazarSpotTag.getChildren().get(0)).getChildren().get(0).toString() );
				String summary = ParseUtils.cleanStringFromWhitespaces( yazarSpotTag.getChildren().get(2).toString() );
				
				String columnistImageSrc = ((TagNode) tagNode.evaluateXPath("/div/div/a/img")[0]).getAttributeByName("src");
	
				// TODO
				String columnistCategory = "";
				
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

	private Date parseDate(TagNode node) {
		
		try {
			String dateStr =  ParseUtils.cleanStringFromWhitespaces( node.getElementsByAttValue("class", "koyu_gri_renk ari11", true, true)[1].getChildren().get(0).toString() );
			Date date = parseDateFromString(dateStr);
			return date;
		}
		catch(Exception e){
			logger.error("Exception during date parse", e);
			return new Date();
		}
	}

	/** **************************************************** */
	/** HELPER METHODS */
	/** **************************************************** */

	private Date parseDateFromString(String dateStr) {

		Date date = DateUtil.parseTurkishDateHavingMonthAsString(dateStr);
		return date==null ? new Date() : date;
		
	}

}
