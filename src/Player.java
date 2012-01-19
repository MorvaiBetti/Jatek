public interface Player{

	public void setTable(QuixoBoard qt);

	//hanyadik jatekos, ideje
	public void datas(int sequence, long time);
	
	//kovi lepest szamolja ki
	public Move nextMove();
	
	//A jatekos szinet/mintajat adja vissza
	public int getColor();

	public void setDepth(int d);
}