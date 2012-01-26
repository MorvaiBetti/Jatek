import java.io.*;
import java.util.*;

public class Human extends SimplePlayer{
	public static String in;
	public static StringTokenizer st;
	public static BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));

	public Move nextMove(Move prevStep) {
		int i=0;
		/**@moveDatas lepes adatai*/
		int[] moveDatas=new int[4];
		try {
			in = reader.readLine();
			st = new StringTokenizer(in);
			
			/**megfelelo meretu legyen az input*/
			if(in.length()!=7){
				System.out.println("Rossz input! Helyes input: 'x y nx ny'");
				return nextMove(prevStep);
			}
				
			/**input letarolasa*/
		    while(st.hasMoreTokens()) {
		         moveDatas[i]=Integer.parseInt(st.nextToken());
		         i++;
		    }
			    
		    /**Ha szabalytalan a lepes, akkor ujra kerem*/
		    if(!table.legal(moveDatas[0], moveDatas[1], color, moveDatas[2], moveDatas[3])){
		    	System.out.println("legal lepest adjon meg!");
		    	return nextMove(prevStep);
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Move move=new Move(moveDatas[0], moveDatas[1], moveDatas[2], moveDatas[3]);
		return move;
	}
}