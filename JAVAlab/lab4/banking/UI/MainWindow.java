package banking.UI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import banking.DAO.*;
import banking.domain.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.text.AttributedCharacterIterator;

public class MainWindow extends JFrame{
	//Specify the size of two string fields in the record
	final static int FIRST_NAME_SIZE=20;
	final static int LAST_NAME_SIZE=20;
	final static int RECORD_SIZE=(FIRST_NAME_SIZE+LAST_NAME_SIZE);
	
	//**********************************************Window components
	//Access address.dat using RandomAccessFile
	private RandomAccessFile raf;
	
	//Text fields
	private JTextField jtfFirstName=new JTextField(FIRST_NAME_SIZE);
	private JTextField jtfLastName=new JTextField(LAST_NAME_SIZE);
	
	//Buttons
	private JButton jbtAdd=new JButton("ADD");
	private JButton jbtDelete=new JButton("DELETE");
	private JButton jbtSearch=new JButton("SEARCH");
	private JButton jbtSort=new JButton("SORT");
	
	//Create customer Lists and the list model
	private DefaultListModel model=new DefaultListModel();
	private JList jlCustomer=new JList(model);
	
	//***********************************************
	
	Bank bank=Bank.getBank();
	
	public MainWindow()
	{
		//Open a random acess file if it exists
		try
		{
			raf=new RandomAccessFile("bank.txt","rw");
		}
		catch(IOException ex)
		{
			System.out.print("Error: "+ex);
			System.exit(0);
		}
		
		//*************************************Puts the components
		//Panel p1 for holding labels customers list
		JPanel p1=new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(new JLabel("Customers List"),BorderLayout.CENTER);
		
		//Panel list for holding the customers list
		JPanel list=new JPanel();
		list.setLayout(new BorderLayout());
		list.add(jlCustomer,BorderLayout.CENTER);
		
		//Panel combined1 p1 and list
		JPanel combined1=new JPanel();
		combined1.setLayout(new BorderLayout());
		combined1.add(p1,BorderLayout.NORTH);
		combined1.add(list,BorderLayout.CENTER);
		
		//Panel jpFirstName
		JPanel jpFirstName=new JPanel();
		jpFirstName.setLayout(new BorderLayout());
		jpFirstName.add(new JLabel("FirstName"),BorderLayout.WEST);
		jpFirstName.add(jtfFirstName,BorderLayout.CENTER);
		
		//Panel jpLastName
		JPanel jpLastName=new JPanel();
		jpLastName.setLayout(new BorderLayout());
		jpLastName.add(new JLabel("LastName"),BorderLayout.WEST);
		jpLastName.add(jtfLastName,BorderLayout.CENTER);
		
		//Panel combined2 for holding FirstName and LastName
		JPanel combined2=new JPanel();
		combined2.setLayout(new GridLayout(4,1));
		combined2.add(new JPanel());
		combined2.add(jpFirstName);
		combined2.add(new JPanel());
		combined2.add(jpLastName);
		
		//Panel p2 for buttons
		JPanel p2=new JPanel();
		p2.add(jbtAdd);
		p2.add(jbtDelete);
		p2.add(jbtSearch);
		p2.add(jbtSort);
		
		//Panel p3
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(2,1));
		p3.add(new JPanel());
		p3.add(p2);
		
		//Panel combined3 for holding p2 and combined2
		JPanel combined3=new JPanel();
		combined3.setLayout(new GridLayout(2,1));
		combined3.add(combined2);
		combined3.add(p3);
		
		//Set the panel with line border
		combined1.setBorder(new BevelBorder(BevelBorder.RAISED));
		combined3.setBorder(new BevelBorder(BevelBorder.RAISED));
		add(combined1,BorderLayout.WEST);
		add(combined3,BorderLayout.EAST);
		//*********************************************
		
