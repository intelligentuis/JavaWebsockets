

import java.rmi.Naming;
import java.util.*;



public class Game
{
	private static final String KEY = "rmi://localhost:10101/";
	String id = "#"+UUID.randomUUID();

	public  void main(String[] args) {
		
		Player p = new Player();
		send(p);
	}

    public void send(Player p)
	{
		 try {
            Map<String,Player> players = (HashMap<String,Player>) Naming.lookup(KEY + "P");
            players.put(id,p);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}