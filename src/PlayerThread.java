import java.lang.management.ManagementFactory;

public class PlayerThread extends Thread implements Player {
	public String playerName;
	public int sequence;
	public Player p;
	public long maxTime;
	public Move prevStep=null;
	public Move nextStep;
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
	
	//Az adott szal adott futasa mennyi idot vett igenybe
	public long getElapsedTime(){
		return ManagementFactory.getThreadMXBean().getThreadUserTime(getId())/1000000;
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
	
	public Move nextMove(Move pS, long oTime) {
		status=active;
		nextStep=p.nextMove(pS, oTime);
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
	
	public void exit(){
		status=exit;
	}
}