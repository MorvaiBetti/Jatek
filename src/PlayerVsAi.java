import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PlayerVsAi {

		/*(sor; oszlop)*/
		public static QuixoBoard t=new QuixoBoard();
		public static String be;
		public static StringTokenizer st;
		public static BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		public static int[] m;
		public static int i;
		public static int a;
		public static int b;
		
		public static void jatekosLep(){
			m=new int[4]; 	
			i=0;
			try {
				be = reader.readLine();
				st = new StringTokenizer(be);
				
				//megfelelo meretu legyen az input
				if(be.length()!=7){
					System.out.println("Rossz input!");
					jatekosLep();
					return;
				}
				
				//input letarolasa
			    while(st.hasMoreTokens()) {
			         m[i]=Integer.parseInt(st.nextToken());
			         i++;
			    }
			    
			    //Ha szabalytalan a lepes, akkor ujra kerem
			    if(!t.szabalyos(m[0], m[1], b, m[2], m[3])){
			    	System.out.println("Szabalyos lepest adjon meg!");
			    	jatekosLep();
			    	return;
			    }
			    t.lepestVegrehajt(m[0], m[1], 1, m[2], m[3]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		public static void main(String[] args){
			Move m0=null, m1=null;
			boolean megy=true;

			Player rp = new CheatRandomPlayer();
			rp.adatok(2, 0);
			rp.beallitTablat(t);
			a=rp.getSzin();
			b=(rp.getSzin() == QuixoBoard.X) ? QuixoBoard.O : QuixoBoard.X;

			t.kiir();
			while(megy){
				//------------------- RandomAI ---------------------------
				m0=rp.kovLepes(m1, 0);
				System.out.println();
				m0.toString();
				if(t.szabalyos(m0.x, m0.y, rp.getSzin(), m0.xn, m0.yn)){
					t.lepestVegrehajt(m0.x, m0.y, rp.getSzin(), m0.xn, m0.yn);
				}else {
					System.out.println("Szabalytalan l�ps X=crp1, O nyert!");
					return;
					}
				System.out.println("crp1: \t ");
				t.kiir();
				System.out.println();
				
				//Ha az elso jatekos nyer
				if(t.nyert(rp.getSzin())){
			    	megy=false;
			    	System.out.println("X = rp megnyerte a jatekot");
			    	continue;
			    }else if(t.nyert((rp.getSzin() == QuixoBoard.X) ? QuixoBoard.O : QuixoBoard.X)){			    	
			    		megy=false;
			    		System.out.println("O = player megnyerte a jatekot");
			    		continue;
			    	}
				
				
				//--------------------- player -------------------------
				jatekosLep();
				t.kiir();
				if(t.nyert(b)){
				  	megy=false;
				   	System.out.println("X megnyerte a jatekot");
				   	break;
				}else if(t.nyert(a)){
				   	megy=false;
				   	System.out.println("O = rp megnyerte a jatekot");
				   	continue;
				}
			}
		}
}
