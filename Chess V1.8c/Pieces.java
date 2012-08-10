/**
 * The superclass for all the pieces objects
 * this class defines the variables common to all pieces objects
 * June 12 2009
 * @by Davis Wang
 **/

import java.awt.*;

public abstract class Pieces
{
    protected int x, y; //the x and y of the piece
    protected Image image; //the image of the piece
    GamePanel board = null; //the GamePanel object needed by every piece for the methods and the Pieces array
    boolean colorOfPiece; //true = white, false = black;
    int moveCounter; //this is only used by pawn, rook and king.

    /**
     * The abstract method for legalMove
     * in each individual piece object a set of rules are developed that fits the chess rules
     * and the method returns true or false based on the parameters passed in
     * @param
     * mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal, these four parameters stores the initial positions and final positions of the piece
     **/
    abstract public boolean legalMove (int mouseXInitial, int mouseYInitial, int mouseXFinal, int mouseYFinal);

    /**
     * This method sets the x position
     * @param
     *  newX, the new x position of that piece
     **/
    public void setXPosition (int newX)
    {
	x = newX;
    }


    /**
     * This method sets the y position
     * @param
     *  newY, the new y position of that piece
     **/
    public void setYPosition (int newY)
    {
	y = newY;
    }


    /**
     * This method gets the X position of that piece
     **/
    public int getXPosition ()
    {
	return x;
    }


    /**
     * This method gets the Y position of that piece
     **/
    public int getYPosition ()
    {
	return y;
    }


    /**
     * The abstract draw method
     * @param
     *  g, the Graphics context
     **/
    abstract public void draw (Graphics g);

}
