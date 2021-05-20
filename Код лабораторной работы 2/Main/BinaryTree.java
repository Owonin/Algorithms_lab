package Main;

import java.util.TreeSet;

public class BinaryTree {

    public static class Node {
        private int value; // ключ узла
        private Node leftChild; // Левый узел потомок
        private Node rightChild; // Правый узел потомок


        public int getValue() {
            return this.value;
        }

        public void setValue(final int value) {
            this.value = value;
        }

        public Node getLeftChild() {
            return this.leftChild;
        }

        public void setLeftChild(final Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return this.rightChild;
        }

        public void setRightChild(final Node rightChild) {
            this.rightChild = rightChild;
        }
    }

    private Node rootNode; // корневой узел

    public BinaryTree() { // Пустое дерево
        rootNode = null;
    }

    public BinaryTree(int[] arr){
        for (int i : arr)
            insert(i);
    }

    public void find(int value) {
        Node currentNode = rootNode;
        int comparisonCount = 1;

        while (currentNode != null) {
            if(currentNode.getValue() == value) {
                comparisonCount++;
                System.out.println("Бинарное дерево. Индификатор найден. Количество проверок: " + comparisonCount);
                break;
            }
            if (value < currentNode.getValue()) {
                comparisonCount++;
                currentNode = currentNode.getLeftChild();
            } else {
                comparisonCount++;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) {
                comparisonCount++;
                System.out.println("Бинарное дерево. Индификатор не найден. Количество проверок: " + comparisonCount);
            }
        }
    }

    public void insert(int value) {
        Node newNode = new Node();
        newNode.setValue(value);
        if (rootNode == null) {
            rootNode = newNode;
        } else {
            Node currentNode = rootNode;
            Node parentNode;
            while (true)
                {
                    parentNode = currentNode;
                    if (value == currentNode.getValue()) {
                        return;
                    } else if (value < currentNode.getValue()) {
                        currentNode = currentNode.getLeftChild();
                        if (currentNode == null) {
                            parentNode.setLeftChild(newNode);
                            return;
                        }
                    } else {
                        currentNode = currentNode.getRightChild();
                        if (currentNode == null) {
                            parentNode.setRightChild(newNode);
                            return;
                        }
                    }
                }
            }
        }

        public boolean delete(int value)
        {
            Node currentNode = rootNode;
            Node parentNode = rootNode;
            boolean isLeftChild = true;
            while (currentNode.getValue() != value) {
                parentNode = currentNode;
                if (value < currentNode.getValue()) {
                    isLeftChild = true;
                    currentNode = currentNode.getLeftChild();
                } else {
                    isLeftChild = false;
                    currentNode = currentNode.getRightChild();
                }
                if (currentNode == null)
                    return false;
            }

            if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
                if (currentNode == rootNode)
                    rootNode = null;
                else if (isLeftChild)
                    parentNode.setLeftChild(null);
                else
                    parentNode.setRightChild(null);
            } else if (currentNode.getRightChild() == null) {
                if (currentNode == rootNode)
                    rootNode = currentNode.getLeftChild();
                else if (isLeftChild)
                    parentNode.setLeftChild(currentNode.getLeftChild());
                else
                    parentNode.setRightChild(currentNode.getLeftChild());
            } else if (currentNode.getLeftChild() == null) {
                if (currentNode == rootNode)
                    rootNode = currentNode.getRightChild();
                else if (isLeftChild)
                    parentNode.setLeftChild(currentNode.getRightChild());
                else
                    parentNode.setRightChild(currentNode.getRightChild());
            } else {
                Node heir = receiveHeir(currentNode);
                if (currentNode == rootNode)
                    rootNode = heir;
                else if (isLeftChild)
                    parentNode.setLeftChild(heir);
                else
                    parentNode.setRightChild(heir);
            }
            return true;
        }


        private Node receiveHeir(Node node) {
            Node parentNode = node;
            Node heirNode = node;
            Node currentNode = node.getRightChild();
            while (currentNode != null)
            {
                parentNode = heirNode;
                heirNode = currentNode;
                currentNode = currentNode.getLeftChild();
            }
            if (heirNode != node.getRightChild())
            {
                parentNode.setLeftChild(heirNode.getRightChild());
                heirNode.setRightChild(node.getRightChild());
            }
            return heirNode;
        }
    }

