# Software Test Plan (STP)
## VWO – Digital Experience Optimization & Conversion Rate Optimization Platform

**Document Reference:** VWO-STP-2026-V1.0  
**Target Application:** [VWO Platform](https://app.vwo.com/)  
**Document Status:** Ready for Review / Sign-off  
**Prepared By:** QA Engineering Team & AI QA Specialist  
**Reviewed By:** Pramod Dutta (Product Management & QA Lead)  
**Date of Document:** January 7, 2026 (Updated: October 2026)  

---

## 1. Document Control & Revision History

| Version | Date | Author | Description of Changes | Approved By |
| :--- | :--- | :--- | :--- | :--- |
| 1.0 | Oct 2026 | QA Team / Pramod Dutta | Initial comprehensive Test Plan based on VWO Platform PRD | QA & Engineering Leads |

---

## 2. Objective

The primary objective of this Test Plan is to provide a structured, robust, and production-ready quality assurance framework for the **VWO (Visual Website Optimizer) Digital Experience Optimization (DXO) and Conversion Rate Optimization (CRO) Platform** (accessible at `https://app.vwo.com/`).

The testing process ensures that VWO delivers:
1. **Flawless Experimentation & Statistical Accuracy:** Reliable execution of A/B, Split URL, and Multivariate Testing (MVT) powered by the Bayesian **SmartStats** statistical engine.
2. **Sub-second Low-latency Client Experience:** Seamless asynchronous delivery of test variations via the VWO client snippet with zero perceptual flicker (<50ms execution overhead) and editing console responsiveness within 2 seconds.
3. **Behavioral Insights Integrity:** 100% data fidelity for user interactions across Heatmaps (clicks, scrolls, mouse movements), Session Recordings, On-page Surveys, and Funnels.
4. **Data Privacy, Governance & Security:** Uncompromising adherence to GDPR, CCPA, 2FA, Role-Based Access Control (RBAC), and SOC2/enterprise compliance standards with comprehensive PII masking.
5. **Seamless Ecosystem Interoperability:** High-fidelity bidirectional synchronization across third-party analytics (Google Analytics, Mixpanel), CDPs (Segment), CRMs (Salesforce), and eCommerce platforms (Shopify).

---

## 3. Scope of Testing

### 3.1 In-Scope Testing Types

1. **Functional Testing:**
   - Verification of all user flows across Campaign Creation, Variation Editor, Audience Targeting, Metrics Configuration, Campaign Launch, and Conclusion.
   - Comprehensive CRUD validation on Experiments, Goals, Segments, Personalization rules, and Integrations.
2. **Data Validation & Statistical Testing:**
   - Validation of Bayesian SmartStats calculations (Conversion Rate probability distributions, Chance to Beat Control, Credible Intervals).
   - Event tracking accuracy, bounce rate calculations, visitor deduplication, and goal completion triggers.
3. **Performance & Latency Testing:**
   - Web application UI responsiveness (verifying all core authoring workflows respond within 2.0 seconds).
   - Lightweight evaluation of the tracking snippet payload and edge CDN delivery speed.
4. **Security & Access Control (RBAC) Testing:**
   - Multi-factor authentication (2FA/MFA), SSO (SAML/Okta), and session lifecycle (timeouts, token invalidation).
   - Permission segregation across Admin, Marketer, Designer, and Analyst roles.
   - Protection against OWASP Top 10 vulnerabilities (SQLi, Stored/Reflected XSS in code editor, CSRF, IDOR).
5. **Data Privacy & Compliance Testing:**
   - Compliance with GDPR, CCPA, and regional cookie consent banners.
   - Session recording PII masking (passwords, credit card numbers, personal emails).
6. **Cross-Browser & Cross-Device Compatibility Testing:**
   - Validation across Windows, macOS, Android, and iOS across Chrome, Firefox, Safari, and Edge.
7. **Usability & Editor Workflow Testing:**
   - WYSIWYG Visual Editor (drag-and-drop, element reordering, inline text edits, modal creation).
   - Developer Code Editor (syntax validation, custom CSS/JS injection, DOM tree inspection).
8. **Integration & API Testing:**
   - Third-party webhook dispatch and REST API synchronization (Google Analytics, Mixpanel, Segment, Shopify, Salesforce, Snowflake).
9. **Concurrency & Load Testing:**
   - High-concurrency event ingestion: Validating that high traffic volumes (millions of impressions) are buffered and processed without dropped events or reporting lag.
10. **Regression & Smoke Testing:**
    - Automated CI/CD sanity checks on deployment pipelines to maintain platform stability.

### 3.2 Out-of-Scope (Exclusions)
- Internal uptime and SLA of third-party destination servers (e.g., outages on Google Analytics or Mixpanel servers).
- Non-standard/legacy end-of-life web browsers (e.g., Internet Explorer 11).
- Direct performance testing of external client-owned web servers hosting client websites.

---

## 4. Inclusions: Feature-by-Feature CRUD & Workflow Matrix

### 4.1 Experimentation & Testing Engine (A/B, Split URL, MVT)
- **POST (Create):** Create new A/B, Split URL, and MVT experiments; define target URLs, multiple variations, and conversion goals.
- **GET (Read/Inspect):** Preview variations across responsive viewport simulators; inspect active campaign parameters.
- **PUT (Modify):** Adjust traffic split allocation percentages, modify audience targeting conditions, and alter hypothesis descriptions.
- **DELETE/ARCHIVE:** Pause active campaigns, soft-delete, archive historical experiments, and restore archived campaigns.
- **Winner Promotion:** Conclude test, apply winning variation to 100% of traffic directly from the VWO console.

### 4.2 Visual & Code Editor
- Point-and-click element editing, text rewriting, CSS style replacement, and image swapping.
- Custom JavaScript and CSS code editor with linting, error trapping, and anti-flicker preview.
- Undo/Redo history, responsive breakpoint toggles (Mobile, Tablet, Desktop), and selector specificity checks.

### 4.3 SmartStats Engine (Bayesian Analysis)
- Verify continuous calculation of "Chance to Beat Control" and "Expected Conversion Rate".
- Validate statistical stopping rules and false discovery rate (FDR) control guardrails.
- Ensure sample size adequacy thresholds prevent premature campaign conclusions.

### 4.4 Behavioral Insights (VWO Insights)
- **Heatmaps:** Click maps, scroll depth percentage markers, and mouse movement/focus heatmaps across device viewports.
- **Session Recordings:** Continuous recording capture, player controls (playback speed, skip inactivity), keystroke anonymization, and PII input masking.
- **On-Page Surveys & Feedback:** Trigger conditions (exit intent, scroll trigger, time-on-page), survey response collection, and rating aggregations.
- **Funnel Analytics:** Multi-step funnel definition, step conversion rate calculation, and drop-off point diagnostics.

### 4.5 Audience Targeting & Personalization
- Dynamic user segmentation based on Geo-location (country/city), device/browser, referral source, query parameters, and custom cookies.
- Real-time personalization rule engine delivering customized content instantly to matched visitor personas.

### 4.6 Ecosystem Integrations
- Event forwarding to Google Analytics (GA4) and Mixpanel via client-side data layer triggers.
- Server-side CDP ingestion via Segment and sync to Snowflake data warehouses.
- Pre-built CMS/eCommerce plugin interoperability (Shopify, WordPress, Drupal, Salesforce).

### 4.7 Program & Workflow Management (VWO Plan)
- Experiment ideation backlog, hypothesis card creation, Kanban board state transitions (Idea -> In Review -> Development -> Live -> Concluded).
- Multi-user commenting, task assignments, and activity audit trails.

---

## 5. Test Environments & Compatibility Matrix

### 5.1 Infrastructure Environments
| Environment | URL / Endpoint | Purpose | Access Protocols |
| :--- | :--- | :--- | :--- |
| **QA / Staging** | `https://qa-app.vwo.com/` | Feature testing, API verification, test case execution | VPN + 2FA / Okta SSO |
| **Pre-Production** | `https://preprod-app.vwo.com/` | Final acceptance, cross-browser QA, regression suite | Restricted IP + 2FA |
| **Production** | `https://app.vwo.com/` | Sanity checks, canary deployment validation | Enterprise Role Access |

### 5.2 Device & Browser Compatibility Matrix
| Operating System | Browsers Supported | Device Types Tested | Network Conditions |
| :--- | :--- | :--- | :--- |
| **Windows 11 / 10** | Google Chrome (Latest), Mozilla Firefox (Latest), MS Edge (Latest) | Desktop, Laptop (1920x1080, 1366x768) | Broadband (100 Mbps), Throttled 4G |
| **macOS (Sonoma / Ventura)** | Safari (Latest), Google Chrome (Latest) | MacBook Pro / Air (Retina Displays) | High-speed Wi-Fi |
| **Android 13 / 14** | Google Chrome Mobile, Samsung Internet | Samsung Galaxy S23/S24, Google Pixel 8 | 5G, 4G LTE, Throttled 3G |
| **iOS 17 / 18** | Apple Safari Mobile, Chrome for iOS | iPhone 15 Pro, iPhone 14, iPad Pro | Wi-Fi, 5G Cellular |

---

## 6. Defect Reporting, Severity & Triage Procedure

### 6.1 Defect Lifecycle
```
[New] --> [Triaged / Assigned] --> [In Progress] --> [Resolved / Fixed] --> [Verified / Retested] --> [Closed]
                                           |
                                           v
                                   [Rejected / Deferred]
```

### 6.2 Severity and Priority Classification
| Level | Severity (Impact) | Priority (Urgency) | Example Scenario | SLA for Resolution |
| :--- | :--- | :--- | :--- | :--- |
| **Critical (P1)** | Platform outage, visitor page break, snippet halts host website | Immediate hotfix | Tracking snippet causes JavaScript error that blocks host site checkout | < 4 hours |
| **High (P2)** | Core feature broken without workaround | Next sprint / urgent | SmartStats engine not calculating conversion metrics; Visual Editor fails to save | < 24 hours |
| **Medium (P3)** | Feature impaired but functional workaround exists | Normal sprint cycle | Heatmap rendering misaligned by 5px on specific viewport; filter dropdown lag | < 5 business days |
| **Low (P4)** | Cosmetic flaw, minor UI typo | Backlog grooming | Text misalignment in settings modal; tooltip typo | Next release |

### 6.3 Point of Contact (POC) Matrix
- **QA Lead / Automation:** Divya / QA Specialist
- **Product Management:** Pramod Dutta
- **Frontend Engineering POC:** Devesh
- **Backend & Data Science POC:** Sonal
- **DevOps & Infrastructure POC:** Prajeeth
- **Defect Tracking Tool:** JIRA (`https://jira.vwo.internal/projects/VWO-QA`)

---

## 7. Test Strategy & Test Design Techniques

### 7.1 Test Design Methodologies Applied
- **Boundary Value Analysis (BVA):** Testing visitor allocation boundaries (0%, 1%, 99%, 100%), timeout thresholds, minimum sample size inputs.
- **Equivalence Class Partitioning (ECP):** Valid vs. invalid audience segment criteria, supported vs. unsupported URL regex patterns.
- **Decision Table Testing:** Matrix testing of audience targeting rules (e.g., Geo == US AND Device == Mobile AND New Visitor == True).
- **State Transition Testing:** Campaign lifecycle progression (Draft -> Scheduled -> Running -> Paused -> Archived -> Finished).
- **Error Guessing & Exploratory Testing:** Script blocking via AdBlock/Brave Shields, rapid double-clicks on editor buttons, network drops during variation save.

### 7.2 Execution Methodology
1. **Smoke & Sanity Testing:** Run immediately upon new build deployment; 100% pass required to proceed.
2. **Shift-Left Quality:** Static code analysis, PR validation, early contract testing for APIs before UI readiness.
3. **End-to-End User Flow Validation:** Simulating real marketer workflows from hypothesis creation to winner deployment.
4. **Automated Regression Testing:** Nightly Playwright automated regression execution covering critical user journeys.

---

## 8. Test Schedule & Sprint Milestones

| Milestone / Task | Sprint Window | Deliverables | Owner |
| :--- | :--- | :--- | :--- |
| **Phase 1: Test Planning & Strategy** | Week 1 (Days 1–3) | Approved Test Plan, Traceability Matrix | QA Lead / PM |
| **Phase 2: Test Case Design & Automation Setup** | Week 1 (Days 4–5) | Functional & API Test Suites in TestRail/Jira | QA Team |
| **Phase 3: Test Execution Cycle 1 (Core Features)** | Week 2 (Days 1–5) | Execution logs, Defect logs (Jira) | QA Team |
| **Phase 4: Defect Verification & Cycle 2 Execution** | Week 3 (Days 1–3) | Retest reports, Edge case verification | QA Team / Dev |
| **Phase 5: Performance, Security & Cross-Browser QA** | Week 3 (Days 4–5) | Security scan, BrowserStack compatibility log | Security / QA |
| **Phase 6: Final Regression & Test Closure** | Week 4 (Days 1–2) | Test Summary Report, Production Sign-off | QA Lead & Pramod Dutta |

---

## 9. Test Deliverables

1. **Comprehensive Test Plan (This Document):** Baseline for all verification activities.
2. **Test Scenarios & Test Cases Document:** Granular step-by-step test cases mapped to PRD Functional Requirements (FR1 to FR9).
3. **Automated Test Scripts:** Playwright regression test suite for core user journeys.
4. **Daily Defect Status & Triage Reports:** EOD summaries published to development stakeholders.
5. **Final Test Summary & Sign-Off Report:** Executive quality metrics, defect distribution, and launch recommendation.

---

## 10. Entry and Exit Criteria

### 10.1 Requirement Analysis & Planning
- **Entry:** Receipt of finalized PRD from Pramod Dutta.
- **Exit:** Clarifications resolved; Test Plan signed off.

### 10.2 Test Execution Phase
- **Entry:** Stable build deployed on QA/Staging environment; Smoke tests pass 100%; Test cases authored and reviewed.
- **Exit:** 100% test cases executed; Zero Critical (P1) or High (P2) unresolved defects; 95%+ test pass rate overall.

### 10.3 Test Closure Phase
- **Entry:** All planned test cycles completed; All verified bugs closed in JIRA.
- **Exit:** Final Test Summary Report approved by Product Manager (Pramod Dutta) and Engineering Leads.

---

## 11. Tools & Infrastructure

| Tool Category | Tool Name | Purpose |
| :--- | :--- | :--- |
| **Test Management & Bug Tracking** | JIRA / TestRail | Defect tracking, test case repository, sprint planning |
| **Test Automation Framework** | Playwright (Node.js/TypeScript) | End-to-end browser automation, visual regression |
| **API & Backend Testing** | Postman / Newman / REST Assured | RESTful API endpoint validation, contract testing |
| **Cross-Browser Cloud Testing** | BrowserStack | Real device cloud testing across OS/Browser matrices |
| **Performance & Load Testing** | k6 / Apache JMeter | Ingestion throughput, response time latency profiling |
| **Security & Privacy Testing** | OWASP ZAP, Burp Suite | Vulnerability scanning, token validation, PII checks |
| **Team Collaboration** | Slack & Confluence | Status broadcasts, team alignment, meeting logs |

---

## 12. Risks and Mitigations

| Risk Description | Severity | Impact | Mitigation Strategy |
| :--- | :--- | :--- | :--- |
| **Flicker (FOOC - Flash of Original Content)** | High | Visitor sees original page before variation loads | Rigorously test and validate the VWO Asynchronous Anti-Flicker snippet under varied network latencies. |
| **Tracking Discrepancy with Google Analytics** | High | Customer disputes conversion numbers | Perform automated dual-tracking reconciliation tests to verify discrepancy stays within standard ±3% tolerance. |
| **High Traffic Spikes Crashing Ingestion** | Critical | Data loss during high-volume client campaigns | Conduct load testing on data ingestion endpoints up to 50,000 req/sec; implement resilient message queuing. |
| **Third-Party Script Blocking (AdBlockers)** | Medium | Tests not delivered to certain users | Implement First-Party Domain Proxying / Custom Domain tracking validation tests. |
| **Resource Unavailability / Sick Leave** | Medium | Schedule delays | Maintain paired automation responsibilities and cross-training across QA team members. |

---

## 13. Approvals & Sign-Off Matrix

By signing below, the stakeholders confirm that this Test Plan accurately reflects the testing requirements and quality standards required for the VWO platform release.

| Role | Name | Designation | Signature / Approval Status | Date |
| :--- | :--- | :--- | :--- | :--- |
| **Author / QA Lead** | Divya | Senior QA Automation Specialist | Approved | October 2026 |
| **Product Management** | Pramod Dutta | Principal Product Manager (VWO) | Pending Final Sign-Off | October 2026 |
| **Frontend Engineering** | Devesh | Lead Frontend Architect | Approved | October 2026 |
| **Backend & Data Science** | Sonal | Lead Backend & Data Systems Engineer | Approved | October 2026 |
| **DevOps & Infrastructure** | Prajeeth | Lead DevOps & Infrastructure Engineer | Approved | October 2026 |
