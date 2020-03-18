package am_system;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class PersonGUI {
    String type;
    boolean login;
    Person p;
    
    PersonGUI(){
        p=new Person();
        type=null;
        login=false;
    }
    
    public void type(){
        JFrame frame=new JFrame("Who Are You");
        JButton adminbtn=new JButton("Admin");
        adminbtn.setBounds(75,50,150,50);
        JButton userbtn=new JButton("User");
        userbtn.setBounds(75,110,150,50);
        JButton exitbtn=new JButton("Exit");
        exitbtn.setBounds(75,170,150,50);
        userbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                type="User";
                frame.dispose();
                Login();
            }
        });
        adminbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                type="Admin";
                frame.dispose();
                Login();
            }
        });
        exitbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        frame.setSize(300,300);
        frame.add(adminbtn);
        frame.add(userbtn);
        frame.add(exitbtn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void Register(){
        JFrame frame=new JFrame("Register");
        JLabel userlbl=new JLabel("User Name : ");
        JTextField usertxt=new JTextField(30);
        usertxt.setBounds(85,50, 150, 50);
        userlbl.setBounds(10,50,75,50);
        userlbl.setLabelFor(usertxt);

        JLabel passwordlbl=new JLabel("Password : ");
        JPasswordField passwordtxt=new JPasswordField(30);
        passwordtxt.setBounds(85,110, 150, 50);
        passwordlbl.setBounds(10,105,75,50);
        userlbl.setLabelFor(passwordtxt);
        
        JButton signupbtn=new JButton("SignUP");
        signupbtn.setBounds(125, 170, 75, 30);

        JButton backbtn=new JButton("Back");
        backbtn.setBounds(125, 220, 75, 30);
        
        backbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                if(type.equals("Admin")){
                    frame.dispose();
                    Admin();
                }
                else{
                    frame.dispose();
                    Login();
                }
            }
        });
        
        signupbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                String password=new String(passwordtxt.getPassword());
                String uname=new String(usertxt.getText());
                Person p=new Person(uname, type, password);
                try{
                    if(p.register()){
                        if(type.equals("Admin")){
                            JOptionPane.showMessageDialog(null, "Registration Successfull");
                            Admin();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Registration Successfull\nYou Can Login Now");
                            Login();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Registration Not Successfull\nTry Again");
                        Register();
                    }
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }                
            }
        });
        
        frame.setSize(300, 300);
        frame.add(signupbtn);
        frame.add(backbtn);
        frame.add(passwordlbl);
        frame.add(passwordtxt);
        frame.add(userlbl);
        frame.add(usertxt);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
    
    public void Login(){
        JFrame frame=new JFrame("Login");
        
        JLabel userlbl=new JLabel("User Name : ");
        JTextField usertxt=new JTextField(30);
        usertxt.setBounds(85,50, 150, 50);
        userlbl.setBounds(10,50,75,50);
        userlbl.setLabelFor(usertxt);

        JLabel password=new JLabel("Password : ");
        JPasswordField fpassword=new JPasswordField(30);
        fpassword.setBounds(85,110, 150, 50);
        password.setBounds(10,105,75,50);
        userlbl.setLabelFor(fpassword);
        
        
        JButton loginbtn=new JButton("Login");
        loginbtn.setBounds(115, 170, 100, 30);
               
        JButton exitbtn=new JButton("Exit");
        exitbtn.setBounds(80,280,70,30);
        
        JButton backbtn=new JButton("Back");
        backbtn.setBounds(170, 280, 75, 30);
        
        JButton regbtn=new JButton("Register");
        regbtn.setBounds(115, 220, 100, 30);
        
        frame.setSize(300, 400);
        frame.add(exitbtn);
        frame.add(loginbtn);
        frame.add(userlbl);
        frame.add(backbtn);
        frame.add(password);
        frame.add(usertxt);
        frame.add(fpassword);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        
        loginbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    frame.dispose();
                    String password=new String(fpassword.getPassword());
                    String uname=new String(usertxt.getText());
                    
                    p=new Person(uname, type, password);
                    if(p.login()){
                        JOptionPane.showMessageDialog(null, "Login Successfull as "+type);
                        login=true;
                        if(type.equals("Admin")){
                            Admin();
                        }
                        else{
                            User();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Login Failed");
                        frame.dispose();
                        Login();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PersonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        exitbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        if(!type.equals("Admin")){
            frame.add(regbtn);
            regbtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    Register();
                }
            });
        }
        
        backbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                type();
            }
        });
    }
        
    public void Admin(){
        JFrame frame=new  JFrame("Admin");
        JButton addAdminbtn=new JButton("Register Admin");
        addAdminbtn.setBounds(75,50,150,50);
        JButton removeAdminbtn=new JButton("Remove Admin");
        removeAdminbtn.setBounds(75,110,150,50);
        JButton changePasswordbtn=new JButton("Change Password");
        changePasswordbtn.setBounds(75,170,150,50);
        JButton feedbackbtn=new JButton("Feedback");
        feedbackbtn.setBounds(75, 230, 150, 50);
        JButton backbtn=new JButton("Sign Out");
        backbtn.setBounds(75, 290, 150, 50);
        JButton exitbtn=new JButton("Exit");
        backbtn.setBounds(75, 350, 150, 50);
        addAdminbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Register();
            }
        });
        changePasswordbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                Admin admin=new Admin(p);
                admin.login=true;
                ChangePassword();
            }
        });
        removeAdminbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                removeAdmin();
            }
        });
        feedbackbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                checkfeedback();
            }
        });
        
        exitbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        backbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Sign Out Successful");
                frame.dispose();
                Login();
            }
        });
        
        frame.setSize(300, 500);
        frame.add(addAdminbtn);
        frame.add(removeAdminbtn);
        frame.add(changePasswordbtn);
        frame.add(feedbackbtn);
        frame.add(exitbtn);
        frame.add(backbtn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void checkfeedback(){
        
        String title="",t1="";
        
        if(type.equals("Admin")){
            title="Check FeedBack";
            t1="Feedback : ";
        }
        else{
            title="Check History";
            t1="History : ";
        }
        
        JFrame frame=new JFrame(title);
        JLabel userlbl=new JLabel(t1);
        JTextArea usertxt=new JTextArea();
        usertxt.setBounds(85,50, 150, 150);
        userlbl.setBounds(10,100,75,50);
        userlbl.setLabelFor(usertxt);
        
        JButton checkbtn=new JButton("Check");
        checkbtn.setBounds(110,250,80,30);
        JButton  backbtn=new JButton("Back");
        backbtn.setBounds(110,310,80,30);
        
        backbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                if(type.equals("Admin")){
                    frame.dispose();
                    Admin();
                }
                else{
                    frame.dispose();
                    User();
                }
            }
        });
        checkbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(type.equals("Admin")){
                    Admin admin=new Admin();
                    admin.setAdmin(p);
                    admin.login=true;
                    admin.checkFeedBack(usertxt);
                }
                else {
                    User user=new User(p);
                    user.login=true;
                    user.checkHistory(p.getUName(),usertxt);
                }
            }
        });
        
        frame.setSize(300, 400);
        frame.add(userlbl);
        frame.add(usertxt);
        frame.add(checkbtn);
        frame.add(backbtn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void removeAdmin(){
        JFrame frame=new JFrame("Admin");
        JLabel userlbl=new JLabel("Enter User Name : ");
        JTextField usertxt=new JTextField(30);
        usertxt.setBounds(135,50, 150, 50);
        userlbl.setBounds(10,50,125,50);
        userlbl.setLabelFor(usertxt);
        
        JButton removebtn=new JButton("Remove");
        removebtn.setBounds(150, 105, 100, 30);
        
        JButton backbtn=new JButton("Back");
        backbtn.setBounds(150, 155, 100, 30);
        
        backbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                if(type.equals("Admin")){
                    Admin();
                }
                else{
                    User();
                }
            }
        });
        
        removebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String uname=new String(usertxt.getText());
                p.remove(uname);
                if(type.equals("User")){
                    uname.equals(p.getUName());
                    frame.dispose();
                    Login();
                }
                frame.dispose();
            }
        });
        
        frame.setSize(325, 300);
        frame.add(userlbl);
        frame.add(usertxt);
        frame.add(removebtn);
        frame.add(backbtn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void User(){
        JFrame frame=new  JFrame("Users");
        JButton reservebtn=new JButton("Reserve Ticket");
        reservebtn.setBounds(75,50,150,50);
        JButton deletebtn=new JButton("Delete Account");
        deletebtn.setBounds(75,110,150,50);
        JButton changePasswordbtn=new JButton("Change Password");
        changePasswordbtn.setBounds(75,170,150,50);
        JButton feedbackbtn=new JButton("Feedback");
        feedbackbtn.setBounds(75, 230, 150, 50);
        JButton checkhistorybtn=new JButton("Check History");
        checkhistorybtn.setBounds(75, 290, 150, 50);
        JButton backbtn=new JButton("Sign Out");
        backbtn.setBounds(75, 390, 150, 50);
        JButton exitbtn=new JButton("Exit");
        exitbtn.setBounds(75, 450, 150, 50);
        
        reservebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                ReserveTicket();
            }
        });
        
        changePasswordbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                User user=new User(p);
                user.login=true;
                ChangePassword();
            }
        });
        
        backbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Sign Out Successful");
                frame.dispose();
                Login();
            }
        });
        
        checkhistorybtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                checkfeedback();
            }
        });
        
        exitbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                System.exit(0);
            }
        });
        
        deletebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                removeAdmin();
            }
        });

        feedbackbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                feedback();
            }
        });
        
        frame.setSize(300, 600);
        frame.add(reservebtn);
        frame.add(deletebtn);
        frame.add(changePasswordbtn);
        frame.add(feedbackbtn);
        frame.add(checkhistorybtn);
        frame.add(exitbtn);
        frame.add(backbtn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void feedback(){
        JFrame frame=new JFrame("FeedBack");
        JLabel userlbl=new JLabel("FeedBack : ");
        JTextField usertxt=new JTextField();
        usertxt.setBounds(85,50, 150, 50);
        userlbl.setBounds(10,50,75,30);
        userlbl.setLabelFor(usertxt);
        
        JButton feedbackbtn=new JButton("Submit");
        feedbackbtn.setBounds(80, 120, 100, 30);
        feedbackbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                User user=new User(p);
                user.login=true;
                String fdbck=usertxt.getText();
                user.feedback(fdbck);
                usertxt.setText("");
            }            
        });
        
        JButton backbtn=new JButton("Back");
        backbtn.setBounds(80, 170, 100, 30);
        backbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                User();
            }
        });
        
        frame.setSize(300, 300);
        frame.add(usertxt);
        frame.add(userlbl);
        frame.add(feedbackbtn);
        frame.add(backbtn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
    
    public void ReserveTicket(){
        JFrame frame=new JFrame("Tickets");
        ButtonGroup airlinebtn=new ButtonGroup();
        
        JLabel airlinelbl=new JLabel("Select The Respective Airline : ");      
        airlinelbl.setBounds(10, 50, 220, 30);
        

        JRadioButton piabtn=new JRadioButton("PIA");
        JRadioButton airbluebtn=new JRadioButton("AIR BLUE");
        JRadioButton shaheenbtn=new JRadioButton("SHAHEEN");
        
        piabtn.setActionCommand("pia");
        airbluebtn.setActionCommand("airblue");
        shaheenbtn.setActionCommand("shaheen");
        
        piabtn.setBounds(225, 50, 150, 30);
        airbluebtn.setBounds(225, 90, 150, 30);
        shaheenbtn.setBounds(225, 130, 150, 30);
        
        airlinebtn.add(piabtn);
        airlinebtn.add(airbluebtn);
        airlinebtn.add(shaheenbtn);
    
        ButtonGroup classbtn=new ButtonGroup();

        JLabel classlbl=new JLabel("Select The Respective Class : ");
        classlbl.setBounds(10, 200, 300, 30);
        
        JRadioButton economybtn=new JRadioButton("Economy Class");
        JRadioButton firstbtn=new JRadioButton("First Class");
        JRadioButton businessbtn=new JRadioButton("Business Class");
        
        economybtn.setBounds(225, 200, 150, 30);
        firstbtn.setBounds(225, 240, 150, 30);
        businessbtn.setBounds(225, 280, 150, 30);
        
        classbtn.add(economybtn);
        classbtn.add(firstbtn);
        classbtn.add(businessbtn);
        
        JButton reservebtn=new JButton("Reserve Ticket");
        reservebtn.setBounds(125, 325, 150, 30);
        
        JButton backbtn=new JButton("Back");
        backbtn.setBounds(125, 365, 150, 30);

        JButton exitbtn=new JButton("Exit");
        exitbtn.setBounds(125, 405, 150, 30);
        
        reservebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                String plane=null;
                if(piabtn.isSelected()||airbluebtn.isSelected()||shaheenbtn.isSelected()){
                    plane = airlinebtn.getSelection().getActionCommand();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Class Not Selected");
                    frame.dispose();
                    ReserveTicket();
                }
                
                int rank=0;
                
                if(economybtn.isSelected()){
                    rank=1;
                }
                else if(firstbtn.isSelected()){
                    rank=2;
                }
                else if(businessbtn.isSelected()){
                    rank=3;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Class Not Selected");
                    frame.dispose();
                    ReserveTicket();
                }
                User user=new User(p);
                user.login=true;
                user.confirm(plane, rank);
                
                while(true){
                    String confirm=JOptionPane.showInputDialog(null, "Want To Reserve Another Ticket\n1. Yes\n2. No"
                            + "\nEnter Chioce");
                    if(confirm.equals("1")){
                        frame.dispose();
                        ReserveTicket();
                        break;
                    }
                    else if (confirm.equals("2")){
                        frame.dispose();
                        User();
                        break;
                    }
                    else{
                        continue;
                    }
                }     
            }
        });
        backbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                User();
            }
        });
        
        exitbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        
        frame.setSize(400, 500);
        frame.add(airlinelbl);
        frame.add(piabtn);
        frame.add(airbluebtn);
        frame.add(shaheenbtn);
        
        frame.add(classlbl);
        frame.add(economybtn);
        frame.add(firstbtn);
        frame.add(businessbtn);

        frame.add(reservebtn);
        frame.add(exitbtn);
        frame.add(backbtn);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
    
    public void ChangePassword(){
        JFrame frame=new JFrame("Update Password");
        JLabel userlbl=new JLabel("New Password : ");
        JPasswordField usertxt=new JPasswordField(30);
        usertxt.setBounds(85,50, 150, 30);
        userlbl.setBounds(10,50,75,30);
        userlbl.setLabelFor(usertxt);
        
        JLabel cnfmlbl=new JLabel("Enter Again : ");
        JPasswordField cnfmtxt=new JPasswordField(30);
        cnfmtxt.setBounds(85,90, 150, 30);
        cnfmlbl.setBounds(10,90,75,30);
        cnfmlbl.setLabelFor(cnfmtxt);
        
        JButton updatebtn=new JButton("Update");
        updatebtn.setBounds(100,125,75,30);

        JButton backbtn=new JButton("Back");
        backbtn.setBounds(100,165,75,30);
        
        backbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                if(type.equals("Admin")){
                    Admin();
                }
                else if(type.equals("User")){
                    User();
                }
            }
        });
        
        updatebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(type.equals("Admin")){
                    Admin admin=new Admin(p);
                    char[] password=usertxt.getPassword();
                    char[] passwordcnfm=cnfmtxt.getPassword();
                    String p1=Arrays.toString(password),p2=Arrays.toString(passwordcnfm);
                    admin.login=true;
                    if(p1.equals(p2)){
                        JOptionPane.showMessageDialog(null, "Password Update Successfull");
                        JOptionPane.showMessageDialog(null, "Login Again");
                        frame.dispose();
                        admin.changePassword(p1);
                        Login();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Password Did Not Match");
                        frame.dispose();
                        ChangePassword();
                    }
                }
                else{
                    User user=new User(p);
                    char[] password=usertxt.getPassword();
                    char[] passwordcnfm=cnfmtxt.getPassword();
                    String p1=Arrays.toString(password),p2=Arrays.toString(passwordcnfm);
                    user.login=true;
                    if(p1.equals(p2)){
                        JOptionPane.showMessageDialog(null, "Password Update Successfull");
                        JOptionPane.showMessageDialog(null, "Login Again");
                        frame.dispose();
                        user.changePassword(p1);
                        frame.dispose();
                        Login();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Password Did Not Match");
                        frame.dispose();
                        ChangePassword();
                    }
                }
            }
        });
        
        frame.setSize(300, 300);
        frame.add(userlbl);
        frame.add(usertxt);
        frame.add(cnfmlbl);
        frame.add(cnfmtxt);
        frame.add(updatebtn);
        frame.add(backbtn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
}
