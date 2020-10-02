package rogue;
import java.util.ArrayList;
import java.util.Map;
import java.awt.Point;


/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 */
public class Room {
      private int id;
      private int width;
      private int height;
      private ArrayList<Item> roomItems;
      private Player player;
      private ArrayList<String> doorDirection = new ArrayList<String>();
      private ArrayList<Integer> doorLocation = new ArrayList<Integer>();

      // Default constructor
      public Room() {

      }

      // Required getter and setters below
      public int getWidth() {
            return width;
      }

      public void setWidth(int newWidth) {
            this.width = newWidth;
      }

      public int getHeight() {
            return height;
      }

      public void setHeight(int newHeight) {
            this.height = newHeight;
      }

      public int getId() {
            return id;
      }

      public void setId(int newId) {
            this.id = newId;
      }

      public ArrayList<Item> getRoomItems() {
            return this.roomItems;
      }

      public void setRoomItems(ArrayList<Item> newRoomItems) {
            this.roomItems = newRoomItems;
      }

      public Player getPlayer() {
            return player;
      }

      public void setPlayer(Player newPlayer) {
            this.player = newPlayer;
      }

      public int getDoor(String direction){
            return 0;
      }

      /*
      direction is one of NSEW
      location is a number between 0 and the length of the wall
      */

      public void setDoor(String direction, int location){
            doorDirection.add(direction);
            doorLocation.add(location);
      }

      public boolean isPlayerInRoom() {
            return true;
      }




   /**
    * Produces a string that can be printed to produce an ascii rendering of the room and all of its contents
    * @return (String) String representation of how the room looks
    */
   public String displayRoom() {
    return null;


   }
}