		//*************************************Preparation
		//Write the records in the file into the bank
		try {
			writeToBank();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		//Write the records in the bank into the list
		for(int i=0;i<bank.getNumOfCustomers();i++)
		{
			model.addElement(bank.getCustomer(i).getFirstName()+","+bank.getCustomer(i).getLastName());
		}
		
		//**********************************************************
		
		//**************************************Functions of the buttons
		jbtAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				write();
				bank.addCustomer(jtfFirstName.getText(), jtfLastName.getText());;
				String fn=jtfFirstName.getText().replaceAll(" ", " ");
				String ln=jtfLastName.getText().replaceAll(" ", " ");
				model.addElement(fn+","+ln);
			}
		});
		
		jlCustomer.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				if(bank.getNumOfCustomers()==0)//No customers
				{
					jtfFirstName.setText(" ");
					jtfLastName.setText(" ");
				}
				else if(jlCustomer.getSelectedIndex()==-1)//No selection
				{
					int numberOfCustomer = bank.getNumOfCustomers();
					jtfFirstName.setText(bank.getCustomer(0).getFirstName());
					jtfLastName.setText(bank.getCustomer(0).getLastName());
				}
				else
				{
					jtfFirstName.setText(bank.getCustomer(jlCustomer.getSelectedIndex()).getFirstName());
					jtfLastName.setText(bank.getCustomer(jlCustomer.getSelectedIndex()).getLastName());
				}
			}
		});
		
		jbtDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				bank.removeCustomer(jlCustomer.getSelectedIndex());
				model.removeAllElements();
				for(int i=0;i<bank.getNumOfCustomers();i++)
					model.addElement(bank.getCustomer(i).getFirstName()+","+bank.getCustomer(i).getLastName());
				try {
					Reorganize();
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		jbtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int index=bank.searchCustomers(jtfFirstName.getText(),jtfLastName.getText());
				if(index!=-1)
					jlCustomer.setSelectedIndex(index);
				else
					System.out.println("NOT FOUND!");
			}
		});
		
		jbtSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				bank.sortCustomers();
				model.removeAllElements();
				for(int i=0;i<bank.getNumOfCustomers();i++)
					model.addElement(bank.getCustomer(i).getFirstName()+" "+bank.getCustomer(i).getLastName());
				try
				{
					Reorganize();
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
			}
		});
	}
	
	//*********************************************************
	
	public void write() {
		try
		{
			raf.seek(raf.length());
			FixedLengthStringIO.writeFixedLengthString(jtfFirstName.getText(),FIRST_NAME_SIZE,raf);
			FixedLengthStringIO.writeFixedLengthString(jtfLastName.getText(), LAST_NAME_SIZE, raf);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void read(long position)throws IOException {
		raf.seek(position);
		String FirstN=FixedLengthStringIO.readFixedLengthString(FIRST_NAME_SIZE, raf);
		String LastN=FixedLengthStringIO.readFixedLengthString(LAST_NAME_SIZE, raf);
		
		jtfFirstName.setText(FirstN);
		jtfLastName.setText(LastN);
	}
	
	public void Reorganize() throws IOException
	{
		File file=new File("bank.txt");
		try {
			FileWriter fwriter=new FileWriter(file);
			fwriter.write("");
			fwriter.flush();
			fwriter.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		for(int i=0;i<bank.getNumOfCustomers();i++)
		{
			try {
				raf.seek(raf.length());
				FixedLengthStringIO.writeFixedLengthString(bank.getCustomer(i).getFirstName(), FIRST_NAME_SIZE, raf);
				FixedLengthStringIO.writeFixedLengthString(bank.getCustomer(i).getLastName(), LAST_NAME_SIZE, raf);
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public void writeToBank() throws IOException
	{
		int length=0;
		try {
			length=(int)raf.length();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		for(int i=0;i<length/2/RECORD_SIZE;++i)
		{
			raf.seek(i*2*RECORD_SIZE);
			String FirstName=FixedLengthStringIO.readFixedLengthString(FIRST_NAME_SIZE, raf).replaceAll(" ", "");
			String LastName=FixedLengthStringIO.readFixedLengthString(LAST_NAME_SIZE, raf).replaceAll(" ", "");
			bank.addCustomer(FirstName, LastName);
		}
	}
	
	public static void main(String[] args)
	{
		MainWindow mWindow=new MainWindow();
		mWindow.pack();
		mWindow.setTitle("Bank");
		mWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mWindow.setVisible(true);
	}
}
