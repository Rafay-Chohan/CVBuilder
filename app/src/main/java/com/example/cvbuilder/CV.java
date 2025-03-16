package com.example.cvbuilder;


import android.net.Uri;

public class CV {
    Uri profilePic;
    String Name,Email,PhoneNumber,Gender,Summary,Education,WorkPlace,WorkDuration,Certification,References;

    public CV() {
        Name = "";
        Email = "";
        PhoneNumber = "";
        Summary = "";
        Education = "";
        WorkPlace = "";
        WorkDuration = "";
        Certification = "";
        References = "";
        Gender = "";
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public CV(Uri profilePic, String name, String email, String phoneNumber, String gender, String summary, String education, String workPlace, String workDuration, String certification, String references) {
        this.profilePic = profilePic;
        Name = name;
        Email = email;
        PhoneNumber = phoneNumber;
        Summary = summary;
        Education = education;
        WorkPlace = workPlace;
        WorkDuration = workDuration;
        Certification = certification;
        References = references;
        Gender =gender;
    }
    public Uri getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Uri profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getWorkPlace() {
        return WorkPlace;
    }

    public void setWorkPlace(String workPlace) {
        WorkPlace = workPlace;
    }

    public String getWorkDuration() {
        return WorkDuration;
    }

    public void setWorkDuration(String workDuration) {
        WorkDuration = workDuration;
    }

    public String getCertification() {
        return Certification;
    }

    public void setCertification(String certification) {
        Certification = certification;
    }

    public String getReferences() {
        return References;
    }

    public void setReferences(String references) {
        References = references;
    }
}
