package zad3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddBookFrame extends JFrame {

	ImageIcon imgIcon;

	public AddBookFrame() {
		JPanel contentPane = new JPanel();
		JPanel textPane = new JPanel();
		JPanel imgPane = new JPanel();
		JPanel labelPane = new JPanel();
		JPanel bPane = new JPanel();
		JLabel l_author = new JLabel(" Author ");
		JLabel l_title = new JLabel(" Title ");
		JLabel l_price = new JLabel(" Price ");
		JLabel l_image = new JLabel();
		JTextField author = new JTextField();
		JTextField title = new JTextField();
		JTextField price = new JTextField();
		JButton b_img = new JButton("Choose cover ...");
		JButton addImg = new JButton("Add");
		JButton cancelImg = new JButton("Cancel");

		b_img.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser img = new JFileChooser();
				int answer = img.showOpenDialog(new JFrame());

				if (answer == JFileChooser.APPROVE_OPTION) {
					imgIcon = new ImageIcon(img.getSelectedFile().getAbsolutePath());
					l_image.setIcon(getScaledImage(imgIcon, textPane.getPreferredSize().height));
				}

			}
		});

		addImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Main.mtm.addBook(author.getText(), title.getText(), price.getText().replace(',', '.'), imgIcon);
					dispose();
				} catch (Exception e1) {
					JOptionPane op = new JOptionPane();
					op.showMessageDialog(getContentPane(), "Something went wrong! Probably you didn't entered data or entered bad data type.");
				}
			}
		});

		cancelImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		labelPane.add(l_author);
		labelPane.add(l_title);
		labelPane.add(l_price);
		textPane.add(author);
		textPane.add(title);
		textPane.add(price);
		textPane.add(b_img);
		bPane.add(addImg);
		bPane.add(cancelImg);
		imgPane.add(l_image);
		contentPane.add(labelPane, BorderLayout.WEST);
		contentPane.add(textPane, BorderLayout.CENTER);
		contentPane.add(imgPane, BorderLayout.EAST);
		contentPane.add(bPane, BorderLayout.SOUTH);

		textPane.setLayout(new GridLayout(4, 1));
		labelPane.setLayout(new GridLayout(4, 1));
		bPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		imgPane.setPreferredSize(new Dimension(150, textPane.getPreferredSize().height));
		labelPane.setPreferredSize(new Dimension(labelPane.getPreferredSize().width, textPane.getPreferredSize().height));
		textPane.setPreferredSize(new Dimension(200, textPane.getPreferredSize().height));
		bPane.setPreferredSize(new Dimension(430, bPane.getPreferredSize().height));
		l_image.setIcon(getScaledImage(new ImageIcon("res/default.png"), textPane.getPreferredSize().height));
		contentPane.setPreferredSize(new Dimension(430, 150));
		setResizable(false);
		add(contentPane);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public static ImageIcon getScaledImage(ImageIcon srcImg, int height){
		  double h = srcImg.getIconHeight();
		  double w = srcImg.getIconWidth();
		  double scale = h/w ;
		  int width = (int) Math.round(height / scale);
		  java.awt.Image resizedImage = srcImg.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		  return new ImageIcon(resizedImage);
	}
}
