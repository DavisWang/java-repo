/** The "Bishop" class.
 * The bishop object for chess.
 * finished: everything
 * to do: nothing!!!
 * version 1.0
 * June 12 2009
 * @By Davis Wang
 **/
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
public class Bishop extends Pieces //a^n+b^n=c^n
{
    int steps; //for moving, the number of squares it is moving

    /**
     * The constructor of a bishop object
     * @param
     * newX, the x coordinate of the piece
     * newY, the y coordinate of the piece
     * newImage, the image of the piece
     * board, the GamePanel object
     * newcolorOfPiece, the color of the piece
     **/
    public Bishop (int newX, int newY, Image newImage, GamePanel board, boolean colorOfPiece)
    {
	x = newX;
	y = newY;
	this.board = board;
	image = newImage;
	this.colorOfPiece = colorOfPiece;
    }


    /**
     * This method covers all of the standard bishop capturing and moving rules
     * @param
     * mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal, these four parameters stores the initial positions and final positions of the piece
     **/
    public boolean legalMove (int mouseXInitial, int mouseYInitial, int mouseXFinal, int mouseYFinal)
    {
	if ((mouseXInitial - mouseXFinal) != (mouseYInitial - mouseYFinal) & (mouseXInitial - mouseXFinal) != (mouseYFinal - mouseYInitial)) //the difference between xfinal and xinitial must be the same as yfinal and yinitial for this to be a legal bishop move
	    return false;
	else
	{
	    if (colorOfPiece == true) //white moves
	    {
		if (mouseXInitial < mouseXFinal) //moving from left to right
		{
		    if (mouseYInitial < mouseYFinal) //moving right down
		    {
			steps = mouseYFinal - mouseYInitial; //how many squares
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial + a, mouseYInitial + a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial + steps, mouseYInitial + steps) != 1) //if there is no friendly piece at the destination
			{
			    return true;
			}
		    }
		    if (mouseYInitial > mouseYFinal) //moving right up
		    {
			steps = mouseYInitial - mouseYFinal; //how many squares
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial + a, mouseYInitial - a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial + steps, mouseYInitial - steps) != 1) //if there is no friendly piece at the destination
			{
			    return true;
			}
		    }

		}
		if (mouseXInitial > mouseXFinal) //moving from left to right
		{
		    if (mouseYInitial < mouseYFinal) //moving left down
		    {
			steps = mouseYFinal - mouseYInitial; //how many squares
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial - a, mouseYInitial + a) != 0)  //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial - steps, mouseYInitial + steps) != 1) //if there is no friendly piece at the destination
			{
			    return true;
			}
		    }
		    if (mouseYInitial > mouseYFinal) //moving left up
		    {
			steps = mouseYInitial - mouseYFinal; //how many squares
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial - a, mouseYInitial - a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial - steps, mouseYInitial - steps) != 1) //if there is no friendly piece at the destination
			{
			    return true;
			}
		    }

		}
	    }

	    if (colorOfPiece == false)
	    {
		if (mouseXInitial < mouseXFinal) //moving from left to right
		{

		    if (mouseYInitial < mouseYFinal) //moving right down
		    {
			steps = mouseYFinal - mouseYInitial; //how many squares
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial + a, mouseYInitial + a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial + steps, mouseYInitial + steps) != 2) //if there is no friendly piece at the destination
			{

			    return true;
			}
		    }
		    if (mouseYInitial > mouseYFinal) //moving right up
		    {
			steps = mouseYInitial - mouseYFinal; //how many squares
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial + a, mouseYInitial - a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial + steps, mouseYInitial - steps) != 2) //if there is no friendly piece at the destination
			{
			    return true;
			}
		    }

		}
		if (mouseXInitial > mouseXFinal) //moving from left to right
		{
		    if (mouseYInitial < mouseYFinal) //moving left down
		    {
			steps = mouseYFinal - mouseYInitial; //how many squares
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial - a, mouseYInitial + a) != 0)  //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial - steps, mouseYInitial + steps) != 2) //if there is no friendly piece at the destination
			{
			    return true;
			}
		    }
		    if (mouseYInitial > mouseYFinal) //moving left up
		    {
			steps = mouseYInitial - mouseYFinal; //how many squares
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial - a, mouseYInitial - a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial - steps, mouseYInitial - steps) != 2) //if there is no friendly piece at the destination
			{
			    return true;
			}
		    }
		}
	    }
	}
	return false;
    }


    /**
     * This method draws the bishop
     * @param
     *  g, the Graphics context
     **/
    public void draw (Graphics g)
    {
	if (x >= 0 & y >= 0) //if the piec3 is actually on th3 board
	    g.drawImage (image, x, y, 50, 50, null);
    }
} // Pawn class
