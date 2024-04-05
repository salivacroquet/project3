//Created by Sylvia Croatt, croat038 and Hajar Ahmed, ahme0635

public class LinkedList<T extends Comparable<T>> implements List<T> {
    //ONLY NEED TO DEBUG TO MAKE SURE exclusiveOrTest Passes

    private Node <T> head; //The front of the linked list
    private boolean isSorted; //boolean that is true if the list is sorted in ascending order
    private int size; // the number of items in the list

    //CONSTRUCTOR:
    //initializes the list to an empty list
    public LinkedList() { //SYLVIA
        this.head = null;
        this.isSorted = true;
        this.size = 0;

    }

    //ADD:
    //adds an element to the end of the list
    //return false if the element is invalid and therefore can not be added
    //return true if the element is successfully added to the list
    public boolean add(T element){ //HAJAR
        Node<T> currNode = head; //pointer used to traverse the list

        //if element is null return false and do not add it to the list
        if(element == null)
            return false;
        //Otherwise add it to the list
        else{
            //Case 1: head is null
            if(head == null) //create new node
                head = new Node<T>(element);
            //Case 2: head is NOT null
            else{ //loop through the linked list
                while(currNode.getNext()!=null) //while the next node isn't null
                    currNode = currNode.getNext();//go to the next node
                currNode.setNext(new Node<T>(element));//add the element at the end of the list
            }
            size++;
            checkSort(); //check if the list is sorted using a helper function
            return true;
        }
    }

