package TicTacToeAI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TTTButton extends JButton {
	int position;

	public TTTButton(String text, int position) {
		super(text);
		this.position = position;
	}
	
	public int getPosition(){
		return this.position;
	}
}
