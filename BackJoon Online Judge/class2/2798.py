# https://www.acmicpc.net/problem/2798

# count , total = map(int , input().split())

# cards = list(map(int, input().split()))

# result = 0

# for i in range(count):
#     for k in range(i+1,count):
#         for j in range(k+1,count):
#             sum_cards=cards[i] + cards[k] + cards[j]
#             if (sum_cards > result and total >= sum_cards):
#                 result = sum_cards

# print(result)




from itertools import combinations


card_num, target_num = map(int, input().split())
card_list = list(map(int, input().split()))

biggest_num = 0

for cards in combinations(card_list, 3):
    temp_sum = sum(cards)
    if biggest_num < temp_sum <= target_num:
        biggest_num = temp_sum

print(biggest_num)
            