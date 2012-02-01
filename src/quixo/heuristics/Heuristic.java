package quixo.heuristics;

public interface Heuristic {
	/**Kiszamolja az adott tabla erteket*/
	public void calculation();
	
	/**Visszaadja a kiszamolt tabla erteket.*/
	public int setValue();
}
