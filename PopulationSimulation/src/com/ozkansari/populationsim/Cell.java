package com.ozkansari.populationsim;
/**
 * 
 */

/**
 * 	@author Ozkan SARI
 *
 *	symbolizes one Cell in the board
 */
public class Cell 
{
	int id;
	boolean emptyCell;
	int x_coord;	// not used for now
	int y_coord;	// not used for now
	
	public Cell()
	{
		emptyCell = true;
	}

	public Cell(boolean emptyCell, int x_coord, int y_coord) 
	{
		this.emptyCell = emptyCell;
		this.x_coord = x_coord;
		this.y_coord = y_coord;
	}

	// SETTERS AND GETTERS -----------------------------------------------//
	
	/**
	 * @return the emptyCell
	 */
	public boolean isEmptyCell() {
		return emptyCell;
	}

	/**
	 * @param emptyCell the emptyCell to set
	 */
	public void setEmptyCell(boolean emptyCell) {
		this.emptyCell = emptyCell;
	}

	/**
	 * @return the x_coord
	 */
	public int getX_coord() {
		return x_coord;
	}

	/**
	 * @param x_coord the x_coord to set
	 */
	public void setX_coord(int x_coord) {
		this.x_coord = x_coord;
	}

	/**
	 * @return the y_coord
	 */
	public int getY_coord() {
		return y_coord;
	}

	/**
	 * @param y_coord the y_coord to set
	 */
	public void setY_coord(int y_coord) {
		this.y_coord = y_coord;
	}
	
}
