package com.mycolumnist.model.parser;

import java.util.Date;

import org.apache.log4j.Logger;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.util.DateUtil;

public class HaberturkColumnistParser extends HtmlPageParserBase {

	private static Logger logger = Logger.getLogger(HaberturkColumnistParser.class);
	
	public HaberturkColumnistParser(){
		super(NewspaperEnum.HABERTURK, "http://www.haberturk.com",
				"http://www.haberturk.com/yazarlar", null,
				"UTF-8", false);
	}

	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */
	
	@Override
	protected boolean parseUsingXPath(TagNode node) throws XPatherException {
	
		try {
			
			Date date = parseDate(node);
	
			Object[] tagsForColumnists = node.evaluateXPath("//div[@id='sol_blok']/div[@class='AllArticleListMain']");
			
			for(Object currentObject : tagsForColumnists ){
				
				TagNode tagNode = (TagNode) currentObject;
				
				int sequence = 1;
	
				TagNode linkTag = (TagNode)tagNode.evaluateXPath("/div[@class='AllArticleListTitle']/a")[0];
				
				String hyperlink = getRootDomainAddress() + linkTag.getAttributeByName("href");
				String title = linkTag.getChildren().get(0).toString() ;
				
				String summary = ((TagNode) tagNode.evaluateXPath("/div[@class='AllArticleListDec']")[0]).getChildren().get(0).toString();
				
				String columnistName =  ((TagNode) tagNode.evaluateXPath("/div[@class='AllArticleListName']")[0]).getChildren().get(0).toString();
				
				String columnistImageSrc = ((TagNode) tagNode.evaluateXPath("/a/img")[0]).getAttributeByName("src");
	
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

	/** **************************************************** */
	/** HELPER METHODS */
	/** **************************************************** */

	private Date parseDate(TagNode rootTagNode) throws XPatherException {
		try {
			TagNode dateTagNode = (TagNode) rootTagNode.evaluateXPath("//div[@id='TopDateTime']")[0];
			String dateStr = dateTagNode.getChildren().get(0).toString() ;
			Date date = parseDateFromString(dateStr);
			return date;
		}
		catch(Exception e){
			logger.error("Exception during date parse", e);
			return new Date();
		}
	}
	
	private Date parseDateFromString(String dateStr) {
		
		Date date = DateUtil.parseTurkishDateHavingMonthAsString(dateStr);
		
		return date==null ? new Date() : date;
		
	}

}
