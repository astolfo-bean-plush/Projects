"""
#366
Given two strings of letters, determine whether the second can be made from the first by removing one letter.
The remaining letters must stay in the same order.
ex. funnel("leave", "eave") => true
    funnel("sleet", "lets") => false
"""


def get_strings():
    str1 = input("Enter string 1: ")
    str2 = input("Enter string 2: ")
    return str1, str2


def funnel(str1, str2):
    str2_lst = list(str2)
    for i in range(0, len(str1)):
        str1_lst = list(str1)
        del str1_lst[i]
        if str1_lst == str2_lst:
            return True
    return False


def main():
    str1, str2 = get_strings()
    print(funnel(str1, str2))


main()
