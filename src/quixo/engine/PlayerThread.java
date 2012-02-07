package quixo.engine;
import java.lang.management.ManagementFactory;

public class PlayerThread extends Thread {
	/**@playerName jatekos fajtaja*/
	public String playerName;
	/**@sequence hanyadiknak lep a jatekos*/
	public int sequence;
	public Player p;
	/**@maxTime jatekos maximalis ideje*/
	public long maxTime;
	/**@nextStep jatekos kovetkezo lepese*/
	public Move nextStep;
	/**@t aktualis tabla*/
	public QuixoBoard t;
	/**@passive passiv allapotban van a szal*/
	public static final int passive=0;
	/**@active active allapotban van a szal*/
	public static final int active=1;
	/**@exit vege a szalnak*/
	public static final int exit=2;
	/**@status azt jelzi, hogy eppen milyen allapotban van a szal*/
	public int status=passive;
	
	public PlayerThread(int seq, long mT, String n) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		super();
		this.sequence=seq;
		this.maxTime=mT;
		this.playerName=n;
		p= (Player) Class.forName(playerName).newInstance();
	}
	
	/**Az adott szal adott futasa mennyi idot vett igenybe*/
	public long getElapsedTime(){
		return ManagementFactory.getThreadMXBean().getThreadUserTime(getId())/1000000000;
	}
	
	public void run(){
		try {
			while(true){
				switch(status){
					case active: {
						break;
					}
					case exit: {
						return;
					}
					default: {
						sleep(1);
					}
				}
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	/**kezeli a szalat és ezaltal az idot is tudja merni, hogy mennyi ido alatt allitja be az adott jatekos adatait
	 * @param sequence hanyadiknak lephet a jatekos es egyben melyik mintaval van.
	 * @param time mennyi ideje van a jatekosnak*/
	public void datas(int sequence, long time) {
		status=active;
		p.datas(sequence, time);
		status=passive;
	}
	
	/**Meri, hogy mennyi ido alatt szamolja ki az adott jatekos a kovetkezo lepeset
	 * @param prevStep az ellenfel utolso lepese*/
	public Move nextMove(Move prevStep) {
		status=active;
		nextStep=p.nextMove(prevStep);
		status=passive;
		return nextStep;
	}
	
	public int getColor() {
		return sequence;
	}
	
	/**Meri az idot mikozben letarolja a melyseget
	 * @param depth melyseg*/
	public void setDepth(int d){
		status=active;
		p.setDepth(d);
		status=passive;
	}
	
	/**Meri az idot mikozben letarolja, hogy melyik heurisztikat hasznaljuk
	 * @param h melyik heurisztikat hasznaljuk.*/
	public void setHeuristic(int h){
		status=active;
		p.setHeuristic(h);
		status=passive;
	}
	
	/**Szal befejezese*/
	public void exit(){
		status=exit;
	}
}