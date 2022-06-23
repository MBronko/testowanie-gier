from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()

driver.get("https://en.wikipedia.org/")

WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID, "searchInput")))

driver.find_element(by=By.ID, value="searchInput").send_keys("python")

driver.find_element(by=By.ID, value="searchButton").click()

WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID, "firstHeading")))

elems = driver.find_elements(by=By.PARTIAL_LINK_TEXT, value="Python")

for elem in elems:
    url = elem.get_attribute("href")

    link_driver = webdriver.Firefox()
    link_driver.get(url)

    WebDriverWait(link_driver, 30).until(EC.presence_of_element_located((By.ID, "firstHeading")))

    selector = "//div[@id='bodyContent']//p[.//text()[normalize-space()]]"
    print(f'{url}: {link_driver.find_element(By.XPATH, selector).text}')

    link_driver.close()

driver.close()
