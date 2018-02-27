package com.professional.andri.taskmanager.migration;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by User on 2/25/2018.
 */

public class TaskMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        // DynamicRealm exposes an editable schema
        RealmSchema schema = realm.getSchema();

        // Migrate to version 1: Add a new class.
        // Example:
        // public Person extends RealmObject {
        //     private String name;
        //     private int age;
        //     // getters and setters left out for brevity
        // }
        if (oldVersion == 0) {
            schema.create("Person")
                    .addField("name", String.class)
                    .addField("age", int.class);
            oldVersion++;
        }

        // Migrate to version 2: Add a primary key + object references
        // Example:
        // public Person extends RealmObject {
        //     private String name;
        //     @PrimaryKey
        //     private int age;
        //     private Dog favoriteDog;
        //     private RealmList<Dog> dogs;
        //     // getters and setters left out for brevity
        // }
        if (oldVersion == 2) {
            schema.get("UserRealm")
                    .addField("username", String.class)
                    .addField("password", String.class);
            oldVersion++;
        }

        if(oldVersion == 3){
            schema.get("TaskRealm")
                    .addField("deadline", String.class);
            oldVersion++;
        }

    }
}
