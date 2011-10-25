import java.io.*;
import java.util.*;

public class PlayerVsAi {

		/*(sor; oszlop)*/
	public static QuixoBoard t=new QuixoBoard();
	public static String in;
	public static StringTokenizer st;
	public static BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
	public static int[] m;
	public static int i;
	public static int a;
	public static int b;
		
	public static void playerStep(){
		m=new int[4]; 	
		i=0;
		try {
			in = reader.readLine();
			st = new StringTokenizer(in);
			
			//megfelelo meretu legyen az input
			if(in.length()!=7){
				System.out.println("Rossz input!");
				playerStep();
				return;
			}
				
			//input letarolasa
		    while(st.hasMoreTokens()) {
		         m[i]=Integer.parseInt(st.nextToken());
		         i++;
		    }
			    
		    //Ha szabalytalan a lepes, akkor ujra kerem
		    if(!t.legal(m[0], m[1], b, m[2], m[3])){
		    	System.out.println("legal lepest adjon meg!");
		    	playerStep();
		    	return;
		    }
		    t.makeStep(m[0], m[1], 1, m[2], m[3]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
	public static void main(String[] args){
		Move m0=null, m1=null;
		boolean go=true;

		CheatRandomPlayer rp=new CheatRandomPlayer();
		rp.datas(2, 0);
		rp.setTable(t);
		a=rp.color;
		b=rp.opponentColor;

		t.toString();
		while(go){
			//------------------- RandomAI ---------------------------
			m0=rp.kovLepes(m1, 0);
			System.out.println();
			m0.toString();
			if(t.legal(m0.x, m0.y, rp.color, m0.nx, m0.ny)){
				t.makeStep(m0.x, m0.y, rp.color, m0.nx, m0.ny);
			}else {
				System.out.println("Szabalytalan léps X=crp1, O nyert!");
				return;
			}
			System.out.println("crp1: \t ");
			t.toString();
			System.out.println();
				
			//Ha az elso jatekos nyer
			if(t.win(rp.color)){
		    	go=false;
		    	System.out.println("X = rp megnyerte a jatekot");
		    	continue;
		    }else if(t.win(rp.opponentColor)){			    	
		    		go=false;
		    		System.out.println("O = player megnyerte a jatekot");
		    		continue;
	    	}
				
				
			//--------------------- player -------------------------
			playerStep();
			t.toString();
			if(t.win(b)){
			  	go=false;
			   	System.out.println("X megnyerte a jatekot");
			   	break;
			}else if(t.win(a)){
			   	go=false;
			   	System.out.println("O = rp megnyerte a jatekot");
			   	continue;
			}
		}
	}
}
