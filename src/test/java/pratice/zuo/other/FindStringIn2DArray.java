package pratice.zuo.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/13
 */
public class FindStringIn2DArray {

    public boolean exist(char[][] board, String word) {

        Map<String, List<Position>> positionMap = new HashMap<String, List<Position>>();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                List<Position> positionList = positionMap.get(String.valueOf(board[x][y]));
                if (positionList == null) {
                    positionList = new ArrayList<Position>();
                }
                Position position = new Position(x, y);
                positionList.add(position);
            }
        }

        String[] words = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            words[i] = String.valueOf(word.charAt(i));
        }

        for (int i = 0; i < word.length() - 1; i++) {

        }

        return false;
    }

    class Position {

        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    @Test
    public void test() {
        char[][] board = new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        boolean result = exist(board, word);
        System.out.println(result);
    }

}
