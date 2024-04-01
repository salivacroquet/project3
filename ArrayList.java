//Created by Sylvia Croatt, croat038 and Hajar Ahmed, ahme0635

public class ArrayList<T extends Comparable<T>> implements List<T> {


    private T[] array;
    private boolean isSorted;
    private int size;
    public ArrayList() {//SYLVIA
        this.size = 2;
        this.array = (T[]) new Comparable[size];
        this.isSorted = true;

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

    public int size() {//HAJAR
        return 0;
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
        if(!isSorted)
            this.sort();
        return array[size-1];
    }

    public T getMax() { //HAJAR
        if(!isSorted)
            this.sort();
        return array[0];
    }

    public String toString() {//HAJAR
        String strArray="";
        for(int i=0; i<size;i++){
            strArray= array[i] +"\n";
        }
        return strArray;
    }

    public boolean isSorted() {
        return isSorted;
    }

}
