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

        if(element == null) //if the element is null don't add it to the list
            return false;
        //Case 1: head is null
        else if(head == null)
            head = new Node<T>(element);
        //Case 2: head is NOT null
        else{
            while(currNode.getNext()!=null) //while the next node isn't null
                currNode = currNode.getNext();//go to the next node
            currNode.setNext(new Node<T>(element));
        }
        size++; //increment size
        isSorted =this.isSorted;
        return true;//INCOMPLETE
    }

    //ADD:
    //adds an element to a specific index in the list
    public boolean add(int index, T element){ //HAJAR
        Node<T> currNode = head;
        int currIndex = 0;

        //Case 1: The element is added to the start of the list (index = 0)
        if(index==0){
            if(head == null){ //The list is empty
                head = new Node<T>(element);
            }else{ //The list is not empty
                Node<T> newHead = new Node<T>(element);
                newHead.setNext(head);
                head = newHead;
            }
            size++;
            return true;

        }else if(head != null && index != 0){//Case 2: element is added to the middle or end
            if(index == size+1){ // if the index is the end of the list
                this.add(element); //use the add method to add it to the end
                return true;
            }else{//if the index is in the middle of the list
                while(currNode.getNext()!=null){
                    if(currIndex == index-1){ //if a Node points to the desired index
                        Node<T> nextNode= currNode.getNext();
                        currNode.setNext(new Node<T>(element));//make the node before it point to it
                        currNode = currNode.getNext();
                        currNode.setNext(nextNode); //make the new node point to the next node
                        size++;
                        return true;
                    }
                    currNode = currNode.getNext();//go to the next node
                    currIndex++;
                }
            }
        }

        //Otherwise it is an invalid Index
        return false;
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

    }

    public void reverse(){


    }

    public void exclusiveOr(List<T> otherList){

    }

    public T getMin(){ //SYLVIA
        //add case for empty list
        T min = head.getData();
        Node<T> element = head.getNext();
        int length = T.size(); //need a method for size?
        for (int i = 0; i <= length; i++) {
            T nextNode = element.getData();
            if (nextNode.compareTo(min) < 0) {
                min = nextNode;
            }
            element = element.getNext();
        }
        return min;
    }

    public T getMax(){ //SYLVIA
        //add case for empty list
        T max = head.getData();
        Node<T> element = head.getNext();
        int length = T.size(); //need a method for size?
        for (int i = 0; i <= length; i++) {
            T nextNode = element.getData();
            if (nextNode.compareTo(max) > 0) {
                max = nextNode;
            }
            element = element.getNext();
        }
        return max;
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


