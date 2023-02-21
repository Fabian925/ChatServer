package net.tfobz.synchronization.chat.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BilderAuswahlGUI extends JDialog {
	private ImageIcon imageIcons[] = new ImageIcon[8];
	private JButton[] buttons = new JButton[8];
	private int ausgesuchtesProfilbild = -1;

	public BilderAuswahlGUI() {

		setTitle("ChatClientGUI: Profilbild Auswahl");
		setBounds(120, 120, 570, 255);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);

		imageIcons[0] = new ImageIcon("src\\profilbild0.png");
		imageIcons[1] = new ImageIcon("src\\profilbild1.png");
		imageIcons[2] = new ImageIcon("src\\profilbild2.png");
		imageIcons[3] = new ImageIcon("src\\profilbild3.png");
		imageIcons[4] = new ImageIcon("src\\profilbild4.png");
		imageIcons[5] = new ImageIcon("src\\profilbild5.png");
		imageIcons[6] = new ImageIcon("src\\profilbild6.png");
		imageIcons[7] = new ImageIcon("src\\profilbild7.png");

		// Tux Profilbild
		buttons[0] = new JButton();
		buttons[0].setBounds(0, 0, 140, 105);
		buttons[0].setIcon(imageIcons[0]);
		buttons[0].setForeground(Color.WHITE);
		buttons[0].setBackground(Color.DARK_GRAY);
		buttons[0].setFont(new Font("Tahoma", Font.BOLD, 18));
		buttons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BilderAuswahlGUI.this.ausgesuchtesProfilbild = 0;
				BilderAuswahlGUI.this.dispose();
			}
		});
		getContentPane().add(buttons[0]);

		// Java-Maskotchen Profilbild
		buttons[1] = new JButton();
		buttons[1].setBounds(140, 0, 140, 105);
		buttons[1].setIcon(imageIcons[1]);
		buttons[1].setForeground(Color.WHITE);
		buttons[1].setBackground(Color.DARK_GRAY);
		buttons[1].setFont(new Font("Tahoma", Font.BOLD, 18));
		buttons[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BilderAuswahlGUI.this.ausgesuchtesProfilbild = 1;
				BilderAuswahlGUI.this.dispose();
			}
		});
		getContentPane().add(buttons[1]);

		// RedHat Profilbild
		buttons[2] = new JButton();
		buttons[2].setBounds(280, 0, 140, 105);
		buttons[2].setIcon(imageIcons[2]);
		buttons[2].setForeground(Color.WHITE);
		buttons[2].setBackground(Color.DARK_GRAY);
		buttons[2].setFont(new Font("Tahoma", Font.BOLD, 18));
		buttons[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BilderAuswahlGUI.this.ausgesuchtesProfilbild = 2;
				BilderAuswahlGUI.this.dispose();
			}
		});
		getContentPane().add(buttons[2]);

		// TODO andere Profilbilder
		buttons[3] = new JButton();
		buttons[3].setBounds(420, 0, 140, 105);
		buttons[3].setIcon(imageIcons[3]);
		buttons[3].setForeground(Color.WHITE);
		buttons[3].setBackground(Color.DARK_GRAY);
		buttons[3].setFont(new Font("Tahoma", Font.BOLD, 18));
		buttons[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BilderAuswahlGUI.this.ausgesuchtesProfilbild = 3;
				BilderAuswahlGUI.this.dispose();
			}
		});
		getContentPane().add(buttons[3]);

		buttons[4] = new JButton();
		buttons[4].setBounds(0, 105, 140, 105);
		buttons[4].setIcon(imageIcons[4]);
		buttons[4].setForeground(Color.WHITE);
		buttons[4].setBackground(Color.DARK_GRAY);
		buttons[4].setFont(new Font("Tahoma", Font.BOLD, 18));
		buttons[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BilderAuswahlGUI.this.ausgesuchtesProfilbild = 4;
				BilderAuswahlGUI.this.dispose();
			}
		});
		getContentPane().add(buttons[4]);

		buttons[5] = new JButton();
		buttons[5].setBounds(140, 105, 140, 105);
		buttons[5].setIcon(imageIcons[5]);
		buttons[5].setForeground(Color.WHITE);
		buttons[5].setBackground(Color.DARK_GRAY);
		buttons[5].setFont(new Font("Tahoma", Font.BOLD, 18));
		buttons[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BilderAuswahlGUI.this.ausgesuchtesProfilbild = 5;
				BilderAuswahlGUI.this.dispose();
			}
		});
		getContentPane().add(buttons[5]);

		buttons[6] = new JButton();
		buttons[6].setBounds(280, 105, 140, 105);
		buttons[6].setIcon(imageIcons[6]);
		buttons[6].setForeground(Color.WHITE);
		buttons[6].setBackground(Color.DARK_GRAY);
		buttons[6].setFont(new Font("Tahoma", Font.BOLD, 18));
		buttons[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BilderAuswahlGUI.this.ausgesuchtesProfilbild = 6;
				BilderAuswahlGUI.this.dispose();
			}
		});
		getContentPane().add(buttons[6]);

		buttons[7] = new JButton();
		buttons[7].setBounds(420, 105, 140, 105);
		buttons[7].setIcon(imageIcons[7]);
		buttons[7].setForeground(Color.WHITE);
		buttons[7].setBackground(Color.DARK_GRAY);
		buttons[7].setFont(new Font("Tahoma", Font.BOLD, 18));
		buttons[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BilderAuswahlGUI.this.ausgesuchtesProfilbild = 7;
				BilderAuswahlGUI.this.dispose();
			}
		});
		getContentPane().add(buttons[7]);

	}

	/**
	 * gibt varibalble ausgesuchtesProfilbild zurück.
	 * 
	 * @return -1 wenn kein Profilbild ausgesucht wurde. Ansonsten 0 bis 7.
	 */
	public int getAusgesuchtesProfilbild() {
		return this.ausgesuchtesProfilbild;
	}
}
