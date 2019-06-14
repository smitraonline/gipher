import { AppPage } from './app.po';
import { browser, logging, by, element } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title of application', () => {
    page.navigateTo();
    browser.driver.sleep(5000);
    expect(browser.getTitle()).toEqual('Gipher - Express Yourself');
    browser.driver.sleep(1000);
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click();
    browser.driver.sleep(4000);
    expect(browser.getCurrentUrl()).toContain('/register');
    browser.driver.sleep(1000);
  });

  it('should be able to register user', () => {
    browser.element(by.id('username')).sendKeys('test123');
    browser.element(by.id('email')).sendKeys('test123@email.com');
    browser.element(by.id('password')).sendKeys('test123');
    browser.element(by.css('.register-user')).click();
  });

  it('should be able to login user', () => {
    browser.driver.sleep(5000);
    browser.element(by.id('username')).sendKeys('test123');
    browser.element(by.id('password')).sendKeys('test123');
    browser.element(by.css('.login-button')).click();
  });

  it('should be able to save Gif to Bookmark', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(3000);
    browser.element(by.css('.add-favorite')).click();
    browser.driver.sleep(5000);
  });

  it('should be able to click on Menu item for Bookmark', () => {
    browser.driver.sleep(5000);
    browser.element(by.id('menu_explore')).click();
    browser.driver.sleep(1000);
    browser.element(by.id('menu_bookmarked')).click();
    expect(browser.getCurrentUrl()).toContain('/favourite');
    browser.driver.sleep(1000);
  });

  it('should be able to click on Menu item for Recommendation', () => {
    browser.driver.sleep(5000);
    browser.element(by.id('menu_explore')).click();
    browser.driver.sleep(1000);
    browser.element(by.id('menu_recommended')).click();
    expect(browser.getCurrentUrl()).toContain('/recommendations');
    browser.driver.sleep(1000);
  });

  it('should be able to click on Menu item for History', () => {
    browser.driver.sleep(5000);
    browser.element(by.id('menu_explore')).click();
    browser.driver.sleep(1000);
    browser.element(by.id('menu_history')).click();
    expect(browser.getCurrentUrl()).toContain('/history');
    browser.driver.sleep(1000);
  });

  it('should be able to logout from application', () => {
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-logout')).click();
    browser.driver.sleep(500);
  });
});
