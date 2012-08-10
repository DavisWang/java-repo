/**
 * This is the main JPanel, this class does the moving, checking for stalemates
 * checkmates, checks, does the pawn promotions, alternates the turns, etc...
 *
 * @By Davis Wang
 **/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements MouseListener //controlled using the mouse
{
    // Place instance variables here
    int mouseX = -1, mouseY = -1; //getting the mouse coordinates
    boolean black = false; //for drawing the board
    Image bRook, wRook, bPawn, wPawn, bKnight, wKnight, bBishop, wBishop, bQueen, wQueen, bKing, wKing; //images for the pieces
    boolean turn = true; //true = white turn, false = black turn
    int mouseXInitial = -1, mouseYInitial = -1, mouseXFinal = -1, mouseYFinal = -1; //for piece movement

    /**
     * 0-7 = pawn
     * 8-9 = rook
     * 10-11 = knight
     * 12-13 = bishop
     * 14 = queen
     * 15 = king
     **/
    int pieceNumber; //used after getting the piece selected, and the x y initial and final values, the value used is from the pieces array index
    int state = 0; //0 is waiting to be selected, 1 is waiting to be moved
    Pieces[] wPieces = new Pieces [16]; //The array used for all white pieces
    Pieces[] bPieces = new Pieces [16]; //The array used for all black pieces
    MainApplet z; //used in the constructor

    //3 board designs, 6 board colors, the extra two is the design of the current board.
    Color boarda = new Color (173, 181, 201);
    Color boardb = new Color (233, 236, 241);
    Color board1a = new Color (173, 181, 201);
    Color board1b = new Color (233, 236, 241);
    Color board2a = new Color (140, 97, 73);
    Color board2b = new Color (172, 192, 215);
    Color board3b = new Color (248, 167, 111);
    Color board3a = new Color (138, 70, 51);

    int checkpiecenumber; //used when performing moves while in check
    boolean checkmate; //true is checkmate, false is not checkmate
    boolean checkingForCheckmate; //while this is true, it wont display the "In check" message, this is only true when calling checkmate() and set to false right after
    boolean bincheck; //whether the black king is in check at the moment, used only for stalemate checking
    boolean wincheck; //whehter the white king is in check, used only for stalemate checking

    /**
     * This is the constructor
     * This method initiates the pieces to their original position, and loads the images.
     * @param
     * z, this MainApplet variable is used for the getImage methods, and other applet methods
     **/
    public GamePanel (MainApplet z)  //passes an Applet object in so it can load the images
    {
	this.z = z; //Gets the variable z passed into this costructor to be set to the one owned by this class
	addMouseListener (this); //enable mouse input

	//gets all images
	wPawn = z.getImage (z.getCodeBase (), "images/wp.gif");
	wQueen = z.getImage (z.getCodeBase (), "images/wq.gif");
	wKing = z.getImage (z.getCodeBase (), "images/wk.gif");
	wBishop = z.getImage (z.getCodeBase (), "images/wb.gif");
	wKnight = z.getImage (z.getCodeBase (), "images/wn.gif");
	wRook = z.getImage (z.getCodeBase (), "images/wr.gif");

	bPawn = z.getImage (z.getCodeBase (), "images/bp.gif");
	bQueen = z.getImage (z.getCodeBase (), "images/bq.gif");
	bKing = z.getImage (z.getCodeBase (), "images/bk.gif");
	bBishop = z.getImage (z.getCodeBase (), "images/bb.gif");
	bKnight = z.getImage (z.getCodeBase (), "images/bn.gif");
	bRook = z.getImage (z.getCodeBase (), "images/br.gif");

	//initiates all images, all pieces, and their properties
	for (int a = 0 ; a < 9 ; a++)
	{
	    wPieces [a] = new Pawn (a * 50 + 20, 70, wPawn, this, true);
	    bPieces [a] = new Pawn (a * 50 + 20, 320, bPawn, this, false);
	}

	wPieces [8] = new Rook (20, 20, wRook, this, true);
	bPieces [8] = new Rook (20, 370, bRook, this, false);

	wPieces [9] = new Rook (370, 20, wRook, this, true);
	bPieces [9] = new Rook (370, 370, bRook, this, false);

	wPieces [10] = new Knight (70, 20, wKnight, this, true);
	bPieces [10] = new Knight (70, 370, bKnight, this, false);

	wPieces [11] = new Knight (320, 20, wKnight, this, true);
	bPieces [11] = new Knight (320, 370, bKnight, this, false);

	wPieces [12] = new Bishop (20 + 100, 20, wBishop, this, true);
	bPieces [12] = new Bishop (20 + 100, 370, bBishop, this, false);

	wPieces [13] = new Bishop (370 - 100, 20, wBishop, this, true);
	bPieces [13] = new Bishop (370 - 100, 370, bBishop, this, false);

	wPieces [14] = new Queen (20 + 200, 20, wQueen, this, true);
	bPieces [14] = new Queen (370 - 150, 370, bQueen, this, false);

	wPieces [15] = new King (20 + 150, 20, wKing, this, true);
	bPieces [15] = new King (370 - 200, 370, bKing, this, false);
    } // init method


    /**
     * This method gets the mouse coordinates
     * @param
     * e, the mouse event handler
     **/
    public void mouseClicked (MouseEvent e)
    {
	mouseX = e.getX (); //gets the mouse position
	mouseY = e.getY (); //gets the mouse position

	if (checkmate == false) //if the game is not over
	    move (); //calls move method
	repaint (); //repaints
    }


    /**
     * @ param e, Unused
     **/
    public void mousePressed (MouseEvent e)
    {
    }


    /**
     * @ param e, Unused
     **/
    public void mouseEntered (MouseEvent e)
    {
    }


    /**
     * @ param e, Unused
     **/
    public void mouseReleased (MouseEvent e)
    {
    }


    /**
     * @ param e, Unused
     **/
    public void mouseExited (MouseEvent e)
    {
    }


    /**
     * This method takes care of pawn promotions
     **/
    public void pawnPromotion ()
    {
	int x; //stores the x coordinate of the pawn piece, as the y coordinate is always 7 or 0
	JPanel window = new JPanel ();
	window.setLayout (new FlowLayout ());
	JRadioButton queen = new JRadioButton ("Promote to queen");
	JRadioButton rook = new JRadioButton ("Promote to rook");
	JRadioButton knight = new JRadioButton ("Promote to knight");
	JRadioButton bishop = new JRadioButton ("Promote to bishop");
	ButtonGroup bg = new ButtonGroup ();
	bg.add (queen); //adds the radio button to the button group
	bg.add (rook); //adds the radio button to the button group
	bg.add (knight); //adds the radio button to the button group
	bg.add (bishop); //adds the radio button to the button group
	window.add (queen); //adds the radio button to the window
	window.add (rook); //adds the radio button to the window
	window.add (knight); //adds the radio button to the window
	window.add (bishop); //adds the radio button to the window
	for (int a = 0 ; a < 8 ; a++) //loops through the pawn pieces
	{
	    if (wPieces [a].getYPosition () == rowColToXY (7) & wPieces [a] instanceof Pawn) //if a white pawn has reached the other side of the board
	    {
		x = wPieces [a].getXPosition (); //gets the x position, as the y position is either 7 or 0
		JOptionPane.showMessageDialog (null, window);
		if (queen.isSelected ())
		    wPieces [a] = new Queen (x, rowColToXY (7), wQueen, this, true); //overwrites the current piece to queen
		else if (rook.isSelected ())
		    wPieces [a] = new Rook (x, rowColToXY (7), wRook, this, true); //overwrites the current piece to rook
		else if (knight.isSelected ())
		    wPieces [a] = new Knight (x, rowColToXY (7), wKnight, this, true); //overwrites the current piece to knight
		else if (bishop.isSelected ())
		    wPieces [a] = new Bishop (x, rowColToXY (7), wBishop, this, true); //overwrites the current piece to bishop
	    }
	    else if (bPieces [a].getYPosition () == rowColToXY (0) & bPieces [a] instanceof Pawn) //if a black pawn has reached the other side of the board
	    {
		x = bPieces [a].getXPosition ();
		JOptionPane.showMessageDialog (null, window);
		if (queen.isSelected ())
		    bPieces [a] = new Queen (x, rowColToXY (0), bQueen, this, false); //overwrites the current piece to queen
		else if (rook.isSelected ())
		    bPieces [a] = new Rook (x, rowColToXY (0), bRook, this, false); //overwrites the current piece to rook
		else if (knight.isSelected ())
		    bPieces [a] = new Knight (x, rowColToXY (0), bKnight, this, false); //overwrites the current piece to knight
		else if (bishop.isSelected ())
		    bPieces [a] = new Bishop (x, rowColToXY (0), bBishop, this, false); //overwrites the current piece to bishop
	    }
	}


    }


    /**
     * The check method, checks to see if either king is threatened after each move
     * @param
     * turn, whose turn it is
     * pieceNumber, each piece is given a number that identifies that piece, this variable stores that number
     * mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal, these four variables stores the coordinate of where the player wants to move
     *
     **/
    public boolean check (boolean turn, int pieceNumber, int mouseXInitial, int mouseYInitial, int mouseXFinal, int mouseYFinal)
    {

	if (turn == false) //if this current turn is black's turn
	{
	    deletePiece (mouseXFinal, mouseYFinal); //deletes the piece at where the black piece wants to go
	    bPieces [pieceNumber].setXPosition (20 + mouseXFinal * 50); //moves the black piece there
	    bPieces [pieceNumber].setYPosition (20 + mouseYFinal * 50);
	    for (int a = 0 ; a < 16 ; a++)  //cycles through all the pieces
	    {
		if (xyToRowCol (wPieces [a].getXPosition ()) != -1 & xyToRowCol (wPieces [a].getYPosition ()) != -1) //makes sure pieces that are taken cannot check
		{
		    if (wPieces [a].legalMove (xyToRowCol (wPieces [a].getXPosition ()), xyToRowCol (wPieces [a].getYPosition ()), xyToRowCol (bPieces [15].getXPosition ()), xyToRowCol (bPieces [15].getYPosition ()))) //if any white piece can take the king after black moves
		    {
			//the try catch block moves the deleted piece back to its original position based on checkpiecenumber acquired when calling the deletePiece method
			try
			{
			    wPieces [checkpiecenumber].setXPosition (20 + mouseXFinal * 50);
			    wPieces [checkpiecenumber].setYPosition (20 + mouseYFinal * 50);
			}
			catch (Exception e)
			{
			}

			bPieces [pieceNumber].setXPosition (20 + mouseXInitial * 50); //moves the black piece back to where it is
			bPieces [pieceNumber].setYPosition (20 + mouseYInitial * 50);
			if (checkingForCheckmate == false) //if not checking for checkmate
			{
			    JOptionPane.showMessageDialog (z, "Black in check.");
			}
			return false; //cannot move
		    }
		}
	    }
	    //moves the white piece back, needed here again because the black move might be legal or not, but in either case, the white piece is restored to its original position
	    try
	    {
		wPieces [checkpiecenumber].setXPosition (20 + mouseXFinal * 50);
		wPieces [checkpiecenumber].setYPosition (20 + mouseYFinal * 50);
	    }
	    catch (Exception e)
	    {
	    }
	    //same case here, the black piece is move back to its original position
	    bPieces [pieceNumber].setXPosition (20 + mouseXInitial * 50);
	    bPieces [pieceNumber].setYPosition (20 + mouseYInitial * 50);
	}

	else if (turn == true) //if this current turn is white's turn
	{
	    deletePiece (mouseXFinal, mouseYFinal); //deletes the piece
	    wPieces [pieceNumber].setXPosition (20 + mouseXFinal * 50); //moves the piece there
	    wPieces [pieceNumber].setYPosition (20 + mouseYFinal * 50);
	    for (int a = 0 ; a < 16 ; a++)  //cycles through all the pieces
	    {
		if (xyToRowCol (bPieces [a].getXPosition ()) != -1 & xyToRowCol (bPieces [a].getYPosition ()) != -1) //makes sure pieces that are taken cannot check
		{

		    if (bPieces [a].legalMove (xyToRowCol (bPieces [a].getXPosition ()), xyToRowCol (bPieces [a].getYPosition ()), xyToRowCol (wPieces [15].getXPosition ()), xyToRowCol (wPieces [15].getYPosition ()))) //if any black piece can take the white king
		    {
			//move the black piece back
			try
			{
			    bPieces [checkpiecenumber].setXPosition (20 + mouseXFinal * 50);
			    bPieces [checkpiecenumber].setYPosition (20 + mouseYFinal * 50);
			}
			catch (Exception e)
			{
			}
			//move the white piece back
			wPieces [pieceNumber].setXPosition (20 + mouseXInitial * 50);
			wPieces [pieceNumber].setYPosition (20 + mouseYInitial * 50);
			if (checkingForCheckmate == false) //if not checking for checkmate
			{
			    JOptionPane.showMessageDialog (z, "White in check.");
			}
			return false; //cannot move this
		    }
		}
	    }
	    //move the black piece back
	    try
	    {
		bPieces [checkpiecenumber].setXPosition (20 + mouseXFinal * 50);
		bPieces [checkpiecenumber].setYPosition (20 + mouseYFinal * 50);
	    }
	    catch (Exception e)
	    {
	    }
	    //move the white piece back.
	    wPieces [pieceNumber].setXPosition (20 + mouseXInitial * 50);
	    wPieces [pieceNumber].setYPosition (20 + mouseYInitial * 50);
	}
	return true; //can move
    }


    /**
     * this method checks to see if there is a legal move for the player in check, if not return true, and displays a message
     * otherwise, return false
     *
     **/
    public boolean checkmate (boolean turn)
    {
	try
	{
	    if (turn == false) //checks to see if black has any legal moves
	    {
		for (int a = 0 ; a < 16 ; a++) //cycles through all the pieces
		{
		    for (int x = 0 ; x < 8 ; x++) //the below code cycles through all the board positions.
		    {
			for (int y = 0 ; y < 8 ; y++)
			{
			    if (xyToRowCol (bPieces [a].getXPosition ()) != -1 & xyToRowCol (bPieces [a].getYPosition ()) != -1) //make sure pieces that are taken cannot do anything
			    {
				if (bPieces [a].legalMove (xyToRowCol (bPieces [a].getXPosition ()), xyToRowCol (bPieces [a].getYPosition ()), x, y) == true) //if that move with that piece and that coordinate is legal
				{
				    if (check (false, a, xyToRowCol (bPieces [a].getXPosition ()), xyToRowCol (bPieces [a].getYPosition ()), x, y) == true) //if moving there will not result in a threatened king
				    {
					return false; //not checkmate yet
				    }
				}
			    }
			}
		    }
		}
		//detecting stalemate, this code is only executed when black has no legal moves left, then the loop checks to see if a white piece
		//is threatening the black king, if so, then it is checkmate, if not then it is stalemate
		for (int a = 0 ; a < 16 ; a++)
		{
		    //note: this if statement is the extra line I added in 1.8c to prevent pieces that are already taken to threaten the king, I have it in the check method already, but I forgot to add it here
		    if (xyToRowCol (wPieces [a].getXPosition ()) != -1 & xyToRowCol (wPieces [a].getYPosition ()) != -1)  //make sure pieces that are taken cannot do anything
		    {
			if (wPieces [a].legalMove (xyToRowCol (wPieces [a].getXPosition ()), xyToRowCol (wPieces [a].getYPosition ()), xyToRowCol (bPieces [15].getXPosition ()), xyToRowCol (bPieces [15].getYPosition ())))
			{
			    bincheck = true; //black king in check
			}
		    }
		}

	    }
	    else if (turn == true) //checks to see if white has any legal moves
	    {
		for (int a = 0 ; a < 16 ; a++) //cycles through all the pieces
		{
		    for (int x = 0 ; x < 8 ; x++) //the below code cycles through all the board positions.
		    {
			for (int y = 0 ; y < 8 ; y++)
			{
			    if (xyToRowCol (wPieces [a].getXPosition ()) != -1 & xyToRowCol (wPieces [a].getYPosition ()) != -1) //make sure pieces that are taken cannot do anything
			    {
				if (wPieces [a].legalMove (xyToRowCol (wPieces [a].getXPosition ()), xyToRowCol (wPieces [a].getYPosition ()), x, y) == true) //if that move with that piece and that coordinate is legal
				{
				    if (check (true, a, xyToRowCol (wPieces [a].getXPosition ()), xyToRowCol (wPieces [a].getYPosition ()), x, y) == true) //if moving there will not result in a threatened king
				    {
					return false; //not checkmate yet.
				    }
				}
			    }
			}
		    }
		}
		//detecting stalemate, this code is only executed when white has no legal moves left, then the loop checks to see if a black piece
		//is threatening the white king, if so, then it is checkmate, if not then it is stalemate
		for (int a = 0 ; a < 16 ; a++)
		{ //note: this if statement is the extra line I added in 1.8c to prevent pieces that are already taken to threaten the king, I have it in the check method already, but I forgot to add it here
		    if (xyToRowCol (bPieces [a].getXPosition ()) != -1 & xyToRowCol (bPieces [a].getYPosition ()) != -1)  //make sure pieces that are taken cannot do anything
		    {
			if (bPieces [a].legalMove (xyToRowCol (bPieces [a].getXPosition ()), xyToRowCol (bPieces [a].getYPosition ()), xyToRowCol (wPieces [15].getXPosition ()), xyToRowCol (wPieces [15].getYPosition ()))) //to see if any black piece is threatening the white king
			{
			    wincheck = true; //white king is threatened
			}
		    }
		}
	    }

	}

	catch (Exception e)
	{
	}
	return true; //not checkmate yet
    }




    /**
     * This method converts a point on the board to row or column.
     * @param
     * mouse, this variable stores the mouse coordinates
     **/
    public int xyToRowCol (int mouse)
    {
	if (mouse >= 20 & mouse < 70)
	    return 0;
	else if (mouse >= 70 & mouse < 120)
	    return 1;
	else if (mouse >= 120 & mouse < 170)
	    return 2;
	else if (mouse >= 170 & mouse < 220)
	    return 3;
	else if (mouse >= 220 & mouse < 270)
	    return 4;
	else if (mouse >= 270 & mouse < 320)
	    return 5;
	else if (mouse >= 320 & mouse < 370)
	    return 6;
	else if (mouse >= 370 & mouse < 420)
	    return 7;
	else
	    return -1;

    }


    /**
     * This method converts the row and col to the upper left corner of that specific row or col
     * @param
     * rowCol, the row and col variable used in the method
     **/
    public int rowColToXY (int rowCol)
    {
	return 20 + rowCol * 50; //returns the converted value
    }


    /**
     * This method draws the pieces of the two Pieces arrays.
     * @param
     * g, the graphics component
     **/
    public void drawPieces (Graphics g)
    {
	for (int a = 0 ; a < 16 ; a++)
	{
	    try
	    {
		wPieces [a].draw (g); //draws pieces
		bPieces [a].draw (g); //draws pieces
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    /**
     * This method sets the position of the objects to -1 and -1
     * since there is an if statement in the draw method of the pieces
     * the pieces won't draw itself if its position is -1 and -1
     * @param
     * row, the row of the piece object
     * col, the col of the piece object
     **/
    public void deletePiece (int row, int col)
    {
	try
	{
	    for (int a = 0 ; a < 16 ; a++) //loops through all the pieces
	    {
		if (xyToRowCol (wPieces [a].getXPosition ()) == row & xyToRowCol (wPieces [a].getYPosition ()) == col) //if a white piece has the specified position passed into this method
		{
		    checkpiecenumber = a; //used to perform moves in the check method
		    wPieces [a].setXPosition (-1); //set that piece to somewhere not accessable using the mouse
		    wPieces [a].setYPosition (-1); //set that piece to somewhere not accessable using the mouse
		    return; //stop and return
		}
		else if (xyToRowCol (bPieces [a].getXPosition ()) == row & xyToRowCol (bPieces [a].getYPosition ()) == col) //if a black piece has the specified position passed into this method
		{
		    checkpiecenumber = a; //used to perform moves in the check method
		    bPieces [a].setXPosition (-1); //set that piece to somewhere not accessable using the mouse
		    bPieces [a].setYPosition (-1); //set that piece to somewhere not accessable using the mouse
		    return; //stop and return
		}
	    }
	    checkpiecenumber = -1; //no piece has those coordinates.
	}
	catch (Exception e)
	{
	}
    }


    /**
     * This method checks the row and col of every piece and compares it to the parameters passed into this method
     * the method returns a value based on what it found
     * 0 is not found, 1 is white, 2 is black
     * @param
     * row, the row variable
     * col, the col variable
     **/
    public int pieceFound (int row, int col)
    {
	for (int a = 0 ; a < 16 ; a++)
	{
	    if (xyToRowCol (wPieces [a].getXPosition ()) == row & xyToRowCol (wPieces [a].getYPosition ()) == col) //does a white piece have that row and column?
		return 1;
	    else if (xyToRowCol (bPieces [a].getXPosition ()) == row & xyToRowCol (bPieces [a].getYPosition ()) == col) //does a black piece have that row and column?
		return 2;
	}
	return 0; //no piece at that row and column.
    }


    /**
     * This method handles turns, and interpreting the mouse inputs
     * After getting inputs, it checks to see what piece is selected
     * And then it calles that piece's legalMove method, if it's true,
     * then move the piece and make changes to the board.
     **/
    public void move ()
    {
	if (state == 0) //if pieces are waiting to be clicked on in order to be moved
	{
	    pieceNumber = -1; //the piece identifier gets set to -1
	    if (xyToRowCol (mouseX) >= 0 & xyToRowCol (mouseY) >= 0 & pieceFound (xyToRowCol (mouseX), xyToRowCol (mouseY)) != 0) //if the user clicked on a piece on the board
	    {
		mouseXInitial = xyToRowCol (mouseX); //gets the piece's location
		mouseYInitial = xyToRowCol (mouseY); //gets the piece's location
		for (int a = 0 ; a < 16 ; a++) //loops through all the pieces
		{
		    if (wPieces [a].getXPosition () == rowColToXY (xyToRowCol (mouseX)) & wPieces [a].getYPosition () == rowColToXY (xyToRowCol (mouseY)))
			pieceNumber = a; //if a white piece is at that pisotion, set pieceNumber to that piece's identifier number
		    else if (bPieces [a].getXPosition () == rowColToXY (xyToRowCol (mouseX)) & bPieces [a].getYPosition () == rowColToXY (xyToRowCol (mouseY)))
			pieceNumber = a; //if a black piece is at that pisotion, set pieceNumber to that piece's identifier number
		    state = 1; //ready to be moved now
		}
		z.repaint (); //repaint
	    }
	}

	else
	{
	    if (xyToRowCol (mouseX) != -1 & xyToRowCol (mouseY) != -1) //if the user has clicked on the board somewhere
	    {
		mouseXFinal = xyToRowCol (mouseX); //gets the square position of where the user wants to move
		mouseYFinal = xyToRowCol (mouseY);
		state = 0; //back to waiting to be clicked on
	    }
	}

	if (state == 0 & mouseXFinal >= 0 & mouseYFinal >= 0 & mouseXInitial >= 0 & mouseYInitial >= 0 & pieceNumber != -1) //if the program has everything needed to perform a move
	{
	    if (xyToRowCol (wPieces [pieceNumber].getXPosition ()) == mouseXInitial & xyToRowCol (wPieces [pieceNumber].getYPosition ()) == mouseYInitial & turn == true) //determines the piece to be moved
	    {
		if (wPieces [pieceNumber].legalMove (mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal)) //if that specific piece can move there based on chess rules
		{
		    if (check (turn, pieceNumber, mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal)) //if moving there will not result in a threatened king
		    {
			deletePiece (mouseXFinal, mouseYFinal); //delete the piece at where the piece is moving
			wPieces [pieceNumber].setXPosition (rowColToXY (mouseXFinal)); //move it there
			wPieces [pieceNumber].setYPosition (rowColToXY (mouseYFinal));
			wPieces [pieceNumber].moveCounter++; //move counter is only used for pawn, rook and kings.
			pawnPromotion (); //does the pawn promotion, if applicable
			turn = false; //other player's turn now
			z.turn.setText ("Black's turn"); //make the label display that
			checkingForCheckmate = true; //now, check to see if the other player has any legal moves
			if (checkmate (false) == true) //checkmate, the game has ended
			{
			    checkmate = true; //no more moving allowed
			    if (bincheck == true) //if it is checkmate
				JOptionPane.showMessageDialog (z, "White has won!");
			    else //if it is stalemate
				JOptionPane.showMessageDialog (z, "Stalemate!");
			}
			checkingForCheckmate = false; //not checking for checkmate anymore
		    }
		}
	    }
	    //for comments on the below code, it is simply the black side equivalent of the comments above
	    else if (xyToRowCol (bPieces [pieceNumber].getXPosition ()) == mouseXInitial & xyToRowCol (bPieces [pieceNumber].getYPosition ()) == mouseYInitial & turn == false)
	    {
		if (bPieces [pieceNumber].legalMove (mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal))
		{
		    if (check (turn, pieceNumber, mouseXInitial, mouseYInitial, mouseXFinal, mouseYFinal))
		    {
			deletePiece (mouseXFinal, mouseYFinal);
			bPieces [pieceNumber].setXPosition (rowColToXY (mouseXFinal));
			bPieces [pieceNumber].setYPosition (rowColToXY (mouseYFinal));
			bPieces [pieceNumber].moveCounter++;
			pawnPromotion ();
			turn = true;
			z.turn.setText ("White's turn");
			checkingForCheckmate = true;
			if (checkmate (true) == true)
			{
			    checkmate = true;
			    if (wincheck == true)
				JOptionPane.showMessageDialog (z, "Black has won!");
			    else
				JOptionPane.showMessageDialog (z, "Stalemate!");
			}
			checkingForCheckmate = false;
		    }
		}
	    }

	    if (checkmate == false) //if the game isn't over
	    {
		state = 0; //pieces are ready to be selected again
		mouseXInitial = -1; //reset the inputs
		mouseYInitial = -1;
		mouseXFinal = -1;
		mouseYFinal = -1;
	    }
	}
    }


    public void paintComponent (Graphics g)
    {
	super.paintComponent (g);
	//draws the board and pieces

	if (z.b1.isSelected ()) //board pattern 1
	{
	    boarda = board1a;
	    boardb = board1b;
	}


	else if (z.b2.isSelected ()) //board pattern 2
	{
	    boarda = board2a;
	    boardb = board2b;
	}


	else if (z.b3.isSelected ()) //board pattern 3
	{
	    boarda = board3a;
	    boardb = board3b;

	}


	z.repaint (); //repaints

	//this code draws the board
	for (int a = 0 ; a < 8 ; a++)
	{
	    for (int b = 0 ; b < 8 ; b++)
	    {
		if (black)
		    g.setColor (boarda);
		else
		    g.setColor (boardb);
		g.fillRect (20 + a * 50, 20 + b * 50, 50, 50);
		black = !black;
	    }
	    black = !black;
	}

	drawPieces (g); //draws all the pieces based on their position

	// the below code draws the green outline when the user clicks on their own piece
	if (state == 1 & turn == true & pieceFound (mouseXInitial, mouseYInitial) == 1 | state == 1 & turn == false & pieceFound (mouseXInitial, mouseYInitial) == 2) //draws the green outline
	{
	    for (int a = 0 ; a < 8 ; a++)
	    {
		for (int b = 0 ; b < 8 ; b++)
		{
		    if (xyToRowCol (mouseX) == a & xyToRowCol (mouseY) == b)
		    {
			g.setColor (new Color (0, 64, 0));
			g.drawRect (20 + a * 50, 20 + b * 50, 50, 50);
			g.setColor (new Color (0, 128, 0));
			g.drawRect (20 + a * 50 + 1, 20 + b * 50 + 1, 48, 48);
			g.setColor (new Color (0, 255, 0));
			g.drawRect (20 + a * 50 + 2, 20 + b * 50 + 2, 46, 46);
			g.setColor (new Color (0, 128, 0));
			g.drawRect (20 + a * 50 + 3, 20 + b * 50 + 3, 44, 44);
			g.setColor (new Color (0, 64, 0));
			g.drawRect (20 + a * 50 + 4, 20 + b * 50 + 4, 42, 42);
		    }
		}
	    }
	}

	//the below shows where you can go with the pieces
	//note: the below code is commented because it does not work well with castling
	// if (pieceNumber >= 0)
	// {
	//     g.setColor (Color.green);
	//     if (turn == true)
	//     {
	//         for (int a = 0 ; a < 8 ; a++)
	//         {
	//             for (int b = 0 ; b < 8 ; b++)
	//             {
	//                 if (wPieces [pieceNumber].legalMove (mouseXInitial, mouseYInitial, a, b))
	//                     g.fillOval (rowColToXY (a) + 15, rowColToXY (b) + 15, 10, 10);
	//             }
	//         }
	//     }
	//     else if (turn == false)
	//     {
	//         for (int a = 0 ; a < 8 ; a++)
	//         {
	//             for (int b = 0 ; b < 8 ; b++)
	//             {
	//                 if (bPieces [pieceNumber].legalMove (mouseXInitial, mouseYInitial, a, b))
	//                     g.fillOval (rowColToXY (a) + 15, rowColToXY (b) + 15, 10, 10);
	//             }
	//         }
	//     }
	//
	// }
    } // paint method
}


