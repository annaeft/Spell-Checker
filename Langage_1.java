import java.awt.Event;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Character.Subset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.*;


public class Langage {
    
    public Dictionnaire modele = new Dictionnaire(1);
    
    
    public Langage(String nom, String alphabet, String[] corpus) {
        this.nom = nom;
        this.alphabet = alphabet;
        for (String oeuvre:corpus)
            apprendre(mots(lireFichier(oeuvre)));
    }
    
    
    public static String[] mots(String texte) {
        return texte.split("[^\\p{L}]+");
    }
    
   
    public void apprendre(String [] features){
        for (String f : features)
            modele.put(f.toLowerCase(), modele.get(f.toLowerCase())+1);
    }
    
    public Set<String> connus(Set<String> mots) {
        Set<String> connus = new HashSet<String>();
        for (String mot : mots)
            if (modele.containsKey(mot))
                connus.add(mot);
        return connus;
    }
    
	
	public Set<String> modifications1(String mot){        
		Set<String> modifications = new HashSet<String>();    
		String[][] separations =  new String[mot.length() + 1][2];
		for (int i=0; i<mot.length()+1; i++){
			separations[i][0] = mot.substring(0, i);
			separations[i][1] = mot.substring(i, mot.length());
		}
		for (int i=0; i<separations.length; i++) {
			// Supressions
			if (!separations[i][1].isEmpty())
				modifications.add(separations[i][0] + separations[i][1].substring(1));
			// Transpositions
			if (separations[i][1].length() > 1)
				modifications.add(separations[i][0] + separations[i][1].charAt(1) + separations[i][1].charAt(0) + separations[i][1].substring(2));
			// Mutations
			if (!separations[i][1].isEmpty())
				for (int j=0; j<alphabet.length(); j++)
					modifications.add(separations[i][0] + alphabet.charAt(j) + separations[i][1].substring(1));
			// Insertions
			for (int j=0; j<alphabet.length(); j++)
				modifications.add(separations[i][0] + alphabet.charAt(j) + separations[i][1]);            
		}
	   return modifications;
	}
     
     public Set<String> modifications2Connues(String mot) {
            Set<String> modifications = new HashSet<String>();
            for (String edit1:modifications1(mot))
                for (String edit2:modifications1(edit1))
                    if (modele.containsKey(edit2))
                        modifications.add(edit2);
            return modifications;
        }    
    