import cv2 as image
import os
from tkinter import *
from PIL import ImageTk,Image
from tkinter import filedialog

def check(reff):
    max=0
    result="Not a bangladeshi currency"

    orb = image.ORB_create()
    kp1, des1 = orb.detectAndCompute(reff, None)

    bf = image.BFMatcher()




          ###-----for 2 tk-----
    #reading all data note
    path = "./Data/Image/Training/2"
    all_notes = []
    all_Note_Name = []
    noteList = os.listdir(path)

    for note_name in noteList:
        note = image.imread(f'{path}/{note_name}')
        all_notes.append(note)

    total=0
    i=0

    #finding feature from data note
    for note in all_notes:
        kp2, des2 = orb.detectAndCompute(note, None)

        matches = bf.knnMatch(des2, des1, k=2)

        good = []
        for m, n in matches:
            if m.distance < 0.7*n.distance:
                good.append([m])
        total+=len(good)
        i=i+1

    if max < total/i and total/i>=1:
        max = total/i
        result = "This is a bangladeshi 2 taka note."




          ###-----for 10 tk-----
    path = "./Data/Image/Training/10"
    all_notes = []
    all_Note_Name = []
    noteList = os.listdir(path)

    for note_name in noteList:
        note = image.imread(f'{path}/{note_name}')
        all_notes.append(note)

    total=0
    i=0

    for note in all_notes:
        kp2, des2 = orb.detectAndCompute(note, None)

        matches = bf.knnMatch(des2, des1, k=2)

        good = []
        for m, n in matches:
            if m.distance < 0.7*n.distance:
                good.append([m])
        total+=len(good)
        i=i+1

    if max < total/i and total/i>=1:
        max = total/i
        result = "This is a bangladeshi 10 taka note."




          ###-----for 20 tk-----
    #reading all data note
    path = "./Data/Image/Training/20"
    all_notes = []
    all_Note_Name = []
    noteList = os.listdir(path)

    for note_name in noteList:
        note = image.imread(f'{path}/{note_name}')
        all_notes.append(note)

    total=0
    i=0

    for note in all_notes:
        kp2, des2 = orb.detectAndCompute(note, None)

        matches = bf.knnMatch(des2, des1, k=2)

        good = []
        for m, n in matches:
            if m.distance < 0.7*n.distance:
                good.append([m])
        total+=len(good)
        i=i+1

    if max < total/i and total/i>=1:
        max = total/i
        result = "This is a bangladeshi 20 taka note."



          ###-----for 50-----
    #reading all data note
    path = "./Data/Image/Training/50"
    all_notes = []
    all_Note_Name = []
    noteList = os.listdir(path)

    for note_name in noteList:
        note = image.imread(f'{path}/{note_name}')
        all_notes.append(note)

    total=0
    i=0

    for note in all_notes:
        kp2, des2 = orb.detectAndCompute(note, None)

        matches = bf.knnMatch(des2, des1, k=2)

        good = []
        for m, n in matches:
            if m.distance < 0.7*n.distance:
                good.append([m])
        total+=len(good)
        i=i+1

    if max < total/i and total/i>=1:
        max = total/i
        result = "This is a bangladeshi 50 taka note."




          ###-----for 100 tk-----
    #reading all data note
    path = "./Data/Image/Training/100"
    all_notes = []
    all_Note_Name = []
    noteList = os.listdir(path)

    for note_name in noteList:
        note = image.imread(f'{path}/{note_name}')
        all_notes.append(note)

    total=0
    i=0

    for note in all_notes:
        kp2, des2 = orb.detectAndCompute(note, None)

        matches = bf.knnMatch(des2, des1, k=2)

        good = []
        for m, n in matches:
            if m.distance < 0.7*n.distance:
                good.append([m])
        total+=len(good)
        i=i+1

    if max < total / i and total / i >= 1:
        max = total / i
        result="This is a bangladeshi 100 taka note"




          ###-----for 500 tk-----
    #reading all data note
    path = "./Data/Image/Training/500"
    all_notes = []
    all_Note_Name = []
    noteList = os.listdir(path)

    for note_name in noteList:
        note = image.imread(f'{path}/{note_name}')
        all_notes.append(note)
        #print(note_name)

    total=0
    i=0

    for note in all_notes:
        kp2, des2 = orb.detectAndCompute(note, None)

        matches = bf.knnMatch(des2, des1, k=2)

        good = []
        for m, n in matches:
            if m.distance < 0.7*n.distance:
                good.append([m])
        total+=len(good)
        i=i+1

    if max < total / i and total / i >= 1:
        max = total / i
        result="This is a bangladeshi 500 taka note"




          ###-----for 1000 tk-----
    #reading all data note
    path = "./Data/Image/Training/1000"
    all_notes = []
    all_Note_Name = []
    noteList = os.listdir(path)

    for note_name in noteList:
        note = image.imread(f'{path}/{note_name}')
        all_notes.append(note)

    total=0
    i=0

    for note in all_notes:
        kp2, des2 = orb.detectAndCompute(note, None)

        matches = bf.knnMatch(des2, des1, k=2)

        good = []
        for m, n in matches:
            if m.distance < 0.7*n.distance:
                good.append([m])
        total+=len(good)
        i=i+1

    if max < total / i and total / i >= 1:
        max = total / i
        result="This is a bangladeshi 1000 taka note"

    return result




#print(result)





#notekp1 = image.drawKeypoints(note, kp1, None)
#reffkp2 = image.drawKeypoints(reff, kp2, None)

#matched = image.drawMatchesKnn(note,kp1, reff, kp2, good, None, flags=2)
#print(len(good))

#image.imshow("Note", note)
#image.imshow("Refference", reff)
#image.imshow("Matched", matched)
#image.waitKey(0)
#image.destroyAllWindows()
