/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YRRRRRRRRR;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 *
 * @author sick
 */
public class Inittable {
    private static String[] columnNames;
    private static Object[][] data;
    private static JPopupMenu popup;
    
    public static JPopupMenu getpopup(){return popup;}
    
    public  static  JTable create()
    {
        columnNames = new String[]{"WebSite","Desc","Ho"};

         data = new Object[][]{
	    {"KKKDM", "Normal Manga",
	     new Boolean(false)},
	    {"EH", "Hentai Mange",
	     new Boolean(false)},	   
        };

        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        //table.setCellSelectionEnabled(true);        
        popupintable pit = new popupintable();
        popup = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Search");
        popup.add(menuItem1);
        menuItem1.addActionListener(pit);
        JMenuItem menuItem2 = new JMenuItem("Random");
        popup.add(menuItem2);
        menuItem2.addActionListener(pit);
       // PopupListener pls=new PopupListener(popup);
        //table.addMouseListener(pls);
        
        return table;
    }
}


class popupintable implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                   + "    Event source: " + source.getText()
                   + " (an instance of " + source.getText() + ")";
        System.out.println(s);
 
    }
    
        public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Item event detected."
                   + "    Event source: " + source.getText()
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
        
        System.out.println(s);
    }

}
/*
class PopupListener extends MouseAdapter {
        JPopupMenu popup;
 
        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }
 
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }
 
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }
 
        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
        }

}
*/