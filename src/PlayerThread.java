import java.lang.management.ManagementFactory;

public class PlayerThread extends Thread implements Player{
	public String jatekosNeve;
	public int sorrend;
	public Player p;
	public long maxIdo;
	public long elteltIdo=0;
	public int kovMunka;
	public Move elozoLepes=null;
	public Move kLepes;
	public QuixoBoard t;
	public static final int passziv=1;
	public static final int aktiv=0;
	public static final int kilepes=2;
	public int allapot=passziv;
	
	public PlayerThread(int sor, long mI, String n) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		super();
		this.sorrend=sor;
		this.maxIdo=mI;
		this.jatekosNeve=n;
		p= (Player) Class.forName(jatekosNeve).newInstance();
	}
	
	//Az adott szal adott futasa mennyi idot vett igenybe
	public long getElteltIdo(){
	  return ManagementFactory.getThreadMXBean().getThreadUserTime(getId()) / 1000000;
	}
	
	public void run(){
		try {
			while(true){
				switch(allapot){
					case aktiv: {
						break;
					}
					case kilepes: {
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
	
	public void adatok(int sorrend, long ido) {
		allapot=aktiv;
		p.adatok(sorrend, ido);
		allapot=passziv;
	}

	public Move kovLepes(Move eL, long ido) {
		allapot=aktiv;
		System.out.println(getName() + ", allapot: " + allapot);
		kLepes=p.kovLepes(elozoLepes, elteltIdo);
		allapot=passziv;
		return kLepes;
	}

	public int getSzin() {
		return sorrend;
	}
	
	public void beallitTablat(QuixoBoard t) {
		allapot=aktiv;
		p.beallitTablat(t);
		allapot=passziv;
	}
	
	public void kilep(){
		allapot=kilepes;
	}
}
