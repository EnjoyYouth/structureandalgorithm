package com.structure.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 卡bin查询，前缀树实现demo
 * 
 * @author wangsg3
 *
 */
public class Trie {

	/**
	 * 前缀树根节点
	 */
	private TrieNode rootNode;

	/**
	 * 初始化数据
	 */
	public Trie() {
		super();
		rootNode = new TrieNode();
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		if (word == null || word.isEmpty()) {
			return;
		}

		TrieNode node = this.rootNode;
		for (int i = 0; i < word.length(); i++) {
			char key = word.charAt(i);
//			包含该字符时则继续深入子节点
			if (node.childMap.containsKey(key)) {
				node = node.childMap.get(key);
			}
//			节点不包含该字符时则插入
			else {
				node.childMap.put(key, new TrieNode());
				node = node.childMap.get(key);
			}

//			最后一个字符所在节点的记录为完整字符串
			if (i == word.length() - 1 && !node.isWorld) {
				node.isWorld = true;
			}
		}
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		if (word == null || word.isEmpty()) {
			return false;
		}

		TrieNode node = this.rootNode;
		for (int i = 0; i < word.length(); i++) {
			char key = word.charAt(i);
			if (!node.childMap.containsKey(key)) {
				return false;
			} else {
				node = node.childMap.get(key);
			}
		}

		return node.isWorld;
	}

	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 */
	public boolean startsWith(String prefix) {
		if (prefix == null || prefix.isEmpty()) {
			return false;
		}

		TrieNode node = this.rootNode;
		for (int i = 0; i < prefix.length(); i++) {
			char key = prefix.charAt(i);
			if (!node.childMap.containsKey(key)) {
				return false;
			} else {
				node = node.childMap.get(key);
			}
		}

		return true;
	}
	
	/**
	 * 前缀树子节点信息
	 * 
	 * @author wangsg3
	 *
	 */
	class TrieNode {
//		是否为完整单词
		boolean isWorld = false;

//		该节点对应的子节点
		Map<Character, TrieNode> childMap = new HashMap<Character, Trie.TrieNode>();
	}

}

