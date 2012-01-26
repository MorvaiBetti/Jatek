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
	}
	

	public int calculation(){
		fields=new int[5][4];
		emptyFields();

		/**oszlopokon megy vegig*/
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
		return mine-yours-free;
			
	}
		/*	if(yours>=mine){
				free=-free;
			}	
			//fields[i][0]=sum();
		}
		
		/**sorokon megy vegig*/
	/*	for(int j=0; j<5; j++){
			for(int i=0; i<5; i++){
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
		/*	if(yours>=mine){
				free=-free;
			}	
			fields[j][1]=sum();
		}
		
		/**foatlon megyek vegig
		for(int i=0; i<5;i++){
			if(table.getField(i, i)==model){
				mine++;
			}
			if(table.getField(i, i)==(model+1)%2){
				yours++;
			}
			if(table.getField(i, i)==QuixoBoard.empty){
				free++;
			}
		}
	/*	if(yours>=mine){
			free=-free;
		}	
		fields[0][2]=sum();
		
		/**mellekatlon megyek vegig
		for(int i=0; i<5; i++){
			if(table.getField(i, 4-i)==model){
				mine++;
			}
			if(table.getField(i, 4-i)==(model+1)%2){
				yours++;
			}
			if(table.getField(i, 4-i)==QuixoBoard.empty){
				free++;
			}
		}
	/*	if(yours>=mine){
			free=-free;
		}
		fields[0][3]=sum();
		
		for(int i=0; i<5; i++){
			for(int j=0; j<4; j++){
				value=value+fields[i][j];
			}
		}
		return value;
	}
	*/
	/**Kiuriti a fields-et*/
	public void emptyFields(){
		value=0;
		for(int i=0; i<5; i++){
			for(int j=0; j<4; j++){
				fields[i][j]=0;
			}
		}
	}
	
	/**osszegzi az ertekeket
	public int sum(){
		//int result;
		value=(mine*me)-(you*yours);
	//	result=(free*nobody)+(mine*me)+(yours*you);
	/*	result=(int) Math.pow(me, mine);
		result=result+(free*nobody);
		result=(int) (result-Math.pow(you, yours));
		mine=0;
		free=0;
		yours=0;
		return value;
	}*/
}
