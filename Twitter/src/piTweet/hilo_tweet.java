package piTweet;

import java.io.File;
import java.util.Calendar;

import javax.swing.JOptionPane;

import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

public class hilo_tweet implements Runnable {
	private Calendar actual;

	public hilo_tweet() {
	}

	public void run() {
		do {
			for (int i = 0; i < Ventana_Main.Lista.size(); i++) {
				actual = Calendar.getInstance();
				if (actual.compareTo(Ventana_Main.Lista.getTweet(i).getFecha()) >= 0) {
					try {
						if(Ventana_Main.Lista.getTweet(i).getImage()!=null){
							StatusUpdate prueba = new StatusUpdate(Ventana_Main.Lista.getTweet(i).getText());
							prueba.setMedia(new File(Ventana_Main.Lista.getTweet(i).getImage()));
							Ventana_Main.twitter.updateStatus(prueba);
						}else{
							Ventana_Main.twitter.updateStatus(Ventana_Main.Lista.getTweet(i).getText());
						}
					} catch (TwitterException te) {
						JOptionPane.showMessageDialog(null, "Algo ha salido mal, comprueba las Tokens", "Error", 0);
					}
					Ventana_Main.Lista.remove(i);
					Ventana_Main.Lista.actualizar();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception o) {}
		} while (true);
	}
}
