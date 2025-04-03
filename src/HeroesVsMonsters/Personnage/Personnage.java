package HeroesVsMonsters.Personnage;

import HeroesVsMonsters.Loot.Arme;
import HeroesVsMonsters.Loot.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Personnage {
    protected String nom;
    protected int pv;
    protected int pvMax;
    protected int force;
    protected int defense;
    protected int dexterite;

    protected List<Item> inventaire = new ArrayList<>();
    protected Arme armeEquipee;

    protected int xp; // XP du personnage
    protected int niveau = 1;
    protected int xpPourNiveauSuivant = 100;

    public Personnage(String nom, int vie, int force, int defense, int dexterite) {
        this.nom = nom;
        this.pvMax = vie;
        this.pv = vie;
        this.force = force;
        this.defense = defense;
        this.dexterite = dexterite;
    }

    // Soins
    public void soigner(int quantite) {
        pv = Math.min(pv + quantite, pvMax);
        System.out.println(nom + " a été soigné de " + quantite + " PV. Il a maintenant " + pv + " PV.");
    }

    // Vérifie si le personnage est mort
    public boolean isDead() {
        return pv <= 0;
    }

    // Subir des dégâts
    public void subirDegats(int degats) {
        pv = Math.max(pv - degats, 0);
        if (degats > 0) {
            System.out.println(nom + " a perdu " + degats + " PV. Il lui reste " + pv + "/" + pvMax + " PV.");
        } else {
            System.out.println(nom + " a résisté aux dégâts !");
        }
    }

    // Modificateurs de combat
    public int getModificateurForce() {
        return (force - 10) / 2; // Modificateur basé sur la force, plus équilibré
    }

    public int getModificateurDexterite() {
        return (dexterite - 10) / 2;
    }

    public int getModificateurDefense() {
        return (defense - 10) / 2;
    }

    // Gestion de l'expérience et des niveaux
    // Classe Personnage - Méthode gagnerExperience
    public void gagnerExperience(int xpGagne) {
        xp += xpGagne;
        System.out.println(nom + " gagne " + xpGagne + " XP. Total : " + xp + "/" + xpPourNiveauSuivant);

        while (xp >= xpPourNiveauSuivant) {
            xp -= xpPourNiveauSuivant;
            niveau++;
            xpPourNiveauSuivant *= 2; // XP requise doublée à chaque niveau

            // Augmentation des stats à chaque montée de niveau
            pvMax += 2;
            force += 1;
            defense += 1;
            dexterite += 1;
            pv = pvMax;

            System.out.println("🎉 " + nom + " passe au niveau " + niveau + " !");
            System.out.println("🔺 Stats augmentées : +2 PV max, +1 Force, +1 Dextérité, +1 Défense");
            System.out.println("💪 PV : " + pv + "/" + pvMax + " | FOR : " + force + " | DEX : " + dexterite + " | DEF : " + defense);
        }
    }

    // --- Getters-Setter --- //
    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    public int getPvMax() {
        return pvMax;
    }

    public int getForce() {
        return force;
    }

    public int getDefense() {
        return defense;
    }

    public int getDexterite() {
        return dexterite;
    }

    public List<Item> getInventaire() {
        return inventaire;
    }

    // Inventaire
    public void ajouterItem(Item item) {
        inventaire.add(item);
        System.out.println(nom + " obtient : " + item.getName());
    }

    public void afficherInventaire() {
        if (inventaire.isEmpty()) {
            System.out.println("Inventaire vide.");
        } else {
            System.out.println("Inventaire de " + nom + " :");
            for (int i = 0; i < inventaire.size(); i++) {
                System.out.println("[" + i + "] " + inventaire.get(i));
            }
        }
    }

    // Arme
    public void equiperArme(Arme arme) {
        this.armeEquipee = arme;
        if (arme != null) {
            System.out.println(nom + " équipe " + arme.getName() + " (1D" + arme.getFacesDeDegats() + ")");
        } else {
            System.out.println(nom + " n’a plus d’arme équipée !");
        }
    }

    public Arme getArmeEquipee() {
        return armeEquipee;
    }

    // Retourne l'XP et le niveau du personnage
    public int getXp() {
        return xp;
    }

    public int getNiveau() {
        return niveau;
    }

    @Override
    public String toString(){
        return getNom();
    }
}

