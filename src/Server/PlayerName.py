def funct():
    name = input("Enter Your Name:")
    list=[]
    with open("playerlist.txt", "r") as inputt:
        with open("playerlist.txt", "a") as output:
            content = [x.strip("\n") for x in inputt.readlines()]

            if name in content:
                print("NAME TAKEN")
                funct()
            else:
                print("GOOD TO GO")
                output.write(name+"\n")

funct()
