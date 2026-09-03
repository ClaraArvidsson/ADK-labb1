public class Tree {
    private int size;
    private int height;
    private Branch branch;

    public Tree(){
        size = 0;
        height = 0;
    }

    public Tree(Tree tree, int i, int value){
        size = tree.size + 1;
        int newHeight;
        if (i < 2){
           newHeight = 1; 
        } else {
            newHeight = (int) (Math.log(i)/Math.log(2)) + 1;
        }
        height = Math.max(tree.height, newHeight);
        branch = new Branch(tree.branch, height, i, value);
    }

    
    private interface Node{
        public String toString();
        public int getValue(int i);
    }

    private class Branch implements Node{
        private int maxinsubtree;
        private int height;
        private Node left;
        private Node right;

        public Branch(int height, int i, int value){
            this.height = height;
            maxinsubtree = value;
            int bit = (i >> (height-1)) & 1;
            if (height <= 1){
                if (bit == 0){
                    left = new Leaf(value);
                } else {
                    right = new Leaf(value);
                }
            } else {
                Branch newBranch = new Branch(height-1, i, value);
                if (bit == 0){
                    left = newBranch;
                } else {
                    right = newBranch;
                }
            }        
        }

        public Branch(Branch oldBranch, int height, int i, int value){
            this.height = height;
            maxinsubtree = value;
            int bit = (i >> (height-1)) & 1;
            if (height <= 1){
                if (bit == 0){
                    left = new Leaf(value);
                    right = oldBranch.right;
                } else {
                    right = new Leaf(value);
                    left = oldBranch.left;
                }
            } else {
                if (bit == 0){
                    left = new Branch((Branch) oldBranch.left, height-1, i, value);
                    right = oldBranch.right;

                } else {
                    right = new Branch((Branch) oldBranch.right, height-1, i, value);
                    left = oldBranch.left;
                }
            }        
        }

        public int getHeight(){
            return height;
        }

        public int getValue(int i){
            int bit = (i >> (height-1)) & 1;
            if (height <= 1){
                if (bit == 0){
                    return left != null ? left.getValue(0) : 0;
                } else {
                    return right != null ? right.getValue(0) : 0;
                }
            } else {
                if (bit == 0){
                    return left != null ? left.getValue(i) : 0;
                } else {
                    return right != null ? right.getValue(i) : 0;
                }
            }  
        }

        public String toString(){
            String leftString = left != null ? left.toString() : "null";
            String rightString = right != null ? right.toString() : "null";
            return "(" + leftString + " " + rightString + ")";
        }
    }

    private class Leaf implements Node{
        private final int value;

        public Leaf(int value){
            this.value = value;
        }

        public int getHeight(){
            return 0;
        }

        public String toString(){
            return Integer.toString(value);
        }

        public int getValue(int i){
            return value;
        }
    }

    public String toString(){
        return branch.toString();
    }

    public static Tree newarray(){
        return new Tree();
    }

    public static Tree set(Tree a, int i, int value){
        return new Tree(a, i, value);
    }



    private int getHeight(){
        return height;
    }

    public static int get(Tree a, int i){
        return a.branch.getValue(i);
    }


    public static void main(String[] args){
        Tree tree = newarray();
        tree = set(tree, 5, 12);
        System.out.println(tree.toString());

        System.out.println(get(tree, 5));
    }
}



