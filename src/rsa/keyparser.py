import sys

def parse(path):
    key = []
    with open(path, 'r') as file:
        for linenum, line in enumerate(file, start=1):
            n = e = d = None
            try:
                tokens = line.split()
                for token in tokens:
                    parts = token.split('=')
                    name, value = parts[0], int(parts[1])
                    if name == 'n':
                        n = value
                    elif name == 'e':
                        e = value
                    elif name == 'd':
                        d = value
                    else:
                        print(f'Unrecognized symbol at line {linenum}: {name}={value}')
                        sys.exit(1)
                key.append((n, e, d))
            except Exception as err:
                print(f'Error parsing key file at line {linenum}')
                sys.exit(1)
    return key
