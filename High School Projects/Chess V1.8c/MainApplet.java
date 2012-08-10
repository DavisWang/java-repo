/**
 * The Applet that owns the GamePanel object, and creates the basic interface for the applet
 * June 12 2009
 *
 * @by Davis Wang
 **/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApplet extends JApplet
{
    // Place instance variables here
    JButton newGame; //new game button
    JLabel turn; //turn label
    JRadioButton b1, b2, b3; //board pattern radio buttons

    /**
     * Calls makeGUI() to create a new JPanel, then calls
     * setContentPane to put the JPanel in place on the JApplet
     *
     **/
    public void init ()
    {
	JPanel mainPanel = makeGUI ();
	setContentPane (mainPanel);
    } // init method


    /**
     * Creates the main GUI layout in a JPanel.
     *
     * @return A new JPanel with the layout for the JApplet's GUI.
     **/
    public JPanel makeGUI ()
    {
	// The default layout for a JPanel is a FlowLayout.
	JPanel newPanel = new JPanel (); //the main panel
	JPanel buttons = new JPanel (); //the panel at the bottom of the applet window
	JPanel statusBar = new JPanel (); //the panel at the right of the applet window
	JPanel canvas = new JPanel (); //the chessboard panel

	b1 = new JRadioButton ("Board 1", true); //default pattern
	b2 = new JRadioButton ("Board 2");
	b3 = new JRadioButton ("Board 3");
	ButtonGroup bg = new ButtonGroup ();
	bg.add (b1);
	bg.add (b2);
	bg.add (b3);

	//sets a bunch of layouts, and creates the GamePanel object
	newPanel.setLayout (new BoxLayout (newPanel, BoxLayout.X_AXIS));
	canvas.setLayout (new BoxLayout (canvas, BoxLayout.Y_AXIS));
	statusBar.setLayout (new BoxLayout (statusBar, BoxLayout.Y_AXIS));
	final GamePanel gp = new GamePanel (this);
	gp.setPreferredSize (new Dimension (500, 500));

	newPanel.add (canvas);
	newPanel.add (statusBar);

	canvas.setPreferredSize (new Dimension (500, 500));
	canvas.add (gp);
	canvas.add (buttons);


	buttons.add (Box.createVerticalGlue ());

	newGame = new JButton ("New Game");
	turn = new JLabel ("White's turn");
	statusBar.add (turn);
	statusBar.add (b1);
	statusBar.add (b2);
	statusBar.add (b3);
	buttons.add (newGame);

	//when new game is clicked, resets everything
	class newGame implements ActionListener
	{
	    public void actionPerformed (ActionEvent e)
	    {
		for (int a = 0 ; a < 9 ; a++)
		{
		    gp.wPieces [a] = new Pawn (a * 50 + 20, 70, gp.wPawn, gp, true);
		    gp.bPieces [a] = new Pawn (a * 50 + 20, 320, gp.bPawn, gp, false);
		}

		gp.wPieces [8] = new Rook (20, 20, gp.wRook, gp, true);
		gp.bPieces [8] = new Rook (20, 370, gp.bRook, gp, false);

		gp.wPieces [9] = new Rook (370, 20, gp.wRook, gp, true);
		gp.bPieces [9] = new Rook (370, 370, gp.bRook, gp, false);

		gp.wPieces [10] = new Knight (70, 20, gp.wKnight, gp, true);
		gp.bPieces [10] = new Knight (70, 370, gp.bKnight, gp, false);

		gp.wPieces [11] = new Knight (320, 20, gp.wKnight, gp, true);
		gp.bPieces [11] = new Knight (320, 370, gp.bKnight, gp, false);

		gp.wPieces [12] = new Bishop (20 + 100, 20, gp.wBishop, gp, true);
		gp.bPieces [12] = new Bishop (20 + 100, 370, gp.bBishop, gp, false);

		gp.wPieces [13] = new Bishop (370 - 100, 20, gp.wBishop, gp, true);
		gp.bPieces [13] = new Bishop (370 - 100, 370, gp.bBishop, gp, false);

		gp.wPieces [14] = new Queen (20 + 200, 20, gp.wQueen, gp, true);
		gp.bPieces [14] = new Queen (370 - 150, 370, gp.bQueen, gp, false);

		gp.wPieces [15] = new King (20 + 150, 20, gp.wKing, gp, true);
		gp.bPieces [15] = new King (370 - 200, 370, gp.bKing, gp, false);
		gp.turn = true;
		gp.checkmate = false;
		gp.bincheck = false;
		gp.wincheck = false;
		repaint ();
	    }
	}
	newGame.addActionListener (new newGame ());

	return newPanel;
    }
} // GUIClassTemplate class
