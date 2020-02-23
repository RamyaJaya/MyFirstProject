package com.tigers.demo;


/**
 * Burn the binary tree starting from the target node
 * Given a binary tree and target node. By giving the fire to the target node and fire starts to spread in a complete tree. The task is to print the sequence of the burning nodes of a binary tree.
 *
 * Rules for burning the nodes :
 *
 * Fire will spread constantly to the connected nodes only.
 * Every node takes the same time to burn.
 * A node burns only once.
 *
 * Input : 
 *                        12
 *                      /     \
 *                    13       10
 *                           /     \
 *                        14       15
 *                       /   \     /  \
 *                      21   24   22   23
 * target node = 14
 *
 * Output :
 * 14
 * 21, 24, 10
 * 15, 12
 * 22, 23, 13
 *
 */
public class BurnLeafNode
{
    public static void main( String[] args )
    {
        
        try {

            Node root = new Node();
            Node rightchild = new Node();
            Node againRight = new Node();
            Node againagainRight = new Node();
            root.setRight(rightchild);
            root.getRight().setRight(againRight);
            root.getRight().getRight().setRight(againagainRight);
            rightchild.setParent(root);
            againRight.setParent(rightchild);
            againagainRight.setParent(againRight);

            //ScheduledAudienceKafkaListener listener = new ScheduledAudienceKafkaListener();
            //listener.start();
            int i = burn(root, 1, false, false, true);
            System.out.print(i);
        } catch(Exception e) {
            System.out.print(e);
        }
    }

    public static int burn(Node n,
                           int myburnTime,
                           boolean skipLeftChildCall,
                           boolean skipRightChildCall,
                           boolean skipParentCall) {
        int leftSubTreeBurnTime = 0;
        if (n.getLeft() != null && !skipLeftChildCall) {

            leftSubTreeBurnTime = burn(n.getLeft(), myburnTime+1, false, false, true);
        }

        int rightSubTreeBurnTime = 0;
        if (n.getRight() != null && !skipRightChildCall) {

            rightSubTreeBurnTime = burn(n.getRight(), myburnTime+1, false, false, true);

        }

        int parentBurnTime = 0;
        if (n.getParent() != null && !skipParentCall) {
            //Check if I am the left child of my parent
            if (n.getParent().getLeft() != null && n.parent.getLeft() == n) {
                skipLeftChildCall = true;
            } //Check if I am the right child of my parent
            else if (n.getParent().getRight() != null && n.parent.getRight() == n) {
                skipRightChildCall = true;
            }

            parentBurnTime = burn(n.getParent(), myburnTime+1, skipLeftChildCall, skipRightChildCall, true);

        }

        if (leftSubTreeBurnTime >= rightSubTreeBurnTime && leftSubTreeBurnTime >= parentBurnTime && leftSubTreeBurnTime >= myburnTime) {

            return leftSubTreeBurnTime;
        }
        if (rightSubTreeBurnTime >= leftSubTreeBurnTime && rightSubTreeBurnTime >= parentBurnTime && rightSubTreeBurnTime >= myburnTime) {

            return rightSubTreeBurnTime;
        }
        if (myburnTime > parentBurnTime) {

            return myburnTime;
        }

        return parentBurnTime;

    }


}

