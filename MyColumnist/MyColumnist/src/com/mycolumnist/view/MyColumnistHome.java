package com.mycolumnist.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.mycolumnist.common.NewspaperEnum;
import com.mycolumnist.model.parser.base.HtmlPageParserBase;
import com.mycolumnist.model.parser.base.ParserVariables;
import com.mycolumnist.model.persistence.entity.Column;
import com.mycolumnist.model.persistence.entity.Columnist;
import com.mycolumnist.model.persistence.entity.Newspaper;
import com.mycolumnist.model.persistence.hibernate.ColumnHibernate;
import com.mycolumnist.model.persistence.hibernate.ColumnistHibernate;
import com.mycolumnist.model.persistence.hibernate.NewspaperHibernate;

public class MyColumnistHome {

	private static Logger logger = Logger.getLogger(MyColumnistHome.class);
	
	HtmlPageParserBase milliyetColumnistParser;
	HtmlPageParserBase hurriyetColumnistParser;
	HtmlPageParserBase zamanColumnistParser;
	HtmlPageParserBase sabahColumnistParser;
	HtmlPageParserBase radikalColumnistParser;
	HtmlPageParserBase postaColumnistParser;
	HtmlPageParserBase haberturkColumnistParser;
	HtmlPageParserBase takvimColumnistParser;
	HtmlPageParserBase fanatikColumnistParser;
	HtmlPageParserBase vatanColumnistParser;
	
	private String searchText;
	private List<Newspaper> newspapers;
	private List<Column> columnSearchResult;
	private List<Columnist> columnistSearchResult;
	
	private Columnist selectedColumnist;
	private List<Column> selectedColumns;
	
	boolean searchMode = false;
	boolean columnistSelectMode = false;
	
	public MyColumnistHome(){
		
		if(!ParserVariables.isParsInitialized()){
			logger.info("First time Parse");
			ParserVariables.parseAll();
		}
		
		milliyetColumnistParser = ParserVariables.getMilliyetColumnistParser();
		hurriyetColumnistParser = ParserVariables.getHurriyetColumnistParser();
		zamanColumnistParser = ParserVariables.getZamanColumnistParser();
		sabahColumnistParser = ParserVariables.getSabahColumnistParser();
		radikalColumnistParser = ParserVariables.getRadikalColumnistParser();
		postaColumnistParser = ParserVariables.getPostaColumnistParser();
		haberturkColumnistParser = ParserVariables.getHaberturkColumnistParser();
		takvimColumnistParser = ParserVariables.getTakvimColumnistParser();
		fanatikColumnistParser = ParserVariables.getFanatikColumnistParser();
		vatanColumnistParser = ParserVariables.getVatanColumnistParser();
		
		searchText="";
		columnSearchResult = new ArrayList<Column>();
		columnistSearchResult = new ArrayList<Columnist>();
		
		newspapers = NewspaperHibernate.findAll();
	}
	
	
	/** **************************************************** */
	/** COMMAND(S) */
	/** **************************************************** */
	
	public void searchCommand(){
		
		searchMode=true;
		
		if(searchText.trim().length()<3){
			return;
		}
		
		columnSearchResult = ColumnHibernate.findColumnsByTitle(searchText);
		columnistSearchResult = ColumnistHibernate.findColumnistsByName(searchText);
		
	}
	
	public void refreshSelectedColumns(){
		FacesContext context = FacesContext.getCurrentInstance();
		String value = context.getExternalContext().getRequestParameterValuesMap().get("columnistIdSelected")[0];
		if(value!=null){
			Long columnistId = Long.valueOf(value);
			
			selectedColumns = ColumnHibernate.findColumnsByColumnistId(columnistId,0,20);
			selectedColumnist = selectedColumns.get(0).getColumnist();
			
		}
		columnistSelectMode=true;
	}
	
	public int getActiveTab(){
		
		if(searchMode){
			return 2;
		} else if(columnistSelectMode){
			return 3;
		} else {
			return 0;
		}
	}
	/** **************************************************** */
	/** MODIFIER AND ACCESSOR METHODS */
	/** **************************************************** */
	
	public Columnist getSelectedColumnist() {
		return selectedColumnist;
	}
	
	public List<Column> getSelectedColumns() {
		return selectedColumns;
	}
	
	public boolean isColumnistSelectMode() {
		return columnistSelectMode;
	}
	
	public boolean isSearchMode() {
		return searchMode;
	}

	public List<Newspaper> getNewspapers() {
		return newspapers;
	}
	
	public NewspaperEnum getSabahEnum(){
		return NewspaperEnum.SABAH;
	}
	
	public NewspaperEnum getHaberturkEnum(){
		return NewspaperEnum.HABERTURK;
	}
	
	public NewspaperEnum getZamanEnum(){
		return NewspaperEnum.ZAMAN;
	}
	
	public NewspaperEnum getTakvimEnum(){
		return NewspaperEnum.TAKVIM;
	}
	
	public NewspaperEnum getFanatikEnum(){
		return NewspaperEnum.FANATIK;
	}
	
	public NewspaperEnum getPostaEnum(){
		return NewspaperEnum.POSTA;
	}
	
	public NewspaperEnum getVatanEnum(){
		return NewspaperEnum.VATAN;
	}
	
	public NewspaperEnum getRadikalEnum(){
		return NewspaperEnum.RADIKAL;
	}
	
	public NewspaperEnum getMilliyetEnum(){
		return NewspaperEnum.MILLIYET;
	}
	
	public NewspaperEnum getHurriyetEnum(){
		return NewspaperEnum.HURRIYET;
	}
	
	public NewspaperEnum [] getNewspaperEnumList(){
		return NewspaperEnum.values();
	}
	
	public List<Column> getMilliyetColumnList(){
		return milliyetColumnistParser.getColumnList();
	}
	
	public List<Column> getHurriyetColumnList(){
		return hurriyetColumnistParser.getColumnList();
	}
	
	public List<Column> getZamanColumnList(){
		return zamanColumnistParser.getColumnList();
	}
	
	public List<Column> getSabahColumnList(){
		return sabahColumnistParser.getColumnList();
	}
	
	public List<Column> getRadikalColumnList(){
		return radikalColumnistParser.getColumnList();
	}
		
	public List<Column> getPostaColumnList(){
		return postaColumnistParser.getColumnList();
	}
	
	public List<Column> getHaberturkColumnList(){
		return haberturkColumnistParser.getColumnList();
	}
	
	public List<Column> getTakvimColumnList(){
		return takvimColumnistParser.getColumnList();
	}
	
	public List<Column> getFanatikColumnList(){
		return fanatikColumnistParser.getColumnList();
	}
	
	public List<Column> getVatanColumnList(){
		return vatanColumnistParser.getColumnList();
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<Column> getColumnSearchResult() {
		return columnSearchResult;
	}

	public void setColumnSearchResult(List<Column> columnSearchResult) {
		this.columnSearchResult = columnSearchResult;
	}

	public List<Columnist> getColumnistSearchResult() {
		return columnistSearchResult;
	}

	public void setColumnistSearchResult(List<Columnist> columnistSearchResult) {
		this.columnistSearchResult = columnistSearchResult;
	}
}
