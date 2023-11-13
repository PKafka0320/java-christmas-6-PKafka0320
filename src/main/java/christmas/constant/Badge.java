package christmas.constant;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int price;

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public static Badge checkBadge(int totalBenefit) {
        if (totalBenefit >= SANTA.price) {
            return Badge.SANTA;
        }
        if (totalBenefit >= TREE.price) {
            return Badge.TREE;
        }
        if (totalBenefit >= STAR.price) {
            return Badge.STAR;
        }
        return null;
    }
}
