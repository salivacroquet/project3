//Created by Sylvia Croatt, croat038 and Hajar Ahmed, ahme0635
public class LinkedList<T extends Comparable<T>> implements List<T> {


    private Node<T> head; //The front of the linked list
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
    public boolean add(T element) { //HAJAR
        Node<T> currNode = head; //pointer
        T oldData = currNode.getData();
        //CHECK IF SORTED

        if (element == null) //if the element is null don't add it to the list
            return false;
            //Case 1: head is null
        else if (head == null)
            head = new Node<T>(element);
            //Case 2: head is NOT null
        else {
            while (currNode.getNext() != null) //while the next node isn't null
                currNode = currNode.getNext();//go to the next node
            if (oldData.compareTo(currNode.getData()) < 0) //check if sorted
                isSorted = false;
            oldData = currNode.getData(); //save the data from the node to compare to the next one
            currNode.setNext(new Node<T>(element));
        }
        size++; //increment size
        return true;//INCOMPLETE
    }

    //ADD:
    //adds an element to a specific index in the list
    public boolean add(int index, T element) { //HAJAR
        Node<T> currNode = head;
        int currIndex = 0;
        T oldData = currNode.getData();

        //Case 1: The element is added to the start of the list (index = 0)
        if (index == 0) {
            if (head == null) { //The list is empty
                head = new Node<T>(element);
            } else { //The list is not empty
                Node<T> newHead = new Node<T>(element);
                if (oldData.compareTo(newHead.getData()) > 0)
                    isSorted = false;
                newHead.setNext(head);
                head = newHead;
            }
            size++;
            return true;

        } else if (head != null && index != 0) {//Case 2: element is added to the middle or end
            if (index == size + 1) { // if the index is the end of the list
                this.add(element); //use the add method to add it to the end
                return true;
            } else {//if the index is in the middle of the list
                while (currNode.getNext() != null) {
                    if (currIndex == index - 1) { //if a Node points to the desired index
                        Node<T> nextNode = currNode.getNext();
                        if (oldData.compareTo(currNode.getData()) < 0)
                            isSorted = false;
                        oldData = currNode.getData(); //save the data from the node to compare to the next one
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
    public void clear() { //HAJAR
        head = null;
        isSorted = true;
        size = 0;

    }

    //GET:
    //get an element at a given index
    public T get(int index) { //HAJAR
        int currIndex = 0;
        Node<T> currNode = head;
        if (head != null) {
            while (currNode != null) {
                if (currIndex == index)
                    return currNode.getData();
                currNode = currNode.getNext();
                currIndex++;

            }
        }
        return null;
    }

    //indexOf:
    //get the index of a certain element
    public int indexOf(T element) { //HAJAR
        int currIndex = 0;
        Node<T> currNode = head;
        if (head != null) {
            while (currNode != null) {
                if (currNode.getData() == element)
                    return currIndex;
                currNode = currNode.getNext();
                currIndex++;
            }
        }

        return -1;
    }

    //isEmpty:
    //returns true if the list is empty
    public boolean isEmpty() { //HAJAR
        if (size == 0)
            return true;
        else
            return false;
    }

    //Size:
    //returns the size of the list
    public int size() {
        return size;
    }

    //SORT:
    //Sorts the elements from largest to smallest
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
    
    //REMOVE
    //removes and element at at a given index
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


    //removeDuplicates
    //removes and duplicate elements in the list
    public void removeDuplicates() {
        //empty or null??
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
    }


    //REVERSE
    //reverses the list
    public void reverse() {


    }


    //exclusiveOr
    //
    public void exclusiveOr(List<T> otherList) {

    }


    //getMin
    //gets the minimum value
    public T getMin() { //SYLVIA
        if (size == 0) {
            return null;
        } else {
            T min = head.getData();
            Node<T> element = head;
            while (element.getNext() != null) {
                element = element.getNext();
                T nextNode = element.getData();
                if (nextNode.compareTo(min) < 0) {
                    min = nextNode;
                }
                element = element.getNext();
            }
            return min;
        }
    }


    //getMax
    //gets the max value
    public T getMax() { //SYLVIA
        if (size == 0) {
            return null;
        }
        T max = head.getData();
        Node<T> element = head;
        while (element.getNext() != null) {
            element = element.getNext();
            T nextNode = element.getData();
            if (nextNode.compareTo(max) > 0) {
                max = nextNode;
            }
            element = element.getNext();
        }
        return max;
    }


    //toString
    //makes a string of all th elements of a list
    public String toString() { //SYLVIA
        StringBuilder strList = new StringBuilder();
        Node<T> element = head;
        while (element != null) {
            strList.append(element.getData()).append(", ");
            element = element.getNext();
        }
        return strList.toString();
    }

    //isSorted
    //verifies if the list is sorted
    public boolean isSorted() { //SYLVIA
        return isSorted;
//        if (head == null) {
//            return false;
//        }
//        Node<T> check = head;
//        while (check.getNext() != null) {
//            T element = check.getData();
//            T nextNode = check.getNext().getData();
//            if (element.compareTo(nextNode) > 0) {
//                return false;
//            } else if (element.compareTo(nextNode) < 0) {
//                check = check.getNext();
//            }
//        }
//        return true;
//    }

    }
}
