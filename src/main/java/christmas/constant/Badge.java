package christmas.constant;

public enum Badge {
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Badge checkBadge(int totalBenefit) {
        if (totalBenefit >= 20_000) {
            return Badge.SANTA;
        }
        if (totalBenefit >= 10_000) {
            return Badge.TREE;
        }
        if (totalBenefit >= 5_000) {
            return Badge.STAR;
        }
        return null;
    }
}
