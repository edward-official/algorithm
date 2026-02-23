import sys

sys.setrecursionlimit(10**7)
data = sys.stdin.buffer.read().split()
pre = list(map(int, data))
n = len(pre)

idx = 0
out = []

def build(lo, hi):
  global idx
  if idx >= n:
    return
  x = pre[idx]
  if x <= lo or x >= hi:
    return

  idx += 1
  build(lo, x)
  build(x, hi)
  out.append(str(x))

build(-1, 10**6 + 1)
sys.stdout.write("\n".join(out))
