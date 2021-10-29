import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DeskApp extends JFrame implements ActionListener{
    JTextArea textArea = new JTextArea();
    JMenuItem openItem = new JMenuItem("Open");
    JMenuItem saveItem = new JMenuItem("Save");
    JMenuItem exitItem = new JMenuItem("Exit");
    JScrollPane scrollBar = new JScrollPane(textArea);
    JSpinner fontSizeSpinner = new JSpinner();
    JButton colorButton = new JButton("Color");
    JComboBox fontBox;
    DeskApp(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Mmanzi Text editor");
        ImageIcon image = new ImageIcon("C:\\Users\\MUDAHEMUKA MANZI\\Pictures\\signature.png");
        this.setIconImage(image.getImage());
        this.setLocation(300, 50);
        this.setLayout(new FlowLayout());

//         textArea.setPreferredSize(new Dimension(450, 450));
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Helvetica", Font.PLAIN, 20));
        this.setSize(700, 580);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        this.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        scrollBar.setPreferredSize(new Dimension(650, 450));
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        fontSizeSpinner.setPreferredSize(new Dimension(50, 20));
        fontSizeSpinner.setValue(20);
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));
            }
        });
        colorButton.addActionListener(this);
        JLabel fontLabel = new JLabel("Font-size");
//         String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//         fontBox = new JComboBox(fonts);
//         fontBox.setSelectedItem("Helvetica");
//         fontBox.setSelectedIndex(4);
//         fontBox.addActionListener(this);
        this.add(fontLabel);
        this.add(fontSizeSpinner);
        this.add(colorButton);
//         this.add(fontBox);
        this.setVisible(true);
        this.add(scrollBar);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == openItem){
            JFileChooser fileChooser = new JFileChooser();
            System.out.println(fileChooser.showOpenDialog(null));

        }
        if(e.getSource() == colorButton){
            JColorChooser colorChooser = new JColorChooser();
            Color color = colorChooser.showDialog(null, "Choose a color", Color.black);
            textArea.setForeground(color);
        }
        if(e.getSource() == fontBox){
            textArea.setFont(new Font((String)fontBox.getSelectedItem(), Font.PLAIN , textArea.getFont().getSize()));
        }
        if(e.getSource() == saveItem){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showSaveDialog(null);
            if(response == 0){
                try {
                    FileWriter fileWriter = new FileWriter(saveItem.getName());
                    fileWriter.append(saveItem.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
