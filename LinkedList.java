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

    //ADD: INCOMPLETE
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
        return true;//INCOMPLETE
    }

    //ADD: INCOMPLETE
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

    //GET: INCOMPLETE
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

    //indexOf: INCOMPLETE
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

    public T remove(int index){
        return null;
    }

    public void removeDuplicates(){

    }

    public void reverse(){


    }

    public void exclusiveOr(List<T> otherList){

    }

    public T getMin(){
        return null;
    }

    public T getMax(){
        return null;
    }

    public String toString(){
        return "";
    }

    public boolean isSorted(){
        return false;
    }

}

