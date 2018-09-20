package com.waben.stock.interfaces.dto.organization;

import java.util.List;

/**
 * 树节点
 * 
 * @author luomengan
 *
 */
public class TreeNode {

	/**
	 * 栏目ID
	 */
	private Long id;
	/**
	 * 栏目名称
	 */
	private String name;
	/**
	 * 层级
	 */
	private int level;
	/**
	 * 父栏目ID
	 */
	private Long pid;
	/**
	 * 是否展开
	 */
	private boolean open;

	private List<TreeNode> children;

	public TreeNode(Long id, String name, int level, Long pid, boolean open) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.pid = pid;
		this.open = open;
	}

	public TreeNode() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

}
