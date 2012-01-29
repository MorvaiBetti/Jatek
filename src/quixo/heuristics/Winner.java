package quixo.heuristics;

import quixo.engine.QuixoBoard;

public class Winner {
	public QuixoBoard table;
	/**@model az aktualis minta*/
	public int model; 	
	/**@mine az aktualis minta koordinataja*/
	public int[][] mineFields; 	
	/**@yours az ellenfel minta darabszama*/
	public int[][] yoursFields; 	
	/**@sor sort vizsgalok*/
	public boolean sor;
	/**@oszlop oszlopot vizsgalok*/
	public boolean oszlop;
	/**@atlo atlot vizsgalok*/
	public boolean atlo;
	/**@mellekAtlo mellekatlot vizsgalok*/
	public boolean mellekAtlo;
	/**@value tabla erteke*/
	public int value;
	/**@mine sajat babik szama*/
	public int mine;
	/**@yours ellenfel babuinak szama*/
	public int yours;
	/**@nobody ures babuk szama*/
	public int nobody;
	/**@me sajat babu erteke*/
	public int me=4;
	/**@you ellenfel babu erteke*/
	public int you=4;
	/**@win a nyeres erteke*/
	public int win=5;
	
	
	public Winner(int color, QuixoBoard t){
		model=color;
		table=t;
		value=0;
		yoursFields=new int[5][2];
		mineFields=new int[5][2];
		value=0;
		mine=0;
		yours=0;
		nobody=0;
		calculation();
	}
	
	public void calculation(){
		/**Soronkent megy vegig*/
		for(int i=0; i<5; i++){
			mine=0; yours=0;
			for(int j=0; j<5; j++){
				if(table.getField(i, j)==model){
					mineFields[mine][0]=i;
					mineFields[mine][1]=j;
					mine++;
				}
				if(table.getField(i, j)==(model+1)%2){
					yoursFields[yours][0]=i;
					yoursFields[yours][1]=j;
					yours++;
				}else {nobody++;}
			}
			findField();
		}
		
		/**oszlopononkent megyek vegig*/
		for(int i=0; i<5; i++){
			mine=0; yours=0;
			for(int j=0; j<5; j++){
				if(table.getField(j, i)==model){
					mineFields[mine][0]=i;
					mineFields[mine][1]=j;
					mine++;
				}
				if(table.getField(j, i)==(model+1)%2){
					yoursFields[yours][0]=i;
					yoursFields[yours][1]=j;
					yours++;
				}else {nobody++;}
			}
			findField();
		}
		/*
		/**atlon megy vegig
		k=0;
		l=0;
		for(int i=0; i<5; i++){
			if(table.getField(i, i)==model){
				mineFields[k][0]=i;
				mineFields[k][1]=i;
				k++;
			}
			if(table.getField(i, i)==(model+1)%2){
				yoursFields[l][0]=i;
				yoursFields[l][1]=i;
				l++;
			}
		}
		findField();
		
		/**mellekatlon megyek vegig
		k=0;
		l=0;
		for(int i=0; i<5; i++){
			if(table.getField(i, 4-i)==model){
				mineFields[k][0]=i;
				mineFields[k][1]=4-i;
				k++;
			}
			if(table.getField(i, 4-i)==(model+1)%2){
				yoursFields[l][0]=i;
				yoursFields[l][1]=4-i;
				l++;
			}
		}
		findField();*/
	}
	
	public void findField(){
		if(yours<4){
			value=value-yours;
		}
		if(yours==5){
			value=-(int) Math.pow(win, yours);
			return;
		}
		if(yours==4){
			/**ha sorban van negy egyforma*/
			if(mineFields[0][0]==mineFields[1][0]){
				sor=true;
				for(int i=0; i<3; i++){
					if(mineFields[i][1]+1!=mineFields[i+1][1] && findStep(mineFields[i][0], mineFields[i][1]+1, (model+1)%2)){
						value=value-(int) Math.pow(win, yours);
						return;
					}
				}
				sor=false;
			}
			if(mineFields[0][1]==mineFields[1][1]){
				oszlop=true;
				for(int i=0; i<3; i++){
					if(mineFields[i][0]+1!=mineFields[i+1][0] && findStep(mineFields[i][0]+1, mineFields[i][1], (model+1)%2)){
						value=value-(int) Math.pow(win, yours);
						return;
					}
				}
				oszlop=false;
			}
		}
		if(mine==5){
			value=value+(int) Math.pow(win, mine)+nobody;
			return;
		}
		if(mine==4){
			/**ha sorban van negy egyforma*/
			if(mineFields[0][0]==mineFields[1][0]){
				sor=true;
				for(int i=0; i<3; i++){
					if(mineFields[i][1]+1!=mineFields[i+1][1] && findStep(mineFields[i][0], mineFields[i][1]+1, model)){
						value=value+(int) Math.pow(win, mine)+nobody;
						return;
					}
				}
				sor=false;
			}
			if(mineFields[0][1]==mineFields[1][1]){
				oszlop=true;
				for(int i=0; i<3; i++){
					if(mineFields[i][0]+1!=mineFields[i+1][0] && findStep(mineFields[i][0]+1, mineFields[i][1], model)){
						value=value+(int) Math.pow(win, mine)+nobody;
						return;
					}
				}
				oszlop=false;
			}
		}
		if(yours>mine){nobody=-nobody;}
		value=value+(int) (Math.pow(me, mine))+nobody;
	}
	
	/**Ellenorzi, hogy egy lepesbol nyerhet-e valaki*/
	public boolean findStep(int x, int y, int model){
		if(x>4 || x<0 || y>4 || y<0){return false;}
		if(sor){
			/**lentrol felteszem*/
			if(x==0 && table.legal(4, y, model, x, y)){
				return true;
			}
			/**fontrol leteszem*/
			if(x==4 && table.legal(0, y, model, x, y)){
				return true;
			}
			/**ha vizszintesen a szelen van, akkor a sor elejere is tehetem*/
			if((x==0 || x==4) && table.legal(x, y, model, x, 0)){
				return true;
			}
			/**ha felette a sajatom van*/
			if(x+1<5 && table.getField(x+1, y)==model && table.legal(4, y, model, 0, y)){
				return true;
			}
			/**ha alatta a sajatom van*/
			if(x-1>=0 && table.getField(x-1, y)==model && table.legal(0, y, model, 4, y)){
				return true;
			}
		}
		if(oszlop){
			/**jobrol balra teszem*/
			if(y==0 && table.legal(x, 4, model, x, y)){
				return true;
			}
			/**balrol jobbra teszem*/
			if(y==4 && table.legal(x, 0, model, x, y)){
				return true;
			}
			/**ha fuggolegesen a szelen van, akkor az oszlop elejere is tehetem*/
			if((y==0 || y==4) && table.legal(x, y, model, 0, y)){
				return true;
			}
			/**ha tole jobbra a sajatom van*/
			if(y+1<5 && table.getField(x, y+1)==model && table.legal(4, y, model, 0, y)){
				return true;
			}
			/**ha tole balra a sajatom van*/
			if(y-1>=0 && table.getField(x, y-1)==model && table.legal(0, y, model, 4, y)){
				return true;
			}
		}
		return false;
	}
}
