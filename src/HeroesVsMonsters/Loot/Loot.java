package HeroesVsMonsters.Loot;

public class Loot {
    private String name;
    private int value;
    private String type; // arme, armure, potion, etc.
    private int tauxDrop;

    public Loot(String name, int value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.tauxDrop = 0;
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public String getType() {
        return type;
    }
    public int getTauxDrop() {
        return tauxDrop;
    }


    @Override
    public String toString() {
        return "Loot{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", type='" + type + '\'' +
                '}';
    }
}
