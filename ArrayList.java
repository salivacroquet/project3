//Created by Sylvia Croatt, croat038 and Hajar Ahmed, ahme0635

public class ArrayList<T extends Comparable<T>> implements List<T> {


    private T[] array; //an array containing the elements
    private boolean isSorted; //boolean that is true if the list is sorted in ascending order
    private int size; // the number of items in the list

    boolean added; 

    boolean removed;
    public ArrayList() {//SYLVIA
        this.size = 0;
        this.array = (T[]) new Comparable[2];
        this.isSorted = true;

    }

    //ADD
    //adds a given element to the end of the array
    public boolean add(T element) { //SYLVIA
        if (element == null) { //if the element is null do not add it
            return false;
        }
        else { //Otherwise add it to the array
            //check if array is full, if so resize it
            if (size == array.length) {
                resize();
            }
        }

        added=true;
        //add the element after the other items
        array[size] = element;
        size=size(); //update size()
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
        added=true;
        array[index] = element; //assigns element to new index
        size=size(); //increment size
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

        for (int i = 0; i < this.size(); i++) { //goes through each element until it finds one that matches input
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
        if(added){
            size++;
            added=false;
        }if(removed){
            size--;
            removed=false;
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
        if (index < 0 || index >= size) {
            return null;
        }

        T removedElement = array[index];
        for (int i = index; i < size-1; i++) {
            array[i] = array[i+1];
        }
        removed =true;
        array[size-1]=null;
        size=size();
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
        if(otherList == null)
            return;

        ArrayList<T> other = (ArrayList<T>) otherList;
        this.sort();
        other.sort();
        this.removeDuplicates();
        other.removeDuplicates();

        if(this.size == 0){
            this.array = other.array;
        } else {
            ArrayList<T> temp = new ArrayList<T>();
            temp.array = this.array;
            temp.size = this.size;

            temp.add(other);

            temp.sort();
            temp.removeDuplicates();

            ArrayList<T> result = new ArrayList<T>();

            for(int i = 0; i < temp.size(); i++){
                if(this.indexOf(temp.array[i]) == -1
                || other.indexOf(temp.array[i]) == -1){
                    // add exclusive item
                    result.add(temp.array[i]);
                }
            }

            this.array = result.array;
            this.size = result.size;
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

    public void add(List<T> otherList){
        for(int i = 0; i < otherList.size(); i++){
            this.add(otherList.get(i));
        }
        size = size();
    }

}
