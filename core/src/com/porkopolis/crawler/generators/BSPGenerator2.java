package com.porkopolis.crawler.generators;
public class BSPGenerator2{
    private Dungeon dungeon
    private ArrayList<room> rooms = new ArrayList<room>;
    
    public void BSPGenerator2(Dungeon dungeon){
        this.dungeon = dungeon;
    }
    public void generate(){
    }
    
    private class leaf{
    }
    
    private class Room{
        int[][] room;
        int x, y, width, height;
        public void Room(int x, int y, width, height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            room = new int[height][width]
        }
        public void createRoom(){
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    room[x][y] = dungeon.getFloor1();
                    if(y == 0)
                        room[x][y] = dungeon.getTopWall();
                    if(y == height)
                        room[x][y] = dungeon.getBottomWall();
                    if(x == width)
                        room[x][y] = dungeon.getRightWall();
                    if(x == 0)
                        room[x][y] = dungeon.getLeftWall();
                    if(x == 0 && y == 0)
                        room[x][y] = dungeon.getTileSet.TOP_LEFT_INSIDE;
                    if(x == width && y == 0)
                        room[x][y] = dungeon.getTileSet.TOP_RIGHT_INSIDE;
                    if(x == 0 && y == height)
                        room[x][y] = dungeon.getTileSet.BOTTOM_LEFT_INSIDE;
                    if(x == width && y == height)
                        room[x][y] = dungeon.getTileSet.BOTTOM_LEFT_INSIDE;
                }
            }   
        }
    }

}