package book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Student {
	private String name;
	private String ID;
	private volatile String grade;
	private int i;

	private Student() {
		this.name = name;
		this.grade = grade;
		this.ID = grade;
	}
	private Student(Student students) {
		this.name = new String(students.name);
		this.ID = new String(students.ID);
		this.grade = new String(students.grade);
	}
	static ArrayList <Student> students = new ArrayList();
	public static void main(String[] args) {
		Student newstude = new Student();
		
		
		JFrame frame = new JFrame("Welcome to your Student Manager");
		frame.setSize(800, 600);
		JCheckBox checkBox1 = new JCheckBox("Add new Student");
		JCheckBox checkBox2 = new JCheckBox("View all Students");
		JCheckBox checkBox3 = new JCheckBox("Search for a student by thier ID");
		
		checkBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBox1.isSelected()) {
					addStudent();
				}
			}
		});
		
		checkBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBox2.isSelected()) {
					viewAllStudents();
				}
			}
		});
		frame.add(checkBox1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		System.out.print("hau");
		
	}
	private static void addStudent() {
		Student one = new Student();
		one.name = JOptionPane.showInputDialog("Enter Name");
		one.ID = JOptionPane.showInputDialog("Enter Student ID");
		one.grade = JOptionPane.showInputDialog("Enter Student's Grade");
		students.add(one);
	}
	private static void viewAllStudents() {
		JFrame frame = new JFrame();
		JTextArea textArea
		
	}

}
