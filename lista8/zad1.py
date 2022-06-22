import os
from selenium import webdriver
from selenium.webdriver.support.ui import Select
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement

driver = webdriver.Firefox()

driver.get(os.path.abspath("page/index.html"))

driver.find_element(by=By.PARTIAL_LINK_TEXT, value="Contact").click()

# textarea_elems = driver.find_elements(by=By.TAG_NAME, value="textarea")
# text_input_elems = driver.find_elements(by=By.CSS_SELECTOR, value='#contact_form > input[type="text"]')

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
        print("text pusty")

if elems['message'].text == '':
    print("textarea puste")

text_values = [
    "Test Name",
    "Test Surname",
    "Test Email",
    "Test Message"
]
option_name = "Poland"

select = Select(elems['country'])
select.select_by_visible_text(option_name)

for value, elem in zip(text_values, elems.values()):
    elem.send_keys(value)

driver.save_screenshot("ss.png")

driver.find_element(by=By.CSS_SELECTOR, value='#contact_form > input[type="submit"]').click()

driver.switch_to.alert.accept()
