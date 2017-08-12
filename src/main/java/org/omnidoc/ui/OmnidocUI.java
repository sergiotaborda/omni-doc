package org.omnidoc.ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class OmnidocUI extends JFrame implements  WindowListener {



	public OmnidocUI(){
		this.setTitle("OmniDoc Transformer");

		init();
		this.addWindowListener(this);
		this.pack();
	}

	public void init(){
		JTabbedPane pane = new JTabbedPane(); 
		this.getContentPane().add(pane);
		
		pane.add("Xdoc-to-HTML", new ArticleTransformPanel());
	
	}


	public void windowClosed(WindowEvent arg0) {

	}

	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
