package com.mycolumnist.model.parser;

import java.util.Date;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.util.DateUtil;

public class TakvimColumnistParser extends HtmlPageParserBase {

	public TakvimColumnistParser(){
		super(NewspaperEnum.TAKVIM, 
				"http://www.takvim.com.tr",
				"http://www.takvim.com.tr/Yazarlar/", 
				"http://www.takvim.com.tr/rss/Yazarlar.xml",
				"ISO-8859-9", false);
	}

	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */
	
	@Override
	protected boolean parseUsingXPath(TagNode node) throws XPatherException {

		try {
			// Parse date
			// <td align="left" valign="top" class="tarih"> <span style="margin-left: 10px"> DATE </span> </td> 
			String dateString = ((TagNode)node.evaluateXPath("//td[@class='tarih']/span")[0]).getChildren().get(0).toString();
			Date date = parseDateFromString(dateString);
			
			// <span class="yazarlar"> COLUMNIST_NAME </span>
			Object[] columnistNames = node.evaluateXPath("//span[@class='yazarlar']");
			
			// <a class="news_title" href="LINK"> TITLE </a>
			Object[] columnTitlesAndLinks = node.evaluateXPath("//a[@class='news_title']");
			
			// <div class="news3"> <a href="LINK"> SUMMARY </a> </div> 
			Object[] columnSummaries = node.evaluateXPath("//div[@class='news3']/a");
			
			for( int i=0; i<columnistNames.length; i++){
				
				int sequence = 1;
				
				String columnistName = ((TagNode)columnistNames[i]).getChildren().get(0).toString();
				
				String hyperlink = getRootDomainAddress() + ((TagNode)columnTitlesAndLinks[i]).getAttributeByName("href");
				
				String title = ((TagNode)columnTitlesAndLinks[i]).getChildren().get(0).toString();
				
				String summary = ((TagNode)columnSummaries[i]).getChildren().get(0).toString();
				
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
