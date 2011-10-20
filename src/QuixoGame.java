import java.io.*;
import java.util.StringTokenizer;

public class QuixoGame{
	/*Ket jatekos jatszik egymas ellen. Az nyer, aki hamarabb kirakja a sajat mintajabol az otot.*/
	
	public static void main(String[] args){
		QuixoBoard t=new QuixoBoard();
		t.kiir();
		//Lepesek beolvasasahoz
		BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		String be;
		StringTokenizer st;
		boolean megy=true; 		//megy: a jatek folyamatban van, ha false, akkor vege a jateknak
		int[] m=new int[4]; 	//m: a lepesek lementesehez
		int i, j; 				//i: bemeno parameterek letarolasahoz; j: melyik jatekos a soron kovetkezo
		j=1;

		
		//Addig lepnek felvaltva a jatekosok, amig valamelyikuk nem nyer vagy elfogynak a kovetkezo jatekos szabalyos lepesei
		while(megy){
			if(!t.vanSzabalyosLepes(j)){
				megy=false; 
				break;
			}
			i=0;
			if(j==1){
	    		System.out.println("X lép: ");
	    	} else {System.out.println("O lép: ");}
			try {
				be = reader.readLine();
				st = new StringTokenizer(be);
			    while (st.hasMoreTokens()) {
			         m[i]=Integer.parseInt(st.nextToken());
			         i++;
			    }
			    //Addig kerem be a lepes koordinatait, amig azok szabalyosak nem lesznek
			    while(!t.szabalyos(m[0], m[1], j, m[2], m[3])){
			    	i=0;
			    	if(j==1){
			    		System.out.println("X lép: ");
			    	} else {System.out.println("O lép: ");}
					be = reader.readLine();
					st = new StringTokenizer(be);
				    while (st.hasMoreTokens()) {
				         m[i]=Integer.parseInt(st.nextToken());
				         i++;
				    }
			    }
			    //Vegrehajtom a lepest, kiirom a tablat, ellenorzom, hogy nyert-e valamelyik jatekos
			    t.lepestVegrehajt(m[0], m[1], j, m[2], m[3]);
			    t.kiir();
			    if(t.nyert(j)){ 	//Ha valamelyik jatekos nyert, akkor vege a jateknak
			    	megy=false;
			    	if(j==1){
			    		System.out.println("X megnyerte a jatekot");
			    	} else {System.out.println("O megnyerte a jatekot");}
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