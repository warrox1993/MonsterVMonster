package HeroesVsMonsters;

import java.util.Random;

public class De {
    private final int faces;
    private int valeur;
    private static final Random random = new Random(); // static car partagé entre tous les dés

    public De(int faces) {
        if (faces < 2) {
            throw new IllegalArgumentException("Un dé doit avoir au moins 2 faces.");
        }
        this.faces = faces;
        this.valeur = 0;
    }

    public void lancer() {this.valeur = random.nextInt(faces) + 1;
    }

    // Retourne la valeur du dernier lancer
    public int getValeur() {return valeur;
    }

    // Méthode utilitaire statique pour un lancer direct
    public static int lancerDe(int faces) {
        if (faces < 2) {
            throw new IllegalArgumentException("Nombre de faces invalide : " + faces);
        }
        return random.nextInt(faces) + 1;
    }

    @Override
    public String toString() {
        return "Dé à " + faces + " faces → " + valeur;
    }
}
