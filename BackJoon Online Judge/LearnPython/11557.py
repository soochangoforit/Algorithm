count = int(input())

for _ in range(count):
    case = int(input())
    name = ''
    max = 0

    for _a in range(case):
        a , b = input().split()
        b = int(b)

        if(b>max):
            name = a
            max = b
        
    print(f"{name}")


