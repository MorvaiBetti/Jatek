package quixo.heuristics;

import quixo.engine.QuixoBoard;

public class Calculater {
	/**@mine az aktualis minta darabszama*/
	public int mine; 	
	/**@yours az ellenfel minta darabszama*/
	public int yours; 
	/**@free az ures minta darabszama*/
	public int free; 	
	public int me=3;
	public int you=-3;
	public int nobody=0;
 	/**@fields hogy helyezkednek el a mintak*/
	public int[][] fields;
	public QuixoBoard table;
 	/**@model aktualis minta*/
	public int model;
 	/**@value tabla erteke*/
	public int value;
	
	public Calculater(int color, QuixoBoard t){
		model=color;
		table=t;
		calculation(color);
	}
	

	public void calculation(int model){
		emptyFields();
		int k=0;

		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				for(int l=0; l<5; l++){
					if(l!=i){
						//oszlop
						if(table.getField(l,j)==model){
							fields[k][1]+=me;
						}
						if(table.getField(l,j)==QuixoBoard.empty){
							fields[k][1]+=nobody;
						}
						if(table.getField(l,j)==(model+1)%2){
							fields[k][1]+=you;
						}
					}
					if(l!=j){
						//sor
						if(table.getField(i,l)==model){
							fields[k][2]+=me;
						}
						if(table.getField(i,l)==QuixoBoard.empty){
							fields[k][2]+=nobody;
						}
						if(table.getField(i,l)==(model+1)%2){
							fields[k][2]+=you;
						}
					}
					if(i==j){
						//foatlo
						if(l!=i){
							if(table.getField(l,l)==model){
								fields[k][3]+=me;
							}
							if(table.getField(l,l)==QuixoBoard.empty){
								fields[k][3]+=nobody;
							}
							if(table.getField(l,l)==(model+1)%2){
								fields[k][3]+=you;
							}
						}
					}
					if(i==4-j){
						//mellekatlo
						if(l!=i){
							if(table.getField(l,4-l)==model){
								fields[k][4]+=me;
							}
							if(table.getField(l,4-l)==QuixoBoard.empty){
								fields[k][4]+=nobody;
							}
							if(table.getField(l,4-l)==(model+1)%2){
								fields[k][4]+=you;
							}
						}
					}
				}
				k++;
			}
		}
	}

	public void emptyFields(){
		for(int i=0; i<5; i++){
			for(int j=2; j<4; j++){
				fields[i][j]=0;
			}
		}
	}

	public int sum(){/*
		/**oszlopokon megy vegig*/
		int mine = 0;
		int yours = 0;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table.getField(i,j)==model){
					mine++;
				}
				if(table.getField(i,j)==(model+1)%2){
					yours++;
				}
				if(table.getField(i, j)==QuixoBoard.empty){
					free++;
				}
			}	
		}
		value=(mine*me)-(yours*you);
		return value;
	}
}
