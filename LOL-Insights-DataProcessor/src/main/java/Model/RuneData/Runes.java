package Model.RuneData;

public class Runes {
    Keystone keystone;
    Keystone primaryRuneTree;
    Keystone secondaryRuneTree;

    public Keystone getKeystone() {
        return keystone;
    }
    private void setKeystone(Keystone keystone) {
        this.keystone = keystone;
    }

    public Keystone getPrimaryRuneTree() {
        return primaryRuneTree;
    }
    private void setPrimaryRuneTree(Keystone primaryRuneTree) {
        this.primaryRuneTree = primaryRuneTree;
    }

    public Keystone getSecondaryRuneTree() {
        return secondaryRuneTree;
    }
    private void setSecondaryRuneTree(Keystone secondaryRuneTree) {
        this.secondaryRuneTree = secondaryRuneTree;
    }
}
