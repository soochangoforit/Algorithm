# 이진 트리를 만들고 전위, 후위, 중위 순회를 구현한다.

class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None

class Tree:
    def __init__(self):
        self.root = None

    def preorder(self, node):
        if node is None:
            return
        print(node.data, end='')
        self.preorder(node.left)
        self.preorder(node.right)

    def inorder(self, node):
        if node is None:
            return
        self.inorder(node.left)
        print(node.data, end='')
        self.inorder(node.right)

    def postorder(self, node):
        if node is None:
            return
        self.postorder(node.left)
        self.postorder(node.right)
        print(node.data, end='')

if __name__ == '__main__':
    tree = Tree()
    tree.root = Node('A')
    tree.root.left = Node('B')
    tree.root.right = Node('C')
    tree.root.left.left = Node('D')
    tree.root.left.right = Node('E')

    
    print('전위 순회:')
    tree.preorder(tree.root)
    print()
    
    print('중위 순회:')
    tree.inorder(tree.root)
    print()
    

    print('후위 순회:')
    tree.postorder(tree.root)
    print()
