//Created by Sylvia Croatt, croat038 and Hajar Ahmed, ahme0635

public class ArrayList<T extends Comparable<T>> implements List<T> {


    private T[] array;
    private boolean isSorted;
    private int size;
    public ArrayList() {//SYLVIA
        this.size = 0;
        this.array = (T[]) new Comparable[2];
        this.isSorted = true;

    }
///most of sylvia methods need editing

    //ADD
    //adds a given element to the end of the array
    public boolean add(T element) { //SYLVIA
        array[size++] = element;
        //CHECK IF SORTED
        isSorted = false; //no longer sorted
        return true;
    }
    //ADD
    //adds given element to a specified index in array
    public boolean add(int index, T element) { //SYLVIA
        if (element == null) { //won't add null element to list
            return false;
        }
        for (int i = 0; i > index; i++) { //shifts over elements after the added element
            array[i] = array[i -1];
        }
        array[index] = element; //assigns element to new index
        size++; //increment size
        //CHECK IF SORTED
        isSorted = false; //no longer sorted
        return true;

    }


    //CLEAR
    //clears the array
    public void clear() { //SYLVIA
        this.array = (T[]) new Comparable[2];
        size = 0; //adjust size
        isSorted = true; //is sorted because empty
    }

    //GET
    //gives the element at a given index of the array
    public T get(int index) { //SYLVIA
        return array[index]; //returns the element at given index
    }

    //indexOf
    //gives the index of a given element
    public int indexOf(T element) { //SYLVIA
        for (int i = 0; i < size; i++) { //goes through each element until it finds an one that matches input
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    //isEmpty
    //checks if the array is empty
    public boolean isEmpty() { //SYLVIA
        if (size == 0)
            return true;
        else{
            return false;
        }
    }

    public int size() {//HAJAR
        int numItems=0;
        for(int i=0; i<array.length-1; i++){
            if(array[i] != null){
                numItems++;
            }
        }
        size = numItems;
        return size;
    }

    public void sort() { //SYLVIA
        for (int i = 0; i < size - 1; i++) {
            for (int j = i; j >= 0; j--) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1 ] = temp;
                }
            }
        }
    }

    public T remove(int index) { //HAJAR
        //WEDNESDAY
        return null;
    }

    public void removeDuplicates() { //HAJAR
    //THURSDAY
    }

    public void reverse() { //HAJAR
        int distance = size-1;
        for(int i=0;i<size-1;i++){
            array[distance-i] = array[i];
            distance--;
        }
    }

    public void exclusiveOr(List<T> otherList) { //HAJAR
    //THURSDAY
    }

    public T getMin() { //HAJAR
        if(size==0)
            return null;
        else{
            T min=array[0];
            for(int i=0;i<size-1;i++){
                if(min.compareTo(array[i]) > 0)
                    min = array[i];
            }
            return min;
        }
    }

    public T getMax() { //HAJAR
        if(size==0)
            return null;
        else{
            T max=array[0];
            for(int i=0;i<size-1;i++){
                if(max.compareTo(array[i]) < 0)
                    max = array[i];
            }
            return max;
        }
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
