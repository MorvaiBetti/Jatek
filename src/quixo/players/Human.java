package quixo.players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import quixo.engine.Move;
import quixo.engine.QuixoPlayer;

public class Human extends QuixoPlayer{
	private static String in;
	private static StringTokenizer st;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
	
	public Human(int sequence){
			this.datas(sequence, Long.MAX_VALUE, 0, "null", 0,0,0);

	}
	
	protected Move nextMove(Move prevStep) {
		return null;
	}
	
	public void makeStep(Move step){
		table.makeStep(step, color);
	}
}