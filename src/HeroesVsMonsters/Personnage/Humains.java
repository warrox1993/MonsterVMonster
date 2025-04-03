package HeroesVsMonsters.Personnage;

public class Humains extends Personnage {
    private int niveau = 1;
    private int experience = 0;
    private int xpPourNiveauSuivant = 100;

    public Humains(String nom, int vie, int force, int defense, int dexterite) {
        super(nom, vie, force, defense, dexterite);
    }

    public void gagnerExperience(int montant) {
        experience += montant;
        System.out.println("🧠 " + nom + " gagne " + montant + " XP. Total : " + experience + "/" + xpPourNiveauSuivant);

        while (experience >= xpPourNiveauSuivant) {
            experience -= xpPourNiveauSuivant;
            niveau++;
            xpPourNiveauSuivant *= 2; // XP doublée à chaque niveau

            // Stats améliorées à chaque montée de niveau
            pvMax += 2;
            force += 1;
            dexterite += 1;
            defense += 1;
            pv = pvMax;

        }
    }

    public int getNiveau() {
        return niveau;
    }

    public int getExperience() {
        return experience;
    }

    public void afficherEtat() {
        System.out.println(nom + " a " + pv + " PV sur " + pvMax + " PV.");
    }

    @Override
    public String toString() {
        return "Humains{" +
                "nom='" + nom + '\'' +
                ", pv=" + pv +
                ", pvMax=" + pvMax +
                ", force=" + force +
                ", defense=" + defense +
                ", dexterite=" + dexterite +
                ", niveau=" + niveau +
                ", xp=" + experience +
                '}';
    }
}