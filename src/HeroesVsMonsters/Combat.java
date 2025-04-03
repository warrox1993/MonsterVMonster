package HeroesVsMonsters;

import HeroesVsMonsters.Loot.Arme;
import HeroesVsMonsters.Loot.Item;
import HeroesVsMonsters.Loot.LootFactory;
import HeroesVsMonsters.Loot.PotionSoin;
import HeroesVsMonsters.Personnage.Humains;
import HeroesVsMonsters.Personnage.Personnage;

public class Combat {
    private final Personnage joueur;
    private final Personnage ennemi;
    private final PotionSoin potion;

    public Combat(Personnage joueur, Personnage ennemi, PotionSoin potion) {
        this.joueur = joueur;
        this.ennemi = ennemi;
        this.potion = potion;
    }

    public void demarrerCombat() {
        System.out.println("Début du combat entre " + joueur.getNom() + " et " + ennemi.getNom());
        int tour = 1;

        while (!joueur.isDead() && !ennemi.isDead()) {
            System.out.println("\n--- Tour " + tour + " ---");

            // Attaque du joueur
            effectuerAttaque(joueur, ennemi);
            afficherPV(); // Affichage après attaque du joueur

            // Vérification si l'ennemi est mort
            if (ennemi.isDead()) {
                System.out.println(ennemi.getNom() + " est vaincu !");
                if (joueur instanceof Humains) {
                    ((Humains) joueur).gagnerExperience(50); // Donne de l'XP après la victoire
                }
                break; // Fin du combat si l'ennemi est mort
            }

            // Attaque de l'ennemi
            effectuerAttaque(ennemi, joueur);
            afficherPV(); // Affichage après attaque de l'ennemi

            // Vérification si le joueur est toujours en vie
            /*if (!joueur.isDead()) {
                // Loot après victoire
                Item loot = LootFactory.genererLootAleatoire();
                if (loot != null) {
                    joueur.ajouterItem(loot);
                    System.out.println("Butin récupéré : " + loot);
                } else {
                    System.out.println("Rien d'intéressant trouvé...");
                }
            }*/

            // Soin automatique si les PV sont inférieurs à 30% des PV max
            if (joueur.getPv() < joueur.getPvMax() * 0.3) {
                System.out.println(joueur.getNom() + " utilise automatiquement une potion !");
                potion.utiliser(joueur);
            }

            tour++; // Incrémente le tour


        }

        // Fin du combat, résumé
        System.out.println("\nFin du combat.");
        afficherPV();

        // Résumé après victoire
        if (!joueur.isDead()) {
            System.out.println("\n>> Le joueur fouille le corps du " + ennemi.getNom() + "...");
            Item loot = LootFactory.genererLootAleatoire();
            if (loot != null) {
                joueur.ajouterItem(loot);
                System.out.println("Butin récupéré : " + loot);
            } else {
                System.out.println("Rien d'intéressant trouvé...");
            }

            System.out.println("\n🧠 Résumé après victoire :");
            if (joueur instanceof Humains) {
                Humains humain = (Humains) joueur;
                System.out.println("XP : " + humain.getExperience());
                System.out.println();
                System.out.println("Niveau : " + humain.getNiveau());
            }
            System.out.println("PV : " + joueur.getPv() + "/" + joueur.getPvMax());
        }
    }

    // Fonction d'attaque
    public void effectuerAttaque(Personnage attaquant, Personnage cible) {
        int jetBrut = De.lancerDe(20);
        int modAttaque = attaquant.getModificateurForce();
        int jetTotal = jetBrut + modAttaque;

        System.out.println(attaquant.getNom() + " attaque " + cible.getNom());
        System.out.println(" Jet d'attaque : " + jetBrut + " + mod FOR (" + modAttaque + ") = " + jetTotal);

        //Esquiver
        int esquiveAttaquant = De.lancerDe(20) + attaquant.getModificateurDexterite();
        int esquiveCible = De.lancerDe(20) + cible.getModificateurDexterite();

        System.out.println(attaquant.getNom() + " jet esquive " + cible.getNom());
        System.out.println(cible.getNom() + " jet esquive " + attaquant.getNom());

        if (esquiveAttaquant < esquiveCible) {
            System.out.println(cible.getNom() + "esquive " +  attaquant.getNom());
            return;  //le return ici c'est important pour éviter d'attaquer en cas d'esquive.
        }


        //Version 1 : echec critique et dégat critique :

        //Echec Critique :

        if (jetBrut == 1) {
            System.out.println("Échec critique ! L’attaque échoue totalement.");
            return;
        }

        //Coup Critique
        boolean critique = (jetBrut == 20);
        int faces = (attaquant.getArmeEquipee() != null) ? attaquant.getArmeEquipee().getFacesDeDegats() : 6;
        int jetDegats = De.lancerDe(faces);
        int degats = jetDegats + modAttaque;


        if (critique) {
            degats *= 2;
            System.out.println("Coup critique ! Dégâts doublés.");
        }



        degats = Math.max(degats, 0);
        System.out.println("Dégâts infligés : " + jetDegats + " + mod FOR (" + modAttaque + ") = " + degats);
        cible.subirDegats(degats);

        // Gestion de la durabilité de l’arme
        if (attaquant.getArmeEquipee() != null) {
            Arme arme = attaquant.getArmeEquipee();
            arme.utiliser();
            System.out.println("Durabilité de " + arme.getName() + " : " + arme.getDurabilite() + "/" + arme.getDurabiliteMax());

            if (arme.estBrisee()) {
                System.out.println(arme.getName() + " est brisée !");
                attaquant.equiperArme(null);

                boolean nouvelleArmeEquipee = false;
                for (Item item : attaquant.getInventaire()) {
                    if (item instanceof Arme nouvelleArme && !nouvelleArme.estBrisee()) {
                        attaquant.equiperArme(nouvelleArme);
                        System.out.println(attaquant.getNom() + " équipe une nouvelle arme : " + nouvelleArme.getName());
                        nouvelleArmeEquipee = true;
                        break;
                    }
                }

                if (!nouvelleArmeEquipee) {
                    System.out.println(attaquant.getNom() + " n’a plus d’arme utilisable !");
                }
            }
        }
    }

    // Afficher les PV de chaque personnage
    public void afficherPV() {
        System.out.println(joueur.getNom() + " : " + joueur.getPv() + "/" + joueur.getPvMax() + " PV");
        System.out.println(ennemi.getNom() + " : " + ennemi.getPv() + "/" + ennemi.getPvMax() + " PV");
    }
}