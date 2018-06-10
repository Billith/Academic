/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Main extends JFrame {
	static MyTableModel mtm;
	public Main(List<Book> books) {
		mtm = new MyTableModel(books);
		JTable tbl = new JTable(mtm);
		JPanel buttonPane = new JPanel();
		JButton b_add = new JButton("Add");
		JButton b_remove = new JButton("Remove");
		
		b_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddBookFrame();
			}
		});
		
		b_remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mtm.removeRow(tbl.getSelectedRow());
				} catch (Exception e1) {
					JOptionPane op = new JOptionPane();
					op.showMessageDialog(getContentPane(), "Nothing to remove! Select something first you moron!");
				}
			}
		});
		
		tbl.setRowHeight(80);
		buttonPane.add(b_add);
		buttonPane.add(b_remove);
		buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JScrollPane(tbl), BorderLayout.CENTER);
		add(buttonPane, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(600, getPreferredSize().height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

  public static void main(String[] args) {
	  List<Book> books = new ArrayList<Book>();
	  try {
		  Scanner sf = new Scanner(new File("res/books.txt"));
		  while(sf.hasNextLine()) {
			  String[] line = sf.nextLine().split(";");
			  String author = line[0];
			  String title = line[1];
			  String coverFile = "res/"+line[3];
			  NumberFormat nf = NumberFormat.getInstance();
			  try {
				  double price = nf.parse(line[2]).doubleValue();
				  books.add(new Book(author, title, price, getScaledImage(new ImageIcon(coverFile))));
			  } catch (ParseException e) {
				  e.printStackTrace();
			  }
		  }
	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
	  }
	  new Main(books);
  }
  public static ImageIcon getScaledImage(ImageIcon srcImg){
	  double h = srcImg.getIconHeight();
	  double w = srcImg.getIconWidth();
	  double scale = h/w ;
	  int width = (int) Math.round(100 / scale);
	  java.awt.Image resizedImage = srcImg.getImage().getScaledInstance(width, 100, java.awt.Image.SCALE_SMOOTH);
	  return new ImageIcon(resizedImage);
  }
}
