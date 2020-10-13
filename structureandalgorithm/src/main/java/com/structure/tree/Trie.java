package com.structure.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 卡bin查询，前缀树实现demo
 * 
 * @author wangsg3
 *
 */
public class Trie<V> {

	/**
	 * 前缀树根节点
	 */
	protected TrieNode<V> rootNode;

	/**
	 * 初始化数据
	 */
	public Trie() {
		super();
		rootNode = new TrieNode<V>();
	}

	/**
	 * 插入键值对
	 * 
	 * @param word  字符串键, word不可为null
	 * @param value 前缀树中需保留的数据
	 */
	public void insert(String word, V value) {
		if (word == null) {
			throw new NullPointerException();
		}
		TrieNode<V> node = this.rootNode;
		for (int i = 0; i < word.length(); i++) {
			char key = word.charAt(i);
//			包含该字符时则继续深入子节点
			if (node.childMap.containsKey(key)) {
				node = node.childMap.get(key);
			}
//			节点不包含该字符时则插入
			else {
				node.childMap.put(key, new TrieNode<V>());
				node = node.childMap.get(key);
			}
		}
//		最后一个字符所在节点的记录为完整字符串，如先前已存在则更新数据
		node.isWorld = true;
		node.data = value;
	}

	/** Returns if the word is in the trie. */
	public boolean contains(String word) {
		if (word == null) {
			return false;
		}

		TrieNode<V> node = this.rootNode;
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
	public boolean containsPrefix(String prefix) {
		if (prefix == null) {
			return false;
		}

		TrieNode<V> node = this.rootNode;
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
	 * 获取键所对应的值
	 * 
	 * @param key 键
	 * @return 键所对应的数据, key为null时返回null
	 */
	public V get(String key) {
		if (key == null) {
			return null;
		}

		TrieNode<V> node = this.rootNode;
		for (int i = 0; i < key.length(); i++) {
			char ch = key.charAt(i);
			if (!node.childMap.containsKey(ch)) {
				return null;
			} else {
				node = node.childMap.get(ch);
			}
		}

		return node.data;
	}

	/**
	 * 返回key在前缀树中的最长前缀
	 * 
	 * @param key
	 * @return 最长前缀字符串，key为null时返回空串
	 */
	public String getLongestPrefix(String key) {
		if (key == null || key.isEmpty()) {
			return "";
		}

		TrieNode<V> node = this.rootNode;
		int i = 0;
		for (; i < key.length(); i++) {
			char ch = key.charAt(i);
			if (!node.childMap.containsKey(ch)) {
				break;
			}
			node = node.childMap.get(ch);
		}
		return key.substring(0, i);
	}

	/**
	 * 返回key在前缀树中的最长完整前缀字符串
	 * 
	 * @param key
	 * @return 最长完整前缀字符串，key为null时返回空串
	 */
	public String getLongestExistsKey(String key) {
		if (key == null || key.isEmpty()) {
			return "";
		}

		TrieNode<V> node = this.rootNode;
		int end = 0;
		for (int i = 0; i < key.length(); i++) {
			char ch = key.charAt(i);
			if (!node.childMap.containsKey(ch)) {
				break;
			}
			node = node.childMap.get(ch);
//			更新下标值
			end = node.isWorld ? i + 1 : end;
		}
		return key.substring(0, end);
	}

	/**
	 * 前缀树子节点信息
	 * 
	 * @author wangsg3
	 *
	 */
	static class TrieNode<N> {
//		是否为完整单词
		boolean isWorld = false;

//		节点中缓存的数据
		N data;

//		该节点对应的子节点
		Map<Character, TrieNode<N>> childMap = new HashMap<Character, TrieNode<N>>();
	}

}
