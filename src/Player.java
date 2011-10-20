public interface Player{
	
	public void beallitTablat(QuixoBoard qt);
	
	//hanyadik jatekos, ideje
	public void adatok(int sorrend, long ido);
	
	//megkapja az ellenfel utolso lepeset, idejet es a sajat idejet
	public Move kovLepes(Move elozoLepes, long ido);
	
	//A jatekos szinet/mintajat adja vissza
	public int getSzin();
}