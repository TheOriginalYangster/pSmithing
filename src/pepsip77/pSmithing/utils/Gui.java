package pepsip77.pSmithing.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import pepsip77.pSmithing.data.Bar;
import pepsip77.pSmithing.data.Data;
import pepsip77.pSmithing.data.Item_;
import pepsip77.pSmithing.data.Location;
import java.awt.Window.Type;

public class Gui {

	public JFrame frmPsmithing;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public Gui() {
		initialize();
		frmPsmithing.setVisible(true);
	}

	private void initialize() {
		frmPsmithing = new JFrame();
		frmPsmithing.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmPsmithing.setResizable(false);
		frmPsmithing.setTitle("pSmithing");
		frmPsmithing.setBounds(100, 100, 215, 254);
		frmPsmithing.getContentPane().setLayout(null);
		
		JComboBox<Bar> cbBar = new JComboBox<Bar>();
		cbBar.setBounds(10, 120, 179, 20);
		frmPsmithing.getContentPane().add(cbBar);
		cbBar.setModel(new DefaultComboBoxModel<Bar>(Bar.values()));
		
		JComboBox<Item_> cbItem = new JComboBox<Item_> ();
		cbItem.setBounds(10, 151, 179, 20);
		frmPsmithing.getContentPane().add(cbItem);
		cbItem.setModel(new DefaultComboBoxModel<Item_>(Item_.values()));
		
		JRadioButton rbSmeltOres = new JRadioButton("Smelt ores");
		buttonGroup.add(rbSmeltOres);
		rbSmeltOres.setBounds(10, 11, 109, 23);
		frmPsmithing.getContentPane().add(rbSmeltOres);
		
		JRadioButton rbMakeItems = new JRadioButton("Forge items");
		buttonGroup.add(rbMakeItems);
		rbMakeItems.setBounds(10, 37, 109, 23);
		frmPsmithing.getContentPane().add(rbMakeItems);
		
		JRadioButton rbSmeltAndMake = new JRadioButton("Smelt ores + forge items");
		buttonGroup.add(rbSmeltAndMake);
		rbSmeltAndMake.setBounds(10, 63, 183, 23);
		frmPsmithing.getContentPane().add(rbSmeltAndMake);
		
		JComboBox<Location> cbLocation = new JComboBox<Location>();
		cbLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Location loc = (Location)cbLocation.getSelectedItem();
				rbSmeltOres.setEnabled(loc.hasFurnance());
				rbMakeItems.setEnabled(loc.hasAnvil());
				rbSmeltAndMake.setEnabled(loc.hasFurnance() && loc.hasAnvil());
				rbSmeltOres.setSelected(false);
				if(rbSmeltOres.isSelected() && !loc.hasFurnance())
					rbSmeltOres.setSelected(false);
				if(rbMakeItems.isSelected() && !loc.hasAnvil())
					rbMakeItems.setSelected(false);
				if(rbSmeltAndMake.isSelected() && !(loc.hasFurnance() && loc.hasAnvil()))
					rbSmeltAndMake.setSelected(false);
			}
		});
		cbLocation.setBounds(10, 89, 179, 20);
		frmPsmithing.getContentPane().add(cbLocation);
		cbLocation.setModel(new DefaultComboBoxModel<Location>(Location.values()));
		
		JButton btStart = new JButton("START");
		btStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Data.forgeItems = rbMakeItems.isSelected();
				Data.smeltOres = rbSmeltOres.isSelected();
				Data.smeltAndForge = rbSmeltAndMake.isSelected();
				
				Data.currentLocation = (Location) cbLocation.getSelectedItem();
				Data.currentBar = (Bar) cbBar.getSelectedItem();
				Data.currentItem = (Item_) cbItem.getSelectedItem();
				
				if((rbMakeItems.isSelected() && rbMakeItems.isEnabled()) 
						|| (rbSmeltOres.isSelected() && rbSmeltOres.isEnabled()) 
						|| (rbSmeltAndMake.isSelected() && rbSmeltAndMake.isEnabled()))
					frmPsmithing.setVisible(false);
				else
					infoBox("Please select mode", "");
			}
		});
		btStart.setBounds(10, 182, 179, 23);
		frmPsmithing.getContentPane().add(btStart);
	}
	
	 public static void infoBox(String infoMessage, String titleBar)
	    {
	        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }
}
