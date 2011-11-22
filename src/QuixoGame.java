import java.io.*;
import java.util.StringTokenizer;

public class QuixoGame{
	/*Ket jatekos jatszik egymas ellen. Az nyer, aki hamarabb kirakja a sajat mintajabol az otot.*/
	
	public static void main(String[] args){
		QuixoBoard t=new QuixoBoard();
		System.out.println(t);
		//Lepesek beolvasasahoz
		BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		String in;
		StringTokenizer st;
		boolean go=true; 		//megy: a jatek folyamatban van, ha false, akkor vege a jateknak
		int[] m=new int[4]; 	//m: a lepesek lementesehez
		int i, j; 				//i: bemeno parameterek letarolasahoz; j: melyik jatekos a soron kovetkezo
		j=1;

		
		//Addig lepnek felvaltva a jatekosok, amig valamelyikuk nem nyer vagy elfogynak a kovetkezo jatekos legal lepesei
		while(go){
			i=0;
			if(j==1){
	    		System.out.println("X steps: ");
	    	} else {System.out.println("O steps: ");}
			try {
				in = reader.readLine();
				st = new StringTokenizer(in);
			    while (st.hasMoreTokens()) {
			         m[i]=Integer.parseInt(st.nextToken());
			         i++;
			    }
			    //Addig kerem be a lepes koordinatait, amig azok legalak nem lesznek
			    while(!t.legal(m[0], m[1], j, m[2], m[3])){
			    	i=0;
			    	if(j==1){
			    		System.out.println("X steps: ");
			    	} else {System.out.println("O steps: ");}
					in = reader.readLine();
					st = new StringTokenizer(in);
				    while (st.hasMoreTokens()) {
				         m[i]=Integer.parseInt(st.nextToken());
				         i++;
				    }
			    }
			    //Vegrehajtom a lepest, kiirom a tablat, ellenorzom, hogy nyert-e valamelyik jatekos
			    t.makeStep(m[0], m[1], j, m[2], m[3]);
			    System.out.println(t);
			    if(t.win(j)){ 	//Ha valamelyik jatekos nyert, akkor vege a jateknak
			    	go=false;
			    	if(j==1){
			    		System.out.println("X win this game");
			    	} else {System.out.println("O win this game");}
			    	break;
			    }
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(j==2){j--;} 			//valt a ket jatekos kozott. X utan a O jon.
			else {j++;}
		}
	}
}