# def funct():
#     name = input("Enter Your Name:")
#     list=[]
#     with open("playerlist.txt", "r") as inputt:
#         with open("playerlist.txt", "a") as output:
#             content = [x.strip("\n") for x in inputt.readlines()]
#
#             if name in content:
#                 print("NAME TAKEN")
#                 funct()
#             else:
#                 print("GOOD TO GO")
#                 output.write(name+"\n")
#
# funct()



def funct(x):
    with open("playerlist.txt", "r") as inputt:
        with open("playerlist.txt", "a") as output:
            content = [x.strip("\n") for x in inputt.readlines()]

            if x in content:
                print("NAME TAKEN")
                funct()
            else:
                print("GOOD TO GO")
                output.write(x+"\n")


y="Ant"

# def playerleft(y):
#     with open("playerlist.txt", "r") as inputt:
#         lines=inputt.readlines()
#
#
#
#         with open("playerlist.txt", "w") as output:
#             for x in output:
#                 if x!=y+"\n":
#                     output.write(x)
#                     print("PLAYER DELETED")
#                     output.close()
#                 else:
#                     print("PLAYER NOT FOUND")
#                     playerleft(y)
# playerleft(y)
#
#
