package com.mycolumnist.model.parser;

import java.util.Date;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.util.DateUtil;
import com.mycolumnist.util.ParseUtils;

public class RadikalColumnistParser extends HtmlPageParserBase {

	public RadikalColumnistParser(){
		super(NewspaperEnum.RADIKAL, "http://www.radikal.com.tr/",
				"http://www.radikal.com.tr/Radikal.aspx?aType=RadikalYazarlar", 
				"http://www.radikal.com.tr/d/rss/RssYazarlar.xml",
				"ISO-8859-9", false);
	}

	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */
	
	@Override
	protected boolean parseUsingXPath(TagNode node) throws XPatherException {
		
		try {
			Object[] tagsForColumnists = node.evaluateXPath("//div[@class='yazar_box']");
			
			for(Object currentObject : tagsForColumnists ){
				
				TagNode tagNode = (TagNode) currentObject;
				
				int sequence = 1;
	
				TagNode linkTag = (TagNode)tagNode.evaluateXPath("//div[@class='yazarbox_right']/p/a")[0];
				
				String hyperlink = getRootDomainAddress() + linkTag.getAttributeByName("href");
				
				String columnistName =  ParseUtils.cleanStringFromWhitespaces( linkTag.getChildren().get(0).toString() );
				
				String title = ((TagNode) tagNode.evaluateXPath("//div[@class='yazarbox_right']/strong/a")[0]).getChildren().get(0).toString() ;
				String summary = ((TagNode) tagNode.evaluateXPath("//div[@class='yazarbox_right']/span/a")[0]).getChildren().get(0).toString();
				
				String dateString = ((TagNode) tagNode.evaluateXPath("//div[@class='yazarbox_left']/p[2]")[0]).getChildren().get(0).toString();
				Date date = parseDateFromString(dateString);
				
				String columnistImageSrc = ((TagNode) tagNode.evaluateXPath("//div[@class='yazarbox_left']/a/img")[0]).getAttributeByName("src");
	
				String columnistCategory = ((TagNode) tagNode.evaluateXPath("//div[@class='yazarbox_left']/p[1]/span/a")[0]).getChildren().get(0).toString();
				
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
		Date date = DateUtil.parseRegularTurkishStyleDate(dateStr,"/");
		return date;
	}

}
