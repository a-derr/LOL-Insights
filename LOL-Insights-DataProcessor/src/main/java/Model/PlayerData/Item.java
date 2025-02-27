package Model.PlayerData;

public class Item {
    Boolean canUse;
    Boolean consumable;
    int count;
    String displayName;
    int itemID;
    int price;
    int slot;

    public Boolean getCanUse() {
        return canUse;
    }
    private void setCanUse(Boolean canUse) {
        this.canUse = canUse;
    }

    public Boolean getConsumable() {
        return consumable;
    }
    private void setConsumable(Boolean consumable) {
        this.consumable = consumable;
    }

    public int getCount() {
        return count;
    }
    private void setCount(int count) {
        this.count = count;
    }

    public String getDisplayName() {
        return displayName;
    }
    private void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getItemID() {
        return itemID;
    }
    private void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getPrice() {
        return price;
    }
    private void setPrice(int price) {
        this.price = price;
    }

    public int getSlot() {
        return slot;
    }
    private void setSlot(int slot) {
        this.slot = slot;
    }


}
