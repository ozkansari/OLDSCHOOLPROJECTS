package com.mycolumnist.model.parser.base;

import java.io.IOException;

import org.apache.log4j.Logger;
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

public class ParserVariables {

	private static Logger logger = Logger.getLogger(ParserVariables.class);
	
	private static boolean parsInitialized = false;
	
	private static HtmlPageParserBase milliyetColumnistParser;
	private static HtmlPageParserBase hurriyetColumnistParser;
	private static HtmlPageParserBase zamanColumnistParser;
	private static HtmlPageParserBase sabahColumnistParser;
	private static HtmlPageParserBase radikalColumnistParser;
	private static HtmlPageParserBase postaColumnistParser;
	private static HtmlPageParserBase haberturkColumnistParser;
	private static HtmlPageParserBase takvimColumnistParser;
	private static HtmlPageParserBase fanatikColumnistParser;
	private static HtmlPageParserBase vatanColumnistParser;

	public ParserVariables(){
		initParsers();
	}

	private static void initParsers() {
		milliyetColumnistParser = new MilliyetColumnistParser();
		hurriyetColumnistParser = new HurriyetColumnistParser();
		zamanColumnistParser = new ZamanColumnistParser();
		sabahColumnistParser = new SabahColumnistParser();
		radikalColumnistParser = new RadikalColumnistParser();
		postaColumnistParser = new PostaColumnistParser();
		haberturkColumnistParser = new HaberturkColumnistParser();
		takvimColumnistParser = new TakvimColumnistParser();
		fanatikColumnistParser = new FanatikColumnistParser();
		vatanColumnistParser = new VatanColumnistParser();
		parsInitialized=true;
	}

	/** **************************************************** */
	/** INITIALIZER METHOD(S) */
	/** **************************************************** */
	
	/**
	 * @param newspaperParser
	 */
	private static void parseNewspaper(HtmlPageParserBase newspaperParser) {

		logger.info("START PARSING " + newspaperParser.getNewspaperName() );
		logger.info("_____________________________________________________________________");
		try {
			newspaperParser.startParsingHtmlPage();
		} catch (IOException e) {
			logger.error("IOException during ParserVariables.startParsingHtmlPage() : ",e);
		} catch (XPatherException e) {
			logger.error("XPatherException during ParserVariables.startParsingHtmlPage() : ",e);
		}
		logger.info("END PARSING " + newspaperParser.getNewspaperName() );
		logger.info("_____________________________________________________________________");
	}
	
	public static void parseAll() {
		if(!parsInitialized){
			initParsers();
		}
		parseMilliyetColumnistParser();
        parseHurriyetColumnistParser();
        parseZamanColumnistParser();
        parseSabahColumnistParser();
        parseRadikalColumnistParser();
        parsePostaColumnistParser();
        parseHaberturkColumnistParser();
        parseTakvimColumnistParser();
        parseFanatikColumnistParser();
        parseVatanColumnistParser();
	}

	public static HtmlPageParserBase parseMilliyetColumnistParser() {
		parseNewspaper(milliyetColumnistParser);
		return milliyetColumnistParser;
	}
	
	public static HtmlPageParserBase parseHurriyetColumnistParser() {
		parseNewspaper(hurriyetColumnistParser);
		return hurriyetColumnistParser;
	}

	public static HtmlPageParserBase parseZamanColumnistParser() {
		parseNewspaper(zamanColumnistParser);
		return zamanColumnistParser;
	}
	
	public static HtmlPageParserBase parseSabahColumnistParser() {
		parseNewspaper(sabahColumnistParser);
		return sabahColumnistParser;
	}
	
	
	public static HtmlPageParserBase parseRadikalColumnistParser() {
		parseNewspaper(radikalColumnistParser);
		return radikalColumnistParser;
	}
	
	public static HtmlPageParserBase parsePostaColumnistParser() {
		parseNewspaper(postaColumnistParser);
		return postaColumnistParser;
	}
	
	public static HtmlPageParserBase parseHaberturkColumnistParser() {
		parseNewspaper(haberturkColumnistParser);
		return haberturkColumnistParser;
	}
		
	public static HtmlPageParserBase parseTakvimColumnistParser() {
		parseNewspaper(takvimColumnistParser);
		return takvimColumnistParser;
	}
	
	public static HtmlPageParserBase parseFanatikColumnistParser() {
		parseNewspaper(fanatikColumnistParser);
		return fanatikColumnistParser;
	}
	
	public static HtmlPageParserBase parseVatanColumnistParser() {
		parseNewspaper(vatanColumnistParser);
		return vatanColumnistParser;
	}

	/** **************************************************** */
	/** MODIFIER AND ACCESSOR METHODS */
	/** **************************************************** */
	
	public static HtmlPageParserBase getMilliyetColumnistParser() {
		return milliyetColumnistParser;
	}

	public static HtmlPageParserBase getHurriyetColumnistParser() {
		return hurriyetColumnistParser;
	}

	public static HtmlPageParserBase getZamanColumnistParser() {
		return zamanColumnistParser;
	}

	public static HtmlPageParserBase getSabahColumnistParser() {
		return sabahColumnistParser;
	}

	public static HtmlPageParserBase getRadikalColumnistParser() {
		return radikalColumnistParser;
	}

	public static HtmlPageParserBase getPostaColumnistParser() {
		return postaColumnistParser;
	}

	public static HtmlPageParserBase getHaberturkColumnistParser() {
		return haberturkColumnistParser;
	}

	public static HtmlPageParserBase getTakvimColumnistParser() {
		return takvimColumnistParser;
	}

	public static HtmlPageParserBase getFanatikColumnistParser() {
		return fanatikColumnistParser;
	}

	public static HtmlPageParserBase getVatanColumnistParser() {
		return vatanColumnistParser;
	}

	public static boolean isParsInitialized() {
		return parsInitialized;
	}

}
