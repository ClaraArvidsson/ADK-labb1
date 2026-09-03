public class Tree {
    private Root root;
    public Tree(){
        root = new Root();
    }
    
    private class Node{
    }

    private class Root{
        private int size;
        private Branch start;

        public Root(){
            size = 0;
        }
    }

    private class Branch extends Node{
        private int maxinsubtree;
        private Node left;
        private Node right;

        public Branch(int height, int index, int value){
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
    }

    private class Leaf extends Node{
        private final int value;

        public Leaf(int value){
            this.value = value;
        }
    }
}


