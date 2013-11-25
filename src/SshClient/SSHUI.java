/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SshClient;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author sick
 */
public class SSHUI extends JFrame{
    	public static void main(String args[]) {
         SwingUtilities.invokeLater(new Runnable() {
                 public void run() {
                try {
                    SSHUI ss=new SSHUI();
                    SwingWorker_lis_s swingworker = new SwingWorker_lis_s(ss.jtAreaOutput,11229);
                    swingworker.execute();
                    ss.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                                
                                
                                     }
                                                   });

	}
public SSHUI() {
                SSHUI.setDefaultLookAndFeelDecorated(true);
		//this.setSize(500, 500);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(gridBag);
                create_outputview();
                create_inputview();
                pack();
	
                
                
	}


private void create_inputview(){
    jtAreaInput=new JTextArea(10, 50);
   scrollPane2 = new JScrollPane(jtAreaInput,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    //       ,
//				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setWheelScrollingEnabled(true);
   jtAreaInput.setLineWrap(true);
   jtAreaInput.setWrapStyleWord(true);
        c0 = new GridBagConstraints();
        c0.gridx = 0;
        c0.gridy = 1;
        c0.gridwidth = 1;
        c0.gridheight = 1;
        c0.weightx = 0;
        c0.weighty = 0;
        c0.fill = GridBagConstraints.NONE;
        c0.anchor = GridBagConstraints.WEST;

   this.getContentPane().add(scrollPane2,c0);
}

private void create_outputview()
{
jtAreaOutput=new JTextArea(30, 50);
DefaultCaret caret = (DefaultCaret)jtAreaOutput.getCaret();
caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
jtAreaOutput.setEditable(false);
jtAreaOutput.setWrapStyleWord(true);
jtAreaOutput.setLineWrap(true);
scrollPane1 = new JScrollPane(jtAreaOutput,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //,
	//			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	//			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        c1.gridwidth = 1;
        c1.gridheight = 1;
        c1.weightx = 0;
        c1.weighty = 0;
        c1.fill = GridBagConstraints.NONE;
        c1.anchor = GridBagConstraints.WEST;
this.getContentPane().add(scrollPane1,c1);
//jtAreaOutput.append("asdfjkasd;fjaskdlfjaskdlsdklsdf;sf;dfsa;dfdslk");
}
GridBagConstraints c0 ;
GridBagConstraints c1 ;
JScrollPane scrollPane1;
JScrollPane scrollPane2;
JTextArea jtAreaOutput;
JTextArea jtAreaInput;
GridBagLayout gridBag = new GridBagLayout();
}
