package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class Database {

    String url;
    TravelData travelData;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
    }

    public void create() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();

                try {
                    stmt.execute("DROP TABLE Offers;");
                } catch (SQLException sqle) { }

                stmt.execute("CREATE TABLE IF NOT EXISTS Offers (\n"
                        + " id integer PRIMARY KEY,\n"
                        + " description next NOT NULL\n"
                        + ");"
                );
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public void showGui() {

        String[] choices = { "polski", "english"};
        String loc = (String) JOptionPane.showInputDialog(
                null,
                "Choose language",
                null,
                JOptionPane.PLAIN_MESSAGE,
                null,
                choices,
                choices[0]
        );

        Locale locale = (loc.equals("polski")) ? new Locale("pl-PL") : new Locale("en-GB");

        //PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Offers(id, description) VALUES(?,?)");
        //pstmt.setInt();

        JFrame mainWindow = new JFrame();
        JTable table = new JTable(new DefaultTableModel(25,1));
        //table.setPreferredSize(new Dimension(400,500));
        mainWindow.setMinimumSize(new Dimension(table.getPreferredSize().width + 50,table.getPreferredSize().height + 50));
        mainWindow.setLocationRelativeTo(null);
        mainWindow.add(table);
        mainWindow.setVisible(true);
    }
}
