package com.mycolumnist.model.parser;

import java.util.Date;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.util.DateUtil;

public class PostaColumnistParser extends HtmlPageParserBase {

	public PostaColumnistParser(){
		super(NewspaperEnum.POSTA, "http://www.posta.com.tr",
				"http://www.posta.com.tr/yazarlar", null,
				"UTF-8", true);
	}

	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */
	
	/*
		<div class="yazarTumu">
		  <div class="eposta">
		    <a href="mailto:COLUMNIST_EMAIL">mabirand@e-kolay.net</a>
		  </div>
		  <a href="COLUMNIST_ALL_COLUMNS_HYPERLINK" class="name">
		    <strong>
				<img src="COLUMNIST_IMAGE" alt="COLUMNIST_NAME" width="74" height="83" />
				COLUMNIST_NAME
			</strong>
		  </a>
		  <p>
		    <a href="HYPERLINK">
				<strong>
					TITLE
				</strong>
		    </a>
		    <em>DATE</em>
		  </p>
		  <div class="cls"></div>
		</div>
	 */
	@Override
	protected boolean parseUsingXPath(TagNode node) throws XPatherException {

		try {
			Object[] tagsForColumnists = node.evaluateXPath("//div[@class='yazarTumu']");
			
			for(Object currentObject : tagsForColumnists ){
				
				TagNode tagNode = (TagNode) currentObject;
				
				int sequence = 1;
				
				TagNode imgTag = (TagNode) tagNode.evaluateXPath("/a[1]/strong/img")[0];
				String columnistImageSrc = imgTag.getAttributeByName("src");
				String columnistName = imgTag.getAttributeByName("alt");
				
				TagNode hyperlinkTag = (TagNode) tagNode.evaluateXPath("/p[1]/a")[0];
				String hyperlink = hyperlinkTag.getAttributeByName("href");
					
				TagNode titleTag = (TagNode) tagNode.evaluateXPath("/p[1]/a/strong")[0];
				String title = titleTag.getChildren().get(0).toString();
				
				TagNode dateTag = (TagNode) tagNode.evaluateXPath("/p[1]/em")[0];
				String dateString = dateTag.getChildren().get(0).toString();
				Date date = parseDateFromString(dateString);
				
				// TODO: Inner parse required
				String summary = "";
				
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
	protected boolean parseUsingElementAttributes(TagNode node)
			throws XPatherException {
		
		try {
			// <div class="yazarTumu">
			TagNode[] tagsForColumnists = node.getElementsByAttValue("class", "yazarTumu", true, true);
			
			// Loop for each columnist part of the html
			for (TagNode currentObject : tagsForColumnists) {
				
				int sequence = 1;
			}
			
			// TODO: IMPLEMENTATION HAS NOT FINISHED YET
			return false;
			
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
