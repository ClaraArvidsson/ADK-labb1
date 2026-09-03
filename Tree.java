public class Tree {
    private int size;
    private int height;
    private Branch branch;

    public Tree(){
        size = 0;
        height = 0;
    }

    public Tree(Tree tree, int i, int value){
        // int height;
        if (i < 2){
           height = 1; 
        } else {
            height = (int) (Math.log(i)/Math.log(2)) + 1;
        }
        branch = new Branch(height, i, value);
    }

    
    private interface Node{
        public int getHeight();
        public int getValue();
        public String toString();
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
                } else if (bit == 1){
                    right = new Leaf(value);
                }
            } else {
                Branch newBranch = new Branch(height-1, i, value);
                if (bit == 0){
                    left = newBranch;
                } else if (bit == 1){
                    right = newBranch;
                }
            }        
        }

        public int getHeight(){
            return height;
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

        public int getValue(){
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

    // public static int get(Tree a, int i){
    //     int height = a.getHeight();

    //     int bit = (i >> (height-1)) & 1;
    //     if (height <= 1){
    //         int value = bit == 0 ? right.getValue() : left.getValue();
    //     }
    // }

    public static int getNodeValue(Node a, int i){
        int height = a.getHeight();
        
        int bit = (i >> (height-1)) & 1;
        if (height <= 0){
            return a.getValue();
        }
    }

    public static void main(String[] args){
        Tree tree = newarray();
        tree = set(tree, 5, 12);
        System.out.println(tree.toString());
    }
}



