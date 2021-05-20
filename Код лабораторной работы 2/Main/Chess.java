package Main;

public class Chess {
    int[][] borad = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},};

    int[][] openBorad = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},};

    public void place(int x, int y){
        borad[y][x] = 1;
        close(x,y,1);
        placeNext(y+1);
    }

    public void replace(int x, int y){
        borad[y][x] = 0;
        close(x,y,-1);
    }

    public void close(int x, int y, int p){
        int i=0;
        while (x+i<8 && y+i<8) {
            openBorad[y + i][x + i] += p;
            i++;
        }
        i=1;
        while (x-i>=0 && y+i<8) {
            openBorad[y + i][x - i] += p;
            i++;
        }
        i=1;
        while (y+i<8){
            openBorad[y+i][x] += p;
            i++;
        }
    }

    public void placeNext(int y){
        if(y!=8) {
            for (int i = 0; i < 8; i++) {
                if (openBorad[y][i] <= 0) {
                    place(i, y);
                    replace(i, y);
                }
            }
        }
        else {
            showBoard();
            System.out.println("\n\n");
        }
    }

    public void showBoard(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++)
                System.out.print(borad[i][j]+"\t");
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        Chess chess = new Chess();
        chess.placeNext(0);
    }
}
