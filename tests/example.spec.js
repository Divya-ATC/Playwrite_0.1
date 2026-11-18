const { test, expect } = require('@playwright/test');

test.describe('JavaScript Playwright Example', () => {
  test('basic local browser test', async ({ page }) => {
    await page.setContent('<h1>Hello Playwright!</h1>');
    await expect(page.locator('h1')).toHaveText('Hello Playwright!');
  });

  test('has title', async ({ page }) => {
    await page.goto('https://playwright.dev/');
    await expect(page).toHaveTitle(/Playwright/);
  });

  test('get started link', async ({ page }) => {
    await page.goto('https://playwright.dev/');
    await page.getByRole('link', { name: 'Get started' }).click();
    await expect(page.getByRole('heading', { name: 'Installation' })).toBeVisible();
  });
});
