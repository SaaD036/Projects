import cv2 as image
import tkinter as tk

from tkinter import *
from PIL import ImageTk,Image
from tkinter import filedialog


root = tk.Tk()
root.title('BD currency Recognition')
root.geometry("800x550")

result=""

def open():
	global my_image
	root.filename = filedialog.askopenfilename(initialdir = "/",title = "Select file",filetypes = (("jpeg files","*.jpg"),("all files","*.*")))
	my_label = Label(root,text = root.filename).pack()
	my_image = ImageTk.PhotoImage(Image.open(root.filename))
	reff = image.imread(root.filename)
	my_image_label = Label(image = my_image).pack()

	result = "SaaD"

	return reff

my_btn = Button(root,text = "open file",command = open).pack()


print(result)

root.mainloop()