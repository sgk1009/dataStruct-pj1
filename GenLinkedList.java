import java.util.*;
import java.io.*;
import java.util.NoSuchElementException;

/* in my code I have the head node be index 0, so for all the index positions it goes by 0,1,2...etc. 
 */

public class GenLinkedList{
    private Node head;
    private Node tail;
    private int size;

        private static class Node
    {
        int data;
        Node next;

        Node(int d, Node n)
        {
            data = d;
            next = n;
        }
    }

public void addFront(int data) {
        if (head == null){          //empty list
            head= new Node(data, null);
            tail= head;
        }
        else{
        head= new Node(data,head);
        }
        size ++;
    }

public void addEnd(int d) {
    if (head == null){          //empty list
        head= new Node(d, null);
        tail= head;
    }
    else{
    tail.next= new Node(d,null);
    tail= tail.next;
    }
    size ++;
}

public int removeFront(){
       int olddata;

       if (head == null)        //empty list
           throw new NoSuchElementException();
       else if (head == tail)
       {
           olddata = head.data;          
           head = null;
           tail = null;
       }
       else
       {
           olddata = head.data;
           head = head.next;           
       }
       size--;
       return olddata;
    }

public int removeEnd(){
    int olddata=0;
    if (head == null){      //empty list 
        throw new NoSuchElementException();
    }
    if(head==tail){
        olddata=tail.data;
        head=tail=null;
    }
    else{
        olddata=tail.data;
        Node p=head;
        while(p.next != tail){
            p=p.next;}
            olddata=tail.data;
            p.next=null;
            tail=p;
    }
    size --;
    return olddata;
}  

public void set(int data, int index){
    if (index < 0 ){
        System.out.print("cannot have a negative index");
    }

    if (index ==0){
    
     head= new Node(data,head);
    }
    
    else{
        Node p = head;
        for(int j=0; j<index-1; j++){
            p= p.next;
        }
            p.next=new Node (data, p.next);
        }
    size++;
    }

public int get(int i){
        
        if(i<0 || i>size -1){
            throw new ArrayIndexOutOfBoundsException("Index: " + i + "size: " + size);
        }
        Node p = head;
        for(int j=0; j<i; j++){
            p= p.next;
        }
        return p.data;
    }

public void swap(int index1, int index2){
    if (index1 == index2){
        return;
    }

    if ( index1 <= -1 || index2 >= size ){
        System.out.printf("Index 1 is not in the current list size");
    }

    if(index2 <= -1 || index2 >= size ){
        System.out.printf("Index 1 is not in the current list size");
    }

    Node prev1 = null, curr1 = head;
        for(int j=0; j!=index1; j++){ 
            prev1 = curr1;
            curr1 = curr1.next;
        }

        Node prev2 = null, curr2 = head;
        for(int j=0; j!=index2; j++) {
            prev2 = curr2;
            curr2 = curr2.next;
        }
    if (curr1 == null || curr2 == null)
    {return;}
if (prev1 != null)
    {prev1.next = curr2;}
else 
    {head = curr2;}
if (prev2 != null)
   { prev2.next = curr1;}
else 
    {head = curr1;}
Node temp = curr1.next;
curr1.next = curr2.next;
curr2.next = temp;
}

public void shift(int shift){
    if (shift>0){               //positive shift
        Node oldtail=tail;
        oldtail.next=head;
        shift=shift % size;
        for(int i=0;i<size-shift-2;i++){
            oldtail=oldtail.next;
        }
        head=oldtail.next;
        oldtail.next=null;
    }     
    else{                   //negative shift
        shift=Math.abs(shift);
        shift=shift % size;
        int count=1;
        Node current=head;
        while (count < shift && current != null) {
            current = current.next;
            count++;
            }
         Node shiftnode=current;
        while (current.next != null){
            current = current.next;}
            current.next=head;
            head=shiftnode.next;
            shiftnode.next=null;
    }
}

public void removeMatching(int value){
        if(head.data==value){
        head = head.next;
        }
        Node curr=head.next;
        Node prev=head;
        for (int i=0;i<size;i++) {
            if (curr.data==value) {
                prev.next = curr.next;
                size--;
            }
            else {
                prev = curr;
                curr = curr.next;
            }
        }
        
    }

public void erase(int index, int num){
    if(index +num <size){ //make sure index and num won't go past the list
        Node temp=head;
        for (int i = 0; temp != null && i < index - 1;i++){
            temp = temp.next;
        }
        
        for (int i=0; i<num;i++){
        Node delete=temp.next;
        temp.next= temp.next.next;
        delete.next=null;
        }
        if(temp.next==null){
            tail=temp;
            System.out.println("new tail: " + tail);
        }
        size=size-num;
    }
    else{
        System.out.println("invalid erasure length \n");
    }
    
}


public void insertList(List<Integer> listname, int index){
 if (index<size){
        Node p=head;
        for(int i=0;i<index;i++){
            p=p.next;
        }
        int j=index;
        for(int i=0; i<listname.size();i++){
            set(listname.get(i),j);
            j++;
        }
    size=size + listname.size();
    }
    else{
        System.out.println("Intex is bigger than size of Linked List \n");
    }
    
}

    @Override
    public String toString()
    {
       StringBuilder sb = new StringBuilder("[ ");

       Node p = head;
       while (p != null)
       {
          sb.append(p.data + " ");
          p = p.next;
       }

       sb.append("]");

       return new String(sb);
    }



    public static void main(String args[])
    {
        //MAKE SURE TO TEST EVERY METHOD AND PRINT BEFORE AND AFTER 
       GenLinkedList list = new GenLinkedList();

       for (int i=1; i<6; i++){
          list.addFront(i);}
          System.out.println("List adding from the front:");
          System.out.println(list);
          list.addEnd(7);
        System.out.println("List adding 7 from the end:");
          System.out.println(list);
        list.set(8,1);
        list.set(8,4);
        System.out.println("List setting 8 at index 1 and 8 at index 4:");
       System.out.println(list);
       list.removeFront();
       System.out.println("List removing 1 from the front:");
          System.out.println(list);
        list.removeEnd();
       System.out.println("List removing 1 from the end:");
       System.out.println(list);
      System.out.println("Get index 3:");
        System.out.println(list.get(3));
        list.swap(2,4);
        System.out.println("List swapping index 2 and 4:");
       System.out.println(list);
       list.removeMatching(8);
       System.out.println("List removing all matching 8(s) from the list:");
       System.out.println(list);
       System.out.println("List erasing 2 elements from index 1:");
       list.erase(1,2);
       System.out.println(list);
       List<Integer>llist=new LinkedList<Integer>();
        llist.add(56);
        llist.add(57);
        list.insertList(llist, 1);
        System.out.println("New list to add to the list:");
        System.out.println(llist);
        System.out.println("After adding the New list at index 1:");
        System.out.println(list);
      System.out.println("Shift list +2:");
       list.shift(1);
       System.out.println(list);
       System.out.println("Shift list -1:");
       list.shift(-1);
       System.out.println(list);
   
    }
}
    
