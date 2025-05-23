package Model.Map;

public class Grid {

    private int width;
    private int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void addCells(Cell[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(i,j);
                System.out.println("x: " + grid[i][j].getX() + ", y: " + grid[i][j].getY());
            }
        }
    }
}
