
    public List<Suggestion> suggestions(String mot) {
        double probabiliteTypo = 20.;
        mot = mot.toLowerCase();
        MinHeap heap = new MinHeap();
        if (modele.containsKey(mot))
            heap.add(new Suggestion(mot, modele.get(mot)/1.));        
        for (String edit1:connus(modifications1(mot))) {
            heap.add(new Suggestion(edit1, modele.get(edit1)/probabiliteTypo));
        }        
        for (String edit2:modifications2Connues(mot)) {
            heap.add(new Suggestion(edit2, modele.get(edit2)/Math.pow(probabiliteTypo,3)));
        }        
        Collections.sort(heap.h, Collections.reverseOrder());
        if (heap.isEmpty())
            heap.add(new Suggestion(mot, 0.));
        return heap.h;
    }
    
    public String corriger(String mot) {
        return suggestions(mot).get(0).getMot();
    }
    
    public String corrigerPhrase(String phrase) {
        Pattern pattern = Pattern.compile("[\\p{L}]+");
        Matcher m = pattern.matcher(phrase);
        StringBuffer sb = new StringBuffer();  
        while (m.find())
        {  
          m.appendReplacement(sb, "");  
          sb.append(corriger(m.group()));
        }  
        m.appendTail(sb);  
        return sb.toString(); 
    }
    
    
    public static String lireFichier(String chemin) {
        String texte = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(chemin));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            texte = sb.toString();
            br.close();
        } catch (FileNotFoundException e) { 
            e.printStackTrace();
        } catch (IOException e) { 
            e.printStackTrace();
        }
        return texte;                
    }
     public String modifier(String mot) {
                
            int i = (int)(Math.random()*mot.length());
            String debut = mot.substring(0, i);
            String fin = mot.substring(i, mot.length());
            int lettre = (int)(Math.random()*alphabet.length());
            
            switch((int)(Math.random()*4)) {
            case 0: // Supression
                return debut + fin.substring(1);
            case 1: // Transposition
                if (fin.length() > 1)
                    return debut + fin.charAt(1) + fin.charAt(0) + fin.substring(2);
                else return modifier(mot);
            case 2: // Mutation
                return debut + alphabet.charAt(lettre) + fin.substring(1);
            case 3: // Insertion
                return debut + alphabet.charAt(lettre) + fin;
            default:
                return mot;
            }        
    }
    