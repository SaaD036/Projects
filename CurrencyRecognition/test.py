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

def open():
    global my_image
    root.filename = filedialog.askopenfilename(initialdir="/", title="Select file", filetypes=(("jpeg files", "*.jpg"), ("all files", "*.*")))
    my_label = Label(root, text=root.filename).pack()
    my_image = ImageTk.PhotoImage(Image.open(root.filename))
    reff = image.imread(root.filename)
    new_reff = image.resize(reff, (300, 180))

    result = check(reff)

    res_label = Label(root, text=result, fg="#000000")
    res_label.place(x=300, y=150, width=200, height=80)

folder_name: Label = Label(root, text="", fg="#ffffff", bg="#000000")
folder_name.place(x=30, y=50, height=20, width=120)


browse = Button(root, text="Browse", fg="#ffffff", bg="#000000", command=open)
browse.place(x=160, y=50, height=50, width=80)

root.mainloop()