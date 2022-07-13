# 최소 공배수 구하기 유클리드 알고리즘

a,b =map(int , input().split())

A , B = a , b

#  A에서 B를 나누는데 0이 되기전까지 나눈다.
while B!=0:
    # A에서 B를 나눈 나머지를 A로 다시 할당한다.
    A = A % B
    A , B = B , A 
# B가 0이 되는 순간 A는 최대공약수가 된다.

# 최소 공배수 구하기
lc = (a//A * b//A * A)

print(A) # 최대공약수 출력
print(lc)

