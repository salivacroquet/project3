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
    
    //ADD
    //adds a given element to the end of the array
    public boolean add(T element) { //SYLVIA
        if (element == null) {
            return false;
        }
        else {
            if (size == array.length) {
                resize();
            }
        }
        array[size++] = element;
        checkSort();
        return true;
    }
    //ADD
    //adds given element to a specified index in array
    public boolean add(int index, T element) { //SYLVIA
        if (element == null || index<0 || index>=size) { //won't add null element to list
            return false;
        }
        if (size == array.length) {
            resize();
        }
        for (int i = size-1; i >= index; i--) { //shifts over elements after the added element
            array[i+1] = array[i];
        }
        array[index] = element; //assigns element to new index
        size++; //increment size
        checkSort();
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

    public T remove(int index) { //SYLVIA
        if (index < 0) {
            return null;
        }
        else if (index >= size) {
            return null;
        }
        T remElement = array[index];
        for (int i = index; i < size -1; i++) {
            array[i] = array[i+1];
        }
        size--;
        checkSort();
        return remElement;
    }
    public void removeDuplicates() { //HAJAR
        for(int i=0;i<size-1;i++){
            for(int j=0;j<size-1;j++){
                if(i != j && array[i]==array[j]){
                    remove(j);
                    j++;
                }
            }
        }

    }

    public void reverse() { //HAJAR
        if(size > 1){
            T temp = null;
            for(int i=0; i<size/2;i++){
                temp = array[i];
                array[i] = array[size-i-1];
                array[size-i-1] = temp;
            }
        }
    }

    public void exclusiveOr(List<T> otherList) { //SYLVIA
        if (size == 1) {
            this.clear();
        }
        if (size > 1) {
            ArrayList<T> other = (ArrayList<T>) otherList;
            this.sort();
            this.removeDuplicates();
            other.sort();
            other.removeDuplicates();
            for (int i = 0; i < size; i++) {
                if (other.indexOf(array[i]) == -1) {
                    this.add(other.get(i));
                    }
                if (other.indexOf(array[i]) != -1) {
                    this.remove(indexOf(other.get(i)));
                }

                }
            sort();
            }
        }

    public T getMin() { //HAJAR
        if(size==0)
            return null;
        else{
            T min=array[0];
            for(int i=0;i<size;i++){
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
            for(int i=0;i<size;i++){
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

    //RESIZE
    //A helper function that copies and resizes the array
    public void resize(){ //HAJAR
        T[] bigArray = (T[]) new Comparable[size*2];
        for(int i=0;i<size;i++)
            bigArray[i] = array[i];
        this.array = bigArray;
    }
    
    //CheckSort
    //checks if the array is sorted
    public void checkSort(){
        isSorted = true;
        if(size>1){
            for(int i=0; i<size-1;i++){
                if(array[i].compareTo(array[i+1]) >0)
                    isSorted=false;
            }
        }
    }

}
