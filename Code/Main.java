                                                                                /**
                                                                                 * @author T.Spolnik
                                                                                 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;
public class Main {
    public static void main(String[] args){                                     // setting action and frame's visibility
        EventQueue.invokeLater(() -> {
            PhotoBrowserFrame frame = new PhotoBrowserFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
class PhotoBrowserFrame extends JFrame{                                         // Frame and menu
	private static final long serialVersionUID = -4138082191368870827L;
	Image toSave;
    BufferedImage saved;
    public PhotoBrowserFrame(){           
        setTitle("PhotoResizer");
        setSize(800, 800);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu1 = new JMenu("Browser");
        menuBar.add(menu1);
        JMenuItem openItem = new JMenuItem("Open");                             // Browsing
        menu1.add(openItem);          
        openItem.addActionListener(new PhotoBrowserListener());
        JMenuItem exitItem = new JMenuItem("Close");                            // Closing
        menu1.add(exitItem);           
        exitItem.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });            
        JMenu menu2 = new JMenu("Resizing");                                    // Resizing menu
        menuBar.add(menu2);            
        JMenuItem resize10 = new JMenuItem("10%");
        menu2.add(resize10);
        resize10.addActionListener(new Resize10());       
        JMenuItem resize20 = new JMenuItem("20%");
        menu2.add(resize20);
        resize20.addActionListener(new Resize20());
        JMenuItem resize30 = new JMenuItem("30%");
        menu2.add(resize30);
        resize30.addActionListener(new Resize30());
        JMenuItem resize40 = new JMenuItem("40%");
        menu2.add(resize40);
        resize40.addActionListener(new Resize40());
        JMenuItem resize50 = new JMenuItem("50%");
        menu2.add(resize50);
        resize50.addActionListener(new Resize50());
        JMenuItem resize60 = new JMenuItem("60%");
        menu2.add(resize60);
        resize60.addActionListener(new Resize60());
        JMenuItem resize70 = new JMenuItem("70%");
        menu2.add(resize70);
        resize70.addActionListener(new Resize70());
        JMenuItem resize80 = new JMenuItem("80%");
        menu2.add(resize80);
        resize80.addActionListener(new Resize80());
        JMenuItem resize90 = new JMenuItem("90%");
        menu2.add(resize90);
        resize90.addActionListener(new Resize90());       
        JMenu menu3 = new JMenu("About");                                        // About menu
        menuBar.add(menu3);
        JMenuItem about = new JMenuItem("About software");
        menu3.add(about);
        about.addActionListener(new AL());      
        label = new JLabel();
        add(label);       
        JScrollPane scroll = new JScrollPane(label);                            // Adding scroll to the label
        add(scroll);
        chooser = new JFileChooser();
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter("File type: .gif", "gif");// .gif filter extension
        chooser.setFileFilter(filter1);
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("File type: .jpg", "jpg");// .jpg filter extension
        chooser.setFileFilter(filter2);
        chooser.setAccessory(new OnePhotoPreviewer(chooser));      
        chooser.setFileView(new PhotoIconView(filter1, filter2, new ImageIcon("gif.ico"), new ImageIcon("jpg.ico")));  
	}           
    private class Resize10 implements ActionListener {                          // Resizing 10% of original image
        @Override
        public void actionPerformed(ActionEvent ae) {
            new ResizeMultipleFiles(1, 10);
        }
    }
    private class Resize20 implements ActionListener {                          // Resizing 20% of original image
        @Override
        public void actionPerformed(ActionEvent ae) {
            new ResizeMultipleFiles(2, 10);
        }
    }
    private class Resize30 implements ActionListener {                          // Resizing 30% of original image
        @Override   
        public void actionPerformed(ActionEvent ae) {
            new ResizeMultipleFiles(3, 10);
        }
    }
    private class Resize40 implements ActionListener {                          // Resizing 40% of original image
        @Override
        public void actionPerformed(ActionEvent ae) {
            new ResizeMultipleFiles(4, 10);
        }
    }
    private class Resize50 implements ActionListener {                          // Resizing 50% of original image
        @Override
        public void actionPerformed(ActionEvent ae) {
            new ResizeMultipleFiles(5, 10);
        }
    }
    private class Resize60 implements ActionListener {                          // Resizing 60% of original image
        @Override
        public void actionPerformed(ActionEvent ae) {
            new ResizeMultipleFiles(6, 10);
        }
    }
    private class Resize70 implements ActionListener {                          // Resizing 70% of original image
        @Override
        public void actionPerformed(ActionEvent ae) {
            new ResizeMultipleFiles(7, 10);
        }
    }
    private class Resize80 implements ActionListener {                          // Resizing 80% of original image
        @Override
        public void actionPerformed(ActionEvent ae) {
            new ResizeMultipleFiles(8, 10);
        }
    }
    private class Resize90 implements ActionListener {                          // Resizing 90% of original image
        @Override
        public void actionPerformed(ActionEvent ae) {
            new ResizeMultipleFiles(9, 10);
        }
    }
    private class AL implements ActionListener{                                 // About software
        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(null, "\n "
                    + "Click 'Browser' and then 'Open' to view files in full resolution. This only previews files.\n To resize images, click 'Resizing' to choose desired mode of resizing, and then\n "
                    + "in the browser that pops up choose the files you want to resize. File browser allows you to pick .gif or .jpg filter.\n "
                    + "Once the resized image pops up, press 'enter' to see next image's miniature, or click 'ok'.\n "
                    + "You can also hold 'enter' to go quickly through next miniatures.\n "
                    + "Files are saved in the program's directory.\n ");
        }
    } 
    private class PhotoBrowserListener implements ActionListener{               // Browsing files
        @Override
        public void actionPerformed(ActionEvent event){
            chooser.setCurrentDirectory(new File("."));                         // default directory - user's dir "My Documents" in Windows
                int result = chooser.showOpenDialog(PhotoBrowserFrame.this);
                    if (result == JFileChooser.APPROVE_OPTION){
                        String name = chooser.getSelectedFile().getPath();      
                        label.setIcon(new ImageIcon(name));
                    }
            }
    }
    public class ResizeMultipleFiles extends JFrame implements ActionListener{  // Resizes multiple image files with the desired ratio
        private static final long serialVersionUID = -3179179291413177391L;
        public ResizeMultipleFiles(int a, int b) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(".")); 
            chooser.setMultiSelectionEnabled(true);
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("File type: .gif", "gif");// .gif filter extension
            chooser.setFileFilter(filter1);
            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("File type: .jpg", "jpg");// .jpg filter extension
            chooser.setFileFilter(filter2);     
            chooser.setFileView(new PhotoIconView(filter1, filter2, new ImageIcon("gif.ico"), new ImageIcon("jpg.ico")));  
            int result = chooser.showOpenDialog(chooser);
            if (result == JFileChooser.APPROVE_OPTION){
                chooser.getSelectedFiles();                 
                BufferedImage oImage = null;
                File[] selectedFiles = chooser.getSelectedFiles();
                for (File selectedFile : selectedFiles) {
                    try {
                        oImage = ImageIO.read(selectedFile);                    // Redrawing image
                        oImage.createGraphics();
                        oImage.getGraphics();   
                        ImageIcon icon = new ImageIcon(oImage);
                        icon = new ImageIcon(icon.getImage().getScaledInstance( // Resizing image
                            icon.getIconWidth() * a / b,                        // a = scaling ratio parameter 1
                            icon.getIconHeight() * a / b,                       // b = scaling ratio parameter 2
                            Image.SCALE_SMOOTH));
                        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(icon.getIconWidth() * 1/5, icon.getIconHeight() * 1/5, Image.SCALE_SMOOTH));                    
                        Image img = icon.getImage();                
                        BufferedImage(img);         // saving scaled image       
                            try {
                                File outputfile = new File(selectedFile.getName()); // using the original file's name
                                ImageIO.write(saved, "jpg", outputfile);            // saving in .jpg format                                
                                JOptionPane.showMessageDialog(null, null, outputfile.getAbsolutePath() , 0, new ImageIcon(scaledIcon.getImage()));//showing scaled image 
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }catch (IOException ex) {
                        ex.printStackTrace();                    
                    }              
                }
            } 
            else if (result == JFileChooser.CANCEL_OPTION){
                chooser.cancelSelection();
            }         
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
        }
        private BufferedImage BufferedImage(Image img) {
            toSave = img;
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return saved = bimage;   
        }   
    }        
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 400;
    private final JLabel label;
    private final JFileChooser chooser;
}
class PhotoIconView extends FileView{                                           // Creates two extension filters .gif and .jpg
	public PhotoIconView(FileNameExtensionFilter filter1, FileNameExtensionFilter filter2, Icon anIcon1, Icon anIcon2){
            filter01 = filter1;
            filter02 = filter2;
            icon01 = anIcon1;
            icon02 = anIcon2;
	}
        @Override
	public Icon getIcon(File f){
            if (!f.isDirectory() && filter01.accept(f)) return icon01;
            if (!f.isDirectory() && filter02.accept(f)) return icon02;
            else return null;
	}
	private final FileNameExtensionFilter filter01;
      	private final FileNameExtensionFilter filter02;
	private final Icon icon01;
        private final Icon icon02;
}
class OnePhotoPreviewer extends JLabel{                                         // Previews one image file from the default output directory
	private static final long serialVersionUID = -4900294696171670597L;
	public OnePhotoPreviewer(JFileChooser chooser){
	setPreferredSize(new Dimension(400, 400));
        setBorder(BorderFactory.createEtchedBorder());
	chooser.addPropertyChangeListener((PropertyChangeEvent event) -> {
            if (event.getPropertyName() == null ? JFileChooser.SELECTED_FILE_CHANGED_PROPERTY == null : event.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)){
                File f = (File) event.getNewValue();
                if (f==null){
                    setIcon(null);
                    return;                                       
                }
                ImageIcon icon = new ImageIcon(f.getPath());
                if (icon.getIconWidth() > getWidth()) icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), -1, Image.SCALE_SMOOTH));
                setIcon(icon);
            }
        });
    }
}