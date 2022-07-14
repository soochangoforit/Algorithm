# https://www.acmicpc.net/problem/10102



time = int(input())
text = input()

A = 0
B = 0

for i in text:
    if i == 'A':
        A += 1
    else:
        B += 1

if A > B :
    print("A")
elif B > A :
    print("B")
else:
    print("Tie")


