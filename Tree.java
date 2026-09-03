public class Tree {
    private int size;
    private int height;
    private Branch branch;

    public Tree() {
        size = 0;
        height = 0;
    }

    public Tree(Tree oldTree, int i, int value) {
        size = oldTree.size + 1;
        int newHeight = i < 2 ? 1 : (int) (Math.log(i) / Math.log(2)) + 1;
        height = Math.max(oldTree.height, newHeight);
        if (oldTree.branch == null) {
            branch = new Branch(height, i, value);
        } else if (oldTree.height < height) {
            Branch leftBranch = oldTree.branch.expand(height - 1);
            Branch rightBranch = new Branch(height - 1, i, value);
            branch = new Branch(Math.max(leftBranch.maxinsubtree, rightBranch.maxinsubtree), height, leftBranch,
                    rightBranch);
        } else {
            branch = new Branch(oldTree.branch, height, i, value);
        }
    }

    private interface Node {
        public String toString();

        public int getValue(int i);
    }

    private class Branch implements Node {
        private int maxinsubtree;
        private int height;
        private Node left;
        private Node right;

        public Branch(int maxinsubtree, int height, Node left, Node right) {
            this.maxinsubtree = maxinsubtree;
            this.height = height;
            this.left = left;
            this.right = right;
        }

        public Branch(int height, int i, int value) {
            this.height = height;
            maxinsubtree = value;
            int bit = (i >> (height - 1)) & 1;
            if (height <= 1) {
                if (bit == 0) {
                    left = new Leaf(value);
                } else {
                    right = new Leaf(value);
                }
            } else {
                Branch newBranch = new Branch(height - 1, i, value);
                if (bit == 0) {
                    left = newBranch;
                } else {
                    right = newBranch;
                }
            }
        }

        public Branch(Branch oldBranch, int height, int i, int value) {
            this.height = height;
            int bit = (i >> (height - 1)) & 1;
            if (height <= 1) {
                if (bit == 0) {
                    left = new Leaf(value);
                    right = (Leaf) oldBranch.right;
                } else {
                    right = new Leaf(value);
                    left = (Leaf) oldBranch.left;
                }

                int leftMaxValue = left != null ? ((Leaf) left).value : 0;
                int rightMaxValue = right != null ? ((Leaf) left).value : 0;

                maxinsubtree = Math.max(leftMaxValue, rightMaxValue);
            } else {
                if (bit == 0) {
                    if (oldBranch.right != null)
                        right = oldBranch.right;
                    if (oldBranch.left == null) {
                        left = new Branch(height - 1, i, value);
                    } else {
                        left = new Branch((Branch) oldBranch.left, height - 1, i, value);
                    }
                } else {
                    if (oldBranch.left != null)
                        left = oldBranch.left;
                    if (oldBranch.right == null) {
                        right = new Branch(height - 1, i, value);
                    } else {
                        right = new Branch((Branch) oldBranch.right, height - 1, i, value);
                    }
                }

                int leftMaxValue = left != null ? ((Branch) left).maxinsubtree : 0;
                int rightMaxValue = right != null ? ((Branch) right).maxinsubtree : 0;

                maxinsubtree = Math.max(leftMaxValue, rightMaxValue);
            }
        }

        private Branch expand(int targetHeight) {
            if (targetHeight <= height) {
                return this;
            } else {
                return (new Branch(maxinsubtree, height + 1, this, null)).expand(targetHeight);
            }
        }

        public int getValue(int i) {
            int bit = (i >> (height - 1)) & 1;
            if (height <= 1) {
                if (bit == 0) {
                    return left != null ? left.getValue(0) : 0;
                } else {
                    return right != null ? right.getValue(0) : 0;
                }
            } else {
                if (bit == 0) {
                    return left != null ? left.getValue(i) : 0;
                } else {
                    return right != null ? right.getValue(i) : 0;
                }
            }
        }

        public String toString() {
            String leftString = left != null ? left.toString() : "null";
            String rightString = right != null ? right.toString() : "null";
            return "(" + leftString + " " + rightString + ")";
        }
    }

    private class Leaf implements Node {
        private final int value;

        public Leaf(int value) {
            this.value = value;
        }

        public String toString() {
            return Integer.toString(value);
        }

        public int getValue(int i) {
            return value;
        }
    }

    public String toString() {
        return branch.toString();
    }

    public static Tree newarray() {
        return new Tree();
    }

    public static Tree set(Tree a, int i, int value) {
        return new Tree(a, i, value);
    }

    public static int get(Tree a, int i) {
        return a.branch.getValue(i);
    }

    public static void main(String[] args) {
        Tree tree = newarray();
        tree = set(tree, 0, 10);
        tree = set(tree, 1, 20);
        tree = set(tree, 0, 30);
        tree = set(tree, 4, 40);
        System.out.println(tree.toString());

    }
}