    //ADD:
    //adds an element to a specific index in the list
    //return false if the element is invalid and therefore can not be added
    //return true if the element is successfully added to the list
    public boolean add(int index, T element){ //HAJAR
        Node<T> currNode = head; //pointer used to traverse the list
        int currIndex = 0; //keeps track of the current index

        //if the element is null or the index is out of bounds do not add it ot the list
        if(element == null || index<0 || index>=size){
            return false;
        }//Otherwise add it to the list
        else{
            //Case 1: The list is empty and the index is 0
            if(head == null && index==0)
                head = new Node<T>(element);
            //Case 2: head is not null and the index is 0
            else if(index==0){ //head is not null and index is 0
                Node<T> newHead = new Node<T>(element);
                newHead.setNext(head);
                head = newHead;
            }else if(index == size) { //if the index is the end of the list
                this.add(element);
                size--;
            }else{//if the index is in the middle of the list
                while(currNode.getNext()!=null){
                    if(currIndex == index-1){ //if a Node points to the desired index
                        Node<T> nextNode= currNode.getNext();
                        currNode.setNext(new Node<T>(element));//make the node before it point to it
                        currNode = currNode.getNext();
                        currNode.setNext(nextNode); //make the new node point to the next node
                    }
                    currNode = currNode.getNext();//go to the next node
                    currIndex++;
                }
            }
            size++;
            checkSort(); //check if it is sorted
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
        int currIndex=0; //keeps track of the current index
        Node<T> currNode = head; //pointer
        if (head != null) { //if not empty
            while(currNode !=null){ //loop through the list
                if(currIndex == index) //once we get to the index return its element
                    return currNode.getData();
                currNode = currNode.getNext();
                currIndex++;

            }
        }
        return null; //Otherwise return null
    }

    //indexOf:
    //get the index of a certain element
    public int indexOf(T element){ //HAJAR
        int currIndex=0; //keeps track of the current index
        Node<T> currNode = head; //pointer
        if (head != null) { //if the list is not empty
            while(currNode !=null){ //loop through the list
                if(currNode.getData() == element) //once we find the element return its index
                    return currIndex;
                currNode = currNode.getNext();
                currIndex++;
            }
        }

        return -1; //if the element is not in the list return -1
    }

    //isEmpty:
    //returns true if the list is empty
    public boolean isEmpty(){ //HAJAR
        if(size == 0) //if no items in the list it is empty
            return true;
        else //Otherwise there is something in it
            return false;
    }

    //Size:
    //returns the size of the list
    public int size(){
        return size;
    }

    //SORT:
    //Sorts the elements from largest to smallest using Bubble sort
    public void sort(){//HAJAR
        Node<T> currNode = head; //represents the current node
        Node<T> nextNode = null; //points to the node after current node
        T temp= null; //temporary node used to hold a node

        if(size> 1){//if there is at least two items in the list
            while(currNode != null){ //loop through the list
                nextNode=currNode.getNext();

                //compare the current node with the nodes after it
                while(nextNode != null){
                    T currData = currNode.getData();
                    T nextData = nextNode.getData();
                    //if element in currNode is bigger than nextNode
                    if(currData.compareTo(nextData) > 0){
                        //swap their element values
                        temp= currData;
                        currNode.setData(nextData);
                        nextNode.setData(temp);
                    }
                    nextNode = nextNode.getNext();
                }
                currNode = currNode.getNext();
            }
        }
        isSorted = true;//the list is now sorted
    }

    public T remove(int index) { //SYLVIA
        if (index < 0 || head == null || index >= size) {
            //if index out of bounds or list is empty return null
            return null;
        }else{//Remove element
            T dataRemoved=null;
            if (index == 0) { //case that removes the head (index = 0)
                dataRemoved = head.getData();
                head = head.getNext();
            } else { //case when index != head (index > 0)
                Node<T> nextNode = head;
                for (int i = 0; i < index - 1; i++) { //moves through list to find the indicated index
                    nextNode = nextNode.getNext();
                }
                if (nextNode.getNext() != null) { //makes sure that the next node isn't null
                    dataRemoved = nextNode.getNext().getData();
                    nextNode.setNext(nextNode.getNext().getNext());
                }
            }
            size--;
            checkSort(); //check if the list is sorted
            return dataRemoved;
        }
    }

    //removeDuplicates
    //removes and duplicate elements in the list
    public void removeDuplicates() {
        Node<T> element = head;
        while (element != null) {
            Node<T> check = element;
            while (check.getNext() != null) { //compares element to all other elements in list
                if (element.getData().equals(check.getNext().getData())) { //if they equal each other, remove
                    check.setNext(check.getNext().getNext());
                    size--;
                } else {//otherwise (they are not equal) continue traversing the list
                    check = check.getNext();
                }
            }
            element =  element.getNext();
        }
        checkSort(); //check if it is sorted
    }

    //REVERSE
    //reverses the order of the linked list by making everything point in reverse order
    public void reverse(){//HAJAR
        Node<T> previous = null; //previous node
        Node<T> pointer = head; //current node
        Node<T> next = null; //next node

        while(pointer != null){ 
            //shift the nodes
            next = pointer.getNext();//set next node to node after current node
            pointer.setNext(previous); // make the current node point to the previous node
            previous = pointer; 
            pointer = next;
        }
        head = previous; //set the new head
        checkSort(); //check if it is sorted

    }

    public void exclusiveOr(List<T> otherList){ //HAJAR
        LinkedList<T> other = (LinkedList<T>) otherList;

        if(this.size==0){//if this list is empty 
            this.head= other.head; //make this list equal other list
        }
        if(other.size() > 0){ //if other is not empty
            
            //sort and remove duplicates
            this.sort();
            other.sort();
            this.removeDuplicates();
            other.removeDuplicates();

            Node<T> thisNode = head; //pointer for this list
            Node<T> otherNode = null; //pointer for other list

            //STEP 1: Remove all the similar element from this list and other list
            while(thisNode != null){
                T thisData = thisNode.getData();
                int thisIndex = indexOf(thisData);


                otherNode = other.head; //go to the beginning of other list
                while(otherNode != null){
                    T otherData = otherNode.getData(); //get Data
                    int otherIndex = other.indexOf(otherData); //find Index

                    //if otherData is the same as thisData remove it from both list
                    if(thisData.compareTo(otherData) == 0){
                        other.remove(otherIndex);
                        remove(thisIndex);

                    }

                    otherNode =otherNode.getNext();
                }
                thisNode = thisNode.getNext();

            }

            //STEP 2: add all the elements on other list to this list
            otherNode = other.head;
            while(otherNode != null){
                add(otherNode.getData());
                otherNode=otherNode.getNext();
            }
            sort();//sort this list
        }
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

    //toString
    //returns a string representation of the lists where each component is
    //seperated by a comma
    public String toString () { //SYLVIA
        StringBuilder strList = new StringBuilder();
        Node<T> element = head;
        while (element != null) {/loop through the list
            //add data to the string representation of the list
            strList.append(element.getData()).append(", ");
            element = element.getNext();
        }
        return strList.toString();
    }

    public boolean isSorted () { 
        return isSorted;
    }

    //CheckSort
    //A helper function to check if the list is sorted
    public void checkSort(){
        isSorted=true;
        if(size>1){ //if the list has at least two elements
            Node<T> check = head;
            while (check.getNext() != null) {//loop through the list
                T element = check.getData();
                T nextData = check.getNext().getData();
                //check if the next data is smaller than the current data
                if (element.compareTo(nextData) > 0)
                    isSorted=false; //if so it is not sorted
                check = check.getNext();
            }
        }
    }

}
