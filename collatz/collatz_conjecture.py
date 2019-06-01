def get_collatz():
    return int(input("Enter an integer N where N > 1: "))


def main():
    step = 1
    n = get_collatz()
    num = n
    while n != 1:
        if n % 2 == 0:
            orig_n = n
            n /= 2
            print("Step {0:,}: {1:,.0f} / 2 = {2:,.0f}".format(step, orig_n, n))
        else:
            orig_n = n
            n = (n * 3) + 1
            print("Step {0:,}: ({1:,.0f} * 3) + 1 = {2:,.0f}".format(step, orig_n, n))
        step += 1
    print("The number {0:,} took {1} steps to reach 1.".format(num, step - 1))


main()
