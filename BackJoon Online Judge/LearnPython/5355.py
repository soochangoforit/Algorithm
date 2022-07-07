T = int(input())

for i in range(T):
    mars_math = input().split(' ')

    num = float(mars_math[0])

    operators = mars_math[1:]

    for operator in operators:
        if operator == '@':
            num *= 3
        elif operator == '%':
            num += 5
        else:
            num -= 7
    
    print(format(num, ".2f"))

