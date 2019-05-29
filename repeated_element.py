# In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.
# Return the element repeated N times.


def main():
    a = get_list()
    non_dupe_elements = []
    counter = 0
    for i in a:
        if i not in non_dupe_elements:
            non_dupe_elements.append(i)
            counter += 1
        else:
            print(i)


def get_list():
    print("Enter N digits, spaced, with only 1 digit repeating twice, ex. [2,2,3,5] or [1,2,5,6,8,8,12]")
    user_input = input()
    return user_input.split()


main()
