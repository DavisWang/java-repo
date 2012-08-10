/** The "King" class.
 * The king object, the main part of this class is the legalMove method
 * which determines whether a king move is a legal move by the chess rules
 * June 12 2009
 * @by Davis Wang
 **/
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
public class King extends Pieces
{
    /**
     * The constructor of a king object
     * @param
     * newX, the x coordinate of the piece
     * newY, the y coordinate of the piece
     * newImage, the image of the piece
     * board, the GamePanel object
     * newcolorOfPiece, the color of the piece
     **/
    public King (int newX, int newY, Image newImage, GamePanel board, boolean colorOfPiece)
    {
	x = newX;
	y = newY;
	this.board = board;
	image = newImage;
	this.colorOfPiece = colorOfPiece;
    }


    /**
     * This method covers all of the standard king capturing and moving rules
     * @param
     * mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal, these four parameters stores the initial positions and final positions of the piece
     **/
    public boolean legalMove (int mouseXInitial, int mouseYInitial, int mouseXFinal, int mouseYFinal)
    {
	//longest if statement!!!
	//checks to see it the position the king wants to move to is within one square (diagonal, vertically or horizontally)
	if (mouseXInitial + 1 == mouseXFinal & mouseYInitial + 1 == mouseYFinal | mouseXInitial - 1 == mouseXFinal & mouseYInitial - 1 == mouseYFinal | mouseXInitial - 1 == mouseXFinal & mouseYInitial == mouseYFinal | mouseXInitial - 1 == mouseXFinal & mouseYInitial + 1 == mouseYFinal | mouseXInitial == mouseXFinal & mouseYInitial - 1 == mouseYFinal | mouseXInitial == mouseXFinal & mouseYInitial == mouseYFinal | mouseXInitial == mouseXFinal & mouseYInitial + 1 == mouseYFinal | mouseXInitial + 1 == mouseXFinal & mouseYInitial == mouseYFinal | mouseXInitial + 1 == mouseXFinal & mouseYInitial - 1 == mouseYFinal)
	{
	    if (colorOfPiece == true) //white moves
	    {
		if (board.pieceFound (mouseXFinal, mouseYFinal) != 1) //there isn't a friendly piece at where the king wants to move
		{
		    return true;
		}
	    }
	    else //black moves
	    {
		if (board.pieceFound (mouseXFinal, mouseYFinal) != 2) //there isn't a friendly piece at where the king wants to move
		{
		    return true;
		}
	    }

	}
	else //castling moves
	{
	    if (colorOfPiece == true) //white king
	    {
		if (moveCounter == 0 & board.wPieces [8].moveCounter == 0 & mouseXInitial == 3 & mouseYInitial == 0 & mouseXFinal == 1 & mouseYFinal == 0 & board.pieceFound (2, 0) == 0 & board.pieceFound (1, 0) == 0) //kingside castle
		{
		    if (board.checkingForCheckmate == false) //if not checking for checkmates
		    { //move the rook
			board.wPieces [8].setXPosition (20 + 2 * 50);
			board.wPieces [8].setYPosition (20);
		    }
		    return true;
		}
		else if (moveCounter == 0 & board.wPieces [9].moveCounter == 0 & mouseXInitial == 3 & mouseYInitial == 0 & mouseXFinal == 5 & mouseYFinal == 0 & board.pieceFound (6, 0) == 0 & board.pieceFound (4, 0) == 0 & board.pieceFound (5, 0) == 0) //queenside castle
		{
		    if (board.checkingForCheckmate == false) //if not checking for checkmates
		    { //move the rook
			board.wPieces [9].setXPosition (20 + 4 * 50);
			board.wPieces [9].setYPosition (20);
		    }
		    return true;
		}
	    }

	    else
	    {
		if (moveCounter == 0 & board.bPieces [8].moveCounter == 0 & mouseXInitial == 3 & mouseYInitial == 7 & mouseXFinal == 1 & mouseYFinal == 7 & board.pieceFound (2, 7) == 0 & board.pieceFound (1, 7) == 0) //kingside castle
		{
		    if (board.checkingForCheckmate == false) //if not checking for checkmates
		    { //move the rook
			board.bPieces [8].setXPosition (20 + 2 * 50);
			board.bPieces [8].setYPosition (370);
		    }
		    return true;
		}
		else if (moveCounter == 0 & board.wPieces [9].moveCounter == 0 & mouseXInitial == 3 & mouseYInitial == 7 & mouseXFinal == 5 & mouseYFinal == 7 & board.pieceFound (6, 7) == 0 & board.pieceFound (4, 7) == 0 & board.pieceFound (5, 7) == 0) //queenside castle
		{
		    if (board.checkingForCheckmate == false) //if not checking for checkmates
		    { //move the rook
			board.bPieces [9].setXPosition (20 + 4 * 50);
			board.bPieces [9].setYPosition (20 + 7 * 50);
		    }
		    return true;
		}
	    }
	}
	return false;
    }


    /**
     * This method draws the king
     * @param
     *  g, the Graphics context
     **/
    public void draw (Graphics g)
    {
	g.drawImage (image, x, y, 50, 50, null);
    }
} // King class
