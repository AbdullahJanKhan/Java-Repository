package am_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class Payment {
    
    class node{
        String data;
        node next;
    }
    
    node head, tail;
    
    Payment(){
        head=tail=null;
    }
    
    public void addCard(String UName, String ISBN, double ammount){
        loadList(UName);
        node temp=head.next;
        node cardAdd=new node();
        cardAdd.data=ISBN+" "+Double.toString(ammount);
        cardAdd.next=temp.next;
        temp.next=cardAdd;
        UpdateFile(UName);
    }

    private void loadList(String UName) {
        File file=new File(UName+".txt");
        try {
            Scanner read=new Scanner(file);
            while (read.hasNextLine()){
                createNode(read.nextLine());
            }
        } 
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void UpdateFile(String UName) {
        File file=new File(UName+".txt");
        try {
            PrintWriter write=new PrintWriter(file);
            node temp=head;
            while (temp!=null){
                write.println(temp.data);
                temp=temp.next;
            }
            System.out.println("---Data Updated---");
            write.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void createNode(String data){
        node temp=new node();
        temp.data=data;
        if(head==null){
            head=tail=temp;
            temp.next=null;
        }
        else{
            tail.next=temp;
            tail=temp;
            temp.next=null;
        }
    }
    
    public boolean payCard(String UName, double price){
        loadList(UName);
        node temp=head.next.next;
        File tem=new File("temp.txt");
        
        if(temp==null){
            return false;
        }
        
        try{
            PrintWriter write=new PrintWriter(tem);
            write.print(temp.data);
            write.close();
            
            Scanner read=new Scanner(tem);
            String iban=read.next();
            double amnt=read.nextDouble();
            amnt-=price;
            read.close();
            
            PrintWriter Write=new PrintWriter(tem);
            Write.print(iban+" ");
            Write.print(amnt);
            Write.close();
            
            Scanner Read=new Scanner(tem);
            temp.data=Read.nextLine();
            Read.close();
            
            UpdateFile(UName);
            AdminUpdate(price);
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return true;
    }
    
    void AdminUpdate(double ammount){
        head=tail=null;
        loadList("AbdullahJ");
        
        node temp=head.next.next;
        
        File tem=new File("temp.txt");
        try{
            PrintWriter write=new PrintWriter(tem);
            write.print(temp.data);
            write.close();
            
            Scanner read=new Scanner(tem);
            String iban=read.next();
            double amnt=read.nextDouble();
            amnt+=ammount;
            read.close();
            
            PrintWriter Write=new PrintWriter(tem);
            Write.print(iban+" ");
            Write.print(amnt);
            Write.close();
            
            Scanner Read=new Scanner(tem);
            temp.data=Read.nextLine();
            Read.close();
            UpdateFile("AbdullahJ");
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
