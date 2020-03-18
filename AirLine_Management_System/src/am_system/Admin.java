package am_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Admin {
    private Person admin;
    public String name;
    public boolean login;
    class node{
        String data;
        node next;
    }
    private node head, tail;
    
    public Admin(){
    }
    
    public Admin(Person admin){
        this.admin=admin;
    }
    
    public Admin(Person admin, String name) {
        this.admin = admin;
        this.name = name;
        try{
            login=admin.login();
            if(login){
                System.out.println("---Login Successfull---");
            }
            else{
                System.out.println("---Login Failed---");
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Person getAdmin() {
        return admin;
    }

    public void setAdmin(Person admin) {
        this.admin = admin;
    }
    
    public void AddAdmin(String UName,String password){
        if(login){
            try {
                Scanner in=new Scanner(System.in);
                while(!admin.register(UName,password,"Admin")){
                    //input both user name and password again from the user
                    
                    System.out.println("---Do You Want To Go Back or Register Again---(Y/N)");
                    while(true){
                        char opt=in.next().charAt(0);
                        if(opt=='y'||opt=='Y'){
                            System.out.print("Enter User Name : ");
                            UName=in.nextLine();
                            System.out.print("\nEnter Password : ");
                            password=in.nextLine();
                            AddAdmin(UName,password);
                        }
                        else if (opt=='N'||opt=='n'){
                            System.out.println("---Registration Cancelled---");
                            break;
                        }
                        else{
                            System.out.println("---Option Incorrect---");
                        }
                    }
                }
                System.out.println("---Registered Successfully---");
            } 
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else{
            System.out.println("---Not Logged In---");
        }
    }
    
    public void removeAdmin(String UName){
        if(login){
            admin.remove(UName);
        }
        else{
            System.out.println("---Not Logged In---");
        }
    }
    
    public void checkFeedBack(JTextArea usertxt){
        if(login){
            File file=new File("feedback.txt");
            try {
                Scanner read=new Scanner(file);
                if(!read.hasNextLine()){
                    JOptionPane.showMessageDialog(null, "No Record Found");
                }
                
                usertxt.setText("");
                
                int i=1;
                
                while(read.hasNextLine()){
                    String s=Integer.toString(i)+" "+read.nextLine();
                    usertxt.append(s);
                    usertxt.append("\n");
                    i++;
                }
                File temp=new File(file.getAbsolutePath());
                temp.delete();
            }
            catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else{
            System.out.println("---Not Logged In---");
        }
    }
    
    public void changePassword(String password){
        if(login){
            LoadList();
            node temp=head.next;
            temp.data=password;
            updateFile();
        }
        else{
            System.out.println("---Not Logged In---");
        }

    }
    
    private void LoadList() {
        head=tail=null;
        File file=new File(admin.getUName().concat(".txt"));
        try {
            Scanner read=new Scanner(file);
            while (read.hasNext()){
                node temp=new node();
                temp.data=read.nextLine();
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
        File file=new File(admin.getUName()+".txt");
        node temp=head;
        
        try{
            PrintWriter write=new PrintWriter(file);
            while (temp!=null){
                write.println(temp.data);
                temp=temp.next;
            }
            write.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());            
        }
    }
}

