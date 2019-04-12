package com.example.android.corruption_whistleblower_app.Model;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Post {


    public String report;
    public String badgeNo;
    public String image;
    public String location;
    public String name;
    public String email;
    public String caseSerialNo ;
    public String date;
    public Map<String, Boolean> stars = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)

    }

    public Post(String report, String badgeNo, String image, String location, String name, String email,String caseSerialNo,String date) {

        this.report = report;
        this.badgeNo = badgeNo;
        this.image = image;
        this.location = location;
        this.name = name;
        this.email = email;
        this.date=date;
        this.caseSerialNo=caseSerialNo;

    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("report", report);
        result.put("badgeNo", badgeNo);
        result.put("image", image);
        result.put("location", location);
        result.put("name", name);
        result.put("email", email);
        result.put("caseSerialNo", caseSerialNo);
        result.put("date", date);

        return result;
    }
    // [END post_to_map]


}
