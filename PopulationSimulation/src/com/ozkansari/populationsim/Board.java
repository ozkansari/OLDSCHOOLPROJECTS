package com.ozkansari.populationsim;
import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 */

import java.awt.Font;

/**
 * 	@author Ozkan SARI
 *
 *	Board where 
 */
public class Board 
{
	// frame values where Board will be displayed
	private int x0, y0;
	private int windowHeight, windowWidth;
	
	// values to create board
	public static int cellSize;
	static int n_dim= SimulationConstants.GRID_DIMENSION;
	
	// board values consisting of N*N cells
	private Cell [][] board;

	/**
	 * @param x0
	 * @param y0
	 * @param windowHeight
	 * @param windowWidth
	 * @param cellSize
	 */
	public Board(int x0, int y0, int windowHeight, int windowWidth, int cellSize) 
	{
		this.x0 = x0;
		this.y0 = y0;
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		this.cellSize = cellSize;
		
		board = new Cell [n_dim][n_dim];
		
        initBoard( board );
	}

	/**
	 * 
	 */
	public void initBoard( Cell [][] boardRef ) 
	{
		for(int i=0; i<n_dim; ++i) {
            for(int j=0; j<n_dim; ++j){
            	boardRef[i][j] = new Cell();
            	boardRef[i][j].setEmptyCell(true);
            }
        }
	}
	
	/**
	 * 
	 */
	public void clearBoard(  ) 
	{
		for(int i=0; i<n_dim; ++i) {
            for(int j=0; j<n_dim; ++j){
            	board[i][j].setEmptyCell(true);
            }
        }
	}
	
	
	/**
	 * 
	 */
	public void copyBoard( Cell [][] newBoard ) 
	{
		for(int i=0; i<n_dim; ++i) {
            for(int j=0; j<n_dim; ++j){
            	board[i][j] = new Cell();
            	board[i][j].setEmptyCell( newBoard[i][j].isEmptyCell() );
            }
        }
	}
	
	/**
	 * @param g
	 */
	public void drawBoard(Graphics g) 
	{
        // clear the window
        g.setColor( Color.black );
        g.fillRect(0,0,windowWidth,windowHeight);
       
        // draw the vertical grid lines
        g.setColor( Color.gray );
        for( int i=1; i<n_dim; i++ ) {
        	g.drawLine(x0+cellSize*i , y0,x0+cellSize*i , y0+cellSize*n_dim );
        }
         
        // draw the horizontal grid lines 
        for( int i=1; i<n_dim; i++ ) {
        	g.drawLine(x0,y0+cellSize*i,x0+cellSize*n_dim,y0+cellSize*i);
        }
	}
	
	/**
	 * 
	 */
	public void drawPopulation( Graphics g )
	{
		int xPos, yPos;
		
        // Set the font and color for the marks on board
        g.setFont(new Font("TimeRoman",Font.BOLD,(int)(cellSize*1.25)));
        g.setColor(Color.green);
        
        // draw people on region
        for(int i=0;i<n_dim;++i){
            for(int j=0;j<n_dim;++j){
                if(board[i][j].emptyCell == false ) {  
                	xPos = x0 + j*cellSize;
                	yPos = y0 + i*cellSize;
                	g.drawString("O", xPos+2, yPos+cellSize-2);
                }
            }
        }
	}
	
	public void setSpecificCell( int x, int y, boolean emptyCellVal ) {
		
		board[x][y].setEmptyCell(emptyCellVal );
	}
	
	public boolean getSpecificCell( int x, int y ) {
		
		return board[x][y].isEmptyCell( );
	}
	
	public Cell[][] getBoard() {
		return board;
	}
	

} // -end-of-Board class-
