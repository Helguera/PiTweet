package piTweet;

import java.util.Date;

public class Tweet {
	private String texto;
	private Date fecha;
	
	public Tweet(String texto, Date fecha){
		this.texto=texto;
		this.fecha=fecha;
	}
	
	public String getText(){
		return texto;
	}
	
	public Date getFecha(){
		return fecha;
	}
	
	public void setText(String texto){
		this.texto=texto;
	}
	
	public void setDate(Date fecha){
		this.fecha=fecha;
	}
}
