package quixo.engine;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game{
	/**@table jatektabla*/
	private static QuixoBoard table=new QuixoBoard();
	/**@move utoljara megtett lepes*/
	private static Move move;
	/**@p emberi jatekosok*/
	private static QuixoPlayer[] p=new QuixoPlayer[2];
	/**@pt gepi jatekosok*/
	private static PlayerThread[] pt=new PlayerThread[2];
	/**@j lepes sorszama*/
	private static int j; 
	 /**@ai gepi jatekosok szama*/
	private static int ai; 
	 /**@people emberek szama*/
	private static int people;
	/**@ind aktualis jatekos szama*/
	private static int ind;
	/**@nextInd kovetkezo jatekos szama*/
	private static int nextInd;
	/**@maxTime jatekosok ideje*/
	private static long maxTime;
	
	/**@player1 az elso jatekos szama*/
	private static int player1;
	/**@depth1 az elso jatekos melysege*/
	private static int depth1;
	/**@random1 az elso jatekos milyen seed-et hasznal*/
	private static int random1;
	/**@heuristic1 az elso jatekos heurisztikaja*/
	private static String heuristic1;
	/**@me1 sajat babu erteke*/
	private static int me1; 	
	/**@you1 ellenfel babu erteke*/
	private static int you1;
	/**@nobody1 az elso jatekos eseten az ures babuk erteke*/
	private static int nobody1;
	
	/**@player2 a masodik jatekos szama*/
	private static int player2;
	/**@depth2 a masodik jatekos melysege*/
	private static int depth2;
	/**@random2 a masodik jatekos milyen seed-et hasznal*/
	private static int random2;
	/**@heuristic2 a masodik jatekos altal hasznalt heurisztika*/
	private static String heuristic2;
	/**@me2 sajat babu erteke*/
	private static int me2; 	
	/**@you2 ellenfel babu erteke*/
	private static int you2;
	/**@nobody2 az elso jatekos eseten az ures babuk erteke*/
	private static int nobody2;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
	private static int text;
	
	/**Jatekos letrehozas es szal inditasa*/
	private static void player(int i, long maxTime, int playerTipe, int depth, int random, String heuristic, int me, int you, int nobody)throws Exception{
			if(playerTipe==1){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.RandomPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime, random, heuristic, me, you, nobody);
				ai++;
				return;
			}
			if(playerTipe==2){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.CheatRandomPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime, random, heuristic, me, you, nobody);
				ai++;
				return;
			}
			if(playerTipe==3){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.DefendPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime, random, heuristic, me, you, nobody);
				ai++;
				return;
			}
			if(playerTipe==4){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.CollectorPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime, random, heuristic, me, you, nobody);
				ai++;
				return;
			}
			if(playerTipe==5){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.CalculatingPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime, random, heuristic, me, you, nobody);
				ai++;
				return;
			}
			if(playerTipe==6){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.DefendCalculatingPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime, random, heuristic, me, you, nobody);
				ai++;
				return;
			}
			if(playerTipe==7){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.GreedyCalculatingPlayer");
				pt[ai].start();
				pt[ai].datas(i, maxTime, random, heuristic, me, you, nobody);
				ai++;
				return;
			}
			if(playerTipe==8){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.minimax.Greedy");
				pt[ai].start();
				pt[ai].datas(i, maxTime, random, heuristic, me, you, nobody);
				ai++;
				return;
			}
			if(playerTipe==9){
				pt[ai]=new PlayerThread(i, maxTime, "quixo.players.minimax.Minimax");
				pt[ai].start();
				pt[ai].datas(i, maxTime, random, heuristic, me, you, nobody);
				pt[ai].setDepth(depth);
				ai++;
				return;
			}
			if(playerTipe==0){
				p[people]=new quixo.players.Human(people);
				p[people].datas(i, maxTime, random, heuristic, me, you, nobody);
				people++;
				return;
			}
	}
	
	/**nyert-e valamelyik jatekos*/
	private static boolean winner(){
		if(table.win(QuixoBoard.X) && table.win(QuixoBoard.O)){
			if(text==1){
				System.out.println("Drawn!");
			} else {System.out.println(2);}
			return true;
		}
		if(table.win(QuixoBoard.X)){
			if(text==1){
				System.out.println("X won");
			}else {System.out.println(QuixoBoard.X);}
			return true;
		}
		if(table.win(QuixoBoard.O)){
			if(text==1){
				System.out.println("O won");
			} else {System.out.println(QuixoBoard.O);}
			return true;
		}
		return false;
	}
	
	/**meghivja az ai nextMove()-jat, ellenorzi az ai lepeset es vegrehajtja azt
	 * @param i a timb hanyadik jatekosa jon
	 * @param oTime ellenfel eddig eltelt ideje*/
	private static boolean aiStep(int i, long oTime){
		move = pt[i].nextMove(move);
		if (move != null) {
		   /** 'lejart-e az ideje?' ellenorzese*/
		   if (pt[i].getElapsedTime() > maxTime) {
			   if(pt[i].getColor()==QuixoBoard.X){
				   		if(text==1){
				   			System.out.println("X's time is expired so O won!");
				   		} else {System.out.println(QuixoBoard.O);}
			            return false;
			          } else {
			        	  if(text==1){
			        		  System.out.println("O's time is expired so X won!");
			        	  }else {System.out.println(QuixoBoard.X);}
			            return false;
			          }
		   }
		   
		   /** 'csalt-e valaki?' ellenorzese*/
		   if(table.legal(move.getX(), move.getY(), pt[i].getColor(), move.getNx(), move.getNy())){
			   table.makeStep(move, pt[i].getColor());
		   } else {
			   if(pt[i].getColor()==QuixoBoard.X){
				   if(text==1){
					   System.out.println("X cheated so O won!");
				   }else {System.out.println(QuixoBoard.O);}
				   	return false;
			   } else {
				   if(text==1){
					   System.out.println("O cheated so X won!");
				   }else {System.out.println(QuixoBoard.X);}
				   return false;
		   		}
		   }
		   if(text==1){
			   System.out.println("\n"+j+". step "+ind+". player stepped | "+ move +" time: "+pt[i].getElapsedTime()+" figure: "+pt[i].getColor()+" || "+pt[ind].sequence+" my name is "+pt[i].playerName);
			   System.out.println(table);
		   }

		} else {
			/** ind jatekos null-t lepett => lepes kenyszer miatt kikapott*/
			if(pt[i].getColor()==QuixoBoard.X){
				if(text==1){
					System.out.println("X didn't step so O won!");
				}else {System.out.println(QuixoBoard.O);}
				return false;
		    } else {
		    	if(text==1){
		    		System.out.println("O didn't step so X won!");
		    	}else {System.out.println(QuixoBoard.X);}
		          return false;
		        }
			}
		return true;
	}
	
	/**meghivja a human jatekos nextMove()-jat es vegrehajtja azt
	 * @param i i-edik human jatekos lep*/
	private static void humanStep(int i){
		table.nextSteps(p[i].getColor());
		move = p[i].nextMove(move);	   
		table.makeStep(move, p[i].getColor());
		if(text==1){
			System.out.println("\n"+j+". step "+ind+". player stepped | "+ move +" figure: "+p[i].getColor());	
			System.out.println(table);
		}
		return;
	}
	
	/**Human vs Human: Ket emberi jatekos felvaltva lep*/
	private static void people(){
		while(!table.win(p[0].getColor()) && !table.win(p[1].getColor())){
			ind=j%2;
			nextInd=(j+1)%2;
			humanStep(ind);
			winner();
			j++;
		}
	}
	
	/**AI vs AI: ket gepi jatekos felvaltva lep*/
	private static void ai(){
		while(!table.win(pt[0].getColor()) && !table.win(pt[1].getColor())){
			ind=j%2;
			nextInd=(j+1)%2;
			if(!aiStep(ind, pt[nextInd].getElapsedTime())){
				return;
			}
			winner();
			j++;
		}
	}
	
	/**Human vs AI: ember es gep felvaltva lep. A szan alapjan dol el, hogy melyik jatekos kezd*/
	private  static void humanvsAI(){
		/**Ha a gepi jatekos van az X-el, akkor o kezd, kulonben az ember*/
		if(pt[0].getColor()==QuixoBoard.X){
			while(!table.win(pt[0].getColor()) && !table.win(p[0].getColor())){			
				aiStep(0, 0);
				if(winner()){
					return;
				}
				j++;
				humanStep(0);
				winner();
				j++;
			}
		}else {
			while(!table.win(pt[0].getColor()) && !table.win(p[0].getColor())){
				humanStep(0);
				if(winner()){
					return;
				}
				j++;
				aiStep(0, 0);
				if(winner()){
					return;
				}
				j++;
			}
		}
	}
	

	/**time=args[0], 
	 * player1=args[1], random1=args[2],  player1depth=args[3], heuristic1[4], me1=args[5], you1=args[6], nobody1=args[7]
	 * player2=args[8], random2=args[9], player2depth=args[10], heuristic2[11], me2=args[12], you2=args[13], nobody2=args[14]
	 * text=args[15]*/
	/*public static void main(String[] args) throws Exception{
		
		j=ai=people=0;
		move=null;

		text=1;
		if(text==1){
			System.out.println("Lehetseges jatekosok:");
			System.out.println("0 Human: Emberi jatekos");
			System.out.println("1 RandomPlayer: Gepi jatekos, aki veletlen valaszt egy szabalyos lepest.");
			System.out.println("2 CheatRandomPlayer: Gepi jatekos, aki veletlen valaszt egy szabalyos lepest, elofordulhat, hogy csalni probal.");
			System.out.println("3 DefendPlayer: Ha az ellenfelnek egy babu hianyzik, hogy nyerjen, akkor vedekezik. Egyebkent random lep.");
			System.out.println("4 CollectorPlayer: Ha van lehetosege, akkor egy uj babut tesz le, egyebkent random.");
		//	System.out.println("5 CalculatingPlayer: Kiszamol egy tablat, hogy hova mennyire erdemes lepni. Kiirja a tablat, majd random lep.");
			System.out.println("6 DefendCalculatingPlayer: A legveszelyesebb mezore probal lepni. Azaz probalja minel jobban gatolni az ellenfelet");
			System.out.println("7 GreedyCalculatingPlayer: A legjobb helyre probal lepni.");
			System.out.println("8 Greedy: Egy melysegig nezi a fat.");
			System.out.println("9 Minimax: parameterben megadott melysegig vizsgalja a jatekfat.");
			System.out.println();
			System.out.println("Mindenhova szamot kell megadni, kiveve heurisztika megadasakor. Lehetseges heurisztikak: Index, PrevTable, Winner.");
			System.out.println();
		}
		
		try {
				table=new QuixoBoard();
				j=ai=people=0;
				move=null;
				
				System.out.println("Game time:");
				maxTime= Integer.parseInt(reader.readLine());
				
				
				System.out.println("First player:");
				player1= Integer.parseInt(reader.readLine());
				if(player1>0){
					System.out.println("First player's random:");
					random1= Integer.parseInt(reader.readLine());
					
					if(player1==9){
						System.out.println("First player's depth:");
						depth1= Integer.parseInt(reader.readLine());
					}else {
						depth1=0;
					}
					if(player1>7){	
						System.out.println("First player's heuristic:");
						heuristic1= reader.readLine();
						
						System.out.println("First player's me:");
						me1= Integer.parseInt(reader.readLine());
						
						System.out.println("First player's you:");
						you1= Integer.parseInt(reader.readLine());
						
						System.out.println("First player's nobody:");
						nobody1= Integer.parseInt(reader.readLine());
					}else {
						heuristic1="null";
						me1=0;
						you1=0;
						nobody1=0;
					}
				}else{
					random1=0;
					heuristic1="null";
					me1=0;
					you1=0;
					nobody1=0;
				}
				
				System.out.println("Second player:");
				player2= Integer.parseInt(reader.readLine());
				if(player2>0){
					System.out.println("Second player's random:");
					random2= Integer.parseInt(reader.readLine());
					
					if(player2==9){
						System.out.println("Second player's depth:");
						depth2= Integer.parseInt(reader.readLine());
					}else {
						depth2=0;
					}
	
					if(player2>7){
						System.out.println("Second player's heuristic:");
						heuristic2= reader.readLine();
						
						System.out.println("Second player's me:");
						me2= Integer.parseInt(reader.readLine());
						
						System.out.println("Second player's you:");
						you2= Integer.parseInt(reader.readLine());
						
						System.out.println("Second player's nobody:");
						nobody2= Integer.parseInt(reader.readLine());
					}else {
						heuristic2="null";
						me2=0;
						you2=0;
						nobody2=0;
					}
				}else{
					random2=0;
					heuristic2="null";
					me2=0;
					you2=0;
					nobody2=0;
				}
				
				player(0, maxTime, player1, depth1, random1, heuristic1, me1, you1, nobody1);
				player(1, maxTime, player2, depth2, random2, heuristic2, me2, you2, nobody2);
				if(text==1){
					System.out.println(table);
				}
				if(people==2){
					people();
					return;
				}else if(ai==2){
						ai();
						pt[0].exit();
						pt[1].exit();
						return;
					}else{humanvsAI();
							pt[0].exit();
							return;
						}	
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}