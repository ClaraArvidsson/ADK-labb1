public class Tree {
    private Root root;
    public Tree(){
        root = new Root();
    }
    
    private interface Node{
        public String toString();
    }

    private class Root{
        private int size;
        private Branch start;

        public Root(){
            size = 0;
        }

        public String toString(){
            return start.toString();
        }
    }

    private class Branch implements Node{
        private int maxinsubtree;
        private Node left;
        private Node right;

        public Branch(int height, int index, int value){
            maxinsubtree = value;
            int bit = (index << (height-1)) & 1;
            if (height == 1){
                if (bit == 0){
                    left = new Leaf(value);
                } else if (bit == 1){
                    right = new Leaf(value);
                }
            } else {
                Branch newBranch = new Branch(height-1, index, value);
                if (bit == 0){
                    left = newBranch;
                } else if (bit == 1){
                    right = newBranch;
                }
            }        
        }

        public String toString(){
            return "(" + left.toString() + " " + right.toString() + ")";
        }
    }

    private class Leaf implements Node{
        private final int value;

        public Leaf(int value){
            this.value = value;
        }

        public String toString(){
            return Integer.toString(value);
        }
    }

    public String toString(){
        return root.toString();
    }
}


