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
        int currIndex = 0;

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
            }else{//if the index is in the middle
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
            checkSort();
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
    //Sorts the elements from largest to smallest using Bubble sort
    public void sort(){//HAJAR
        Node<T> currNode = head;
        Node<T> nextNode = null;
        T temp= null;

        if(size> 1){
            while(currNode != null){
                nextNode=currNode.getNext();
                while(nextNode != null){
                    T currData = currNode.getData();
                    T nextData = nextNode.getData();
                    if(currData.compareTo(nextData) > 0){
                        temp= currData;
                        currNode.setData(nextData);
                        nextNode.setData(temp);
                    }
                    nextNode = nextNode.getNext();
                }
                currNode = currNode.getNext();
            }
        }
        isSorted = true;
    }

    public T remove(int index) { //SYLVIA
        if (index < 0 || head == null || index >= size) {
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
            checkSort();
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
                } else {
                    check = check.getNext();
                }
            }
            element = element = element.getNext();
        }
        checkSort();
    }

    //REVERSE
    //reverses the order of the linked list
    public void reverse(){//HAJAR
        Node<T> previous = null;
        Node<T> pointer = head;
        Node<T> next = null;

        while(pointer != null){
            next = pointer.getNext();
            pointer.setNext(previous);
            previous = pointer;
            pointer = next;
        }
        head = previous;
        checkSort();

    }
    
    public void exclusiveOr(List<T> otherList){ //HAJAR
        LinkedList<T> other = (LinkedList<T>) otherList;

        if(this.size==0){
            this.head= other.head;
        }
        if(other.size() > 0){
            this.sort();
            other.sort();
            this.removeDuplicates();
            other.removeDuplicates();

            Node<T> thisNode = head;
            Node<T> otherNode = null;

            //STEP 1: Add all the elements in the other list that are not in this list to this list
            while(thisNode != null){
                T thisData = thisNode.getData();
                int thisIndex = indexOf(thisData);


                otherNode = other.head;
                while(otherNode != null){
                    T otherData = otherNode.getData();
                    int otherIndex = other.indexOf(otherData);

                    if(thisData.compareTo(otherData) == 0){
                        other.remove(otherIndex);
                        remove(thisIndex);

                    }

                    otherNode =otherNode.getNext();
                }
                thisNode = thisNode.getNext();

            }

            //STEP 2: Remove the elements that are the same among this list and other list
            otherNode = other.head;
            while(otherNode != null){
                add(otherNode.getData());
                otherNode=otherNode.getNext();
            }
            sort();
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
        return isSorted;
    }

    //CheckSort
    //A helper function to check if the list is sorted
    public void checkSort(){//SYLVIA
        isSorted=true;
        if(size>1){
            Node<T> check = head;
            while (check.getNext() != null) {
                T element = check.getData();
                T nextData = check.getNext().getData();
                if (element.compareTo(nextData) > 0)
                    isSorted=false;
                check = check.getNext();
            }
        }
    }

}
