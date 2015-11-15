package TicTacToeAI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


public class DataLogger {
	
	public long game_id;
	public ArrayList<int[]> gameStateArrayList = new ArrayList<int[]>();
	public int[] gameState;
	public int[] currentGame;
	public int result; //1 means computer won, -1 is loss, 0 is draw
	
	public DataLogger(Date time){
		this.game_id = time.getTime();
	}
	
	public void parseGameState(ArrayList<Integer> noughts, ArrayList<Integer> crosses) {
		gameState = new int[25];
		for(int cell = 0; cell < 25; cell++) {
			gameState[cell] = -1; // -1 means no entry
		}
		for(Integer o : noughts) {
			gameState[o] = 0; // 0 means O
		}
		for(Integer x : crosses) {
			gameState[x] = 1; // 1 means X
		}
		gameStateArrayList.add(gameState);
	}
	
	public void writeGameResult(int result){
		this.result = result;
	}
	
	public void writeDataToText() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("log-" + game_id + ".txt");
		writer.println("Tic Tac Toe Game: " + game_id);
		for(int[] gameState : gameStateArrayList) {
			for(int pos : gameState){
				writer.print(pos);
			}
			writer.println();
		}
		writer.println("Result: " + result);
		writer.close();
	}
	
	public void writeDataToLossLog() throws FileNotFoundException {
		String fileName = "losses.txt";

        // This will reference one line at a time
        String line = null;
        ArrayList<String> lines = new ArrayList<String>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	lines.add(line);
            }
            bufferedReader.close(); 
        }
        catch (Exception e) {
        	
        }
		PrintWriter writer = new PrintWriter("losses.txt");
		for(String aline : lines) {
			writer.println(aline);
		}
		int lossData[] = new int[25]; //game state a few steps before loss
		int losingMove = -1;
		for(int i = 0; i < 25; i++){
			lossData[i] = gameStateArrayList.get(gameStateArrayList.size() - 2)[i];
			//
			if (gameStateArrayList.get(gameStateArrayList.size() - 3)[i] != gameStateArrayList.get(gameStateArrayList.size() - 2)[i]) {
				losingMove = i;
				System.out.println("Losing Move was: " + losingMove);
			}
		}
		System.out.println("Adding Loss: " + Arrays.toString(lossData));
		writer.println(Arrays.toString(lossData));
		writer.println(losingMove);
		writer.close();
	}
	
	public int checkForbiddenMoves(ArrayList<Integer> noughts, ArrayList<Integer> crosses){
		currentGame = new int[25];
		for(int cell = 0; cell < 25; cell++) {
			currentGame[cell] = -1; // -1 means no entry
		}
		for(Integer o : noughts) {
			currentGame[o] = 0; // 0 means O
		}
		for(Integer x : crosses) {
			currentGame[x] = 1; // 1 means X
		}
		System.out.println("Checking for match: " + Arrays.toString(currentGame) + "CUR");
		// The name of the file to open.
        String fileName = "losses.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	//System.out.println("Checking for match: " + line);
            	int k = 0;
            	if (line.equals(Arrays.toString(currentGame))){
            		System.out.println("Match!");
            		int badMove = Integer.parseInt(bufferedReader.readLine());
            		System.out.println("Bad Move is: " + badMove);
            		return badMove;
            	}
            	
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" + fileName + "'");                  
        }
        return -1;
	}
	
}
