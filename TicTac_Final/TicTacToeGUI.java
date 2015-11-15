package tTT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TicTacToeGUI extends JFrame
{
	public static final int bSize = 5;
	public static final int rows = 4;
	public static final int winsL = (4*bSize*bSize - 4*(rows - 1)*bSize - 2*(rows - 1)*(bSize - (rows - 1)));
	private static final String TITLE="Tic Tac Toe";
	private static final int WIDTH=450;
	private static final int HEIGHT=600;
	private static final int MaxDepth = 2;
	private Container content;
	private JLabel result;
	private JButton[] cells;
	private JButton exitButton;
	private JButton initButton;
	private CellButtonHandler[] cellHandlers;
	private ExitButtonHandler exitHandler;
	private InitButtonHandler initHandler;
	private boolean noughts;
	private boolean gameOver;
	public int[][] wins = new int[winsL][rows];
	
	private ArrayList<Integer> noughtsArray = new ArrayList();
	private ArrayList<Integer> crossesArray = new ArrayList();
	private ArrayList<Integer> emptyArray = new ArrayList();
	public int scorish = 0;

	public TicTacToeGUI()
	{
		//Necessary initialization code
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Get content pane
		content=getContentPane();
		content.setBackground(Color.blue.darker());
		
		//Set layout
		content.setLayout(new GridLayout(bSize + 1,bSize));

		//Create cells and handlers
		cells=new TTTButton[bSize*bSize];
		cellHandlers=new CellButtonHandler[bSize*bSize];
		for(int i=0; i<bSize*bSize; i++)
		{
			char ch=(char)('0'+i+1);
			cells[i]=new TTTButton(""+ch, i);
			cellHandlers[i]=new CellButtonHandler();
			cells[i].addActionListener(cellHandlers[i]);
		}
		
		//Create init and exit buttons and handlers
		exitButton=new JButton("EXIT");
		exitHandler=new ExitButtonHandler();
		exitButton.addActionListener(exitHandler);
		initButton=new JButton("CLEAR");
		initHandler=new InitButtonHandler();
		initButton.addActionListener(initHandler);
		
		//Create result label
		result=new JLabel("Noughts", SwingConstants.CENTER);
		result.setForeground(Color.white);
						
		//Add elements to the grid content pane
		for(int i=0; i<bSize*bSize; i++)
		{
			content.add(cells[i]);
		}
		content.add(initButton);
		content.add(result);
		content.add(exitButton);
		
		//Initialize
		init();
	}
	public void makeWins()
	{
		/* wins[1][0] = 0;
		wins[1][1] = 1;
		wins[1][2] = 2;
		wins[1][3] = 3;
		wins[2][0] = 4;
		wins[2][1] = 1;
		wins[2][2] = 2;
		wins[2][3] = 3;
		wins[3][0] = 5;
		wins[3][2] = 6;
		wins[3][1] = 7;
		wins[3][3] = 8;
		wins[4][1] = 6;
		wins[4][2] = 7;
		wins[4][3] = 8;
		wins[4][0] = 9;
		wins[5][1] = 10;
		wins[5][2] = 11;
		wins[5][3] = 12;
		wins[5][0] = 13;
		wins[6][1] = 14;
		wins[6][2] = 11;
		wins[6][3] = 12;
		wins[6][0] = 13;
		wins[7][1] = 15;
		wins[7][2] = 16;
		wins[7][3] = 17;
		wins[7][0] = 18;
		wins[8][1] = 16;
		wins[8][2] = 17;
		wins[8][3] = 18;
		wins[8][0] = 19;
		wins[9][1] = 20;
		wins[9][2] = 21;
		wins[9][3] = 22;
		wins[9][0] = 23;
		wins[10][1] = 21;
		wins[10][2] = 22;
		wins[10][3] = 23;
		wins[10][0] = 24;
		wins[11][0] = 0;
		wins[11][1] = 5;
		wins[11][2] = 15;
		wins[11][3] = 10;
		wins[12][0] = 1;
		wins[12][1] = 6;
		wins[12][2] = 11;
		wins[12][3] = 16;
		wins[13][0] = 2;
		wins[13][2] = 7;
		wins[13][1] = 12;
		wins[13][3] = 17;
		wins[14][1] = 13;
		wins[14][2] = 18;
		wins[14][3] = 3;
		wins[14][0] = 8;
		wins[15][1] = 4;
		wins[15][2] = 9;
		wins[15][3] = 14;
		wins[15][0] = 19;
		wins[16][1] = 5;
		wins[16][2] = 10;
		wins[16][3] = 15;
		wins[16][0] = 20;
		wins[17][1] = 6;
		wins[17][2] = 16;
		wins[17][3] = 11;
		wins[17][0] = 21;
		wins[18][1] = 7;
		wins[18][2] = 17;
		wins[18][3] = 12;
		wins[18][0] = 22;
		wins[19][1] = 8;
		wins[19][2] = 13;
		wins[19][3] = 18;
		wins[19][0] = 23;
		wins[20][1] = 9;
		wins[20][2] = 14;
		wins[20][3] = 19;
		wins[20][0] = 24;
		wins[21][0] = 0;
		wins[21][1] = 6;
		wins[21][2] = 12;
		wins[21][3] = 18;
		wins[22][0] = 6;
		wins[22][1] = 12;
		wins[22][2] = 18;
		wins[22][3] = 24;
		wins[23][0] = 5;
		wins[23][2] = 11;
		wins[23][1] = 17;
		wins[23][3] = 23;
		wins[24][1] = 1;
		wins[24][2] = 7;
		wins[24][3] = 13;
		wins[24][0] = 19;
		wins[25][1] = 4;
		wins[25][2] = 8;
		wins[25][3] = 12;
		wins[25][0] = 16;
		wins[26][1] = 3;
		wins[26][2] = 7;
		wins[26][3] = 11;
		wins[26][0] = 15;
		wins[27][1] = 8;
		wins[27][2] = 12;
		wins[27][3] = 16;
		wins[27][0] = 20;
		wins[0][1] = 21;
		wins[0][2] = 17;
		wins[0][3] = 13;
		wins[0][0] = 9; */
		
		wins = perms(bSize, rows);
		for (int q = 0; q < winsL; q++) 
		{
			System.out.println("break");
			for (int p = 0; p < rows; p++)
			{
				System.out.println(wins[q][p]);
			}
		}
		for (int q = 0; q < bSize*bSize; q++) 
		{
			emptyArray.add(q);
		}
}
	
	public void init()
	{
		//Initialize booleans
		noughts=true;
		gameOver=false;
		
		//Initialize text in buttons
		for(int i=0; i<bSize*bSize; i++)
		{
			cells[i].setText(" ");
		}
		
		//Initialize result label
		result.setText("Noughts");
		
		setVisible(true);
		crossesArray.clear();
		noughtsArray.clear();
		makeWins();
	}
	public int[][] perms (int n, int m) 
	{
		int a = 0;
		int[][] arr = new int[10000][m];
		ArrayList<Integer> bob = new ArrayList<Integer>();
		for (int i = 0; i < n*n; i++)
		{
			if (i%n < n - (m - 1))
			{
				int b = 0;
				for (int j = 0; j < m; j++) {
				arr[a][j] = i + b;
				b++;
				/*arr[a][2] = i + 1;
				arr[a][3] = i + 2;
				arr[a][0] = i + 3; */
				}
				a++;
			}
		}
		for (int i = 0; i < n*n; i++)
		{
			if (i < n*n - n*(m - 1))
			{
				for (int j = 0; j < m; j++) {
				arr[a][j] = i + j*n;
				}
		//		arr[a][2] = i + n;
			//	arr[a][3] = i + 2*n;
				//arr[a][0] = i + 3*n;
				a++;
			}
		}
		for (int i = 0; i < n*n; i++)
		{
			if (((i - (m-1)*n) >= (m-1)) && (i%n >= (m-1)))
			{
				for (int j = 0; j < m; j++) {
					arr[a][j] = i - j*(n + 1);	
				}
//				arr[a][2] = i - n - 1;
	//			arr[a][3] = i - (2*(n + 1));
		//		arr[a][0] = i - (3*(n + 1));
				a++;
			}
		}
		for (int i = 0; i < n*n; i++)
		{
			if ((i - (m-1)*n >= 0) && i%n < n-(m-1))
			{
				for (int j = 0; j < m; j++)
				{
				arr[a][j] = i - j*(n - 1);
				}
	//			arr[a][2] = i - n + 1;
		//		arr[a][3] = i - 2*(n - 1);
			//	arr[a][0] = i - 3*(n - 1);
				a++;
			}
		}
	return arr;
	}

	
	public boolean checkWinner(ArrayList crosses, ArrayList naughts)
	{
		boolean hi = false;
		for (int i = 0; i < winsL; i++)
		{
			ArrayList<Integer> lst = new ArrayList<Integer>();
			for (int j = 0; j < rows; j++)
			{
				lst.add(wins[i][j]);
			}
			if ((crosses.containsAll(lst)) || (naughts.containsAll(lst))) {
				hi = true;
			}
		}
		
		return hi;
	}
	
	//computer needs to see in the future
	
	public void aiNext (int i) 
	{
		if (!noughts) 
		{
			crossesArray.add(i);
		} else {
			noughtsArray.add(i);
		}
		noughts = !noughts;
		emptyArray.remove(emptyArray.indexOf(i));
	}
	
	public void aiBack (int i) 
	{
		noughts = !noughts;
		if (!noughts) 
		{
			crossesArray.remove(crossesArray.indexOf(i));
		} else {
			noughtsArray.remove(noughtsArray.indexOf(i));
		}
		emptyArray.add(i);
	}
	
	public int analyze () 
	{
		int pScore = 0;
		for (int i = 0; i < winsL; i++)
		{
			ArrayList<Integer> os = new ArrayList<Integer>();
			ArrayList<Integer> xs = new ArrayList<Integer>();
			for (int f = 0; f < noughtsArray.size(); f++)
			{
				os.add(noughtsArray.get(f));
			}
			for (int g = 0; g < crossesArray.size(); g++)
			{
				xs.add(crossesArray.get(g));
			}
			int a = 0;
			int b = 0;
			for (int h = 0; h < rows; h++)
			{
				for (int l = 0; l < xs.size(); l++) {
					if (xs.get(l) == wins[i][h])
					{
						a++;
					}
				}
				for (int v = 0; v < os.size(); v++) {
					if (os.get(v) == wins[i][h])
					{
						b++;
					}
				}
			}
			if (a > 0 && b > 0) 
			{
				pScore += 0;
			} else if (a > 0) {
				pScore += Math.pow(25, a);
			} else if (b > 0) {
				pScore -= Math.pow(55, b);
			}
		}
		return pScore;
	}
	
	private int MaxMove (int depth)
	{
		ArrayList<Integer> moves = new ArrayList<Integer>(); 
		for (int x = 0; x < emptyArray.size(); x++)
		{
		moves.add(emptyArray.get(x));
		}
		
		if (moves.size() == 1)
		{
			return moves.get(0);
		} else {
			int bestMove = 0;
			int bestMoveValue = -1000000;
			for (int k = 0; k < moves.size(); k++) 
			{
				System.out.println("MaxMove# " + k);
				aiNext(moves.get(k));
				int minMove = (moves.get(k));
				
				if (depth < MaxDepth && !(checkWinner(noughtsArray, crossesArray))) {
					minMove = MinMove(depth + 1);
				}
				if (checkWinner(noughtsArray, crossesArray))
				{
					bestMoveValue = 1000000;
					bestMove = minMove;
					aiBack(moves.get(k));
					break;
				}
				int value;
				if (depth != MaxDepth) {
				value = scorish;
				} else {
				value = analyze();
				}
				if (value > bestMoveValue)
				{
					System.out.println("max Move " + minMove);
					System.out.println("max Value " + value);
					bestMoveValue = value;
					bestMove = minMove;
				}
				
				aiBack(moves.get(k));
			}
			scorish = bestMoveValue;
			return bestMove;
		}
	}
	
	private int MinMove (int depth)
	{
		ArrayList<Integer> moves = new ArrayList<Integer>(); 
		for (int x = 0; x < emptyArray.size(); x++)
		{
		moves.add(emptyArray.get(x));
		}
		if (moves.size() == 1)
		{
			return moves.get(0);
		} else {
			int bestMove = 0;
			int bestMoveValue = 1000000;
			for (int k = 0; k < moves.size(); k++) 
			{
				System.out.println("MinMove# " + k);
				aiNext(moves.get(k));
				int maxMove = (moves.get(k));
				if (depth < MaxDepth && !(checkWinner(noughtsArray, crossesArray))) {
					maxMove = MaxMove(depth + 1);
				}
				if (checkWinner(noughtsArray, crossesArray))
				{
					bestMoveValue = -1000000;
					bestMove = maxMove;
					aiBack(moves.get(k));
					break;
				}
				int value;
				if (depth != MaxDepth) {
			    value = scorish;
				} else {
			    value = analyze();
				}
				
				if (value < bestMoveValue)
				{
					System.out.println("min Move " + maxMove);
					System.out.println("min Value " + value);
					bestMoveValue = value;
					bestMove = maxMove;
				}
				
				aiBack(moves.get(k));
			}
			scorish = bestMoveValue;
			return bestMove;
		}
	}

	
	public static void main(String[] args)
	{
		//Create TicTacToe object
		TicTacToeGUI gui=new TicTacToeGUI();
		
	}
	
	private class CellButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//If game over, ignore
			if(gameOver)
			{
				return;
			}
			
			//Get button pressed
			TTTButton pressed=(TTTButton)(e.getSource());
			
			//Get text of button
			String text=pressed.getText();
			
			//If noughts or crosses, ignore
			if(text.equals("O") || text.equals("X"))
			{
				return;
			}
			
			//Add nought or cross
			if(noughts)
			{
				pressed.setText("O");
				noughtsArray.add(pressed.getPosition());
			}
			else
			{
				pressed.setText("X");
				crossesArray.add(pressed.getPosition());
			}
			System.out.println("pos " + pressed.getPosition());
			emptyArray.remove(emptyArray.indexOf(pressed.getPosition()));
			//Check winner
			if(checkWinner(noughtsArray, crossesArray))
			{
				//End of game
				gameOver=true;
				
				//Display winner message
				if(noughts)
				{
					result.setText("Noughts win!!");
				}
				else
				{
					result.setText("Crosses win!");
				}
			}
			else if ((noughtsArray.size() + crossesArray.size()) == bSize*bSize){
				gameOver = true;
				result.setText("Draw!");
			}
			else
			{
				//Change player
				noughts=!noughts;

				//Display player message
				if(noughts)
				{
					result.setText("Noughts");
				}
				else
				{
					result.setText("Computer");
					ActionEvent aClick = new ActionEvent(cells[MaxMove(0)], 1, "aiMove");
					this.actionPerformed(aClick);
				}
			}
		}
	}

	
	private class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	private class InitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			init();
		}
	}
}