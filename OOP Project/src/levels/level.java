package levels;

public class level {
    private int[][] lvldata;

    public level(int[][] lvldata){
          this.lvldata = lvldata;
    }

    public int getSpritesIndex(int x , int y){
        return lvldata[y][x];
    }

    public int[][] GetLevelData(){
        return lvldata;
    }
}
