//Created by Sylvia Croatt, croat038 and Hajar Ahmed, ahme0635

public class ArrayList<T extends Comparable<T>> implements List<T> {


    private T[] array;
    private boolean isSorted;
    private int size;
    public ArrayList() {//SYLVIA
        this.array = (T[]) new Comparable[size];
        this.isSorted = true;
        this.size = 0;

    }


    public boolean add(T element) {
        return false;
    }

    public boolean add(int index, T element) {
        return false;
    }

    public void clear() {
        
    }

    public T get(int index) {
        return null;
    }

    public int indexOf(T element) {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return size;
    }

    public void sort() {

    }

    public T remove(int index) { //HAJAR
        return null;
    }

    public void removeDuplicates() { //HAJAR

    }

    public void reverse() { //HAJAR

    }

    public void exclusiveOr(List<T> otherList) { //HAJAR

    }

    public T getMin() { //HAJAR
        return null;
    }

    public T getMax() { //HAJAR
        return null;
    }

    public String toString() {//HAJAR
        return "";
    }

    public boolean isSorted() {//HAJAR
        return false;
    }

}
