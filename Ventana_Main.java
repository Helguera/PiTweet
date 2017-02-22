package piTweet;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.TextArea;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import twitter4j.conf.*;
import twitter4j.*;
import java.awt.Label;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Ventana_Main extends JFrame {

	private String oAuthConsumerKey = "";
	private String oAuthConsumerSecret = "";
	private String oAuthAccessToken = "";
	private String oAuthAccessTokenSecret = "";
	private Twitter twitter;
	public static String seleccionado;
	private FormatoFecha formato;
	private String imagen = null;
	
	private JPanel contentPane;
	private JPasswordField pwdCk;
	private JPasswordField pwdSck;
	private JPasswordField pwdAt;
	private JPasswordField pwdSat;
	private JTextField txtusuario;
	private TextArea textAreaMain;
	private JLabel lblCaracteres;
	private int num_caracteres;
	private JCheckBox chckbxProgramar;
	public static List list;
	private JFormattedTextField frmtdtxtfldFecha;
	private JButton btnEditar;
	private JButton btnCancelar;
	private JPanelBackground panel;
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Main frame = new Ventana_Main();
					frame.setVisible(true);
					frame.setCaracteres(frame.getCaracteres());
					//hilo_tweet hilo = new hilo_tweet();
					//new Thread(hilo).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana_Main() {
		Programados Lista = new Programados();
		
		setTitle("PiTweet");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsumerkey = new JLabel("Consumer Key");
		lblConsumerkey.setBounds(31, 12, 120, 15);
		contentPane.add(lblConsumerkey);
		
		JLabel lblConsumer_Secret = new JLabel("Secret Consumer Key");
		lblConsumer_Secret.setBounds(31, 34, 163, 15);
		contentPane.add(lblConsumer_Secret);
		
		JLabel lblAccesToken = new JLabel("Acces Token");
		lblAccesToken.setBounds(31, 55, 100, 15);
		contentPane.add(lblAccesToken);
		
		JLabel lblNewLabel = new JLabel("Secret Acces Token");
		lblNewLabel.setBounds(31, 75, 151, 15);
		contentPane.add(lblNewLabel);
		
		pwdCk = new JPasswordField();
		pwdCk.setBounds(228, 8, 275, 19);
		contentPane.add(pwdCk);
		
		pwdSck = new JPasswordField();
		pwdSck.setText("");
		pwdSck.setBounds(228, 30, 275, 19);
		contentPane.add(pwdSck);
		
		pwdAt = new JPasswordField();
		pwdAt.setBounds(228, 51, 275, 19);
		contentPane.add(pwdAt);
		
		pwdSat = new JPasswordField();
		pwdSat.setText("");
		pwdSat.setBounds(228, 71, 275, 19);
		contentPane.add(pwdSat);
		
		JRadioButton rdbtnTweet = new JRadioButton("Tweet");
		rdbtnTweet.setSelected(true);
		rdbtnTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtusuario.setEnabled(false);
			}
		});
		rdbtnTweet.setBounds(361, 102, 149, 23);
		contentPane.add(rdbtnTweet);
		
		JRadioButton rdbtnMensajeDirecto = new JRadioButton("Mensaje Directo");
		rdbtnMensajeDirecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtusuario.setEnabled(true);
			}
		});
		rdbtnMensajeDirecto.setBounds(361, 122, 149, 23);
		contentPane.add(rdbtnMensajeDirecto);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMensajeDirecto);
		group.add(rdbtnTweet);
		
		txtusuario = new JTextField();
		txtusuario.setEnabled(false);
		txtusuario.setText("@Usuario");
		txtusuario.setBounds(386, 145, 114, 19);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Configuracion();
				if(!chckbxProgramar.isSelected()){
					if(rdbtnTweet.isSelected()){
						try{
							twitter.updateStatus(textAreaMain.getText());
							JOptionPane.showMessageDialog(null, "Tweet enviado", "", 1);
						}catch(TwitterException te){
							JOptionPane.showMessageDialog(null, "Algo ha salido mal, comprueba las Tokens", "Error", 0);
						}
						
					}
					if(rdbtnMensajeDirecto.isSelected()){
						try {
				            DirectMessage message = twitter.sendDirectMessage(txtusuario.getText(), textAreaMain.getText());
				            JOptionPane.showMessageDialog(null, "Mensaje Directo enviado", "", 1);
				        } catch (TwitterException te) {
				        	JOptionPane.showMessageDialog(null, "Algo ha salido mal, comprueba las Tokens", "Error", 0);
				        }
					}
				}else{
					String fecha_string = frmtdtxtfldFecha.getText();
					Date fecha = new Date();
					try{
						formato = new FormatoFecha();
						fecha =(Date)formato.stringToValue(fecha_string);
					}catch(Exception f){
						
					}
					
					Calendar calendario = Calendar.getInstance();
					calendario.setTime(fecha);
					Calendar actual = Calendar.getInstance();
					if(actual.compareTo(calendario)<0){
						Tweet t;
						if (btnCancelar.isEnabled()){
							t = Lista.getTweet(seleccionado);
							t.setDate(calendario);
							t.setText(textAreaMain.getText());
							t.setTitulo(textAreaMain.getText());
							if(imagen!=null){
								t.setImage(imagen);
							}
							Lista.actualizar();
							
						}else{
							t = new Tweet(textAreaMain.getText(),calendario,imagen);
							try{
								Lista.add(t);
								textAreaMain.setText("");
								imagen=null;
								panel.setBackground(null, 0, 0);
							}catch(Exception w){
							}
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "Fecha no valida", "Error", 0);
					}
				}
			}
		});
		btnEnviar.setBounds(31, 190, 272, 25);
		contentPane.add(btnEnviar);
		
		lblCaracteres = new JLabel("");
		lblCaracteres.setBounds(308, 190, 63, 25);
		contentPane.add(lblCaracteres);
		
		textAreaMain = new TextArea();
		textAreaMain.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				if (textAreaMain.getText().length() > 140) {
					JOptionPane.showMessageDialog(null, "No puede superar los 140 caracteres", "Error", 0);
					do{
						textAreaMain.setText(textAreaMain.getText().substring(0, textAreaMain.getText().length()-1));
					}while(textAreaMain.getText().length()>140);
				}	
				lblCaracteres.setText(Integer.toString(textAreaMain.getText().length()) + "/140");	
			}});
		textAreaMain.setBounds(31, 96, 324, 88);
		contentPane.add(textAreaMain);
		

		
		JLabelLink lblTutorial = new JLabelLink();
		lblTutorial.setBounds(374, 506, 129, 21);
		lblTutorial.setText("©Javier Helguera");
		lblTutorial.setTextLink("©Javier Helguera");
		lblTutorial.setLink("https://github.com/Helguera/piTweet");
		contentPane.add(lblTutorial);
		
		JLabelLink lblDonate = new JLabelLink();
		lblDonate.setBounds(31, 506, 92, 21);
		lblDonate.setText("Donate Here");
		lblDonate.setTextLink("Donate Here");
		lblDonate.setLink("https://www.paypal.me/Helguera");
		contentPane.add(lblDonate);
		
		try{
			frmtdtxtfldFecha = new JFormattedTextField(new FormatoFecha());
			frmtdtxtfldFecha.setEnabled(false);
			frmtdtxtfldFecha.setText("dd/mm/yy hh:mm:ss");
			frmtdtxtfldFecha.setBounds(386, 196, 117, 19);
			contentPane.add(frmtdtxtfldFecha);
		}catch(Exception e){
		}
		
		chckbxProgramar = new JCheckBox("Programar");
		chckbxProgramar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxProgramar.isSelected()){
					btnEnviar.setText("Guardar");
					frmtdtxtfldFecha.setEnabled(true);
				}else{
					btnEnviar.setText("Enviar");
					frmtdtxtfldFecha.setEnabled(false);
				}
			}
		});
		chckbxProgramar.setBounds(361, 172, 103, 23);
		contentPane.add(chckbxProgramar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Imagen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(29, 356, 378, 150);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAddImagen = new JButton("Añadir Imagen");
		btnAddImagen.setBounds(231, 22, 136, 25);
		panel_1.add(btnAddImagen);
		
		panel = new JPanelBackground();
		panel.setBounds(12, 22, 211, 122);
		panel_1.add(panel);
		
		JButton btnEliminarImagen = new JButton("Borrar");
		btnEliminarImagen.setBounds(231, 52, 136, 25);
		panel_1.add(btnEliminarImagen);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Programados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(29, 223, 474, 128);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		list = new List();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionado=list.getSelectedItem();
			}
		});
		list.setBounds(10, 21, 348, 97);
		panel_2.add(list);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(366, 57, 96, 25);
		panel_2.add(btnEliminar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(366, 21, 96, 25);
		panel_2.add(btnEditar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelar.setEnabled(false);
				try{
					FormatoFecha prueba = new FormatoFecha();
					textAreaMain.setText("");
					Calendar hoy = Calendar.getInstance();
					frmtdtxtfldFecha.setText(prueba.valueToString(hoy.getTime()));
					imagen=null;
					panel.setBackground(null, 0, 0);
				}catch(Exception o){}
			}
		});
		btnCancelar.setBounds(366, 93, 96, 25);
		panel_2.add(btnCancelar);
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getItemCount()>0){
					btnCancelar.setEnabled(true);
				}
				try{
					FormatoFecha prueba = new FormatoFecha();
					textAreaMain.setText(Lista.get(seleccionado));
					frmtdtxtfldFecha.setText(prueba.valueToString(Lista.getTweet(seleccionado).getFecha().getTime()));
				}catch(Exception o){}
				ImageIcon h = new ImageIcon(Lista.getTweet(seleccionado).getImage());
				panel.setBackground(Lista.getTweet(seleccionado).getImage(),h.getIconWidth(), h.getIconHeight());
				
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelar.setEnabled(false);
				if(list.getItemCount()>0){
					try{
						Lista.remove(seleccionado);
						Lista.toStringg();
						
					}catch (Exception f){}
					
					try{
						FormatoFecha prueba = new FormatoFecha();
						textAreaMain.setText("");
						Calendar hoy = Calendar.getInstance();
						frmtdtxtfldFecha.setText(prueba.valueToString(hoy.getTime()));
						imagen=null;
						panel.setBackground(null, 0, 0);
					}catch(Exception r){}
				}
			}
		});
		btnAddImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jpg", "jpg",".png","png");
				selector.setFileFilter(filtro);
				int seleccion = selector.showOpenDialog(contentPane);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = selector.getSelectedFile();
					imagen=fichero.getAbsolutePath();
					ImageIcon h = new ImageIcon(fichero.getAbsolutePath());
					panel.setBackground(fichero.getAbsolutePath(),h.getIconWidth(), h.getIconHeight());
				}
			}
		});
		
		btnEliminarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imagen=null;
				panel.setBackground(null, 0, 0);
				Lista.getTweet(seleccionado).setImage(null);
			}
		});
		
		

		
	}
	
	private int getCaracteres(){
		return textAreaMain.getText().length();
	}
	
	private void setCaracteres(int caracteres){
		lblCaracteres.setText(caracteres+"/140");
	}
	
	private void Configuracion(){
		char[] auxiliar=pwdCk.getPassword();
		oAuthConsumerKey = new String(auxiliar);
		auxiliar=pwdSck.getPassword();
		oAuthConsumerSecret = new String(auxiliar);
		auxiliar=pwdAt.getPassword();
		oAuthAccessToken = new String(auxiliar);
		auxiliar=pwdSat.getPassword();
		oAuthAccessTokenSecret = new String(auxiliar);
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(oAuthConsumerKey);
		cb.setOAuthConsumerSecret(oAuthConsumerSecret);
		cb.setOAuthAccessToken(oAuthAccessToken);
		cb.setOAuthAccessTokenSecret(oAuthAccessTokenSecret);

		
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}
}
