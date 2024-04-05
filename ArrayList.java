//Created by Sylvia Croatt, croat038 and Hajar Ahmed, ahme0635

public class ArrayList<T extends Comparable<T>> implements List<T> {


    private T[] array; //an array containing the elements
    private boolean isSorted; //boolean that is true if the list is sorted in ascending order
    private int size; // the number of items in the list

    boolean added; //boolean that is true if something is added to the list

    boolean removed; //boolean that is true if something is removed from the list

    //constructor:
    //initialized the list to an empty array where the length of the array is 2
    public ArrayList() {
        this.size = 0;
        this.array = (T[]) new Comparable[2];
        this.isSorted = true;

    }

    //add:
    //adds an element to the end of the list
    //return false if the element is invalid and therefore can not be added
    //return true if the element is successfully added to the list
    public boolean add(T element) {
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
        size=size(); //update size
        checkSort(); //check if array is sorted
        return true;
    }

    //add:
    //adds an element to a specific index in the list
    //return false if the element is invalid and therefore can not be added
    //return true if the element is successfully added to the list
    public boolean add(int index, T element) {
        //if index is out of bounds or element is null do NOT add the element to the array
        if (element == null || index<0 || index>=size) {
            return false;
        }//Otherwise:
        if (size == array.length) {//if the array is full resize it
            resize();
        }

        //shifts over elements after the added element
        for (int i = size-1; i >= index; i--) {
            array[i+1] = array[i];
        }
        added=true;
        array[index] = element; //assigns element to new index
        size=size(); //update size
        checkSort(); //check if array is sorted
        return true;

    }

    //clear:
    //empty the list
    public void clear() {
        this.array = (T[]) new Comparable[2];
        size = 0;
        isSorted = true;
    }

    //get:
    //get an element at a given index
    public T get(int index) {
        if(index>=0 && index< size){//if the index is within bounds
            return array[index]; //returns the element at given index
        }//otherwise index is out of bounds so return null
        return null;
    }

    //indexOf:
    //get the index of a certain element,
    // if the element is not in the list or null return -1
    public int indexOf(T element) {
        if(size==0 || element==null){//if the list is empty or element is null
            return -1;
        }//Otherwise check if element is in the list
        int count =0;
        while(count>=0 && count<size){
            if (array[count].equals(element)) {
                //return the index of the element in the list that matches the given element
                return count;
            }
            count++;
        }

        return -1;
    }

    //isEmpty:
    //returns true if the list is empty
    public boolean isEmpty() {
        if (size == 0) //the list is empty
            return true;
        else{//otherwise the list is not empty
            return false;
        }
    }

    //size:
    //returns the size of the list
    public int size() {
        if(added){//if something was added to the list
            size++; //increase size by 1
            added=false;//reset added to false
        }if(removed){ //if something was removed from the list
            size--;//decrease size by 1
            removed=false;//reset removed to false
        }

        return size;
    }

