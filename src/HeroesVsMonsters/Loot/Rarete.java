package HeroesVsMonsters.Loot;

public enum Rarete {
    COMMUNE(60),
    RARE(25),
    EPIC(10),
    LEGENDAIRE(4),
    UNIQUE(1);

    private final int tauxDrop;

    Rarete(int tauxDrop) {
        this.tauxDrop = tauxDrop;
    }

    public int getTauxDrop() {
        return tauxDrop;
    }
}
