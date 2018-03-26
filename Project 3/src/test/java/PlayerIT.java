/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.monoply_testing.BoardI;
import com.mycompany.monoply_testing.DiceI;
import com.mycompany.monoply_testing.PlayerI;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Opinator
 */
public class PlayerIT {

    DiceI dice;
    PlayerI player;
    Random rnd;
    int start;
    BoardI board;

    @Before
    public void setUp() {
        player = mock(PlayerI.class);
        dice = mock(DiceI.class);
        rnd = new Random();
        board = mock(BoardI.class);
        start = 0;

        when(board.getBoard()).thenReturn(new PlayerI[3][20]);
        when(dice.rollDice()).thenReturn(rnd.nextInt(10) + 2);
        int tempRoll = dice.rollDice();
        when(player.getPosition()).thenReturn(start + tempRoll);
    }

    @Test
    public void takeTurnTest() {
        PlayerI[][] testBoard = board.getBoard();
        int playerPos = start;
        testBoard[0][start] = player;

        int roll = dice.rollDice();
        int newPositon = playerPos + roll;

        testBoard[0][start] = null;
        testBoard[0][newPositon] = player;

        assertNull(testBoard[0][0]);
        assertEquals(testBoard[0][newPositon], player);

    }

}
