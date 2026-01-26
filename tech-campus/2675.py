import sys

input = sys.stdin.readline
n_lines = int(input().strip())
for _ in range(n_lines):
  answer = []
  
  n_, word = input().split()
  n = int(n_)
  
  for letter in word:
    answer.append(letter * n)
  
  print("".join(answer))