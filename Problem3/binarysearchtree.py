class Node():

    def __init__(self):
        self.data = None
        self.left = None
        self.right = None

    def set_data(self, data):
        self.data = data

    def add_child(self, child_node):
        """
        Adds a child node if no children already assigned to this node
        :param child_node: the node being added
        :return: True if node was added successfully, False otherwise
        """
        if self.data is None:
            self.set_data(child_node.data)
        elif child_node.data <= self.data:
            if self.left is None:
                self.left = child_node
                return True
            return False
        elif self.right is None:
            self.right = child_node
            return True
        return False

    def __repr__(self):
        s = str(self.data)
        if self.left is not None:
            s += " L:(" + str(self.left) + ")"
        if self.right is not None:
            s += " R:(" + str(self.right) + ")"
        return s

    def gen_queue(self):
        """
        Returns a queue of nodes in order starting from this one
        :return: A queue of nodes in order
        """
        queue = []
        if self.left is not None:
            for x in self.left.gen_queue():
                queue.append(x)

        queue.append(self)

        if self.right is not None:
            for x in self.right.gen_queue():
                queue.append(x)
        return queue

    def __iter__(self):
        self.queue = self.gen_queue()
        return self

    def __next__(self):
        if self.queue:
            return self.queue.pop(0).data
        else:
            raise StopIteration


class BinarySearchTree():

    def __init__(self, name=None, root=None):
        self.name = name
        self.root = root

    def add(self, child_node):
        if self.root is None:
            self.root = child_node
        else:
            pointer = self.root
            while (pointer is not None):
                if pointer.add_child(child_node):
                    break
                elif child_node.data <= pointer.data:
                    pointer = pointer.left
                else:
                    pointer = pointer.right

    def add_all(self, *args):
        for arg in args:
            child_node = Node()
            child_node.set_data(arg)
            self.add(child_node)

    def gen_queue(self, node):
        return Node.gen_queue(node)

    def __iter__(self):
        self.queue = self.gen_queue(self.root)
        return self

    def __next__(self):
        if self.queue:
            return self.queue.pop(0).data
        else:
            raise StopIteration

    def __repr__(self):
        return str(self.root)

