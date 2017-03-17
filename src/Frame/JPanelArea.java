package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JPanelArea extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final String YOU_LOOSE = "You loose!";
	private final String YOU_WIN = "YOU WIN!";
	private final String CONGRATULATION = "Congratulation! You have a good day today ;)";
	private final String BOMB_WAS_DETONATED = "Boooooooom... Try again :(";
	private final int FIELD_WITH_BOMB = -1;
	private final int FIELD_CLOSED = 0;
	private final int FIELD_OPENED = 1;
	private final int FIELD_WITH_FLAG_ON_BOMB = 2;
	private final int FIELD_WITH_FLAG_ON_EMPTY_BLOCK = 3;

	private int[][] checkBlocks;

	private MouseListener mouseListener;
	private ActionListener actionListener;
	private static int BLOCKS_X;
	private static int BLOCKS_Y;
	private static int bombs;
	private static int BOMBS;

	private static JButton[][] blocks;
	private int isOpen = 0;

	private static JPanelArea pArea = new JPanelArea();

	// private JFrame frame;

	private ImageIcon iZero = new ImageIcon("img/0.png");
	private ImageIcon iOne = new ImageIcon("img/1.png");
	private ImageIcon iTwo = new ImageIcon("img/2.png");
	private ImageIcon iThree = new ImageIcon("img/3.png");
	private ImageIcon iFour = new ImageIcon("img/4.png");
	private ImageIcon iFive = new ImageIcon("img/5.png");
	private ImageIcon iSix = new ImageIcon("img/6.png");
	private ImageIcon iSeven = new ImageIcon("img/7.png");
	private ImageIcon iEight = new ImageIcon("img/8.png");
	private ImageIcon iBomb = new ImageIcon("img/bomb.png");
	private ImageIcon iBombSmile = new ImageIcon("img/bombSmile.png");
	private ImageIcon iCloseBlock = new ImageIcon("img/close.png");
	private ImageIcon iFlag = new ImageIcon("img/Bflag.png");
	private ImageIcon iDefuse = new ImageIcon("img/Defuse.png");

	private JPanelArea() {
		BLOCKS_X = 9;
		BLOCKS_Y = 9;
		bombs = 10;
		BOMBS = bombs;
	}

	private JPanelArea(int blocks_x, int blocks_y, int bomb) {
		BLOCKS_X = blocks_x;
		BLOCKS_Y = blocks_y;
		bombs = bomb;
		BOMBS = bombs;
	}

	public static JPanelArea getJPanelArea() {
		return pArea;
	}

	public static void setParametersGame(int blocks_x, int blocks_y, int bomb) {
		BLOCKS_X = blocks_x;
		BLOCKS_Y = blocks_y;
		bombs = bomb;
		BOMBS = bombs;
	}

	public int getBLOCKS_X() {
		return BLOCKS_Y;
	}

	public int getBLOCKS_Y() {
		return BLOCKS_Y;
	}

	public int getBOMBS() {
		return BOMBS;
	}

	
	public void setEmptyCheckBlocks() {
		isOpen = 0;
		for (int i = 0; i < BLOCKS_X; i++) {
			for (int j = 0; j < BLOCKS_Y; j++) {
				checkBlocks[i][j] = FIELD_CLOSED;
				blocks[i][j].setIcon(iCloseBlock);
			}
		}
	}

	public void removeButtons() {
		for (int i = 0; i < BLOCKS_X; i++)
			for (int j = 0; j < BLOCKS_Y; j++)
				remove(blocks[i][j]);
	}

	protected void createRandomBombs() {
		BOMBS = bombs;
		Random rand = new Random();
		for (int i = 0; i < BOMBS; i++) {
			int first = rand.nextInt(BLOCKS_X);
			int second = rand.nextInt(BLOCKS_Y);

			if (checkBlocks[first][second] == FIELD_WITH_BOMB) {
				i--;
				continue;
			}
			checkBlocks[first][second] = FIELD_WITH_BOMB;
		}
	}

	public void run(JFrame frame) {
		frame.setSize(BLOCKS_Y * 45, BLOCKS_X * 45 + 80);
		frame.setLocationRelativeTo(null);
		setBackground(new Color(167, 167, 167));

		Level();

		setLayout(new GridLayout(BLOCKS_X, BLOCKS_Y));
		int count = 1;
		for (int i = 0; i < BLOCKS_X; i++) {
			for (int j = 0; j < BLOCKS_Y; j++) {
				blocks[i][j] = new JButton("");
				blocks[i][j].setIcon(iCloseBlock);
				blocks[i][j].setBackground(new Color(167, 167, 167));
				blocks[i][j].setBorderPainted(true);
				blocks[i][j].setPreferredSize(new Dimension(45, 45));
				blocks[i][j].addActionListener(actionListener);
				blocks[i][j].addMouseListener(mouseListener);
				blocks[i][j].setName(count + "");
				add(blocks[i][j]);
				count++;
			}
		}
		updateUI();
	}

	private void openingBlock(int i, int j) {
		Color cGrey = new Color(192, 192, 192);
		Color cRed = new Color(255, 0, 0);
		int countBombs = 0;
		int start = -1;
		int end = 2;

		if (j == 0)
			start = 0;
		if (j == BLOCKS_Y - 1)
			end = 1;

		
		if (checkBlocks[i][j] == FIELD_CLOSED) {
			blocks[i][j].setBackground(cGrey);
			checkBlocks[i][j] = FIELD_OPENED;
			isOpen++;
			if(isOpen == 1)
				JPanelHeader.getJPanelHeader().startTimer();
			if (isOpen == (BLOCKS_X * BLOCKS_Y - BOMBS)) {
				JPanelHeader.getJPanelHeader().resetTimer();
				JOptionPane.showMessageDialog(this,CONGRATULATION + "Your time: "+ JPanelHeader.getJPanelHeader().getTime() , YOU_WIN, JOptionPane.ERROR_MESSAGE);
				for (int n = 0; n < BLOCKS_X; n++) {
					for (int m = 0; m < BLOCKS_Y; m++) {
						if ((checkBlocks[n][m] == FIELD_WITH_BOMB) || (checkBlocks[n][m] == FIELD_WITH_FLAG_ON_BOMB)) {
							blocks[n][m].setIcon(iDefuse);
							checkBlocks[n][m] = FIELD_OPENED;
							blocks[n][m].setBackground(cGrey);
						}
					}
				}
			}

			if (i != 0) {

				for (int k = start; k < end; k++)
					countBombs = ((checkBlocks[i - 1][j - (-k)] == -1) || (checkBlocks[i - 1][j - (-k)] == 2))
							? countBombs + 1 : countBombs + 0;
			}

			if (start != 0)
				countBombs = ((checkBlocks[i][j - 1] == -1) || (checkBlocks[i][j - 1] == 2)) ? countBombs + 1
						: countBombs + 0;

			if (end != 1)
				countBombs = ((checkBlocks[i][j + 1] == -1) || (checkBlocks[i][j + 1] == 2)) ? countBombs + 1
						: countBombs + 0;

			if (i != BLOCKS_X - 1)
				for (int k = start; k < end; k++)
					countBombs = ((checkBlocks[i + 1][j - (-k)] == -1) || (checkBlocks[i + 1][j - (-k)] == 2))
							? countBombs + 1 : countBombs + 0;

			switch (countBombs) {
			case 0: {
				blocks[i][j].setIcon(iZero);
				if (i != 0)
					for (int k = start; k < end; k++)
						openingBlock(i - 1, j - (-k));
				if (start != 0)
					openingBlock(i, j - 1);
				if (end != 1)
					openingBlock(i, j + 1);
				if (i != BLOCKS_X - 1)
					for (int k = start; k < end; k++)
						openingBlock(i + 1, j - (-k));
				break;
			}
			case 1:
				blocks[i][j].setIcon(iOne);
				break;
			case 2:
				blocks[i][j].setIcon(iTwo);
				break;
			case 3:
				blocks[i][j].setIcon(iThree);
				break;
			case 4:
				blocks[i][j].setIcon(iFour);
				break;
			case 5:
				blocks[i][j].setIcon(iFive);
				break;
			case 6:
				blocks[i][j].setIcon(iSix);
				break;
			case 7:
				blocks[i][j].setIcon(iSeven);
				break;
			case 8:
				blocks[i][j].setIcon(iEight);
				break;
			}
		}

		if (checkBlocks[i][j] == FIELD_WITH_BOMB) {
			JPanelHeader.getJPanelHeader().resetTimer();
			JPanelHeader.getJPanelHeader().setIconOnButton(iBombSmile);
			int saveI = i, saveJ = j;
			blocks[i][j].setBackground(cRed);
			blocks[i][j].setIcon(iBomb);
			JOptionPane.showMessageDialog(this,BOMB_WAS_DETONATED , YOU_LOOSE, JOptionPane.ERROR_MESSAGE);
			for (int n = 0; n < BLOCKS_X; n++) {
				for (int m = 0; m < BLOCKS_Y; m++) {
					if (checkBlocks[n][m] == FIELD_CLOSED)
						checkBlocks[n][m] = FIELD_OPENED;
					if (checkBlocks[n][m] == FIELD_WITH_FLAG_ON_BOMB)
						blocks[n][m].setIcon(iDefuse);
					if (checkBlocks[n][m] == FIELD_WITH_BOMB) {
						blocks[n][m].setIcon(iBomb);
						checkBlocks[n][m] = FIELD_OPENED;
						if ((n == saveI) && (m == saveJ)) {
							continue;
						}
						blocks[n][m].setBackground(cGrey);
					}
				}
			}
		}
	}

	protected void Level() {
		isOpen = 0;
		blocks = new JButton[BLOCKS_X][BLOCKS_Y];
		checkBlocks = new int[BLOCKS_X][BLOCKS_Y];

		createRandomBombs();

		//// ActionListener{
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() instanceof JButton) {

					String text = ((JButton) e.getSource()).getName();
					for (int i = 0; i < BLOCKS_X; i++) {
						for (int j = 0; j < BLOCKS_Y; j++) {
							if (blocks[i][j].getName().equals(text)) {
								// body{
								openingBlock(i, j);
								// body}
							}
						}
					}

				}
			}

		};
		//// ActionListener}

		//// MouseListener{
		mouseListener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {

				if (arg0.getButton() == MouseEvent.BUTTON1) {
					if (arg0.getSource() instanceof JButton) {
						String text = ((JButton) arg0.getSource()).getName();
						for (int i = 0; i < BLOCKS_X; i++) {
							for (int j = 0; j < BLOCKS_Y; j++) {
								if (blocks[i][j].getName().equals(text)) {
									if (checkBlocks[i][j] <= FIELD_CLOSED)
										blocks[i][j].setIcon(iZero);
								}
							}
						}
					}
				}

				if (arg0.getButton() == MouseEvent.BUTTON3) {
					if (arg0.getSource() instanceof JButton) {
						String text = ((JButton) arg0.getSource()).getName();
						for (int i = 0; i < BLOCKS_X; i++) {
							for (int j = 0; j < BLOCKS_Y; j++) {
								if (blocks[i][j].getName().equals(text)) {

									if (checkBlocks[i][j] == FIELD_CLOSED) {
										BOMBS--;
										JPanelHeader.getJPanelHeader().refreshCountBomb();
										blocks[i][j].setIcon(iFlag);
										checkBlocks[i][j] = FIELD_WITH_FLAG_ON_EMPTY_BLOCK;
										break;
									}
									if (checkBlocks[i][j] == FIELD_WITH_BOMB) {
										BOMBS--;
										JPanelHeader.getJPanelHeader().refreshCountBomb();
										blocks[i][j].setIcon(iFlag);
										checkBlocks[i][j] = FIELD_WITH_FLAG_ON_BOMB;
										break;
									}
									if (checkBlocks[i][j] == FIELD_WITH_FLAG_ON_EMPTY_BLOCK) {
										BOMBS++;
										JPanelHeader.getJPanelHeader().refreshCountBomb();
										blocks[i][j].setIcon(iCloseBlock);
										checkBlocks[i][j] = FIELD_CLOSED;
										break;
									}
									if (checkBlocks[i][j] == FIELD_WITH_FLAG_ON_BOMB) {
										BOMBS++;
										JPanelHeader.getJPanelHeader().refreshCountBomb();
										blocks[i][j].setIcon(iCloseBlock);
										checkBlocks[i][j] = FIELD_WITH_BOMB;
										break;
									}
								}
							}
						}
					}
				}

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (arg0.getSource() instanceof JButton) {
					String text = ((JButton) arg0.getSource()).getName();
					for (int i = 0; i < BLOCKS_X; i++) {
						for (int j = 0; j < BLOCKS_Y; j++) {
							if (blocks[i][j].getName().equals(text)) {
								if (checkBlocks[i][j] <= FIELD_CLOSED)
									blocks[i][j].setIcon(iCloseBlock);
							}
						}
					}
				}

			}

		};
		//// MouseListener}

	}

}
