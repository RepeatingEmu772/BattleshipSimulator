import java.util.Scanner;

public class Game {
    public Board board;

    public Game(int ros, int cols){
        Board boar = new Board(ros,cols);
        this.board=boar;
    }

    public static boolean checkInput(int x, int y){
        if(x>=3&&x<=10&&y>=3&&y<=10){
            return true;
        }
        else {
            return false;
        }


    }


    public static void main(String[] args){
        // for testing purposes let this be 3 x 3
        boolean debug=false;
        boolean boardset=false;
        Scanner outterScanner= new Scanner(System.in);
        System.out.println("Welcome to BattleShips!");
        System.out.println("To run game in debug mode, enter d otherwise put n");
        String mode=outterScanner.nextLine();

        while(!boardset) {
            if (mode.equals("d")) {
                System.out.println("Debug Mode activated");
                debug = true;
                boardset = true;
            } else if (mode.equals("n")) {
                System.out.println("Debug Mode Disabled");
                debug = false;
                boardset = true;
            } else {
                System.out.println("Invalid Debug type");
                boardset = false;
                System.out.println("To run game in debug mode, enter d otherwise put n");
                mode=outterScanner.nextLine();
            }
        }

        boolean coset=false;
        System.out.println("Enter co-ordinates for board");
        String dim=outterScanner.nextLine();
        String[] dimensions=dim.split(" ",2);

        int x=Integer.valueOf(dimensions[0]);
        int y=Integer.valueOf(dimensions[1]);

        if(x>=3&&x<=10&&y>=3&&y<=10){
            coset=true;
        }
        else {
            coset=false;
        }

        while (!coset){
            System.out.println("Invalid Co-ordinates,Enter new ones ");
            String dim2=outterScanner.nextLine();
            String[] dimensions2=dim2.split(" ",2);
            x=Integer.valueOf(dimensions2[0]);
            y=Integer.valueOf(dimensions2[1]);
            if(x>=3&&x<=10&&y>=3&&y<=10){
                coset=true;
            }
            else {
                 coset=false;
            }
        }

        Game game = new Game(x,y);


        //game.board.initialiseBoard();
        //int numofBoats = game.board.countBoats();
        game.board.placeBoats();
        System.out.println(Integer.valueOf(game.board.allBoats.length)+" have been placed on Board!");
        //game.board.printBoard();
        boolean droneused=false;
        boolean missileused=false;

        while(game.board.shipsRemaining!=0){
            game.board.turns++;
            if(debug){
                game.board.printBoard();
            }
            else {
                game.board.display();
            }
            System.out.println("Turn "+ game.board.turns);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter co-ordinates to hit or initate a drone/missile");

            String command = scanner.nextLine();
            System.out.println(command);

            if(command.equals("drone")){
                if(!droneused) {
                    System.out.println("Drone Initialted");
                    System.out.println("Would you like to scan a row or a column. Enter r for row or c for column");
                    String alignin = scanner.nextLine();
                    char align = alignin.charAt(0);
                    System.out.println("Enter co-ordiante for missile launch");
                    String dcommands = scanner.nextLine();
                    game.board.drone(Integer.valueOf(dcommands), align);
                    droneused = true;
                }
                else{
                    System.out.println("You have already used your drone!");
                }

            }
            else if(command.equals("missile")){
                if(!missileused) {
                    System.out.println("Missile Initialted");
                    System.out.println("Enter co-ordiantes for missile launch");
                    String mcommands = scanner.nextLine();
                    String[] missile = mcommands.split(" ", 2);

                    int missilex = Integer.valueOf(missile[0]);
                    int missiley = Integer.valueOf(missile[1]);
                    game.board.missile(missilex, missiley);
                    game.board.boatCount();
                    missileused = true;
                }
                else {
                    System.out.println("You have already used your missile!");
                }
            }
            else{
                String[] attack = command.split(" ", 2);
                boolean valid =false;
                int attackx = Integer.valueOf(attack[0]);
                int attacky = Integer.valueOf(attack[1]);

                if(x>=3&&x<=game.board.getWidth()&&y>=3&&y<=game.board.getHeight()){
                    valid=true;
                }
                else {
                    valid=false;
                }

                while (!valid){
                    System.out.println("Invalid Co-ordinates,Enter new ones ");
                    String dim2=outterScanner.nextLine();
                    String[] dimensions2=dim2.split(" ",2);
                    x=Integer.valueOf(dimensions2[0]);
                    y=Integer.valueOf(dimensions2[1]);
                    if(x>=3&&x<=game.board.getWidth()&&y>=3&&y<=game.board.getHeight()){
                        valid=true;
                    }
                    else {
                        valid=false;
                    }
                }
                if (game.board.fire(attackx, attacky) == true) {
                    game.board.boatCount();
                    game.board.totalShots++;
                }
            }



        }
        System.out.println("you won in "+ game.board.turns+" turns");
    }
}
