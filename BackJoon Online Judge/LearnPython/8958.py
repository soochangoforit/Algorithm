# https://www.acmicpc.net/problem/8958

case = int(input())

total = 0
temp = 1

for _ in range(case):
    text = input()
    for i in range(len(text)):
        if text[i] == "O":

            total += temp
            temp += 1
        else:
            temp = 1
            continue
    
    print(total)
    total = 0
    temp = 1

