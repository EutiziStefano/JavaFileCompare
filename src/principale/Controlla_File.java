package principale;

import javax.swing.JFrame;
import java.awt.Label;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import utility.file_RW_arraylist;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JCheckBox;

public class Controlla_File  extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3682694761753316669L;

	public static JFrame f;

	JFileChooser chooserA = new JFileChooser();
	JFileChooser chooserB = new JFileChooser();
	JFileChooser chooserDEST = new JFileChooser();

	Ascoltatore_aprielencoA listner_aprielencoA= new Ascoltatore_aprielencoA();
	Ascoltatore_aprielencoB listner_aprielencoB= new Ascoltatore_aprielencoB();
	Ascoltatore_aprielencoDEST listner_aprielencoDEST= new Ascoltatore_aprielencoDEST();
	Ascoltatore_Confronta listner_Confronta= new Ascoltatore_Confronta();

	ButtonGroup gruppo_tipo = new ButtonGroup();
	public JRadioButton rdbtnRecordComuni;
	public JRadioButton rdbtnRecordA_B;
	public JRadioButton rdbtnRecordB_A;

	public JCheckBox chckbxEliminaDuplicati;
	
	public Controlla_File() throws InterruptedException {
		
		//Thread.sleep(3000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setTitle("File Compare - 3 Click");
		setSize(470, 332);
		setLocationRelativeTo(null);

		f=this;

		getContentPane().setLayout(null);

		Label label = new Label("Select Files and compare method.");
		label.setBounds(10, 10, 414, 36);
		getContentPane().add(label);

		rdbtnRecordComuni = new JRadioButton("Common Record");
		rdbtnRecordComuni.setSelected(true);
		rdbtnRecordComuni.setBounds(10, 105, 180, 23);
		getContentPane().add(rdbtnRecordComuni);

		rdbtnRecordA_B = new JRadioButton("Record A - B");
		rdbtnRecordA_B.setBounds(10, 131, 180, 23);
		getContentPane().add(rdbtnRecordA_B);

		rdbtnRecordB_A = new JRadioButton("Record B - A");
		rdbtnRecordB_A.setBounds(10, 157, 180, 23);
		getContentPane().add(rdbtnRecordB_A);

		gruppo_tipo.add(rdbtnRecordComuni);
		gruppo_tipo.add(rdbtnRecordA_B);
		gruppo_tipo.add(rdbtnRecordB_A);


		JButton btnApriFileA = new JButton("Open file A");
		btnApriFileA.setBounds(20, 52, 130, 23);
		getContentPane().add(btnApriFileA);
		btnApriFileA.addActionListener(listner_aprielencoA);

		JButton btnApriFileB = new JButton("Open file B");
		btnApriFileB.setBounds(311, 52, 130, 23);
		getContentPane().add(btnApriFileB);
		btnApriFileB.addActionListener(listner_aprielencoB);

		JButton btnConfronta = new JButton("COMPARE");
		btnConfronta.setBounds(154, 260, 109, 23);
		getContentPane().add(btnConfronta);
		btnConfronta.addActionListener(listner_Confronta);
		
		JButton btnDirectoryDiDestinazione = new JButton("Destination Directory");
		btnDirectoryDiDestinazione.addActionListener(listner_aprielencoDEST);
		btnDirectoryDiDestinazione.setBounds(209, 118, 177, 48);
		getContentPane().add(btnDirectoryDiDestinazione);
		
		chckbxEliminaDuplicati = new JCheckBox("Delete duplicates");
		chckbxEliminaDuplicati.setBounds(10, 211, 169, 23);
		getContentPane().add(chckbxEliminaDuplicati);
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println("INIZIO");
		Controlla_File main;
		try {
			main = new Controlla_File();
			main.setVisible(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public class Ascoltatore_aprielencoA implements ActionListener {
		public void actionPerformed(ActionEvent event)
		{
			FileNameExtensionFilter filter = new FileNameExtensionFilter("File txt", "txt");
			chooserA.setFileFilter(filter);
			chooserA.showOpenDialog(f.getContentPane());
		}
	}
	public class Ascoltatore_aprielencoB implements ActionListener {
		public void actionPerformed(ActionEvent event)
		{
			FileNameExtensionFilter filter = new FileNameExtensionFilter("File txt", "txt");
			chooserB.setFileFilter(filter);
			chooserB.showOpenDialog(f.getContentPane());
		}
	}

	public class Ascoltatore_aprielencoDEST implements ActionListener {
		public void actionPerformed(ActionEvent event)
		{
			chooserDEST.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooserDEST.showOpenDialog(f.getContentPane());
		}
	}

	public class Ascoltatore_Confronta implements ActionListener {
		public void actionPerformed(ActionEvent event)
		{

			boolean ischeckeddup=chckbxEliminaDuplicati.isSelected();
			
			File fA = chooserA.getSelectedFile();
			File fB = chooserB.getSelectedFile();
			File FileDest=chooserDEST.getSelectedFile();

			if(fA==null){
				JOptionPane.showMessageDialog(f,"SELECT A FILE");
			}
			else if(fB==null){
				JOptionPane.showMessageDialog(f,"SELECT B FILE");
			}
			else if(FileDest==null){
				JOptionPane.showMessageDialog(f,"SELECT DESTINATION DIRECTORY");
			}
			else{

				String directoryDestinazione=FileDest.getAbsolutePath()+"\\";

				GregorianCalendar g=new GregorianCalendar();

				String file_output=directoryDestinazione+"Compare_"+g.get(Calendar.YEAR)+(g.get(Calendar.MONTH)+1)+g.get(Calendar.DAY_OF_MONTH)+"_"+g.get(Calendar.HOUR_OF_DAY)+g.get(Calendar.MINUTE)+".txt";

				boolean tuttook=false;
				
				if(rdbtnRecordComuni.isSelected()){
					try{
						if(ischeckeddup){
							file_RW_arraylist.calcola_comuni_elimina_duplicati(file_output, fA, fB);
						}
						else{
							file_RW_arraylist.calcola_comuni(file_output, fA, fB);
						}
						tuttook=true;
					}catch(Exception e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(f,"EXCEPTION: "+ e.getMessage());
					}catch (java.lang.OutOfMemoryError e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(f,"ERROR: "+ e.getMessage());
					}

				}
				if(rdbtnRecordA_B.isSelected()){
					try{
						
						if(ischeckeddup){
							file_RW_arraylist.calcola_differenza_elimina_duplicati(file_output, fA, fB);
						}
						else{
							file_RW_arraylist.calcola_differenza(file_output, fA, fB);
						}
						tuttook=true;
					}catch(Exception e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(f,"EXCEPTION: "+ e.getMessage());
					}catch (java.lang.OutOfMemoryError e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(f,"ERROR: "+ e.getMessage());
					}
				}
				if(rdbtnRecordB_A.isSelected()){
					try{
						if(ischeckeddup){
							file_RW_arraylist.calcola_differenza_elimina_duplicati(file_output, fB, fA);
						}
						else{
							file_RW_arraylist.calcola_differenza(file_output, fB, fA);
						}
						tuttook=true;
					}catch(Exception e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(f,"EXCEPTION: "+ e.getMessage());
					}catch (java.lang.OutOfMemoryError e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(f,"ERROR: "+ e.getMessage());
					}

				}

				if(tuttook){
					JOptionPane.showMessageDialog(f,"PROCESS FINISHED!\r\nGENERATED FILE: "+ file_output);
				}
				

			}
		}
	}
}
