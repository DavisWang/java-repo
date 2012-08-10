/** The "Queen" class.
 * The Queen object for chess
 * Finished: tout le monde
 * To do: nada
 * ctrl-v and bishop and rook class is my friend!
 * By Davis Wang
 **/
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
public class Queen extends Pieces
{
    int steps; //used for the bishop movements
    /**
     * The constructor of a queen object
     * @param
     * newX, the x coordinate of the piece
     * newY, the y coordinate of the piece
     * newImage, the image of the piece
     * board, the GamePanel object
     * newcolorOfPiece, the color of the piece
     **/
    public Queen (int newX, int newY, Image newImage, GamePanel board, boolean colorOfPiece)
    {

	x = newX;
	y = newY;
	this.board = board;
	image = newImage;
	this.colorOfPiece = colorOfPiece;
    }


    /**
     * This method covers all of the standard queen capturing and moving rules
     * @param
     * mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal, these four parameters stores the initial positions and final positions of the piece
     **/
    public boolean legalMove (int mouseXInitial, int mouseYInitial, int mouseXFinal, int mouseYFinal)
    {
	//for more detailed comments, refer to the rook and the bishop class, as the queen is just a copy and paste combination of both
	if ((mouseXInitial - mouseXFinal) != (mouseYInitial - mouseYFinal) & (mouseXInitial - mouseXFinal) != (mouseYFinal - mouseYInitial)) //rook moves for queen
	{
	    if (colorOfPiece == true)
	    {
		if (mouseXInitial == mouseXFinal)
		{
		    if (mouseYInitial > mouseYFinal)
		    {
			for (int a = mouseYInitial - 1 ; a > mouseYFinal ; a--)
			{
			    if (board.pieceFound (mouseXInitial, a) != 0)  //if there is a piece between the rook and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXFinal, mouseYFinal) != 1)
			{
			    return true;
			}
		    }
		    if (mouseYInitial < mouseYFinal)
		    {
			for (int a = mouseYInitial + 1 ; a < mouseYFinal ; a++)
			{
			    if (board.pieceFound (mouseXInitial, a) != 0)  //if there is a piece between the rook and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXFinal, mouseYFinal) != 1)
			{
			    return true;
			}
		    }

		}
		if (mouseYInitial == mouseYFinal)
		{
		    if (mouseXInitial > mouseXFinal)
		    {
			for (int a = mouseXInitial - 1 ; a > mouseXFinal ; a--)
			{
			    if (board.pieceFound (a, mouseYInitial) != 0)  //if there is a piece between the rook and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXFinal, mouseYFinal) != 1)
			{

			    return true;
			}
		    }
		    if (mouseXInitial < mouseXFinal)
		    {
			for (int a = mouseXInitial + 1 ; a < mouseXFinal ; a++)
			{
			    if (board.pieceFound (a, mouseYInitial) != 0)  //if there is a piece between the rook and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXFinal, mouseYFinal) != 1)
			{

			    return true;
			}
		    }
		}
	    }

	    if (colorOfPiece == false)
	    {
		if (mouseXInitial == mouseXFinal)
		{
		    if (mouseYInitial > mouseYFinal)
		    {
			for (int a = mouseYInitial - 1 ; a > mouseYFinal ; a--)
			{
			    if (board.pieceFound (mouseXInitial, a) != 0)  //if there is a piece between the rook and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXFinal, mouseYFinal) != 2)
			{

			    return true;
			}
		    }
		    if (mouseYInitial < mouseYFinal)
		    {
			for (int a = mouseYInitial + 1 ; a < mouseYFinal ; a++)
			{
			    if (board.pieceFound (mouseXInitial, a) != 0)  //if there is a piece between the rook and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXFinal, mouseYFinal) != 2)
			{

			    return true;
			}
		    }

		}
		if (mouseYInitial == mouseYFinal)
		{
		    if (mouseXInitial > mouseXFinal)
		    {
			for (int a = mouseXInitial - 1 ; a > mouseXFinal ; a--)
			{
			    if (board.pieceFound (a, mouseYInitial) != 0)  //if there is a piece between the rook and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXFinal, mouseYFinal) != 2)
			{

			    return true;
			}
		    }
		    if (mouseXInitial < mouseXFinal)
		    {
			for (int a = mouseXInitial + 1 ; a < mouseXFinal ; a++)
			{
			    if (board.pieceFound (a, mouseYInitial) != 0)  //if there is a piece between the rook and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXFinal, mouseYFinal) != 2)
			{

			    return true;
			}
		    }
		}
	    }

	}
	else //bishop moves for queen
	{
	    if (colorOfPiece == true)
	    {
		if (mouseXInitial < mouseXFinal) //moving from left to right
		{

		    if (mouseYInitial < mouseYFinal) //moving right down
		    {
			steps = mouseYFinal - mouseYInitial;
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial + a, mouseYInitial + a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial + steps, mouseYInitial + steps) != 1)
			{

			    return true;
			}
		    }
		    if (mouseYInitial > mouseYFinal) //moving right up
		    {
			steps = mouseYInitial - mouseYFinal;
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial + a, mouseYInitial - a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial + steps, mouseYInitial - steps) != 1)
			{

			    return true;
			}
		    }

		}
		if (mouseXInitial > mouseXFinal) //moving from left to right
		{
		    if (mouseYInitial < mouseYFinal) //moving left down
		    {
			steps = mouseYFinal - mouseYInitial;
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial - a, mouseYInitial + a) != 0)  //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial - steps, mouseYInitial + steps) != 1)
			{

			    return true;
			}
		    }
		    if (mouseYInitial > mouseYFinal) //moving left up
		    {
			steps = mouseYInitial - mouseYFinal;
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial - a, mouseYInitial - a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial - steps, mouseYInitial - steps) != 1)
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
			steps = mouseYFinal - mouseYInitial;
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial + a, mouseYInitial + a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial + steps, mouseYInitial + steps) != 2)
			{

			    return true;
			}
		    }
		    if (mouseYInitial > mouseYFinal) //moving right up
		    {
			steps = mouseYInitial - mouseYFinal;
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial + a, mouseYInitial - a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial + steps, mouseYInitial - steps) != 2)
			{

			    return true;
			}
		    }

		}
		if (mouseXInitial > mouseXFinal) //moving from left to right
		{
		    if (mouseYInitial < mouseYFinal) //moving left down
		    {
			steps = mouseYFinal - mouseYInitial;
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial - a, mouseYInitial + a) != 0)  //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial - steps, mouseYInitial + steps) != 2)
			{

			    return true;
			}
		    }
		    if (mouseYInitial > mouseYFinal) //moving left up
		    {
			steps = mouseYInitial - mouseYFinal;
			for (int a = 1 ; a < steps ; a++)
			{
			    if (board.pieceFound (mouseXInitial - a, mouseYInitial - a) != 0) //if there is a piece between the bishop and the place it wants to move to
				return false;
			}
			if (board.pieceFound (mouseXInitial - steps, mouseYInitial - steps) != 2)
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
     * This method draws the queen
     * @param
     *  g, the Graphics context
     **/
    public void draw (Graphics g)
    {
	if (x >= 0 & y >= 0) //if the piece is actually on the board
	    g.drawImage (image, x, y, 50, 50, null);
    }
} // Pawn class
