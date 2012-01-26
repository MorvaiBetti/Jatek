
public class Winner {
	public QuixoBoard table;
	public int model; 	/**@model az aktualis minta*/
	public int mine; 	/**@mine az aktualis minta darabszama*/
	public int yours; 	/**@yours az ellenfel minta darabszama*/
	public int free; 	/**@mine az ures minta darabszama*/
	
	
	public Winner(int color, QuixoBoard t){
		model=color;
		table=t;
	}
	
	public int calculation(){
		/**Soronkent megy vegig*/
		for(int i=0; i<5; i++){
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
			if(mine==4){
				//for()
			}
			if(yours==4){
				
			}
		}
		return 0;
	}
}
