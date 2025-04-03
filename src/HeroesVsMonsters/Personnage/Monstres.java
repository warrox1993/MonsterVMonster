package HeroesVsMonsters.Personnage;

public class Monstres extends Personnage {

    private final TypeEnnemi type;

    public Monstres(String nom, int vie, int force, int defense, int dexterite, TypeEnnemi type) {
        super(nom, vie, force, defense, dexterite);
        this.type = type;
    }

    public TypeEnnemi getType() {
        return type;
    }

    public void afficherEtat() {
        System.out.println(nom + " a " + pv + " PV sur " + pvMax + " PV.");
        System.out.println("Type : " + type);
    }

    @Override
        public String toString() {
            return String.format("%s [Type: %s, PV: %d/%d]", getNom(), type, getPv(), getPvMax());
        }
}


