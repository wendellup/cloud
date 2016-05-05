package com.cloud

import com.cloud.common.Constants
import grails.converters.JSON

class TreeController {

    int appId = Constants.APP_ID;
    long loginUserId = Constants.LOGIN_USER_ID;
    def ParameterAppService parameterAppService;
    List<TreeNode> treeList = new ArrayList<TreeNode>();

    def index() {
        return "index";
    }

    def initTree() {
        List<TreeNode> node = new ArrayList<TreeNode>();
        if (params.fromGameChannel || params.fromCardChannel || params.fromClassifyChannel) {
            List<Integer> appIdList = parameterAppService.listAppParameterIdsByParentId(Constants.GAME_HALL_SDK);
            for (int appIdTemp : appIdList) {
                if ((params.fromGameChannel && (appIdTemp == Constants.GAME_HALL_SDK_RECOMMEND || appIdTemp == Constants.GAME_HALL_SDK_ONLINE_GAME)) || (params.fromCardChannel && appIdTemp == Constants.GAME_HALL_SDK_RECOMMEND_CARD_SET) || (params.fromClassifyChannel && appIdTemp == Constants.GAME_HALL_SDK_CLASSIFY_RECOMMEND)) {
                    TreeNode treeNode = getTreeNode(appIdTemp);
                    List<TreeNode> childNodeList = new ArrayList<TreeNode>();
                    List<Integer> childIdList = parameterAppService.listAppParameterIdsByParentId(appIdTemp);
                    for (int childId : childIdList) {
                        TreeNode childNode = getTreeNode(childId);
                        childNodeList.add(childNode);
                    }
                    treeNode.setChildren(childNodeList);
                    node.add(treeNode);
                }
            }
        } else if (params.fromBlogTag) {
            TreeNode rootNode = new TreeNode();
            rootNode.setId(800);
            rootNode.setpId(0);
            rootNode.setText("Blog根节点");
            List<ParameterApp> nodeList = new ArrayList<ParameterApp>();
            List<Integer> appIdList = parameterAppService.listAppParameterIdsByParentId(Constants.BLOG_ROOT_ID);
            for (int appIdTemp : appIdList) {
                if ((params.fromBlogTag && (appIdTemp == Constants.BLOG_ARTICLE_ID || appIdTemp == Constants.BLOG_SOFTWARE_ID))) {
                    TreeNode treeNode = getTreeNode(appIdTemp);
//                    List<TreeNode> childNodeList = new ArrayList<TreeNode>();
//                    List<Integer> childIdList = parameterAppService.listAppParameterIdsByParentId(appIdTemp);
//                    for (int childId : childIdList) {
//                        TreeNode childNode = getTreeNode(childId);
//                        childNodeList.add(childNode);
//                    }
//                    treeNode.setChildren(childNodeList);
//                    node.add(treeNode);
                    nodeList.add(treeNode);
                }
            }
            rootNode.setChildren(nodeList);
            node.add(rootNode);
        }
        render(node as JSON);
    }


    private TreeNode getTreeNode(int nodeId) {
        ParameterApp app = parameterAppService.queryParameterAppById(nodeId);
        TreeNode treeNode = null;
        if (null != app) {
            treeNode = new TreeNode();
            treeNode.setId(app.getId());
            treeNode.setpId(app.getParentId());
            treeNode.setDepth(app.getDepth());
            treeNode.setText(app.getName());
        }
        return treeNode;
    }
}
