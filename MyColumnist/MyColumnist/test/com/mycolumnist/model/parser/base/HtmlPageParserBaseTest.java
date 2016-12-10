package com.mycolumnist.model.parser.base;

import java.io.IOException;

import org.htmlcleaner.XPatherException;

import com.mycolumnist.model.parser.FanatikColumnistParser;
import com.mycolumnist.model.parser.HaberturkColumnistParser;
import com.mycolumnist.model.parser.HurriyetColumnistParser;
import com.mycolumnist.model.parser.MilliyetColumnistParser;
import com.mycolumnist.model.parser.PostaColumnistParser;
import com.mycolumnist.model.parser.RadikalColumnistParser;
import com.mycolumnist.model.parser.SabahColumnistParser;
import com.mycolumnist.model.parser.TakvimColumnistParser;
import com.mycolumnist.model.parser.VatanColumnistParser;
import com.mycolumnist.model.parser.ZamanColumnistParser;


public class HtmlPageParserBaseTest {


	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		MilliyetColumnistParser milliyetColumnistParser = new MilliyetColumnistParser();
		HurriyetColumnistParser hurriyetColumnistParser = new HurriyetColumnistParser();
		ZamanColumnistParser zamanColumnistParser = new ZamanColumnistParser();
		SabahColumnistParser sabahColumnistParser = new SabahColumnistParser();
		RadikalColumnistParser radikalColumnistParser = new RadikalColumnistParser();
		PostaColumnistParser postaColumnistParser = new PostaColumnistParser();
		HaberturkColumnistParser haberturkColumnistParser = new HaberturkColumnistParser();
		TakvimColumnistParser takvimColumnistParser = new TakvimColumnistParser();
		FanatikColumnistParser fanatikColumnistParser = new FanatikColumnistParser();
		VatanColumnistParser vatanColumnistParser = new VatanColumnistParser();
		
		try {
			// milliyetColumnistParser.startParsingHtmlPage();
			// hurriyetColumnistParser.startParsingHtmlPage();
			// zamanColumnistParser.startParsingHtmlPage();
			// sabahColumnistParser.startParsingHtmlPage();
			// radikalColumnistParser.startParsingHtmlPage();
			// haberturkColumnistParser.startParsingHtmlPage();
			// vatanColumnistParser.startParsingHtmlPage();
			// postaColumnistParser.startParsingHtmlPage();
			takvimColumnistParser.startParsingHtmlPage();
			// fanatikColumnistParser.startParsingHtmlPage();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
