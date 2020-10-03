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
      private Player player = new Player();
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
            if (player.getCurrentRoom().getId() == id) {
                  return true;
            }
            return false;
      }

      /**
      * Produces a string that can be printed to produce an ascii rendering of the room and all of its contents
      * @return (String) String representation of how the room looks
      */
      public String displayRoom() {
            // Not sure if this is the most efficient way of doing this, this is just the most relevant way coming from C
            char[] roomChars = new char[(width + 1) * height];

            // North wall
            for (int i = 0; i < width; i++) {
                  roomChars[i] = '-';
                  roomChars[i + 1] = '\n';
            }

            // Inside room
            for (int i = 1; i < height - 1; i++) { // i iterates the height
                  roomChars[i * (width + 1)] = '|'; // height * size of one line = i * width + 1 (+1 for '\n')
                  for (int o = 1; o < width - 1; o++) {
                        roomChars[i * (width + 1) + o] = '.'; // same as last iteration except o now iterates on the line from left to right
                        roomChars[i * (width + 1) + o + 1] = '|'; // 1 to the right of the iterator
                        roomChars[i * (width + 1) + o + 2] = '\n'; // 2 to the left of the iterator
                  }
            }

            // South wall
            for (int i = 0; i < width; i++) {
                  roomChars[(height * (width + 1) + i) - (width + 1)] = '-';
                  roomChars[(height * (width + 1) + i) - (width + 1) + 1] = '\n';
            }

            // Doors
            for (int i = 0; i < doorDirection.size(); i++) {
                  String wall = doorDirection.get(i);
                  int location = doorLocation.get(i);
                  if (wall.equals("N")) {
                        roomChars[location] = '+';
                  } else if (wall.equals("E")) {
                        roomChars[location * (width + 1) + (width - 1)] = '+';
                  } else if (wall.equals("S")) {
                        roomChars[(height * (width + 1) + location) - (width + 1)] = '+';
                  } else if (wall.equals("W")) {
                        roomChars[location * (width + 1)] = '+';
                  }
            }

            // Items
            for (int i = 0; i < roomItems.size(); i++) {
                  Item currentItem = roomItems.get(i);

                  int xLocation = (int) currentItem.getXyLocation().getX();
                  int yLocation = (int) currentItem.getXyLocation().getY();

                  roomChars[xLocation + (yLocation * (width + 1))] = '*';
            }

            // Player
            if (isPlayerInRoom()) {
                  int xLocation = (int) player.getXyLocation().getX(); // getX() returns double, need to cast to int
                  int yLocation = (int) player.getXyLocation().getY();

                  roomChars[xLocation + (yLocation * (width + 1))] = '@';
            }

            String roomString = String.valueOf(roomChars); // Converting array of chars to string
            return roomString;
      }
}
