package com.mycolumnist.model.parser.base;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.persistence.entity.Column;
import com.mycolumnist.model.persistence.entity.Columnist;
import com.mycolumnist.model.persistence.entity.Newspaper;
import com.mycolumnist.model.persistence.hibernate.ColumnHibernate;
import com.mycolumnist.model.persistence.hibernate.ColumnistHibernate;
import com.mycolumnist.model.persistence.hibernate.NewspaperHibernate;
import com.mycolumnist.util.EntityUtil;

public abstract class HtmlPageParserBase {

	private static Logger logger = Logger.getLogger(HtmlPageParserBase.class);
	
	protected Newspaper newspaper;
	protected NewspaperEnum newspaperEnum;
	protected String rootDomainAddress;
	protected URL newspaperPageURL;
	protected URL newspaperRssURL;
	protected String charset = "UTF-8";
	
	public List<Columnist> columnistList;
	private List<Column> columnList;
	
	protected HtmlCleaner cleaner;
	protected CleanerProperties props;
	
	protected boolean innerParseRequired = false;
		
	private Date startOfToday = new Date();
	
	/**
	 * HtmlPageParserBase constructor creates HtmlCleaner instances and set
	 * default parse properties
	 */
	public HtmlPageParserBase(
			NewspaperEnum newspaperEnum,
			String rootDomainAddress, 
			String newspaperPageURLStr,
			String newspaperRssURLStr, 
			String charset,
			boolean innerParseRequired) {

		this.newspaperEnum = newspaperEnum;
		this.rootDomainAddress = rootDomainAddress;
		setNewspaperPageURL(newspaperPageURLStr);
		setNewspaperRssURL(newspaperRssURLStr);
		this.charset = charset;
		this.innerParseRequired = innerParseRequired;
		
		this.newspaper = NewspaperHibernate.findById(newspaperEnum.getId());
		
	}

	@SuppressWarnings("deprecation")
	private void init() {
		
		startOfToday = new Date();
		startOfToday.setHours(0);
		startOfToday.setMinutes(0);
		startOfToday.setSeconds(0);
		
		if(columnistList==null){
			columnistList = new ArrayList<Columnist>();
		} else {
			columnistList.clear();
		}
		
		if(columnList==null){
			columnList = new ArrayList<Column>();
		} else {
			columnList.clear();
		}
		
		// create an instance of HtmlCleaner
		cleaner = new HtmlCleaner();

		// take default cleaner properties
		props = cleaner.getProperties();

		// customize cleaner's behaviour with property setters
		props.setOmitComments(true);
		props.setNamespacesAware(true);
	}

	
	/** **************************************************** */
	/** PARSE METHODS */
	/** **************************************************** */	
	
	/**
	 * Common parse method to start parsing
	 * 
	 * @throws IOException
	 * @throws XPatherException
	 */
	public void startParsingHtmlPage() throws IOException, XPatherException {

		init();

		try {
			
			boolean success = false;
			
			TagNode node = null;
			try {
				node = getRootTagNode();
				success = true;
			}
			catch(Exception e){
				success = false;
				logger.error("Error parsing TagNode: ",e);
			}
			
			success = parseUsingXPath(node);
			if(!success) {
				success = parseUsingElementAttributes(node);
			}
			if(!success) {
				success = parseUsingRss();
			}
			if(!success || columnList.size()<=0) {
				success = getLatestFromDatabase();
			}
			if(!success) {
				logger.error(newspaperEnum.getName()+ " is not successfull");
			}
			
		} catch(Exception allException){
			logger.error(newspaperEnum.getName() + " PARSE EXCEPTION : ",allException);
		}
		
	}
	

