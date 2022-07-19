# https://www.acmicpc.net/problem/15829

count = int(input())

alpha = input()

r = 31
M = 1234567891
result = 0

# 알파벳을 숫자로 바꾸는 작업 필요
# 그렇다면 우리는 알파벳을 숫자로 바꾸는 것을 먼저 해야한다.
# a 기준으로 아스키코드는 97이므로 96을 빼주면 된다.
# r = 31로주고 M을 1234567891로 준 뒤
# ∑alphabet * r ^ i Mod M을 하면된다. 


for i in range(len(alpha)):
    num = ord(alpha[i]) - 96 # a는 해쉬코드 97
    result += num*(r**i)
print(result%M)

