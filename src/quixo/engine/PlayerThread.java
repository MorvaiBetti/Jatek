package quixo.engine;
import java.lang.management.ManagementFactory;

public class PlayerThread extends Thread implements Player {
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
	public static final int passive=0;
	public static final int active=1;
	public static final int exit=2;
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
	
	public void datas(int sequence, long time) {
		status=active;
		p.datas(sequence, time);
		status=passive;
	}
	
	public Move nextMove(Move prevStep) {
		status=active;
		nextStep=p.nextMove(prevStep);
		status=passive;
		return nextStep;
	}
	
	public int getColor() {
		return sequence;
	}
	
	public void setTable(QuixoBoard t) {
		status=active;
		p.setTable(t);
		status=passive;
	}
	public void setDepth(int d){
		p.setDepth(d);
	}
	
	public void exit(){
		status=exit;
	}
}