	/**
	 * Gives the root tag node using html cleaner parser
	 * 
	 * @return resulted TagNode of the tree-like structure created by HtmlCleaner
	 * 
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	protected TagNode getRootTagNode() throws IOException,
			UnsupportedEncodingException {
		// Open a connection to the desired URL
		URLConnection conn = newspaperPageURL.openConnection();

		// Get an input stream reader to url connection
		InputStreamReader io = new InputStreamReader(conn.getInputStream(),
				charset);

		// Clean HTML taken from simple string, file, URL, input stream,
		// input source or reader. Result is root node of created
		// tree-like structure. Single cleaner instance may be safely used
		// multiple times.
		TagNode node = cleaner.clean(io);
		return node;
	}
	
	/**
	 * Parses given root tag node using attributes of the html elements
	 * 
	 * @param node root tag node to be parsed
	 * 
	 * @return whether or not the operation is succeeded
	 * 
	 * @throws XPatherException 
	 */
	protected boolean parseUsingElementAttributes(TagNode node) throws XPatherException {
		return false;
	}
	
	/**
	 * Parses given root tag node using XPATH
	 * 
	 * @param node root tag node to be parsed
	 * 
	 * @return whether or not the operation is succeeded
	 * 
	 * @throws XPatherException 
	 */
	protected boolean parseUsingXPath(TagNode node) throws XPatherException {
		return false;
	}

	/**
	 * Parses using newspaperRssURL rss source
	 * 
	 * @return whether or not the operation is succeeded
	 */
	protected boolean parseUsingRss(){
		return false;
	}
	
	/**
	 * Parses the page belongs to the columns
	 * 
	 * @return whether or not the operation is succeeded
	 */
	protected boolean parseInnerPage(String innerPageUrl){
		return false;
	}
	
	/**
	 * Retrieves the latest data from database
	 * @return
	 */
	protected boolean getLatestFromDatabase() {
		columnistList = ColumnistHibernate.findColumnistsByNewspaper(newspaperEnum.getName(), null, null);
		columnList = ColumnHibernate.findLatestColumnsByNewspaper(newspaperEnum.getName());
		return columnistList!=null && columnList!=null ;
	}
	
	/** **************************************************** */
	/** HELPER METHODS */
	/** **************************************************** */	
	
	/**
	 * Creates instances of columnists and columns
	 * 
	 * @param sequence
	 * @param summary
	 * @param hyperlink
	 * @param title
	 * @param date
	 * @param columnistName
	 */
	protected void createResultedInstances(int sequence, String summary,
			String hyperlink, String title, Date date, String columnistName, String category, String imageSrc) {
		// Create
		Columnist theColumnist = createColumnistInstanceIfRequired(columnistName, newspaper,category, imageSrc, null);
		Column theColumn = createColumnInstanceIfRequired(sequence, summary.trim(), theColumnist, hyperlink, EntityUtil.getStandardizedText(title), date);

		// Log
		logger.debug(theColumnist.toString());
		logger.debug(theColumn.toString());
	}
	
	/**
	 * Creates a columnist instance using the specified fields if
	 *  a columnist with the given name does not exist
	 * 
	 * @param name
	 * @param newspaper
	 * @param category
	 * @param latestColumn
	 * 
	 * @return the newly created or existing columnist instance
	 */
	private Columnist createColumnistInstanceIfRequired(String name, Newspaper newspaper,
			String category, String imageSrc, Column latestColumn) {
		
		String nameCap = EntityUtil.getFullyCapitalizedText(name);
		String newspaperCap = EntityUtil.getFullyCapitalizedText(newspaper.getName());
		String categoryCap = EntityUtil.getFullyCapitalizedText(category);
		String nameTag = EntityUtil.getCleanText(name);
		
		Columnist theColumnist = checkExistingColumnist(nameCap,newspaperCap);
		if (theColumnist == null) {
			theColumnist = new Columnist(newspaperCap, nameCap, nameTag, imageSrc, categoryCap, newspaper, latestColumn);
			ColumnistHibernate.create(theColumnist);
		} else {
			Columnist columnistToUpdate = new Columnist(newspaperCap, nameCap, nameTag, imageSrc, categoryCap, newspaper, latestColumn);
			columnistToUpdate.setId(theColumnist.getId());
			ColumnistHibernate.update(columnistToUpdate);
			logger.info("Columnist " + theColumnist.getName() + " already exists. Updated.");
			
			theColumnist=null;
			theColumnist=columnistToUpdate;
		}
		columnistList.add(theColumnist);
		return theColumnist;
	
	}
	
