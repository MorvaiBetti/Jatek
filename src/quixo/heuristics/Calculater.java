package quixo.heuristics;

import quixo.engine.QuixoBoard;

public class Calculater implements Heuristic{
	/**@mine az aktualis minta darabszama*/
	public int mine; 	
	/**@yours az ellenfel minta darabszama*/
	public int yours; 
	/**@free az ures minta darabszama*/
	public int free; 	
	/**@me sajat babu erteke*/
	public int me=2; 	
	/**@you ellenfel babu erteke*/
	public int you=5; 	
	/**@nobody ures mezo erteke*/
	public int nobody=0;
	/**@table aktualis tabla*/
	public QuixoBoard table;
 	/**@model aktualis minta*/
	public int model;
 	/**@value tabla erteke*/
	public int value;
	
	public Calculater(int color, QuixoBoard t){
		model=color;
		table=t;
		value=0;
		calculation();
	}
	/**A szamlalokat kinullazza*/
	public void empty(){
			value=0;
			mine=0;
			yours=0;
			free=0;
	}
	
	/**Az adott szin szempontjabol kiszamolja, melyik szinbol mennyi van a tablan.
	 * @param model ehhez a mintahoz szamitja a tabla erteket*/
	public void calculation(){
		/**sorokon megy vegig*/
		for(int i=0; i<5; i++){
			empty();
			for(int j=0; j<5; j++){
				if(table.getField(i, j)==model){
					mine++;
				}
				if(table.getField(i, j)==(model+1)%2){
					yours++;
				}
				if(table.getField(i, j)==QuixoBoard.empty){
					free++;
				}
			}
			value=sum();
		}
		/**oszlopokon megy vegig*/
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table.getField(j, i)==model){
					mine++;
				}
				if(table.getField(j, i)==(model+1)%2){
					yours++;
				}
				if(table.getField(j, i)==QuixoBoard.empty){
					free++;
				}
			}
			value=value+sum();
		}
		
		/**foatlon megy vegig*/
		for(int i=0; i<5; i++){
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
		value=value+sum();
		
		/**mellekatlon megy vegig*/
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
		value=value+sum();
		return;
	}

	/**osszegzi a tabla tartalmat*/
	public int sum(){
		int result;
		if(mine<yours){
			free=-free;
		}
		if(mine>yours){
			mine=mine*mine;
		}
		result=(int) ((mine*me)-Math.pow(yours, you)+free);
		mine=0;
		yours=0;
		free=0;
		return result;
	}

	@Override
	public int setValue() {
		return value;
	}
}
