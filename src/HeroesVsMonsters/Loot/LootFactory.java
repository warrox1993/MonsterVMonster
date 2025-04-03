package HeroesVsMonsters.Loot;

import HeroesVsMonsters.De;

import java.util.*;

public class LootFactory {
    private static final Random random = new Random();

    private static final Map<Rarete, List<Item>> lootsParRarete = new HashMap<>();

    static {
        lootsParRarete.put(Rarete.COMMUNE, new ArrayList<>(List.of(
                new Arme("Dague émoussée", 10, 4, 4, Rarete.COMMUNE),
                new Arme("Épée courte", 15, 6, 5, Rarete.COMMUNE)
        )));

        lootsParRarete.put(Rarete.RARE, new ArrayList<>(List.of(
                new Arme("Hache lourde", 30, 8, 6, Rarete.RARE),
                new Arme("Marteau de guerre", 35, 8, 5, Rarete.RARE)
        )));

        lootsParRarete.put(Rarete.EPIC, new ArrayList<>(List.of(
                new Arme("Lame Elfique", 50, 10, 8, Rarete.EPIC),
                new Arme("Claymore antique", 55, 10, 7, Rarete.EPIC)
        )));

        lootsParRarete.put(Rarete.LEGENDAIRE, new ArrayList<>(List.of(
                new Arme("Épée du Dragon", 100, 12, 10, Rarete.LEGENDAIRE),
                new Arme("Faucheuse de Flammes", 120, 14, 9, Rarete.LEGENDAIRE)
        )));

        lootsParRarete.put(Rarete.UNIQUE, new ArrayList<>(List.of(
                new Arme("Excalibur", 300, 20, 20, Rarete.UNIQUE)
        )));
    }

    public static Item genererLootAleatoire() {
        int roll = De.lancerDe(100);
        Rarete rarete = determinerRarete(roll);
        List<Item> loots = lootsParRarete.get(rarete);

        if (loots == null || loots.isEmpty()) return null;
        return loots.get(random.nextInt(loots.size()));
    }

    private static Rarete determinerRarete(int roll) {
        int cumulative = 0;
        for (Rarete r : Rarete.values()) {
            cumulative += r.getTauxDrop();
            if (roll <= cumulative) return r;
        }
        return Rarete.COMMUNE; // fallback de sécurité
    }
}
