import javax.swing.*;
import java.awt.event.*;
class TestWindow implements ActionListener{
    JTextField txtBox;
    public static void main(String[] args) {
        // c
        // reate a new frame
        System.out.println('\n');
        TestWindow gui = new TestWindow();
        gui.go();

    }
    public void go(){
        JFrame frame = new JFrame("frame");
        txtBox =new JTextField(1);
        //txtBox.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(txtBox);
        frame.setSize(300,300);
        frame.setVisible(true);

    }

    @Override

    public void actionPerformed(ActionEvent e) {
    }
}