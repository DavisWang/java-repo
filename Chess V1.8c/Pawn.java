/** The "Pawn" class.
 * The Pawn Object used in chess
 * finished, taking and moving
 * to do: pawn promotion, en passant
 * version 1.0
 * By Davis Wang (that's right!!)
 **/

import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
public class Pawn extends Pieces
{
    /**
     * The constructor of a pawn object
     * @param
     * newX, the x coordinate of the piece
     * newY, the y coordinate of the piece
     * newImage, the image of the piece
     * board, the GamePanel object
     * newcolorOfPiece, the color of the piece
     **/
    public Pawn (int newX, int newY, Image newImage, GamePanel board, boolean newcolorOfPiece)
    {
	x = newX;
	y = newY;
	this.board = board;
	image = newImage;
	colorOfPiece = newcolorOfPiece;
    }


    /**
     * This method covers all of the standard pawn capturing and moving rules except en passant
     * @param
     * mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal, these four parameters stores the initial positions and final positions of the piece
     **/
    public boolean legalMove (int mouseXInitial, int mouseYInitial, int mouseXFinal, int mouseYFinal)
    {
	if (colorOfPiece == true) //white pawn moves
	{
	    //taking other pieces
	    if (mouseYInitial + 1 == mouseYFinal) //checks to see if its not moving back or sideways
	    {
		if (mouseXInitial + 1 == mouseXFinal) //taking pieces on the right
		{
		    if (board.pieceFound (mouseXFinal, mouseYFinal) == 2) //is there a black piece there?
		    {
			return true;
		    }
		}
		else if (mouseXInitial - 1 == mouseXFinal) //taking pieces on the left
		{
		    if (board.pieceFound (mouseXFinal, mouseYFinal) == 2) //is there a black piece there?
		    {
			return true;
		    }
		} //taking other pieces ends here

	    }
	    //first move of the pawn
	    //the below if statement checks to see if there are no pieces in the two squares directly ahead of the pawn and that the pawn has not moved, if so, then it is a valid move
	    if (mouseXInitial == mouseXFinal & mouseYInitial + 2 == mouseYFinal & moveCounter == 0 & board.pieceFound (mouseXFinal, mouseYInitial + 1) == 0 & board.pieceFound (mouseXFinal, mouseYFinal) == 0)
	    {
		return true;
	    }
	    //this if statement checks to see if there is a piece directly in front of the pawn, if not, then it is a valid move
	    if (mouseXInitial == mouseXFinal & mouseYInitial + 1 == mouseYFinal & board.pieceFound (mouseXFinal, mouseYFinal) == 0)
	    {
		return true;
	    }


	}
	else //black pawn moves
	{
	    //taking other pieces
	    if (mouseYInitial - 1 == mouseYFinal) //checks to see if its not moving back or sideways
	    {
		if (mouseXInitial + 1 == mouseXFinal) //taking pieces on the right
		{
		    if (board.pieceFound (mouseXFinal, mouseYFinal) == 1) //is there a white piece there?
		    {
			return true;
		    }
		}
		else if (mouseXInitial - 1 == mouseXFinal) //taking pieces on the left
		{
		    if (board.pieceFound (mouseXFinal, mouseYFinal) == 1) //is there a white piece there?
		    {
			return true;
		    }
		} //taking other pieces ends here

	    }
	    //first move of the pawn
	    //the below if statement checks to see if there are no pieces in the two squares directly ahead of the pawn and that the pawn has not moved, if so, then it is a valid move
	    if (mouseXInitial == mouseXFinal & mouseYInitial - 2 == mouseYFinal & moveCounter == 0 & board.pieceFound (mouseXFinal, mouseYInitial - 1) == 0 & board.pieceFound (mouseXFinal, mouseYFinal) == 0)
	    {
		return true;
	    }
	    //this if statement checks to see if there is a piece directly in front of the pawn, if not, then it is a valid move
	    if (mouseXInitial == mouseXFinal & mouseYInitial - 1 == mouseYFinal & board.pieceFound (mouseXFinal, mouseYFinal) == 0)
	    {
		return true;
	    }
	}
	return false; //not a legal move
    }


    /**
     * this method draws the rook
     * @param g, the Graphics context
     **/
    public void draw (Graphics g)
    {
	if (x >= 0 & y >= 0) //if the piece is actually on the board
	    g.drawImage (image, x, y, 50, 50, null); //draws the piece
    }
} // Pawn class


