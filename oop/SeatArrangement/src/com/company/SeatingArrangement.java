package com.company;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.lang.invoke.*;
public class SeatingArrangement {

    JFrame seatFrame = new JFrame("Seating Arrangement");
    //create a JPanel to hold your components
    JPanel panel = new JPanel();
    //panel.add(new JButton("Name:A button"));
    private JPanel frame;
    private JTable table1;
    private JTextField name;
    private JTextField rollno;
    //private String seatNo;


    private JComboBox classDetail;
    ArrayList<String> seat = new ArrayList<>();
    public SeatingArrangement(){
        seatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        seatFrame.setContentPane(panel);
        seatFrame.pack();
        seatFrame.setLocationRelativeTo(null);
        seatFrame.setVisible(true);
        declareSeats();
        tableData();

        panel.add(new JLabel("Name:"));
        name = new JTextField(10);
        panel.add(name);
        panel.add(new JLabel("Roll No:"));
        rollno = new JTextField(10);
        panel.add(rollno);
        panel.add(new JLabel("Class:"));
        classDetail = new JComboBox();
        classDetail.addItem("yr 1.1");
        classDetail.addItem("yr 2.1");
        classDetail.addItem("yr 3.1");
        panel.add(classDetail);
        panel.add(new JLabel("Seat No:"));
        JTextField seatNo = new JTextField(10);
        panel.add(seatNo);

        JButton GETSEATButton = new JButton("GET SEAT");
        GETSEATButton.addActionListener(e -> {
            if( name.getText().equals("")|| rollno.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Please fill all record to get seat.");
            }else{
                try {
                    Random rn = new Random();
                    String seatNumber="";
                    if(seat.size()==0){
                        JOptionPane.showMessageDialog(null,"THERE ARE NO SEATS AVAILABLE");
                    }else{
                        seatNumber = seat.get(rn.nextInt(seat.size()));
                    }
                    String sql = "insert into seat"+"(name,rollno,classDetail,seatNo)"+"values (?,?,?,?)";
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/intern","root","root");
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1,name.getText());
                    statement.setString(2,rollno.getText());
                    statement.setString(3,""+classDetail.getSelectedItem());
                    statement.setString(4,seatNumber);
                    seat.remove(seatNo);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"RECORD ADDED SUCCESSFULLY");
                    name.setText("");
                    rollno.setText("");
                    for(String i:seat){
                        System.out.println(i);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                tableData();
            }

        });
        panel.add(GETSEATButton);
    }
    public void declareSeats(){
        for(int i=1;i<=3;i++){
            for(int j=1;j<=10;j++){
                String s = "R"+i+"S"+j;
                seat.add(s);
            }
        }
        try {
            String a= "Select seatNo from seat";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/intern","root","root");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(a);
            while (rs.next()){
                seat.remove(rs.getString(1));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void tableData() {
        try{
            JTable table1 = new JTable();
            String a= "Select* from seat";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/intern","root","root");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(a);
            table1.setModel(buildTableModel(rs));
        }catch (Exception ex1){
            JOptionPane.showMessageDialog(null,ex1.getMessage());
        }
    }
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
// names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
// data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
   }
