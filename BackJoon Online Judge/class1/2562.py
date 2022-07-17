a = []

for _ in range(9):
    b = int(input())
    a.append(b)

max = max(a)
print(max)
print(a.index(max) + 1)