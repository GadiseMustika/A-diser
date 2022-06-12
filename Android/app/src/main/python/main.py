import pytesseract
import numpy as np
import urllib
import cv2

pytesseract.pytesseract.tesseract_cmd = "C:\\Program Files\\Tesseract-OCR\\tesseract.exe"

def ocr(url):
    # Read image with opencv
    resp = urllib.urlopen(url)
    image = np.asarray(bytearray(resp.read()), dtype="uint8")
    image = cv2.imdecode(image, cv2.IMREAD_COLOR)

    # Convert image to gray
    color = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Remove some noise
    blur = cv2.GaussianBlur(color,(1,1), 0)
    thresh = cv2.threshold(blur, 0, 255, cv2.THRESH_BINARY_INV + cv2.THRESH_OTSU)[1]
    kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (3,3))

    # Recognize text with tesseract for python
    result = pytesseract.image_to_string(thresh, lang='eng', config='--psm 6')
    return result
