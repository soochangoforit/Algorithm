x ,y , w , h = map(int, input().split())


list = [x , h-y , w-x , y]

print(min(list))