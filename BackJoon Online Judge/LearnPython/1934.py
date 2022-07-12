count = int(input())

for i in range(count):
    a,b = map(int , input().split())

    A , B = a, b

    while B!=0:
        A = A % B
        A, B = B , A
    
    # A는 최대 공약수
    print(a//A * b//A * A)