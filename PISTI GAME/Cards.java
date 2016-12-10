/*
 * Created on 09.May.2005
 *
 */

/**
 * PISTI GAME
 * 
 * @author Ozkan SARI
 *	ozkan_yellow@hotmail.com
 *
 */

/**  DEFINITION OF CLASS "Cards":
 *   
 */
public class Cards {

	private String rank;
	private String suit;
	boolean open;
	boolean pisti;
	
	
	/*  ***********************************************************************************************/
	/*	-- CONSTRUCTORS ------------------------------------------------------------------------------*/
	/*  ***********************************************************************************************/
	
	public Cards() 
	{
		rank ="" ; suit=""; open = false; pisti =false;
					
	} //-end 1st clone constructor of Cards
	
	/**
	 * @param String r, @param String s
	 */
	public Cards( String r, String s )
	{
		rank = r; suit=s;
	
	} //-end 2nd clone constructor of Cards
	
	/**
	 * @param Cards copy
	 */
	public Cards( Cards copy )
	{
		rank = copy.getRank(); suit=copy.getSuit();
	
	} //-end 3rd clone constructor of Cards
	
	
	/*  ***********************************************************************************************/
	/*	-- ACCESSORS ---------------------------------------------------------------------------------*/
	/*  ***********************************************************************************************/
	
	/**
	 * @return String
	 */
	public String getRank( ) 
	{
		return rank;
	} // -end function Cards.getRank():String-
	
	/**
	 * @return String
	 */
	public String getSuit( ) 
	{
		return suit;
	} // -end function Cards.getSuit:STring()-
	
	/**
	 * @param String
	 */
	public void setRank( String r ) 
	{
		rank = r;
	} // -end function Cards.setRank()-
	
	/**
	 * @param String s
	 */
	public void setSuit( String s ) 
	{
		suit = s;
	} // -end function Cards.setSuit()-
	
	public void setPisti() 
	{
		pisti = true;
	} // -end function Cards.setPisti()-
	
	
	/*  ***********************************************************************************************/
	/*	-- The "Cards" FUNCTIONS ---------------------------------------------------------------------*/
	/*  ***********************************************************************************************/
	
	// shuffle deck of cards 
	public void shuffle()
	{
		
	}

	/**
	 * @param Cards[] deck
	 * @return Cards[]
	 */
	public static Cards[] getRegularDeck( Cards deck[] ){
		
		String [] suits = { "s", "h", "d", "c" };
		deck = new Cards [52];
		
		// Initialize the 52 cards in the deck
		for(int j=0; j<4; j++ ) {
			int i;
			for(i=2; i<11; i++ ){
				deck[(i-2)+13*j] = new Cards( i+"" , suits[j]  );
			}
			i=i-3;
			deck[(++i)+13*j] = new Cards( "J" , suits[j] );
			deck[(++i)+13*j] = new Cards( "Q" , suits[j]  );
			deck[(++i)+13*j] = new Cards( "K" , suits[j]  );
			deck[(++i)+13*j] = new Cards( "A" , suits[j]  );
		}
		
		return deck;
			
	}

	public void changeCardStatus()
	{
		open = ( open ? false : true );
		
	} // -end function Cards.changeCardStatus()-
	
	/**
	 * @param Cards other
	 * @return boolean
	 */
	public boolean hasSameRank ( Cards other )
	{		
		if( this.rank.compareTo(other.rank)== 0 )
			return true;
		else 
			return false;
		
	} // -end function Cards.hasSameRank(Card):boolean-

	/**
	 * @param Cards other
	 * @return boolean
	 */
	public boolean isSame ( Cards other )
	{		
		if( this.rank.compareTo(other.rank)== 0 ){
			if( this.suit.compareTo(other.suit)== 0 )
				return true;
		} 
			
		return false;
		
	} // -end function Cards.hasSameRank(Card):boolean-
	
	/**
	 * @return boolean
	 */
	public boolean isJack ( )
	{		
		if( this.getRank().equalsIgnoreCase("J") )
			return true;
		else
			return false;
		
	} // -end function Cards.isJack():boolean-
	
	/**
	 * @return boolean
	 */
	public boolean isAce() 
	{
		return ( this.getRank().equalsIgnoreCase("A") ? true : false ); 	
	} // -end function Cards.isAce():boolean-
	
	/**
	 * @return boolean
	 */
	public boolean isPisti() 
	{
		return ( this.pisti ? true : false ); 
	} // -end function Cards.isPisti()):boolean-
	
	/**
	 * @return String
	 */
	public String toString( )
	{
		return ("" + rank + suit) ;
	}

	
} // -end "Cards" class -
