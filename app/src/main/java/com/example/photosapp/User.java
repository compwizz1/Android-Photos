package com.example.photosapp;
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
    private String username;

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
    private static final String storeFile = "users.dat";

    /**
     * The users list of possible tag types
     */
    private List<String> tagTypes;

    /**
     * The constructor used to initialize the user data
     * @param username The new username for the user
     */
    public User(String username)
    {
        this.username = username;
        albumList = new ArrayList<Album>();
        tagTypes = new ArrayList<String>();
        tagTypes.add("location");
        tagTypes.add("person");
    }

    /**
     * Get Username
     *
     * This method returns the username for the current user
     * @return
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
        return username;
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
     * @param rem
     */
    public void removeAlbum(Album rem)
    {
        albumList.remove(rem);
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
