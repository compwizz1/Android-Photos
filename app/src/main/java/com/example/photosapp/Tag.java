package com.example.photosapp;

import java.io.Serializable;

/**
 * This class represents the Tag object. A tag contains a Tag type and a tag value. Tags are used by the user to search for photos with specific tags.
 *
 * @author Nikunj Jhaveri
 * @author Louie Zhou
 *
 */
public class Tag implements Serializable {

    /**
     * The serialization version ID used to serialize the data
     */
    private static final long serialVersionUID = 1L;

    /**
     * The tag type
     */
    private String tagName;

    /**
     * The tag value
     */
    private String tagValue;

    /**
     * The constructor used to initialize the tag type and name
     * @param name
     * @param Value
     */
    public Tag(String name, String Value)
    {
        tagName = name;
        tagValue = Value;
    }

    /***
     * Equals Tag
     *
     * This method takes in a tga parameter and checks if it is equal to the comparing tag object
     * @param tag
     * @return a boolean value to determine whether the tags are equal or not
     */
    public boolean equals(Tag tag)
    {
        if(this.tagName.equals(tag.tagName) && this.tagValue.equals(tag.tagValue))
            return true;
        return false;
    }

    /***
     * Get Name
     *
     * This method returns the tags name which is the tag's type
     * @return tagName the type of tag the object is.
     */
    public String getTagName()
    {
        return tagName;
    }

    /***
     * Get Tag Value
     *
     * This method returns the value of the tag
     * @return tagValue the value of the tag
     */
    public String getTagValue()
    {
        return tagValue;
    }

    /***
     * String Output
     *
     * This method outputs the string representation of the Tag
     * return tagName and tagValue the data for the tag
     */
    public String toString()
    {
        return tagName +": " +tagValue;
    }

}