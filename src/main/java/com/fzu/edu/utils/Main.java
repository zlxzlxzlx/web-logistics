package com.fzu.edu.utils;

import com.fzu.edu.utils.Tree.TreeData.TreeData;
import com.fzu.edu.utils.Tree.TreeData.TreeNode;
import com.fzu.edu.utils.Tree.TreeSet;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //        生成树
        TreeData treeData = new TreeData();
        List<TreeNode> treeDatas = treeData.getTreeNodes();
        Object Tree = TreeSet.CreateTree(treeDatas, "fatherId", "id", null);
        System.out.println(Tree);

    }
}
