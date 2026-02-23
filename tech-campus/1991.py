import sys
sys.setrecursionlimit(10**6)
inn = sys.stdin.readline
n = int(inn().strip())

graph = {}
for _ in range(n):
  p, left, right = inn().split()
  graph[p] = (left, right)

pre_ = []
in_ = []
post_ = []

def preorder(p, out):
  if p == ".":
    return
  left, right = graph[p]
  out.append(p)
  preorder(left, out)
  preorder(right, out)

def inorder(p, out):
  if p == ".":
    return
  left, right = graph[p]
  inorder(left, out)
  out.append(p)
  inorder(right, out)

def postorder(p, out):
  if p == ".":
    return
  left, right = graph[p]
  postorder(left, out)
  postorder(right, out)
  out.append(p)

preorder("A", pre_)
inorder("A", in_)
postorder("A", post_)

print("".join(pre_))
print("".join(in_))
print("".join(post_))
