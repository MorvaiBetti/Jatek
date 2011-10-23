public interface Player{

	public void setTable(QuixoBoard qt);

	//hanyadik jatekos, ideje
	public void datas(int sequence, long time);
	
	//megkapja az ellenfel utolso lepeset, idejet es a sajat idejet
	public Move nextMove(Move prevStep, long oTime);
	
	//A jatekos szinet/mintajat adja vissza
	public int getColor();
}