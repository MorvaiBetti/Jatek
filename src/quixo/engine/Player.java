package quixo.engine;
public interface Player{

	public void setTable(QuixoBoard qt);

	/**hanyadik jatekos, ideje
	 * @param sequence Hanyadik jatekos
	 * @param time mennyi ideje van*/
	public void datas(int sequence, long time);
	
	/**kovi lepest szamolja ki
	 * @param prevStep az ellenfel utolso lepese*/
	public Move nextMove(Move prevStep);
	
	/**A jatekos szinet/mintajat adja vissza*/
	public int getColor();

	/**A fat milyen melysegig vizsgalja
	 * @param d melyseg*/
	public void setDepth(int d);
	
	/**A fanal melyik heurisztika szerint szamol erteket
	 * @param h heurisztika szama*/
	public void setHeuristic(int h);
}