package game2048;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.System.exit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
public class Game2048 implements ActionListener {
    int score=0;
    JTextField tf[][]=new JTextField[4][4];
    JTextField scr=new JTextField();
    JFrame frame;
    JPanel panel,pane,panes;
    JButton jup,jdown,jright,jleft;
    Font font = new Font(Font.SERIF, Font.BOLD, 60);
    JOptionPane jdag;
    public void gui()
    {
        jup=new JButton("UP");
        jdown=new JButton("DOWN");
        jleft=new JButton("LEFT");
        jright=new JButton("RIGHT");
        panes=new JPanel();
        panel=new JPanel();
        pane=new JPanel();
        frame=new JFrame("2048");
        frame.add(pane,BorderLayout.NORTH);
        frame.add(panel);
        frame.add(panes,BorderLayout.EAST);
        panel.setLayout(new GridLayout(4,4));
        panes.setLayout(new BorderLayout());
        panes.add(jright, BorderLayout.EAST);
        panes.add(jup, BorderLayout.NORTH);
        panes.add(jdown, BorderLayout.SOUTH);
        panes.add(jleft, BorderLayout.WEST);
        jright.addActionListener(this);
        jup.addActionListener(this);
        jdown.addActionListener(this);
        jleft.addActionListener(this);
        for(int k=0;k<4;k++)
        {
            for(int l=0;l<4;l++)
            {
                JTextField textfield = new JTextField();
                tf[k][l]=new JTextField();
                tf[k][l].setEditable(false);
                tf[k][l].setFont(font);
                tf[k][l].setHorizontalAlignment(JTextField.CENTER);
                panel.add(tf[k][l]);
            }
        }
        pane.add(scr,BorderLayout.NORTH);
        scr.setColumns(5);
        scr.setBackground(Color.yellow);
        scr.setEditable(false);
        frame.setVisible(true);
        frame.setSize(600,600);
    }
    int dead=0;
    int game[][]=new int[4][4];
    int gameover()
    {
       int i,j,flag=1;
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                if(game[i][j]==2048)
                {
                    flag=2;
                }
                if(game[i][j]==0)
                {
                    flag=0;
                    break;
                }
            }
        }
        if(flag==2)
        {
            jdag.showMessageDialog(frame,"YOU WIN");
            exit(0);
        }
        return flag;
    }
    public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("UP"))
		{
                    game=upmove(game);
                    initvalue();
                    displaygame();
		}
		if(ae.getActionCommand().equals("DOWN"))
		{
                    downmove();
                    initvalue();
                    displaygame();
		}
		if(ae.getActionCommand().equals("LEFT"))
		{
                    leftmove();
                    initvalue();
                    displaygame();
		}
		if(ae.getActionCommand().equals("RIGHT"))
		{
                    rightmove();
                    initvalue();
                    displaygame();
		}
	}
    int[][] upmove(int gamers[][])
    {
        int i;
        for(i=0;i<4;i++)
        {
            if(gamers[3][i]==gamers[2][i])
            {
                if(gamers[2][i]==gamers[1][i])
                {
                    if(gamers[1][i]==gamers[0][i])
                    {
                        gamers[0][i]=2*gamers[0][i];
                        gamers[1][i]=2*gamers[1][i];
                        gamers[2][i]=gamers[3][i]=0;
                        score+=gamers[0][i]+gamers[1][i];
                    }
                    else
                    {
                        gamers[1][i]=2*gamers[1][i];
                        gamers[3][i]=0;
                        score+=gamers[1][i];
                    }
                }
                else if(gamers[1][i]==gamers[0][i])
                {
                    if(gamers[0][i]==0)
                    {
                        gamers[0][i]=2*gamers[2][i];
                        gamers[1][i]=gamers[2][i]=gamers[3][i]=0;
                        score+=gamers[0][i];
                    }
                    else if(gamers[0][i]!=0)
                    {
                        gamers[0][i]=2*gamers[0][i];
                        gamers[1][i]=2*gamers[2][i];
                        gamers[2][i]=0;
                        gamers[3][i]=0;
                        score+=gamers[0][i]+gamers[1][i];
                    }
                }
                else if(gamers[0][i]==0)
                {
                    gamers[0][i]=gamers[1][i];
                    gamers[1][i]=2*gamers[2][i];
                    gamers[2][i]=gamers[3][i]=0;
                    score+=gamers[1][i];
                }
                
                else
                {
                    gamers[2][i]=2*gamers[2][i];
                    gamers[3][i]=0;
                    score+=gamers[2][i];
                }
            }
            else if((gamers[2][i]==gamers[1][i])&&gamers[1][i]!=0)
            {
                if(gamers[1][i]==gamers[0][i])
                {
                    gamers[0][i]=2*gamers[0][i];
                    gamers[2][i]=gamers[3][i];
                    gamers[3][i]=0;
                    score+=gamers[0][i];
                }
                else if(gamers[0][i]==0)
                {
                    gamers[0][i]=2*gamers[1][i];
                    gamers[1][i]=gamers[3][i];
                    gamers[3][i]=0;
                    score+=gamers[0][i];
                }
                else 
                {
                    gamers[1][i]=2*gamers[1][i];
                    gamers[2][i]=gamers[3][i];
                    gamers[3][i]=0;
                    score+=gamers[1][i];
                }
            }
            else if((gamers[2][i]==gamers[1][i])&&gamers[1][i]==0)
            {
                if((gamers[1][i]==gamers[0][i])&&gamers[0][i]==0)
                {
                    gamers[0][i]=gamers[3][i];
                    gamers[3][i]=0;
                }
                else if(gamers[0][i]==gamers[3][i])
                {
                    gamers[0][i]=2*gamers[0][i];
                    gamers[1][i]=gamers[2][i]=gamers[3][i]=0;
                    score+=gamers[0][i];
                }
                else
                {
                    gamers[1][i]=gamers[3][i];
                    gamers[3][i]=0;
                }
            }
            else if((gamers[1][i]==gamers[0][i])&&gamers[1][i]!=0)
            {
                if(gamers[2][i]==0)
                {
                    gamers[0][i]=2*gamers[0][i];
                    gamers[1][i]=gamers[3][i];
                    gamers[3][i]=0;
                    score+=gamers[0][i];
                }
                else
                {
                    gamers[0][i]=2*gamers[0][i];
                    gamers[1][i]=2*gamers[2][i];
                    gamers[2][i]=0;
                    gamers[3][i]=0;
                    score+=gamers[0][i]+gamers[1][i];
                }
                
            }
            else if((gamers[1][i]==gamers[0][i])&&gamers[1][i]==0)
            {
                gamers[0][i]=gamers[2][i];
                gamers[1][i]=gamers[3][i];
                gamers[2][i]=0;
                gamers[3][i]=0;
            }
            else if(gamers[2][i]==0)
            {
                if(gamers[1][i]==gamers[3][i])
                {
                    if(gamers[0][i]==0)
                    {
                        gamers[0][i]=2*gamers[1][i];
                        gamers[1][i]=gamers[2][i]=gamers[3][i]=0;
                        score+=gamers[0][i];
                    }
                    else
                    {
                        gamers[1][i]=2*gamers[1][i];
                        gamers[2][i]=gamers[3][i]=0;
                        score+=gamers[1][i];
                    }
                    
                }
                else
                {
                    if(gamers[0][i]==0)
                    {
                        gamers[0][i]=gamers[1][i];
                        gamers[1][i]=gamers[3][i];
                        gamers[2][i]=gamers[3][i]=0;
                    }
                    else
                    {
                        gamers[2][i]=gamers[3][i];
                        gamers[3][i]=0;
                    }
                }
                
            }
            else if(gamers[1][i]==0)
            {
                if(gamers[0][i]==gamers[2][i])
                {
                    gamers[0][i]=2*gamers[0][i];
                    gamers[1][i]=gamers[3][i];
                    gamers[2][i]=gamers[3][i]=0;
                    score+=gamers[0][i];
                }
                else
                {
                    gamers[1][i]=gamers[2][i];
                    gamers[2][i]=gamers[3][i];
                    gamers[3][i]=0; 
                }
                
            }
            else if(gamers[0][i]==0)
            {
                gamers[0][i]=gamers[1][i];
                gamers[1][i]=gamers[2][i];
                gamers[2][i]=gamers[3][i];
                gamers[3][i]=0;
            }
        }
        dead=gameover();
        return gamers;
    }
    void leftmove()
    {
        int i,j;
        int gamel[][]=new int[4][4];
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                gamel[j][i]=game[3-i][j];
                
            }
        }
        gamel=upmove(gamel);
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                game[3-i][j]=gamel[j][i];
            }
        }
        dead=gameover();
    }
    void downmove()
    {
        int i,j;
        int gamel[][]=new int[4][4];
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                gamel[j][i]=game[3-j][i];
                
            }
        }
        gamel=upmove(gamel);
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                game[3-j][i]=gamel[j][i];
            }
        }
        dead=gameover();
    }
    void rightmove()
    {
        int i,j;
        int gamel[][]=new int[4][4];
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                gamel[j][i]=game[i][3-j];
                
            }
        }
        gamel=upmove(gamel);
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                game[i][3-j]=gamel[j][i];
            }
        }
        dead=gameover();
    }
    void startvalue()
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                game[i][j]=0;
            }
        } 
        game[rmatrix()][rmatrix()]=rvalue();
    }
    void initvalue()
    {
        if(dead==0)
        {
            int kl=rmatrix();
            int kj=rmatrix();
            if(game[kl][kj]==0)
            {
                game[kl][kj]=rvalue();
            }
            else
            { 
                initvalue();
            } 
        }
        else
        {
            jdag.showMessageDialog(frame,"YOU LOSE");
            exit(0);
        }
    }
    int rmatrix()
    {
        Random rand=new Random();
        int ranpos;
        ranpos=rand.nextInt(4);
        return ranpos;
    }     
    int rvalue()
    {
        Random rand=new Random();
        int randno;
        randno=(rand.nextInt()%2==0) ? 2 : 4;
        return randno;
    }
    void displaygame()
    {
        int i,j;
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                tf[i][j].setText(""+game[i][j]);
            }
        }
        scr.setText(""+score);
    }
    public Game2048( )
    {    
        gui();
        startvalue();
        initvalue();
        displaygame();
    } 
    public static void main(String[] args) {
            Game2048 gms=new Game2048();
        }
}