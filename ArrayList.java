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
///most of sylvia methods need editing

   //ADD
    //adds a given element to the end of the array
    public boolean add(T element) { //SYLVIA
       array[size++] = element;
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
        isSorted = false; //no longer sorted
        return true;

    }

    
    //CLEAR
    //clears the array
    public void clear() { //SYLVIA
        for (int i = 0; i < size; i++) { //make each element null
           array[i] = null;
        }
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
            return size == 0;
    }

    public int size() {//HAJAR
        return 0;
    }

    public void sort() { //SYLVIA

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
