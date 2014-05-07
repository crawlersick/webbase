/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YRRRRRRRRR;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sick
 */
public class JTListSelectionListener extends MouseAdapter implements ListSelectionListener  {
    private JTextArea jta;
    private JTable jtb;
   
    public JTListSelectionListener(JTextArea jta,JTable jtb)
            
    {
     this.jta=jta;
     this.jtb=jtb;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
          if (e.getSource() == jtb.getSelectionModel() && jtb.getRowSelectionAllowed()) {
              int first = e.getFirstIndex();
              int last = e.getLastIndex();
            } else if (e.getSource() == jtb.getColumnModel().getSelectionModel()
                && jtb.getColumnSelectionAllowed()) {
              int first = e.getFirstIndex();
              int last = e.getLastIndex();
            }
            if (e.getValueIsAdjusting()) {
              System.out.println("The mouse button has not yet been released");
              System.out.println("Selected: " + jtb.getSelectionModel().getAnchorSelectionIndex());

            }


      //  System.out.println("Selected: " + jtb.getSelectionModel().getAnchorSelectionIndex());
        
    }
    
    public void mousePressed(MouseEvent e) {
        /*
                    if (e.isPopupTrigger()) {
                Inittable.getpopup().show(e.getComponent(),
                           e.getX(), e.getY());
            }
          
         
        if(e.getButton()==MouseEvent.BUTTON2){Inittable.getpopup().show(e.getComponent(),
                           e.getX(), e.getY());}
        */
        if(e.getButton()==MouseEvent.BUTTON1){
        Inittable.getpopup().show(e.getComponent(),
                           e.getX(), e.getY());
                                        }

        }
/*
    @Override
    public void keyTyped(KeyEvent e) {
      
                Inittable.getpopup().show(e.getComponent(),
                           0, 0);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    */
}
