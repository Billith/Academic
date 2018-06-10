/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

public class Main extends JFrame {
	protected static String cf = null; //AKTUALNIE WCZYTANY PLIK
	protected static String orgContent = ""; //ZAWARTOŚĆ PLIKU W MOMENCIE WCZYTANIA LUB PO SAVIE
	protected static File lastDirectory = new File(".");

	Main() {
		String[] fileOptions = { "Open", "Save", "Save as ...", " ", "Exit" };
		String[] adreses = { "Praca", "Szkoła", "Dom" };
		String[] colors = { "Blue", "Yellow", "Orange", "Red", "White", "Black", "Green" };
		String title = "Prosty edytor - ", titleEmp = "bez tytułu";

		JTextArea jta = new JTextArea();
		JScrollPane jp = new JScrollPane(jta); 
		JMenuBar jmb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu adresy = new JMenu("Adresy");
		JMenu options = new JMenu("Options");
		JMenu fontSize = new JMenu("Font size");
		JMenu f = new JMenu("Foreground");
		JMenu b = new JMenu("Background");
		
		UIManager.put("PopupMenu.border", BorderFactory.createBevelBorder(BevelBorder.RAISED)); //WYPUKŁOŚĆ
		UIManager.put("MenuItem.border", BorderFactory.createBevelBorder(BevelBorder.RAISED)); //WYPUKŁOŚĆ
		jta.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK), "none"); // USUWANIE DOMYSLNEGO CTRL+A
		jta.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK), "none"); // USUWANIE DOMYSLNEGO CTRL+X

		for (String fileOption : fileOptions) {
			if ( fileOption == " ") { // SEPARATOR
				JMenuItem fileOpt = new JMenuItem(fileOption) {
					public void paintComponent(Graphics g) {
						g.setColor(Color.red);
						g.drawLine(5, 2, 103, 2);
						g.drawLine(5, 3, 103, 3);
					}
				};
				fileOpt.setPreferredSize(new Dimension(10, 7));
				file.add(fileOpt);
			} else {
			JMenuItem fileOpt = new JMenuItem(fileOption);
			fileOpt.setName(fileOption);
			fileOpt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String cmd = e.getActionCommand();
					if (cmd.equals("Open")) {
						JFileChooser openFile = new JFileChooser(lastDirectory);
						int returnVal = openFile.showOpenDialog(Main.this);
						
						if(returnVal == JFileChooser.APPROVE_OPTION) {
							File currentFile = openFile.getSelectedFile();
							Main.cf = currentFile.getPath();
							setTitle(title+openFile.getSelectedFile().getPath());
							try (FileReader reader = new FileReader(currentFile)){	
								jta.read(reader, null);
								orgContent = jta.getText();
							} catch (Exception ee) {
								System.out.println(ee);
							}
						}
						int len = jta.getDocument().getLength(); //USTAWIENIE KARETY NA KOŃCU TEKSTU
						jta.setCaretPosition(len); //USTAWIENIE KARETY NA KOŃCU TEKSTU
						lastDirectory = openFile.getCurrentDirectory();
					} else if (cmd.equals("Exit")) {
						saveFile(jta.getText(), jta);
					} else if (cmd.equals("Save")) {
						smallSave(jta);
					} else if (cmd.equals("Save as ...")) {
						JFileChooser saveFileTo = new JFileChooser(lastDirectory); // WYBÓR PLIKU, BIEŻĄCY KATALOG
						saveFileTo.setApproveButtonText("Save");
						saveFileTo.setDialogTitle("Save as ...");
						int returnVal = saveFileTo.showOpenDialog(Main.this);
						
						if(returnVal == JFileChooser.APPROVE_OPTION) {
							File selectedFile = saveFileTo.getSelectedFile();
							try(FileWriter fw = new FileWriter(selectedFile)) {
								jta.write(fw);
							} catch (IOException e1) {
								System.out.println(e);
							}
						}
						lastDirectory = saveFileTo.getCurrentDirectory();
					}
				}
			});
			accele(fileOpt, fileOption);
			mnemo(fileOpt, fileOption);
			file.add(fileOpt);
			}
		}

		for (String adres : adreses) {
			JMenuItem adresButton = new JMenuItem(adres);
			adresButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					switch(e.getActionCommand()) {
						case "Praca" : jta.insert(" adres firmy ", jta.getCaretPosition());break; // WSTAWIANIE TESKTU NA POZYCJI KARETY
						case "Szkoła" : jta.insert(" adres szkoły ", jta.getCaretPosition());break; // WSTAWIANIE TESKTU NA POZYCJI KARETY
						case "Dom" : jta.insert(" adres zamieszkania ", jta.getCaretPosition());break; // WSTAWIANIE TESKTU NA POZYCJI KARETY
					}
				}
			});
			accele(adresButton, adres);
			mnemo(adresButton, adres);
			adresy.add(adresButton);
			adresy.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); //WYPUKŁOŚĆ
		}
		
		for (String txt : colors) {
			JMenuItem fcolor = new JMenuItem(txt);
			fcolor.setIcon(new DrawIcon(txt)); // IKONY
			JMenuItem bcolor = new JMenuItem(txt);
			f.add(fcolor);
			fcolor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					switch (e.getActionCommand()) {
						case "Blue": jta.setForeground(java.awt.Color.blue);break;
						case "Yellow" : jta.setForeground(java.awt.Color.yellow);break;
						case "Orange" : jta.setForeground(java.awt.Color.orange);break;
						case "Red" : jta.setForeground(java.awt.Color.red);break;
						case "White" : jta.setForeground(java.awt.Color.white);break;
						case "Black" : jta.setForeground(java.awt.Color.black);break;
						case "Green" : jta.setForeground(java.awt.Color.green);break;
					}
				}
			});
			b.add(bcolor);
			bcolor.setIcon(new DrawIcon(txt)); // IKONY
			bcolor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					switch (e.getActionCommand()) {
						case "Blue": jta.setBackground(java.awt.Color.blue);break;
						case "Yellow" : jta.setBackground(java.awt.Color.yellow);break;
						case "Orange" : jta.setBackground(java.awt.Color.orange);break;
						case "Red" : jta.setBackground(java.awt.Color.red);break;
						case "White" : jta.setBackground(java.awt.Color.white);break;
						case "Black" : jta.setBackground(java.awt.Color.black);break;
						case "Green" : jta.setBackground(java.awt.Color.green);break;
					}
				}
			});
		}
		
		for(int i=8;i<=24;i+=2) {
			JMenuItem font = new JMenuItem(i+" pts");
			font.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					switch (e.getActionCommand()) {
						case "8 pts" : jta.setFont(new Font("Arial", Font.PLAIN, 8));break;
						case "10 pts" : jta.setFont(new Font("Arial", Font.PLAIN, 10));break;
						case "12 pts" : jta.setFont(new Font("Arial", Font.PLAIN, 12));break;
						case "14 pts" : jta.setFont(new Font("Arial", Font.PLAIN, 14));break;
						case "16 pts" : jta.setFont(new Font("Arial", Font.PLAIN, 16));break;
						case "18 pts" : jta.setFont(new Font("Arial", Font.PLAIN, 18));break;
						case "20 pts" : jta.setFont(new Font("Arial", Font.PLAIN, 20));break;
						case "22 pts" : jta.setFont(new Font("Arial", Font.PLAIN, 22));break;
						case "24 pts" : jta.setFont(new Font("Arial", Font.PLAIN, 24));break;
					}
				}
			});
			fontSize.add(font);
			fontSize.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); // WYPUKŁOŚĆ
		}
		addWindowListener(new WindowAdapter() { // OKNO ZAPISU PRZY WYJSCIU Z NOTATNIKA
			public void windowClosing(WindowEvent evt) {
				saveFile(jta.getText(), jta);
			}
		});
		
		add(jp);
		edit.add(adresy);
		options.add(f);
		options.add(b);
		options.add(fontSize);
		jmb.add(file);
		jmb.add(edit);
		jmb.add(options);
		
		f.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); // WYPUKŁOŚĆ
		b.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); // WYPUKŁOŚĆ
		jta.setFont(new Font("Arial", Font.PLAIN, 12)); // DOMYŚLNA CZCIONKA
		setTitle(title+titleEmp);
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(200, 150));
		setJMenuBar(jmb);
		setVisible(true);
	}

  public static void main(String[] args) {
	  SwingUtilities.invokeLater(new Runnable() {
		  public void run() {
			  new Main();
		  }
	  });
  }
  public void saveFile(String currentContent, JTextArea jta) {
	  if (currentContent.equals(orgContent)) {
			System.exit(0);
		} else {
			int answer = JOptionPane.showConfirmDialog(null, "Czy chcesz zapisać zmiany?", "Prosty notatnik", JOptionPane.YES_NO_CANCEL_OPTION );
			if (answer == JOptionPane.YES_OPTION) {
				smallSave(jta);
			}
			else if (answer == JOptionPane.NO_OPTION) {
				System.exit(0);
			} else if (answer == JOptionPane.CANCEL_OPTION) {
				setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			}
		}
  }
  public void smallSave (JTextArea jta) {
	  if (Main.cf != null) { 
			try (FileWriter fw = new FileWriter(new File(Main.cf))) {
				jta.write(fw);
			} catch (IOException e1) {
				System.out.println(e1);
			}
			orgContent = jta.getText();
	  } else {
		  JFileChooser saveContentToFile = new JFileChooser(lastDirectory); // WYBÓR PLIKU, BIEŻĄCY KATALOG
		  saveContentToFile.setApproveButtonText("Save");
		  saveContentToFile.setDialogTitle("Save");
		  int answer = saveContentToFile.showOpenDialog(Main.this);
		  if (answer == JFileChooser.APPROVE_OPTION) {
			  try (FileWriter fw = new FileWriter(saveContentToFile.getSelectedFile())) {
					jta.write(fw);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			  System.exit(0);
		  } else {
			  setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		  }
		  lastDirectory = saveContentToFile.getCurrentDirectory();
	  }
  }
  public void accele(JMenuItem i, String s) {
	  switch(s) {
		  case "Open" : i.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));break;
		  case "Save" : i.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));break;
		  case "Save as ..." : i.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));break;
		  case "Exit" : i.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));break;
		  case "Praca" : i.setAccelerator(KeyStroke.getKeyStroke("control shift P"));break;
		  case "Szkoła" : i.setAccelerator(KeyStroke.getKeyStroke("control shift S"));break;
		  case "Dom" : i.setAccelerator(KeyStroke.getKeyStroke("control shift D"));break;
	  }
  }
  public void mnemo(JMenuItem i, String s) {
	  switch(s) {
		  case "Open" : i.setMnemonic(KeyEvent.VK_O);break;
		  case "Save" : i.setMnemonic(KeyEvent.VK_S);;break;
		  case "Save as ..." : i.setMnemonic(KeyEvent.VK_A);break;
		  case "Exit" : i.setMnemonic(KeyEvent.VK_X);break;
		  case "Praca" : i.setMnemonic(KeyEvent.VK_P);break;
		  case "Szkoła" : i.setMnemonic(KeyEvent.VK_S);break;
		  case "Dom" : i.setMnemonic(KeyEvent.VK_D);break;
	  }
  }
}