package am_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

class checkout{
    String name;
    String plane;
    String type;
    double price;
    int Srow, Scol;
}

public class User extends Payment {
    public Person user;
    public String name;
    public boolean login;
    public checkout bill;
    int[][] seats;
    Scanner in=new Scanner(System.in);

    class node{
        String info;
        node next;
    }
    
    private node head, tail;
    
    public User(Person user, String name) {
        this.user = user;
        this.name = name;
        bill=new checkout();
        seats=new int[30][30];
        try{
            this.login=user.login();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public User(Person user){
        this.user=user;
        bill=new checkout();
        seats=new int [30][30];
    }
    public void Loadseats(String fname){
        File file=new File(fname.concat(".txt"));
        try{
            Scanner read=new Scanner(file);
            for(int i=0; i<seats.length;i++){
                for(int j=0;j<seats[i].length;j++){
                    seats[i][j]=read.nextInt();
                }
                read.nextLine();
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reserveTicket(String Airline , int rank){
        Loadseats(Airline);
        
        for(int i=(rank-1)*10;i<rank*10;i++){
            for(int j=(rank-1)*10;j<seats[i].length;j++){
                if(seats[i][j]==0){
                    bill.Scol=j;
                    bill.Srow=i;
                    return;
                }
            }
        }
    }
    
    public void updatefile(String fname){
        File file=new File(fname.concat(".txt"));
        try{
            PrintWriter write=new PrintWriter(file);
            for(int i=0; i<seats.length;i++){
                for(int j=0;j<seats[i].length;j++){
                    write.print(seats[i][j]);
                    write.print(' ');
                }
                write.println();
            }
            write.close();
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void confirm(String Airline, int rank){
        reserveTicket(Airline,rank);
        PriceCal(rank);
        int con;
        String s=JOptionPane.showInputDialog("1.Confirm \n2. Cancel\nEnter Option");
        con=Integer.parseInt(s);
        if(con==1){
            bill.plane=Airline;
            JOptionPane.showMessageDialog(null, "Booking Confirmed \nPrice total Price : "+bill.price,"Status",JOptionPane.PLAIN_MESSAGE);
            seats[bill.Srow][bill.Scol]=1;
            updatefile(Airline);
//            JOptionPane.showMessageDialog(null,"How Do You Want To Pay\n1.Card\n2.Cash");
//            System.out.println();
//            System.out.print("Enter You Chioce : ");
            int pay=-1;
            while(true){
                s=JOptionPane.showInputDialog("How Do You Want To Pay\n1.Card\n2.Cash");
                pay=Integer.parseInt(s);
                if(pay==1||pay==2){
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect Option");
                }
            }

            switch (pay) {
                case 1:
                    payCard(user.getUName(),bill.price);
                    break;
                case 2:
                    AdminUpdate(bill.price);
                    break;
                default:
                    break;
                }
            save();
        }
        else{
            JOptionPane.showMessageDialog(null, "Reservation Cancelled");
        }
        
    }
    
    public void save(){
        if(login){
            File file=new File(user.getUName().concat(".txt"));
            try{
                FileWriter fw=new FileWriter(file,true);
                PrintWriter write=new PrintWriter(fw);
                write.println("Plane: "+bill.plane+" "+"Seat No: "+"Row "+bill.Srow+" Column "+bill.Scol+
                "Class: "+bill.type);
                write.close();
            } 
            catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Plane: "+bill.plane+" "+"Seat No: "+"Row "+bill.Srow+" Column "+bill.Scol+
                "Class: "+bill.type);
        }
    }

    private void PriceCal(int rank) {
        switch (rank) {
            case 1:
                bill.type="Economy";
                bill.price=15000;
                break;
            case 2:
                bill.type="First Class";
                bill.price=20000;
                break;
            case 3:
                bill.type="Business";            
                bill.price=25000;
                break;
        }
    }
    
    public void checkHistory(String UName, JTextArea usertxt){
        if(login){
            File file=new  File(UName+".txt");
            Scanner read;
            try {
                read = new Scanner(file);
                read.nextLine();
                read.nextLine();
                if(!read.hasNextLine()){
                    JOptionPane.showMessageDialog(null,"---No History Avaliable---");
                    return;
                }
                while(read.hasNext()){
                    usertxt.setText("");
                    usertxt.append(read.nextLine());
                    usertxt.append("\n");
                }
            } 
            catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void removeUser(String UName){
        user.remove(UName);
    }
    
    public void feedback(String fdbck){
        File file=new File("feedback.txt");
        FileWriter fwrite;
        try {
            fwrite = new FileWriter(file,true);
            PrintWriter write=new PrintWriter(fwrite);
            write.println(fdbck);
            JOptionPane.showMessageDialog(null,"---Thanks For Your precious Feedback---");
            fwrite.close();
            write.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void changePassword(String password){
        if(login){
            head=tail=null;
            LoadList();
            node temp=head.next;
            temp.info=password;
            updateFile();
        }
        else{
            System.out.println("---Login First---");
        }
    }
    
    private void LoadList() {
        File file=new File(user.getUName().concat(".txt"));
        try {
            Scanner read=new Scanner(file);
            while (read.hasNext()){
                node temp=new node();
                temp.info=read.nextLine();
                if(head==null){
                    head=tail=temp;
                    temp.next=null;
                }
                else{
                    tail.next=temp;
                    temp.next=null;
                    tail=temp;
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void updateFile(){
        File file=new File(user.getUName().concat(".txt"));
        node temp=head;
        
        try{
            PrintWriter write=new PrintWriter(file);
            while (temp!=null){
                write.println(temp.info);
                temp=temp.next;
            }
            write.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());            
        }
    }
}