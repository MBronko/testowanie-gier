import os
from selenium import webdriver
from selenium.webdriver.support.ui import Select, WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement

driver = webdriver.Firefox()

driver.get('file:///' + os.path.abspath("page/index.html"))

driver.find_element(by=By.PARTIAL_LINK_TEXT, value="Contact").click()

WebDriverWait(driver, 30).until(
    EC.presence_of_element_located((By.CSS_SELECTOR, '#contact_form > input[type="submit"]')))  # zadanie 5

ids = [
    "name",
    "surname",
    "email",
    "message",
    "country"
]

elems: dict[str, WebElement] = {_id: driver.find_element(by=By.ID, value=_id) for _id in ids}

for elem in list(elems.values())[:3]:
    if elem.get_attribute("value") == '':
        print("pusty")

if elems["message"].text == '':
    print("pusty")

text_values = [
    "Test Name",
    "Test Surname",
    "Test Email",
    "Test Message"
]
option_name = "Poland"

select = Select(elems["country"])
select.select_by_visible_text(option_name)

for value, elem in zip(text_values, elems.values()):
    elem.clear()
    elem.send_keys(value)

driver.save_screenshot("ss.png")

driver.find_element(by=By.CSS_SELECTOR, value='#contact_form > input[type="submit"]').click()

WebDriverWait(driver, 10).until(EC.alert_is_present())  # zadanie 5

driver.switch_to.alert.accept()

driver.close()
