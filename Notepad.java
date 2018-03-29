package Pack;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
public class Notepad implements Printable,ActionListener{

	Menu file,edit,view,help,format;
	MenuItem ne,open,save,saveas,pagesetup,exit,print,undo,cut,copy,paste,delete,wordwrap,font,statusbar,viewhelp,aboutnotepad;
	TextArea ta;
	JFrame f;
	Notepad()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		 f=new JFrame("Notepad");
        Panel p = new Panel();	
        p.setLayout(new BorderLayout());
    	 ta=new TextArea(50,190);
     	p.add(ta);
     	f.add(p);
		MenuBar mb=new MenuBar();
		f.setMenuBar(mb);
		file= new Menu("File");
		edit= new Menu("Edit");
		format= new Menu("format");
		view=new Menu("view");
		help= new Menu("Help");
		
		ne=new MenuItem("New");
		ne.addActionListener(this); 
		ne.setShortcut(new MenuShortcut(KeyEvent.VK_N,false));
		file.add(ne);
		
		open=new MenuItem("open");
		open.addActionListener(this); 
		open.setShortcut(new MenuShortcut(KeyEvent.VK_O,false));
		file.add(open);
		
		save=new MenuItem("Save");
		save.setShortcut(new MenuShortcut(KeyEvent.VK_S,false));

		
		saveas=new MenuItem("Save as");
		pagesetup =new MenuItem("Page setup");
		exit=new MenuItem("Exit");
		exit.addActionListener(this);
		exit.setShortcut(new MenuShortcut(KeyEvent.VK_F4,false));
	
		
		print=new MenuItem("Print");
		print.addActionListener(this);
		print.setShortcut(new MenuShortcut(KeyEvent.VK_P,false));
		
		undo=new MenuItem("Undo");
		
		cut=new MenuItem("Cut");
		cut.addActionListener(this);
		cut.setShortcut(new MenuShortcut(KeyEvent.VK_X,false));
		
		copy=new MenuItem("Copy");
		copy.addActionListener(this);
		copy.setShortcut(new MenuShortcut(KeyEvent.VK_C,false));
		
		paste=new MenuItem("Paste");
		paste.addActionListener(this);
		paste.setShortcut(new MenuShortcut(KeyEvent.VK_V,false));
		
		delete=new MenuItem("Delete");
		
		wordwrap=new MenuItem("Word Wrap");
		font=new MenuItem("Font");
		statusbar=new MenuItem("Status Bar");
		viewhelp=new MenuItem("View Help");
		aboutnotepad=new MenuItem("About Notepad");
		file.add(ne);file.add(open);file.add(save);file.addSeparator();file.add(saveas);file.add(pagesetup);file.add(exit);file.add(print);
		edit.add(undo);edit.add(cut);edit.add(copy);edit.add(paste);edit.add(delete);
		format.add(wordwrap);format.add(font);
		view.add(statusbar);
		help.add(viewhelp);help.add(aboutnotepad);
     	mb.add(file);mb.add(edit);mb.add(format);mb.add(view);mb.add(help);
		f.setSize(300, 700);
		f.setLocation(400,300);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}

	public static void main(String[] args) {
		new Notepad();
		
	}

	public void actionPerformed (ActionEvent ae) {
		if(ae.getSource()==open){    
		    JFileChooser fc=new JFileChooser();    
		    int i=fc.showOpenDialog(fc);    
		    if(i==JFileChooser.APPROVE_OPTION){    
		        File f=fc.getSelectedFile();    
		        String filepath=f.getPath();    
		        try{  
		        BufferedReader br=new BufferedReader(new FileReader(filepath));    
		        String s1="",s2="";                         
		        while((s1=br.readLine())!=null){    
		        s2+=s1+"\n";    
		        }    
		        ta.setText(s2);    
		        br.close();    
		        }catch (Exception ex) {System.out.println("open error");  }                 
		    }    
		} 
		else if(ae.getSource()==exit)
		{
			f.dispose();
		}
		else if(ae.getSource()==save){    
		    JFileChooser fc=new JFileChooser();    
		    int i=fc.showSaveDialog(fc);    
		    if(i==JFileChooser.APPROVE_OPTION){    
		        File f=fc.getSelectedFile();    
		        String filepath=f.getPath();    
		        try{  
		        BufferedWriter out=new BufferedWriter(new FileWriter(filepath));    
		        out.write(ta.getText());  
		        out.close();   
		        }catch (Exception ex) {System.out.println("save error"); 
		        }                    
		        }
		}
		    else if(ae.getSource()==print)
		    {
		    	 PrinterJob job = PrinterJob.getPrinterJob();
		    	    job.setPrintable(this);
		    	    boolean ok = job.printDialog();
		    	    if (ok) {
		    	      try {
		    	        job.print();
		    	      } catch (PrinterException ex) {
		    	        /* The job did not successfully complete */
		    	      }
		    	    }
		    }
		    else if(ae.getSource()==cut)
		    {
		    	   String s = ta.getSelectedText();
		             //StringSelection ss = new StringSelection(sel);
		           //  clip.setContents(ss,ss);
		             ta.replaceRange(" ",ta.getSelectionStart(),ta.getSelectionEnd());
		         }
		    else if(ae.getSource()==copy)
		    {
		    	String s=ta.getSelectedText();
		    
		    	// clip.setContents(clipString,clipString);
		    	
		    }
		
		
	}
	 public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

		    if (page > 0) { /* We have only one page, and 'page' is zero-based */
		      return NO_SUCH_PAGE;
		    }

		    /*
		     * User (0,0) is typically outside the imageable area, so we must translate
		     * by the X and Y values in the PageFormat to avoid clipping
		     */
		    Graphics2D g2d = (Graphics2D) g;
		    g2d.translate(pf.getImageableX(), pf.getImageableY());

		    /* Now we perform our rendering */
		    g.drawString("Hello world!", 100, 100);

		    /* tell the caller that this page is part of the printed document */
		    return PAGE_EXISTS;
		  }
	
	
}
