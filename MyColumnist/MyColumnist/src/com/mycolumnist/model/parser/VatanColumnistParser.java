package com.mycolumnist.model.parser;

import java.util.Date;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.util.DateUtil;

/**
 * 
 * Element DOM Tree To Be parsed is shown in VatanColumnistParser.txt
 * 
 * @author Ozkan SARI
 *
 */
public class VatanColumnistParser extends HtmlPageParserBase {

	public VatanColumnistParser(){
		super(NewspaperEnum.VATAN, 
				"http://www.gazetevatan.com",
				"http://www10.gazetevatan.com/yazarlarbugun.asp", 
				null,
				"ISO-8859-9", 
				true);
	}

	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */
	
	@Override
	protected boolean parseUsingXPath(TagNode node) throws XPatherException {

		try {
			Object[] tagsForColumnists = node.evaluateXPath("/body/table[4]/tbody/tr/td[1]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/font");
			
			for(Object currentObject : tagsForColumnists ){
				
				TagNode tagNode = (TagNode) currentObject;
				
				int sequence = 1;
	
				TagNode imgTag = (TagNode)tagNode.evaluateXPath("/b/font/a/img")[0];
				String columnistImageSrc = imgTag.getAttributeByName("src");
	
				String columnistName = ((TagNode)tagNode.evaluateXPath("/b/font")[0]).getChildren().get(3).toString() ;
				
				TagNode linkTag = (TagNode)tagNode.evaluateXPath("/font[2]/a[2]")[0];
				String hyperlink = linkTag.getAttributeByName("href");
				TagNode titleTag = (TagNode)linkTag.evaluateXPath("/i/b")[0];
				String title =    titleTag.getChildren().get(0).toString();
				
				TagNode dateTag = (TagNode)tagNode.evaluateXPath("/font[2]/font[1]")[0];
				String dateString = dateTag.getChildren().get(0).toString();
				Date date = DateUtil.parseRegularTurkishStyleDate(dateString,"\\.");
					
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
			// <font face="Verdana, Arial, Helvetica, sans-serif" size="2">
	
			TagNode[] tagsForColumnists = node.getElementsByAttValue("face",
					"Verdana, Arial, Helvetica, sans-serif", true, true);
			
			// Loop for each columnist part of the html
			for (TagNode font : tagsForColumnists) {
				
				int sequence = 1;
				
				TagNode font_b = (TagNode) font.getChildren().get(0);
				
				TagNode font_b_font = (TagNode) font_b.getChildren().get(0);
				String columnistName = font_b_font.getChildren().get(3).toString();
				
				TagNode font_b_font_a = (TagNode) font_b_font.getChildren().get(2);
				String hyperlink = font_b_font_a.getAttributeByName("href");
				
				TagNode font_b_font_a_img = (TagNode) font_b_font_a.getChildren().get(0);
				String columnistImageSrc = font_b_font_a_img.getAttributeByName("src");
	
				TagNode font_font2 = (TagNode) font.getChildren().get(3);
				TagNode font_font2_font1 = (TagNode) font_font2.getChildren().get(1);
				String dateString = font_font2_font1.getChildren().get(0).toString();
				Date date = DateUtil.parseRegularTurkishStyleDate(dateString,"\\.");
				
				TagNode font_font2_a2 = (TagNode) font_font2.getChildren().get(6);
				TagNode font_font2_a2_i = (TagNode) font_font2_a2.getChildren().get(0);
				TagNode font_font2_a2_i_b = (TagNode) font_font2_a2_i.getChildren().get(0);
				String title = font_font2_a2_i_b.getChildren().get(0).toString();
				
				// TODO: Iner parse required
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
	protected boolean parseInnerPage(String innerPageUrl){
		// TODO: Implement
		return false;
	}
	
	/** **************************************************** */
	/** HELPER METHODS */
	/** **************************************************** */


}
