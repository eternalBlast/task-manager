package com.professional.andri.taskmanager.model;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Andri on 2/22/2018.
 */

@Parcel
public class User {
    public long id;
    public String level;
    public String name;
    public ArrayList<Task> tasks;
}
