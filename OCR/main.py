import pytesseract
import cv2

pytesseract.pytesseract.tesseract_cmd = "C:\\Program Files\\Tesseract-OCR\\tesseract.exe"
# Read image with opencv
image = cv2.imread("images/ktp4.jpg")

# Convert image to gray
color = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

#Detect Character
#heightImg, widthImg,_ = image.shape
#boxes = pytesseract.image_to_boxes(image)
#for i in boxes.splitlines():
    #print(i)
    #i = i.split(' ')
    #print(i)
    #a, b, c, d = int(i[1]), int(i[2]), int(i[3]), int(i[4])
    #cv2.rectangle(image, (a,heightImg- b), (c,heightImg- d), (50, 50, 255), 2)
    #cv2.putText(image,i[0],(a,heightImg- b+25),cv2.FONT_HERSHEY_SIMPLEX,1,(50,50,255),2)

# Remove some noise
blur = cv2.GaussianBlur(color,(1,1), 0)
change = cv2.threshold(blur, 0, 255, cv2.THRESH_BINARY_INV + cv2.THRESH_OTSU)[1]
kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (3,3))


#Recognize text with tesseract for python
result = pytesseract.image_to_string(change, lang='eng', config='--psm 6')
print(result)

# Allows users to display a window
#cv2.imshow('input', image)
cv2.imshow('input', change)
cv2.waitKey(0)
