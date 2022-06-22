import os
from selenium import webdriver
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.common.by import By
from urllib import request

options = Options()
options.add_argument("--headless")

driver = webdriver.Firefox(options=options)

driver.get(os.path.abspath("page/index.html"))

driver.find_element(by=By.PARTIAL_LINK_TEXT, value="Gallery").click()

elems = driver.find_elements(by=By.CLASS_NAME, value="gallery")

target_dir = "downloaded_images"

for elem in elems:
    if not os.path.exists(target_dir):
        os.mkdir(target_dir)

    url = elem.get_attribute("src")
    filename = url.split("/")[-1]

    print(url)

    request.urlretrieve(elem.get_attribute("src"), f'{target_dir}/{filename}')
