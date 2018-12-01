package com.example.photosapp;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class represents the Album object. Albums across a user cannot have the same names.
 * Albums contain a list of Photo objects and keep track of the number of Photo objects in the album and the dates of the earliest and latest photo.
 *
 * @author Nikunj Jhaveri
 * @author Louie Zhou
 *
 */
public class Album implements Serializable{

    /**
     * The serialization version ID used to serialize the data
     */
    private static final long serialVersionUID = 1L;

    /**
     * The name of the album
     */
    private String name;

    /**
     * This list of photos in the album
     */
    private List <Photo> album;

    /**
     * The number of photos in an album
     */
    public int numOfPhotos;

    /**
     * The date of the photo in the album with the earliest date
     */
    LocalDate earliest;

    /**
     * The date of the photo in the album with the latest date
     */
    LocalDate latest;

    /**
     * The
     * @param albumName
     */
    public Album(String albumName)
    {
        name = albumName;
        album = new ArrayList<Photo>();
        numOfPhotos = album.size();
    }

    public Photo getPhoto(Photo pic) {
        for(Photo p: this.album) {
            if(p.getPathName().equals(pic.getPathName())) {
                return p;
            }
        }
        return null;
    }
    /***
     * Add Photo
     *
     * This method adds a photo to an Album and increments the counter for the number of photos
     *
     * @param pic the photo to be added to the album
     * @return true if the photo was successfully added to the album.
     */
    public boolean addPhoto(Photo pic)
    {
        album.add(pic);
        numOfPhotos = album.size();
        if(earliest == null)
        {
            earliest = pic.getDate();
        }
        if(latest == null)
        {
            latest = pic.getDate();
        }
        else if(earliest.isAfter(pic.getDate()))
        {
            earliest = pic.getDate();
        }
        else if(latest.isBefore(pic.getDate()))
        {
            latest = pic.getDate();
        }
        return true;
    }

    /**
     * Get Photo list
     *
     * This method returns the list of photos for a specific album
     * @return album the album witht the list of photos
     */
    public List<Photo> getPhotos()
    {
        return album;
    }

    /**
     * Remove Photo
     *
     * This method removes a specific photo from an album
     *
     * @param pic the photo to be removed from the album
     */
    public void removePhoto(Photo pic)
    {
        if(!pic.getDate().isEqual(earliest) && !pic.getDate().isEqual(latest))
        {
            album.remove(pic);
            numOfPhotos = album.size();
            return;
        }
        if(album.size()==1)
        {

            earliest = null;
            latest = null;
            album.remove(pic);
            numOfPhotos --;

            return;
        }
        else {
            album.remove(pic);
            numOfPhotos --;
            earliest = album.get(0).getDate();
            latest = album.get(0).getDate();
            for(Photo x: album)
            {
                if(x.getDate().isBefore(earliest))
                {
                    earliest = x.getDate();
                }
                if(x.getDate().isAfter(latest))
                {
                    latest = x.getDate();
                }
            }
        }

    }

    /***
     * Get Album Name
     *
     * This method returns the name of the album object
     * @return name the name of the photo album
     */
    public String getName()
    {
        return name;
    }

    /***
     * Rename Album
     *
     * This method is used to change the current name for an album
     * @param newName the new name the album is to be renamed to
     */
    public void rename(String newName)
    {
        name = newName;
    }


    /***
     * To String
     *
     * this method outputs the string representation of the object
     *
     * @return the name of the album and the number of photos contained in it.
     */
    public String toString()
    {
        return name + " #Photos: " + numOfPhotos;
    }


    /***
     * Album details
     *
     * This method outputs the details of the album such as its name, number of photos, and date range of photos.
     * @return the string representation of all of the albums fields.
     */
    public String AlbumDetails()
    {
        String e = " ";
        String l = " ";
        if(earliest != null)
        {
            e = earliest.toString();
        }
        if(latest != null)
            l = latest.toString();

        String x = "Name: " + name.toString() + "\n# of photos: " + numOfPhotos + "\nDate Range: " + e + " - " + l + " ";
        return x;
    }

}

