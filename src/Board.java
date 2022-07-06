import java.util.Arrays;
import java.util.Random;

public class Board {
    private int boardWidth;
    private int boardHeight;
    Cell[][] cells; //display cells
    //Boat[]

    //vars instructions ask me to make
    Boat[] allBoats;
    int totalShots=0;
    int turns;
    int shipsRemaining;

    //new vars
    private int[] sizeofEachBoat;

    public Board(int ros, int cols) {
        if(ros>=0 && cols>=0) {
            this.boardHeight = ros;
            this.boardWidth = cols;
            initialiseBoard();
        }
    }
    public void initialiseBoard(){
        cells = new Cell[boardHeight][boardWidth];
        for(int i=0;i<boardHeight;i++){
            for (int j=0;j<boardWidth;j++){
                //board[i][j]='-'; //initialize cell here
                Cell cell = new Cell(i,j);
                cell.setStatus('-');
                cells[i][j]=cell;
            }
        }
    }
    public void printBoard(){
        for(int i=0;i<boardHeight;i++){
            for (int j=0;j<boardWidth;j++){
                System.out.print(cells[i][j]);
            }
            System.out.println("");
        }
    }
    public int getWidth(){
        return boardWidth;
    }

    public int getHeight(){
        return boardHeight;
    }

    public void setBoard(Cell[][] board) {
        this.cells = board;
    }


    public int randomXCoordinate(){
        Random rand = new Random();
        int randomX=rand.nextInt(boardWidth);
        return randomX;
    }
    public int randomYCoordinate(){
        Random rand = new Random();
        int randomY=rand.nextInt(boardHeight);
        return randomY;
    }
    public char randomAlignment(){
        Random rand = new Random();
        int align=rand.nextInt(2);
        if(align==0){
            return 'V';
        }
        else {
            return 'H';
        }
    }
    public void countBoats(){
        //System.out.println("count boards executed");
        if(boardWidth==3 || boardHeight==3){
            shipsRemaining=1;
            sizeofEachBoat = new int[1];
            sizeofEachBoat[0]=2;

            allBoats = new Boat[1];
            //System.out.println("excecuted count boards");
        }
        else if((3<boardWidth && boardWidth<=4)||(3<boardHeight && boardHeight<=4)){
            shipsRemaining=2;
            sizeofEachBoat = new int[shipsRemaining];
            sizeofEachBoat[0]=2;
            sizeofEachBoat[1]=3;

            allBoats = new Boat[2];
            //System.out.println("excecuted count boards");
        }
        else if((4<boardWidth && boardWidth<=6)||(4<boardHeight && boardHeight<=6)){
            shipsRemaining=3;
            sizeofEachBoat = new int[shipsRemaining];
            sizeofEachBoat[0]=2;
            sizeofEachBoat[1]=3;
            sizeofEachBoat[2]=3;

            allBoats = new Boat[3];
            //System.out.println("excecuted count boards");
        }
        else if((6<boardWidth && boardWidth<=8)||(6<boardHeight && boardHeight<=8)){
            shipsRemaining=4;
            sizeofEachBoat = new int[shipsRemaining];
            sizeofEachBoat[0]=2;
            sizeofEachBoat[1]=3;
            sizeofEachBoat[2]=3;
            sizeofEachBoat[3]=4;

            allBoats = new Boat[4];
            //System.out.println("excecuted count boards");
        }
        else if((8<boardWidth && boardWidth<=10)||(8<boardHeight && boardHeight<=10)){
            shipsRemaining=5;
            sizeofEachBoat = new int[shipsRemaining];
            sizeofEachBoat[0]=2;
            sizeofEachBoat[1]=3;
            sizeofEachBoat[2]=3;
            sizeofEachBoat[3]=4;
            sizeofEachBoat[4]=5;

            allBoats = new Boat[5];

            // System.out.println("excecuted count boards");
        }
        else{
            System.out.println("invalid board dimensions");
        }
    }
    public boolean placeBoat(int boatSize, int x, int y, char alignment){

        //System.out.println("randoms: "+x+" "+ y +" "+ alignment);

        if(alignment=='V'){

            int i =x;
            for(int times=0; times<boatSize; times++){
                /*if(i>=3){
                    i=i-2;
                }*/
                /*if(i>boatSize){
                    i=i-2;
                }*/
                if(cells[i][y].getStatus()=='-'){
                    cells[i][y].setStatus('B');
                }
                i++;
            }
        }
        else if(alignment=='H'){
            int j =y;
            for(int times=0; times<boatSize; times++) {
                if (j >= 3) {
                    j = j - 2;
                }
                if (cells[x][j].getStatus() == '-') {
                    cells[x][j].setStatus('B');
                }
                j++;
            }

        }

        return true;
    }

