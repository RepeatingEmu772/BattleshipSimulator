public class Boat {
    int boatsize;
    char alignment;
    Cell[] cellsofBoat;


    public Boat(int boatsize, char alignment, Cell firstCell){
        //initialise a cell array of boarts
        //orientation
        //leght
        //setBoat= new Cell[]
        this.boatsize=boatsize;
        this.alignment=alignment;
        this.cellsofBoat =new Cell[boatsize];
        cellsofBoat[0]=firstCell;

        if(alignment=='V'){
            for(int i=1;i<boatsize;i++){
                Cell cell = new Cell(firstCell.getX()+i,firstCell.getY());
                cell.setStatus(firstCell.getStatus());
                cellsofBoat[i]= cell;
                //System.out.println("Set status"+cellsofBoat[i].getStatus());
            }
        }
        else if(alignment=='H'){
            for(int i=1;i<boatsize;i++){
                Cell cell = new Cell(firstCell.getX(),firstCell.getY()+i);
                cell.setStatus(firstCell.getStatus());
                cellsofBoat[i]= cell;
            }
        }
        //System.out.println(cellsofBoat);

    }
}
