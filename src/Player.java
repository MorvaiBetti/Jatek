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

	public void setDepth(int d);
}