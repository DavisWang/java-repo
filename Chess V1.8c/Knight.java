/** The "Knight" class.
 * The Knight object for chess
 * finished: taking and moving
 * to do: nothing!!!
 * version 1.0
 * By Davis Wang (that's right!)
 **/
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
public class Knight extends Pieces
{
    /**
     * The constructor of a knight object
     * @param
     * newX, the x coordinate of the piece
     * newY, the y coordinate of the piece
     * newImage, the image of the piece
     * board, the GamePanel object
     * newcolorOfPiece, the color of the piece
     **/
    public Knight (int newX, int newY, Image newImage, GamePanel board, boolean colorOfPiece)
    {
	x = newX;
	y = newY;
	this.board = board;
	image = newImage;
	this.colorOfPiece = colorOfPiece;
    }


    /**
     * This method covers all of the standard knight capturing and moving rules
     * @param
     * mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal, these four parameters stores the initial positions and final positions of the piece
     **/
    public boolean legalMove (int mouseXInitial, int mouseYInitial, int mouseXFinal, int mouseYFinal)
    {
	//white moves
	if (colorOfPiece == true)
	{ //moving up right
	    if (mouseXFinal == mouseXInitial + 1 & mouseYFinal == mouseYInitial - 2 & board.pieceFound (mouseXFinal, mouseYFinal) != 1)
	    {
		return true;
	    }
	    //moving up left
	    else if (mouseXFinal == mouseXInitial - 1 & mouseYFinal == mouseYInitial - 2 & board.pieceFound (mouseXFinal, mouseYFinal) != 1)
	    {
		return true;
	    }
	    //moving down right
	    else if (mouseXFinal == mouseXInitial + 1 & mouseYFinal == mouseYInitial + 2 & board.pieceFound (mouseXFinal, mouseYFinal) != 1)
	    {
		return true;
	    }
	    //moving down left
	    else if (mouseXFinal == mouseXInitial - 1 & mouseYFinal == mouseYInitial + 2 & board.pieceFound (mouseXFinal, mouseYFinal) != 1)
	    {
		return true;
	    }
	    //moving left up
	    else if (mouseXFinal == mouseXInitial - 2 & mouseYFinal == mouseYInitial - 1 & board.pieceFound (mouseXFinal, mouseYFinal) != 1)
	    {
		return true;
	    }
	    //moving left down
	    else if (mouseXFinal == mouseXInitial - 2 & mouseYFinal == mouseYInitial + 1 & board.pieceFound (mouseXFinal, mouseYFinal) != 1)
	    {
		return true;
	    }
	    //moving right up
	    else if (mouseXFinal == mouseXInitial + 2 & mouseYFinal == mouseYInitial - 1 & board.pieceFound (mouseXFinal, mouseYFinal) != 1)
	    {
		return true;
	    }
	    //moving right down
	    else if (mouseXFinal == mouseXInitial + 2 & mouseYFinal == mouseYInitial + 1 & board.pieceFound (mouseXFinal, mouseYFinal) != 1)
	    {
		return true;
	    }
	}
	//black moves
	if (colorOfPiece == false)
	{
	    if (mouseXFinal == mouseXInitial + 1 & mouseYFinal == mouseYInitial - 2 & board.pieceFound (mouseXFinal, mouseYFinal) != 2)
	    {
		return true;
	    }
	    //moving up left
	    else if (mouseXFinal == mouseXInitial - 1 & mouseYFinal == mouseYInitial - 2 & board.pieceFound (mouseXFinal, mouseYFinal) != 2)
	    {
		return true;
	    }
	    //moving down right
	    else if (mouseXFinal == mouseXInitial + 1 & mouseYFinal == mouseYInitial + 2 & board.pieceFound (mouseXFinal, mouseYFinal) != 2)
	    {
		return true;
	    }
	    //moving down left
	    else if (mouseXFinal == mouseXInitial - 1 & mouseYFinal == mouseYInitial + 2 & board.pieceFound (mouseXFinal, mouseYFinal) != 2)
	    {
		return true;
	    }
	    //moving left up
	    else if (mouseXFinal == mouseXInitial - 2 & mouseYFinal == mouseYInitial - 1 & board.pieceFound (mouseXFinal, mouseYFinal) != 2)
	    {
		return true;
	    }
	    //moving left down
	    else if (mouseXFinal == mouseXInitial - 2 & mouseYFinal == mouseYInitial + 1 & board.pieceFound (mouseXFinal, mouseYFinal) != 2)
	    {
		return true;
	    }
	    //moving right up
	    else if (mouseXFinal == mouseXInitial + 2 & mouseYFinal == mouseYInitial - 1 & board.pieceFound (mouseXFinal, mouseYFinal) != 2)
	    {
		return true;
	    }
	    //moving right down
	    else if (mouseXFinal == mouseXInitial + 2 & mouseYFinal == mouseYInitial + 1 & board.pieceFound (mouseXFinal, mouseYFinal) != 2)
	    {
		return true;
	    }
	}
	return false; //cannot move
    }


    /**
     * This method draws the knight
     * @param
     *  g, the Graphics context
     **/
    public void draw (Graphics g)
    {
	if (x >= 0 & y >= 0) //if the piece is actually on the board
	    g.drawImage (image, x, y, 50, 50, null);
    }
} // Pawn class
