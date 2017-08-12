package org.omnidoc.ui;

import javax.swing.UIManager;

public class Omnidoc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {

		}
		OmnidocUI ui = new OmnidocUI();
		ui.setVisible(true);
	}

}
