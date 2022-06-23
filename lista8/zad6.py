import os
from selenium import webdriver
from selenium.webdriver.support.ui import Select, WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By


class MainPageLocators:
    CONTACT_BUTTON = (By.PARTIAL_LINK_TEXT, "Contact")


class ContactPageLocators:
    NAME_INPUT = (By.ID, "name")
    SURNAME_INPUT = (By.ID, "surname")
    EMAIL_INPUT = (By.ID, "email")
    MESSAGE_TEXTAREA = (By.ID, "message")
    COUNTRY_OPTION = (By.ID, "country")
    FORM_SUBMIT_BUTTON = (By.CSS_SELECTOR, '#contact_form > input[type="submit"]')


class BasePageElement:
    locator = By.ID, ''

    def __set__(self, obj, value):
        driver = obj.driver
        elem = driver.find_element(*self.locator)
        elem.clear()
        elem.send_keys(value)

    def __get__(self, obj, owner):
        driver = obj.driver
        elem = driver.find_element(*self.locator)
        return elem.get_attribute("value")


class NameElement(BasePageElement):
    locator = By.ID, "name"


class SurnnameElement(BasePageElement):
    locator = By.ID, "surname"


class EmailElement(BasePageElement):
    locator = By.ID, "email"


class MessageElement(BasePageElement):
    locator = By.ID, "message"

    def __get__(self, obj, owner):
        driver = obj.driver
        elem = driver.find_element(*self.locator)
        return elem.text


class CountryElement(BasePageElement):
    locator = By.ID, "country"

    def __set__(self, obj, value):
        driver = obj.driver
        elem = driver.find_element(*self.locator)
        select = Select(elem)
        select.select_by_visible_text(value)


class BasePage:
    def __init__(self, driver):
        self.driver = driver


class MainPage(BasePage):

    def __init__(self, driver):
        super().__init__(driver)

    def go_to_contact(self):
        self.driver.find_element(*MainPageLocators.CONTACT_BUTTON).click()
        WebDriverWait(self.driver, 30).until(
            EC.presence_of_element_located(ContactPageLocators.FORM_SUBMIT_BUTTON))  # zadanie 5


class ContactPage(BasePage):
    name_element = NameElement()
    surname_element = SurnnameElement()
    email_element = EmailElement()
    message_element = MessageElement()
    country_element = CountryElement()

    def __init__(self, driver):
        super().__init__(driver)

    def submit_form(self):
        self.driver.find_element(*ContactPageLocators.FORM_SUBMIT_BUTTON).click()

        WebDriverWait(self.driver, 10).until(EC.alert_is_present())  # zadanie 5

        self.driver.switch_to.alert.accept()

    def screen_shot(self, filename):
        self.driver.save_screenshot(filename)


class TestContactForm:
    def __init__(self):
        self.driver = webdriver.Firefox()

        self.driver.get('file:///' + os.path.abspath("page/index.html"))

    def fill_and_submit_contact_form(self):
        main_page = MainPage(self.driver)

        main_page.go_to_contact()

        contact_page = ContactPage(self.driver)

        if contact_page.name_element == '':
            print("pusty")
        if contact_page.surname_element == '':
            print("pusty")
        if contact_page.email_element == '':
            print("pusty")
        if contact_page.message_element == '':
            print("pusty")

        contact_page.name_element = "Test Name"
        contact_page.surname_element = "Test Surname"
        contact_page.email_element = "Test Email"
        contact_page.country_element = "Poland"
        contact_page.message_element = "Test Message"

        contact_page.screen_shot("ss.png")

        contact_page.submit_form()

    def tear_down(self):
        self.driver.close()


test_contact_form = TestContactForm()
test_contact_form.fill_and_submit_contact_form()
test_contact_form.tear_down()
