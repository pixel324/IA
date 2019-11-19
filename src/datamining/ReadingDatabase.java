package datamining;

import java.util.*;
import representations.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.io.*;

/**
 * Cette classe permet de lire une data puis de la convertir en Database
 */
public class ReadingDatabase{
	
	/** 
	 * Constructeur de la classe
	*/
	public ReadingDatabase(){
	}
	
	/** 
	 * Méthode qui fait une lecture du fichier
	 * @param file le chemin du fichier à lire
	 * @return les lignes du fichier
	*/
	public List<String> read(String file){
		File f = new File(file);
		Path reading = Paths.get(f.getAbsolutePath());
		Charset charset = Charset.forName("ISO-8859-1");
		List<String> lines = null;
		try {
			lines = Files.readAllLines(reading, charset);
		}catch (IOException e) {
		}
		return(lines);
	}
	
	/** 
	 * Méthode qui permet de savoir si un fichier existe
	 * @param file le chemin du fichier à lire
	 * @return si le fichier existe
	*/
	public boolean fileExist(String file){
		File f = new File(file);
		if(f.exists()){ 
			return true; 
        }
        return false;
	}
	
	/** 
	 * Méthode qui transforme notre fichier en Database
	 * @param file le chemin du fichier à lire
	 * @return la Database finale
	*/
	public Database transformDb(String file){
		List<Variable> variables = new ArrayList<Variable>();
		List<Map<Variable,String>> transacts = new ArrayList<Map<Variable,String>>();
		
		// réalisation des variables
		List<String> lines = this.read(file);
		for(String val : lines.get(0).split(";")){
			if(val!=""){
				Variable variable = new Variable(val,new HashSet<String>());
				variables.add(variable);
			}
		}
		
		// réalisation des instructions
		for(int i=1; i<lines.size();i++){
			int position = 0;
			Map<Variable,String> transaction = new HashMap<Variable,String>();
			for(String element : lines.get(i).split(";")){
				if(!variables.get(position).getDomain().contains(element)){
					variables.get(position).setDomain(element);
				}
				transaction.put(variables.get(position),element);
				position+=1;
			}
			transacts.add(transaction);
		}
		return new Database(variables,transacts);
	}
	
}
	
