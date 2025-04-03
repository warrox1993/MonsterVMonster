package HeroesVsMonsters;

import HeroesVsMonsters.Loot.Arme;
import HeroesVsMonsters.Loot.Item;
import HeroesVsMonsters.Loot.PotionSoin;
import HeroesVsMonsters.Loot.Rarete;
import HeroesVsMonsters.Personnage.Humains;
import HeroesVsMonsters.Personnage.Monstres;
import HeroesVsMonsters.Personnage.Personnage;
import HeroesVsMonsters.Personnage.TypeEnnemi;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // Création du joueur
        Humains joueur = new Humains("Jean-Baptiste", 100, 14, 5, 10);

        // Création d'une potion
        PotionSoin potion = new PotionSoin("Potion de vie", 10, 20);

        // Création de plusieurs armes AVEC rareté et durabilité
        Arme epee = new Arme("Épée courte", 15, 6, 5, Rarete.COMMUNE);
        Arme hache = new Arme("Hache de guerre", 25, 8, 6, Rarete.RARE);
        Arme lance = new Arme("Lance de cavalerie", 30, 10, 8, Rarete.EPIC);

        // Ajout des armes à l’inventaire
        joueur.ajouterItem(epee);
        joueur.ajouterItem(hache);
        joueur.ajouterItem(lance);

        // Affichage de l'inventaire
        System.out.println("\nInventaire de départ de " + joueur.getNom() + " :");
        joueur.afficherInventaire();

        // Choix de l’arme à équiper (par index dans l'inventaire)
        choisirArme(joueur, 2); // 0 = épée, 1 = hache, 2 = lance

        // Boucle infinie de combats tant que le joueur est vivant
        int compteur = 1;
        int ennemisTues = 0;
        int xpGagne = 0;
        int toursSurvives = 0;
        Random random = new Random();

        // Classe Main - Boucle principale du jeu
        while (!joueur.isDead()) {
            System.out.println("\n=== Combat #" + compteur + " ===");

            // Choix aléatoire du type d’ennemi
            TypeEnnemi type = TypeEnnemi.values()[random.nextInt(TypeEnnemi.values().length)];

            // Création de l'ennemi selon son type
            Monstres ennemi = genererEnnemi(type, compteur);

            Combat combat = new Combat(joueur, ennemi, potion);
            combat.demarrerCombat();

            // Si le joueur meurt, fin du jeu et résumé
            if (joueur.isDead()) {
                System.out.println("\n☠️ Le héros a péri durant le combat contre " + ennemi.getNom() + " !");
                System.out.println("\n📝 Résumé de la carrière de " + joueur.getNom() + ":");
                System.out.println("Nombre d'ennemis tués : " + ennemisTues);
                System.out.println("XP gagné : " + xpGagne);
                System.out.println("Tours survécus : " + toursSurvives);
                System.out.println("Niveau actuel : " + joueur.getNiveau());
                System.out.println("XP actuel : " + joueur.getXp());
                break;
            }

            // Mise à jour des statistiques si le joueur reste en vie
            ennemisTues++;
            xpGagne += getXpFromEnemyType(ennemi.getType()); // XP fixe selon le type d'ennemi
            toursSurvives++;

            // Affichage du niveau et de l'XP actuel après chaque combat
            System.out.println("Niveau actuel : " + joueur.getNiveau());
            System.out.println("XP actuel : " + joueur.getXp());

            compteur++;
            System.out.println("\n💪 Préparation du prochain combat...\n");
        }

        // Résumé final du joueur après sa mort
        if (!joueur.isDead()) {
            System.out.println("\n💀 " + joueur.getNom() + " est tombé après " + (compteur - 1) + " victoire(s).");
            System.out.println("Niveau : " + joueur.getNiveau());
            System.out.println("XP : " + joueur.getXp());
        }
    }

    // Génère un ennemi selon son type avec des PV fixés pour chaque type :


    /*public static Monstres genererEnnemi(TypeEnnemi type, int compteur) {
        switch (type) {
            case GOBELIN:
                return new Monstres("Gobelin #" + compteur, 50, 6 + Math.min(compteur, 10), 3 + Math.min(compteur, 10), 10, TypeEnnemi.GOBELIN);
            case ORC:
                return new Monstres("Orc brutal #" + compteur, 90,10 + Math.min(compteur, 15), 5 + Math.min(compteur, 10), 8, TypeEnnemi.ORC);
            case DRAGON:
                return new Monstres("Dragon de feu #" + compteur, 150, 20 + Math.min(compteur, 20), 10 + Math.min(compteur, 15), 6, TypeEnnemi.DRAGON);
            case SLIME:
                return new Monstres("Slime acide #" + compteur, 30, 4 + Math.min(compteur, 8), 1 + Math.min(compteur, 5), 12, TypeEnnemi.SLIME);
            case DRAGONNET:
                return new Monstres("Dragonnet #" + compteur, 100, 15 + Math.min(compteur, 15), 8 + Math.min(compteur, 10), 5, TypeEnnemi.DRAGONNET);
            case SQUELETTE:
                return new Monstres("Squelette osseux #" + compteur, 60, 8 + Math.min(compteur, 10), 4 + Math.min(compteur, 5), 10, TypeEnnemi.SQUELETTE);
            case LESPOULESDUVOISINS:
                return new Monstres("Les Poules du Voisin #" + compteur, 15, 5 + Math.min(compteur, 5), 2 + Math.min(compteur, 5), 7, TypeEnnemi.LESPOULESDUVOISINS);
            default:
                return new Monstres("Monstre inconnu #" + compteur, 50, 6, 3, 10, TypeEnnemi.GOBELIN);
        }
    }

     */
    // V2 : avec Yeld :  Génère un ennemi selon son type avec des PV fixés pour chaque type
    public static Monstres genererEnnemi(TypeEnnemi type, int compteur) {
        return switch (type) {
            case GOBELIN -> {
            System.out.println("Un gobelin surgit des buissons !");
                int force = 6 + Math.min(compteur, 10);
                int dexterite = 3 + Math.min(compteur, 10);
                yield new Monstres ("Gobelins", 50, force, dexterite, 10, TypeEnnemi.GOBELIN);
            }case ORC -> {
                System.out.println("Un Orc surgit des buissons !");
                int force = 6 + Math.min(compteur, 15);
                int dexterite = 3 + Math.min(compteur, 10);
                yield new Monstres ("Orc", 90, force, dexterite, 10, TypeEnnemi.ORC);
            }case DRAGON -> {
                System.out.println("Un Dragon surgit des buissons !");
                int force = 6 + Math.min(compteur, 10);
                int dexterite = 3 + Math.min(compteur, 10);
                yield new Monstres ("Dragon", 150, force, dexterite, 9, TypeEnnemi.DRAGON);
            }case SLIME -> {
                System.out.println("Un Slime surgit des buissons !");
                int force = 6 + Math.min(compteur, 10);
                int dexterite = 3 + Math.min(compteur, 10);
                yield new Monstres ("Slime", 20, force, dexterite, 5, TypeEnnemi.SLIME);
            }case DRAGONNET -> {
                System.out.println("Un Dragonnet surgit des buissons !");
                int force = 6 + Math.min(compteur, 10);
                int dexterite = 3 + Math.min(compteur, 10);
                yield new Monstres ("Dragonnet", 40, force, dexterite, 10, TypeEnnemi.DRAGONNET);
            }case SQUELETTE -> {
                System.out.println("Un Squelette surgit des buissons !");
                int force = 6 + Math.min(compteur, 10);
                int dexterite = 3 + Math.min(compteur, 10);
                yield new Monstres ("Squellette", 25, force, dexterite, 10, TypeEnnemi.SQUELETTE);
            }case LESPOULESDUVOISINS -> {
                System.out.println("Un Les poules du voisin surgissent des buissons !");
                int force = 6 + Math.min(compteur, 10);
                int dexterite = 3 + Math.min(compteur, 10);
                yield new Monstres ("Les poules du voisin", 10, force, dexterite, 10, TypeEnnemi.LESPOULESDUVOISINS);
            }
        };
    }


    // Version du switch V14+ java 🚫 :
    // Retourne l'XP à attribuer en fonction du type de l'ennemi
    public static int getXpFromEnemyType(TypeEnnemi type) {
        return switch (type) {
            case GOBELIN -> 20;
            case ORC -> 50;
            case DRAGON -> 100;
            case SLIME -> 15 ;
            case DRAGONNET -> 75;
            case SQUELETTE -> 40;
            case LESPOULESDUVOISINS -> 10;
            default -> 0;
        };
    }

    // Utilitaire pour équiper une arme à partir de l’inventaire
    public static void choisirArme(Personnage joueur, int index) {
        if (index < 0 || index >= joueur.getInventaire().size()) {
            System.out.println("Index invalide.");
            return;
        }

        Item item = joueur.getInventaire().get(index);
        if (item instanceof Arme) {
            joueur.equiperArme((Arme) item);
        } else {
            System.out.println("Cet objet n’est pas une arme !");
        }
    }
}