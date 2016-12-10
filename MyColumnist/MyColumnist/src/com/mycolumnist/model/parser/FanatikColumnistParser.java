package com.mycolumnist.model.parser;

import java.util.Date;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.util.DateUtil;
import com.mycolumnist.util.ParseUtils;

public class FanatikColumnistParser extends HtmlPageParserBase {

	public FanatikColumnistParser(){
		super(NewspaperEnum.FANATIK, "http://fanatik.ekolay.net",
				"http://fanatik.ekolay.net/Default.aspx?aType=Yazarlar", null,
				"UTF-8", false);
	}

	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */
	
	@Override
	protected boolean parseUsingXPath(TagNode node) throws XPatherException {

		try  {
			Object[] tagsForColumnists = node.evaluateXPath("//div[@class='bastrib_box']");
			
			for(Object currentObject : tagsForColumnists ){
				
				TagNode tagNode = (TagNode) currentObject;
				
				int sequence = 1;
	
				String columnistName =  ParseUtils.cleanStringFromWhitespaces( ((TagNode)tagNode.evaluateXPath("/div[@class='bastrib_text']/p/a")[0]).getChildren().get(0).toString() );
				
				TagNode linkTag = ((TagNode) tagNode.evaluateXPath("/div[@class='bastrib_text']/h3/a")[0]);
				String title = linkTag.getChildren().get(0).toString() ;
				String summary = ((TagNode) tagNode.evaluateXPath("/div[@class='bastrib_text']")[0]).getChildren().get(4).toString();
				String hyperlink = getRootDomainAddress() + linkTag.getAttributeByName("href");
				
				String dateString = ((TagNode) tagNode.evaluateXPath("//div[@class='bastrib_img']")[0]).getChildren().get(1).toString();
				Date date = parseDateFromString(dateString);
				
				String columnistImageSrc = ((TagNode) tagNode.evaluateXPath("//div[@class='bastrib_img']/a/img")[0]).getAttributeByName("src");
	
				String columnistCategory = "Spor";
				
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
