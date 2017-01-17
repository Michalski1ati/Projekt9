package profilowanie2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class AdressBookDBProfiling extends JFrame implements WindowListener, ActionListener {
	//list = new JList<PersonDBProfiling>();
	private JList<PersonDBProfiling> list;
	
	public AdressBookDBProfiling(){
		
		
		super("Adress Book");
		setBounds(100, 100, 550, 500);
		setLayout(null);
				
		
		addWindowListener(this);
		
		
		list = new JList<PersonDBProfiling>();
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(-1);
		JScrollPane pane = new JScrollPane(list);
		pane.setBounds(10, 10, 260, 440);
		add(pane);
		
		
		 JButton button = new JButton("Dodaj");
        button.setBounds(280, 10, 200, 30);
        button.addActionListener(this);
        button.setActionCommand("dodaj");
        add(button);

        button = new JButton("Usuń");
        button.setBounds(280, 50, 200, 30);
        button.addActionListener(this);
        button.setActionCommand("usuń");
        add(button);

        button = new JButton("Sortuj według nazwiska");
        button.setBounds(280, 90, 200, 30);
        button.addActionListener(this);
        button.setActionCommand("sortnazwisko");
        add(button);
		
		PersonDBProfiling.readDB(new File("person.db"));
				
		
		list.setListData( PersonDBProfiling.getPersonArray() );
		
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("add")){
			
			AdressBookPersonAddDBProfiling popup = new AdressBookPersonAddDBProfiling();
			
			//adding new window listener here to get access to JList
			popup.addWindowListener(new WindowListener() {
				
				@Override
				public void windowOpened(WindowEvent e) {}
				
				@Override
				public void windowIconified(WindowEvent e) {}
				
				@Override
				public void windowDeiconified(WindowEvent e) {}
				
				@Override
				public void windowDeactivated(WindowEvent e) {}
				
				@Override
				public void windowClosing(WindowEvent e) {}
				
				@Override
				public void windowClosed(WindowEvent e) {
					
					list.setListData( PersonDBProfiling.getPersonArray() );
					
				}
				
				@Override
				public void windowActivated(WindowEvent e) {}
			});
			
		}
		else if(e.getActionCommand().equals("remove")){
			PersonDBProfiling.removePerson( list.getSelectedValue() );
		}
		else if(e.getActionCommand().equals("surnamesort")){
			PersonDBProfiling.sortBySurname();
		}
		
		//data must be set for every action
		list.setListData( PersonDBProfiling.getPersonArray() );
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		//save database before exit
		PersonDBProfiling.writeDB(new File("person.db"));
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
