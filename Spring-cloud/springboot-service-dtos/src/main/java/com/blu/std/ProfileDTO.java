package com.blu.std;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ProfileDTO implements Serializable {

    private long   profileID;
    private String profileName;
    private String profileStatus;
    private String profileCategory;
    private Date   profileCreateDate;

    public long getProfileID() {
        return profileID;
    }

    public ProfileDTO(long profileID, String profileName, String profileStatus, String profileCategory, Date profileCreateDate) {
        this.profileID = profileID;
        this.profileName = profileName;
        this.profileStatus = profileStatus;
        this.profileCategory = profileCategory;
        this.profileCreateDate = profileCreateDate;
    }

    public void setProfileID(long profileID) {
        this.profileID = profileID;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }

    public String getProfileCategory() {
        return profileCategory;
    }

    public void setProfileCategory(String profileCategory) {
        this.profileCategory = profileCategory;
    }

    public Date getProfileCreateDate() {
        return profileCreateDate;
    }

    public void setProfileCreateDate(Date profileCreateDate) {
        this.profileCreateDate = profileCreateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDTO that = (ProfileDTO) o;
        return profileID == that.profileID &&
                Objects.equals(profileName, that.profileName) &&
                Objects.equals(profileCategory, that.profileCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileID, profileName, profileCategory);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "profileID=" + profileID +
                ", profileName='" + profileName + '\'' +
                ", profileStatus='" + profileStatus + '\'' +
                ", profileCategory='" + profileCategory + '\'' +
                ", profileCreateDate=" + profileCreateDate +
                '}';
    }
}