    public boolean cellsAvailable(Boat boat){
        boolean available=true;
        for (int i=0;i<boat.cellsofBoat.length;i++){
            if(boat.cellsofBoat[i].getY()>=boardWidth){
                available=false;
                return available;
            }
            if(boat.cellsofBoat[i].getX()>=boardHeight){
                available=false;
                return available;
            }
            if(boat.cellsofBoat[i].getStatus()!='-'){
                available=false;
                return available;
            }
        }
        return available;
    }

    public void placeBoats(){
        //System.out.println("place all boats Executed");
        countBoats();
        //System.out.println(sizeofEachBoat.length);
        for(int i=0; i<this.sizeofEachBoat.length;i++){

            int x = randomXCoordinate();
            int y = randomYCoordinate();
            char alignment= randomAlignment();

            placeBoat(sizeofEachBoat[i],x , y,alignment);
            Boat boat = new Boat(sizeofEachBoat[i],alignment,new Cell(x,y));
            boat.cellsofBoat[0].setStatus('B');

            allBoats[i]=boat;
        }
        //board.printBoard();
    }

    public boolean fire(int x, int y){
        //boolean boatpresent=false;
        if(x<=-1||x>boardHeight-1||y<=-1||y>boardWidth-1){
            System.out.println("Invalid Co-ordiante");
            return false;
        }
        else {
            for (int i = 0; i < allBoats.length; i++) {
                for (int j = 0; j < allBoats[i].cellsofBoat.length; j++) {
                    if (x == allBoats[i].cellsofBoat[j].getX()) {
                        if (y == allBoats[i].cellsofBoat[j].getY()) {
                            // System.out.println("boat cell hit");
                            allBoats[i].cellsofBoat[j].setStatus('H');
                        }
                    }
                }
            }

            if (cells[x][y].getStatus() == '-') {
                System.out.println("miss");
                cells[x][y].setStatus('M');
                //return false;
            } else if (cells[x][y].getStatus() == 'B') {
                System.out.println("hit");
                cells[x][y].setStatus('H');
                printBoard();
                return true;
            } else if (cells[x][y].getStatus() == 'H') {
                System.out.println("penalty--location already hit");
                System.out.println("turn skipped");
                turns++;
                return false;
            } else if (cells[x][y].getStatus() == 'M') {
                System.out.println("penalty--location already missed");
                turns++;
                return false;
            }
            return false;
        }
    }

    public boolean isSunk(Boat boat){
        //System.out.println("Boat Sink called");
        int cellsDead=0;

        for(int i=0; i<boat.cellsofBoat.length;i++){
            //System.out.println("Get status" + boat.cellsofBoat[i].getStatus());
            if(boat.cellsofBoat[i].getStatus()=='H'){
                cellsDead++;
            }
        }

        //System.out.println(" "+cellsDead);
        if(cellsDead== boat.boatsize){
            return true;
        }
        else {
            return false;
        }

    }
    public int boatCount(){
        //System.out.println("boat count being called");
        for(int i=0;i<allBoats.length;i++){
            //System.out.println("ship sunk for loop ran");
            if(isSunk(allBoats[i])){
                System.out.println("Ship Sunk!!");
                shipsRemaining--;
            }
        }
        System.out.println("Ships Remaining "+ shipsRemaining);
        return shipsRemaining;
    }

    public void missile(int x, int y){
        /*
        * CO-ordinates hit
        * (x-1, y-1), (x-1,y), (x-1, y+1),
        * (x, y-1), (x,y), (x, y+1)
        * (x+1,y-1), (x+1, y), (x+1, y+1)
        * */


        fire(x-1,y-1);
        fire(x-1,y);
        fire(x-1, y+1);
        fire(x, y-1);
        fire(x,y);
        fire(x, y+1);
        fire(x+1,y-1);
        fire(x+1, y);
        fire(x+1, y+1);
    }
    public int drone(int c, char align){
    int cellsOccupied=0;
    if(align=='r'){
        for(int i=0;i<cells.length;i++){
            if(cells[c][i].getStatus()=='B'||cells[c][i].getStatus()=='H'){
                cellsOccupied++;
            }
        }
    }
    else if(align=='c'){
        for(int j=0;j<cells.length;j++){
            if(cells[j][c].getStatus()=='B'||cells[j][j].getStatus()=='H'){
                cellsOccupied++;
            }
        }
    }
    System.out.println("Drone found "+cellsOccupied+" co-ordinates occupied");
    return cellsOccupied;

    }

    public void display(){
        for(int i=0;i<boardHeight;i++){
            for (int j=0;j<boardWidth;j++){
                if(cells[i][j].getStatus()=='B'){
                    System.out.print('-');
                }
                else{
                    System.out.print(cells[i][j].getStatus());
                }
            }
            System.out.println("");
        }
    }

}