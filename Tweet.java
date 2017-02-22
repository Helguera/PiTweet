package piTweet;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Tweet {
	private String titulo;
	private String texto;
	private Calendar fecha;
	private SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yy  HH:mm");
	private String imagen;
	
	
	public Tweet(String texto, Calendar fecha, String imagen){
		this.texto=texto;
		this.imagen=imagen;
		titulo=format1.format(fecha.getTime())+" --> "+texto;
		if (texto.length()>25){
			titulo=format1.format(fecha.getTime())+" --> "+texto.substring(0,25)+"...";
		}
		this.fecha=fecha;
	}
	
	public Tweet(){
		
	}
	
	public String getText(){
		return texto;
	}
	
	public Calendar getFecha(){
		return fecha;
	}
	
	public String fechaToString(){
		System.out.println(fecha.getTime().toString());
		String formatted = format1.format(fecha.getTime());
		return formatted;
		
	}
	
	public void setText(String texto){
		this.texto=texto;
	}
	
	public void setDate(Calendar fecha){
		this.fecha=fecha;
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public void setTitulo(String texto){
		titulo=format1.format(fecha.getTime())+" --> "+texto;
		if (texto.length()>25){
			titulo=format1.format(fecha.getTime())+" --> "+texto.substring(0,25)+"...";
		}
	}
	
	public boolean compareTweet(Tweet t){
		if(this.getText().equals(t.getText()) && this.getFecha().compareTo(t.getFecha())==0){
			return true;
		}else return false;
	}
	
	public void setImage(String imagen){
		this.imagen=imagen;
	}
	
	public String getImage(){
		return imagen;
	}
}