	/**
	 * Creates a column instance using the specified fields
	 * 
	 * @param sequence
	 * @param summary
	 * @param columnist
	 * @param hyperlink
	 * @param title
	 * @param date
	 * 
	 * @return the newly created or existing column instance
	 */
	private Column createColumnInstanceIfRequired(int sequence, String summary,
			Columnist columnist, String hyperlink, String title, Date date) {
		
		if(!hyperlink.contains("http") && !hyperlink.contains("HTTP")){
			hyperlink = getRootDomainAddress() + hyperlink;
		}
		
		Column theColumn = checkExistingColumn(hyperlink);
		if (theColumn == null) {
			Integer count = checkExistingColumnCount(columnist, date);
			theColumn = new Column(columnist, date, count+1, title,
					summary.trim(), hyperlink);
			ColumnHibernate.create(theColumn);
		} else {
			Column theColumnToUpdate = new Column(columnist, theColumn.getDate(), theColumn.getSequence(), title,
					summary.trim(), hyperlink);
			theColumnToUpdate.setId(theColumn.getId());
			ColumnHibernate.update(theColumnToUpdate);
			logger.info("Column " + theColumn.getTitle() + " already exists. Updated.");
			
			theColumn=null;
			theColumn=theColumnToUpdate;
		}
		
		if( -1*(theColumn.getDate().getTime()-startOfToday.getTime())<1000){
			columnList.add(theColumn);
		}
		return theColumn;
	}


	/**
	 * Check if a columnist already exist with the given name
	 * 
	 * @param columnistName
	 *            Name of the columnist to be checked
	 *            
	 * @param newspaper
	 *            Name of the newspaper to be checked
	 *             
	 * @return the existing columnist instance or null
	 */
	protected Columnist checkExistingColumnist(String columnistName, String newspaper) {
		Columnist columnist = ColumnistHibernate.findColumnistByNameAndNewspaper(columnistName, newspaper);
		return columnist;
	}

	/**
	 * Check if a column already exist with the given hyperlink
	 * 
	 * @param hyperlink
	 * 
	 * @return
	 */
	protected Column checkExistingColumn(String hyperlink) {
		Column column = ColumnHibernate.findColumnByHyperlink(hyperlink);
		return column;
	}	
	
	private Integer checkExistingColumnCount(Columnist columnist, Date date) {
		Integer countResult = ColumnHibernate.findColumnsCountByColumnistAndDate(columnist,date);
		return countResult;
	}


	/** **************************************************** */
	/** MODIFIER AND ACCESSOR METHODS */
	/** **************************************************** */
	
	public NewspaperEnum getNewspaperEnum() {
		return newspaperEnum;
	}

	public void setNewspaperEnum(NewspaperEnum newspaperEnum) {
		this.newspaperEnum = newspaperEnum;
	}

	public URL getNewspaperPageURL() {
		return newspaperPageURL;
	}
	
	protected void setNewspaperPageURL(String url) {
		if(url==null) return;
		try {
			newspaperPageURL =  new URL(url);
		} catch (MalformedURLException e) {
			logger.error(newspaperEnum.getName() + " url " + url + " exception : " + e );
		}
	}
	
	public URL getNewspaperRssURL() {
		return newspaperRssURL;
	}

	public void setNewspaperRssURL(String url) {
		if(url==null) return;
		try {
			newspaperRssURL =  new URL(url);
		} catch (MalformedURLException e) {
			logger.error(newspaperEnum.getName() + " rss url " + url + " exception : " + e );
		}
	}
	
	public String getNewspaperName() {
		return newspaperEnum.getName();
	}

	public String getRootDomainAddress() {
		return rootDomainAddress;
	}

	protected void setRootDomainAddress(String rootDomainAddress) {
		this.rootDomainAddress = rootDomainAddress;
	}

	public boolean isInnerParseRequired() {
		return innerParseRequired;
	}

	protected void setInnerParseRequired(boolean innerParseRequired) {
		this.innerParseRequired = innerParseRequired;
	}

	public String getCharset() {
		return charset;
	}

	protected void setCharset(String charset) {
		this.charset = charset;
	}

	public List<Column> getColumnList() {
		return columnList;
	}


}
