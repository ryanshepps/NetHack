package rogue;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Point;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Rogue{

      private ArrayList<Room> rooms = new ArrayList<Room>(); // Why do I have to malloc here?
      private ArrayList<Item> items = new ArrayList<Item>();

      public void setPlayer(Player thePlayer){

      }


      public void setSymbols(String filename){

      }

      public ArrayList<Room> getRooms(){
        return rooms;
      }

      public ArrayList<Item> getItems(){
        return items;

      }
      public Player getPlayer(){
        return null;

      }

      public void createRooms(String filename){
            JSONParser parser = new JSONParser();
            try {
                  // Reads in JSON object from filename
                  Object obj = parser.parse(new FileReader(filename));
                  JSONObject jObjectRooms = (JSONObject) obj;

                  // Converts room array in JSON object to JSON array.
                  JSONArray jArrayRooms = (JSONArray) jObjectRooms.get("room");
                  // Iterates through each object in jArrayRooms
                  for (Object roomObject : jArrayRooms) {
                        JSONObject currentRoomObj = (JSONObject) roomObject; // Converts key to JSONObject

                        Room newRoom = new Room();

                        // Converting long to integer.
                        Integer roomWidth = Integer.decode(currentRoomObj.get("width").toString());
                        Integer roomHeight = Integer.decode(currentRoomObj.get("height").toString());
                        Integer roomId = Integer.decode(currentRoomObj.get("id").toString());

                        newRoom.setWidth(roomWidth);
                        newRoom.setHeight(roomHeight);
                        newRoom.setId(roomId);

                        // --DOORS ARRAY-- //
                        JSONArray jArrayDoors = (JSONArray) currentRoomObj.get("doors");
                        for (Object doorsObject : jArrayDoors) {
                              JSONObject currentDoorObj = (JSONObject) doorsObject;

                              String doorDirection = currentDoorObj.get("dir").toString();
                              Integer doorId = Integer.decode(currentDoorObj.get("id").toString());

                              newRoom.setDoor(doorDirection, doorId);
                        }

                        // --LOOT ARRAY-- //
                        ArrayList<Item> roomItems =  new ArrayList<Item>();
                        JSONArray jArrayLoot = (JSONArray) currentRoomObj.get("loot");
                        for (Object lootObject : jArrayLoot) {
                              JSONObject currentLootObj = (JSONObject) lootObject;

                              Item newItem = new Item();

                              Integer itemId = Integer.decode(currentLootObj.get("id").toString());
                              Integer itemXPosition = Integer.decode(currentLootObj.get("x").toString());
                              Integer itemYPosition = Integer.decode(currentLootObj.get("y").toString());
                              Point xyLocation = new Point(itemXPosition, itemYPosition);

                              newItem.setId(itemId);
                              newItem.setXyLocation(xyLocation);

                              roomItems.add(newItem);
                        }

                        newRoom.setRoomItems(roomItems);

                        rooms.add(newRoom);

                        // --ITEMS ARRAY-- //
                        JSONArray jArrayItems = (JSONArray) jObjectRooms.get("items");
                        for (Object itemObject : jArrayItems) {
                              JSONObject currentItemObj = (JSONObject) itemObject;

                              Item newItem = new Item();

                              Integer itemId = Integer.decode(currentItemObj.get("id").toString());
                              String itemName = currentItemObj.get("name").toString();
                              String itemType = currentItemObj.get("type").toString();

                              newItem.setId(itemId);
                              newItem.setName(itemName);
                              newItem.setType(itemType);

                              items.add(newItem);
                        }
                  }
            } catch(FileNotFoundException e) {
                  e.printStackTrace();
            } catch (IOException e) {
                  e.printStackTrace();
            } catch (ParseException e) {
                  e.printStackTrace();
            }
      }

      public String displayAll(){
        //creates a string that displays all the rooms in the dungeon
        return null;
      }
}
