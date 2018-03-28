package user.admin.com.myapplication;

/**
 * Created by karan on 1/29/2017.
 */

public class registered_people {

    private String name;
    private int age;
    private boolean gender;
    private String photoUrl;

    public registered_people(String mname, int mage, boolean mgender, String mphotoUrl) {
        this.name = mname;
        this.age = mage;
        this.gender = mgender;
        this.photoUrl = mphotoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age1) {
        this.age = age1;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender1) {
        this.gender = gender1;
    }

}
