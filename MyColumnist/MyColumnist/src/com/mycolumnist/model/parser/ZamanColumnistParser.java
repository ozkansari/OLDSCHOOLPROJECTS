package com.mycolumnist.model.parser;

import java.util.Date;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.util.DateUtil;
import com.mycolumnist.util.ParseUtils;

public class ZamanColumnistParser extends HtmlPageParserBase {

	public ZamanColumnistParser(){
		super(NewspaperEnum.ZAMAN, 
				"http://zaman.com.tr/",
				"http://zaman.com.tr/yazar.do", 
				"http://zaman.com.tr/yazarlar.rss",
				"ISO-8859-9", 
				false);
	}

	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */
	
	@Override
	protected boolean parseUsingXPath(TagNode node) throws XPatherException {
		
		try {
			Object[] tagsForColumnists = node.evaluateXPath("//div[@id='ortaBuyukSutunDiv']/div[2]/table");
			
			// Parse date
			String dateStr =  parseDate(node);
			Date date = parseDateFromString(dateStr);
			
			for(Object currentObject : tagsForColumnists ){
						
				TagNode tagNode = (TagNode) currentObject;
				
				int sequence = 1;
	
				TagNode linkTag = tagNode.getElementsByAttValue("class", "baslik", true, true)[0];
				
				String hyperlink = getRootDomainAddress() + linkTag.getAttributeByName("href");
				
				String title =  ParseUtils.cleanStringFromWhitespaces( linkTag.getChildren().get(0).toString() );
	
				String summary = tagNode.getElementsByAttValue("class", "spot", true, true)[0].getChildren().get(0).toString();
				
				// Parse and create columnist
				String columnistName =  ParseUtils.cleanStringFromWhitespaces( ((TagNode) tagNode.evaluateXPath("/tbody/tr/td[2]/table/tbody/tr/td/p/span")[0]).getChildren().get(0).toString() );
	
				String columnistImageSrc = ((TagNode) tagNode.evaluateXPath("/tbody/tr/td[1]/img")[0]).getAttributeByName("src");
	
				// TODO
				String columnistCategory = "";
				
				createResultedInstances(sequence, summary, hyperlink, title, date,
						columnistName,columnistCategory,columnistImageSrc);
			}
		}
		catch(Exception allException){
			return false;
		}
		
		return true;
	}

	@Override
	protected boolean parseInnerPage(String innerPageUrl){
		// TODO: Implement
		return false;
	}
	
	private String parseDate(TagNode node) throws XPatherException {
		
		String date = null;
		try {
			date = ParseUtils.cleanStringFromWhitespaces(
					((TagNode) (node.evaluateXPath( "//div[@id='ustDiv']//tr/td[@width='95']/p/font[@color='#6A6D7C']" )[0]))
					.getChildren().get(0).toString());
		} catch (Exception allException) {
			// TODO: log
		}
		return date;
		
	}

	/** **************************************************** */
	/** HELPER METHODS */
	/** **************************************************** */

	private Date parseDateFromString(String dateStr) {
		
		Date dateParsed = DateUtil.parseRegularTurkishStyleDate(dateStr.split(",")[0],"\\.");

		return dateParsed==null? new Date() : dateParsed ;
	}

}
