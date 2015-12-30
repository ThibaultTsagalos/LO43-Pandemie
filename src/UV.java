import java.util.ArrayList;

public class UV
{

    private int position;
    private String nom;
    private Filiere filiere;
    private Marqueur marqueur[];
    private ArrayList<UV> voisins;
    private Professeur professeur;
    private ArrayList<Personnage> personnages;
    private boolean testEclosion; // Permet de ne pas repasser plusieurs par la même UV en cas d'éclosion


    public UV(){

    }

    public UV(int position, String nom, Filiere filiere){
        this.position=position;
        this.nom=nom;
        this.filiere=filiere;
        testEclosion = false;
        marqueur=new Marqueur[3];
        voisins=new ArrayList<UV>();
        professeur=null;
        personnages=new ArrayList<Personnage>();
    }

    /*Accesseurs aux différents variable de UV*/
    public int getPosition(){
        return position;
    }

    public ArrayList<UV> getVoisins(){
        return  this.voisins;
    }

    public ArrayList<Personnage> getPersonnages(){return personnages;}

    public void addPersonnage(Personnage nouveauPersonnage){
        this.personnages.add(nouveauPersonnage);
    }

    public void removePersonnage(Personnage personnageToRemove){
        personnages.remove(personnageToRemove);
    }

    public int getNombreMarqueur(){
        if (marqueur[2]!=null){
            return 3;
        }else if(marqueur[1]!= null){
            return 2;
        }else if(marqueur[0]!=null){
            return 1;
        }else{
            return 0;
        }
    }

    public Filiere getFiliere(){
            return filiere;
    }

    public String getNom(){
        return this.nom;
    }

    public void setEclosion(boolean eclosion){
        this.testEclosion = eclosion;
    }
    public boolean getEclosion(){return this.testEclosion;}
    public void setProfesseur(Professeur p){
        this.professeur=p;
    }
    public Professeur getProfesseur() {
        return professeur;
    }

    public int getNombreVoisins(){
        return voisins.size();
    }

    public boolean correctDestinationProf(Filiere f, int uvCible){
        UV uvDest = voisins.get(uvCible);
        if(uvDest.getFiliere()==f){
            return true;
        }else{
            return false;
        }
    }

    public boolean marqueurFiliere(Filiere f){
        boolean test = false;
        for(Marqueur m : marqueur){
            if(m.getFiliere()==f){
                test = true;
            }
        }

        return test;
    }

    public boolean hasMarqueur(){
        if(marqueur[0]!=null){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Marqueur> getMarqueurs(){
        int i = 0;
        ArrayList<Marqueur> marqueursList = new ArrayList<Marqueur>();
        while (marqueur[i]!=null || i < marqueur.length){
            marqueursList.add(marqueur[i]);
        }

        return marqueursList;
    }
    /*Recupere un marqueur de la collection correspondante*/
    public void addMarqueur (Marqueur m){

        if (marqueur[2] != null){
            setEclosion(true);
        }else{
            if(marqueur[0] == null){
                marqueur[0] = m;
            }else if (marqueur[1] == null){
                marqueur[1] = m;
            }else{
                marqueur[2] =m;
            }
        }
    }

    /*Enlève un marqueur et le replace dans la collection ou il appartient*/
    public Marqueur removeMarqueur(){
        Marqueur m = new Marqueur();
        if(marqueur[2] != null){
            m = marqueur[2];
            marqueur[2]=null;

        }else if(marqueur[1] != null){
            m = marqueur[1];
            marqueur[1] = null;

        }else if (marqueur[0] != null) {
            m = marqueur[0];
            marqueur[0] = null;

        }

        return m;


    }

    public Marqueur removeMarqueur(Filiere f){
        Marqueur m =null;
        //On parcours la liste de marqueur
        for(int i = 0; i<3;i++){
            //On s'assure que la liste n'est pas vide
            if(marqueur[i] != null){
                //Si le marqueur de la case I correspond a la filiere voulu on rentre dans le if
                if(marqueur[i].getFiliere()==f){
                    //m prend la valeur de marqueur[i]
                    m = marqueur[i];
                    //On modifie le tableau en conséquence (on avance les marqueur d'une case et on met les dernière a null)
                    for(int j = i; j<2;j++){
                        if(marqueur[j+1]!=null){
                            marqueur[j] = marqueur[j+1];
                        }else{
                            marqueur[j]=null;
                        }
                    }
                    //on renvoit alors m
                    return m;
                }
            }
        }
        //On renvoit null si aucun marqueur ayant la filière ne correspond
        return m;
    }
}
