import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

class MyButton extends JButton{
	public int flag=0;
	public boolean state=true;
	public MyButton(){
		
	}
	public MyButton(String s){
		super(s);
	}
	public int getFlag(){
		return this.flag;
	}
	public void setFlag(int i){
		this.flag=i;
	}
}
class TicTac implements ActionListener{
	static int PFlag=0,player=1;
	static JFrame frame;
	static MyButton jb[];
	static Random randomGenerator = new Random();
	MyButton option;
	Icon  img1=new ImageIcon("o.png");
	Icon  img2=new ImageIcon("x.png");
	public TicTac(){
		PFlag=0;player=1;
		int w=0,h=0,k=0;
		frame = new JFrame("Tic-Tac-Toe");
		jb = new MyButton[9];
		for(int j=0;j<9;j++){
			jb[k] = new MyButton();
			frame.add(jb[k]);
			jb[k].setBounds(45+w,30+h,100,100);
			jb[k].addActionListener(this);
			w+=100;
			k++;
			if(k==3||k==6){
				w=0;
				h+=100;
			}
		}
		option = new MyButton("Single/Multi-player");
		option.addActionListener(this);
		option.setBounds(100,350,200,50);
	
		frame.add(option);	
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,450);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	public static int checkTicTac(){
		int flag;
		flag=1;
	 for(int i=0;i<2;i++){
		if(jb[0].getFlag()==flag&&jb[1].getFlag()==flag&&jb[2].getFlag()==flag){
			return flag;
		}
		else if(jb[3].getFlag()==flag&&jb[4].getFlag()==flag&&jb[5].getFlag()==flag){
			return flag;
		}
		else if(jb[6].getFlag()==flag&&jb[7].getFlag()==flag&&jb[8].getFlag()==flag){
			return flag;
		}
		else if(jb[0].getFlag()==flag&&jb[3].getFlag()==flag&&jb[6].getFlag()==flag){
			return flag;
		}
		else if(jb[1].getFlag()==flag&&jb[4].getFlag()==flag&&jb[7].getFlag()==flag){
			return flag;
		}
		else if(jb[2].getFlag()==flag&&jb[5].getFlag()==flag&&jb[8].getFlag()==flag){
			return flag;
		}
		else if(jb[0].getFlag()==flag&&jb[4].getFlag()==flag&&jb[8].getFlag()==flag){
			return flag;
		}
		else if(jb[2].getFlag()==flag&&jb[4].getFlag()==flag&&jb[6].getFlag()==flag){
			return flag;
		}
		flag=2;
	 }	
	 return 0;
	}
	
	public static void checkAction(){
			if(checkTicTac()==1||checkTicTac()==2){
						if(checkTicTac()==1)
							JOptionPane.showMessageDialog(frame,"Congratulation !\n O has won");
						else if(PFlag==2)
							JOptionPane.showMessageDialog(frame,"Congratulation !\n X has won");
						else
							JOptionPane.showMessageDialog(frame,"Sorry !\n Computer('X') has won");
						if(JOptionPane.showConfirmDialog(frame,"Do you want to restart the game ?","Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION){
							System.exit(0);
						}
						else{
							frame.dispose();
							main();
						}
			}
			boolean draw=true;
			for(int i=0;i<jb.length;i++){
				if(jb[i].getFlag()==0)
					draw=false;
			}
			if(draw==true){
					JOptionPane.showMessageDialog(frame,"Match has been draw");
						if(JOptionPane.showConfirmDialog(frame,"Do you want to restart the game ?","Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION){
							System.exit(0);
						}
						else{
							frame.dispose();
							main();
						}
			}
	}
	
	public void actionPerformed(ActionEvent e){
		boolean lbreak;
		lbreak=true;
		if(e.getSource()==option){
			if(option.state==true&&option.getFlag()==0){
				PFlag=1;
				option.setText(" Single Player ");
				option.setBackground(Color.YELLOW);
				option.setFlag(1);
			}
			else if(option.state==true&&option.getFlag()==1){
				PFlag=2;
				option.setText(" Two Players ");
				option.setBackground(Color.PINK);
				option.setFlag(0);
			}
		}
		else{
		  for(int i=0;i<jb.length;i++){
			if(e.getSource()==jb[i]){
				if(PFlag==0){
					JOptionPane.showMessageDialog(frame,"Select Player Option");
				}
				else if(jb[i].getFlag()==0){
					
					//off the player option 
					option.state=false;
					if(player==1){
						jb[i].setIcon(img1);
						jb[i].setFlag(1);				
							player=2;
							
					}
					else if(player==2&&PFlag==2){
						jb[i].setIcon(img2);
						jb[i].setFlag(2);
						player=1;
					}
					checkAction();
					
					//Single Player
					if(PFlag==1&&player==2){
						player=1;
						for(int j=0;j<jb.length&&lbreak==true;j++){
							if(jb[j].getFlag()==0){
								jb[j].setFlag(2);
								if(checkTicTac()==2){
									jb[j].setIcon(img2);
									lbreak=false;
								}
								else{
									jb[j].setFlag(0);
								}
							}	
						}
						for(int j=0;j<jb.length&&lbreak==true;j++){
							if(jb[j].getFlag()==0){
								jb[j].setFlag(1);
								if(checkTicTac()==1){
									jb[j].setFlag(2);
									jb[j].setIcon(img2);
									lbreak=false;
								}
								else{
									jb[j].setFlag(0);
								}
							}	
						}
						if(jb[4].getFlag()==0){
									jb[4].setFlag(2);
									jb[4].setIcon(img2);
									lbreak=false;
							}
						while(lbreak==true){
							int randomInt = randomGenerator.nextInt(9);
							if(jb[randomInt].getFlag()==0){
									jb[randomInt].setFlag(2);
									jb[randomInt].setIcon(img2);
									lbreak=false;
							}	
						}
					}
					checkAction();
				}
			}
		 }
		} 
	}
	public static void main(String... arg){
		new TicTac();
	}
}