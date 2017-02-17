package piTweet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;

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

import twitter4j.conf.*;
import twitter4j.*;
import java.awt.Label;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JList;

public class Ventana_Main extends JFrame {

	private String oAuthConsumerKey = "";
	private String oAuthConsumerSecret = "";
	private String oAuthAccessToken = "";
	private String oAuthAccessTokenSecret = "";
	Twitter twitter;
	
	private JPanel contentPane;
	private JPasswordField pwdCk;
	private JPasswordField pwdSck;
	private JPasswordField pwdAt;
	private JPasswordField pwdSat;
	private JTextField txtusuario;
	private TextArea textAreaMain;
	private JLabel lblCaracteres;
	private int num_caracteres;
	JFormattedTextField frmtdtxtfldFecha;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Main frame = new Ventana_Main();
					frame.setVisible(true);
					//do{
						frame.setCaracteres(frame.getCaracteres());
					//}while(true);
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
		setTitle("PiTweet");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 376);
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
		pwdSck.setBounds(227, 30, 276, 19);
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
		lblTutorial.setBounds(380, 243, 129, 21);
		lblTutorial.setText("©Javier Helguera");
		lblTutorial.setTextLink("©Javier Helguera");
		lblTutorial.setLink("https://github.com/Helguera");
		contentPane.add(lblTutorial);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 227, 471, 2);
		contentPane.add(separator);
		
		try{
			frmtdtxtfldFecha = new JFormattedTextField(new FormatoFecha());
			frmtdtxtfldFecha.setEnabled(false);
			frmtdtxtfldFecha.setText("dd/mm/yy hh:mm:ss");
			frmtdtxtfldFecha.setBounds(386, 196, 117, 19);
			contentPane.add(frmtdtxtfldFecha);
		}catch(Exception e){
		}
		
		JCheckBox chckbxProgramar = new JCheckBox("Programar");
		chckbxProgramar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxProgramar.isSelected()){
					frmtdtxtfldFecha.setEnabled(true);
				}else{
					frmtdtxtfldFecha.setEnabled(false);
				}
			}
		});
		chckbxProgramar.setBounds(361, 172, 103, 23);
		contentPane.add(chckbxProgramar);
		
		CustomListModel list_model = new CustomListModel();
		//lista.setModel(list_model);
		
		
		
		
		
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
