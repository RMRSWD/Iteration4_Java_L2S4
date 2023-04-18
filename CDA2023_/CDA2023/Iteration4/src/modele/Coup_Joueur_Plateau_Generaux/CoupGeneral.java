package modele.Coup_Joueur_Plateau_Generaux;
public abstract class CoupGeneral {
    public int num_;
    public CoupGeneral(int num){
        this.num_ = num;
    }
    public CoupGeneral() {

    }
public int getNumLigne() {
    return this.num_;
}
    public abstract char getLettre() ;
}
