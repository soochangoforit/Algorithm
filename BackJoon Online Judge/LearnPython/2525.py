
H, M = map(int , input().split())
timer = int(input())

# timer의 입력값은 60을 넘어갈수 있다.
H += timer // 60 # 몫
M += timer % 60 # 나머지

if M >= 60 :
    H += 1
    M -= 60

if H >= 24 :
    H -= 24

print(H,M)