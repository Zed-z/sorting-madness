package pl.put.poznan.sortingmadness.logic;

public abstract class SortedObject {
    int index;

    public SortedObject(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
}

