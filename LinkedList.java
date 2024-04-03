//Created by Sylvia Croatt, croat038 and Hajar Ahmed, ahme0635

public class LinkedList<T extends Comparable<T>> implements List<T> {


    private Node <T> head; //The front of the linked list
    private boolean isSorted; //boolean that is true if the list is sorted in ascending order
    private int size; // length of the list

    //CONSTRUCTOR:
    //initializes the list to an empty list
    public LinkedList() { //SYLVIA
        this.head = null;
        this.isSorted = true;
        this.size = 0;

    }

    //ADD:
    //adds an element to the end of the list
    public boolean add(T element){ //HAJAR
        Node<T> currNode = head; //pointer
        T oldData= currNode.getData();
        //CHECK IF SORTED

        //Return false and do not add to list if element is null
        if(element == null)
            return false;
        else{ //Add it to the list
            //Case 1: head is null
            if(head == null)
                head = new Node<T>(element);
            //Case 2: head is NOT null
            else{
                while(currNode.getNext()!=null) //while the next node isn't null
                    currNode = currNode.getNext();//go to the next node
                if(oldData.compareTo(currNode.getData()) < 0) //check if sorted
                    isSorted=false;
                oldData= currNode.getData(); //save the data from the node to compare to the next one
                currNode.setNext(new Node<T>(element));
            }
            size++; //increment size
            return true;//INCOMPLETE
        }
    }

    //ADD:
    //adds an element to a specific index in the list
    public boolean add(int index, T element){ //HAJAR
        Node<T> currNode = head;
        int currIndex = 0;
        T oldData = currNode.getData();

        if(element == null || index<0 || index>=size){ 
            //if the element is null or the index is out of bounds do not add it ot the list
            return false;
        }else{ //add it to the list
            //Case 1: head is null
            if(head == null) //The list is empty 
                head = new Node<T>(element);
            else if(index==0){ //head is not null and index=0
                Node<T> newHead = new Node<T>(element);
                if(oldData.compareTo(newHead.getData()) > 0)
                    isSorted = false;
                newHead.setNext(head);
                head = newHead;
            }else if(index == size) { //if the index is the end of the list
                this.add(element);
                size--;
            }else{//if the index is in the middle
                while(currNode.getNext()!=null){
                    if(currIndex == index-1){ //if a Node points to the desired index
                        Node<T> nextNode= currNode.getNext();
                        if(oldData.compareTo(currNode.getData()) < 0)
                            isSorted=false;
                        oldData= currNode.getData(); //save the data from the node to compare to the next one
                        currNode.setNext(new Node<T>(element));//make the node before it point to it
                        currNode = currNode.getNext();
                        currNode.setNext(nextNode); //make the new node point to the next node
                    }
                    currNode = currNode.getNext();//go to the next node
                    currIndex++;
                }
            }
            
            size++;
            return true;
        }
    }

    //CLEAR:
    //empty the list
    public void clear(){ //HAJAR
        head = null;
        isSorted = true;
        size=0;

    }

    //GET:
    //get an element at a given index
    public T get(int index){ //HAJAR
        int currIndex=0;
        Node<T> currNode = head;
        if (head != null) {
            while(currNode !=null){
                if(currIndex == index)
                    return currNode.getData();
                currNode = currNode.getNext();
                currIndex++;

            }
        }
        return null;
    }

    //indexOf:
    //get the index of a certain element
    public int indexOf(T element){ //HAJAR
        int currIndex=0;
        Node<T> currNode = head;
        if (head != null) {
            while(currNode !=null){
                if(currNode.getData() == element)
                    return currIndex;
                currNode = currNode.getNext();
                currIndex++;
            }
        }

        return -1;
    }

    //isEmpty:
    //returns true if the list is empty
    public boolean isEmpty(){ //HAJAR
        if(size == 0)
            return true;
        else
            return false;
    }

    //Size:
    //returns the size of the list
    public int size(){
        return size;
    }

    //SORT:
    //Sorts the elements from largest to smallest
    public void sort(){//HAJAR
        
    }

    public T remove(int index) { //SYLVIA
        if (index < 0 || head == null || index >= size) {
            return null;
        }
        if (index == 0) { //case that removes the head (index = 0)
            T dataRemoved = head.getData();
            head = head.getNext();
            size--;
            return dataRemoved;
        } else { //case when index != head (index > 0)
            Node<T> nextNode = head;
            for (int i = 0; i < index - 1; i++) { //moves through list to find the indicated index
                nextNode = nextNode.getNext();
            }
            if (nextNode.getNext() != null) { //makes sure that the next node isn't null
                T dataRemoved = nextNode.getNext().getData();
                nextNode.setNext(nextNode.getNext().getNext());
                size--;
                return dataRemoved;
            } else {
                return null;
            }
        }
    }

    public void removeDuplicates(){
        //empty or null??
        Node<T> element = head;
        while (element != null) {
            Node <T> check = element;
            while (check.getNext() != null) { //compares element to all other elements in list
                if (element.getData().equals(check.getNext().getData())) { //if they equal each other, remove
                    check.setNext(check.getNext()); //another .getNext?????
                    size--;
                } else {
                    check = check.getNext();
                }
            }
            element = element = element.getNext();
        }
    }

    public void reverse(){


    }

    public void exclusiveOr(List<T> otherList){

    }

    public T getMin(){ //SYLVIA
        if(size==0){
            return null;
        }else{
            T min = head.getData();
            Node<T> element = head;
            while(element.getNext() != null){
                element = element.getNext();
                T nextNode = element.getData();
                if (nextNode.compareTo(min) < 0) {
                    min = nextNode;
                }
            }
            return min;
        }
    }

    public T getMax(){ //SYLVIA
        if(size == 0){
            return null;
        }else{
            T max = head.getData();
            Node<T> element = head;
            while(element.getNext() != null){
                element = element.getNext();
                T nextNode = element.getData();
                if (nextNode.compareTo(max) > 0) {
                    max = nextNode;
                }
            }
            return max;
        }
    }

    public String toString () { //SYLVIA
        StringBuilder strList = new StringBuilder();
        Node<T> element = head;
        while (element != null) {
            strList.append(element.getData()).append(", ");
            element = element.getNext();
        }
        return strList.toString();
    }

    public boolean isSorted () { //SYLVIA
        //I THINK THIS ONLY RETURNS isSorted
        //-HAJAR
        if (head == null) {
            return false;
        }
        Node<T> check = head;
        while (check.getNext() != null) {
            T element = check.getData();
            T nextNode = check.getNext().getData();
            if (element.compareTo(nextNode) > 0) {
                return false;
            } else if (element.compareTo(nextNode) < 0) {
                check = check.getNext();
            }
        }
        return true;
    }

}

