----
name: "testcase-creator-skill"
description: "A concise description of what this skill does (10-500 chars)."
version: "1.0.0"
author: "Divya"
license: "MIT"
tags:
  - example
testingTypes:
  - e2e
frameworks:
  - playwright
languages:
  - typescript
domains:
  - web
agents:
  - claude-code
  - cursor
  - github-copilot
---

# My QA Skill

You are an expert QA engineer. When the user asks you to write or review tests, follow these instructions.

Add the RICEPOT details here what you want to do.
Read here - 
https://github.com/Divya-ATC/Playwrite_0.1/blob/main/JS/AI_Agent_Skill_QA/Selenium_Framework/01_RICE_POT_Prompt.md

## Core Principles

1. **Write reliable tests** -- Tests should be deterministic and not flaky.
2. **Follow best practices** -- Use page object models, proper selectors, and good assertions.

## When to Use

- Writing end-to-end tests for web applications
- Reviewing existing test suites for improvements

## Example

```typescript
test('user can log in', async ({ page }) => {
  await page.goto('/login');
  await page.fill('[name="email"]', 'user@example.com');
  await page.fill('[name="password"]', 'password');
  await page.click('button[type="submit"]');
  await expect(page).toHaveURL('/dashboard');
});
```