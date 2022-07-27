# 한 마을에 모험가가 N명 있습니다. 모험가 길드에서는 N명의 모험가를 대상으로 ‘공포도’를 측정했는데, ‘공포도’가 높은 모험가는 쉽게 공포를 느껴 위험 상황에서 제대로 대처할 능력이 떨어집니다.
# 모험가 길드장인 동빈이는 모험가 그룹을 안전하게 구성하고자 공포도가 X인 모험가는 반드시 X명 이상으로 구성한 모험가 그룹에 참여해야 여행을 떠날 수 있도록 규정했습니다.
# 동빈이는 최대 몇개의 모험가 그룹을 만들 수 있을지 궁금합니다. N명의 모험가에 대한 정보가 주어졌을때, 여행을 떠날 수 있는 그룹 수의 최대값을 구하는 프로그램을 작성하시오
# 또한 몇 명의 모험가는 마을에 그대로 남아 있어도 되기 때문에, 모든 모험가를 특정한 그룹에 넣을 필요는 없습니다.

# 입력: 5
#     : 2 3 1 2 2 
# 출력 : 2

n = int(input())
data = list(map(int, input().split()))
data.sort()

result = 0 #총 그룹의 수
count = 0 # 현재 그룹에 포함된 모험가의 수

for i in data:
		count += 1  # 현재 그룹에 해당 모험가를 포함시키기 , 공포도 한장이 한명의 사람이다.
		if count >= i: # 카드에 적현 공포도에 맞게 사람이 있거나 혹은 공포도보다 더 많은 사람이 그룹에 속할 경우
				result += 1 # 총 그룹의 수 증가시켜서, 그룹을 결성시킨다.
				count = 0 # 현재 그룹에 포함된 모험가의 수 초기화

print(result) # 총 그룹의 수 출력