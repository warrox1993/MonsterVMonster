package HeroesVsMonsters.Loot;

import HeroesVsMonsters.Personnage.Personnage;

public class Arme extends Item implements Utilisable {
    private int facesDeDegats;
    private int durabilite;
    private int durabiliteMax;
    private Rarete rarete;

    // Constructeur avec paramètre pour la durabilité et la rareté
    public Arme(String name, int value, int facesDeDegats, int durabiliteMax, Rarete rarete) {
        super(name, value, TypeItem.ARME);
        this.facesDeDegats = facesDeDegats;
        this.durabiliteMax = durabiliteMax;
        this.durabilite = durabiliteMax;
        this.rarete = rarete;
    }

    //  Méthode pour utiliser l’arme et diminuer sa durabilité
    /*public void utiliser() {
        durabilite--;
        if (durabilite < 0) durabilite = 0;
    }

     */
    // Methode pour utiliser l'arme et diminuer sa durabilité V2 (fonction math) :
    public void utiliser (){
        durabilite = Math.max(0, durabilite -1);
        //Le max (ou le min) va chercher les éléments dans les parenthèses, par exemple ici (0, durabilité -1) va calculer durabilité -1 (exemple ça donne 3) et ensuite comparaison entre 0 et 3. Puis, prend le montant le plus haut donc 3.
    }

    public Arme copier(){
        return new Arme(getName(), getValue(),getFacesDeDegats(),getDurabiliteMax(),getRarete());
    }
    // Vérifie si l’arme est cassée
    public boolean estBrisee() {
        return durabilite <= 0;
    }

    // Accesseurs
    public int getDurabilite() {
        return durabilite;
    }

    public int getDurabiliteMax() {
        return durabiliteMax;
    }

    public int getFacesDeDegats() {
        return facesDeDegats;
    }

    public Rarete getRarete() {
        return rarete;
    }

    @Override
    public void utiliser(Personnage cible) {
        System.out.println("Tu ne peux pas utiliser une arme comme un objet consommable.");
    }

    /*@Override
    public String toString() {
        return getName() + " [Arme : 1D" + facesDeDegats +
                ", Durabilité " + durabilite + "/" + durabiliteMax +
                ", Rareté : " + rarete + "]";
    }
     */
    // V2 return getName -> return String.format :
     @Override
    public String toString() {
         return String.format(
                 " \n" +
                         "╔════════════════════╗\n" +
                         "║ %-18s ║\n" +
                         "╠════════════════════╣\n" +
                         "║ Rareté     : %-7s  ║\n" +
                         "║ Dégâts     : 1D%-3d ║\n" +
                         "║ Durabilité : %2d/%-3d ║\n" +
                         "╚════════════════════╝",
                 getName(), rarete, facesDeDegats, durabilite, durabiliteMax
         );     }

}
