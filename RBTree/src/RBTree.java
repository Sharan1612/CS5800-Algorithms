public class RBTree<T extends Comparable<T>> implements RBTreeOperations<T> {

  RBTreeNodeOperations<T> root;

  public RBTree() {
    root = new NilNode<>(null);
  }

  public void insert(T data) {
    insertHelp(data);
  }

  private void insertHelp(T data) {
    RBTreeNodeOperations<T> y = new NilNode<>(null);
    RBTreeNodeOperations<T> x = root;

    while(!x.isNill()) {
      y = x;
      if (data.compareTo(x.getKey()) < 0) {
        x = x.leftChild();
      } else {
        x = x.rightChild();
      }
    }
    RBTreeNodeOperations<T> node = new RBTreeNode<>(data);
    node.setParent(y);
    if (y.isNill()) {
      root = node;
    } else if (data.compareTo(y.getKey()) < 0) {
      y.setLeft(node);
    } else {
      y.setRight(node);
    }
    node.setColor(Color.RED);

    insertFixUp(node);
  }

  private void insertFixUp(RBTreeNodeOperations<T> z) {
    RBTreeNodeOperations<T> y;

    while (z.getParent().getColor().equals(Color.RED)) {
      if (!z.getParent().isNill() && z.getParent() == z.getParent().getParent().leftChild()) {
        y = z.getParent().getParent().rightChild();
        if (y.getColor().equals(Color.RED)) {
          z.getParent().setColor(Color.BLACK);
          y.setColor(Color.BLACK);
          z.getParent().getParent().setColor(Color.RED);
          z = z.getParent().getParent();
        }
        else {
          if (z == z.getParent().rightChild()) {
            z = z.getParent();
            leftRotate(z);
          }
          z.getParent().setColor(Color.BLACK);
          z.getParent().getParent().setColor(Color.RED);
          rightRotate(z.getParent().getParent());
        }
      } else {
        y = z.getParent().getParent().leftChild();
        if (y.getColor().equals(Color.RED)) {
          z.getParent().setColor(Color.BLACK);
          y.setColor(Color.BLACK);
          z.getParent().getParent().setColor(Color.RED);
          z = z.getParent().getParent();
        }
        else {
          if (z == z.getParent().leftChild()) {
            z = z.getParent();
            rightRotate(z);
          }
          z.getParent().setColor(Color.BLACK);
          z.getParent().getParent().setColor(Color.RED);
          leftRotate(z.getParent().getParent());
        }
      }
    }
    root.setColor(Color.BLACK);
  }

  private void leftRotate(RBTreeNodeOperations<T> x) {
    if (!x.isNill() && !x.rightChild().isNill()) {
      RBTreeNodeOperations<T> y = x.rightChild();
      x.setRight(y.leftChild());
      if (!y.isNill()) {
        y.leftChild().setParent(x);
      }
      y.setParent(x.getParent());
      if (x.getParent().isNill()) {
        root = y;
      } else if (x == x.getParent().leftChild()) {
        x.getParent().setLeft(y);
      } else {
        x.getParent().setRight(y);
      }
      y.setLeft(x);
      x.setParent(y);
    }
  }

  private void rightRotate(RBTreeNodeOperations<T> y) {
    if (!y.isNill() && !y.leftChild().isNill()) {
      RBTreeNodeOperations<T> x = y.leftChild();
      y.setLeft(x.rightChild());
      if (!x.isNill()) {
        x.rightChild().setParent(y);
      }
      x.setParent(y.getParent());
      if (y.getParent().isNill()) {
        root = x;
      } else if (y == y.getParent().leftChild()) {
        y.getParent().setLeft(x);
      } else {
        y.getParent().setRight(x);
      }
      x.setRight(y);
      y.setParent(x);
    }
  }

  @Override
  public RBTreeNodeOperations<T> search(T key) {
    RBTreeNodeOperations<T> x = root;
    while (!x.isNill()) {
      if (x.getKey().equals(key)) {
        return x;
      } else if (key.compareTo(x.getKey()) < 0) {
        x = x.leftChild();
      } else {
        x = x.rightChild();
      }
    }
    return null;
  }

  @Override
  public RBTreeNodeOperations<T> min(RBTreeNodeOperations<T> x) {
    if(x == null) {
      x = root;
    }
    while (!x.leftChild().isNill()) {
      x = x.leftChild();
    }
    return x;
  }

  @Override
  public RBTreeNodeOperations<T> max(RBTreeNodeOperations<T> x) {
    if(x == null) {
      x = root;
    }
    while (!x.rightChild().isNill()) {
      x = x.rightChild();
    }
    return x;
  }

  @Override
  public RBTreeNodeOperations<T> successor(RBTreeNodeOperations<T> x) {
    if (root.isNill()) {
      return null;
    }
    if (!x.rightChild().isNill()) {
      return min(x.rightChild());
    }
    RBTreeNodeOperations<T> y = x.getParent();
    while (!y.isNill() && y == x.getParent().rightChild()) {
      x = y;
      y = y.getParent();
    }
    return y;
  }

  @Override
  public RBTreeNodeOperations<T> predecessor(RBTreeNodeOperations<T> x) {
    if (root.isNill()) {
      return null;
    }
    if (!x.leftChild().isNill()) {
      return max(x.leftChild());
    }
    RBTreeNodeOperations<T> y = x.getParent();
    while (!y.isNill() && y == x.getParent().leftChild()) {
      x = y;
      y = y.getParent();
    }
    return y;
  }

  @Override
  public void sort() {
    inorder(root);
  }

  private void inorder(RBTreeNodeOperations<T> x) {
    if (x.isNill()) {
      return;
    }
    inorder(x.leftChild());
    System.out.print(x.getKey() + "(" + x.getColor() + ")" + "-->");
    inorder(x.rightChild());
  }

  private void transplant(RBTreeNodeOperations<T> u, RBTreeNodeOperations<T> v) {
    if (u.getParent().isNill()) {
      root = v;
    } else if (u == u.getParent().leftChild()) {
      u.getParent().setLeft(v);
    } else {
      u.getParent().setRight(v);
    }
    v.setParent(u.getParent());
  }

  private void deleteFixUp(RBTreeNodeOperations<T> x) {
    RBTreeNodeOperations<T> w;

    while(x != root && x.getColor().equals(Color.BLACK)) {
      if(x == x.getParent().leftChild()) {
        w = x.getParent().rightChild();
        if(w.getColor().equals(Color.RED)) {
          w.setColor(Color.BLACK);
          x.getParent().setColor(Color.RED);
          leftRotate(x.getParent());
          w = x.getParent().rightChild();
        }
        if(w.leftChild().getColor().equals(Color.BLACK) && w.rightChild().getColor().equals(Color.BLACK)) {
          w.setColor(Color.RED);
          x = x.getParent();
        } else {
          if(w.rightChild().getColor().equals(Color.BLACK)) {
            w.leftChild().setColor(Color.BLACK);
            w.setColor(Color.RED);
            rightRotate(w);
            w = x.getParent().rightChild();
          }
          w.setColor(x.getParent().getColor());
          x.getParent().setColor(Color.BLACK);
          w.rightChild().setColor(Color.BLACK);
          leftRotate(x.getParent());
          x = root;
        }
      } else {
        w = x.getParent().leftChild();
        if(w.getColor().equals(Color.RED)) {
          w.setColor(Color.BLACK);
          x.getParent().setColor(Color.RED);
          rightRotate(x.getParent());
          w = x.getParent().leftChild();
        }
        if(w.rightChild().getColor().equals(Color.BLACK) && w.leftChild().getColor().equals(Color.BLACK)) {
          w.setColor(Color.RED);
          x = x.getParent();
        } else {
          if(w.rightChild().getColor().equals(Color.BLACK)) {
            w.rightChild().setColor(Color.BLACK);
            w.setColor(Color.RED);
            leftRotate(w);
            w = x.getParent().leftChild();
          }
          w.setColor(x.getParent().getColor());
          x.getParent().setColor(Color.BLACK);
          w.leftChild().setColor(Color.BLACK);
          rightRotate(x.getParent());
          x = root;
        }
      }
    }
    x.setColor(Color.BLACK);
  }

  public void delete(T key) {
    RBTreeNodeOperations<T> z = search(key);

    RBTreeNodeOperations<T> y = z;
    RBTreeNodeOperations<T> x;
    Color orginalYColor = y.getColor();

    if(z.leftChild().isNill()) {
      x = z.rightChild();
      transplant(z, z.rightChild());
    } else if(z.rightChild().isNill()) {
      x = z.leftChild();
      transplant(z, z.leftChild());
    } else {
      y = min(z.rightChild());
      orginalYColor = y.getColor();
      x = y.rightChild();
      if(y.getParent() == z) {
        x.setParent(y);
      } else {
        transplant(y, y.rightChild());
        y.setRight(z.rightChild());
        y.rightChild().setParent(y);
      }
      transplant(z,y);
      y.setLeft(z.leftChild());
      y.leftChild().setParent(y);
      y.setColor(z.getColor());
    }
    if(orginalYColor.equals(Color.BLACK)) {
      deleteFixUp(x);
    }
  }
}
