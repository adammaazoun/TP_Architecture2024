package tp3;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class InterfaceSwing {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfaceSwing window = new InterfaceSwing();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InterfaceSwing() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 566, 441);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JButton btnNewButton = new JButton("Administrator");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to GUISignInAdmin window
                GUISignInAdmin s = new GUISignInAdmin();
                s.setVisible(true);
                
            }
        });
        btnNewButton.setBounds(205, 119, 123, 23);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Existant Client ");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUISignIn x = new GUISignIn();
                x.setVisible(true);
                
            }
        });
        btnNewButton_1.setBounds(205, 165, 123, 23);
        panel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("New Client");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUISignUP a = new GUISignUP();
                a.setVisible(true);
                
            }
        });
        btnNewButton_2.setBounds(205, 209, 123, 23);
        panel.add(btnNewButton_2);

        JLabel lblNewLabel = new JLabel("Select your position");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(171, 44, 186, 30);
        panel.add(lblNewLabel);
    }
}
