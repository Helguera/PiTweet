package piTweet;

import java.util.ArrayList;
import java.util.Calendar;

public class Programados <Elemento extends Tweet> {
	private ArrayList<Tweet> Programados;
	
	public Programados (){
		Programados = new ArrayList<Tweet>();
	}
	
	private boolean noExiste (Tweet t){
		for (Tweet Elemento: Programados){
			if (Elemento.compareTweet(t)){
				return false;
			}
		}return true;
	}
	
	public void add(Tweet t){
		if(noExiste(t)){
			Programados.add(t);
			Ventana_Main.list.add(t.getTitulo());
		}else{
			for(Tweet Elemento: Programados){
				if (Elemento.getTitulo().equals(Ventana_Main.seleccionado)){
					Elemento.setText(t.getText());
					Elemento.setDate(t.getFecha());
					Elemento.setTitulo(t.getTitulo());
				}
			}
		}
		
	}
	
	public void remove(int posicion){
		Programados.remove(posicion);
	}
	
	public void remove(String texto){
		for(Tweet elemento: Programados){
			if (elemento.getTitulo().equals(texto)){
				Programados.remove(elemento);
				Ventana_Main.list.remove(texto);
			}
		}
		
	}
	
	public Tweet getTweet(String texto){
		Tweet A = new Tweet();
		for (Tweet E: Programados){
			if(E.getTitulo().equals(texto)){
				return E;
			}
		}return A;
	}
	
	public Tweet getTweet(int posicion){
		return Programados.get(posicion);
	}
	
	public String get(String texto){
		for (Tweet elemento: Programados){
			if(elemento.getTitulo().equals(texto)){
				return elemento.getText();
			}
		}return "";
	}
	
	
	public void toStringg(){
		for(Tweet elemento: Programados){
			System.out.println(elemento.getTitulo());
		}
	}
	
	public void actualizar(){
		Ventana_Main.list.removeAll();
		for(Tweet elemento: Programados){
			Ventana_Main.list.add(elemento.getTitulo());
		}
	}
	
	public int size(){
		return Programados.size();
	}
	
	/*public static void main (String args[]){
		Calendar fecha = Calendar.getInstance();
		Tweet t = new Tweet("hola", fecha);
		Programados Lista = new Programados();
		Lista.add(t);
		t=new Tweet("adios",fecha);
		Lista.add(t);
		Lista.toStringg();
	}*/
}
