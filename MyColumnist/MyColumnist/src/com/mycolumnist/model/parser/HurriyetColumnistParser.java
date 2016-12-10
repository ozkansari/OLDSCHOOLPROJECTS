package com.mycolumnist.model.parser;

import java.util.Date;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.util.DateUtil;

public class HurriyetColumnistParser extends HtmlPageParserBase {

	public HurriyetColumnistParser(){
		super(NewspaperEnum.HURRIYET, "http://www.hurriyet.com.tr",
				"http://www.hurriyet.com.tr/yazarlar/", "http://rss.hurriyet.com.tr/rss.aspx?sectionId=9",
				"ISO-8859-9", false);
	}

	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */
	
	@Override
	protected boolean parseUsingXPath(TagNode node) throws XPatherException {

		try {
			TagNode rootFormTag = (TagNode) node.evaluateXPath("//form[@name='h']")[0];
			TagNode rootTableTag = (TagNode) rootFormTag.evaluateXPath("//table[@bgcolor='#FFFFFF']")[0];
			Object[] tagsForColumnists = rootTableTag.evaluateXPath("/tbody/tr[1]/td/table/tbody/tr/td[3]");
	
			// Parse date
			String dateStr = node.getElementsByAttValue("class", "hurriyet_date", true, true)[0].getChildren().get(0).toString();
			Date date = parseDateFromString(dateStr);
			
			for(Object currentObject : tagsForColumnists ){
				
				TagNode tagNode = (TagNode) currentObject;
				
				int sequence = 1;
	
				TagNode linkTag = tagNode.getElementsByAttValue("class", "ustbasliklink", true, true)[0];
				
				String hyperlink = linkTag.getAttributeByName("href");
				
				String title = ((TagNode) tagNode.evaluateXPath("/div/a/strong")[0]).getChildren().get(0).toString();
	
				String summary = tagNode.getChildren().get(8).toString();
				
				// Parse and create columnist
				String columnistName = linkTag.getChildren().get(0).toString();
				
				if(linkTag.getChildren().size()>1) {
					TagNode otherNewspaperDescriptor = (TagNode) linkTag.getChildren().get(1);
					if(linkTag.getChildren().get(1)!=null){
						String otherNewspaperImgSrc = otherNewspaperDescriptor.getAttributeByName("src");
						if(!otherNewspaperImgSrc.equalsIgnoreCase("/images/hurriyetinternet.gif")){
							continue;
						}
					}
				}
	
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
		
		Date date = DateUtil.parseTurkishDateHavingMonthAsString(dateStr);
		
		return date==null ? new Date() : date;
		
	}

}
