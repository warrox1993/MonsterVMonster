package HeroesVsMonsters.Loot;

import HeroesVsMonsters.Personnage.Personnage;

public class PotionSoin extends Item implements Utilisable {
    private int niveauPotion;
    private int valeurSoin;

    public PotionSoin(String name, int value, int valeurSoin) {
        super(name, value, TypeItem.POTION);
        this.niveauPotion = 1; // niveau par défaut
        this.valeurSoin = valeurSoin;
    }

    // --- Getters & Setters ---
    public int getValeurSoin() {
        return valeurSoin;
    }

    public int getNiveauPotion() {
        return niveauPotion;
    }

    public void setNiveauPotion(int niveauPotion) {
        this.niveauPotion = niveauPotion;
    }

    public void setValeurSoin(int valeurSoin) {
        this.valeurSoin = valeurSoin;
    }

    public void utiliser(Personnage cible) {
        cible.soigner(valeurSoin);
        System.out.println(cible.getNom() + " utilise " + getName() + " et récupère " + valeurSoin + " PV !");
    }

    @Override
    public String toString() {
        return getName() + " [Potion de soin niveau " + niveauPotion + "] - Restaure " + valeurSoin + " PV";
    }
}
