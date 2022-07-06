public class Cell {
    private int x; //I used x and y instead of row and column as I thought
    private int y;// that would be much easier to identify
    private char status='f';

    public Cell(int x, int y){
    this.x=x;
    this.y=y;
    //this.status=stats;
    }

    public char getStatus(){
        //System.out.println(this.status);
        return this.status;
    }
    public void setStatus(char stats){
        this.status=stats;
    }

    public int getX(){
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

    @Override
    public String toString() {
        return Character.toString(status);
    }
}
