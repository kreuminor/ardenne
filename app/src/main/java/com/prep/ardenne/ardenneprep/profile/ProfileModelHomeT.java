package com.prep.ardenne.ardenneprep.profile;
/**
 * Created by nirav on 09/01/17.
 */

public class ProfileModelHomeT {

    private String username;

    private String country;

    private int profilePic;

    public ProfileModelHomeT(String username, String country, int profilePic) {
        this.username = username;
        this.country = country;
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }
}
