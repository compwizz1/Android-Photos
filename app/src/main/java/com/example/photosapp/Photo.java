package com.example.photosapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;

//import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * This class represents the Photo object. A photo object contains an image, a caption, a date, and a list of tags that can be used to search for it.
 *
 * @author Nikunj Jhaveri
 * @author Louie Zhou
 *
 */
public class Photo implements Serializable
{
    /**
     * The Serialization ID used to serialize the photo
     */
    private static final long serialVersionUID = 1L;

    /**
     * The list of tags belonging to the photo
     */
    private List <Tag> tags;

    /**
     * The caption for the photo
     */
    private String caption;

    /**
     * A file marker used when obtaining the image from a file
     */
    private final String file = "file:";

    /**
     * For use in making an Image instance
     */
    private String pathName;

    /**
     * Long representation of the last modified date
     */
    private long modifiedRaw;

    /**
     * Human readable representation of the last modified date
     */
    private String modifiedReadable;

    /**
     * For consistent date time format
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    /**
     * The local date time of a photo
     */
    private LocalDate localdate;


    private transient ProxyBitmap proxyPic;


    /**
     * The constructor used to initialize a new photo. This sets the date of the photo and gets the file location.
     *
     */
    public Photo(String pathName)
    {
        this.pathName = pathName;
        //File f = new File(pathName);
        //this.modifiedRaw = f.lastModified();
        //this.modifiedReadable = sdf.format(this.modifiedRaw);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        //localdate = LocalDate.parse(modifiedReadable, formatter);
        this.caption = "";
        this.tags = new ArrayList<Tag>();



    }


    public Uri getPic()
    {
        /*
        try {

            URL url = new URL(pathName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();

            Bitmap b = BitmapFactory.decodeStream(input);
            System.out.println("bitmap object");
            System.out.println(b.toString());

            //Bitmap b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);;
            //proxyPic = new ProxyBitmap(b);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(proxyPic==null)
        {
            System.out.println("no proxy pic inserted");
        }
        return proxyPic.getBitmap();*/

        return Uri.parse(pathName);
    }


    /**
     * Get Photo's Image
     *
     * This method gets the image associated with a photo pbject
     * @return Image associated with the photo
     */
   /* public Image getImage() {
        File f = new File(pathName);
        String mimetype= new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        if(type.equals("image")) {
            return new Image(this.file + this.pathName);
        }
        return null;
    }*/

    /**
     * Path name
     *
     * This method returns the path file name of the photo
     * @return pathname the file name of the photo
     */
    public String getPathName()
    {
        return this.pathName;
    }

    /***
     * Gets the Caption
     *
     * This method returns the current caption of the photo
     *
     * @return the string representation of a photo's caption
     */
    public String getCaption()
    {
        return caption;
    }

    /***
     * Sets Caption
     *
     * This method updates the Caption for the current photo
     *
     * @param newCaption the new caption for the photo
     *
     */
    public void setCaption(String newCaption)
    {
        caption = newCaption;

    }

    /**
     * Get Local Date
     *
     * This method returns the local date object for the current photo
     * @return local date the date the photo was last modified
     */
    public LocalDate getDate()
    {
        return localdate;
    }



    /***
     * Gets Tags
     *
     * This method returns the list of tags for a photo
     * @return the list of tags for the photo
     */
    public List<Tag> getTags()
    {
        return tags;
    }

    /***
     * Adds Tag
     *
     * This method takes in a new tag and adds it to the photo's current list of its tags
     *
     * @param tag the tag to be added to the photo
     * @return
     */
    public boolean addTag(Tag tag)
    {
        for (int i = 0; i < tags.size(); i++)
        {
            if (tags.get(i).equals(tag))
            {
                return false;
            }
        }
        tags.add(tag);
        return true;
    }


    /***
     * Removes tag
     *
     * This method removes a tag from the photos list of tags
     *
     * @param tag The tag that is to be removed from the tag list
     */
    public void removeTag(Tag tag)
    {
        Tag temp = null;
        for(Tag t: tags) {
            if(tag.equals(t)) {
                temp = t;
            }
        }
        tags.remove(temp);

    }

    /**
     * Contains Tag
     *
     * This method check
     * @param search the tag to search for
     * @return returns true if the tag is found or false otherwise
     */
    public boolean containsTag(Tag search)
    {
        for(Tag x: tags)
        {
            if(x.equals(search))
            {
                return true;
            }
        }
        return false;

    }

    /**
     * To String
     *
     * This method returns the date of the photo as a string readable by the user
     * @return the string representation of the local date.
     */
    public String toString()
    {
        return "photo";
    }

    /**
     * Print Tags
     *
     * This method prints all the tags for a given photo
     * @return list of tags as a string
     */
    public String printTags()
    {
        String list = "Tags: ";
        int count = 1;
        for (Tag a: tags)
        {
            list = list + count + ") "+a.getTagName() + ": " + a.getTagValue()+ "/";
            count++;

        }
        return list;
    }
}