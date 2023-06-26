
package ta;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class GUI extends JFrame implements ActionListener,Runnable{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
     JTextField t1,t2,t3,t4,t5,t6,t7;
     JButton b;
     public int NOS,NOC,NOTA,NOTAW,NOTAS,NOSW,NOSL;
     public GUI() {
 
        this.NOS=NOS;
        this.NOC=NOC;
        this.NOTA=NOTA;
        String NS=JOptionPane.showInputDialog("Enter the number of the student : ");
        String NC=JOptionPane.showInputDialog("Enter the number of chairs : ");
        String NTA=JOptionPane.showInputDialog("Enter the number of TA : ");
        NOS=Integer.parseInt(NS);
        NOC=Integer.parseInt(NC);
        NOTA=Integer.parseInt(NTA);
        this.NOTAW=NOTA;
        this.NOTAS=0;
        this.NOSW=NOC;
        if(NOS!=0){
        this.NOSL=(NOS-(NOC+NOTA));}
        else{this.NOSL=0;}
        this.setTitle("Teacher_Assistant");
        this.setSize(600, 400);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        b=new JButton("Click to next Thread");
        l1=new JLabel("Inputs");l2=new JLabel("Output");l3=new JLabel("#TAs");l4=new JLabel("#Chairs");
        l5=new JLabel("#Student");l6=new JLabel("#TAs Working");l7=new JLabel("#TAs Sleeping");
        l8=new JLabel("#Students wating on chairs");l9=new JLabel("#Student that will come later");
        t1=new JTextField(NOTA+""); t2=new JTextField(NOTAW+""); t3=new JTextField(NOC+""); t4=new JTextField(NOTAS+"");
        t5=new JTextField(NOS+""); t6=new JTextField(NOSW+""); t7=new JTextField(NOSL+"");
        l1.setBounds(50, 10, 100, 60); l2.setBounds(420, 10, 60, 60);
        l3.setBounds(20, 55, 100, 60); t1.setBounds(100, 70, 60, 30);
        l6.setBounds(340, 55, 100, 60); t2.setBounds(460, 70, 60, 30);
        l4.setBounds(20, 112, 100, 60); t3.setBounds(100, 130, 60, 30);
        l7.setBounds(340, 112, 100, 60); t4.setBounds(460, 130, 60, 30);
        l5.setBounds(20, 170, 100, 60); t5.setBounds(100, 188, 60, 30);
        l8.setBounds(300, 150, 160, 100); t6.setBounds(460, 188, 60, 30);
        l9.setBounds(295, 210, 160, 100); t7.setBounds(460, 245, 60, 30);
        b.setBounds(200, 300, 180, 50);
        t1.setBackground(Color.LIGHT_GRAY);t3.setBackground(Color.LIGHT_GRAY);t5.setBackground(Color.white);
        t2.setBackground(Color.ORANGE);t4.setBackground(Color.LIGHT_GRAY);t6.setBackground(Color.LIGHT_GRAY);
        t7.setBackground(Color.yellow);
        this.add(l1);this.add(l2);this.add(l3);this.add(t1);
        this.add(l6);this.add(t2);this.add(l4);this.add(t3);
        this.add(l7);this.add(t4);this.add(l5);this.add(t5);
        this.add(l8);this.add(t6);this.add(l9);this.add(t7);this.add(b);
     
     }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==b){
        //Read from textfield
        String RT1=t2.getText();
        String RT2=t4.getText();
        String RT3=t6.getText();
        String RT4=t7.getText();
        //num of TA
        String RT0=t1.getText();
        //convert value of text vield 
        int CRT1=Integer.parseInt(RT1);
        int CRT2=Integer.parseInt(RT2);
        int CRT3=Integer.parseInt(RT3);
        int CRT4=Integer.parseInt(RT4);
        int CRT0=Integer.parseInt(RT0);
        CRT1--;
        CRT3--;
        CRT4--;
        CRT2++;
        //convert result to set it at textfield
        String CR1=String.valueOf(CRT1);
        String CR2=String.valueOf(CRT2);
        String CR3=String.valueOf(CRT3);
        String CR4=String.valueOf(CRT4);
     
        //convert the value to String 
        String CV1=String.valueOf(this.NOTAW);
        String CV2=String.valueOf(this.NOTAS);
        String CV3=String.valueOf(this.NOSW);
        String CV4=String.valueOf(this.NOSL);
        //ADD value to basic after update
        CV1=CR1;
        CV2=CR2;
        CV3=CR3;
        CV4=CR4;
        if(CRT4>=0){
          t7.setText(CV4);
        }
        else if(CRT4<=0&&CRT3>=0){
              t6.setText(CV3);}
        else if(CRT4<=0&&CRT3<=0&&CRT1>=0){
        t2.setText(CV1);
        
        t4.setText(CV2);
        if(CRT2==CRT0){
           this.remove(b);
        }
        }

        }
      }

    @Override
    public void run() {
 
        this.setVisible(true);
           b.addActionListener(this);
    }
    }

     


   



     
     

