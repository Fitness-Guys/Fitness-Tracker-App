package edu.csueb.codepath.fitness_tracker;


import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import static com.parse.ParseClassName.*;

@ParseClassName("User")
public class UserModel extends ParseObject {
    String firstname;
    String lastname;
    int weight;
    int height;
    int age;
    String objectId;
    String username;

    @Override
    public String toString() {
        return "UserModel{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", objectId='" + objectId + '\'' +
                '}';
    }





    public String getObjectId(){
        return "ObjectId";//getString("objectId").toString();
    }


    public String getUsername() {
        return "getString(username";
    }

    public void setUsername(String username) {
        put("username",username);
    }



    public String getFirstname() {
        return "First Name"; //getString("firstname");
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return getString("lastname");
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getWeight() {
        return (int) get("weight");
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return (int) get("height");
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return (int) get("age");
    }

    public void setAge(int age) {
        this.age = age;
    }






}
