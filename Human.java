import java.io.*;
import java.util.*;


public class Human implements Player{
	public QuixoBoard table;
	public ArrayList<Move> steps; 			//lehetseges steps letarolasara
	public int color; 						//A jatekos sajat mintaja
	public int opponentColor; 				//Az ellenfel mintaja
	public long maxTime;
	
	public static String in;
	public static StringTokenizer st;
	public static BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
	
	public void setTable(QuixoBoard qt) {
		table=qt;
	}


	public void datas(int sequence, long time) {
		maxTime=time;
		if(sequence==QuixoBoard.X){ 				//A jatekosnak melyik a mintaja, es az ellenfele melyik
			color=QuixoBoard.X;
			opponentColor=QuixoBoard.O;
		}else if(sequence==QuixoBoard.O){color=QuixoBoard.O;
		opponentColor=QuixoBoard.X;
		}
	}

	public Move nextMove(Move prevStep, long oTime) {
		int i;
		int[] m;
		m=new int[4]; 	
		i=0;
		try {
			in = reader.readLine();
			st = new StringTokenizer(in);
			
			//megfelelo meretu legyen az input
			if(in.length()!=7){
				System.out.println("Rossz input!");
				return nextMove(prevStep, oTime);
			}
				
			//input letarolasa
		    while(st.hasMoreTokens()) {
		         m[i]=Integer.parseInt(st.nextToken());
		         i++;
		    }
			    
		    //Ha szabalytalan a lepes, akkor ujra kerem
		    if(!table.legal(m[0], m[1], color, m[2], m[3])){
		    	System.out.println("legal lepest adjon meg!");
		    	return nextMove(prevStep, oTime);
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Move move=new Move(m[0],m[1],m[2],m[3]);
		return move;
	}

	public int getColor() {
		return color;
	}

}
