package am_system;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Person {
    private String UName;
    private String type;
    private String password;

    public Person(){
        
    }
    
    public Person(String UName, String type, String password) {
        this.UName = UName;
        this.type = type;
        this.password = password;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUName() {
        return UName;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    //login module
    public boolean login()throws Exception{
        File file=new File(UName.concat(".txt"));
        if(!file.exists()){
            JOptionPane.showMessageDialog(null, "Incorrect User Name");
            return false;
        }
        Scanner read=new Scanner(file);
        if(type.equals(read.nextLine())){
            if(password.equals(read.nextLine())){
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Password is Incorrect");
                return false;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Not Registered As A "+type);
            return false;
        }
    }
    //call this method in a loop while (!register) with 
    public boolean register()throws Exception{
        File file=new File(UName.concat(".txt"));
        if(file.exists()){
            JOptionPane.showMessageDialog(null, "User Name Not Avaliable");
            return false;
        }
        PrintWriter write=new PrintWriter(file);
        if(password.length()<8){
            JOptionPane.showMessageDialog(null, "Password Too Short\nMinimum 8 Characters");
            file.deleteOnExit();
            return false;
        }
        write.println(type);
        write.println(password);
        write.close();
        return true;
    }
    public boolean register(String uname, String password, String type)throws Exception{
        File file=new File(uname.concat(".txt"));
        if(file.exists()){
            System.out.println("---User Name Already Taken---");
            return false;
        }
        PrintWriter write=new PrintWriter(file);
        if(password.length()<8){
            System.out.println("---Password Too Short---");
            return false;
        }
        write.println(type);
        write.println(password);
        write.close();
        return true;
    }

    public void remove(String UName) {
        File file=new File(UName.concat(".txt"));
        if(file.exists()){
            file.delete();
            JOptionPane.showMessageDialog(null,"Deleted Successfully");
        }
        else{
            JOptionPane.showMessageDialog(null, "User Not Found");
        }
    }
}
