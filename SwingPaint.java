

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class SwingPaint {
	 JButton clearBtn, blackBtn, blueBtn, greenBtn, tasiBtn,cizBtn,dikdortgenBtn;
	  DrawArea drawArea;
	  JPanel greenp,bluep,blackp;
	  ActionListener actionListener = new ActionListener() {
	 
	  public void actionPerformed(ActionEvent e) {
	      if (e.getSource() == clearBtn) {
	        drawArea.clear();
	      } else if (e.getSource() == blackBtn) {
	        drawArea.black();
	      } else if (e.getSource() == blueBtn) {
	        drawArea.blue();
	      } else if (e.getSource() == greenBtn) {
	        drawArea.green();
	      }else if (e.getSource()==cizBtn){
	    	drawArea.draw_line();
	      }
	      else if (e.getSource()==dikdortgenBtn){
	    	  drawArea.draw_rect();
		      }
	      else if (e.getSource()==tasiBtn){
	    	  drawArea.tasi();
		      }
	  }};
	  public static void main(String[] args) {
		    new SwingPaint().show();
		  }
	  public void show() {
		    // create main frame
		    JFrame frame = new JFrame("Swing Paint");
		    Container content = frame.getContentPane();
		    // set layout on content pane
		    content.setLayout(new BorderLayout());
		    // create draw area
		    drawArea = new DrawArea();
		 
		    // add to content pane
		    content.add(drawArea, BorderLayout.CENTER);
		 
		    // create controls to apply colors and call clear feature
		    JPanel controls = new JPanel();
		    controls.setBackground(Color.GRAY);
		    clearBtn = new JButton("Clear");
		    clearBtn.addActionListener(actionListener);
		    cizBtn = new JButton("CIZ");
		    cizBtn.addActionListener(actionListener);
		    dikdortgenBtn = new JButton("Dikdortgen");
		    dikdortgenBtn.addActionListener(actionListener);
		    tasiBtn = new JButton("Tasi");
		    tasiBtn.addActionListener(actionListener);
		    greenp=new JPanel();
		    greenp.setSize(100, 150);
		    greenp.setBackground(Color.GREEN);
		    bluep=new JPanel();
		    bluep.setBackground(Color.BLUE);
		    blackp=new JPanel();
		    blackp.setBackground(Color.BLACK);
		    blackp.addMouseListener(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent e) {
					if(blackp.contains(e.getX(),e.getY()))
						drawArea.black();} 
					
		     });
		    bluep.addMouseListener(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent e) {
					if(bluep.contains(e.getX(),e.getY()))
						drawArea.blue();} 
					
		     });
		    greenp.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					if(greenp.contains(e.getX(),e.getY()))
						drawArea.green();} 
					
		     });
		    
		    // add to panel
		    controls.add(cizBtn);
		    controls.add(dikdortgenBtn);
		    controls.add(tasiBtn);
		    controls.add(clearBtn);
		    controls.add(blackp);
		    controls.add(greenp);
		    controls.add(bluep);
		    
		 
		    // add to content pane
		    content.add(controls, BorderLayout.NORTH);
		 
		    frame.setSize(500, 500);
		    // can close frame
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    // show the swing paint result
		    frame.setVisible(true);
		 
		    // Now you can try our Swing Paint !!! Enjoy :D
		  }
		 
		
	  
}
