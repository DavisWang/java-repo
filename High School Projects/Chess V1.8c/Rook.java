/** The "Rook" class.
 * The Rook object used in the game of chess
 * finished: moving and taking
 * to do: castling
 * version 1.0
 * By Davis Wang (that's right!!)
 **/

import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
public class Rook extends Pieces //P=NP
{
    /**
     * The constructor of a rook object
     * @param
     * newX, the x coordinate of the piece
     * newY, the y coordinate of the piece
     * newImage, the image of the piece
     * board, the GamePanel object
     * newcolorOfPiece, the color of the piece
     **/
    public Rook (int newX, int newY, Image newImage, GamePanel board, boolean colorOfPiece)
    {
	x = newX;
	y = newY;
	this.board = board;
	image = newImage;
	this.colorOfPiece = colorOfPiece;
    }


    /**
     * This method covers all of the standard rook capturing and moving rules
     * @param
     * mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal, these four parameters stores the initial positions and final positions of the piece
     **/
    public boolean legalMove (int mouseXInitial, int mouseYInitial, int mouseXFinal, int mouseYFinal)
    {
	if (colorOfPiece == true) //white piece moves
	{
	    if (mouseXInitial == mouseXFinal) //horizontal moves
	    {
		if (mouseYInitial > mouseYFinal) //moving up
		{
		    for (int a = mouseYInitial - 1 ; a > mouseYFinal ; a--)
		    {
			if (board.pieceFound (mouseXInitial, a) != 0)  //if there is a piece between the rook and the place it wants to move to
			    return false;
		    }
		    if (board.pieceFound (mouseXFinal, mouseYFinal) != 1) //if there isnt a friendly piece at the destination
		    {
			return true;
		    }
		}
		if (mouseYInitial < mouseYFinal) //moving down
		{
		    for (int a = mouseYInitial + 1 ; a < mouseYFinal ; a++)
		    {
			if (board.pieceFound (mouseXInitial, a) != 0)  //if there is a piece between the rook and the place it wants to move to
			    return false;
		    }
		    if (board.pieceFound (mouseXFinal, mouseYFinal) != 1) //if there isnt a friendly piece at the destination
		    {
			return true;
		    }
		}

	    }
	    if (mouseYInitial == mouseYFinal) //vertical moves
	    {
		if (mouseXInitial > mouseXFinal) //left
		{
		    for (int a = mouseXInitial - 1 ; a > mouseXFinal ; a--)
		    {
			if (board.pieceFound (a, mouseYInitial) != 0)  //if there is a piece between the rook and the place it wants to move to
			    return false;
		    }
		    if (board.pieceFound (mouseXFinal, mouseYFinal) != 1) //if there isnt a friendly piece at the destination
		    {
			return true;
		    }
		}
		if (mouseXInitial < mouseXFinal) //right
		{
		    for (int a = mouseXInitial + 1 ; a < mouseXFinal ; a++)
		    {
			if (board.pieceFound (a, mouseYInitial) != 0)  //if there is a piece between the rook and the place it wants to move to
			    return false;
		    }
		    if (board.pieceFound (mouseXFinal, mouseYFinal) != 1) //if there isnt a friendly piece at the destination
		    {
			return true;
		    }
		}
	    }
	}

	if (colorOfPiece == false) //black moves
	{
	    if (mouseXInitial == mouseXFinal) //horizontal moves
	    {
		if (mouseYInitial > mouseYFinal) //up
		{
		    for (int a = mouseYInitial - 1 ; a > mouseYFinal ; a--)
		    {
			if (board.pieceFound (mouseXInitial, a) != 0)  //if there is a piece between the rook and the place it wants to move to
			    return false;
		    }
		    if (board.pieceFound (mouseXFinal, mouseYFinal) != 2) //if there isnt a friendly piece at the destination
		    {
			return true;
		    }
		}
		if (mouseYInitial < mouseYFinal) //down
		{
		    for (int a = mouseYInitial + 1 ; a < mouseYFinal ; a++)
		    {
			if (board.pieceFound (mouseXInitial, a) != 0)  //if there is a piece between the rook and the place it wants to move to
			    return false;
		    }
		    if (board.pieceFound (mouseXFinal, mouseYFinal) != 2) //if there isnt a friendly piece at the destination
		    {
			return true;
		    }
		}

	    }
	    if (mouseYInitial == mouseYFinal) //vertical moves
	    {
		if (mouseXInitial > mouseXFinal) //left
		{
		    for (int a = mouseXInitial - 1 ; a > mouseXFinal ; a--)
		    {
			if (board.pieceFound (a, mouseYInitial) != 0)  //if there is a piece between the rook and the place it wants to move to
			    return false;
		    }
		    if (board.pieceFound (mouseXFinal, mouseYFinal) != 2) //if there isnt a friendly piece at the destination
		    {
			return true;
		    }
		}
		if (mouseXInitial < mouseXFinal) //right
		{
		    for (int a = mouseXInitial + 1 ; a < mouseXFinal ; a++)
		    {
			if (board.pieceFound (a, mouseYInitial) != 0)  //if there is a piece between the rook and the place it wants to move to
			    return false;
		    }
		    if (board.pieceFound (mouseXFinal, mouseYFinal) != 2) //if there isnt a friendly piece at the destination
		    {
			return true;
		    }
		}
	    }
	}
	return false;
    }


    /**
     * this method draws the rook
     * @param g, the Graphics context
     **/
    public void draw (Graphics g)
    {
	if (x >= 0 & y >= 0) //if the piece is actually on the board
	    g.drawImage (image, x, y, 50, 50, null);
    }
} // Rook class


