def main():
    with open("hamstr.in") as hamster:
        lines = hamster.readlines()

    all_food = int(lines[0])
    print(all_food)
    hum_amount = int(lines[1])
    print(hum_amount)

    hamster_map = []
    for elem in lines[2:]:
        hamster_map.append(list(map(int, elem.split())))
    print(hamster_map)

    summary = count_necessary_food(hamster_map)

    while all_food < summary:
        temp_hamsters = hamster_map[1:]
        min_summary = count_necessary_food(temp_hamsters)
        min_index = 0

        for j in range(1, hum_amount):
            if j != hum_amount - 1:
                temp_hamsters = hamster_map[:j] + hamster_map[j + 1:]
            else:
                temp_hamsters = hamster_map[:j]

            temp_summary = count_necessary_food(temp_hamsters)

            if temp_summary < min_summary:
                min_summary = temp_summary
                min_index = j

        del hamster_map[min_index]
        hum_amount -= 1
        summary = min_summary

    print(hum_amount)


def count_necessary_food(hamsters):
    summary = 0
    ham_amount = hamsters.__len__()
    for h in hamsters:
        form = h[0] + h[1] * (ham_amount - 1)
        summary = summary + form
    return summary


main()
