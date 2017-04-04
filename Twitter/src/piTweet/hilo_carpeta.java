package piTweet;


import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class hilo_carpeta implements Runnable {
	private File directorio;
	private File archivo;
	private String ruta;
	private ArrayList ficheros;
	
	public hilo_carpeta(String ruta){
		this.ruta=ruta;
		directorio = new File(ruta);
		String[] ficheros_original = directorio.list();
		ficheros = new ArrayList<String>();
		try{
			for(int i=0; i<ficheros_original.length;i++){
				ficheros.add(ficheros_original[i]);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "El directorio no existe", "Error", 0);
		}
		
	}
	
	public void run(){
		try{
			comprobacion();	
		}catch(Exception e){
			System.out.println("sdfssduvdvd");
			JOptionPane.showMessageDialog(null, "El directorio no existe", "Error", 0);
			//System.exit(0);
		}
		
		
		while(true){
			
			try{
				Thread.sleep(1000);
			}catch(Exception e){}
			
		}
	}
	public void comprobacion(){
		Ventana_Main.lista_carpeta.removeAll();
		for(int i=0; i<ficheros.size();i++){
			//System.out.println(((String)ficheros.get(i)).substring(((String)ficheros.get(i)).length()-4, ((String)ficheros.get(i)).length()));
			if(((String)ficheros.get(i)).substring(((String)ficheros.get(i)).length()-4, ((String)ficheros.get(i)).length()).equals(".txt")){
				Ventana_Main.lista_carpeta.add(((String)ficheros.get(i)));
			}else{
				ficheros.remove(i);
				i--;
			}
				
		}
	}
	
	
}
