A,B = map(int, input().split())

# A는 갯수
# B는 평균값
# C / A = 결과값 if 소수점이 있으면 +1 ->  = B

print((A*B)-A + 1)

