
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.swing.JComponent;
public class DrawArea extends JComponent {
	private Image image;
	private Graphics2D g2;
	BufferedImage paintImage;
	private int currentX, currentY, oldX, oldY,preX,preY;
	public boolean enable_line=true;;
	public boolean enable_rectt=false;
	public boolean enable_tasi=false;
	ArrayList<Rectangle> rects=new ArrayList<>();
	boolean isFirst=true;
	boolean dragging=false;
	Rectangle rect;
	boolean pressOut = false;
	Rectangle rect_selected;
	boolean prev_image=false;
	
	public DrawArea() {
	    setDoubleBuffered(false);
	    draw_line();
	}
	    
	public void draw_line(){
		enable_rectt=false;
		enable_line=true;
		enable_tasi=false;
			addMouseListener(new MouseAdapter() 
		    {
		      public void mousePressed(MouseEvent e) {
		    	if(!enable_line)
		    		return;
		    	
		        oldX = e.getX();
		        oldY = e.getY();
		        
		      }
		     });
		    addMouseMotionListener(new MouseMotionAdapter() 
		    {
		       public void mouseDragged(MouseEvent e) { 
		    	   if(!enable_line)
			    		return; 
		          currentX = e.getX();
		          currentY = e.getY();
		   
		          if (g2 != null) {
		            g2.drawLine(oldX, oldY, currentX, currentY);
		            repaint();
		            oldX = currentX;
		            oldY = currentY;
		            
		          }
		        }
		      });
			System.out.println("çiz modunda");
			
	}
	
	public void draw_rect(){
			enable_rectt=true;
			enable_line=false;
			enable_tasi=false;
			addMouseListener(new MouseAdapter() 
		    {
				
				public void mousePressed(MouseEvent e) {
					if(!enable_rectt)
						return;
					if(isFirst){
						oldX = e.getX();
				        oldY = e.getY();
				        rect=new Rectangle(0,0,0,0);
				        rect.x=oldX;
				        rect.y=oldY;
				       
					}
				}
				public void mouseReleased(MouseEvent e){
					if(!enable_rectt)
						return;
			    	  currentX=0; currentY=0; oldX=0; oldY=0;
			    	  rect.height=Math.abs(oldX-e.getX());
			    	  rect.width= Math.abs(oldY-e.getY());
			    	  rects.add(rect);
			    	  isFirst=true;
			    	  System.out.println(rects);
			       }
			       
		     });
		    addMouseMotionListener(new MouseMotionAdapter() 
		    {
		       public void mouseDragged(MouseEvent e) {
		    	   if(!enable_rectt)
						return; 
		    	 
		    	  isFirst=false;
		    	  dragging=true;
		    	  
		          currentX = e.getX();
		          currentY = e.getY();
		          
		          
		        	rect.width=Math.abs(currentX-oldX);
		        	rect.height= Math.abs(currentY-oldY);
		        	
		            g2.fill(rect);
		            repaint();
		        }
		      });
		    
			System.out.println("dikdortgen cizme modunda");
		
		
	}
	public void tasi(){
		enable_rectt=false;;
		enable_line=false;
		enable_tasi=true;
		
		addMouseListener(new MouseAdapter() 
	    {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<rects.size();i--)
					if(rects.get(i).contains(e.getX(),e.getY()))
						rect_selected=rects.get(i);
				prev_image=true;
				System.out.println("pressing");
				System.out.println(e.getX());
				System.out.println(e.getY());
				if(!enable_tasi)
					return;
				System.out.println(rects.get(0).width);
				preX = rect_selected.x - e.getX();
				preY = rect_selected.y - e.getY();

				if (rect_selected.contains(e.getX(), e.getY())) {
					//updateLocation(e);
				} else {
					pressOut = true;
				}
				
			}
			public void mouseReleased(MouseEvent e){
				System.out.println("tasi released");
				/*if (rect.contains(e.getX(), e.getY())) {
					updateLocation(e);
				} else {
					pressOut = false;
				}*/
			}
			
	     });
	    addMouseMotionListener(new MouseMotionAdapter() 
	    {
	       public void mouseDragged(MouseEvent e) {
	    	   if(!enable_tasi)
					return;
	    	   if (!pressOut) {
					updateLocation(e);
				} else {
				}
	       }
	       public void updateLocation(MouseEvent e) {
				System.out.println("updatae location");
				paintImage = new BufferedImage(getSize().width, 
	                    getSize().height, BufferedImage.TYPE_INT_ARGB);
		        final Graphics2D g = paintImage.createGraphics();
		        g.setColor(new Color(0, 0, 0, 0));
		        g.setComposite(AlphaComposite.Clear); // overpaint
		        g.fillRect(rect_selected.x,rect_selected.y,rect_selected.width,rect_selected.height);
		        g.dispose();
		        repaint();
				rect_selected.setLocation(preX + e.getX(), preY + e.getY());
				System.out.println(rect_selected.width);
				g2.fill(rect_selected);
				repaint(rect_selected);
			}
	    	  
	       });
	    
	    
		System.out.println("tasima modunda");
		
		
	}
	
	
	 protected void paintComponent(Graphics g) 
	 {
		    if (image == null) {
		      // image to draw null ==> we create
		      image = createImage(getSize().width, getSize().height);
		      g2 = (Graphics2D) image.getGraphics();
		      
		      // enable antialiasing
		      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		      // clear draw area
		      clear();
		    }
		    if(enable_tasi){
		    
		    g.drawImage(image, 0, 0, null);
		    g.drawImage(paintImage, 0, 0, this);
		    }
		    else
		    	g.drawImage(image, 0, 0, null);
	 }
	
	 public void clear() 
	 {
		    g2.setPaint(Color.white);
		    // draw white on entire draw area to clear
		    g2.fillRect(0, 0, getSize().width, getSize().height);
		    g2.setPaint(Color.black);
		    repaint();
	 }
		 
	 public void black() {
	    g2.setPaint(Color.black);
	  }
	 
	 
	  public void green() {
	    g2.setPaint(Color.green);
	  }
	 
	  public void blue() {
	    g2.setPaint(Color.blue);
	  }
	 
	}
 
	    

