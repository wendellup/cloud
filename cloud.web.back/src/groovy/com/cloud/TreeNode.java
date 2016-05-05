package com.cloud;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: yzx Date: 13-8-7 Time: 下午3:40 To change
 * this template use File | Settings | File Templates.
 */
public class TreeNode
{
    private int id;
    private int pId;
    private String text;
    // 节点状态， 'open' 或 'closed'，默认是 'open'
    private String state;
    private String checked;
    // 自定义属性
    private String attributes;
    // 节点数组
    private List<TreeNode> children;
    private int depth;
    private String flag;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getpId()
    {
        return pId;
    }

    public void setpId(int pId)
    {
        this.pId = pId;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getChecked()
    {
        return checked;
    }

    public void setChecked(String checked)
    {
        this.checked = checked;
    }

    public String getAttributes()
    {
        return attributes;
    }

    public void setAttributes(String attributes)
    {
        this.attributes = attributes;
    }

    public List<TreeNode> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeNode> children)
    {
        this.children = children;
    }

    public int getDepth()
    {
        return depth;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }
}
