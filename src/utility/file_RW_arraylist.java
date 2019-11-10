package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JOptionPane;

import principale.Controlla_File;

public class file_RW_arraylist {

	public static void calcola_differenza(String file_destinazione,File Master, File Slave) {

		ArrayList<String> result=new ArrayList<String>();
	
		try {

			ArrayList<String> arr_master=carica_array_da_file(Master);
			ArrayList<String> arr_slave=carica_array_da_file(Slave);
			Collections.sort(arr_master);
			Collections.sort(arr_slave);
			
			int size_M=arr_master.size();
			int size_S=arr_slave.size();
			int trovato=0;

			for(int iM=0;iM<size_M;iM++){

				String m=arr_master.get(iM);
				int j;
				for(j=trovato;j<size_S;j++){

					String s=arr_slave.get(j);
					int compare=m.compareTo(s);

					if(compare==0){
						trovato=j+1;
						break;
					}
					else{
						if(compare<0){
							result.add(m);
							break;
						}		
					}
				}
				if(j>=size_S){
					result.add(m);
				}
				if(trovato>=size_S){
					result.add(m);
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(Controlla_File.f,"Errore durante la lettura dei file di origine! - "+e.getMessage());
			e.printStackTrace();	
		}
		try{

			salva_array_to_file(result,file_destinazione);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Controlla_File.f,"Errore durante la scrittura del file risultato: "+file_destinazione+" - "+e.getMessage());
			e.printStackTrace();
		}
	}

	
	public static void calcola_differenza_elimina_duplicati(String file_destinazione,File Master, File Slave) {

		ArrayList<String> result=new ArrayList<String>();
		
		try {

			ArrayList<String> arr_master=carica_array_da_file(Master);
			ArrayList<String> arr_slave=carica_array_da_file(Slave);
			Collections.sort(arr_master);
			Collections.sort(arr_slave);
			
			int size_M=arr_master.size();
			int size_S=arr_slave.size();
			int trovato=0;

			String m_prec="";
			
			for(int iM=0;iM<size_M;iM++){

				String m=arr_master.get(iM);
				int j;
				for(j=trovato;j<size_S;j++){

					String s=arr_slave.get(j);
					int compare=m.compareTo(s);

					if(compare==0){
						trovato=j;
						break;
					}
					else{
						if(compare<0){
							if(!m.equals(m_prec)){
								result.add(m);
								m_prec=m;
							}
							break;
						}		
					}
				}
				if(j>=size_S){
					result.add(m);
				}
				if(trovato>=size_S){
					result.add(m);
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(Controlla_File.f,"Errore durante la lettura dei file di origine! - "+e.getMessage());
			e.printStackTrace();	
		}
		try{

			salva_array_to_file(result,file_destinazione);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Controlla_File.f,"Errore durante la scrittura del file risultato: "+file_destinazione+" - "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public static void calcola_comuni(String file_destinazione,File Master, File Slave) {
		ArrayList<String> result=new ArrayList<String>();
		
		try {

			ArrayList<String> arr_master=carica_array_da_file(Master);
			ArrayList<String> arr_slave=carica_array_da_file(Slave);

			Collections.sort(arr_master);
			Collections.sort(arr_slave);
			
			int size_M=arr_master.size();
			int size_S=arr_slave.size();
			int trovato=0;

			for(int iM=0;iM<size_M;iM++){

				String m=arr_master.get(iM);

				for(int j=trovato;j<size_S;j++){

					String s=arr_slave.get(j);
					int compare=m.compareTo(s);

					if(compare==0){
						result.add(m);
						trovato=j+1;
						break;
					}
					else{
						if(compare<0){
							break;
						}		
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Controlla_File.f,"Errore durante la lettura dei file di origine! - "+e.getMessage());
		}
		try{

			salva_array_to_file(result,file_destinazione);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Controlla_File.f,"Errore durante la scrittura del file risultato: "+file_destinazione+" - "+e.getMessage());
		}
	}
	
	public static void calcola_comuni_elimina_duplicati(String file_destinazione,File Master, File Slave) {
		ArrayList<String> result=new ArrayList<String>();
		
		try {

			ArrayList<String> arr_master=carica_array_da_file(Master);
			ArrayList<String> arr_slave=carica_array_da_file(Slave);

			Collections.sort(arr_master);
			Collections.sort(arr_slave);
			
			int size_M=arr_master.size();
			int size_S=arr_slave.size();
			int trovato=0;
			String m_prec="";

			for(int iM=0;iM<size_M;iM++){

				String m=arr_master.get(iM);

				for(int j=trovato;j<size_S;j++){

					String s=arr_slave.get(j);
					int compare=m.compareTo(s);
					
					if(compare==0){
						if(!m.equals(m_prec)){
							result.add(m);
							m_prec=m;
						}
						trovato=j;
						break;
					}
					else{
						if(compare<0){
							break;
						}		
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Controlla_File.f,"Errore durante la lettura dei file di origine! - "+e.getMessage());
		}
		try{

			salva_array_to_file(result,file_destinazione);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Controlla_File.f,"Errore durante la scrittura del file risultato: "+file_destinazione+" - "+e.getMessage());
		}
	}
	
	
	public static ArrayList<String> carica_array_da_file(File f) throws Exception{

		FileReader fr=new FileReader(f);
		BufferedReader b=new BufferedReader(fr);
		ArrayList<String> result=new ArrayList<String>();
		String read=b.readLine();

		while(read!=null) {
			if(!read.equals(""))
				result.add(read);

			read=b.readLine();
		}
		b.close();

		return result;
	}

	public static boolean salva_array_to_file(ArrayList<String> arr,String nomefile) throws FileNotFoundException {
		try {
			File file = new File(nomefile);
			FileOutputStream fos = new FileOutputStream(file,false);
			OutputStreamWriter osw = new OutputStreamWriter(fos);

			Iterator<String> it=arr.iterator();
			while(it.hasNext()){
				osw.write(it.next()+System.getProperty("line.separator"));
			}
			osw.flush();
			osw.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new FileNotFoundException(nomefile);
		}
		return true;
	}

	

}
