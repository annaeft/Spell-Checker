public void evaluer(String texte, int nombreMotsMax, int biais) {
        int n = 0, fautes = 0, inconnus=0;
        for (String mot:mots(texte)) {
            if (mot.length() > 4) {
                mot = mot.toLowerCase();
                n++;
                if (biais >0)
                    modele.put(mot, modele.get(mot)+biais);
                String faute = modifier(mot);
                String correction = corriger(faute);
                if (!correction.equals(mot)) {
                    fautes++;
                    if (!modele.containsKey(mot)){
                        inconnus++;
                        //System.out.println(mot + " inconnu !");
                    }
                }
                if (n>=nombreMotsMax)
                    break;
                //System.out.println(faute + " ->" + correction + "(" + modele.get(correction) + "), " + mot + " attendu (" + modele.get(mot) + ")");
            }
        }
        System.out.println(fautes + " fautes sur " + n + " mots, avec " + inconnus + " inconnus (" + (100*fautes)/n + "%, " + (100*(fautes-inconnus))/(n-inconnus) + "%)");
    }
    
    public double probabilitePhrase(String phrase) {
        double produit = 1;
        String[] mots = mots(phrase);
        for (String mot:mots)
            produit *= modele.get(mot);
        return produit;
    }
    
  
    public static Langage predireLangage(String phrase, Langage[] langages) {
        Langage langageMax = null;
        double probaMax = 0;
        for (Langage l:langages) {
            if (l.probabilitePhrase(phrase) > probaMax) {
                probaMax = l.probabilitePhrase(phrase);
                langageMax = l;
            }
        }
        return langageMax;
    }
    
    
    public static Langage francais = new Langage("Francais", "abcdefghijklmnopqrstuvwxyzaaaeeeiiocu", 
              new String[]{"corpus/fr/dictionnaire.txt",
                           "corpus/fr/miserables1.txt", 
                           "corpus/fr/miserables2.txt", 
                           "corpus/fr/miserables3.txt", 
                           "corpus/fr/miserables4.txt",
                           "corpus/fr/miserables5.txt",
                           "corpus/fr/assomoir.txt",
                           "corpus/fr/germinal.txt",
                           "corpus/fr/largent.txt",
                           "corpus/fr/swann.txt",
                           "corpus/fr/rougeetnoir.txt",
						   "corpus/fr/lestroismousquetaires.txt",
						   "corpus/fr/madamebovary.txt"});
    /**
     * Integration du corpus anglais
     */
    public static Langage anglais = new Langage("Anglais", "abcdefghijklmnopqrstuvwxyz", 
              new String[]{"corpus/en/dictionary.txt",
                           "corpus/en/henriVI.txt",
                           "corpus/en/hamlet.txt",
                           "corpus/en/macbeth.txt",
                           "corpus/en/alice.txt",
                           "corpus/en/sherlock.txt",
                           "corpus/en/greatexpectations.txt",
                           "corpus/en/secretadversary.txt",
                           "corpus/en/mobydick.txt",
                           "corpus/en/frankenstein.txt"});