import cv2 as image
import pytesseract

pytesseract.pytesseract.tesseract_cmd = 'C:\\Program Files (x86)\\Tesseract-OCR\\tesseract.exe'

note = image.imread('Data/Image/Training/1000/1000_13.jpg')
#note = image.cvtColor(note, image.COLOR_BGR2RGB)
boxes = pytesseract.image_to_boxes(note)
h_img, w_img, _ = note.shape

for b in boxes.splitlines():
    b = b.split(' ')
    x, y, w, h = int(b[1]), int(b[2]), int(b[3]), int(b[4])
    image.rectangle(note, (x,h_img-y), (w,h_img-h), (0, 0, 255), 1)

#print(pytesseract.image_to_boxes(note))
image.imshow("Note", note)
image.waitKey(0)