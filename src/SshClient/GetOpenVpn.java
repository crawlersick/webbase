package SshClient;

import baseonweb.Xmltools;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.beans.*;
import java.io.File;
import java.util.Random;
import sun.management.FileSystem;
 
public class GetOpenVpn extends JPanel
                              implements ActionListener, 
                                         PropertyChangeListener {
 
    private JProgressBar progressBar;
    private JButton startButton;
    private JTextArea taskOutput;
    private Task task;
    private static String OvpnOutput=null;
    AppspotSocket appsock;
    
    public static String getOvpnOutput(){return OvpnOutput;} 
 
    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            //Initialize progress property.
            setProgress(0);
            try {
                //Sleep for at least one second to simulate "startup".
               // try {
               //     Thread.sleep(1000 + random.nextInt(2000));
               // } catch (InterruptedException ignore) {}
                appsock= new AppspotSocket("sorryformynet");
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(3);
            }
            Thread tempthd=new Thread(new AppspotsockThread(appsock));
            tempthd.start();
            
          
            while (progress < 100) {
                //Sleep for up to one second.
                //Make random progress.
          
                progress =appsock.getProgress();
                
                //System.out.println("%$^####$%^^#$^^"+progress);
                
                setProgress(Math.min(progress, 100));

                try {
                    Thread.sleep(1000);
                } catch (Exception ignore) {
                System.out.println(ignore.toString());
                }

            
            
            
            }
            return null;
        }
 
        /*
         * Executed in event dispatch thread
         */
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            startButton.setEnabled(true);
            taskOutput.append("Done!\n");
        }
    }
 
    public GetOpenVpn() {
        super(new BorderLayout());
 
        //Create the demo's UI.
        startButton = new JButton("Start");
        startButton.setActionCommand("start");
        startButton.addActionListener(this);
 
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
 
        //Call setStringPainted now so that the progress bar height
        //stays the same whether or not the string is shown.
        progressBar.setStringPainted(true); 
 
        taskOutput = new JTextArea(5, 20);
        taskOutput.setMargin(new Insets(5,5,5,5));
        taskOutput.setEditable(false);
 
        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(progressBar);
 
        add(panel, BorderLayout.PAGE_START);
        add(new JScrollPane(taskOutput), BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        

        
        
    }
 
    /**
     * Invoked when the user presses the start button.
     */
    public void actionPerformed(ActionEvent evt) {
        progressBar.setIndeterminate(true);
        startButton.setEnabled(false);
        //Instances of javax.swing.SwingWorker are not reusuable, so
        //we create new instances as needed.
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }
 
    /**
     * Invoked when task's progress property changes.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setIndeterminate(false);
            progressBar.setValue(progress);
            taskOutput.append(String.format(
                        "Completed %d%% of task.\n", progress));
        }
    }
 
    /**
     * Create the GUI and show it. As with all GUI code, this must run
     * on the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        
        
        
        Xmltools xml_tool=new Xmltools();
        File xmlfile = new File("cfg_ovpn.xml");
        if(!xmlfile.exists())
        {
                JFileChooser chooser = new JFileChooser();
                
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("choose the folder to output ovpn files for your first run");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
               //   System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
                  OvpnOutput=chooser.getSelectedFile().toString()+java.nio.file.FileSystems.getDefault().getSeparator();
                  System.out.println("getSelectedFile() : " + OvpnOutput);
                try {
                    xml_tool.writeconfig(OvpnOutput, xmlfile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error:"+ex.toString());
                    System.exit(1);
                } 
                  
                } else {
                  System.out.println("No Selection ");
                  System.exit(0);
                }
        }
        else
        {
            try {
                OvpnOutput=(String) xml_tool.readconfig(xmlfile);
            }catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error:"+ex.toString());
                    System.exit(1);
                }
            System.out.println("read the ovpnoutput: "+OvpnOutput);
        }

        
        //Create and set up the window.
        JFrame frame = new JFrame("OpenVPN Config File Downloader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new GetOpenVpn();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        

        
        
    }
 
    public static void main(String[] args) {
        try {
            //Schedule a job for the event-dispatching thread:
            //creating and showing this application's GUI.
            if(System.getProperty("file.separator").equals("/"))
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            if(System.getProperty("file.separator").equals("\\"))
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                

                
                
                createAndShowGUI();
            }
        });
    }
}


class AppspotsockThread implements Runnable
{
AppspotSocket appsock;
    public AppspotsockThread(AppspotSocket appsock){this.appsock=appsock;}
    @Override
    public void run() {
        
                try {
            String restr=appsock.URLConmunicate("urlfopenvpn?qtype=http://www.vpngate.net/api/iphone/");
            int delaynum=90;
            int speednum=3000000;
            String targetoutputfolder=GetOpenVpn.getOvpnOutput();
            appsock.resultAnalyst(restr,delaynum,speednum,targetoutputfolder);
            appsock.closeappsocket();
                    } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "please re-try ,Error:"+ex.toString());
            //System.exit(2); 
                                            }
    }

}