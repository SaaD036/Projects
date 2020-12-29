import cv2 as image
import tkinter as tk
import os

from tkinter import *
from PIL import ImageTk,Image
from tkinter import filedialog
from Detection import check

root=tk.Tk()
root.title("Bangladeshi currencies")
root.geometry("800x500")
root.configure(background="#000000")

def open():
    global my_image
    root.filename = filedialog.askopenfilename(initialdir="/", title="Select file", filetypes=(("jpeg files", "*.jpg"), ("all files", "*.*")))

    selected_folder_name = Label(root, text=root.filename, fg="#ffffff", bg="#000000")
    selected_folder_name.place(x=200, y=150, height=50, width=300)

    my_image = ImageTk.PhotoImage(Image.open(root.filename))
    reff = image.imread(root.filename)
    new_reff = image.resize(reff, (300, 180))

    note_label=Label(root, image=my_image, bg="#000000")
    note_label.place(x=20, y=220, height=200, width=300)

    result = check(reff)
    res_label = Label(root, text=result, fg="#ffffff", bg="#000000", font="Times 20 bold")
    res_label.place(x=320, y=260, width=430, height=120)

cover_pic=ImageTk.PhotoImage(file="./Data/Image/cover.JPG")
cover = Label(root, image=cover_pic, bg="#000000")
cover.place(x=0, y=0, height=100, width=800)


folder = ImageTk.PhotoImage(file='./Data/Image/folder.png')
my_label_folder = Label(root, image=folder, bg="#000000")
my_label_folder.place(x=140, y=150, height=50, width=50)

browse = Button(root, text="Browse", fg="#ffffff", bg="blue", font="Times 12 bold", border=0, activebackground="green", command=open)
browse.place(x=510, y=155, height=45, width=90)

p = Label(root, text="", bg="#ffffff")
p.place(x=130, y=200, height=1, width=350)

copyriht_image = ImageTk.PhotoImage(file='./Data/Image/copyright.png')
copy_r = Label(root, image=copyriht_image, font="Times 12", bg="#000000")
copy_r.place(x=253, y=435, height=70, width=70)
q = Label(root, text="All copyright goes to ", font="Times 12", fg="#ffffff", bg="#000000")
q.place(x=310, y=450, height=40, width=140)
r = Label(root, text="SaaD's ", font="Times 15 bold", fg="blue", bg="#000000")
r.place(x=445, y=450, height=40, width=70)

root.mainloop()