package com.rfb.demo.rxjavatest.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/25 0025.
 */
public class TrieNode {

    private Map<Character, TrieNode> mSonMap = new HashMap<Character, TrieNode>();
    private int mLevel;
    private boolean mIsData;
    private String mData;

    public TrieNode(Map<Character, TrieNode> sonMap, int level, boolean isData, String data) {
        mSonMap = sonMap;
        mLevel = level;
        mIsData = isData;
        mData = data;
    }

    public void putNewSon(Character c, TrieNode trieNode){
        mSonMap.put(c, trieNode);
    }

    public TrieNode getSon(Character c){
        return mSonMap.get(c);
    }
}
