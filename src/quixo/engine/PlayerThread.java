package quixo.engine;
import java.lang.management.ManagementFactory;

public class PlayerThread extends Thread {
	/**@playerName jatekos fajtaja*/
	protected String playerName;
	/**@sequence hanyadiknak lep a jatekos*/
	protected int sequence;
	/**@p jatekos*/
	protected QuixoPlayer p;
	/**@maxTime jatekos maximalis ideje*/
	protected long maxTime;
	/**@nextStep jatekos kovetkezo lepese*/
	protected Move nextStep;
	/**@t aktualis tabla*/
	protected QuixoBoard t;
	/**@passive passiv allapotban van a szal*/
	protected static final int passive=0;
	/**@active active allapotban van a szal*/
	protected static final int active=1;
	/**@exit vege a szalnak*/
	protected static final int exit=2;
	/**@status azt jelzi, hogy eppen milyen allapotban van a szal*/
	protected int status=passive;
	
	protected PlayerThread(int seq, long mT, String n) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		super();
		this.sequence=seq;
		this.maxTime=mT;
		this.playerName=n;
		p= (QuixoPlayer) Class.forName(playerName).newInstance();
	}
	
	/**Az adott szal adott futasa mennyi idot vett igenybe*/
	protected long getElapsedTime(){
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
	
	/**kezeli a szalat es ezaltal az idot is tudja merni, hogy mennyi ido alatt allitja be az adott jatekos adatait
	 * @param sequence hanyadiknak lephet a jatekos es egyben melyik mintaval van.
	 * @param time mennyi ideje van a jatekosnak
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException */
	protected void datas(int sequence, long time, long random, String h, int me, int you, int nobody) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		status=active;
		p.datas(sequence, time, random, h, me, you, nobody);
		status=passive;
	}
	
	/**Meri, hogy mennyi ido alatt szamolja ki az adott jatekos a kovetkezo lepeset
	 * @param prevStep az ellenfel utolso lepese*/
	protected Move nextMove(Move prevStep) {
		status=active;
		nextStep=p.nextMove(prevStep);
		status=passive;
		return nextStep;
	}
	
	protected int getColor() {
		return sequence;
	}
	
	/**Meri az idot mikozben letarolja a melyseget
	 * @param depth melyseg*/
	protected void setDepth(int d){
		status=active;
		p.setDepth(d);
		status=passive;
	}
	
	/**Szal befejezese*/
	protected void exit(){
		status=exit;
	}
}