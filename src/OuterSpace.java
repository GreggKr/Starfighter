//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7317053773365958771L;
	private Ship ship;
	//private Ammo ammo;
	private Sounds ear;
	private Alien alienOne;
	private Alien alienTwo;
	private Alien alienThree;
	private Alien alienFour;
	private Alien alienFive;
	private Alien alienSix;
	private Alien alienSeven;
	private Alien alienEight;
	private Alien alienNine;
	private Alien alienTen;
	private Alien alienEleven;
	private Alien alienTwelve;
	private Alien alienThirteen;
	private Alien alienFourteen;
	private Alien alienFifteen;
	private Alien alienSixteen;
	private Alien alienSeventeen;
	private Alien alienEightteen;
	private Alien alienNineteen;
	private Alien alienTwenty;

	private Bullets shots;
	private AlienHorde horde;
	
	

	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];

		//instantiate other instance variables
		//Ship, Alien
		ear =  new Sounds();
		ship = new Ship(400,400);
		shots = new Bullets();
		alienOne = new Alien();
		alienTwo = new Alien(100,100);
		alienThree = new Alien(50,50);
		alienFour = new Alien(25,25);
		alienFive = new Alien(200,200);
		alienSix = new Alien(225,225);
		alienSeven = new Alien(250,250);
		alienEight = new Alien(300,300);
		alienNine = new Alien(75,75);
		alienTen = new Alien(130,130);
		alienEleven = new Alien(120,138);
		alienTwelve = new Alien(69,69);
		alienThirteen = new Alien(58,92);
		alienFourteen = new Alien(20,54);
		alienFifteen = new Alien(160,78);
		alienSixteen = new Alien(148,118);
		alienSeventeen = new Alien(118,148);
		alienEightteen = new Alien(254,148);
		alienNineteen = new Alien(148,254);
		alienTwenty = new Alien(158,192);

		
		horde = new AlienHorde(20);
		
		horde.add(alienOne);
		horde.add(alienTwo);
		horde.add(alienThree);
		horde.add(alienFour);
		horde.add(alienFive);
		horde.add(alienSix);
		horde.add(alienSeven);
		horde.add(alienEight);
		horde.add(alienNine);
		horde.add(alienTen);
		horde.add(alienEleven);
		horde.add(alienTwelve);
		horde.add(alienThirteen);
		horde.add(alienFourteen);
		horde.add(alienFifteen);
		horde.add(alienSixteen);
		horde.add(alienSeventeen);
		horde.add(alienEightteen);
		horde.add(alienNineteen);
		horde.add(alienTwenty);
		//horde.addEmAll();
		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);

		if(keys[0] )
		{
			ship.move("LEFT");
		}
		if(keys[1])
		{
			ship.move("RIGHT");
		}
		if(keys[2])
		{
			ship.move("DOWN");
		}
		if(keys[3])
		{
			ship.move("UP");
		}
		if(keys[4]){
			shots.add(new Ammo(ship.getX(), ship.getY()));
			ear.playFire();
			keys[4] = false;
		}
		


		//add code to move Ship, Alien, etc.

		if(ship.getX()<0){
			ship.setX(0);
		}
		if(ship.getX()>800-ship.getWidth()){
			ship.setX(800-ship.getWidth());
		}
		if(ship.getY()<0){
			ship.setY(0);
		}
		if(ship.getY()>600-ship.getHeight()){
			ship.setY(600-ship.getHeight());
		}
		
		
		//add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship
		//alienOne.draw(graphToBack);
		//alienTwo.draw(graphToBack);
		 
		ship.draw(graphToBack);
		shots.drawEmAll(graphToBack);
		shots.moveEmAll();
		shots.cleanEmUp();
		horde.drawEmAll(graphToBack);
		horde.moveEmAll();
		twoDGraph.drawImage(back, null, 0, 0);
		
		//Collision Detection
//		if(shots.collision(alienOne)){
//			alienOne.setPos(-100, -100);;
//		}
//		if(shots.collision(alienTwo)){
//			alienTwo.setPos(-100, -100);;
//		}
//		if(shots.collision(alienThree)){
//			alienOne.setPos(-100, -100);;
//		}
//		if(shots.collision(alienFour)){
//			alienTwo.setPos(-100, -100);;
//		}
//		if(shots.collision(alienFive)){
//			alienOne.setPos(-100, -100);;
//		}
//		if(shots.collision(alienSix)){
//			alienTwo.setPos(-100, -100);;
//		}
//		if(shots.collision(alienSeven)){
//			alienOne.setPos(-100, -100);;
//		}
//		if(shots.collision(alienEight)){
//			alienTwo.setPos(-100, -100);;
//		}
//		if(shots.collision(alienNine)){
//			alienOne.setPos(-100, -100);;
//		}
//		if(shots.collision(alienTen)){
//			alienTwo.setPos(-100, -100);;
//		}
		//horde.removeDeadOnes(shots.collision(horde.aliens));
		if(horde.getSize()>0){
			for(Alien a: horde.aliens){
				horde.removeDeadOnes(shots.collision(a),a);
			}
		}
	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{
      //no code needed here
	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread();
		Thread.sleep(5);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}
