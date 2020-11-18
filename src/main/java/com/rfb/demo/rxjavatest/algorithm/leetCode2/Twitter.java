package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import com.rfb.demo.rxjavatest.annotation.Test;

import java.util.*;

public class Twitter {

    private static class Tweet{
        int id;
        int time;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    private Map<Integer, Set<Integer>> followMap = new HashMap<>();
    private Map<Integer, List<Tweet>> tweetMap = new HashMap<>();
    private int time = 0;

    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        List<Tweet> set = tweetMap.get(userId);
        if(set == null){
            set = new ArrayList<>();
            tweetMap.put(userId, set);
        }
        set.add(new Tweet(tweetId, time));
        time++;
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> ids = new HashSet<>();
        ids.add(userId);
        Set<Integer> follows = followMap.get(userId);
        if(follows != null){
            ids.addAll(follows);
        }

        Map<Integer, Integer> indexMap = new HashMap<>();
        for(Integer id : ids){
            List<Tweet>tweets = tweetMap.get(id);
            if(tweets != null){
                indexMap.put(id, tweets.size()-1);
            }else{
                indexMap.put(id, -1);
            }
        }

        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < 10; i++){

            int max = Integer.MIN_VALUE;
            int maxId = 0;
            int maxIndex = 0;
            int maxTweetId = 0;
            for(Integer id : ids){

                int index = indexMap.get(id);
                List<Tweet> tweets = tweetMap.get(id);

                if(index == -1){
                    continue;
                }

                Tweet tweet = tweets.get(index);
                if(tweet.time > max){
                    max = tweet.time;
                    maxTweetId = tweet.id;
                    maxId = id;
                    maxIndex = index;
                }
            }

            if(max == Integer.MIN_VALUE){
                break;
            }

            indexMap.put(maxId, maxIndex-1);
            result.add(maxTweetId);
        }

        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> set = followMap.get(followerId);
        if(set == null){
            set = new HashSet<>();
            followMap.put(followerId, set);
        }
        set.add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = followMap.get(followerId);
        if(set == null){
            set = new HashSet<>();
            followMap.put(followerId, set);
        }
        set.remove(followeeId);
    }

    public static void test(){
        Twitter twitter = new Twitter();

// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        twitter.getNewsFeed(1);

// 用户1关注了用户2.
        twitter.follow(1, 2);

// 用户2发送了一个新推文 (推文id = 6).
        twitter.postTweet(2, 6);

// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
// 推文id6应当在推文id5之前，因为它是在5之后发送的.
        twitter.getNewsFeed(1);

// 用户1取消关注了用户2.
        twitter.unfollow(1, 2);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
// 因为用户1已经不再关注用户2.
        twitter.getNewsFeed(1);
    }
}
