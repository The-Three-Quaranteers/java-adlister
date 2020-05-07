package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
    // get a list of all ads for logged in user
    List<Ad> usersAds(User user);
    // get a specific ad by ad_id
    Ad getAdByID(long ad_id);
    //updates an ad in the db
    void updateAd(Ad ad);

    //search ads
//    List<Ad> searchedAds(String s, String c);
}
