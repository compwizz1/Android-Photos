package com.example.photosapp;
import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A User is a type of profile on the application.
 * A user has a list of albums which contains photos. The user can perform many functions relating to their albums and their photos.
 *
 * @author Nikunj Jhaveri
 * @author Louie Zhou
 *
 */
public class User implements Serializable {

    /**
     * The username used to log in by the user
     */
    private String username = "TestUser";

    /**
     * The list of albums for the user
     */
    private List <Album> albumList;

    /**
     * The directory string used for serialization
     */
    private static final String storeDir = "data";

    /**
     * The file directory location for serialization
     */
    private static final String storeFile = "user.dat";

    /**
     * The users list of possible tag types
     */
    private List<String> tagTypes;

    private static final long serialVersionUID = 1L;

    public static void writeUser(User u, Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput(storeFile, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(u);
        os.close();
        fos.close();
    }
    public static User readUser(Context context) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(storeFile);
        ObjectInputStream is = new ObjectInputStream(fis);
        User u = (User) is.readObject();
        is.close();
        fis.close();
        return u;
    }
    /**
     * The constructor used to initialize the user data
     */

    public User()
    {
        albumList = new ArrayList<Album>();
        tagTypes = new ArrayList<String>();
        tagTypes.add("location");
        tagTypes.add("person");
    }

    /**
     * Get Username
     *
     * This method returns the username for the current user
     * @return the username as a string
     */
    public String getUserName()
    {
        return username;
    }

    /**
     * Get Album List
     *
     * This method  returns the album list of the user
     * @return albumList the users list of albums
     */
    public List<Album> getAlbumList()
    {
        return albumList;
    }

    /**
     * To String
     *
     * This method is used to return a string representation of the user which is the username
     * @return username the username of the user.
     */
    public String toString()
    {
        return albumList.get(0).toString();
    }

    /**
     * Add Album
     *
     * This method adds and album to the user's current list of albums
     * @param name The new name of the album to be added
     * @return true is the album was added or false if the album name already exists
     */
    public boolean addAlbum(String name)
    {
        for(Album a: albumList)
        {
            if (a.getName().equals(name))
            {
                return false;
            }
        }
        getAlbumList().add(new Album(name));
        return true;
    }

    /**
     * Get Album
     *
     * This method returns an album the user is searching for
     * @param search The album name the user is searching for
     * @return the album if found or null is not found
     */
    public Album getAlbum(Album search) {
        for (Album a: albumList) {
            if(a.getName().equals(search.getName())) {
                return a;
            }
        }
        return null;
    }

    /**
     * Remove Album
     *
     * This method takes in an album as a parameter and removes it from the current list of albums
     * @param rem removes an album
     */
    public void removeAlbum(Album rem)
    {
        int index = 0;
        for(Album x: albumList)
        {
            if (x.getName() == rem.getName())
            {
                albumList.remove(index);
                return;
            }
            index++;
        }

    }

    /**
     *Has album Name
     *
     * This method checks to see if the current album list contains a specific album name
     * @param newAlbum the album name the user is searching for
     * @return true if the name exists in the list of false otherwise
     */
    public boolean hasAlbumName(String newAlbum)
    {
        for(Album a: albumList)
        {
            if(a.getName().equals(newAlbum))
            {
                return true;
            }
        }
        return false;
    }

    public Album getAlbumFromName(String newAlbum)
    {
        for(Album a: albumList)
        {
            if(a.getName().equals(newAlbum))
            {
                return a;
            }
        }
        return null;
    }

    /**
     * 	Get Users Tag Types
     *
     * This method returns the users list of tag types
     * @return tagTypes the list of the available tag types to search for
     */
    public List<String> getTagTypes()
    {
        return tagTypes;
    }

    /**
     * Add New Tag
     *
     * This method adds a new tag type to the users list of tag types whenever a new tag is added to a photo
     * @param newTag The name for the new tag type to be added
     */
    public void addNewTagType(String newTag)
    {
        for(String tag: tagTypes)
        {
            if (tag.equals(newTag))
            {
                return;
            }
        }
        tagTypes.add(newTag);
        return;
    }





}
