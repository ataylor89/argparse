import sys

def parse(path):
    key = []
    with open(path, 'r') as file:
        for index, line in enumerate(file):
            linenum = index + 1
            try:
                tokens = line.split()
                n, e, d = int(tokens[0][2:]), int(tokens[1][2:]), int(tokens[2][2:])
                key.append((n, e, d))
            except Exception as err:
                print(f'Error parsing key file at line {linenum}')
                sys.exit(1)
    return key