    //sort:
    //Sorts the elements from smallest to largest using Bubble sort
    public void sort() {
        //loop through each element and compare it with the other
        //elements in the list
        for (int i = 0; i < size - 1; i++) {
            for (int j = i; j >= 0; j--) {
                //if the previous element is bigger than the next element
                if (array[j].compareTo(array[j + 1]) > 0) {
                    //swap the elements
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1 ] = temp;
                }
            }
        }
        isSorted=true;
    }

    //remove:
    //removes the element at the given index and returns that element
    public T remove(int index) {
        if (index < 0 || index >= size) { //if index is out of bounds
            return null; //nothing is removed
        }
        //Otherwise we remove an element
        T removedElement = array[index];
        //shift all the elements after the removed element one to the left
        for (int i = index; i < size-1; i++) {
            array[i] = array[i+1];
        }

        removed =true;
        //remove the last non-null element
        // (it was already shifted to the left by one so get rid of the duplicate)
        array[size-1]=null;
        size=size(); //update size
        checkSort(); //check if it is sorted
        return removedElement;
    }

    //removeDuplicates
    //removes any duplicate elements in the list
    public void removeDuplicates() {
        //loop through each element and compare it with the other
        //elements in the list
        for(int i=0;i<size;i++){//starts at the beginning of the array
            for(int j=size-1;j>i;j--){//starts at the end of the array
                //if two distinct indices share the same element
                if(array[i]==array[j]){
                    //removes the similar element from the last part of the array
                    remove(j);
                }
            }
        }

        checkSort();
    }

    //reverse:
    //reverses the order of the array
    public void reverse() {
        if(size > 1){//if the list has at least two elements
            T temp = null;

            for(int i=0; i<size/2;i++){//loop through half of the list
                temp = array[i]; //make temp the current value
                //swap the current element to the element symmetric to it in the list
                //meaning that they both share the same distance from the center of the list
                array[i] = array[size-i-1];
                array[size-i-1] = temp;
            }
        }
        checkSort();
    }

    //exclusiveOr:
    //updates this list to contain only the elements in this list or other list but NOT in both
    public void exclusiveOr(List<T> otherList) {
        if(otherList == null) //if the other list is empty do nothing
            return;

        ArrayList<T> other = (ArrayList<T>) otherList;
        sort();
        removeDuplicates();
        other.sort();
        other.removeDuplicates();

        if(this.size == 0){//if this is empty make the other list this
            this.array = other.array;
        } else {

            //Step 1: remove everything in this that is in other
            boolean isremoved = false; //true if something is removed

            for(int i=0;i<size;){

                if(other.indexOf(array[i]) != -1) {//if array[i] is in other
                    T removedData= array[i];
                    int otherIndex= other.indexOf(removedData);
                    //remove that element in this and other
                    other.remove(otherIndex);
                    remove(i);
                    isremoved=true;
                    //Don't increment
                }else{//increment
                    i++;
                }
            }

            //Step2: combine the two lists
            for(int i = 0; i < otherList.size(); i++){
                //add each component of the other list to this list
                this.add(otherList.get(i));
            }
            sort();
        }
    }

    //getMin:
    //returns the smallest element in the list
    public T getMin() {
        if(size==0)//if the list is empty
            return null; //there is no minimum
        else{//Otherwise find the minimum
            T min=array[0];
            //loop through the list
            for(int i=0;i<size;i++){
                //if the minimum is bigger than the current element
                if(min.compareTo(array[i]) > 0)
                    min = array[i];//the new minimum is that element
            }
            return min;
        }
    }

    //getMax:
    //returns the largest element in the list
    public T getMax() {
        if(size==0) //if the list is empty
            return null; //there is no maximum
        else{//Otherwise find the maximum
            T max=array[0];
            //iterate through the list
            for(int i=0;i<size;i++){
                //if the maximum is smaller than the current element
                if(max.compareTo(array[i]) < 0)
                    max = array[i]; //the new maximum is that element
            }
            return max;
        }
    }

    //toString:
    //returns a string representation of the list
    public String toString() {//HAJAR
        String strArray="";
        for(int i=0; i<size;i++){
            //add the data of index to the string list if it is not null
            strArray= strArray + array[i] +" ,";
        }
        return strArray;
    }

    //isSorted:
    //returns true if the list is sorted
    public boolean isSorted() {
        return isSorted;
    }

    //resize
    //A helper function that copies the contents of this.array
    //into a new array with length array.length*2
    public void resize(){
        //make an array double the length of this.array
        T[] bigArray = (T[]) new Comparable[size*2];
        //iterate through the list and copy the contents of this.array into the bigger array
        for(int i=0;i<size;i++)
            bigArray[i] = array[i];

        this.array = bigArray;
    }

    //checkSort:
    //A helper function to check if the list is sorted
    public void checkSort(){
        isSorted = true;
        if(size>1){//if the list has at least two items
            //iterate through the list
            for(int i=0; i<size-1;i++){
                //if the next element is smaller than the current element
                //it is NOT sorted
                if(array[i].compareTo(array[i+1]) >0)
                    isSorted=false;
            }
        }
    }




}
