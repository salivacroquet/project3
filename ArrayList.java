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
        //System.out.println(index);
        if(index>=0 && index< size){
            return array[index]; //returns the element at given index
        }
        return null;
    }

    //indexOf
    //gives the index of a given element
    public int indexOf(T element) { //SYLVIA
        if(size==0 || element==null){
            return -1;
        }

        for (int i = 0; i < size; i++) { //goes through each element until it finds one that matches input
            if (array[i].compareTo(element) == 0) {
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
        size=0;
        for(int i=0; i<array.length; i++){
            if(array[i] != null){
                size++;
            }
        }
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
        isSorted=true;
    }

    public T remove(int index) { //SYLVIA
        if (index < 0 || index>= size) {
            return null;
        }

        T removedElement = array[index];
        for (int i = index; i < size; i++) {
            array[i] = array[i+1];
        }
        size--;
        checkSort();
        return removedElement;
    }

    public void removeDuplicates() { //HAJAR
        for(int i=0;i<size;i++){
            for(int j=size-1;j>i;j--){
                if(i != j && array[i]==array[j]){
                    remove(j);
                }
            }
        }
        checkSort();
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
        checkSort();
    }


    public void exclusiveOr(List<T> otherList) { //SYLVIA
        ArrayList<T> other = (ArrayList<T>) otherList;

        if(this.size == 0){
            this.array = other.array;
        }
        if(other.size() > 0) {
            this.sort();
            other.sort();
            this.removeDuplicates();
            other.removeDuplicates();

            //STEP 1: Remove all similar elements from both lists
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < other.size; j++) {
                    if(this.array[i].compareTo(other.array[j]) ==0){
                        this.remove(i);
                        other.remove(j);
                    }
                }
            }

            //STEP 2: add elements in other to this
            for(int j=0; j<other.size;j++){
                this.add(other.array[j]);
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
            strArray= strArray + array[i] +" ,";
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
