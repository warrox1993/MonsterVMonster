package HeroesVsMonsters.Loot;

public class Item {
    private String name;
    private int value;
    private TypeItem type;
    private int tauxDrop;

    public Item(String name, int value, TypeItem type) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.tauxDrop = 0; // par défaut
    }

    public Item(String name, int value, String type, int tauxDrop) {
        this.name = name;
        this.value = value;
        this.type = TypeItem.valueOf(type.toUpperCase());
        this.tauxDrop = tauxDrop;
    }

    // --- Getters et Setters---
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public TypeItem getType() {
        return type;
    }

    public int getTauxDrop() {
        return tauxDrop;
    }

    // --- Affichage ---
    public void afficherInfos() {
        System.out.println("Nom : " + name + " | Type : " + type + " | Valeur : " + value + " PO");
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", type=" + type +
                ", tauxDrop=" + tauxDrop +
                '}';
    }
}
